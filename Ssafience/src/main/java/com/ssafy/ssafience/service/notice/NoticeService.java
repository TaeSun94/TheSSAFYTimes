package com.ssafy.ssafience.service.notice;

import java.util.List;

import com.ssafy.ssafience.model.dto.Notice;
import com.ssafy.ssafience.model.notice.NoticeModifyRequest;
import com.ssafy.ssafience.model.notice.NoticeWriteRequest;

public interface NoticeService {
	public List<Notice> getNoticeList() throws Exception;
	public Notice getNoticeOne(int noticeNo) throws Exception;
	public int insert(NoticeWriteRequest request) throws Exception;
	public int update(NoticeModifyRequest request, String writer) throws Exception;
	public int delete(int noticeNo, String writer) throws Exception;
}
