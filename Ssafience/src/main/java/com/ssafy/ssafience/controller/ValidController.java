package com.ssafy.ssafience.controller;

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
import com.ssafy.ssafience.model.valid.EmailCheckRequest;
import com.ssafy.ssafience.service.valid.ValidService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Valid : 검증")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/valid")
public class ValidController {

	private static final Logger logger = LoggerFactory.getLogger(ValidController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	ValidService vService;
	

	@ApiOperation(value = "이메일 중복 확인")
	@PostMapping("email")
	public ResponseEntity<BasicResponse> checkEmail(@RequestBody EmailCheckRequest request){
		final BasicResponse result = new BasicResponse();
		
		try {
			boolean checkId = vService.emailDupCheck(request);
			if (checkId) {
				result.result = SUCCESS;
				result.status = HttpStatus.INTERNAL_SERVER_ERROR;
				result.message = "사용가능한 이메일 입니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "이미 사용 중인 이메일 입니다.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "이메일 중복 확인 중 문제가 발생했습니다.";
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	
}
