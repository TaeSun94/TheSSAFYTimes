package com.ssafy.ssafience.model.team;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TeamWriteRequest {
	private String memberId;
	private int teamBoardCategory;
	private String teamBoardTitle;
	private String teamBoardContent;
	private Date teamBoardStartDatetime;
	private Date teamBoardEndDatetime;
	@Setter
	private int teamBoardTotalCount;
	private int teamBoardFrontRemainCount;
	private int teamBoardBackRemainCount;	

}
