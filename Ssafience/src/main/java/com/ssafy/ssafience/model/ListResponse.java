package com.ssafy.ssafience.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListResponse<T> extends BasicResponse{
	private List<T> list ;
	public ListResponse() {
		list = new ArrayList<>();
	}
}
