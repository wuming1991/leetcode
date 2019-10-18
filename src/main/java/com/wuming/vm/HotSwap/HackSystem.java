package com.wuming.vm.HotSwap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

/**
 * @ProjectName: study
 * @Package: com.wuming.vm.HotSwap
 * @Class HackSystem
 * @Author: WuMing
 * @CreateDate: 2018/11/21 18:40
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class HackSystem {
	
	public final static InputStream in = System.in;
	private static ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	
	public final static PrintStream out = new PrintStream(buffer);
	public final static PrintStream err = out;
	
	public static String getBufferString() {
		return buffer.toString();
	}
	
	public static void cleanBuffer() {
		buffer.reset();
	}
	
	public static void setSecurityManager(final SecurityManager s) {
		System.setSecurityManager(s);
	}
	
	public static SecurityManager getSecurityManager() {
		return System.getSecurityManager();
	}
	
	public static long currentTimeMillis() {
		return System.currentTimeMillis();
	}
	
	public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
		System.arraycopy(src, srcPos, dest, destPos, length);
	}
	
	public static int incentityHashCode(Object x) {
		return System.identityHashCode(x);
	}
}
