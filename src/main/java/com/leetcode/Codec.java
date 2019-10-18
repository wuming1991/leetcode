package com.leetcode;

import java.util.HashMap;
import java.util.UUID;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Codec
 * @Author: WuMing
 * @CreateDate: 2019/4/24 18:44
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Codec {
	
	HashMap<String, String> map = new HashMap<>();
	String perfix="http://tinyurl.com/";
	// Encodes a URL to a shortened URL.
	public String encode(String longUrl) {
		String key = UUID.randomUUID().toString().substring(0, 6);
		while (map.containsKey(key)){
			key = UUID.randomUUID().toString().substring(0, 6);
		}
		map.put(key,longUrl);
		return perfix+key;
	}
	
	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl) {
		String key = shortUrl.replace(perfix, "");
		return map.get(key);
	}
}
