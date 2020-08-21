package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class FreeBoard {

	private int  freeBoardNo;
	private String memberId;
	private String freeBoardTitle;
	private String freeBoardContent;
	private int freeBoardHit;
	private Date freeBoardUpdateDatetime;
	private Date freeBoardDatetime;
	private int freeBoardLike;
	private int freeBoardDislike;

}
