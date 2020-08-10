package com.ssafy.ssafience.util;

import javax.servlet.http.HttpServletRequest;

public class ClientUtils {
	public static String getRemoteIP(HttpServletRequest req) {
		String ip = req.getHeader("X-FORWARDED-FOR");
		
		// proxy 환경
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		
		// 웹 로직 서버
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		
		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("HTTP_CLIENT_IP");
		}

		if (ip == null || ip.length() == 0) {
			ip = req.getHeader("HTTP_X_FORWARDED_FOR");
		}

		if (ip == null || ip.length() == 0) {
			ip = req.getRemoteAddr();
		}
		System.out.println(ip);
		return ip;
		
	}
}
