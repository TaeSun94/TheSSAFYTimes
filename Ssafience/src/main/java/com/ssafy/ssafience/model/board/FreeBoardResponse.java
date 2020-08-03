package com.ssafy.ssafience.model.board;

import java.util.List;

import com.ssafy.ssafience.model.BasicResponse;

import io.swagger.annotations.ApiModelProperty;

public class FreeBoardResponse extends BasicResponse{

	@ApiModelProperty(value = "list" , position = 4)
	public List<FreeBoard> freeBoardList;	

	@ApiModelProperty(value = "one" , position = 5)
	public FreeBoard freeBoard;	

}
