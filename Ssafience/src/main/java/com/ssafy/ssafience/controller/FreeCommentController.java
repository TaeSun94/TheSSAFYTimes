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
import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.comment.CommentModifyRequest;
import com.ssafy.ssafience.model.comment.CommentResult;
import com.ssafy.ssafience.model.comment.CommentWriteRequest;
import com.ssafy.ssafience.model.dto.FreeComment;
import com.ssafy.ssafience.model.dto.ProgramComment;
import com.ssafy.ssafience.service.comment.FreeCommentService;
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
@Api(tags = "FreeComment : 자유게시판 댓글")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/free")
public class FreeCommentController {

	private static final Logger logger = LoggerFactory.getLogger(FreeCommentController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	private FreeCommentService cService;
	
	@ApiOperation(value = "특정 자유 게시판 댓글 목록 반환")
	@GetMapping("/{boardno}/comment")
	public ResponseEntity<ListResponse<FreeComment>> getBoardList(@PathVariable int boardno){
		final ListResponse<FreeComment> result = new ListResponse<>();
		try {
			CommentResult<FreeComment> myCommentResult = cService.selectBoardCommentList(boardno);
			if (myCommentResult.isThere()) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.setList(myCommentResult.getCommentList());
				result.message="자유 게시판 댓글 목록 가져오는데 성공했습니다.";								
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
		return new ResponseEntity<ListResponse<FreeComment>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 자유 게시판 특정 댓글 상세 조회")
	@GetMapping({ "/{boardno}/comment/{commentno}" })
	public ResponseEntity<SingleResponse<FreeComment>> getMemberBoardList(
			@PathVariable int boardno, @PathVariable int commentno){
		final SingleResponse<FreeComment> result = new SingleResponse<>();
		try {
			CommentResult<FreeComment> myCommentResult = cService.selectCommentOne(boardno, commentno);
			if (myCommentResult.isThere()) {
				FreeComment comment = myCommentResult.getData();
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
		return new ResponseEntity<SingleResponse<FreeComment>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 자유 게시판 댓글 등록")
	@PostMapping("/comment")
	public ResponseEntity<BasicResponse> insertBoard(@RequestBody CommentWriteRequest request){
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
	
	@ApiOperation(value = "특정 자유 게시판 게시글 댓글 수정")
	@PutMapping("/comment")
	public ResponseEntity<BasicResponse> updateBoard(@RequestBody CommentModifyRequest request){
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
	
	@ApiOperation(value = "자유 게시판 게시글 삭제")
	@DeleteMapping({ "/comment/{commentno}" })
	public ResponseEntity<BasicResponse> deleteBoard(@PathVariable int commentno){
		final BasicResponse result = new BasicResponse();
		
		try {
			int deleteComment = cService.deleteComment(commentno);
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

