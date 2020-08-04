package com.ssafy.ssafience.repo;

import java.util.List;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.board.FreeModifyRequest;
import com.ssafy.ssafience.model.board.FreeWriteRequest;
import com.ssafy.ssafience.model.comment.CommentModifyRequest;
import com.ssafy.ssafience.model.comment.CommentWriteRequest;
import com.ssafy.ssafience.model.dto.FreeBoard;
import com.ssafy.ssafience.model.dto.FreeComment;

@Mapper
public interface FreeRepo {
	public List<FreeBoard> selectBoardList();
	public List<FreeBoard> selectMemberBoardList(String memberId);
	public FreeBoard selectBoardOne(int boardNo);
	public int insert(FreeWriteRequest request);
	public int update(FreeModifyRequest request);
	public int delete(int boardNo);

	public List<FreeComment> selectBoardCommentList(int boardNo);
	public FreeComment selectCommentOne(int commentNo);
	public int insertComment(CommentWriteRequest request);
	public int updateComment(CommentModifyRequest request);
	public int deleteComment(int commentNo);

}
