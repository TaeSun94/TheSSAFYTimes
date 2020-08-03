package com.ssafy.ssafience.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.Article.Article;

@Repository("ArticleJPARepo")
public interface ArticleRepo extends JpaRepository<Article, Integer>{
	List<Article> findByMemberId(String memberId);
	Optional<Article> findByArticleNo(int articleNo);
}
