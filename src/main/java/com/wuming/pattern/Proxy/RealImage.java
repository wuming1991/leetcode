package com.wuming.pattern.Proxy;

import static java.lang.Thread.sleep;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Proxy
 * @Class RealImage
 * @Author: WuMing
 * @CreateDate: 2018/7/13 10:55
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class RealImage implements Image {
	
	String url;
	String date;
	
	@Override
	public String show() {
		return date;
	}
	
	public RealImage(String url) {
		this.url = url;
	}
	
	public void imageLoader() {
		try {
			System.out.println("图片开始加载");
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("图片加载完成");
		date = "图片内容替换占位符";
	}
}
