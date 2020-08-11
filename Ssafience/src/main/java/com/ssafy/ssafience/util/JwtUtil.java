package com.ssafy.ssafience.util;

import java.util.Base64.Encoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
//	Encoder encoder = Base64.getEncoder();
//	private Key key;
	private SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	private String base64Key ;
	
	public JwtUtil(String secret) {
//		this.key = Keys.hmacShaKeyFor(secret.getBytes());
		this.base64Key  = Encoders.BASE64.encode(key.getEncoded())	;
	}
	
	public String createToken(String memberId) {
		System.out.println(memberId);
		Map<String, Object> headers = new HashMap<String, Object>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");
		
		Map<String, Object> payloads = new HashMap<String, Object>();
		Long expiredTime = (long) (1000 * 601);
		Date now = new Date();
		now.setTime(now.getTime() + expiredTime);
		payloads.put("exp", now);
		payloads.put("data", memberId);
		String token = Jwts.builder()
				.setHeader(headers)
				.setClaims(payloads)
				.signWith(key)
//				.signWith(SignatureAlgorithm.HS256 ,base64Key)
				.compact();
		
		System.out.println(token);
		return token;
	}
	
	public void getTokenFromJwtString(String jwtTokenString) throws InterruptedException{
		Claims claims = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(jwtTokenString)
				.getBody();
		Date expiration = claims.get("exp", Date.class);
		System.out.println(expiration);
		
		String data = claims.get("data", String.class);
		System.out.println(data);
	}
	
	public Claims getClaims(String token) throws Exception{
		return Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token)
				.getBody();
	}
	
}
