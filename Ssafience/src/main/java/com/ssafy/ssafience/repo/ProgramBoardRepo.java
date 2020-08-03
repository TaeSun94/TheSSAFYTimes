package com.ssafy.ssafience.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.board.ProgramBoard;

@Repository("ProgramBoardJPARepo")
public interface ProgramBoardRepo extends JpaRepository<ProgramBoard, Integer>{
	Optional<ProgramBoard> findByProgramBoardNo(int programBoardNo) throws Exception;
}
