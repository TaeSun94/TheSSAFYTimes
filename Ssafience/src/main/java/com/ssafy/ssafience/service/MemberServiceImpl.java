package com.ssafy.ssafience.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.member.Member;
import com.ssafy.ssafience.model.member.PasswordRequest;
import com.ssafy.ssafience.repo.MemberRepo;

@Service("MemberServiceImpl")
public class MemberServiceImpl implements MemberService {

	@Autowired
	@Qualifier("MemberJPARepo")
	MemberRepo repo;

	@Autowired
	PasswordEncoder pEncoder;

	@Override
	public Member insertMember(Member m) throws Exception {
		System.out.println("사용자 입력 비번 : " + m.getMemberPw());
		String encodePw = pEncoder.encode(m.getMemberPw());
		System.out.println("암호화 비번 : " + encodePw);

		m.setMemberPw(encodePw);
		return repo.save(m);
	}

	@Override
	public Optional<Member> login(String memberId, String memberPw) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberId(memberId);

		if (!memberOpt.isPresent()) { // 존재하는 아이디가 없는 경우
			System.out.println("라는 아이디어가 없음");
			return null;
		} else { // 비밀번호 확인
			if (!pEncoder.matches(memberPw, memberOpt.get().getMemberPw())) {
				System.out.println("비밀번호 틀림");
				return null;
			} else {
				return memberOpt;
			}
		}
	}
	
	@Override
	public Member getMemberOne(String memberId) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberId(memberId);
		if (memberOpt.isPresent()) {
			return memberOpt.get();
		} else return null;
	}

	@Override
	public boolean deleteMember(String memberId) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberId(memberId);
		if (memberOpt.isPresent()) {
			repo.delete(memberOpt.get());
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateMember(Member m) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberId(m.getMemberId());
		if (memberOpt.isPresent()) {
			System.out.println(memberOpt.get());
			Member tempMember = memberOpt.get();
			tempMember.setMemberFirstName(m.getMemberFirstName());
			tempMember.setMemberLastName(m.getMemberLastName());
			tempMember.setMemberPhone(m.getMemberPhone());
			tempMember.setMemberRegion(m.getMemberRegion());
			tempMember.setMemberClass(m.getMemberClass());
			tempMember.setMemberTrack(m.getMemberTrack());
			tempMember.setMemberUnit(m.getMemberUnit());
			tempMember.setMemberIntro(m.getMemberIntro());
			tempMember.setMemberDesc(m.getMemberDesc());
			tempMember.setMemberAddress(m.getMemberAddress());
			
			repo.save(tempMember);
			return true;
		} else return false;
	}

	@Override
	public boolean updateMemberPw(PasswordRequest request) throws Exception {
		System.out.println(request.memberid);
		System.out.println(request.oldMemberPw);
		Optional<Member> memberOpt = repo.findByMemberId(request.memberid);
		if (memberOpt.isPresent()) {
			System.out.println(memberOpt.get());
			if (!pEncoder.matches(request.oldMemberPw, memberOpt.get().getMemberPw())) {
				return false;
			} else {
				Member tempMember = memberOpt.get();
				tempMember.setMemberPw(pEncoder.encode(request.newMemberPw));
				repo.save(tempMember);
				return true;
			}
		} else return false;
	}

}
