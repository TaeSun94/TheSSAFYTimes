package com.ssafy.ssafience.model.comment;

import java.util.List;

import com.ssafy.ssafience.model.BasicResponse;

import io.swagger.annotations.ApiModelProperty;

public class FreeCommentResponse extends BasicResponse{

	@ApiModelProperty(value = "list" , position = 4)
	public List<FreeComment> freeCommentList;
	
	@ApiModelProperty(value = "one" , position = 5)
	public FreeComment comment;	

}
