package com.ssafy.ssafience.service.like;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafience.model.dto.FreeBoard;
import com.ssafy.ssafience.model.like.LikeRequest;
import com.ssafy.ssafience.model.like.LikeResult;
import com.ssafy.ssafience.repo.FreeRepo;

@Service
public class FreeLikeServiceImpl implements FreeLikeService{

	@Autowired
	FreeRepo repo;
	
	@Override
	public LikeResult insertLike(LikeRequest request) throws Exception {
		System.out.println(request);
		LikeResult result = new LikeResult();
		FreeBoard board = repo.selectBoardOne(request.getBoardNo());
		if (board != null) {
			result.setThere(true);
			int insert = repo.insertLike(request);
			System.out.println("insert  : "+insert);
			if (insert == 0) {
				result.setIn(true);				
			} else {
				result.setIn(false);
			}
		} else {
			result.setThere(false);
		}
		return result;
	}

}
