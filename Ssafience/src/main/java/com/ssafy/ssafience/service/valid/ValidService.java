package com.ssafy.ssafience.service.valid;

import com.ssafy.ssafience.model.valid.EmailCheckRequest;

public interface ValidService {
	public boolean emailDupCheck(EmailCheckRequest request) throws Exception;
	public boolean checkEmailRegex(String memberEmail) throws Exception;	
}
