package com.ssafy.ssafience.service;

import java.util.List;

import com.ssafy.ssafience.model.follow.Follow;
import com.ssafy.ssafience.model.follow.FollowMember;

public interface FollowService {
	public List<FollowMember> getMyFollower(String memberId);	// 나를 팔로워하는 사람들 이름, 이메일, 아이디
	public List<FollowMember> getMyFollowing(String memberId);	// 내가 팔로잉하는 사람들
	public boolean insertFollow(Follow f) throws Exception;
	public boolean deleteFollow(int followNo) throws Exception;

	public List<FollowMember> test();
}