package com.ssafy.ssafience.model.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResult<Member> {
	private boolean isMember;
	private boolean isMatch;
	private boolean isValid;
	private Member data;
}
