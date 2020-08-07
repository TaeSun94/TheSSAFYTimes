package com.ssafy.ssafience.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.dto.TeamApply;
import com.ssafy.ssafience.model.dto.TeamBoard;
import com.ssafy.ssafience.model.dto.TeamBoardResultDTO;
import com.ssafy.ssafience.model.team.TeamApplyRequest;
import com.ssafy.ssafience.model.team.TeamModifyRequest;
import com.ssafy.ssafience.model.team.TeamWriteRequest;
import com.ssafy.ssafience.repo.MemberRepo;
import com.ssafy.ssafience.repo.TeamRepo;

@Service
public class TeamServiceImpl implements TeamService{
	
	@Autowired
	TeamRepo repo;

	@Autowired
	private MemberRepo mRepo;
	
	@Override
	public List<TeamBoardResultDTO> selectBoardList() throws Exception {
		return repo.selectBoardList();
	}
	
	@Override
	public TeamBoardResultDTO selectBoardDetailOne(int boardNo) throws Exception {
		TeamBoardResultDTO board = repo.selectBoardDetailOne(boardNo);
		System.out.println(board);
		if (board != null) {
			return board;
		} else return null;
	}

	@Override
	public TeamBoard selectBoardOne(int boardNo) throws Exception {
		TeamBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			return board;
		} else return null;
	}

	@Override
	public int insert(TeamWriteRequest request) throws Exception {
		Member member = mRepo.selectMemberOne(request.getMemberId());
		if (member != null) {
			int total = request.getTeamBoardFrontRemainCount() + request.getTeamBoardBackRemainCount();
			request.setTeamBoardTotalCount(total);
			return repo.insert(request);
		} else return -1;
	}

	@Override
	public int update(TeamModifyRequest request) throws Exception {
		TeamBoard board = repo.selectBoardOne(request.getBoardNo());
		if (board != null && board.getMemberId().equals(request.getMemberId())) {
			int modFrontCount = request.getBoardFrontRemainCount()-board.getTeamBoardFrontRemainCount();
			int modBackCount = request.getBoardBackRemainCount()-board.getTeamBoardBackRemainCount();
			request.setBoardTotalCount(board.getTeamBoardTotalCount() + modFrontCount + modBackCount);
			return repo.update(request);
		} else return -1;
	}

	@Override
	public int delete(int boardNo) throws Exception {
		TeamBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			return repo.delete(boardNo);
		} else return -1;
	}
	
	@Override
	public List<TeamApply> selectApplyList(int boardNo) throws Exception {
		TeamBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			return repo.selectApplyList(boardNo);
		} else return null;
	}
	
	@Override
	public int apply(TeamApplyRequest request) throws Exception {
		TeamBoard board = repo.selectBoardOne(request.getTeamBoardNo());
		if (board != null) {
			return repo.apply(request);
		} else return -1;
	}
	
	@Override
	public int accept(int applyNo) throws Exception {
		return repo.accept(applyNo);
	}

}
