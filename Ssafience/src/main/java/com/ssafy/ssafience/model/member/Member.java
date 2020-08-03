package com.ssafy.ssafience.model.member;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter 
	private int memberNo;
	@Getter @Setter private String memberId;
	@Getter @Setter private String memberPw;
	@Getter @Setter private String memberEmail;
	@Getter @Setter private String memberFirstName;
	@Getter @Setter private String memberLastName;
	@Getter @Setter private String memberPhone;
	
	@Getter @Setter private int memberRegion;
	@Getter @Setter private int memberClass;
	@Getter @Setter private int memberTrack;
	@Getter @Setter private int memberUnit;

	@Getter @Setter private String memberIntro;
	@Getter @Setter private String memberDesc;
	@Getter @Setter private String memberAuth;
	@Getter @Setter private boolean memberAuthStatus;
	@Getter @Setter private String memberAddress;

	@Getter @Setter 
	private Date memberDatetime;
	
	@Getter @Setter private Date memberAuthDatetime;
	
	@PrePersist
	private void onCreate() {
		this.memberRegion=0;
		this.memberClass=0;
		this.memberTrack=0;
		this.memberUnit=0;
		this.memberDatetime = new Date();
	}

}
