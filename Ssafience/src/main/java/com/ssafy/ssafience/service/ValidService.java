package com.ssafy.ssafience.service;

public interface ValidService {
	public boolean isMember(String memberEmail) throws Exception;
	public boolean checkIdDup(String memberId) throws Exception;
	public boolean checkAuthStatus(String memberId) throws Exception;
	public boolean confirmMemberAuth(String memberEmail) throws Exception;
	public boolean checkEmailDup(String memberEmail) throws Exception;
	public boolean checkEmailRegex(String memberEmail) throws Exception;
	public boolean sendValidKey(String memberEmail) throws Exception;
	public boolean checkValidKey(String memberEmail, String memberAuth) throws Exception;

}
