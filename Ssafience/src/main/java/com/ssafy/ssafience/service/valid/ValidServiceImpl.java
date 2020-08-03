package com.ssafy.ssafience.service.valid;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.valid.EmailCheckRequest;
import com.ssafy.ssafience.repo.ValidRepo;

@Service
public class ValidServiceImpl implements ValidService{

	@Autowired
	ValidRepo repo;
	
	@Override
	public boolean emailDupCheck(EmailCheckRequest request) throws Exception {
		String check = repo.getEmail(request.getMemberEmail().trim());
		if (check!=null) return false;
		else return true;
	}

	@Override
	public boolean checkEmailRegex(String memberEmail) throws Exception {
		boolean emailValid = false;
		String regex = "^[_A-Za-z0-9-]+(.[_A-Za-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		emailValid = Pattern.matches(regex, memberEmail);		
		return emailValid;
	}
}
