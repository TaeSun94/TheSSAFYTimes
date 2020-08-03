package com.ssafy.ssafience.model.dto;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ProgramComment {
	private int programCommentNo;
	private int programBoardNo;
	private String memberId;
	private String programCommentContent;
	private int programCommentLike;
	private int programCommentDislike;
	private int programCommentDel;
}
