package com.ssafy.ssafience.service.member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.dto.Member;
import com.ssafy.ssafience.model.dto.MemberResultDTO;
import com.ssafy.ssafience.model.member.LoginResult;
import com.ssafy.ssafience.model.member.MemberDetailResult;
import com.ssafy.ssafience.model.member.ModifyRepoRequest;
import com.ssafy.ssafience.model.member.ModifyRequest;
import com.ssafy.ssafience.model.member.SignInRequest;
import com.ssafy.ssafience.model.member.SignUpRequest;
import com.ssafy.ssafience.repo.CategoryRepo;
import com.ssafy.ssafience.repo.MemberRepo;

@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepo repo;
	
	@Autowired
	private CategoryRepo cRepo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Override
	public Member selectMemberOne(String memberId) throws Exception {
		return repo.selectMemberOne(memberId);
	}
	
	@Override
	public MemberDetailResult<Integer> selectMemberOneForModify(String memberId) throws Exception {
		MemberDetailResult<Integer> result = null;
		MemberResultDTO member = repo.selectMemberDetail(memberId);

		System.out.println(member);
		if (member != null) {
			result = new MemberDetailResult<Integer>(member);
			if (member.getMemberInterested()!=null) {
				List<String> interestedList = Arrays.asList(member.getMemberInterested().split(","));				
				List<Integer> ilist = new ArrayList<>();
				for (String str : interestedList) {
					ilist.add(Integer.parseInt(str));
				}
				result.insertIntList(ilist);
			}
			if (member.getMemberSkill()!=null) {
				List<String> skillList = Arrays.asList(member.getMemberSkill().split(","));
				List<Integer> slist = new ArrayList<>();				
				for (String str : skillList) {
					slist.add(Integer.parseInt(str));
				}
				result.insertSkillList(slist);
			}
			return result;
		} else {
			return result;
		}
	}
		
	@Override
	public MemberDetailResult<String> selectMemberOneDetail(String memberId) throws Exception {
		MemberDetailResult<String> result = null;
		MemberResultDTO member = repo.selectMemberDetail(memberId);
		System.out.println(member);
		if (member != null) {
			result = new MemberDetailResult<String>(member);
			System.out.println(result);
			if (member.getMemberInterested()!=null) {
				List<String> interestedList = Arrays.asList(member.getMemberInterested().split(","));				
				List<String> ilist = cRepo.getInterestedAndSkillList(interestedList);
				result.insertIntList(ilist);
			}
			if (member.getMemberSkill()!=null) {
				List<String> skillList = Arrays.asList(member.getMemberSkill().split(","));
				List<String> slist = cRepo.getInterestedAndSkillList(skillList);				
				result.insertSkillList(slist);
			}
			return result;
		} else {
			return result;
		}
	}
	
	@Override
	public LoginResult<Member> singIn(SignInRequest request) throws Exception {
		
		LoginResult<Member> result = new LoginResult<>();
		Member member = repo.selectMemberOneByMemberEmail(request.getMemberEmail());
		if (member != null) {
			result.setMember(true);
			if (encoder.matches(request.getMemberPw(), member.getMemberPw())) {
				result.setValid(true);
				result.setData(member);
			} else {
				result.setValid(false);
			}
		} else {
			result.setMember(false);
		}
		
		return result;
	}

	// 회원가입
	@Override
	public int insert(SignUpRequest request) throws Exception {
		System.out.println(request);
		String encodePw = encoder.encode(request.getMemberPw());
		Member member = Member.builder()
				.memberId(request.getMemberId())
				.memberPw(encodePw)
				.memberEmail(request.getMemberEmail())
				.build();
		
		return repo.insert(member);
	}

	@Override
	public int update(ModifyRequest request) throws Exception {
//		ObjectMapper mapper = new ObjectMapper();
//		ModifyRequest request = mapper.convertValue(obj, ModifyRequest.class);
//		
		System.out.println("MemberService : "+request);
		
		Member member = repo.selectMemberOne(request.getMemberId());
		if (member != null) {
			ModifyRepoRequest repoRequest = new ModifyRepoRequest(request);
			System.out.println("INTER : "+request.getInterestedList().size());
			System.out.println("SKILL : "+request.getSkillList().size());
			if (request.getInterestedList().size() > 0) {
				String memberInterested = "";	
				for (Integer no : request.getInterestedList()) {
					memberInterested+=no+",";
				}
				repoRequest.insertInterested(memberInterested.substring(0, memberInterested.length()-1));
			} else {
				repoRequest.insertInterested(null);
			}
			if (request.getSkillList().size() > 0) {
				String memberSkill = "";
				for (Integer no : request.getSkillList()) {
					memberSkill+=no+",";
				}
				repoRequest.insertSkill(memberSkill.substring(0, memberSkill.length()-1));
			} else {
				repoRequest.insertSkill(null);
			}
			System.out.println(repoRequest);
			return repo.update(repoRequest);
		} else return -1;	// 없는 회원
	}

	@Override
	public int delete(String memberId) throws Exception {
		Member member = repo.selectMemberOne(memberId);
		if (member != null) {
			return repo.delete(memberId);
		} else return -1;	// 없는 회원
	}

}
