package com.wuming.vm;

import com.wuming.pattern.BusinessDelegate.ThatService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm
 * @Class DynamicProxyTest
 * @Author: WuMing
 * @CreateDate: 2018/11/21 16:13
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class DynamicProxyTest {
	interface IHello {
		void sayHello();
	}
	static class Hello implements IHello {
		@Override
		public void sayHello() {
			System.out.println("hello world");
		}
	}
	static class DynamicProxy implements InvocationHandler {
		Object obj;
		Object bind(Object obj) {
			this.obj = obj;
			return Proxy
				.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
					this);
		}
		@Override
		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("welcome");
			return method.invoke(obj, args);
		}
	}
	public static void main(String[] args) {
		IHello iHello = (IHello) new DynamicProxy().bind(new Hello());
		iHello.sayHello();
	}
}
