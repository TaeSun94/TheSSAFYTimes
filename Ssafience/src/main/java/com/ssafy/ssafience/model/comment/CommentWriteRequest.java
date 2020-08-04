package com.ssafy.ssafience.model.comment;

import lombok.Getter;

@Getter
public class CommentWriteRequest {
	private int boardNo;
	private String memberId;
	private String commentContent;
}
