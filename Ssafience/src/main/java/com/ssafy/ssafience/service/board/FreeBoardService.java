package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.board.BoardResult;
import com.ssafy.ssafience.model.board.FreeModifyRequest;
import com.ssafy.ssafience.model.board.FreeWriteRequest;
import com.ssafy.ssafience.model.dto.FreeBoard;
import com.ssafy.ssafience.model.hit.HitRequest;

public interface FreeBoardService {
	public List<FreeBoard> selectBoardList() throws Exception;
	public BoardResult<FreeBoard> selectMemberBoardList(String memberId) throws Exception;
	public FreeBoard selectBoardOne(int boardNo) throws Exception;
	public FreeBoard selectBoardDetailOne(HitRequest request) throws Exception;
	public int insert(FreeWriteRequest request) throws Exception;
	public int update(FreeModifyRequest request) throws Exception;
	public int delete(int boardNo) throws Exception;

}
