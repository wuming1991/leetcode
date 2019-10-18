package com.wuming.vm.HotSwap;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.HotSwap
 * @Class HotSwapClassLoader
 * @Author: WuMing
 * @CreateDate: 2018/11/21 18:05
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class HotSwapClassLoader extends ClassLoader {
	
	public HotSwapClassLoader() {
		//使用双亲委派模型,指定HotSwapClassLoader的类加载器为父类加载器
		super(HotSwapClassLoader.class.getClassLoader());
	}
	
	public Class loadByte(byte[] classByte) {
		//将classbyte转换成Class文件
		return defineClass(null, classByte, 0, classByte.length);
	}
}
