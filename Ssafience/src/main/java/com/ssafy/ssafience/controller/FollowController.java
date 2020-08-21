package com.ssafy.ssafience.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.ListResponse;
import com.ssafy.ssafience.model.dto.FollowMember;
import com.ssafy.ssafience.model.follow.FollowWriteRequest;
import com.ssafy.ssafience.model.follow.UnFollowWriteRequest;
import com.ssafy.ssafience.service.follow.FollowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Follow : 팔로우")
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class FollowController {

	private static final Logger logger = LoggerFactory.getLogger(FollowController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	FollowService fService;
	
	@GetMapping("/follow/{memberid}/er")
	@ApiOperation(value = "memberId를 팔로워하는 사람들 목록(아이디, 이메일) 가져오기")
	public ResponseEntity<ListResponse<FollowMember>> getFollowerList(@PathVariable String memberid){
		logger.debug("getFollowerList 호출");
		final ListResponse<FollowMember> result = new ListResponse<>();
		try {
			List<FollowMember> followList = fService.getFollowerList(memberid);
			result.result = SUCCESS;
			result.message = "당신의 팔로워 목록 조회가 성공적으로 이뤄졌습니다.";
			result.status = HttpStatus.OK;
			result.setList(followList);
			
		} catch (Exception e) {
			result.result = FAIL;
			result.message = "팔로워 목록 조회 중 문제가 발생했습니다.";
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<ListResponse<FollowMember>>(result, HttpStatus.OK);
	}

	@GetMapping("/follow/{memberid}/ing")
	@ApiOperation(value = "memberId가 팔로잉하는 사람들 목록(아이디, 이메일) 가져오기")
	public ResponseEntity<ListResponse<FollowMember>> getFollowingList(@PathVariable String memberid){
		logger.debug("getFollowingList 호출");
		final ListResponse<FollowMember> result = new ListResponse<>();
		try {
			List<FollowMember> followList = fService.getFollowingList(memberid);
			result.result = SUCCESS;
			result.message = "당신의 팔로잉 목록 조회가 성공적으로 이뤄졌습니다.";
			result.status = HttpStatus.OK;
			result.setList(followList);
			
		} catch (Exception e) {
			result.result = FAIL;
			result.message = "팔로잉 목록 조회 중 문제가 발생했습니다.";
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		return new ResponseEntity<ListResponse<FollowMember>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "From이 To를 팔로우 한다.")
	@PostMapping("/follow")
	public ResponseEntity<BasicResponse> follow(@RequestBody FollowWriteRequest request){
		logger.debug("follow 호출");
		final BasicResponse result = new BasicResponse();
		try {
			int followCheck = fService.follow(request);
			if (followCheck == -1) {
				result.result = NOTAVAILABLE;
				result.message = "이미 팔로우 중입니다.";
				result.status = HttpStatus.UNPROCESSABLE_ENTITY;
			} else if (followCheck == 1) {
				result.result = SUCCESS;
				result.message = "팔로우가 성공적으로 이뤄졌습니다.";
				result.status = HttpStatus.OK;
			} else {
				result.result = FAIL;
				result.message = "팔로우에 실패했습니다.";
				result.status = HttpStatus.NO_CONTENT;
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.message = "팔로우 등록 중 문제가 발생했습니다.";
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "From이 To를 언팔로우 한다.")
	@PostMapping("/unfollow")
	public ResponseEntity<BasicResponse> unfollow(@RequestBody UnFollowWriteRequest request){
		logger.debug("unfollow 호출");
		final BasicResponse result = new BasicResponse();
		try {
			System.out.println("Controller :"+request);
			int followCheck = fService.unfollow(request);
			if (followCheck == 1) {
				result.result = SUCCESS;
				result.message = "언팔로우가 성공적으로 이뤄졌습니다.";
				result.status = HttpStatus.OK;
			} else {
				result.result = FAIL;
				result.message = "언팔로우에 실패했습니다. 팔로우 관계 인지 확인하세요.";
				result.status = HttpStatus.NO_CONTENT;
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.message = "언팔로우 중 문제가 발생했습니다.";
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
}
