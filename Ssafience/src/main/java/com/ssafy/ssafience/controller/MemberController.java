package com.ssafy.ssafience.controller;

import java.util.Optional;

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
import com.ssafy.ssafience.model.member.LoginResponse;
import com.ssafy.ssafience.model.member.Member;
import com.ssafy.ssafience.model.member.MemberResponse;
import com.ssafy.ssafience.model.member.PasswordRequest;
import com.ssafy.ssafience.model.member.SignupResponse;
import com.ssafy.ssafience.service.MemberService;
import com.ssafy.ssafience.service.ValidService;

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
@RequestMapping("/api/member")
public class MemberController {
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	@Qualifier("MemberServiceImpl")
	MemberService mService;

	@Autowired
	@Qualifier("ValidServiceImpl")
	ValidService vService;

    @PostMapping("/signup")
    @ApiOperation(value = "가입하기")
    public ResponseEntity<BasicResponse> signup(@RequestBody Member m) {
    	
    	final SignupResponse result = new SignupResponse();
    	
		try {
			boolean avaliableEmailReg = vService.checkEmailRegex(m.getMemberEmail());
			if (!avaliableEmailReg) {	// 이메일 형식 아님	
				result.status=HttpStatus.OK;
				result.result=NOTAVAILABLE;
				result.message="이메일 형식을 확인하세요.";
			} else {
				try {
					Member newMember = mService.insertMember(m);
					if (newMember != null) {
						result.result=SUCCESS;
						result.status=HttpStatus.OK;				
						result.message="회원가입이 성공적으로 이루어졌습니다.";
					} else {
						result.result=FAIL;
						result.status=HttpStatus.OK;				
						result.message="회원가입에 실패했습니다. 다시 시도해주시기 바랍니다.";
					}		
				} catch (Exception e) {
					result.result=FAIL;
					result.status=HttpStatus.INTERNAL_SERVER_ERROR;				
					result.message="회원가입 진행 중 문제가 발생하여 회원가입에 실패했습니다. 다시 시도해주시기 바랍니다.";
					e.printStackTrace();
				}
			}
		} catch (Exception e1) {
			result.result=FAIL;
			result.status=HttpStatus.INTERNAL_SERVER_ERROR;				
			result.message="이메일 형식을 체크하던 중 문제가 발생했습니다. 다시 시도해주시기 바랍니다.";
			e1.printStackTrace();
		}
    	
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<LoginResponse> login(@RequestBody Member m){

    	final LoginResponse result = new LoginResponse();

		try {
			Optional<Member> memberOpt = mService.login(m.getMemberId(), m.getMemberPw());
			if (memberOpt != null) {	// 로그인 성공
				if (memberOpt.get().isMemberAuthStatus()) {		// 이메일 인증 완료
					result.result=SUCCESS;
					result.member=memberOpt.get();
					result.status=HttpStatus.OK;
					result.member_auth_status=true;
					result.message="성공적으로 로그인되었습니다. ";				
				} else {
					result.result=NOTAVAILABLE;
					result.member=memberOpt.get();
					result.status=HttpStatus.OK;
					result.member_auth_status=false;
					result.message="이메일 인증이 필요합니다. \n 이메일 인증을 진행하세요.";				
				}
			} else {	// 로그인 실패
				result.result=FAIL;
				result.status=HttpStatus.NO_CONTENT;
				result.message="가입하지 않은 아이디이거나, 잘못된 비밀번호입니다.";
			}
		} catch (Exception e) {
			result.result=FAIL;
			result.status=HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="로그인 중 문제가 발생하여 회원가입에 실패했습니다. 다시 시도해주시기 바랍니다.";
			e.printStackTrace();
		}
    	


    	return new ResponseEntity<LoginResponse>(result, HttpStatus.OK);
    	
    }
    
    @GetMapping("/{memberId}")
    @ApiOperation(value = "회원 상세 정보 조회")
    public ResponseEntity<MemberResponse> getMemberProfile(@PathVariable String memberId){
    	final MemberResponse result = new MemberResponse();
    	
    	try {
			Member memberOne = mService.getMemberOne(memberId);
			if (memberOne != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "회원 조회가 성공적으로 이루어졌습니다. ";
				result.member = memberOne;
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 회원 입니다. 확인하고 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "회원 조회 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<MemberResponse>(result, HttpStatus.OK);
    }
    
    @PutMapping
    @ApiOperation(value = "회원 정보 수정")
    public ResponseEntity<BasicResponse> updateMember(@RequestBody Member m){

    	final BasicResponse result = new BasicResponse();
    	try {
			boolean updateMember = mService.updateMember(m);
			if (updateMember) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "회원 수정이 성공적으로 이루어졌습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 회원 입니다. 확인하고 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "회원 수정 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);

    }
    
    @DeleteMapping("/{memberId}")
    @ApiOperation(value = "회원삭제")
    public ResponseEntity<BasicResponse> deleteMember(@PathVariable String memberId){
    	final BasicResponse result = new BasicResponse();
    	try {
			boolean deleteMember = mService.deleteMember(memberId);
			if (deleteMember) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "회원 삭제가 성공적으로 이루어졌습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "없는 회원 입니다. 확인하고 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "회원 삭제 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
    }

    @PutMapping("/updatePw")
    @ApiOperation(value = "비밀번호 변경")
    public ResponseEntity<BasicResponse> updateMemberPw(@RequestBody PasswordRequest request){

    	final BasicResponse result = new BasicResponse();
    	try {
			boolean updateMemberPw = mService.updateMemberPw(request);
			if (updateMemberPw) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "비밀번호 변경이 성공적으로 이루어졌습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "일치하는 회원이 없거나 비밀번호가 잘못되었습니다. 확인하고 다시 시도해주세요.";
			}
			
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "비밀번호 변경 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
			e.printStackTrace();
		}
    	
    	return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);


    	
    }
    
}