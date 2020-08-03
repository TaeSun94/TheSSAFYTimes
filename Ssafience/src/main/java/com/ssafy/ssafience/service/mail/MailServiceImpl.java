package com.ssafy.ssafience.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.mail.AuthResult;
import com.ssafy.ssafience.model.mail.SendMailRequest;
import com.ssafy.ssafience.model.mail.SendMailResult;
import com.ssafy.ssafience.repo.MemberRepo;
import com.ssafy.ssafience.util.SsafienceMailHandler;
import com.ssafy.ssafience.util.TempKey;

@Service
public class MailServiceImpl implements MailService{
	
	@Autowired
	MemberRepo mRepo;
	
	@Autowired
	JavaMailSender mailSender;

	@Override
	public SendMailResult sendValidKey(SendMailRequest request) throws Exception {
		SendMailResult result = new SendMailResult();
		String memberAuth = new TempKey().getKey(20);

		Member member = mRepo.selectMemberOneByMemberEmail(request.getMemberEmail());
		if (member != null) {
			// 데이터 베이스에 인증키 저장!
			SsafienceMailHandler mailHandler = new SsafienceMailHandler(mailSender);
			mailHandler.setSubject("[Ssafience 이메일 인증 요청의 건]");
			mailHandler.setText(request.getMemberEmail(), memberAuth);
			mailHandler.setFrom("glglthssla@naver.com", "Ssafience");
			mailHandler.setTo(request.getMemberEmail());
			mailHandler.send();
			result.setSaveKey(mRepo.setMemberAuth(request.getMemberEmail(), memberAuth));
			result.setMember(true);
			result.setData(member);
		} else {
			result.setMember(false);
		}		
		return result;
	}

	@Override
	public AuthResult checkMemberAuth(String memberEmail, String memberAuth) throws Exception {
		AuthResult result = new AuthResult();
		Member member = mRepo.selectMemberOneByMemberEmail(memberEmail);
		if (member != null) {
			result.setMember(true);
			if (member.getMemberAuth().equals(memberAuth)) {
				System.out.println(memberAuth);
				System.out.println(member.getMemberAuth());
				mRepo.setAuthStatus(memberEmail, 1);
				result.setValid(true);
			} else result.setValid(false);
		} else {
			result.setMember(false);
		}
		
		return result;
	}

}
