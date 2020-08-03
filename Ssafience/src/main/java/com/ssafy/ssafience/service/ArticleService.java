package com.ssafy.ssafience.service;

import java.util.List;

import com.ssafy.ssafience.model.Article.Article;

public interface ArticleService {
	public List<Article> getArticleList() throws Exception;
	public List<Article> getMyArticleList(String memberId) throws Exception;
	public Article insertArticle(Article a) throws Exception;
	public boolean updateArticle(Article a) throws Exception;
	public boolean deleteArticle(int articleNo) throws Exception;	
	
}
