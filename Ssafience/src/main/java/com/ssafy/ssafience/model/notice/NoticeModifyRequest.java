package com.ssafy.ssafience.model.notice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeModifyRequest {
	private int noticeNo;
	private String noticeTitle;
	private String noticeContent;
}
