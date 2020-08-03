package com.ssafy.ssafience.controller.board;

import java.util.ArrayList;
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
import com.ssafy.ssafience.model.board.FreeBoard;
import com.ssafy.ssafience.model.board.FreeBoardResponse;
import com.ssafy.ssafience.service.board.FreeBoardService;

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
@RequestMapping("/api/free/board")
public class FreeBoardController {
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	@Qualifier("FreeBoardServiceImpl")
	FreeBoardService bService;

	@GetMapping
	@ApiOperation(value = "자유게시판 목록 조회")
	public ResponseEntity<FreeBoardResponse> getBoardList(){
		final FreeBoardResponse result = new FreeBoardResponse();
		List<FreeBoard> freeBoardList = new ArrayList<>();
		
		try {
			freeBoardList = bService.getFreeBoardList();
			result.result=SUCCESS;
			result.status= HttpStatus.OK;
			result.message="총 "+freeBoardList.size()+"건의 게시글이 있습니다.";
			result.freeBoardList = freeBoardList;
		} catch (Exception e) {
			result.result=FAIL;
			result.status= HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="서버로 부터 게시글 목록을 가져 오던 중 오류가 발생했습니다. \n 잠시 후 다시 이용해주세요.";
			e.printStackTrace();
		}
		
		return new ResponseEntity<FreeBoardResponse>(result, HttpStatus.OK);
	}

	@GetMapping("/{freeBoardNo}")
	@ApiOperation(value = "특정 자유게시판 상세 조회")
	public ResponseEntity<FreeBoardResponse> getBoard(@PathVariable int freeBoardNo){
		final FreeBoardResponse result = new FreeBoardResponse();
		
		try {
			FreeBoard boardOne = bService.getBoardOne(freeBoardNo);
			if (boardOne != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message=freeBoardNo+"번 게시글의 상세정보 입니다.";
				result.freeBoard = boardOne;				
			} else {
				result.result=FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않는 공지사항 입니다.";
			}
		} catch (Exception e) {
			result.result=FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = freeBoardNo+"번 게시글을 조회하던 중 문제가 발생했습니다.";
			e.printStackTrace();
		}		
		
		return new ResponseEntity<FreeBoardResponse>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "새로운 자유게시판 게시글 등록")
	public ResponseEntity<BasicResponse> insertBoard(@RequestBody FreeBoard board){
		final BasicResponse result = new BasicResponse();
		
		try {
			FreeBoard newBoard = bService.insertBoard(board);
			if (newBoard != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "자유게시판에 새로운 게시글 등록이 성공적으로 이루어졌습니다. ";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message="새로운 게시글 등록에 실패했습니다. \n 잠시후 다시 시도해주세요";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "자유게시판에 게시글을 등록 하던 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	

	@PutMapping
	@ApiOperation(value = "특정 자유게시판 게시글 수정")
	public ResponseEntity<BasicResponse> updateBoard(@RequestBody FreeBoard board){
		final BasicResponse result = new BasicResponse();
		
		try {
			boolean updateBoard = bService.updateBoard(board);
			if (updateBoard) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "자유게시판에 게시글 수정이 성공적으로 이루어졌습니다.";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message="없는 게시글 입니다. 확인하고 다시 시도헤주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "게시글 수정 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{freeBoardNo}")
	@ApiOperation(value = "특정 자유게시판 게시글 삭제")
	public ResponseEntity<BasicResponse> deleteBoard(@PathVariable int freeBoardNo){
		final BasicResponse result = new BasicResponse();
		
		try {
			boolean deleteBoard = bService.deleteBoard(freeBoardNo);
			if (deleteBoard) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "자유게시판 게시글 삭제가 성공적으로 이루어졌습니다.";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 게시글 입니다. 확인하고 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "게시글 삭제 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
				
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);		
	}
	
}
