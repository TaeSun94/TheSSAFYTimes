package com.ssafy.ssafience.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SingleResponse<T> extends BasicResponse {
	private T data;
	private String Authorization;
}
