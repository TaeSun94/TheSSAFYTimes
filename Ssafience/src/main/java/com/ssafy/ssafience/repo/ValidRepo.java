package com.ssafy.ssafience.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.ssafience.model.member.Member;

@Repository("ValidJPARepo")
public interface ValidRepo extends JpaRepository<Member, String>{

	Optional<Member> findByMemberId(String memberId);
	Optional<Member> findByMemberEmail(String MemberEmail);
	Optional<Member> findByMemberEmailAndMemberAuth(String memberEmail, String memberAuth);

}
