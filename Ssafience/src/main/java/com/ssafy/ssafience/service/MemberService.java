package com.ssafy.ssafience.service;

import java.util.Optional;

import com.ssafy.ssafience.model.member.Member;
import com.ssafy.ssafience.model.member.PasswordRequest;

public interface MemberService {
	public Optional<Member> login(String memberId, String memberPw) throws Exception;
	public Member insertMember(Member m) throws Exception;
	public Member getMemberOne(String memberId) throws Exception;
	public boolean deleteMember(String memberId) throws Exception;
	public boolean updateMember(Member m) throws Exception;
	public boolean updateMemberPw(PasswordRequest request) throws Exception;
}
