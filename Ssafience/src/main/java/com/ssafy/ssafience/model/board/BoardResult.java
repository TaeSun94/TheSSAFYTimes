package com.ssafy.ssafience.model.board;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardResult<T> {
	private boolean isAuthor;
	private List<T> boardList;
	
	public BoardResult() {
		this.boardList = new ArrayList<T>();
	}
}
