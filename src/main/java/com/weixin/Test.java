package com.weixin;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ProjectName: study
 * @Package: com.weixin
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2019/1/10 16:40
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test {
	
	public static void main(String[] args) {
		new Test().tt();
	}
	
	public void tt(){
		System.out.println(Thread.currentThread().getStackTrace()[2].getMethodName());
	}
}
