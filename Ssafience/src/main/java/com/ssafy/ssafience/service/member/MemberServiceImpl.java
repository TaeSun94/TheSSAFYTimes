package com.ssafy.ssafience.service.member;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.member.LoginResult;
import com.ssafy.ssafience.model.member.MemberDetailResult;
import com.ssafy.ssafience.model.member.ModifyRepoRequest;
import com.ssafy.ssafience.model.member.ModifyRequest;
import com.ssafy.ssafience.model.member.SignInRequest;
import com.ssafy.ssafience.model.member.SignUpRequest;
import com.ssafy.ssafience.repo.MemberRepo;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepo repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public Member selectMemberOne(String memberId) throws Exception {
		return repo.selectMemberOne(memberId);
	}
	
	@Override
	public MemberDetailResult selectMemberOneDetail(String memberId) throws Exception {
		MemberDetailResult result = null;
		Member member = repo.selectMemberOne(memberId);
		System.out.println(memberId+":"+member);
		System.out.println(member != null);
		if (member != null) {
			result = new MemberDetailResult(member);
			if (member.getMemberInterested()!=null) {
				List<String> interestedList = Arrays.asList(member.getMemberInterested().split(","));				
				result.insertIntList(interestedList);
			}
			if (member.getMemberSkill()!=null) {
				List<String> skillList = Arrays.asList(member.getMemberSkill().split(","));
				result.insertSkillList(skillList);
			}
			System.out.println(memberId+":"+result);
			return result;
		} else {
			System.out.println(memberId+":"+result);
			return result;
		}
	}
	
	@Override
	public LoginResult<Member> singIn(SignInRequest request) throws Exception {
		
		LoginResult<Member> result = new LoginResult<>();
		Member member = repo.selectMemberOneByMemberEmail(request.getMemberEmail());
		if (member != null) {
			result.setMember(true);
			if (encoder.matches(request.getMemberPw(), member.getMemberPw())) {
				result.setValid(true);
				result.setData(member);
			} else {
				result.setValid(false);
			}
		} else {
			result.setMember(false);
		}
		
		return result;
	}

	// 회원가입
	@Override
	public int insert(SignUpRequest request) throws Exception {
		System.out.println(request);
		String encodePw = encoder.encode(request.getMemberPw());
		Member member = Member.builder()
				.memberId(request.getMemberId())
				.memberPw(encodePw)
				.memberEmail(request.getMemberEmail())
				.build();
		
		return repo.insert(member);
	}

	@Override
	public int update(ModifyRequest request) throws Exception {
		Member member = repo.selectMemberOne(request.getMemberId());
		if (member != null) {
			String memberInterested = String.join(",", request.getInterestedList());
			String memberSkill = String.join(",", request.getSkillList());
			ModifyRepoRequest repoRequest = new ModifyRepoRequest(request, memberInterested, memberSkill);
			return repo.update(repoRequest);
		} else return -1;	// 없는 회원
	}

	@Override
	public int delete(String memberId) throws Exception {
		Member member = repo.selectMemberOne(memberId);
		if (member != null) {
			return repo.delete(memberId);
		} else return -1;	// 없는 회원
	}

}
