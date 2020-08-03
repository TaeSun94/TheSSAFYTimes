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
import com.ssafy.ssafience.model.SingleResponse;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.member.MemberDetailResult;
import com.ssafy.ssafience.model.member.ModifyRequest;
import com.ssafy.ssafience.model.member.SignUpRequest;
import com.ssafy.ssafience.service.member.MemberService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Member : 회원")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/member")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";

	@Autowired
	private MemberService mService;

	@ApiOperation(value = "특정 회원 목록 반환")
	@GetMapping("/{memberid}")
	public ResponseEntity<SingleResponse<MemberDetailResult>> getMemberList(@PathVariable String memberid) {
		final SingleResponse<MemberDetailResult> result = new SingleResponse<>();

		try {
			MemberDetailResult member = mService.selectMemberOneDetail(memberid);
			if (member != null) {
				System.out.println(member);
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.setData(member);
				result.message = "회원 목록 가져오는데 성공했습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않는 아이디 입니다. 확인하고 다시 시도해주세요.";
			}

		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "모든 회원 목록 가져오는 중 문제가 발생했습니다.";
		}

		return new ResponseEntity<SingleResponse<MemberDetailResult>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 회원 등록")
	@PostMapping
	public ResponseEntity<SingleResponse<Member>> signup(@RequestBody SignUpRequest request) {
		final SingleResponse<Member> result = new SingleResponse<>();

		try {
			int insertMember = mService.insert(request);
			if (insertMember == 1) {
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

		return new ResponseEntity<SingleResponse<Member>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "회원 정보 수정")
	@PutMapping
	public ResponseEntity<BasicResponse> updateMember(@RequestBody ModifyRequest request) {
		final BasicResponse result = new BasicResponse();
		
		System.out.println(request);

		try {
			int updateMember = mService.update(request);
			if (updateMember == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 회원";
			} else if (updateMember == 1) {
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

	@ApiOperation(value = "회원 삭제")
	@DeleteMapping("/{memberId}")
	public ResponseEntity<BasicResponse> deleteMember(@PathVariable String memberId) {
		final BasicResponse result = new BasicResponse();

		try {
			int deleteMember = mService.delete(memberId);
			if (deleteMember == -1) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 회원";
			} else if (deleteMember == 1) {
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
