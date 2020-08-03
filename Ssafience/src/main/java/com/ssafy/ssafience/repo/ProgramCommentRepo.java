package com.ssafy.ssafience.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.comment.ProgramComment;

@Repository("ProgramCommentJPARepo")
public interface ProgramCommentRepo extends JpaRepository<ProgramComment, Integer>{
	List<ProgramComment> findByProgramBoardNo(int programBoardNo);
	Optional<ProgramComment> findByProgramBoardNoAndProgramCommentNo(int programBoardNo, int programCommentNo);
}
