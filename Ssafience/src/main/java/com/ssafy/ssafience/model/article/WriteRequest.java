package com.ssafy.ssafience.model.article;

import lombok.Getter;

@Getter
public class WriteRequest {

	private String memberId;
	private String articleTitle;
	private String articleContent;
	private int articleType;
	
}
