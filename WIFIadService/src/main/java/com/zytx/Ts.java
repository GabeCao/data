package com.zytx;

import org.apache.shiro.crypto.hash.SimpleHash;

public class Ts {

	public static void main(String[] args) {
		String source = "123456";
		String salt = "shiro";
		int hashIterations = 3;
		SimpleHash re = new SimpleHash("md5", source, salt, hashIterations);
		System.out.println(re);
	}
}
