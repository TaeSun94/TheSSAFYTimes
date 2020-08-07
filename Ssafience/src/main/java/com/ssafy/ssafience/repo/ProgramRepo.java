package com.ssafy.ssafience.repo;

import java.util.List;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.board.ProgramWriteRequest;
import com.ssafy.ssafience.model.comment.CommentModifyRequest;
import com.ssafy.ssafience.model.comment.CommentWriteRequest;
import com.ssafy.ssafience.model.dto.ProgramBoard;
import com.ssafy.ssafience.model.dto.ProgramComment;
import com.ssafy.ssafience.model.like.LikeRequest;

@Mapper
public interface ProgramRepo {
	public List<ProgramBoard> selectBoardList();
	public List<ProgramBoard> selectMemberBoardList(String memberId);
	public ProgramBoard selectBoardOne(int boardNo);
	public int insert(ProgramWriteRequest request);
	public int update(ProgramModifyRequest request);
	public int delete(int boardNo);

	public List<ProgramComment> selectBoardCommentList(int boardNo);
	public ProgramComment selectCommentOne(int commentNo);
	public int insertComment(CommentWriteRequest request);
	public int updateComment(CommentModifyRequest request);
	public int deleteComment(int commentNo);

	public int insertLike(LikeRequest request);

}
