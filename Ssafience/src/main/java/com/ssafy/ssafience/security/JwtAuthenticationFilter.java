package com.ssafy.ssafience.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.ssafy.ssafience.util.JwtUtil;

import io.jsonwebtoken.Claims;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter{

	private JwtUtil jwtUtil;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
//		String token = request.getHeader("Authorization");
		Authentication authentication;
		try {
			authentication = getAuthentication(request);
//			 && jwtUtil.isValidToken(token)
			if (authentication != null) {
//				System.out.println("들어가니");
				SecurityContext context = SecurityContextHolder.getContext();
				context.setAuthentication(authentication);
			} else {
				System.out.println("꺄꺄꺄꺄꺄꺆꺄");
//				throw new AccessDeniedException("에러 발생");
			}
			
			
			chain.doFilter(request, response);
		} catch (Exception e) {
			System.out.println("EXCEPTION");
//			e.printStackTrace();
			throw new AccessDeniedException("인증되지 않은 사용자 입니다.");
		}
	}
	
	private Authentication getAuthentication(HttpServletRequest request) throws Exception {
		String token = request.getHeader("Authorization");
		System.out.println("token: "+token);
		if (token == null) return null;
		
		Claims claims = jwtUtil.getClaims(token.substring("Bearer ".length()));
		return new UsernamePasswordAuthenticationToken(claims, null);
	}
	

}
