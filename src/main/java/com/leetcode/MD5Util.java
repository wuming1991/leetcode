package com.leetcode;

import java.security.MessageDigest;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class MD5Util
 * @Author: WuMing
 * @CreateDate: 2019/12/18 11:48
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class MD5Util {
	
	public MD5Util() {
	}
	
	public static String encoder(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());
			byte[] b = md.digest();
			StringBuffer buf = new StringBuffer("");
			
			for (int offset = 0; offset < b.length; ++offset) {
				int i = b[offset];
				if (i < 0) {
					i += 256;
				}
				if (i < 16) {
					buf.append("0");
				}
				buf.append(Integer.toHexString(i));
			}
			return buf.toString();
		} catch (Exception var6) {
			var6.printStackTrace();
			return null;
		}
	}
}
