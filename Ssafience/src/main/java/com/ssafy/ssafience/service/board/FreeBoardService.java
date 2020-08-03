package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.board.FreeBoard;

public interface FreeBoardService {
	public List<FreeBoard> getFreeBoardList() throws Exception;
	public FreeBoard getBoardOne(int freeBoardNo) throws Exception;
	public FreeBoard insertBoard(FreeBoard board) throws Exception;
	public boolean updateBoard(FreeBoard board) throws Exception;
	public boolean deleteBoard(int freeBoardNo) throws Exception;
	
}
