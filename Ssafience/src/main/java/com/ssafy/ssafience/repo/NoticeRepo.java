package com.ssafy.ssafience.repo;

import java.util.List;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.dto.Notice;
import com.ssafy.ssafience.model.notice.NoticeModifyRequest;
import com.ssafy.ssafience.model.notice.NoticeWriteRequest;

@Mapper
public interface NoticeRepo {
	public List<Notice> getNoticeList();
	public Notice getNoticeOne(int noticeNo);
	public int insert(NoticeWriteRequest request);
	public int update(NoticeModifyRequest request);
	public int delete(int noticeNo);
}
