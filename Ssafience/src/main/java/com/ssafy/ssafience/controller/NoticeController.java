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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.notice.Notice;
import com.ssafy.ssafience.model.notice.NoticeResponse;
import com.ssafy.ssafience.service.NoticeService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
//@CrossOrigin(origins = { "http://localhost:8888" })
@CrossOrigin("*")
@RestController
@RequestMapping("/api/notice")
public class NoticeController {
	
	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";
	
	@Autowired
	@Qualifier("NoticeServiceImpl")
	NoticeService nService;
	
	
	@GetMapping
	@ApiOperation(value = "공지사항 목록 조회")
	public ResponseEntity<NoticeResponse> getNoticeList(){

		final NoticeResponse result = new NoticeResponse();
		try {
			List<Notice> noticeList = nService.getNoticeList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.message="총 "+noticeList.size()+"건의 공지사항이 있습니다.";
			result.noticeList = noticeList;				
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="공지사항 목록조회 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		return new ResponseEntity<NoticeResponse>(result, HttpStatus.OK);
	}

	@GetMapping("/{noticeNo}")
	@ApiOperation(value = "특정 공지사항 상세조회")
	public ResponseEntity<NoticeResponse> getNoticeList(@PathVariable int noticeNo){

		final NoticeResponse result = new NoticeResponse();

		try {
			Notice noticeOne = nService.getNoticeOne(noticeNo);
			System.out.println(noticeOne);
			if (noticeOne != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message=noticeNo +"번 공지사항의 상세정보 입니다.";		
				result.notice = noticeOne;
			} else {	
				System.out.println("없음");
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 공지사항 입니다. ";			
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="공지사항 상세조회 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<NoticeResponse>(result, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(value = "새로운 공지사항 등록")
	public ResponseEntity<BasicResponse> insertNotice(@RequestBody Notice n){
		final BasicResponse result = new BasicResponse();

		try {
			Notice newNotice = nService.insertNotice(n);
			if (newNotice != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message="새로운 공지사항 등록이 성공적으로 이루어졌습니다. ";
			} else {
				result.result = FAIL;
				result.status = HttpStatus.NO_CONTENT;
				result.message="새로운 공지사항 등록에 실패했습니다. \n 확인 후 다시 시도해주세요.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="공지사항 등록 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
					
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

	@PutMapping
	@ApiOperation(value = "공지사항 수정")
	public ResponseEntity<BasicResponse> updateNotice(@RequestBody Notice n){
		final BasicResponse result = new BasicResponse();

		try {
			boolean updateNotice = nService.updateNotice(n);
			if (updateNotice) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message="공지사항 수정이 성공적으로 이루어졌습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 공지사항 입니다. \n 확인 후 다시 시도해주세요.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="공지사항 수정 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
			
		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{noticeNo}")
	@ApiOperation(value = "공지사항 삭제")
	public ResponseEntity<BasicResponse> deleteNotice(@PathVariable int noticeNo){
		final BasicResponse result = new BasicResponse();

		try {
			boolean deleteNotice = nService.deleteNotice(noticeNo);
			if (deleteNotice) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.message="공지사항 삭제가 성공적으로 이루어졌습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message="존재하지 않는 공지사항 입니다. \n 확인 후 다시 시도해주세요.";
			}
		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message="공지사항 삭제 중 문제가 발생했습니다. \n 잠시후 다시 시도해주세요. ";
			e.printStackTrace();
		}
		
		return new ResponseEntity<BasicResponse>(result,  HttpStatus.OK);		
	}

}