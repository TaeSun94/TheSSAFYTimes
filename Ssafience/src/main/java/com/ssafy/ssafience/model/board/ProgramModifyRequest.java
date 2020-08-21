package com.ssafy.ssafience.model.board;

import lombok.Getter;

@Getter
public class ProgramModifyRequest {
	private int  programBoardNo;
	private String memberId;
	private String programBoardTitle;
	private String programBoardContent;
	private int programBoardTrack;	
}
