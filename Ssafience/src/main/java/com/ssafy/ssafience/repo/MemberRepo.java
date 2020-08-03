package com.ssafy.ssafience.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.member.Member;


@Repository("MemberJPARepo")
public interface MemberRepo extends JpaRepository<Member, Integer>{

	Optional<Member> findByMemberId(String memberId);
	Optional<Member> findByMemberEmail(String MemberEmail);
	Optional<Member> findByMemberIdAndMemberPw(String memberId, String memberPw);

}
