package com.ssafy.ssafience.service.follow;

import java.util.List;

import com.ssafy.ssafience.model.dto.FollowMember;
import com.ssafy.ssafience.model.follow.FollowWriteRequest;
import com.ssafy.ssafience.model.follow.UnFollowWriteRequest;

public interface FollowService {
	public int follow(FollowWriteRequest request) throws Exception;
	public int unfollow(UnFollowWriteRequest request) throws Exception;
	public List<FollowMember> getFollowerList(String memberId) throws Exception;
	public List<FollowMember> getFollowingList(String memberId) throws Exception;

}
