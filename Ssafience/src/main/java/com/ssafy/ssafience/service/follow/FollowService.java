package com.ssafy.ssafience.service.follow;

import java.util.List;

import com.ssafy.ssafience.model.dto.FollowMember;
import com.ssafy.ssafience.model.follow.FollowWriteRequest;

public interface FollowService {
	public int follow(FollowWriteRequest request) throws Exception;
	public int unfollow(int followNo) throws Exception;
	public List<FollowMember> getFollowerList(String memberId) throws Exception;
	public List<FollowMember> getFollowingList(String memberId) throws Exception;

}
