package com.wuming.vm.dispatch;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.invoke.MethodType;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.dispatch
 * @Class DynamicDispatchTest
 * @Author: WuMing
 * @CreateDate: 2018/10/26 17:36
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class DynamicDispatchTest {
	
	class GrandFather {
		
		void thinking() {
			System.out.println("GrandFather" + "thinking");
		}
	}
	
	class Father extends GrandFather {
		
		@Override
		void thinking() {
			System.out.println("Father" + "thinking");
		}
	}
	
	class Son extends Father {
		
		@Override
		void thinking() {
			try {
				MethodType methodType = MethodType.methodType(void.class);
				MethodHandle thinking = lookup()
					.findSpecial(GrandFather.class, "thinking", methodType, getClass());
				thinking.invoke(this);
			} catch (Throwable e) {
			}
		}
	}
	
	public static void main(String[] args) {
		(new DynamicDispatchTest().new Son()).thinking();
	}
}
