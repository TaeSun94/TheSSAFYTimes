package com.ssafy.ssafience.service.like;

import java.util.List;

import com.ssafy.ssafience.model.dto.FreeBoardLike;
import com.ssafy.ssafience.model.like.LikeRequest;
import com.ssafy.ssafience.model.like.LikeResult;

public interface FreeLikeService {
	public LikeResult insertLike(LikeRequest request) throws Exception;
}
