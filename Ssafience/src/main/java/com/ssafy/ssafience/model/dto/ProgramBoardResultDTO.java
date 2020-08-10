package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class ProgramBoardResultDTO {

	private int  programBoardNo;
	private String memberId;
	private String programBoardTitle;
	private String programBoardContent;
	private String programBoardTrack;
	private int programBoardHit;
	private Date programBoardUpdateDatetime;
	private Date programBoardDatetime;
	private int programBoardLike;
	private int programBoardDislike;

}
