package com.ssafy.ssafience.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.member.Member;
import com.ssafy.ssafience.service.ValidService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://192.168.25.3:8888" })
@CrossOrigin("*")
@RestController
@RequestMapping("/api/valid")
public class ValidController {

	static final String SUCCESS = "success";
	static final String FAIL = "fail";

	@Autowired
	@Qualifier("ValidServiceImpl")
	ValidService vService;

	// 회원가입 직후를 제외한 시점에 이 함수를 호출하여 이메일 인증 진행
	@PostMapping("/sendEmail")
	@ApiOperation(value = "회원 인증 이메일 보내기")
	public ResponseEntity<BasicResponse> sendEmail(@RequestBody Member m) {
		
		BasicResponse result = new BasicResponse();
		// 인증 메일 보내고, 인증키 저장
		boolean sendEamil = false;
		System.out.println("인증 메일 보내기");
		try {
			boolean isMember = vService.isMember(m.getMemberEmail());
			if (isMember) {
				try {
					vService.sendValidKey(m.getMemberEmail());
					sendEamil = true;
					if (sendEamil) {
						result.result = SUCCESS;
						result.status = HttpStatus.OK;
						result.message="등록한 이메일로 이메일 인증을 진행하세요.";
					} else {
						result.result = FAIL;
						result.status = HttpStatus.OK;					
						result.message="등록된 이메일로 인증 메일 보내기에 실패 했습니다. ";
					}
				} catch (Exception e) {
					result.status = HttpStatus.INTERNAL_SERVER_ERROR;
					result.result = FAIL;
					result.message="인증 메일 보내는 중 문제가 발생했습니다. \n 다시 시도해 주세요.";
					e.printStackTrace();
				}
			} else {
				result.status = HttpStatus.OK;
				result.result = FAIL;
				result.message="존재하지 않는 회원입니다. 회원가입을 진행하세요.";
			}
		} catch (Exception e1) {
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.result = FAIL;
			result.message="회원조회 중 문제가 발생했습니다. \n 다시 시도해 주세요.";
			e1.printStackTrace();
		}
			
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);

	}

	@GetMapping("/emailKeyValid")
	@ApiOperation(value = "이메일 인증 확인 ")
	public ResponseEntity<BasicResponse> checkValidKey(String memberEmail, String memberAuth) {
		
		System.out.println("이메일  :" + memberEmail +" , 인증키 : " + memberAuth);

		BasicResponse result = new BasicResponse();

		try {
			boolean checkValidKey = vService.checkValidKey(memberEmail, memberAuth);
			if (checkValidKey) { // 일치
				result.result = SUCCESS;
				boolean confirmAuth = vService.confirmMemberAuth(memberEmail);
				if (confirmAuth) {
					result.result= SUCCESS;
					result.status=HttpStatus.OK;
					result.message="이메일 인증이 성공적으로 이루어졌습니다. ";
				} else {
					result.result= FAIL;
					result.status=HttpStatus.OK;
					result.message="이메일 인증에 실패했습니다.";
				}
				
			} else { // 불일치
				result.result = FAIL;
				result.status=HttpStatus.OK;
				result.message="인증키가 일치하지 않습니다. 다시 시도해주시기 바랍니다.";
				System.out.println("Key 불일치 ");
			}
		} catch (Exception e) {
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.result = FAIL;
			result.message="이메일 인증 진행 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}

		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);

	}

	@PostMapping("/idValid")
	@ApiOperation(value = "회원 아이디 증복 체크")
	public ResponseEntity<BasicResponse> checkIdDup(@RequestBody Member m) {

		final BasicResponse result = new BasicResponse();

		try {
			boolean avaliableId = vService.checkIdDup(m.getMemberId());
			if (!avaliableId) { // 중복
				result.result = FAIL;
				result.status = HttpStatus.OK;
				result.message="이미 존재하는 아이디 입니다.";
			} else { // 사용 가능 ID
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message="사용 가능한 아이디 입니다.";
			}
		} catch (Exception e) {
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.result = FAIL;
			result.message="회원 아이디 중복 체크 진행 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@PostMapping("/emailValid")
	@ApiOperation(value = "회원 이메일 증복 체크")
	public ResponseEntity<BasicResponse> checkEmailDup(@RequestBody Member m) {

		final BasicResponse result = new BasicResponse();

		try {
			boolean avaliableEmailReg = vService.checkEmailRegex(m.getMemberEmail());
			boolean avaliableEmail = vService.checkEmailDup(m.getMemberEmail());
			if (!avaliableEmailReg) {	// 이메일 형식 아님	
				result.status=HttpStatus.OK;
				result.result=FAIL;
				result.message="이메일 형식을 확인하세요.";
			} else if(!avaliableEmail) {
				result.status=HttpStatus.OK;
				result.result=FAIL;
				result.message="이미 존재하는 이메일 입니다.";
			} else {
				result.status=HttpStatus.OK;
				result.result=SUCCESS;
				result.message="사용 가능한 이메일 입니다.";
			}
			
		} catch (Exception e) {
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.result = FAIL;
			result.message="회원 이메일 중복 체크 진행 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
    	
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
