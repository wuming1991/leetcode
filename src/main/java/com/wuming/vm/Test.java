package com.wuming.vm;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/8/15 16:51
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	// 内存占用，每一个OOMObject对象大约占用64K
	public byte[] placeholder = new byte[64 * 1024];
	
	public static void fillHeap(int num) throws InterruptedException {
		List<Test> list = new ArrayList<Test>();
		for (int i = 0; i < num; i++) {
			// 稍作延迟让内存变化曲线更加明显
			Thread.sleep(50);
			list.add(new Test());
		}
		System.gc();
	}
	
	public static void main(String[] args) throws Exception {
		Thread.sleep(5000);
		fillHeap(800);
		/*String s="%E6%98%9F%E6%B2%B3%E5%93%88%E5%93%88%F0%9F%98%84";
		String decode = URLDecoder.decode(s);
		System.out.println(decode);
		String s1 = decode.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", "");
		System.out.println(s1);*/
		
	}
	
}
