package com.ssafy.ssafience.service.comment;

import java.util.List;

import com.ssafy.ssafience.model.comment.ProgramComment;

public interface ProgramCommentService {
	public List<ProgramComment> getCommentList(int programBoardNo) throws Exception;
	public ProgramComment getCommentOne(int programBoardNo, int programCommentNo) throws Exception;
	public boolean insertComment(ProgramComment comment) throws Exception;
	public boolean updateComment(ProgramComment comment) throws Exception;
	public boolean deleteComment(int programBoardNo, int programCommentNo) throws Exception;
}
