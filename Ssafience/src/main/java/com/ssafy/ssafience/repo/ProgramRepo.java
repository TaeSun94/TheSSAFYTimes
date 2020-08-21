package com.ssafy.ssafience.repo;

import java.util.List;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.board.ProgramWriteRequest;
import com.ssafy.ssafience.model.comment.CommentModifyRequest;
import com.ssafy.ssafience.model.comment.CommentWriteRequest;
import com.ssafy.ssafience.model.dto.ProgramBoard;
import com.ssafy.ssafience.model.dto.ProgramBoardResultDTO;
import com.ssafy.ssafience.model.dto.ProgramComment;
import com.ssafy.ssafience.model.hit.HitRequest;
import com.ssafy.ssafience.model.like.LikeRequest;

@Mapper
public interface ProgramRepo {
	public List<ProgramBoardResultDTO> selectBoardList();
	public List<ProgramBoardResultDTO> selectMemberBoardList(String memberId);
	public ProgramBoard selectBoardOne(int boardNo);
	public ProgramBoardResultDTO selectBoardDetailOne(int boardNo);
	public int insert(ProgramWriteRequest request);
	public int update(ProgramModifyRequest request);
	public int delete(int boardNo);

	public int hit(HitRequest request);

	public List<ProgramComment> selectBoardCommentList(int boardNo);
	public ProgramComment selectCommentOne(int commentNo);
	public int insertComment(CommentWriteRequest request);
	public int updateComment(CommentModifyRequest request);
	public int deleteComment(int commentNo);

	public int insertLike(LikeRequest request);
}
