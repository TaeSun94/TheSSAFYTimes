package com.ssafy.ssafience.repo;

import java.util.List;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.dto.Article;

@Mapper
public interface ArticleRepo {
	public List<Article> selectArticleList();
	public List<Article> selectMemberArticleList(String memberId);
	public Article selectArticleOne(int articleNo);
	public int insert(WriteRequest request);
	public int update(ArticleModifyRequest request);
	public int delete(int articleNo);
}
