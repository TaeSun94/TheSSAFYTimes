package com.ssafy.ssafience.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.comment.CommentModifyRequest;
import com.ssafy.ssafience.model.comment.CommentResult;
import com.ssafy.ssafience.model.comment.CommentWriteRequest;
import com.ssafy.ssafience.model.dto.FreeBoard;
import com.ssafy.ssafience.model.dto.FreeComment;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.repo.FreeRepo;
import com.ssafy.ssafience.repo.MemberRepo;

@Service
public class FreeCommentServiceImpl implements FreeCommentService{
	
	@Autowired
	private FreeRepo repo;
	
	@Autowired
	private MemberRepo mRepo;
	
	@Override
	public CommentResult<FreeComment> selectBoardCommentList(int boardNo) throws Exception {
		CommentResult<FreeComment> result = new CommentResult<>();
		FreeBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			List<FreeComment> list = repo.selectBoardCommentList(boardNo);
			result.setThere(true);
			result.setCommentList(list);
		} else {
			result.setThere(false);
		}
		
		return result;
	}

	@Override
	public CommentResult<FreeComment> selectCommentOne(int boardNo, int commentNo) throws Exception {
		CommentResult<FreeComment> result = new CommentResult<>();
		FreeBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			result.setThere(true);
			FreeComment comment = repo.selectCommentOne(commentNo);
			result.setData(comment);
		} else {
			result.setThere(false);
		}
		return result;
	}

	@Override
	public int insertComment(CommentWriteRequest request) throws Exception {
		FreeBoard board = repo.selectBoardOne(request.getBoardNo());
		Member member = mRepo.selectMemberOne(request.getMemberId());
		if (member != null && board != null) {
			return repo.insertComment(request);
		} else return -1;	// 없는 회원 또는 없는 게시글
	}

	@Override
	public int updateComment(CommentModifyRequest request) throws Exception {
		FreeComment comment = repo.selectCommentOne(request.getCommentNo());
		if (comment != null) {
			return repo.updateComment(request);
		} else return -1;	// 없는 댓글
	}

	@Override
	public int deleteComment(int commentNo) throws Exception {
		FreeComment comment = repo.selectCommentOne(commentNo);
		if (comment != null) {
			return repo.deleteComment(commentNo);
		} else return -1;	// 없는 댓글
	}

}
