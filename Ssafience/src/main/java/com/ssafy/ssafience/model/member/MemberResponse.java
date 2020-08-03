package com.ssafy.ssafience.model.member;

import com.ssafy.ssafience.model.BasicResponse;

import io.swagger.annotations.ApiModelProperty;

public class MemberResponse extends BasicResponse{
	@ApiModelProperty(value = "member" , position = 4)
	public Member member;

}
