package com.ssafy.ssafience.repo;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.member.ModifyRepoRequest;
import com.ssafy.ssafience.model.member.ModifyRequest;
import com.ssafy.ssafience.model.member.SignUpRequest;

@Mapper
public interface MemberRepo {
	public Member selectMemberOne(String memberId);
	public Member selectMemberOneByMemberEmail(String memberEmail);
	public int insert(Member member);
	public int update(ModifyRepoRequest request);
	public int delete(String memberId);
	public int setMemberAuth(String memberEmail, String memberAuth);
	public int setAuthStatus(String memberEmail, int status);
}
