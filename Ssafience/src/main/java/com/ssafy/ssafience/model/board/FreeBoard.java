package com.ssafy.ssafience.model.board;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
//@Table(name="free_board")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FreeBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Getter @Setter private int  freeBoardNo;
	@Getter @Setter private String memberId;
	@Getter @Setter private String freeBoardTitle;
	@Getter @Setter private String freeBoardContent;
	@Getter @Setter private int freeBoardHit;
	@Getter @Setter private Date freeBoardUpdateDatetime;
	@Getter @Setter private Date freeBoardDatetime;
	@Getter @Setter private int freeBoardLikeCount;

	@PrePersist
	private void onCreate() {
		this.freeBoardDatetime = new Date();
	}
	
//	@PostUpdate
//	protected void onUpdate() {
//		this.freeBoardUpdateDatetime = new Date();
//	}
	
}
