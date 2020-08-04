package com.ssafy.ssafience.model.mail;

import com.ssafy.ssafience.model.dto.Member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendMailResult {
	private boolean isMember;
	private int saveKey;
	private Member data;
}
