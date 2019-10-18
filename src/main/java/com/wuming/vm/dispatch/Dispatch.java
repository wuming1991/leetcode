package com.wuming.vm.dispatch;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.dispatch
 * @Class Dispathc
 * @Author: WuMing
 * @CreateDate: 2018/10/20 13:56
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Dispatch {
	
	static class QQ {}
	
	static class WeiXin {}
	
	public static class Father {
		public void choice(QQ arg) {
			System.out.println("father choice qq");
		}
		public void choice(WeiXin arg) {
			System.out.println("father choice WeiXin");
		}
	}
	
	public static class Son extends Father {
		@Override
		public void choice(QQ arg) {
			System.out.println("Son choice qq");
		}
		@Override
		public void choice(WeiXin arg) {
			System.out.println("Son choice WeiXin");
		}
	}
	public static void main(String[] args) {
		Father father = new Father();
		Son son = new Son();
		father.choice(new QQ());
		son.choice(new WeiXin());
	}
}
