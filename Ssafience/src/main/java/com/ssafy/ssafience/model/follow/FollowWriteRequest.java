package com.ssafy.ssafience.model.follow;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class FollowWriteRequest {
	private String memberIdFrom;
	private String memberIdTo;
}
