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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.ListResponse;
import com.ssafy.ssafience.model.category.CategoryResult;
import com.ssafy.ssafience.service.category.CategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Category : 카테고리")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/category")
public class CategoryController {

	private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	CategoryService cService;
	
	@ApiOperation(value = "지역 카테고리 목록 조회")
	@GetMapping("/region")
	public ResponseEntity<ListResponse<CategoryResult>> getRegionList(){
		final ListResponse<CategoryResult> result = new ListResponse<>();
		
		try {
			List<CategoryResult> categoryList = cService.getRegionList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "지역 카테고리 조회가 성공적으로 이루어졌습니다. ";
			result.setList(categoryList);
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "지역 카테고리 목록을 가져오던 중 문제가 발생했습니다. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<CategoryResult>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "지역별 기수 카테고리 목록 조회")
	@GetMapping("/{categoryno}/unit")
	public ResponseEntity<ListResponse<CategoryResult>> getUnitList(@PathVariable int categoryno){
		final ListResponse<CategoryResult> result = new ListResponse<>();
		
		try {
			List<CategoryResult> categoryList = cService.getUnitList(categoryno);
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "지역별 기수 카테고리 조회가 성공적으로 이루어졌습니다. ";
			result.setList(categoryList);
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "지역별 기수 카테고리 목록을 가져오던 중 문제가 발생했습니다. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<CategoryResult>>(result, HttpStatus.OK);
	}
	

	@ApiOperation(value = "지역별 기수별 트랙 카테고리 목록 조회")
	@GetMapping("/{categoryno}/track")
	public ResponseEntity<ListResponse<CategoryResult>> getTrackList(@PathVariable int categoryno){
		final ListResponse<CategoryResult> result = new ListResponse<>();
		
		try {
			List<CategoryResult> categoryList = cService.getTrackList(categoryno);
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "지역별 기수별 트랙 카테고리 조회가 성공적으로 이루어졌습니다. ";
			result.setList(categoryList);
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "지역별 기수별 트랙 카테고리 목록을 가져오던 중 문제가 발생했습니다. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<CategoryResult>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로그래밍 게시판 트랙 카테고리 목록 조회")
	@GetMapping("/program-track")
	public ResponseEntity<ListResponse<CategoryResult>> getBoardTrackList(){
		final ListResponse<CategoryResult> result = new ListResponse<>();
		
		try {
			List<CategoryResult> categoryList = cService.getBoardTrackList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "프로그래밍 게시판 트랙 카테고리 목록 조회가 성공적으로 이루어졌습니다. ";
			result.setList(categoryList);
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "프로그래밍 게시판 트랙 카테고리 목록을 가져오던 중 문제가 발생했습니다. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<CategoryResult>>(result, HttpStatus.OK);
	}
	

	@ApiOperation(value = "관심 기술 및 언어, 사용 기술 및 언어 카테고리 목록 조회")
	@GetMapping("/skill-language")
	public ResponseEntity<ListResponse<CategoryResult>> getSkillAndLanguageList(){
		final ListResponse<CategoryResult> result = new ListResponse<>();
		
		try {
			List<CategoryResult> categoryList = cService.getSkillAndLanguageList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "관심 기술 및 언어, 사용 기술 및 언어 카테고리  조회가 성공적으로 이루어졌습니다. ";
			result.setList(categoryList);
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "관심 기술 및 언어, 사용 기술 및 언어 카테고리 목록을 가져오던 중 문제가 발생했습니다. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<CategoryResult>>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "프로젝트 종류 카테고리 목록 조회")
	@GetMapping("/project")
	public ResponseEntity<ListResponse<CategoryResult>> getProjectList(){
		final ListResponse<CategoryResult> result = new ListResponse<>();
		
		try {
			List<CategoryResult> categoryList = cService.getProjectList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "프로젝트 종류 카테고리  조회가 성공적으로 이루어졌습니다. ";
			result.setList(categoryList);
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "프로젝트 종류 카테고리 목록을 가져오던 중 문제가 발생했습니다. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<CategoryResult>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "기사 분류 카테고리 목록 조회")
	@GetMapping("/article")
	public ResponseEntity<ListResponse<CategoryResult>> getArticleList(){
		final ListResponse<CategoryResult> result = new ListResponse<>();
		
		try {
			List<CategoryResult> categoryList = cService.getArticleList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message = "프로젝트 종류 카테고리  조회가 성공적으로 이루어졌습니다. ";
			result.setList(categoryList);
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "기사 분류 카테고리 목록을 가져오던 중 문제가 발생했습니다. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<ListResponse<CategoryResult>>(result, HttpStatus.OK);
	}
	
}
