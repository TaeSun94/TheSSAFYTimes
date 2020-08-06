package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.dto.TeamBoard;
import com.ssafy.ssafience.model.team.TeamModifyRequest;
import com.ssafy.ssafience.model.team.TeamWriteRequest;

public interface TeamService {
	public List<TeamBoard> selectBoardList() throws Exception;
	public TeamBoard selectBoardOne(int boardNo) throws Exception;
	public int insert(TeamWriteRequest request) throws Exception;
	public int update(TeamModifyRequest request) throws Exception;
	public int delete(int boardNo) throws Exception;
}
