package com.ssafy.ssafience.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
import com.ssafy.ssafience.model.ListResponse;
import com.ssafy.ssafience.model.SingleResponse;
import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.ArticleResult;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.dto.Article;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.dto.Notice;
import com.ssafy.ssafience.model.notice.NoticeModifyRequest;
import com.ssafy.ssafience.model.notice.NoticeWriteRequest;
import com.ssafy.ssafience.service.article.ArticleService;
import com.ssafy.ssafience.service.notice.NoticeService;

import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//@CrossOrigin(origins = { "http://localhost:3000" })
@Api(tags = "Notice : 공지사항")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	static final String SUCCESS = "success";
	static final String FAIL = "fail";
	static final String NOTAVAILABLE = "notavailable";

	@Autowired
	private NoticeService nService;

	@ApiOperation(value = "모든 공지사항 목록 반환")
	@GetMapping
	public ResponseEntity<ListResponse<Notice>> getArticleList() {
		final ListResponse<Notice> result = new ListResponse<>();
		try {
			List<Notice> list = nService.getNoticeList();
			result.result = SUCCESS;
			result.status = HttpStatus.OK;
			result.setList(list);
			result.message = "공지사항 목록 가져오는데 성공했습니다.";

		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "모든 공지사항 목록 가져오는 중 문제가 발생했습니다.";
		}
		return new ResponseEntity<ListResponse<Notice>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "특정 공지사항 반환")
	@GetMapping("/{noticeNo}")
	public ResponseEntity<SingleResponse<Notice>> getMemberArticleList(@PathVariable int noticeNo) {
		final SingleResponse<Notice> result = new SingleResponse<>();

		try {
			Notice notice = nService.getNoticeOne(noticeNo);
			if (notice != null) {
				result.result = SUCCESS;
				result.status = HttpStatus.OK;
				result.setData(notice);
				result.message = "공지사항 목록 가져오는데 성공했습니다.";
			} else {
				result.result = NOTAVAILABLE;
				result.status = HttpStatus.NO_CONTENT;
				result.message = "존재하지 않거나 이미 삭제된 공지사항입니다.";
			}

		} catch (Exception e) {
			result.result = FAIL;
			result.status = HttpStatus.INTERNAL_SERVER_ERROR;
			result.message = "공지사항 상세정보 가져오는 중 문제가 발생했습니다.";
		}

		return new ResponseEntity<SingleResponse<Notice>>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 공지사항 등록")
	@PostMapping
	public ResponseEntity<BasicResponse> signup(Authentication auth, @RequestBody NoticeWriteRequest request) {
		final BasicResponse result = new BasicResponse();

		try {
			Claims claim = (Claims) auth.getPrincipal();
			String memberId = claim.get("data", String.class);
			request.setMemberId(memberId);
			try {
				int insertNotice = nService.insert(request);
				if (insertNotice == 1) {
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
		} catch (Exception e) {
			result.result = FAIL;
			result.message = "인증되지 않은 사용자 입니다. ";
			result.status = HttpStatus.UNAUTHORIZED;
//			e.printStackTrace();
		}

		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "공지사항 수정")
	@PutMapping
	public ResponseEntity<BasicResponse> updateMember(Authentication auth, @RequestBody NoticeModifyRequest request) {
		final BasicResponse result = new BasicResponse();
		try {
			Claims claim = (Claims) auth.getPrincipal();
			String memberId = claim.get("data", String.class);

			try {
				int updateNotice = nService.update(request, memberId);
				if (updateNotice == -1) {
					result.result = NOTAVAILABLE;
					result.status = HttpStatus.NO_CONTENT;
					result.message = "없는 공지사항";
				} else if (updateNotice == 1) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message = "수정 성공";
				} else if (updateNotice == -2) {
					result.result = NOTAVAILABLE;
					result.status = HttpStatus.UNAUTHORIZED;
					result.message = "본인이 작성한 게시글만 수정/삭제 할 수 있습니다.";
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
		} catch (Exception e) {
			result.result = FAIL;
			result.message = "인증되지 않은 사용자 입니다. ";
			result.status = HttpStatus.UNAUTHORIZED;
//			e.printStackTrace();
		}

		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
	}

	@ApiOperation(value = "공지사항 삭제")
	@DeleteMapping("/{noticeNo}")
	public ResponseEntity<BasicResponse> deleteMember(Authentication auth, @PathVariable int noticeNo) {
		final BasicResponse result = new BasicResponse();
		try {
			Claims claim = (Claims) auth.getPrincipal();
			String memberId = claim.get("data", String.class);

			try {
				int deleteNotice = nService.delete(noticeNo, memberId);
				if (deleteNotice == -1) {
					result.result = NOTAVAILABLE;
					result.status = HttpStatus.NO_CONTENT;
					result.message = "없는 공지사항";
				} else if (deleteNotice == 1) {
					result.result = SUCCESS;
					result.status = HttpStatus.OK;
					result.message = "삭제 성공";
				} else if (deleteNotice == -2) {
					result.result = NOTAVAILABLE;
					result.status = HttpStatus.UNAUTHORIZED;
					result.message = "본인이 작성한 게시글만 삭제/수정 할 수 있습니다.";
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
		} catch (Exception e) {
			result.result = FAIL;
			result.message = "인증되지 않은 사용자 입니다. ";
			result.status = HttpStatus.UNAUTHORIZED;
//			e.printStackTrace();
		}

		return new ResponseEntity<BasicResponse>(result, HttpStatus.OK);

	}

}
