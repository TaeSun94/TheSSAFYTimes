package com.ssafy.ssafience.model.team;

import lombok.Getter;

@Getter
public class TeamApplyRequest {
	private int teamBoardNo;
	private String memberId;
	private String teamApplyContent;
	private String teamApplyPosition;
}