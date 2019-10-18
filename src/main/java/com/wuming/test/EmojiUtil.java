package com.wuming.test;

import com.github.binarywang.java.emoji.EmojiConverter;

/**
 * @ProjectName: study
 * @Package: com.wuming.test
 * @Class EmojiUtil
 * @Author: WuMing
 * @CreateDate: 2018/9/26 15:33
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class EmojiUtil {
	
	
	private static EmojiConverter emojiConverter = EmojiConverter.getInstance();
	
	/**
	 * 将emojiStr转为 带有表情的字符
	 */
	public static String emojiConverterUnicodeStr(String emojiStr) {
		String result = emojiConverter.toUnicode(emojiStr);
		return result;
	}
	
	/**
	 * 带有表情的字符串转换为编码
	 */
	public static String emojiConverterToAlias(String str) {
		String result = emojiConverter.toAlias(str);
		return result;
		
	}
}
