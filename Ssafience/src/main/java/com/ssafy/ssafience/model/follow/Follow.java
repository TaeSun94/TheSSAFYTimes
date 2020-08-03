package com.ssafy.ssafience.model.follow;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.ssafience.model.member.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter private int followNo;
	
	@Getter @Setter	
	private String memberIdFrom;

	@Getter @Setter	
	private String memberIdTo;
	
	@Getter @Setter private Date followDatetime;
	
	@PrePersist
	private void onCreate() {
		this.followDatetime = new Date();
	}
	
}
