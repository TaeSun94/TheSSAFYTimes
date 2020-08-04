package com.ssafy.ssafience.service.mail;

import com.ssafy.ssafience.model.mail.AuthResult;
import com.ssafy.ssafience.model.mail.SendMailRequest;
import com.ssafy.ssafience.model.mail.SendMailResult;

public interface MailService {
	public SendMailResult sendValidKey(SendMailRequest request) throws Exception;
	public AuthResult checkMemberAuth(String memberEmail, String memberAuth) throws Exception;
}
