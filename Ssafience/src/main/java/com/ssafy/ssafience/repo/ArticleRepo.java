package com.ssafy.ssafience.repo;

import java.util.List;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.dto.Article;
import com.ssafy.ssafience.model.dto.ArticleResultDTO;

@Mapper
public interface ArticleRepo {
	public List<ArticleResultDTO> selectArticleList(int pageno, int per);
	public List<ArticleResultDTO> selectMemberArticleList(String memberId, int pageno, int per);
	public List<ArticleResultDTO> selectArticleDetailOne();
	public Article selectArticleOne(int articleNo);
	public int insert(WriteRequest request);
	public int update(ArticleModifyRequest request);
	public int delete(int articleNo);
}
