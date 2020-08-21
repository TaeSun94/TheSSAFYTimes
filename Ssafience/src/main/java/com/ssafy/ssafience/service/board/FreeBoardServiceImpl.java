package com.ssafy.ssafience.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.board.BoardResult;
import com.ssafy.ssafience.model.board.FreeModifyRequest;
import com.ssafy.ssafience.model.board.FreeWriteRequest;
import com.ssafy.ssafience.model.dto.FreeBoard;
import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.hit.HitRequest;
import com.ssafy.ssafience.repo.FreeRepo;
import com.ssafy.ssafience.repo.MemberRepo;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{
	
	@Autowired
	private FreeRepo repo;

	@Autowired
	private MemberRepo mRepo;
	
	@Override
	public List<FreeBoard> selectBoardList() throws Exception {
		return repo.selectBoardList();
	}

	@Override
	public BoardResult<FreeBoard> selectMemberBoardList(String memberId) throws Exception {
		BoardResult<FreeBoard> result = new BoardResult<FreeBoard>();
		Member member = mRepo.selectMemberOne(memberId);
		if (member != null) {
			List<FreeBoard> list = repo.selectMemberBoardList(memberId);
			result.setAuthor(true);			
			result.setBoardList(list);
		} else {
			result.setAuthor(false); // 없는 회원
		}		
		return result;
	}

	@Override
	public FreeBoard selectBoardOne(int boardNo) throws Exception {		
		FreeBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			return board;
		} else return null;
	}

	@Override
	public FreeBoard selectBoardDetailOne(HitRequest request) throws Exception {
		FreeBoard board = repo.selectBoardOne(request.getBoardNo());
		if (board != null) {
			int hit = repo.hit(request);
			return repo.selectBoardOne(request.getBoardNo());
		} else return null;
	}

	@Override
	public int insert(FreeWriteRequest request) throws Exception {
		Member member = mRepo.selectMemberOne(request.getMemberId());
		if (member != null) {
			return repo.insert(request);
		} else return -1;
	}

	@Override
	public int update(FreeModifyRequest request) throws Exception {
		FreeBoard board = repo.selectBoardOne(request.getFreeBoardNo());
		if (board != null && board.getMemberId().equals(request.getMemberId())) {
			return repo.update(request);
		} else return -1;
	}

	@Override
	public int delete(int boardNo) throws Exception {
		FreeBoard board = repo.selectBoardOne(boardNo);
		if (board != null) {
			return repo.delete(boardNo);
		} else return -1;
	}

}
