package com.ssafy.ssafience.model.notice;

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
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Notice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter private int noticeNo;
	@Getter @Setter private String noticeTitle;
	@Getter @Setter private String member_id;
	@Getter @Setter private Date noticeDatetime;
	@Getter @Setter private String noticeContent;

	@PrePersist
	private void onCreate() {

		this.noticeDatetime = new Date();

	}
}
