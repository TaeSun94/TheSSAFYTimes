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
public class FreeComment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Getter @Setter private int freeCommentNo;
	@Getter @Setter private int freeBoardNo;
	@Getter @Setter private String memberId;
	@Getter @Setter private String freeCommentContent;
	@Getter @Setter private int freeCommentLike;
	@Getter @Setter private int freeCommentDislike;
	@Getter @Setter private int freeCommentDel;
	
	@PrePersist
	private void onCreate() {
		this.freeCommentDel = 0;
	}
}
