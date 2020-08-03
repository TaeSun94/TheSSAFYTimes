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
import com.ssafy.ssafience.model.board.ProgramBoard;
import com.ssafy.ssafience.model.board.ProgramBoardResponse;
import com.ssafy.ssafience.service.board.ProgramBoardService;

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
@RequestMapping("/api/board/program")
public class ProgramBoardController {
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	@Qualifier("ProgramBoardServiceImpl")
	ProgramBoardService bService;

	@GetMapping
	@ApiOperation(value = "프로그래밍 게시판 목록 조회")
	public ResponseEntity<ProgramBoardResponse> getBoardList(){
		final ProgramBoardResponse result = new ProgramBoardResponse();
		List<ProgramBoard> programBoardList = new ArrayList<>();
		
		try {
			programBoardList = bService.getProgramBoardList();
			result.result=SUCCESS;
			result.status= HttpStatus.OK;
			result.message="총 "+programBoardList.size()+"건의 게시글이 있습니다.";
			result.programBoardList = programBoardList;
		} catch (Exception e) {
			result.result=FAIL;
			result.status= HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="서버로 부터 게시글 목록을 가져 오던 중 오류가 발생했습니다. \n 잠시 후 다시 이용해주세요.";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ProgramBoardResponse>(result, HttpStatus.OK);
	}

	@GetMapping("/{programBoardNo}")
	@ApiOperation(value = "특정 프로그래밍 게시판 상세 조회")
	public ResponseEntity<ProgramBoardResponse> getBoard(@PathVariable int programBoardNo){
		final ProgramBoardResponse result = new ProgramBoardResponse();
		
		try {
			ProgramBoard boardOne = bService.getBoardOne(programBoardNo);
			if (boardOne != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message=programBoardNo+"번 게시글의 상세정보 입니다.";
				result.programBoard = boardOne;				
			} else {
				result.result=FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않는 게시글 입니다.";
			}
		} catch (Exception e) {
			result.result=FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = programBoardNo+"번 게시글을 조회하던 중 문제가 발생했습니다.";
			e.printStackTrace();
		}		
		
		return new ResponseEntity<ProgramBoardResponse>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "새로운 프로그래밍 게시판 게시글 등록")
	public ResponseEntity<ProgramBoardResponse> insertBoard(@RequestBody ProgramBoard board){
		final ProgramBoardResponse result = new ProgramBoardResponse();
		
		try {
			ProgramBoard newBoard = bService.insertBoard(board);
			if (newBoard != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "프로그래밍 게시판에 새로운 게시글 등록이 성공적으로 이루어졌습니다. ";
				result.programBoard = newBoard;
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message="새로운 게시글 등록에 실패했습니다. \n 잠시후 다시 시도해주세요";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "프로그래밍 게시판에 게시글을 등록 하던 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<ProgramBoardResponse>(result, HttpStatus.OK);
	}
	

	@PutMapping
	@ApiOperation(value = "특정 프로그래밍 게시판 게시글 수정")
	public ResponseEntity<ProgramBoardResponse> updateBoard(@RequestBody ProgramBoard board){
		final ProgramBoardResponse result = new ProgramBoardResponse();
		
		try {
			boolean updateBoard = bService.updateBoard(board);
			if (updateBoard) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "프로그래밍 게시판에 게시글 수정이 성공적으로 이루어졌습니다.";
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
		
		return new ResponseEntity<ProgramBoardResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{programBoardNo}")
	@ApiOperation(value = "특정 프로그래밍 게시판 게시글 삭제")
	public ResponseEntity<ProgramBoardResponse> deleteBoard(@PathVariable int programBoardNo){
		final ProgramBoardResponse result = new ProgramBoardResponse();
		
		try {
			boolean deleteBoard = bService.deleteBoard(programBoardNo);
			if (deleteBoard) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "프로그래밍 게시판 게시글 삭제가 성공적으로 이루어졌습니다.";
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
				
		return new ResponseEntity<ProgramBoardResponse>(result, HttpStatus.OK);		
	}
	
}
