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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.follow.Follow;
import com.ssafy.ssafience.model.follow.FollowMember;
import com.ssafy.ssafience.model.follow.FollowResponse;
import com.ssafy.ssafience.service.FollowService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin("*")
@RestController
@RequestMapping("/api/follow")
public class FollowController {
	
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	@Qualifier("FollowServiceImpl")
	FollowService fService;
	
	@GetMapping("/testeset/stes")
	@ApiOperation(value = "Entity Manage TEST")
	public List<FollowMember> test(){
		return fService.test();
	}
	
	@GetMapping("/{memberId}/er")
	@ApiOperation(value = "팔로워 목록 가져오기")
	public ResponseEntity<FollowResponse> getFollowerList(@PathVariable String memberId){
		final FollowResponse result = new FollowResponse();
		
		return new ResponseEntity<FollowResponse>(result, HttpStatus.OK);
	}

	@GetMapping("/{memberId}/ing")
	@ApiOperation(value = "팔로잉 목록 가져오기")
	public ResponseEntity<FollowResponse> getFollowingList(@PathVariable String memberId){
		final FollowResponse result = new FollowResponse();
		
		return new ResponseEntity<FollowResponse>(result, HttpStatus.OK);
	}

	// 자신제외, 자신이 등록하지 않은 사람들 목록 API
	

	@PostMapping
	@ApiOperation(value = "From이 To를 팔로우")
	public ResponseEntity<BasicResponse> follow(@RequestBody Follow f){
		final BasicResponse result = new BasicResponse();
		if (f.getMemberIdFrom().trim().equals(f.getMemberIdTo().trim())) {
			result.result = NOTAVAILABLE;
			result.status = HttpStatus.UNPROCESSABLE_ENTITY;
			result.message="자기 자신을 팔로우할 수 없습니다. ";			
		} else {			
			try {
				boolean insertFollow = fService.insertFollow(f);
				if (insertFollow) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message="팔로잉이 성공적으로 이루어졌습니다.";		
				} else {	
					result.result = NOTAVAILABLE;
					result.status = HttpStatus.UNPROCESSABLE_ENTITY;
					result.message="이미 등록됐습니다. ";			
				}
			} catch (Exception e) {
				result.result = FAIL;
				result.status = HttpStatus.INTERNAL_SERVER_ERROR;
				result.message="팔로잉 등록 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
				e.printStackTrace();
			}			
		}
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);		
	}
	
	@DeleteMapping
	@ApiOperation(value = "From이 To를 언팔로우")
	public ResponseEntity<BasicResponse> unfollow(@RequestBody Follow f){
		final BasicResponse result = new BasicResponse();
		try {
			boolean insertFollow = fService.deleteFollow(f.getFollowNo());
			if (insertFollow) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message="팔로잉 해제가 성공적으로 이루어졌습니다.";		
			} else {	
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.UNPROCESSABLE_ENTITY;
				result.message="이미 해제됐습니다. ";			
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="팔로잉 해제 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);		
	}
	
}