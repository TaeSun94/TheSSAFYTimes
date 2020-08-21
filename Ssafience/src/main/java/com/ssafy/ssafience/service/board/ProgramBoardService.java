package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.board.BoardResult;
import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.board.ProgramWriteRequest;
import com.ssafy.ssafience.model.dto.ProgramBoard;
import com.ssafy.ssafience.model.dto.ProgramBoardResultDTO;
import com.ssafy.ssafience.model.hit.HitRequest;

public interface ProgramBoardService {
	public List<ProgramBoardResultDTO> selectBoardList() throws Exception;
	public BoardResult<ProgramBoardResultDTO> selectMemberBoardList(String memberId) throws Exception;
	public ProgramBoard selectBoardOne(int boardNo) throws Exception;
	public ProgramBoardResultDTO selectBoardDetailOne(HitRequest request) throws Exception;
	public int insert(ProgramWriteRequest request) throws Exception;
	public int update(ProgramModifyRequest request) throws Exception;
	public int delete(int boardNo) throws Exception;

}
