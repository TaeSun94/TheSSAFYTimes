package com.ssafy.ssafience.model.dto;

import java.sql.Date;

import lombok.Getter;

@Getter
public class FreeBoardLike {
	private int freeBoardLikeNo;
	private int freeBoardNo;
	private String memberId;
	private int freeBoardLikeCheck;	
	private Date freeBoardLikeDatetime;	
}
