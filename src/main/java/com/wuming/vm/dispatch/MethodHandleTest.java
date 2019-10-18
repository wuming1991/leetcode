package com.wuming.vm.dispatch;

import static java.lang.invoke.MethodHandles.lookup;

import com.wuming.SystemUtil;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import jdk.nashorn.internal.lookup.Lookup;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.dispatch
 * @Class MethodHandleTest
 * @Author: WuMing
 * @CreateDate: 2018/10/22 21:20
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MethodHandleTest {
	static class ClassA {
		public void println(String s) {
			System.out.println(s + "aaa");
		}
	}
	public static void main(String[] args) throws Throwable {
		for (int i = 0; i < 10; i++) {
			Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
			getPrintlnMH(obj).invokeExact("icyfenix");
		}
	}
	private static MethodHandle getPrintlnMH(Object reveiver) throws Exception {
		MethodType methodType = MethodType.methodType(void.class, String.class);
		return lookup().findVirtual(reveiver.getClass(), "println", methodType).bindTo(reveiver);
	}
}
