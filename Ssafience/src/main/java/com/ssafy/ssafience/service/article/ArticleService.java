package com.ssafy.ssafience.service.article;

import java.util.List;

import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.ArticleResult;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.dto.Article;
import com.ssafy.ssafience.model.dto.ArticleResultDTO;

public interface ArticleService {
	public List<ArticleResultDTO> selectArticleList(int pageno) throws Exception;
	public ArticleResult<ArticleResultDTO> selectMemberArticleList(String memberId, int pageno) throws Exception;
	public Article selectArticleOne(int articleNo) throws Exception;
	public int insert(WriteRequest request) throws Exception;
	public int update(ArticleModifyRequest request) throws Exception;
	public int delete(int articleNo) throws Exception;
}
