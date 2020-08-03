package com.ssafy.ssafience.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.Article.Article;
import com.ssafy.ssafience.repo.ArticleRepo;

@Service("ArticleServiceImpl")
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	@Qualifier("ArticleJPARepo")
	ArticleRepo repo;

	@Override
	public List<Article> getArticleList() throws Exception{
		return repo.findAll();
	}

	@Override
	public List<Article> getMyArticleList(String memberId) throws Exception {
		return repo.findByMemberId(memberId);
	}

	@Override
	public Article insertArticle(Article a) throws Exception {
		return repo.save(a);
	}

	@Override
	public boolean updateArticle(Article a) throws Exception {
		Optional<Article> articleOpt = repo.findByArticleNo(a.getArticleNo());
		if (articleOpt.isPresent()) {
			Article tempArticle = articleOpt.get();
			tempArticle.setArticleTitle(a.getArticleTitle());
			tempArticle.setArticleContent(a.getArticleContent());
			tempArticle.setArticleType(a.getArticleType());
			repo.save(tempArticle);
			return true;
		}else return false;
	}

	@Override
	public boolean deleteArticle(int articleNo) throws Exception {
		Optional<Article> articleOpt = repo.findByArticleNo(articleNo);
		if (articleOpt.isPresent()) {
			repo.delete(articleOpt.get());
			return true;
		}else return false;
	}

}
