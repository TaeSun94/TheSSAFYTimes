package com.ssafy.ssafience.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.comment.CommentModifyRequest;
import com.ssafy.ssafience.model.comment.CommentResult;
import com.ssafy.ssafience.model.comment.CommentWriteRequest;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.dto.ProgramBoard;
import com.ssafy.ssafience.model.dto.ProgramComment;
import com.ssafy.ssafience.repo.MemberRepo;
import com.ssafy.ssafience.repo.ProgramRepo;

@Service
public class ProgramCommentServiceImpl implements ProgramCommentService{

	@Autowired
	private ProgramRepo repo;
	
	@Autowired
	private MemberRepo mRepo;
	
	@Override
	public CommentResult<ProgramComment> selectBoardCommentList(int boardNo) throws Exception {
		CommentResult<ProgramComment> result = new CommentResult<>();
		ProgramBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			List<ProgramComment> list = repo.selectBoardCommentList(boardNo);
			result.setThere(true);
			result.setCommentList(list);
		} else {
			result.setThere(false);
		}
		return result;
	}

	// 특정 댓글 상세
	@Override
	public CommentResult<ProgramComment> selectCommentOne(int boardNo, int commentNo) throws Exception {
		CommentResult<ProgramComment> result = new CommentResult<>();
		ProgramBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			result.setThere(true);
			ProgramComment comment = repo.selectCommentOne(commentNo);
			result.setData(comment);
		} else {
			result.setThere(false);
		}
		
		return result;
	}

	@Override
	public int insertComment(CommentWriteRequest request) throws Exception {
		ProgramBoard board = repo.selectBoardOne(request.getBoardNo());
		Member member = mRepo.selectMemberOne(request.getMemberId());
		if (member != null && board != null) {
			return repo.insertComment(request);
		} else return -1; // 없는 회원 또는 없는 게시글
	}

	@Override
	public int updateComment(CommentModifyRequest request) throws Exception {
		ProgramComment comment = repo.selectCommentOne(request.getCommentNo());
		if (comment != null) {
			return repo.updateComment(request);
		} else return -1; // 없는 댓글
	}

	@Override
	public int deleteComment(int commentNo) throws Exception {
		ProgramComment comment = repo.selectCommentOne(commentNo);
		if (comment != null) {
			return repo.deleteComment(commentNo);
		} else return -1; // 없는 댓글
	}

}
