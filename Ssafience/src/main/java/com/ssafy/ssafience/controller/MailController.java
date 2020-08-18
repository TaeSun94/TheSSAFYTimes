package com.ssafy.ssafience.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.mail.AuthResult;
import com.ssafy.ssafience.model.mail.SendMailRequest;
import com.ssafy.ssafience.model.mail.SendMailResult;
import com.ssafy.ssafience.service.mail.MailService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Mail : 인증 메일 전송")
@CrossOrigin("*")
@Controller
@RequestMapping("/api/mail")
public class MailController {
	private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
	
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	MailService mService;
	
	@ApiOperation(value = "이메일 인증을 위한 메일 전송")
	@RequestMapping(value = "/validEmail", method = RequestMethod.POST)
	public ResponseEntity<BasicResponse> sendEmail(@RequestBody SendMailRequest request) {
		logger.debug("sendEmail 호출");
		final BasicResponse result = new BasicResponse();
		try {
			SendMailResult myMailResult = mService.sendValidKey(request);
			if (!myMailResult.isMember()) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않는 회원입니다. 회원가입을 진행하세요.";
			} else if (myMailResult.getSaveKey()==1) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message="인증 메일이 성공적으로 발송되었습니다. 등록한 이메일로 이메일 인증을 진행하세요.";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.INTERNAL_SERVER_ERROR;
				result.message="이메일 인증 도중 문제가 발생했습니다. (인증키 저장 안됨)";
			}
			
		} catch (Exception e) {
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.result = FAIL;
			result.message="이메일 인증을 위한 메일 전송 중 문제가 발생했습니다. \n 다시 시도해 주세요.";
			e.printStackTrace();
		}		
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
		
	}
	
	@ApiOperation(value = "이메일 인증 확인")
	@RequestMapping(value = "/emailKeyValid", method = RequestMethod.GET)
	public ModelAndView verifyEmailKey(HttpServletRequest request) {
		logger.debug("verifyEmailKey 호출");
		ModelAndView mv = new ModelAndView();
		String memberEmail = (String)request.getParameter("memberEmail");
		String memberAuth = (String)request.getParameter("memberAuth");
		
		String message = "";
		try {
			AuthResult myAuthResult = mService.checkMemberAuth(memberEmail, memberAuth);
			if (!myAuthResult.isMember()) {
				message = "존재하지 않는 회원입니다. 회원가입을 진행하세요.";
			} else if (!myAuthResult.isValid()) {
				message = "인증에 실패했습니다. 이메일 확인 후 다시 시도해주시기 바랍니다.";
			} else {
				message = "이메일 인증이 성공적으로 이루어졌습니다. ";
			}
		} catch (Exception e) {
			message = "이메일 인증 진행 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요.";
		}
		mv.addObject("message", message);
		mv.setViewName("mailResult");
		return mv;
	}

}
