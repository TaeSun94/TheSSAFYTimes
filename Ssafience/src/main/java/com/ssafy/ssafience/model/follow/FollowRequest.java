package com.ssafy.ssafience.model.follow;

import lombok.Builder;
import lombok.Data;

@Data
public class FollowRequest {

	private String memberIdFrom;
	private String memberIdTo;	
	
	@Builder
	public FollowRequest(String memberIdFrom, String memberIdTo) {
		this.memberIdFrom = memberIdFrom;
		this.memberIdTo = memberIdTo;
	}
}
