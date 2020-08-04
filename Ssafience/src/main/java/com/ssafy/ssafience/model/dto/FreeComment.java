package com.ssafy.ssafience.model.dto;

import lombok.Getter;

@Getter
public class FreeComment {
	private int freeCommentNo;
	private int freeBoardNo;
	private String memberId;
	private String freeCommentContent;
	private int freeCommentLike;
	private int freeCommentDislike;
	private int freeCommentDel;
}
