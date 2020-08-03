package com.ssafy.ssafience.model.follow;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.Assert;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name="member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class FollowMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private String memberId;
	@Getter @Setter private String memberEmail;
	@Getter @Setter private String memberLastName;
	@Getter @Setter private String memberFirstName;
	
	@Builder
	public FollowMember(String memberId, String memberEmail, String memberLastName, String memberFirstName) {
		Assert.hasText(memberId, "ID must not be empty!");
		Assert.hasText(memberEmail, "Email must not be empty!");
//		Assert.hasText(memberLastName, "LastName must not be empty!");
//		Assert.hasText(memberFirstName, "FirstName must not be empty!");
		this.memberId = memberId;
		this.memberEmail = memberEmail;
//		this.memberLastName = memberLastName;
//		this.memberFirstName = memberFirstName;
	}

}
