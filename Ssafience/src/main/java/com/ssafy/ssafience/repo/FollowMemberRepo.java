package com.ssafy.ssafience.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

@Repository("FollowMemberRepo")
public class FollowMemberRepo {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object[]> findFollower(){
		String jpql = "SELECT m, f FROM Member memberId='admin'";
		Query query = em.createQuery(jpql);
		return query.getResultList();
		
	};
}