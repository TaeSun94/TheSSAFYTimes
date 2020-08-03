package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.board.ProgramBoard;

public interface ProgramBoardService {
	public List<ProgramBoard> getProgramBoardList() throws Exception;
	public ProgramBoard getBoardOne(int programBoardNo) throws Exception;
	public ProgramBoard insertBoard(ProgramBoard board) throws Exception;
	public boolean updateBoard(ProgramBoard board) throws Exception;
	public boolean deleteBoard(int programBoardNo) throws Exception;
}
