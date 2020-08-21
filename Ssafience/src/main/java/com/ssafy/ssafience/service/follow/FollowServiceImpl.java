package com.ssafy.ssafience.service.follow;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.dto.Follow;
import com.ssafy.ssafience.model.dto.FollowMember;
import com.ssafy.ssafience.model.follow.FollowWriteRequest;
import com.ssafy.ssafience.model.follow.UnFollowWriteRequest;
import com.ssafy.ssafience.repo.FollowRepo;
import com.ssafy.ssafience.repo.MemberRepo;

@Service
public class FollowServiceImpl implements FollowService{

	@Autowired
	FollowRepo repo;
	
	@Autowired
	MemberRepo mRepo;
	
	@Override
	public int follow(FollowWriteRequest request) throws Exception {
		Follow isFollow = repo.isFollow(request);
		System.out.println(request);
		System.out.println(isFollow);
		if (isFollow != null) return -1;	// 이미 팔로우
		else return repo.follow(request);		
	}

	@Override
	public int unfollow(UnFollowWriteRequest request) throws Exception {
		System.out.println(request);
		int delete = repo.unFollow(request);
		System.out.println(delete);
		return delete;
	}

	@Override
	public List<FollowMember> getFollowerList(String memberId) throws Exception {		
		return repo.getFollowerList(memberId);
	}

	@Override
	public List<FollowMember> getFollowingList(String memberId) throws Exception {
		return repo.getFollowingList(memberId);
	}

}
