package com.ssafy.ssafience.model.comment;

import lombok.Getter;

@Getter
public class CommentModifyRequest {
	private int commentNo;
	private String memberId;
	private String commentContent;
}
