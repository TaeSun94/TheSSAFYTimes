//package com.ssafy.ssafience.config;
//
//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//import org.jasypt.salt.StringFixedSaltGenerator;
//
//public class Jasypt {
//	public static void main(String[] args) {
//		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
//		encryptor.setPassword("mailPw");
//		encryptor.setAlgorithm("PBEWithMD5AndDES");
//		encryptor.setSaltGenerator(new StringFixedSaltGenerator("SsaFienCE"));
//		
//		String encryptText = encryptor.encrypt("gong0731-");
//		String plainText = encryptor.decrypt(encryptText);
//		
//		System.out.println("encryptText : " + encryptText); // 암호화된 값
//		System.out.println("plainText : " + plainText); // 복호화된 값 
//		
//	}
//}
