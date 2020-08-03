package com.ssafy.ssafience.model.notice;

import lombok.Getter;

@Getter
public class NoticeWriteRequest {
	private String memberId;
	private String noticeTitle;
	private String noticeContent;
}
