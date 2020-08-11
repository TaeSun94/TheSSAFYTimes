package com.ssafy.ssafience.model.hit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HitRequest {
	private int boardNo;
	private String ipAddress;
	
	@Builder
	public HitRequest(int boardNo, String ipAddress) {
		this.boardNo = boardNo;
		this.ipAddress = ipAddress;
	}
}
