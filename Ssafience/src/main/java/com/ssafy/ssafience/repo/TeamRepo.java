package com.ssafy.ssafience.repo;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ssafience.model.dto.TeamBoard;
import com.ssafy.ssafience.model.team.TeamModifyRequest;
import com.ssafy.ssafience.model.team.TeamWriteRequest;

@Mapper
public interface TeamRepo {
	public List<TeamBoard> selectBoardList();
	public TeamBoard selectBoardOne(int boardNo);
	public int insert(TeamWriteRequest request);
	public int update(TeamModifyRequest request);
	public int delete(int boardNo);
}
