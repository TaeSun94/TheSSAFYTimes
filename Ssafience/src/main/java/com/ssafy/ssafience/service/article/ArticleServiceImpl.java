package com.ssafy.ssafience.service.article;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.article.ArticleModifyRequest;
import com.ssafy.ssafience.model.article.ArticleResult;
import com.ssafy.ssafience.model.article.WriteRequest;
import com.ssafy.ssafience.model.dto.Article;
import com.ssafy.ssafience.model.dto.ArticleResultDTO;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.repo.ArticleRepo;
import com.ssafy.ssafience.repo.MemberRepo;

@Service
public class ArticleServiceImpl implements ArticleService{
	
	@Autowired
	private ArticleRepo repo;
	
	@Autowired
	private MemberRepo mRepo;
	
	@Override
	public List<ArticleResultDTO> selectArticleList() throws Exception {
		return repo.selectArticleList();
	}

	@Override
	public ArticleResult<ArticleResultDTO> selectMemberArticleList(String memberId) throws Exception {
		ArticleResult<ArticleResultDTO> result = new ArticleResult<>();
		Member member = mRepo.selectMemberOne(memberId);
		if (member != null) {
			List<ArticleResultDTO> list = repo.selectMemberArticleList(memberId);
			result.setMember(true);
			result.setList(list);
		} else {
			result.setMember(false); // 없는 회원
		}
		return result;	
	}
	
	@Override
	public Article selectArticleOne(int articleNo) throws Exception {
		Article article = repo.selectArticleOne(articleNo);
		if (article != null) {
			return article;
		} else return null;	// 없는 뉴스피드
	}

	@Override
	public int insert(WriteRequest request) throws Exception {
		Member member = mRepo.selectMemberOne(request.getMemberId());
		if (member != null) {
			return repo.insert(request);
		} else return -1;	// 없는 회원
	}

	@Override
	public int update(ArticleModifyRequest request) throws Exception {
		Article article = repo.selectArticleOne(request.getArticleNo());
		if (article != null && article.getMemberId().equals(request.getMemberId())) {
			return repo.update(request);
		} return -1; // 해당 회원에게 없는 뉴스피드
	}

	@Override
	public int delete(int articleNo) throws Exception {
		Article article = repo.selectArticleOne(articleNo);
		if (article != null) {
			return repo.delete(articleNo);
		} return -1; // 없는 뉴스피드
	}

}
