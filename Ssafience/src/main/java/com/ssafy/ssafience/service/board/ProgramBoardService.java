package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.board.BoardResult;
import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.board.ProgramWriteRequest;
import com.ssafy.ssafience.model.dto.ProgramBoard;
import com.ssafy.ssafience.model.dto.ProgramBoardResultDTO;

public interface ProgramBoardService {
	public List<ProgramBoardResultDTO> selectBoardList() throws Exception;
	public BoardResult<ProgramBoardResultDTO> selectMemberBoardList(String memberId) throws Exception;
	public ProgramBoard selectBoardOne(int boardNo) throws Exception;
	public ProgramBoardResultDTO selectBoardDetailOne(int boardNo) throws Exception;
	public int insert(ProgramWriteRequest request) throws Exception;
	public int update(ProgramModifyRequest request) throws Exception;
	public int delete(int boardNo) throws Exception;

}
