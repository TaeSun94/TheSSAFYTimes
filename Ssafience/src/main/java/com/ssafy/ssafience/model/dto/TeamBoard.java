package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;

@Getter
public class TeamBoard {
	private int teamBoardNo;
	private String memberId;
	private int teamBoardCategory;
	private String teamBoardTitle;
	private String teamBoardContent;
	private Date teamBoardStartDatetime;
	private Date teamBoardEndDatetime;
	private int teamBoardTotalCount;
	private int teamBoardFrontRemainCount;
	private int teamBoardBackRemainCount;	
	private Date teamBoardDatetime;
}
