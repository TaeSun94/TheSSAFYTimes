package com.ssafy.ssafience.model.board;

import java.util.List;

import com.ssafy.ssafience.model.BasicResponse;

import io.swagger.annotations.ApiModelProperty;

public class ProgramBoardResponse extends BasicResponse{

	@ApiModelProperty(value = "list" , position = 4)
	public List<ProgramBoard> programBoardList;	

	@ApiModelProperty(value = "one" , position = 5)
	public ProgramBoard programBoard;	

}
