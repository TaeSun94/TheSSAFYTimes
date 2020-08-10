package com.ssafy.ssafience.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeamApply {
	private int teamApplyNo;
	private int teamBoardNo;
	private String memberId;
	private String teamApplyContent;
	private String teamApplyPosition;
	private int teamApplyStatus;	
}
