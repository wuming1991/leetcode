package com.wuming.vm;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm
 * @Class Excep
 * @Author: WuMing
 * @CreateDate: 2018/8/30 12:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Excep {
	
	public static int i = 1;
	
	public static void main(String[] args) {
		new Excep().exc();
	}
	
	public int exc() {
		int a;
		try {
			a = 1;
			return a;
		} catch (Exception e) {
			a = 2;
			return a;
		} finally {
			a = 3;
			return a;
		}
	}
}
