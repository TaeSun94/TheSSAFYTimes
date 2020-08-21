package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class Notice {
	private int noticeNo;
	private String memberId;
	private String noticeTitle;
	private String noticeContent;
	private Date noticeDatetime;
}
