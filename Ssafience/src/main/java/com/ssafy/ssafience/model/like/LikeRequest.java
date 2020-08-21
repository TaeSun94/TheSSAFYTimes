package com.ssafy.ssafience.model.like;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class LikeRequest {
	private int boardNo;
	private String memberId;
	private int boardLikeCheck;	// 1,true, 좋아요  | 0,false, 싫어요
}
