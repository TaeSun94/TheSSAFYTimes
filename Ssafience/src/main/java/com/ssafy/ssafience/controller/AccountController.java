package com.ssafy.ssafience.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.SingleResponse;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.member.LoginResult;
import com.ssafy.ssafience.model.member.SignInRequest;
import com.ssafy.ssafience.model.member.SignUpRequest;
import com.ssafy.ssafience.service.member.MemberService;
import com.ssafy.ssafience.service.valid.ValidService;
import com.ssafy.ssafience.util.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Account : 계정")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/account")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	static final String NOTVALID = "notvalid";
	
	@Autowired
	MemberService mService;
	
	@Autowired
	ValidService vService;
	
	@Autowired
	JwtUtil jwtUtil;

	@ApiOperation(value = "회원가입")
	@PostMapping("/signup")
	public ResponseEntity<BasicResponse> signUp(@RequestBody SignUpRequest request){
		logger.debug("signUp 호출");
		final BasicResponse result = new BasicResponse();
		
		try {
			boolean availableEmailReg = vService.checkEmailRegex(request.getMemberEmail());
			if (availableEmailReg) {
				int insertMember = mService.insert(request);
				if (insertMember == 1) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message = "회원가입이 성공적으로 이루어졌습니다.";
				} else {
					result.result = FAIL;
					result.status = HttpStatus.NO_CONTENT;
					result.message = "회원가입에 실패했습니다. 다시 시도해주시기 바랍니다.";
				}
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.BAD_REQUEST;
				result.message="이메일 형식을 확인하세요.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "회원가입 진행 중 문제가 발생하여 회원가입에 실패했습니다. 다시 시도해주시기 바랍니다.";
			e.printStackTrace();
		}

		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "로그인")
	@PostMapping("/signin")
	public ResponseEntity<SingleResponse<Member>> signin(@RequestBody SignInRequest request, HttpServletResponse response){
		logger.debug("signin 호출");
		final SingleResponse<Member> result = new SingleResponse<>();
		
		try {
			LoginResult<Member> validMember = mService.singIn(request);
			if (!validMember.isMember()) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
			} else if (!validMember.isValid()) {				
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "입하지 않은 아이디이거나, 잘못된 비밀번호입니다. (비밀번호 틀림)";
			} else {
				Member member = validMember.getData();
				////////////////////////////////////////////////////////
				String accessToken = jwtUtil.createToken(member.getMemberId());
				System.out.println(accessToken);
				response.addHeader("Authorization", "Bearer "+accessToken);
				System.out.println(accessToken);
				jwtUtil.getTokenFromJwtString(accessToken);
				////////////////////////////////////////////////////////
				
				if (member.isMemberAuthStatus()) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message="성공적으로 로그인되었습니다. ";					
					result.setData(member);
				} else {
					result.result = NOTVALID;
					result.status = HttpStatus.OK;
					result.message="이메일 인증이 필요합니다. \n 이메일 인증을 진행하세요.";					
				}
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "로그인 중 문제가 발생하여 회원가입에 실패했습니다. 다시 시도해주시기 바랍니다.";
			e.printStackTrace();
		}
		
		return new ResponseEntity<SingleResponse<Member>>(result, HttpStatus.OK);
	}
	
}




