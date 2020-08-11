package com.ssafy.ssafience.util;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;

public class ClientUtils {
	public static String getRemoteIP(HttpServletRequest request) {

		String ip = request.getHeader("X-FORWARDED-FOR");
		System.out.println("TEST : X-FORWARDED-FOR : " + ip);
		if (ip == null) {
			ip = request.getHeader("Proxy-Client-IP");
			System.out.println("TEST : Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			System.out.println("TEST : WL-Proxy-Client-IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			System.out.println("TEST : HTTP_CLIENT_IP : " + ip);
		}
		if (ip == null) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			System.out.println("TEST : HTTP_X_FORWARDED_FOR : " + ip);
		}
		if (ip == null) {
			ip = request.getRemoteAddr();
			System.out.println("RemoteAddr : "+ip);
			System.out.println("LocalAddr : "+request.getLocalAddr());
		}
		return ip;
	}

}
