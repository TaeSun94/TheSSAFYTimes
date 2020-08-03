package com.ssafy.ssafience.model.article;

import lombok.Getter;

@Getter
public class ArticleModifyRequest {
	private int articleNo;
	private String memberId;
	private String articleTitle;
	private String articleContent;
	private int articelType;
}
