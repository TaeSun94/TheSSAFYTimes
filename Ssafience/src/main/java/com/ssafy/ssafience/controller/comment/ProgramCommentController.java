package com.ssafy.ssafience.controller.comment;

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
import com.ssafy.ssafience.model.comment.ProgramComment;
import com.ssafy.ssafience.model.comment.ProgramCommentResponse;
import com.ssafy.ssafience.service.comment.ProgramCommentService;

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
@RequestMapping("/api/program")
public class ProgramCommentController {
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";

	@Autowired
	@Qualifier("ProgramCommentServiceImpl")
	ProgramCommentService cService;

	@GetMapping("/{programBoardNo}/comment")
	@ApiOperation(value = "특정 프로그래밍 게시판 댓글 목록 조회")
	public ResponseEntity<ProgramCommentResponse> getBoardCommentList(int programBoardNo) {

		final ProgramCommentResponse result = new ProgramCommentResponse();
		List<ProgramComment> programCommentList = new ArrayList<>();

		try {
			programCommentList = cService.getCommentList(programBoardNo);

			if (programCommentList != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "총 " + programCommentList.size() + "건의 댓글이 있습니다.";
				result.programCommentList = programCommentList;
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = " 게시글 또는 댓글이 존재하지 않습니다. \n 확인 후 다시 이용해주세요.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "서버로 부터 댓글 목록을 가져 오던 중 오류가 발생했습니다. \n 잠시 후 다시 이용해주세요.";
			e.printStackTrace();
		}

		return new ResponseEntity<ProgramCommentResponse>(result, HttpStatus.OK);
	}

	@GetMapping({ "/{programBoardNo}/comment/{programCommentNo}" })
	@ApiOperation(value = "특정 프로그래밍 게시판 특정 댓글 상세 조회")
	public ResponseEntity<ProgramCommentResponse> getBoardCommentOne(@PathVariable int programBoardNo,
			@PathVariable int programCommentNo) {
		System.out.println("게시판 : " + programBoardNo);
		System.out.println("댓글 : " + programCommentNo);

		final ProgramCommentResponse result = new ProgramCommentResponse();

		ProgramComment commentOne = null;
		try {
			commentOne = cService.getCommentOne(programBoardNo, programCommentNo);
			if (commentOne != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "프로그래밍 게시판의 "+commentOne.getProgramCommentNo()+"번 댓글 상세조회가 성공적으로 이루어졌습니다. ";
				result.comment = commentOne;
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = " 게시글 또는 댓글이 존재하지 않습니다. \n 확인 후 다시 이용해주세요.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "서버로 부터 댓글 목록을 가져 오던 중 오류가 발생했습니다. \n 잠시 후 다시 이용해주세요.";
			e.printStackTrace();
		}

		return new ResponseEntity<ProgramCommentResponse>(result, HttpStatus.OK);
	}

	@PostMapping("/comment")
	@ApiOperation(value = "특정 프로그래밍 게시판 게시글 댓글 등록")
	public ResponseEntity<ProgramCommentResponse> insertBoard(@RequestBody ProgramComment comment) {
		final ProgramCommentResponse result = new ProgramCommentResponse();

		try {
			boolean newComment = cService.insertComment(comment);

			if (newComment) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "새로운 댓글 등록이 성공적으로 이루어졌습니다. ";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "댓글 등록에 실패했습니다. \n 잠시후 다시 시도해주세요";
			}

		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "댓글을 등록 하던 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}

		return new ResponseEntity<ProgramCommentResponse>(result, HttpStatus.OK);
	}

	@PutMapping("/comment")
	@ApiOperation(value = "특정 프로그래밍 게시판 게시글 댓글 수정")
	public ResponseEntity<ProgramCommentResponse> updateBoard(@RequestBody ProgramComment comment) {
		final ProgramCommentResponse result = new ProgramCommentResponse();

		try {
			boolean updateComment = cService.updateComment(comment);

			if (updateComment) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "댓글 수정이 성공적으로 이루어졌습니다. ";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 게시글 또는 댓글 입니다. 확인하고 다시 시도해주세요.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "댓글을 수정 하던 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}

		return new ResponseEntity<ProgramCommentResponse>(result, HttpStatus.OK);
	}

	@DeleteMapping({ "/{programBoardNo}/comment/{programCommentNo}" })
	@ApiOperation(value = "특정 자유게시판 게시글 삭제")
	public ResponseEntity<ProgramCommentResponse> deleteBoard(@PathVariable int programBoardNo,
			@PathVariable int programCommentNo) {
		final ProgramCommentResponse result = new ProgramCommentResponse();

		try {
			boolean deleteComment = cService.deleteComment(programBoardNo, programCommentNo);
			if (deleteComment) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "댓글 삭제가 성공적으로 이루어졌습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 게시글 또는 댓글 입니다. 확인하고 다시 시도해주세요.";
			}

		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "댓글 삭제 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}

		return new ResponseEntity<ProgramCommentResponse>(result, HttpStatus.OK);
	}

}