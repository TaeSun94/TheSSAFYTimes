package com.ssafy.ssafience.model.member;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class ModifyRequest {

	private String memberId;
	private String memberFirstName;
	private String memberLastName;
	private String memberPhone;
	private String memberAddress;

	private int memberRegion;
	private int memberClass;
	private int memberTrack;
	private int memberUnit;

	private String memberIntro;
	private String memberDesc;
	
	private List<Integer> interestedList;
	private List<Integer> skillList;
	
	public ModifyRequest() {
		this.interestedList = new ArrayList<Integer>();
		this.skillList = new ArrayList<Integer>();
	}

}
