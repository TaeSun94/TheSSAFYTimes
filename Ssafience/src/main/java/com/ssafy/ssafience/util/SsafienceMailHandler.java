package com.ssafy.ssafience.util;

import java.io.UnsupportedEncodingException;

import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SsafienceMailHandler {
	
	private JavaMailSender mailSender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public SsafienceMailHandler(JavaMailSender mailSender) throws MessagingException {
		this.mailSender = mailSender;
		message = this.mailSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		// TODO Auto-generated constructor stub
	}

	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}
	
	public void setText(String memberEmail, String memberAuth) throws MessagingException {
		String url = "http://localhost:8888/ssafience/api/mail/emailKeyValid?memberEmail="+memberEmail
					+ "&memberAuth="+memberAuth;		
		String text = "<h1>The SSAFY Times 이메일 인증</h1><p>The SSAFY Times 를 찾아주셔서 감사합니다. <br>" +
						"아래의 링크를 클릭하여 회원가입을 완료하세요 :)</p><p>" +
						"<a href='"+url+"' target='_blank' >"+
						"이메일 인증 확인!</a></p>";
		
		messageHelper.setText(text, true);
	}
	
	public void setFrom(String email, String name) throws UnsupportedEncodingException, MessagingException {
		messageHelper.setFrom(email, name);
	}
	
	public void setTo(String email) throws MessagingException {
		messageHelper.setTo(email);
	}
	
	public void addInline(String contentId, DataSource dataSource) throws MessagingException {
		messageHelper.addInline(contentId, dataSource);
	}
	
	public void send() {
		mailSender.send(message);
	}
		
}
