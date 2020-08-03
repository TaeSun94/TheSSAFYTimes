package com.ssafy.ssafience.service.comment;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.board.ProgramBoard;
import com.ssafy.ssafience.model.comment.ProgramComment;
import com.ssafy.ssafience.repo.ProgramBoardRepo;
import com.ssafy.ssafience.repo.ProgramCommentRepo;

@Service("ProgramCommentServiceImpl")
public class ProgramCommentServiceImpl implements ProgramCommentService {

	@Autowired
	@Qualifier("ProgramCommentJPARepo")
	ProgramCommentRepo cRepo;

	@Autowired
	@Qualifier("ProgramBoardJPARepo")
	ProgramBoardRepo bRepo;

	@Override
	public List<ProgramComment> getCommentList(int programBoardNo) throws Exception {
		Optional<ProgramBoard> boardOpt = bRepo.findByProgramBoardNo(programBoardNo);
		if (boardOpt.isPresent()) {
			List<ProgramComment> commentList = cRepo.findByProgramBoardNo(programBoardNo);
			return commentList;
		} else
			return null;
	}

	@Override
	public ProgramComment getCommentOne(int programBoardNo, int programCommentNo) throws Exception {
		Optional<ProgramBoard> boardOpt = bRepo.findByProgramBoardNo(programBoardNo);
		if (boardOpt.isPresent()) {
			Optional<ProgramComment> commentOpt = cRepo.findByProgramBoardNoAndProgramCommentNo(programBoardNo,
					programCommentNo);
			if (commentOpt.isPresent()) {
				return commentOpt.get();
			} else
				return null;
		} else
			return null;
	}

	@Override
	public boolean insertComment(ProgramComment comment) throws Exception {
		Optional<ProgramBoard> boardOpt = bRepo.findByProgramBoardNo(comment.getProgramBoardNo());
		if (boardOpt.isPresent()) {
			cRepo.save(comment);
			return true;
		} else
			return false;
	}

	@Override
	public boolean updateComment(ProgramComment comment) throws Exception {
		Optional<ProgramBoard> boardOpt = bRepo.findByProgramBoardNo(comment.getProgramBoardNo());
		if (boardOpt.isPresent()) {
			Optional<ProgramComment> commentOpt = cRepo.findByProgramBoardNoAndProgramCommentNo(
					comment.getProgramBoardNo(), comment.getProgramCommentNo());
			if (commentOpt.isPresent()) {
				ProgramComment tempComment = commentOpt.get();
				tempComment.setProgramCommentContent(comment.getProgramCommentContent());
				cRepo.save(tempComment);
				return true;
			} else
				return false;
		} else
			return false;
	}

	@Override
	public boolean deleteComment(int programBoardNo, int programCommentNo) throws Exception {
		Optional<ProgramBoard> boardOpt = bRepo.findByProgramBoardNo(programBoardNo);
		if (boardOpt.isPresent()) {
			Optional<ProgramComment> commentOpt = cRepo.findByProgramBoardNoAndProgramCommentNo(programBoardNo,
					programCommentNo);
			if (commentOpt.isPresent()) {
				cRepo.delete(commentOpt.get());
				return true;
			} else
				return false;
		} else
			return false;
	}

}
