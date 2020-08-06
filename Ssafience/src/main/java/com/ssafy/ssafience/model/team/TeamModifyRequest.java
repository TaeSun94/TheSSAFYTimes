package com.ssafy.ssafience.model.team;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
public class TeamModifyRequest {
	private int boardNo;
	private String memberId;
	private int boardCategory;
	private String boardTitle;
	private String boardContent;
	private Date boardStartDatetime;
	private Date boardEndDatetime;
	@Setter
	private int boardTotalCount;
	private int boardFrontRemainCount;
	private int boardBackRemainCount;
}
