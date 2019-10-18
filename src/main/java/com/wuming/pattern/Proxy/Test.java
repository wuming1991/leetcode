package com.wuming.pattern.Proxy;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Proxy
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/13 11:04
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		ProxyImage proxyImage = new ProxyImage("111");
		System.out.println(proxyImage.show());
	}
}
