package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamApplyList {
	private int boardNo;
	private String boardTitle;
	private String boardType;
	private int applyStatus;
	private Date endDate;
}
