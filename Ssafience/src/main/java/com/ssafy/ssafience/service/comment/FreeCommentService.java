package com.ssafy.ssafience.service.comment;

import java.util.List;

import com.ssafy.ssafience.model.comment.FreeComment;

public interface FreeCommentService {
	public List<FreeComment> getCommentList(int freeBoardNo) throws Exception;
	public FreeComment getCommentOne(int freeBoardNo, int freeCommentNo) throws Exception;
	public boolean insertComment(FreeComment comment) throws Exception;
	public boolean updateComment(FreeComment comment) throws Exception;
	public boolean deleteComment(int freeBoardNo, int freeCommentNo) throws Exception;
}
