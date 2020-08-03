package com.ssafy.ssafience.service.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.board.FreeBoard;
import com.ssafy.ssafience.model.comment.FreeComment;
import com.ssafy.ssafience.repo.FreeBoardRepo;
import com.ssafy.ssafience.repo.FreeCommentRepo;

@Service("FreeCommentServiceImpl")
public class FreeCommentServiceImpl implements FreeCommentService{

	@Autowired
	@Qualifier("FreeCommentJPARepo")
	FreeCommentRepo cRepo;
	
	@Autowired
	@Qualifier("FreeBoardJPARepo")
	FreeBoardRepo bRepo;
	
	@Override
	public List<FreeComment> getCommentList(int freeBoardNo) throws Exception {
		Optional<FreeBoard> boardOpt = bRepo.findByFreeBoardNo(freeBoardNo);
		if (boardOpt.isPresent()) {
			List<FreeComment> commentList = cRepo.findByFreeBoardNo(freeBoardNo);
			return commentList;
		} else {
			return null;			
		}
	}

	@Override
	public FreeComment getCommentOne(int freeBoardNo, int freeCommentNo) throws Exception {
		Optional<FreeBoard> boardOpt = bRepo.findByFreeBoardNo(freeBoardNo);
		if (boardOpt.isPresent()) {
			Optional<FreeComment> commentOpt = cRepo.findByFreeBoardNoAndFreeCommentNo(freeBoardNo, freeCommentNo);
			if (commentOpt.isPresent()) {
				return commentOpt.get();
			} else return null;
		} else {
			return null;			
		}
	}

	@Override
	public boolean insertComment(FreeComment comment) throws Exception {
		Optional<FreeBoard> boardOpt = bRepo.findByFreeBoardNo(comment.getFreeBoardNo());
		if (boardOpt.isPresent()) {
			cRepo.save(comment);
			return true;
		} else {
			return false;			
		}
	}

	@Override
	public boolean updateComment(FreeComment comment) throws Exception {
		Optional<FreeBoard> boardOpt = bRepo.findByFreeBoardNo(comment.getFreeBoardNo());
		if (boardOpt.isPresent()) {
			Optional<FreeComment> commmentOpt = cRepo.findByFreeBoardNoAndFreeCommentNo(comment.getFreeBoardNo(), comment.getFreeCommentNo());
			if (commmentOpt.isPresent()) {
				FreeComment tempComment = commmentOpt.get();
				tempComment.setFreeCommentContent(comment.getFreeCommentContent());
				cRepo.save(tempComment);
				return true;				
			} else return false;
		} else {
			return false;			
		}
	}

	@Override
	public boolean deleteComment(int freeBoardNo, int freeCommentNo) throws Exception {
		Optional<FreeBoard> boardOpt = bRepo.findByFreeBoardNo(freeBoardNo);
		if (boardOpt.isPresent()) {
			Optional<FreeComment> commmentOpt = cRepo.findByFreeBoardNoAndFreeCommentNo(freeBoardNo, freeCommentNo);
			if (commmentOpt.isPresent()) {
				cRepo.delete(commmentOpt.get());
				return true;				
			} else return false;
		} else {
			return false;			
		}
	}

}
