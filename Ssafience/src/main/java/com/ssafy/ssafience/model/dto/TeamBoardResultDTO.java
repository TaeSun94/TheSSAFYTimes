package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class TeamBoardResultDTO {
	private int teamBoardNo;
	private String memberId;
	private String teamBoardCategory;
	private String teamBoardTitle;
	private String teamBoardContent;
	private Date teamBoardStartDatetime;
	private Date teamBoardEndDatetime;
	private int teamBoardTotalCount;
	private int teamBoardFrontRemainCount;
	private int teamBoardBackRemainCount;	
	private Date teamBoardDatetime;
}
