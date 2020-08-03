package com.ssafy.ssafience.model.board;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
public class FreeModifyRequest {
	private int freeBoardNo;
	private String memberId;
	private String freeBoardTitle;
	private String freeBoardContent;
	private Date freeBoardUpdateDatetime;
}
