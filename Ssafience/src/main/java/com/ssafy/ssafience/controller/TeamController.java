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
import com.ssafy.ssafience.model.board.FreeModifyRequest;
import com.ssafy.ssafience.model.board.FreeWriteRequest;
import com.ssafy.ssafience.model.dto.FreeBoard;
import com.ssafy.ssafience.model.dto.TeamBoard;
import com.ssafy.ssafience.model.team.TeamModifyRequest;
import com.ssafy.ssafience.model.team.TeamWriteRequest;
import com.ssafy.ssafience.service.board.TeamService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Team : 팀 매칭")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/team/board")
public class TeamController {

	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	TeamService tService;
	
	@ApiOperation(value = "모든 팀 매칭 게시판 목록 반환")
	@GetMapping
	public ResponseEntity<ListResponse<TeamBoard>> getBoardList(){
		final ListResponse<TeamBoard> result = new ListResponse<>();
		try {
			List<TeamBoard> list = tService.selectBoardList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.setList(list);
			result.message="팀 매칭 게시판 목록 가져오는데 성공했습니다.";				
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="모든 팀 매칭 게시판 목록 가져오는 중 문제가 발생했습니다.";
			e.printStackTrace();
		}
		return new ResponseEntity<ListResponse<TeamBoard>>(result, HttpStatus.OK);
	}

	
	@ApiOperation(value = "특정 팀 매칭 게시판 상세 조회")
	@GetMapping("/{boardno}")
	public ResponseEntity<SingleResponse<TeamBoard>> getBoardOne(@PathVariable int boardno){
		final SingleResponse<TeamBoard> result = new SingleResponse<>();
		
		try {
			TeamBoard board = tService.selectBoardOne(boardno);
			if (board != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message=" 팀 매칭 게시판 상세조회에 성공했습니다.";	
				result.setData(board);
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 게시글 입니다. 확인 후 다시 시도해주세요.";				
			}			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message=" 팀 매칭 게시판 목록 가져오는 중 문제가 발생했습니다.";
			e.printStackTrace();
		}
		
		return new ResponseEntity<SingleResponse<TeamBoard>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 팀 매칭 게시판 게시글 등록")
	@PostMapping
	public ResponseEntity<BasicResponse> insertBoard(@RequestBody TeamWriteRequest request){
		final BasicResponse result = new BasicResponse();
		
		try {
			int insertBoard = tService.insert(request);
			if (insertBoard == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않는 회원입니다. 회원가입을 진행해주세요.";
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
	
	@ApiOperation(value = "팀 매칭 게시판 게시글 수정")
	@PutMapping
	public ResponseEntity<BasicResponse> updateBoard(@RequestBody TeamModifyRequest request){
		final BasicResponse result = new BasicResponse();
		
		try {
			int updateBoard = tService.update(request);
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
	
	@ApiOperation(value = "팀 매칭 게시판 게시글 삭제")
	@DeleteMapping("/{boardno}")
	public ResponseEntity<BasicResponse> deleteBoard(@PathVariable int boardno){
		final BasicResponse result = new BasicResponse();
		
		try {
			int deleteBoard = tService.delete(boardno);
			if (deleteBoard == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "해당 게시글의 작성자가 맞는지 확인하고 다시 시도해주세요.";
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
