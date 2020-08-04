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
import com.ssafy.ssafience.model.SingleResponse;
import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.ArticleResult;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.board.BoardResult;
import com.ssafy.ssafience.model.board.FreeModifyRequest;
import com.ssafy.ssafience.model.board.FreeWriteRequest;
import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.board.ProgramWriteRequest;
import com.ssafy.ssafience.model.dto.Article;
import com.ssafy.ssafience.model.dto.FreeBoard;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.dto.ProgramBoard;
import com.ssafy.ssafience.service.article.ArticleService;
import com.ssafy.ssafience.service.board.FreeBoardService;
import com.ssafy.ssafience.service.board.ProgramBoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "ProgramBoard : 프로그래밍 게시판")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/program/board")
public class ProgramBoardController {

	private static final Logger logger = LoggerFactory.getLogger(ProgramBoardController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	private ProgramBoardService fService;
	
	@ApiOperation(value = "모든 프로그래밍 게시판 목록 반환")
	@GetMapping
	public ResponseEntity<ListResponse<ProgramBoard>> getBoardList(){
		final ListResponse<ProgramBoard> result = new ListResponse<>();
		try {
			List<ProgramBoard> list = fService.selectBoardList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.setList(list);
			result.message="프로그래밍 게시판 목록 가져오는데 성공했습니다.";				
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="모든 프로그래밍게시판 목록 가져오는 중 문제가 발생했습니다.";
			e.printStackTrace();
		}
		return new ResponseEntity<ListResponse<ProgramBoard>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 회원의 프로그래밍 게시판 목록 반환")
	@GetMapping("/{memberId}")
	public ResponseEntity<ListResponse<ProgramBoard>> getMemberBoardList(@PathVariable String memberId){
		final ListResponse<ProgramBoard> result = new ListResponse<>();
		
		try {
			BoardResult<ProgramBoard> myBoardResult = fService.selectMemberBoardList(memberId);
			if (myBoardResult.isAuthor()) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.setList(myBoardResult.getBoardList());
				result.message="프로그래밍 게시판 목록 가져오는데 성공했습니다.";				
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 회원입니다. 회원가입을 진행해주세요.";				
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="프로그래밍 게시판 목록 가져오는 중 문제가 발생했습니다.";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<ProgramBoard>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 프로그래밍 게시판 게시글 등록")
	@PostMapping
	public ResponseEntity<BasicResponse> insertBoard(@RequestBody ProgramWriteRequest request){
		final BasicResponse result = new BasicResponse();
		
		try {
			int insertBoard = fService.insert(request);
			if (insertBoard == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "일치하는 회원 또는 게시글이 없습니다. 확인하고 다시 시도해주세요..";
			} else if (insertBoard == 1) {
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
	
	@ApiOperation(value = "프로그래밍 게시판 게시글 수정")
	@PutMapping
	public ResponseEntity<BasicResponse> updateBoard(@RequestBody ProgramModifyRequest request){
		final BasicResponse result = new BasicResponse();
		
		try {
			int updateBoard = fService.update(request);
			if (updateBoard == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "해당 게시글의 작성자가 맞는지 확인하고 다시 시도해주세요.";
			} else if (updateBoard == 1) {
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
	
	@ApiOperation(value = "프로그래밍 게시판 게시글 삭제")
	@DeleteMapping("/{boardNo}")
	public ResponseEntity<BasicResponse> deleteBoard(@PathVariable int boardNo){
		final BasicResponse result = new BasicResponse();
		
		try {
			int deleteBoard = fService.delete(boardNo);
			if (deleteBoard == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않는 게시글 입니다. 확인하고 다시 시도해주세요.";
			} else if (deleteBoard == 1) {
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
















