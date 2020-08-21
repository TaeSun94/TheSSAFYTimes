package com.ssafy.ssafience.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;

@ControllerAdvice
@RestController
public class GlobalException {
	
	static final String FAIL = "fail";
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(value = AccessDeniedException.class)
	public ResponseEntity<BasicResponse> handleException(AccessDeniedException e){
		final BasicResponse result = new BasicResponse();
		result.message = e.getMessage();
		result.result = FAIL;
		result.status = HttpStatus.UNAUTHORIZED;
		e.printStackTrace(); 
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
}
