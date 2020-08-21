package com.ssafy.ssafience.repo;

import org.mapstruct.Mapper;

@Mapper
public interface ValidRepo {
	public String getId(String memberId);
	public String getEmail(String memberEmail);
}
