package com.ssafy.ssafience.service.board;

import java.util.List;

import com.ssafy.ssafience.model.dto.TeamApply;
import com.ssafy.ssafience.model.dto.TeamApplyList;
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
	
	// 자신의 Status == 1인 프로젝트 목록
	public List<TeamApplyList> selectMyAppliedTeam(String memberId) throws Exception;

	// 자신이 지원한 프로젝트의 EndDateTime이 오늘 날짜 이후 인 것만 
	public List<TeamApplyList> selectMyApplyList(String memberId) throws Exception;

}
