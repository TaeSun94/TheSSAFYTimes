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
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
//@Table(name="program_board")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProgramBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Getter @Setter private int programBoardNo;
	@Getter @Setter private String programBoardTitle;
	@Getter @Setter private String memberId;
	@Getter @Setter private Date programBoardDatetime;
	@Getter @Setter private String programBoardTrack;
	@Getter @Setter private String programBoardContent;
	@Getter @Setter private int programBoardHit;
	@Getter @Setter private Date programBoardUpdateDatetime;
	
	@PrePersist
	private void onCreate() {
		this.programBoardDatetime = new Date();
	}
	
//	@PostUpdate
//	protected void onUpdate() {
//		this.programBoardUpdateDatetime = new Date();
//	}
	
}
