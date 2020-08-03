package com.ssafy.ssafience.model.comment;

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
//@Table(name="free_board")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProgramComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	
	@Getter @Setter private int programCommentNo;
	@Getter @Setter private int programBoardNo;
	@Getter @Setter private String memberId;
	@Getter @Setter private String programCommentContent;
	@Getter @Setter private int programCommentLike;
	@Getter @Setter private int programCommentDislike;
	@Getter @Setter private int programCommentDel;
	
	@PrePersist
	private void onCreate() {
		this.programCommentDel = 0;
	}

}
