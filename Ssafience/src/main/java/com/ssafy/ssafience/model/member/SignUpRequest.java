package com.ssafy.ssafience.model.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
	private String memberEmail;	
	private String memberPw;
}
