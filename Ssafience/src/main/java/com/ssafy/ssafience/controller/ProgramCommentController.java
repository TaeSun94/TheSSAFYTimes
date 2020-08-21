package com.ssafy.ssafience.controller;

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
import com.ssafy.ssafience.model.comment.CommentModifyRequest;
import com.ssafy.ssafience.model.comment.CommentResult;
import com.ssafy.ssafience.model.comment.CommentWriteRequest;
import com.ssafy.ssafience.model.dto.ProgramComment;
import com.ssafy.ssafience.service.comment.ProgramCommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "ProgramComment : 프로그래밍 게시판 댓글")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/program")
public class ProgramCommentController {

	private static final Logger logger = LoggerFactory.getLogger(ProgramCommentController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	private ProgramCommentService cService;
	
	@ApiOperation(value = "특정 프로그래밍 게시판 댓글 목록 반환")
	@GetMapping("/{boardNo}/comment")
	public ResponseEntity<ListResponse<ProgramComment>> getBoardList(@PathVariable int boardNo){
		logger.debug("getBoardList 호출");
		final ListResponse<ProgramComment> result = new ListResponse<>();
		try {
			CommentResult<ProgramComment> myCommentResult = cService.selectBoardCommentList(boardNo);
			if (myCommentResult.isThere()) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.setList(myCommentResult.getCommentList());
				result.message="프로그래밍 게시판 댓글 목록 가져오는데 성공했습니다.";								
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 게시글입니다. 다시 확인해주세요.";				
			}			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="게시판 댓글 목록 가져오는 중 문제가 발생했습니다.";
			e.printStackTrace();
		}
		return new ResponseEntity<ListResponse<ProgramComment>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 프로그래밍 게시판 특정 댓글 상세 조회")
	@GetMapping({ "/{boardNo}/comment/{commentNo}" })
	public ResponseEntity<SingleResponse<ProgramComment>> getMemberBoardList(
			@PathVariable int boardNo, @PathVariable int commentNo){
		logger.debug("getMemberBoardList 호출");
		final SingleResponse<ProgramComment> result = new SingleResponse<>();
		try {
			CommentResult<ProgramComment> myCommentResult = cService.selectCommentOne(boardNo, commentNo);
			if (myCommentResult.isThere()) {
				ProgramComment comment = myCommentResult.getData();
				if (comment != null) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message="댓글 상세 정보 가져오는데 성공했습니다.";													
					result.setData(myCommentResult.getData());
				} else {
					result.result = NOTAVAILABLE;
					result.status = HttpStatus.NO_CONTENT;
					result.message="존재하지 않는 댓글입니다. 다시 확인해주세요.";				
				}
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 게시글입니다. 다시 확인해주세요.";				
			}			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="댓글 상세 정보 가져오는 중 문제가 발생했습니다.";
			e.printStackTrace();
		}
		return new ResponseEntity<SingleResponse<ProgramComment>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 프로그래밍 게시판 댓글 등록")
	@PostMapping("/comment")
	public ResponseEntity<BasicResponse> insertBoard(@RequestBody CommentWriteRequest request){
		logger.debug("insertBoard 호출");
		final BasicResponse result = new BasicResponse();
		
		try {
			int insertComment = cService.insertComment(request);
			if (insertComment == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "일치하는 회원 또는 게시글이 없습니다. 확인하고 다시 시도해주세요.";
			} else if (insertComment == 1) {
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
	
	@ApiOperation(value = "특정 프로그래밍 게시판 게시글 댓글 수정")
	@PutMapping("/comment")
	public ResponseEntity<BasicResponse> updateBoard(@RequestBody CommentModifyRequest request){
		logger.debug("updateBoard 호출");
		final BasicResponse result = new BasicResponse();
		
		try {
			int updateComment = cService.updateComment(request);
			if (updateComment == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "해당 댓글이 없습니다. 확인하고 다시 시도해주세요.";
			} else if (updateComment == 1) {
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
	@DeleteMapping({ "/comment/{commentNo}" })
	public ResponseEntity<BasicResponse> deleteBoard(@PathVariable int commentNo){
		logger.debug("deleteBoard 호출");
		final BasicResponse result = new BasicResponse();
		
		try {
			int deleteComment = cService.deleteComment(commentNo);
			if (deleteComment == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "해당 댓글이 없습니다. 확인하고 다시 시도해주세요.";
			} else if (deleteComment == 1) {
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