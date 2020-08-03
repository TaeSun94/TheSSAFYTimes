package com.ssafy.ssafience.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.follow.Follow;
import com.ssafy.ssafience.model.member.Member;

@Repository("FollowJPARepo")
public interface FollowRepo extends JpaRepository<Follow, Integer>{
	Optional<Follow> findByMemberIdFromAndMemberIdTo(String memberIdFrom, String memberIdTo);
}
