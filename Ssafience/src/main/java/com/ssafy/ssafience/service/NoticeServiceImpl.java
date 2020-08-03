package com.ssafy.ssafience.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.notice.Notice;
import com.ssafy.ssafience.repo.NoticeRepo;

@Service("NoticeServiceImpl")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	@Qualifier("NoticeJPARepo")
	NoticeRepo repo;
	
	@Override
	public List<Notice> getNoticeList() throws Exception {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public Notice getNoticeOne(int noticeNo) throws Exception {
		Optional<Notice> noticeOpt = repo.findByNoticeNo(noticeNo);
		if (noticeOpt.isPresent()) return noticeOpt.get();
		else return null;
	}

	@Override
	public Notice insertNotice(Notice n) throws Exception {
		return repo.save(n);
	}

	@Override
	public boolean updateNotice(Notice n) throws Exception {
		Optional<Notice> noticeOpt = repo.findByNoticeNo(n.getNoticeNo());
		if (noticeOpt.isPresent()) {
			Notice tempNotice = noticeOpt.get();
			tempNotice.setNoticeContent(n.getNoticeContent());
			tempNotice.setNoticeTitle(n.getNoticeTitle());
			repo.save(tempNotice);
			return true;
		} else return false;
	}

	@Override
	public boolean deleteNotice(int noticeNo) throws Exception {
		Optional<Notice> noticeOpt = repo.findByNoticeNo(noticeNo);
		if (noticeOpt.isPresent()) {
			repo.delete(noticeOpt.get());
			return true;
		} else return false;
	}

}