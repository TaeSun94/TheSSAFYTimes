package com.ssafy.ssafience.model.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Member {

	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberEmail;
	private String memberFirstName;
	private String memberLastName;
	private String memberPhone;

	private int memberRegion;
	private int memberClass;
	private int memberTrack;
	private int memberUnit;

	private String memberIntro;
	private String memberDesc;
	private String memberAuth;
	private boolean memberAuthStatus;
	private String memberAddress;
	
	private int memberFollow;
	private int memberFollower;
	private String memberInterested;
	private String memberSkill;

	private Date memberDatetime;
	private Date memberAuthDatetime;

	
	@Builder
	public Member(String memberId, String memberPw, String memberEmail) {
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberEmail = memberEmail;
	}
}
