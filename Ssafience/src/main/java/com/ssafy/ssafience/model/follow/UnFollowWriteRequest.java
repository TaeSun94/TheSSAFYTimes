package com.ssafy.ssafience.model.follow;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UnFollowWriteRequest {
	private String memberIdFrom;
	private String memberIdTo;
}
