package com.ssafy.ssafience.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ssafy.ssafience.model.ListResponse;
import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.ArticleResult;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.dto.Article;
import com.ssafy.ssafience.service.article.ArticleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Article : 뉴스피드")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/article")
public class ArticleController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	private ArticleService aService;
	
	@ApiOperation(value = "모든 뉴스피드 목록 반환")
	@GetMapping
	public ResponseEntity<ListResponse<Article>> getArticleList(){
		logger.debug("getArticleList 호출");
		final ListResponse<Article> result = new ListResponse<>();
		try {
			List<Article> list = aService.selectArticleList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.setList(list);
			result.message="뉴스피드 목록 가져오는데 성공했습니다.";				
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="모든 뉴스피드 목록 가져오는 중 문제가 발생했습니다.";
		}
		return new ResponseEntity<ListResponse<Article>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 회원의 뉴스피드 목록 반환")
	@GetMapping("/{memberid}")
	public ResponseEntity<ListResponse<Article>> getMemberArticleList(@PathVariable String memberid){
		logger.debug("getMemberArticleList 호출");
		final ListResponse<Article> result = new ListResponse<>();
		
		try {
			ArticleResult myArticleResult = aService.selectMemberArticleList(memberid);
			if (myArticleResult.isMember()) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.setList(myArticleResult.getList());
				result.message="뉴스피드 목록 가져오는데 성공했습니다.";				
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="목록 가져오는데 실패했습니다. 해당 뉴스피드의 작성자가 맞는지 확인하고 다시 시도해주세요";				
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="뉴스피드 목록 가져오는 중 문제가 발생했습니다.";
		}
		
		return new ResponseEntity<ListResponse<Article>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 뉴스피드 등록")
	@PostMapping
	public ResponseEntity<BasicResponse> insertArticle(@RequestBody WriteRequest request){
		logger.debug("insertArticle 호출");
		final BasicResponse result = new BasicResponse();
		
		try {
			int insertArticle = aService.insert(request);
			if (insertArticle == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 회원";
			} else if (insertArticle == 1) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "등록 성공";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "등록 실패";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "등록 중 문제발생";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 수정")
	@PutMapping
	public ResponseEntity<BasicResponse> updateArticle(@RequestBody ArticleModifyRequest request){
		logger.debug("updateArticle 호출");
		final BasicResponse result = new BasicResponse();
		
		try {
			int updateArticle = aService.update(request);
			if (updateArticle == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 피드";
			} else if (updateArticle == 1) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "수정 성공";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "수정 실패";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "수정 중 문제발생";
			e.printStackTrace();
		}

		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "뉴스피드 삭제")
	@DeleteMapping("/{articleno}")
	public ResponseEntity<BasicResponse> deleteArticle(@PathVariable int articleno){
		logger.debug("deleteArticle 호출");
		final BasicResponse result = new BasicResponse();
		
		try {
			int deleteArticle = aService.delete(articleno);
			if (deleteArticle == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 뉴스피드";
			} else if (deleteArticle == 1) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "삭제 성공";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "삭제 실패";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "삭제 중 문제발생";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
		
	}

}
















