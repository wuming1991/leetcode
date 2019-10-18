package com.wuming.vm.HotSwap;


import java.lang.reflect.Method;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.HotSwap
 * @Class JavaClassExecuter
 * @Author: WuMing
 * @CreateDate: 2018/11/21 18:50
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class JavaClassExecuter {
	
	public static String execute(byte[] classByte) {
		HackSystem.cleanBuffer();
		ClassModifier cm = new ClassModifier(classByte);
		byte[] modiBytes = cm
			.modifyUTF8Constant("java/lang/System", "com/wuming/vm/HotSwap/HackSystem");
		HotSwapClassLoader loader = new HotSwapClassLoader();
		Class clazz = loader.loadByte(modiBytes);
		try {
			Method method = clazz.getMethod("main", new Class[]{String[].class});
			method.invoke(null, new String[]{null});
		} catch (Throwable e) {
			e.printStackTrace(HackSystem.err);
		}
		return HackSystem.getBufferString();
	}
}
