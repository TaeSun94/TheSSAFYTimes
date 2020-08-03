package com.ssafy.ssafience.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.board.BoardResult;
import com.ssafy.ssafience.model.board.ProgramModifyRequest;
import com.ssafy.ssafience.model.board.ProgramWriteRequest;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.dto.ProgramBoard;
import com.ssafy.ssafience.repo.FreeRepo;
import com.ssafy.ssafience.repo.MemberRepo;
import com.ssafy.ssafience.repo.ProgramRepo;

@Service
public class ProgramBoardServiceImpl implements ProgramBoardService{
	
	@Autowired
	private ProgramRepo repo;

	@Autowired
	private MemberRepo mRepo;
	
	@Override
	public List<ProgramBoard> selectBoardList() throws Exception {
		return repo.selectBoardList();
	}

	@Override
	public BoardResult<ProgramBoard> selectMemberBoardList(String memberId) throws Exception {
		BoardResult<ProgramBoard> result = new BoardResult<ProgramBoard>();
		Member member = mRepo.selectMemberOne(memberId);
		if (member != null) {
			List<ProgramBoard> list = repo.selectMemberBoardList(memberId);
			result.setAuthor(true);
			result.setBoardList(list);
		} else {
			result.setAuthor(false);
		}
		return result;
	}

	@Override
	public ProgramBoard selectBoardOne(int boardNo) throws Exception {
		ProgramBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			return board;
		} else return null;
	}

	@Override
	public int insert(ProgramWriteRequest request) throws Exception {
		Member member = mRepo.selectMemberOne(request.getMemberId());
		if (member != null) {
			return repo.insert(request);
		} else return -1;
	}

	@Override
	public int update(ProgramModifyRequest request) throws Exception {
		ProgramBoard board = repo.selectBoardOne(request.getProgramBoardNo());
		if (board != null && board.getMemberId().equals(request.getMemberId())) {
			return repo.update(request);
		} else return -1;
	}

	@Override
	public int delete(int boardNo) throws Exception {
		ProgramBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			return repo.delete(boardNo);
		} else return -1;
	}

}
