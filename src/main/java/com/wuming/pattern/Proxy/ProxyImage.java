package com.wuming.pattern.Proxy;

import java.text.RuleBasedCollator;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Proxy
 * @Class ProxyImage
 * @Author: WuMing
 * @CreateDate: 2018/7/13 10:55
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ProxyImage implements Image {
	
	String url;
	RealImage real;
	
	public ProxyImage(String url) {
		this.url = url;
	}
	
	@Override
	public String show() {
		System.out.println("这里是图片占位符");
		if (2 < url.length()) {
			real = new RealImage(url);
			real.imageLoader();
			return real.show();
		} else {
			return "您没有权限查看图片";
		}
	}
}
