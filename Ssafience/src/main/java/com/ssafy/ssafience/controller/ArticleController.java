package com.ssafy.ssafience.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.Article.Article;
import com.ssafy.ssafience.model.Article.ArticleResponse;
import com.ssafy.ssafience.service.ArticleService;
import com.ssafy.ssafience.service.MemberService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@CrossOrigin("*")
@RestController
@RequestMapping("/api/article")
public class ArticleController {
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	@Qualifier("ArticleServiceImpl")
	ArticleService aService;
	
	@Autowired
	@Qualifier("MemberServiceImpl")
	MemberService mService;
	
	@GetMapping
	@ApiOperation(value = "전제 뉴스피드 목록 조회")
	public ResponseEntity<ArticleResponse> getNoticeList(){
		final ArticleResponse result = new ArticleResponse();
		try {
			List<Article> articleList = aService.getArticleList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "총"+articleList.size()+"건의 뉴스피드가 있습니다.";
			result.articleList = articleList;
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.HTTP_VERSION_NOT_SUPPORTED.INTERNAL_SERVER_ERROR;
			result.message = "뉴스피드 목록조회 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ArticleResponse>(result, HttpStatus.OK);
	}

	@GetMapping("/{memberId}")
	@ApiOperation(value = "회원의 뉴스피드 목록 조회")
	public ResponseEntity<ArticleResponse> getMyArticleList(@PathVariable String memberId){

		final ArticleResponse result = new ArticleResponse();
		try {
			if (mService.getMemberOne(memberId) != null) {
				List<Article> articleList = aService.getMyArticleList(memberId);
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "총"+articleList.size()+"건의 뉴스피드가 있습니다.";
				result.articleList = articleList;				
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않는 회원입니다. 확인 후 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.HTTP_VERSION_NOT_SUPPORTED.INTERNAL_SERVER_ERROR;
			result.message = "뉴스피드 목록조회 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ArticleResponse>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "새로운 뉴스피드 등록")
	public ResponseEntity<BasicResponse> insertArtice(@RequestBody Article a){
		final BasicResponse result = new ArticleResponse();
		try {
			if (mService.getMemberOne(a.getMemberId()) != null) {
				Article newArticle = aService.insertArticle(a);
				if (newArticle != null) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message="새로운 뉴스피드 등록이 성공적으로 이루어졌습니다. ";
				} else {
					result.result = FAIL;
					result.status = HttpStatus.NO_CONTENT;
					result.message="새로운 뉴스피드 등록에 실패했습니다. \n 확인 후 다시 시도해주세요.";
				}				
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 회원입니다. 확인 후 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.HTTP_VERSION_NOT_SUPPORTED.INTERNAL_SERVER_ERROR;
			result.message = "뉴스피드 등록 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@PutMapping
	@ApiOperation(value = "뉴스피드 수정")
	public ResponseEntity<BasicResponse> updateArticle(@RequestBody Article a){
		final BasicResponse result = new ArticleResponse();
		
		try {
			if (mService.getMemberOne(a.getMemberId()) != null) {
				boolean updateArticle = aService.updateArticle(a);
				if (updateArticle) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message="뉴스피드 수정이 성공적으로 이루어졌습니다. ";
				} else {
					result.result = FAIL;
					result.status = HttpStatus.NO_CONTENT;
					result.message="해당 뉴스피드가 존재하지 않습니다.. \n 확인 후 다시 시도해주세요.";
				}			
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 회원입니다. 확인 후 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.HTTP_VERSION_NOT_SUPPORTED.INTERNAL_SERVER_ERROR;
			result.message = "뉴스피드 수정 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{articleNo}")
	@ApiOperation(value = "뉴스피드 삭제")
	public ResponseEntity<BasicResponse> deleteArticle(@PathVariable int articleNo){
		final BasicResponse result = new ArticleResponse();
		
		try {
			boolean deleteArticle = aService.deleteArticle(articleNo);
			if (deleteArticle) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message="뉴스피드 삭제가 성공적으로 이루어졌습니다. ";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message="뉴스피드 삭제에 실패했습니다. \n 확인 후 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.HTTP_VERSION_NOT_SUPPORTED.INTERNAL_SERVER_ERROR;
			result.message = "뉴스피드 삭제 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
		
	}

}
















