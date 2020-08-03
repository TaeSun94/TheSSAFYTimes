package com.ssafy.ssafience.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.board.FreeBoard;

@Repository("FreeBoardJPARepo")
public interface FreeBoardRepo extends JpaRepository<FreeBoard, Integer>{
	Optional<FreeBoard> findByFreeBoardNo(int freeBoardNo);
}
