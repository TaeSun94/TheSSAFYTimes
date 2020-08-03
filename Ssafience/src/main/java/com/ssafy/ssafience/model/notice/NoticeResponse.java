package com.ssafy.ssafience.model.notice;

import java.util.List;

import com.ssafy.ssafience.model.BasicResponse;

import io.swagger.annotations.ApiModelProperty;

public class NoticeResponse extends BasicResponse {
	
	@ApiModelProperty(value = "list", position = 4)
    public List<Notice> noticeList;

	@ApiModelProperty(value = "one", position = 5)
    public Notice notice;
}
