package com.ssafy.ssafience.service.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.dto.Notice;
import com.ssafy.ssafience.model.notice.NoticeModifyRequest;
import com.ssafy.ssafience.model.notice.NoticeWriteRequest;
import com.ssafy.ssafience.repo.NoticeRepo;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeRepo repo;
	
	@Override
	public List<Notice> getNoticeList() throws Exception {
		return repo.getNoticeList();
	}

	@Override
	public Notice getNoticeOne(int noticeNo) throws Exception {
		return repo.getNoticeOne(noticeNo);
	}

	@Override
	public int insert(NoticeWriteRequest request) throws Exception {
		return repo.insert(request);
	}

	@Override
	public int update(NoticeModifyRequest request) throws Exception {
		Notice notice = repo.getNoticeOne(request.getNoticeNo());
		if (notice != null) {
			return repo.update(request);
		} else return -1;
	}

	@Override
	public int delete(int noticeNo) throws Exception {
		Notice notice = repo.getNoticeOne(noticeNo);
		if (notice != null) {
			return repo.delete(noticeNo);
		} else return -1;
	}



}
