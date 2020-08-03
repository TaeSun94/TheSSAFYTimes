package com.ssafy.ssafience.model.member;

import com.ssafy.ssafience.model.BasicResponse;

import io.swagger.annotations.ApiModelProperty;

public class LoginResponse extends BasicResponse{

	@ApiModelProperty(value = "member" , position = 4)
	public Member member;

	@ApiModelProperty(value = "member_auth_status" , position = 5)
	public boolean member_auth_status;
}
