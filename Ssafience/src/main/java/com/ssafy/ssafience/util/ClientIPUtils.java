package com.ssafy.ssafience.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.stereotype.Component;

@Component
public class ClientIPUtils {
	public static String getLocalHostAddress() throws UnknownHostException {

		InetAddress ia = null;
		ia = InetAddress.getLocalHost(); // 내 pc의 host를 얻어온다
//		System.out.println("로컬호스트:" + ia.getHostAddress());
		return ia.getHostAddress();

	}
}
