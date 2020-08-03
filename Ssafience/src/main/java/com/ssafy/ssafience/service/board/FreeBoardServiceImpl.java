

package com.ssafy.ssafience.service.board;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.board.FreeBoard;
import com.ssafy.ssafience.repo.FreeBoardRepo;

@Service("FreeBoardServiceImpl")
public class FreeBoardServiceImpl implements FreeBoardService{

	@Autowired
	@Qualifier("FreeBoardJPARepo")
	FreeBoardRepo repo;
	
	@Override
	public List<FreeBoard> getFreeBoardList() throws Exception {
		return repo.findAll();
	}

	@Override
	public FreeBoard getBoardOne(int freeBoardNo) throws Exception {
		Optional<FreeBoard> boardOpt = repo.findByFreeBoardNo(freeBoardNo);
		if (boardOpt.isPresent()) {
			FreeBoard tempBoard = boardOpt.get();
			tempBoard.setFreeBoardHit(tempBoard.getFreeBoardHit()+1);
			FreeBoard board =  repo.save(tempBoard);
			return board;
		}
		else return null;
	}

	@Override
	public FreeBoard insertBoard(FreeBoard board) throws Exception {
		return repo.save(board);
	}

	@Override
	public boolean updateBoard(FreeBoard board) throws Exception {
		Optional<FreeBoard> boardOpt = repo.findByFreeBoardNo(board.getFreeBoardNo());
		if (boardOpt.isPresent()) {
			FreeBoard tempBoard = boardOpt.get();
			tempBoard.setFreeBoardContent(board.getFreeBoardContent());
			tempBoard.setFreeBoardTitle(board.getFreeBoardTitle());
			tempBoard.setFreeBoardUpdateDatetime(new Date());
			repo.save(tempBoard);
			return true;
		} else return false;
	}

	@Override
	public boolean deleteBoard(int freeBoardNo) throws Exception {
		Optional<FreeBoard> boardOpt = repo.findByFreeBoardNo(freeBoardNo);
		if (boardOpt.isPresent()) {
			repo.delete(boardOpt.get());
			return true;
		} else return false;
	}

}
