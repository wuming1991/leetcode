package com.wuming.vm.dispatch;

import static java.lang.invoke.MethodHandles.lookup;

import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import sun.nio.cs.ext.MS950_HKSCS_XP;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.dispatch
 * @Class InvokeDynamicTest
 * @Author: WuMing
 * @CreateDate: 2018/10/23 20:27
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class InvokeDynamicTest {
	public static void main(String[] args) throws Throwable {
		INDY_BootstrapMethod().invokeExact("wuming");
	}
	public static void testMethod(String s) {
		System.out.println("hello string " + s);
	}
	public static CallSite BootstrapMethod(MethodHandles.Lookup lookup, String name,
		MethodType methodType)
		throws Exception {
		return new ConstantCallSite(lookup.findStatic(InvokeDynamicTest.class, name, methodType));
	}
	private static MethodType MT_BootstrapMethod() {
		return MethodType.fromMethodDescriptorString(
			"(Ljava/lang/invoke/MethodHandles$Lookup;Ljava/lang/String;Ljava/lang/invoke/MethodType;)Ljava/lang/invoke/CallSite;",
			null);
	}
	private static MethodHandle MH_BootstrapMethod() throws Exception {
		return lookup()
			.findStatic(InvokeDynamicTest.class, "BootstrapMethod", MT_BootstrapMethod());
	}
	private static MethodHandle INDY_BootstrapMethod() throws Throwable {
		CallSite callSite = (CallSite) MH_BootstrapMethod()
			.invokeWithArguments(lookup(), "testMethod",
				MethodType.fromMethodDescriptorString("(Ljava/lang/String;)V", null));
		return callSite.dynamicInvoker();
	}
}
