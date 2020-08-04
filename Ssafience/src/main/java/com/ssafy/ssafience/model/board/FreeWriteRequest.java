package com.ssafy.ssafience.model.board;

import lombok.Getter;

@Getter
public class FreeWriteRequest {
	private String memberId;
	private String freeBoardTitle;
	private String freeBoardContent;
}
