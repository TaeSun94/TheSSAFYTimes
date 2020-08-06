package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ProgramBoard {

	private int  programBoardNo;
	private String memberId;
	private String programBoardTitle;
	private String programBoardContent;
	private int programBoardTrack;
	private int programBoardHit;
	private Date programBoardUpdateDatetime;
	private Date programBoardDatetime;
	private int programBoardLikeCount;

}
