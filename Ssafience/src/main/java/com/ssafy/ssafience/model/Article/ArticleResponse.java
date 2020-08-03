package com.ssafy.ssafience.model.Article;

import java.util.List;

import com.ssafy.ssafience.model.BasicResponse;

import io.swagger.annotations.ApiModelProperty;

public class ArticleResponse extends BasicResponse{
	@ApiModelProperty(value = "article" , position = 4)
	public List<Article> articleList;
}
