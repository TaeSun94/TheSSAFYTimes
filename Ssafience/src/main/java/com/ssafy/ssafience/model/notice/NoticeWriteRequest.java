package com.ssafy.ssafience.model.notice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class NoticeWriteRequest {
	private String memberId;
	private String noticeTitle;
	private String noticeContent;
}
