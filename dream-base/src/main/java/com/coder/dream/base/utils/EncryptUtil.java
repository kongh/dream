package com.coder.dream.base.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class EncryptUtil {

	/**
	 * 进行MD5加密编码
	 * 
	 * @param encoding
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encodeMD5String(String encoding) throws Exception{
		return encode(encoding,"MD5");
	}
	
	/**
	 * 进行SHA加密编码
	 * 
	 * @param encoding
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encodeSHAString(String encoding) throws Exception{
		return encode(encoding, "SHA");
	}
	
	/**
	 * 通过制定的方法进行相应的加密
	 * 
	 * @param encoding
	 * @param method
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	public static String encode(String encoding,String method) throws Exception{
		MessageDigest instance = MessageDigest.getInstance(method);
		instance.update(encoding.getBytes());
		return new BigInteger(1, instance.digest()).toString(16);
	}
}
