package com.ssafy.ssafience.repo;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.dto.MemberResultDTO;
import com.ssafy.ssafience.model.member.ModifyRepoRequest;

@Mapper
public interface MemberRepo {
	public Member selectMemberOne(String memberId);
	public Member selectMemberOneByMemberEmail(String memberEmail);
	public MemberResultDTO selectMemberDetail(String memberId);
	public int insert(Member member);
	public int update(ModifyRepoRequest request);
	public int delete(String memberId);
	public int setMemberAuth(String memberEmail, String memberAuth);
	public int setAuthStatus(String memberEmail, int status);
}
