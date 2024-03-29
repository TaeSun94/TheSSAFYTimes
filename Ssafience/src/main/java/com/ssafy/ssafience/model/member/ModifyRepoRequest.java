package com.ssafy.ssafience.model.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ModifyRepoRequest extends ModifyRequest{
	private String memberInterested;
	private String memberSkill;

	@Builder
	public ModifyRepoRequest(ModifyRequest request) {
		this.setMemberId(request.getMemberId());
		this.setMemberFirstName(request.getMemberFirstName());
		this.setMemberLastName(request.getMemberLastName());
		this.setMemberPhone(request.getMemberPhone());
		this.setMemberAddress(request.getMemberAddress());
		this.setMemberRegion(request.getMemberRegion());
		this.setMemberTrack(request.getMemberTrack());
		this.setMemberUnit(request.getMemberUnit());
		this.setMemberIntro(request.getMemberIntro());
		this.setMemberDesc(request.getMemberDesc());
		this.setMemberImgurl(request.getMemberImgurl());
	}
	
	public void insertInterested(String interested) {
		this.memberInterested = interested;
	}
	
	public void insertSkill(String skill) {
		this.memberSkill = skill;
	}
	
}
