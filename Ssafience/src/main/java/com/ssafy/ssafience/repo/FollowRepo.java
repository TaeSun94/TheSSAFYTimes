package com.ssafy.ssafience.repo;

import java.util.List;

import org.mapstruct.Mapper;

import com.ssafy.ssafience.model.dto.Follow;
import com.ssafy.ssafience.model.dto.FollowMember;
import com.ssafy.ssafience.model.follow.FollowWriteRequest;

@Mapper
public interface FollowRepo {
	public Follow isFollow(FollowWriteRequest request);
	public int follow(FollowWriteRequest request);
	public int unFollow(int followNo);
	public List<FollowMember> getFollowerList(String memberId);
	public List<FollowMember> getFollowingList(String memberId);
}
