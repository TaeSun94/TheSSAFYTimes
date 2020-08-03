package com.ssafy.ssafience.model.dto;

import java.sql.Date;

import lombok.Getter;

@Getter
public class Follow {
	private int followNo;
	private String memberIdFrom;
	private String memberIdTo;
	private Date followDatetime;
}

