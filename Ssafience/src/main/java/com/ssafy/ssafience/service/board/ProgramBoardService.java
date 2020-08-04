package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.board.BoardResult;
import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.board.ProgramWriteRequest;
import com.ssafy.ssafience.model.dto.ProgramBoard;

public interface ProgramBoardService {
	public List<ProgramBoard> selectBoardList() throws Exception;
	public BoardResult<ProgramBoard> selectMemberBoardList(String memberId) throws Exception;
	public ProgramBoard selectBoardOne(int boardNo) throws Exception;
	public int insert(ProgramWriteRequest request) throws Exception;
	public int update(ProgramModifyRequest request) throws Exception;
	public int delete(int boardNo) throws Exception;

}
