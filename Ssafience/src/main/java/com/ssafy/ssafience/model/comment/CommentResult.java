package com.ssafy.ssafience.model.comment;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResult<T> {
	private boolean isThere;
	private boolean isAuthor;
	private List<T> commentList;
	private T data;
}
