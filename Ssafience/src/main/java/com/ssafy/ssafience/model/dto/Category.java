package com.ssafy.ssafience.model.dto;

import lombok.Getter;

@Getter
public class Category {
	private int categoryNo;
	private int categoryManageNo;
	private String categoryName;
	private int categoryNoParent;
	private int categoryOrder;	
}
