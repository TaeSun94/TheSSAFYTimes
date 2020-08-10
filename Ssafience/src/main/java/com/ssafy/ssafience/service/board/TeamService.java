package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.dto.TeamApply;
import com.ssafy.ssafience.model.dto.TeamBoard;
import com.ssafy.ssafience.model.dto.TeamBoardResultDTO;
import com.ssafy.ssafience.model.team.TeamApplyRequest;
import com.ssafy.ssafience.model.team.TeamModifyRequest;
import com.ssafy.ssafience.model.team.TeamWriteRequest;

public interface TeamService {
	public List<TeamBoardResultDTO> selectBoardList() throws Exception;
	public TeamBoard selectBoardOne(int boardNo) throws Exception;
	public TeamBoardResultDTO selectBoardDetailOne(int boardNo) throws Exception;
	public int insert(TeamWriteRequest request) throws Exception;
	public int update(TeamModifyRequest request) throws Exception;
	public int delete(int boardNo) throws Exception;
	
	public List<TeamApply> selectApplyList(int boardNo) throws Exception;
	public int apply(TeamApplyRequest request) throws Exception;
	public int accept(int applyNo) throws Exception;
}
