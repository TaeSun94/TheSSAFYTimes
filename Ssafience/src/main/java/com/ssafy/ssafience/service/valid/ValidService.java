package com.ssafy.ssafience.service.valid;

import com.ssafy.ssafience.model.valid.EmailCheckRequest;
import com.ssafy.ssafience.model.valid.IdCheckRequest;

public interface ValidService {
	public boolean idDupCheck(IdCheckRequest request) throws Exception;
	public boolean emailDupCheck(EmailCheckRequest request) throws Exception;
	public boolean checkEmailRegex(String memberEmail) throws Exception;	
}
