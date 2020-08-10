package com.ssafy.ssafience.service.like;

import com.ssafy.ssafience.model.like.LikeRequest;
import com.ssafy.ssafience.model.like.LikeResult;

public interface FreeLikeService {
	public LikeResult insertLike(LikeRequest request) throws Exception;
}
