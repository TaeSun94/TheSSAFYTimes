package com.ssafy.ssafience.model;


import org.springframework.http.HttpStatus;

import io.swagger.annotations.ApiModelProperty;

public class BasicResponse {

	@ApiModelProperty(value = "status", position = 1)
    public HttpStatus status;
    @ApiModelProperty(value = "result", position = 2)
    public String result;
    @ApiModelProperty(value = "message", position = 3)
    public String message;

}