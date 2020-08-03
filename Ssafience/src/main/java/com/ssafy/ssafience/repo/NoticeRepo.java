package com.ssafy.ssafience.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.notice.Notice;

@Repository("NoticeJPARepo")
public interface NoticeRepo extends JpaRepository<Notice, Integer>{
	Optional<Notice> findByNoticeNo(int noticeNo);
}
