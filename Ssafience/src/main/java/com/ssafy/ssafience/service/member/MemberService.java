package com.ssafy.ssafience.service.member;

import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.member.LoginResult;
import com.ssafy.ssafience.model.member.MemberDetailResult;
import com.ssafy.ssafience.model.member.ModifyRequest;
import com.ssafy.ssafience.model.member.SignInRequest;
import com.ssafy.ssafience.model.member.SignUpRequest;

public interface MemberService {
	public Member selectMemberOne(String memberId) throws Exception;
	public MemberDetailResult selectMemberOneDetail(String memberId) throws Exception;
	public LoginResult<Member> singIn(SignInRequest request) throws Exception;
	public int insert(SignUpRequest request) throws Exception;
	public int update(ModifyRequest request) throws Exception;
	public int delete(String memberId) throws Exception;
}
