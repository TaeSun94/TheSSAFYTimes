package com.ssafy.ssafience.model.member;

import java.util.List;

import com.ssafy.ssafience.model.dto.MemberResultDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDetailResult<T> extends MemberResultDTO{
	private List<T> memberInterestedList;
	private List<T> memberSkillList;	
	
	public MemberDetailResult(MemberResultDTO m) {
		super.setMemberNo(m.getMemberNo());
		super.setMemberId(m.getMemberId());
		super.setMemberPw(m.getMemberPw());
		super.setMemberEmail(m.getMemberEmail());
		super.setMemberFirstName(m.getMemberFirstName());
		super.setMemberLastName(m.getMemberLastName());
		super.setMemberPhone(m.getMemberPhone());
		super.setMemberRegion(m.getMemberRegion());
		super.setMemberClass(m.getMemberClass());
		super.setMemberTrack(m.getMemberTrack());
		super.setMemberUnit(m.getMemberUnit());
		super.setMemberIntro(m.getMemberIntro());
		super.setMemberDesc(m.getMemberDesc());
		super.setMemberAuth(m.getMemberAuth());
		super.setMemberAuthStatus(m.isMemberAuthStatus());
		super.setMemberAddress(m.getMemberAddress());
		super.setMemberFollow(m.getMemberFollow());
		super.setMemberFollower(m.getMemberFollower());
		super.setMemberDatetime(m.getMemberDatetime());
		super.setMemberAuthDatetime(m.getMemberAuthDatetime());
		super.setMemberInterested(m.getMemberInterested());
		super.setMemberSkill(m.getMemberSkill());
	}
	
	public void insertIntList(List<T> memberInterestedList) {
		this.memberInterestedList = memberInterestedList;
	}
	
	public void insertSkillList(List<T> memberSkillList) {
		this.memberSkillList = memberSkillList;		
	}
}
