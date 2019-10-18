package com.wuming.test;

import com.wuming.test.AuthUtil.Auth;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @ProjectName: study
 * @Package: com.wuming.test
 * @Class test
 * @Author: WuMing
 * @CreateDate: 2018/7/31 21:34
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class test {
	
	public static void main(String[] args) {
		Auth currentAuthInfo = AuthUtil.getCurrentAuthInfo(1L, "111");
		System.out.println(currentAuthInfo.getId());
		System.out.println(currentAuthInfo.getName());
		currentAuthInfo = AuthUtil.getCurrentAuthInfo(2L, "1211");
		System.out.println(currentAuthInfo.getId());
		System.out.println(currentAuthInfo.getName());
		Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
		Set<Entry<Thread, StackTraceElement[]>> entries = allStackTraces.entrySet();
		int i = 0;
		for (Entry<Thread, StackTraceElement[]> entry : entries) {
			System.out.println("第" + i + "个");
			System.out.println(entry.getKey());
			for (StackTraceElement stackTraceElement : entry.getValue()) {
				System.out.println(stackTraceElement);
			}
			i++;
		}
		
	/*	String s="[[%F0%9F%99%88]]乌拉来来回来了正月[[%F0%9F%92%95]][[%F0%9F%8E%93]]";
		String s1 = EmojiUtil.emojiConverterToAlias(s);
		String s2 = EmojiUtil.emojiConverterUnicodeStr(s);
		System.out.println(s1);
		System.out.println(s2);*/
		String s = "pages/detail/detail?id=1019&width=760";
		String[] split = s.split("\\?");
		System.out.println(split[0]);
		System.out.println(split[1].split("&")[0]);
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				int c = 0;
				while (c < 5) {
					System.out
						.println(Thread.currentThread().getName() + System.currentTimeMillis());
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					c++;
				}
			}
		};
		Thread thread = new Thread(runnable);
		Thread thread1 = new Thread(runnable);
		thread.start();
		thread1.start();
	}
	
	
}
