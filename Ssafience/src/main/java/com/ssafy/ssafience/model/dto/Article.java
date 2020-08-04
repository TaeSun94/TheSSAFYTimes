package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
public class Article {

	@Getter private int articleNo;
	@Getter @Setter private String memberId;
	@Getter @Setter private String articleTitle;
	@Getter @Setter	private String articleContent;
	@Getter @Setter	private int articleType;
	@Getter private Date articleDatetime;
	
}
