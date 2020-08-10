package com.ssafy.ssafience.model.article;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.ssafience.model.dto.Article;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleResult<T> {
	private boolean isMember;
	private List<T> list;
	
	public ArticleResult() {
		this.list = new ArrayList<T>();
	}
}
