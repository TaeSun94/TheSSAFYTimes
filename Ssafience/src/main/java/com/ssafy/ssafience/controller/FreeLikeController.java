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
import com.ssafy.ssafience.model.like.LikeRequest;
import com.ssafy.ssafience.model.like.LikeResult;
import com.ssafy.ssafience.service.like.FreeLikeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "FreeLike : 자유게시판 좋아요")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/free")
public class FreeLikeController {

	private static final Logger logger = LoggerFactory.getLogger(FreeLikeController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	FreeLikeService fService;
	
	@ApiOperation(value = "좋아요(1) / 싫어요(0)")
	@PostMapping("/like")
	public ResponseEntity<BasicResponse> likeFreeBoard(@RequestBody LikeRequest request){
		final BasicResponse result = new BasicResponse();
		
		try {
			LikeResult insertLike = fService.insertLike(request);
			if (!insertLike.isThere()) {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "일치하는 게시글이 없습니다. 확인하고 다시 시도해주세요.";
			} else if (!insertLike.isIn()) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message = "성공적으로 등록됨!";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "이미 등록됨!";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "등록 중 문제발생";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

}
