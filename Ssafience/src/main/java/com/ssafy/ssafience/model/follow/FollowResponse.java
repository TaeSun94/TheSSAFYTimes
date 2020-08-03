package com.ssafy.ssafience.model.follow;

import java.util.List;

import com.ssafy.ssafience.model.BasicResponse;
import com.ssafy.ssafience.model.member.Member;

import io.swagger.annotations.ApiModelProperty;

public class FollowResponse extends BasicResponse{
	
	// 나를 따르는 사람
	@ApiModelProperty(value = "follower" , position = 4)
	public List<Member> follower;

	// 내가 따르는 사람
	@ApiModelProperty(value = "following" , position = 4)
	public List<Member> following;

}
