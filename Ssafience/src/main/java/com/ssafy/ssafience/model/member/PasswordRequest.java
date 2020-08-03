package com.ssafy.ssafience.model.member;

import io.swagger.annotations.ApiModelProperty;

public class PasswordRequest {

	@ApiModelProperty(value = "id", position = 1)
    public String memberid;

	@ApiModelProperty(value = "old", position = 2)
    public String oldMemberPw;

	@ApiModelProperty(value = "new", position = 3)
    public String newMemberPw;

}
