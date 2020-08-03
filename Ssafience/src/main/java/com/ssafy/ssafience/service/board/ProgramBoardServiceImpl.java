package com.ssafy.ssafience.service.board;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.board.ProgramBoard;
import com.ssafy.ssafience.repo.ProgramBoardRepo;

@Service("ProgramBoardServiceImpl")
public class ProgramBoardServiceImpl implements ProgramBoardService{
	@Autowired
	@Qualifier("ProgramBoardJPARepo")
	ProgramBoardRepo repo;

	@Override
	public List<ProgramBoard> getProgramBoardList() {
		return repo.findAll();
	}

	@Override
	public ProgramBoard getBoardOne(int programBoardNo) throws Exception {
		Optional<ProgramBoard> boardOpt = repo.findByProgramBoardNo(programBoardNo);
		if (boardOpt.isPresent()) {
			ProgramBoard tempBoard = boardOpt.get();
			tempBoard.setProgramBoardHit(tempBoard.getProgramBoardHit()+1);
			ProgramBoard board = repo.save(tempBoard);
			return board;
		}
		else return null;
	}

	@Override
	public ProgramBoard insertBoard(ProgramBoard board) throws Exception {
		return repo.save(board);
	}

	@Override
	public boolean updateBoard(ProgramBoard board) throws Exception {
		Optional<ProgramBoard> boardOpt = repo.findByProgramBoardNo(board.getProgramBoardNo());
		if (boardOpt.isPresent()) {
			ProgramBoard tempBoard = boardOpt.get();
			tempBoard.setProgramBoardContent(board.getProgramBoardContent());
			tempBoard.setProgramBoardTitle(board.getProgramBoardTitle());
			tempBoard.setProgramBoardTrack(board.getProgramBoardTrack());
			tempBoard.setProgramBoardUpdateDatetime(new Date());
			repo.save(tempBoard);
			return true;
		} else return false;
	}

	@Override
	public boolean deleteBoard(int programBoardNo) throws Exception {
		Optional<ProgramBoard> boardOpt = repo.findByProgramBoardNo(programBoardNo);
		if (boardOpt.isPresent()) {
			repo.delete(boardOpt.get());
			return true;
		}
		else return false;
	}
}
