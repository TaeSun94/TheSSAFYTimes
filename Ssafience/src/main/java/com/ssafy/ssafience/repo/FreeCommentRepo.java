package com.ssafy.ssafience.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.comment.FreeComment;

@Repository("FreeCommentJPARepo")
public interface FreeCommentRepo extends JpaRepository<FreeComment, Integer>{
	List<FreeComment> findByFreeBoardNo(int freeBoardNo);
	Optional<FreeComment> findByFreeBoardNoAndFreeCommentNo(int freeBoardNo, int freeCommentNo);
}
