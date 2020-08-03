package com.ssafy.ssafience.service;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.member.Member;
import com.ssafy.ssafience.repo.ValidRepo;
import com.ssafy.ssafience.util.SsafienceMailHandler;
import com.ssafy.ssafience.util.TempKey;

@Service("ValidServiceImpl")
public class ValidServiceImpl implements ValidService{
	
	@Autowired
	ValidRepo repo;
	
	@Autowired
	JavaMailSender mailSender;
	
	@Override
	public boolean checkIdDup(String memberId) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberId(memberId);
		if (memberOpt.isPresent()) {
			System.out.println(memberOpt.get());
			return false;
		} else return true;

	}
	
	@Override
	public boolean checkEmailDup(String memberEmail) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberEmail(memberEmail);
		if (memberOpt.isPresent()) {	// 중복
			return false;
		} else return true;
	}

	@Transactional
	@Override
	public boolean sendValidKey(String memberEmail) throws Exception{
		String memberAuth = new TempKey().getKey(20);

		// 데이터 베이스에 인증키 저장
		Optional<Member> memberOpt = repo.findByMemberEmail(memberEmail);
		if (memberOpt.isPresent()) {	// 이메일 인증메일 보내기
			
			System.out.println("인증키 : "+memberAuth);

			SsafienceMailHandler mailHandler = new SsafienceMailHandler(mailSender);
			mailHandler.setSubject("[Ssafience 이메일 인증 요청의 건]");
			mailHandler.setText(memberEmail, memberAuth);
			mailHandler.setFrom("glglthssla@naver.com", "Ssafience");
			mailHandler.setTo(memberEmail);
			mailHandler.send();
			memberOpt.get().setMemberAuth(memberAuth);
			repo.save(memberOpt.get());
			System.out.println("메일 전송 완료 ");

			return true;
		} else {
			return false;
		}
		
	}

	@Override
	public boolean checkEmailRegex(String memberEmail) throws Exception {
		boolean emailValid = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		
		emailValid = Pattern.matches(regex, memberEmail);
		
		return emailValid;
	}

	@Override
	public boolean checkValidKey(String memberEmail, String memberAuth) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberEmailAndMemberAuth(memberEmail, memberAuth);
		if (memberOpt.isPresent()) {	// 이메일 & 인증키 매칭 -> 이메일 인증 성공 
			return true;
		} else return false;		// 이메일 인증 실패
	}

	@Override
	public boolean confirmMemberAuth(String memberEmail) throws Exception {
		Optional<Member> membetOpt = repo.findByMemberEmail(memberEmail);
		if (membetOpt.isPresent()) {
			Date now = new Date();
			membetOpt.get().setMemberAuthStatus(true);			
			membetOpt.get().setMemberAuthDatetime(now);		// 인증 완료 시간 등록
			repo.save(membetOpt.get());
			return true;
		} else return false;
	}

	@Override
	public boolean checkAuthStatus(String memberId) throws Exception {
		
		Optional<Member> memberOpt = repo.findByMemberId(memberId);
		
		if (memberOpt.isPresent()) {
			System.out.println(memberOpt.get());
			if (memberOpt.get().isMemberAuthStatus()) {		// 이메일 인증 완료
				return true;
			} else return false;
		} else return false;	// 없는 회원

	}

	@Override
	public boolean isMember(String memberEmail) throws Exception {
		Optional<Member> memberOpt = repo.findByMemberEmail(memberEmail);
		if (memberOpt.isPresent()) {
			return true;
		} else return false;
	}

}
