package com.ssafy.ssafience.service.article;

import java.util.List;

import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.ArticleResult;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.dto.Article;

public interface ArticleService {
	public List<Article> selectArticleList() throws Exception;
	public ArticleResult selectMemberArticleList(String memberId) throws Exception;
	public Article selectArticleOne(int articleNo) throws Exception;
	public int insert(WriteRequest request) throws Exception;
	public int update(ArticleModifyRequest request) throws Exception;
	public int delete(int articleNo) throws Exception;
}
