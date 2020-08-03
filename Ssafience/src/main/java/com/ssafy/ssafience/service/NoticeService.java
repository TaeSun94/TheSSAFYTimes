package com.ssafy.ssafience.service;

import java.util.List;

import com.ssafy.ssafience.model.notice.Notice;

public interface NoticeService {
	public List<Notice> getNoticeList() throws Exception;
	public Notice getNoticeOne(int noticeNo) throws Exception;
	public Notice insertNotice(Notice n) throws Exception;
	public boolean updateNotice(Notice n) throws Exception;
	public boolean deleteNotice(int noticeNo) throws Exception;
	
}
