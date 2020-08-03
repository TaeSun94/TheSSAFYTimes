package com.ssafy.ssafience.model.Article;

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
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter 
	private int articleNo;
	@Getter @Setter private String memberId;
	@Getter @Setter private String articleTitle;
	@Getter @Setter	private String articleContent;
	@Getter @Setter	private int articleType;
	@Getter private Date articleDatetime;
	
	@PrePersist
	private void onCreate() {
		this.articleType=0;
		this.articleDatetime = new Date();
	}
	
}
