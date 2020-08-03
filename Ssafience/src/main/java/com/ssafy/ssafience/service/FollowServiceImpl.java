package com.ssafy.ssafience.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.follow.Follow;
import com.ssafy.ssafience.model.follow.FollowMember;
import com.ssafy.ssafience.model.follow.FollowRequest;
import com.ssafy.ssafience.model.member.Member;
import com.ssafy.ssafience.repo.FollowMemberRepo;
import com.ssafy.ssafience.repo.FollowRepo;
import com.ssafy.ssafience.repo.MemberRepo;

@Service("FollowServiceImpl")
public class FollowServiceImpl implements FollowService{
	
	@Autowired
	@Qualifier("FollowJPARepo")
	FollowRepo fRepo;

	@Autowired
	@Qualifier("FollowMemberRepo")
	FollowMemberRepo mRepo;

	@Autowired
	@Qualifier("MemberJPARepo")
	MemberRepo memRepo;

	@Override
	public boolean insertFollow(Follow f) throws Exception{
		Optional<Member> fromMember = memRepo.findByMemberId(f.getMemberIdFrom());
		Optional<Member> toMember = memRepo.findByMemberId(f.getMemberIdTo());
		if (fromMember.isPresent() && toMember.isPresent()) {
			System.out.println(fromMember.get());
			System.out.println(toMember.get());
			Optional<Follow> followOpt = fRepo.findByMemberIdFromAndMemberIdTo(fromMember.get().getMemberId(), toMember.get().getMemberId());
			if (followOpt.isPresent()) return false;
			else {
				fRepo.save(f);
				return true;
			}			
		} else return false;
		
	}

	@Override
	public boolean deleteFollow(int followNo) throws Exception {
		Optional<Follow> followOpt = fRepo.findById(followNo);
		if (followOpt.isPresent()) {
			fRepo.delete(followOpt.get());
//			System.out.println("삭제");
			return true;
		} else return false;
	}

	@Override
	public List<FollowMember> getMyFollower(String memberId) {
		return null;
	}

	@Override
	public List<FollowMember> getMyFollowing(String memberId) {
		return null;
	}

	public List<FollowMember> test(){
		List<Object[]> tmpList = mRepo.findFollower();
		
		List<FollowMember> fmList = new ArrayList<FollowMember>();
		for (Object[] obj : tmpList) {
			fmList.add(FollowMember.builder()
					.memberId((String)obj[0])
					.memberEmail((String)obj[1])
//					.memberLastName((String)obj[2])
//					.memberFirstName((String)obj[3])
					.build()
					);
						
		}
		return fmList;
	}
}