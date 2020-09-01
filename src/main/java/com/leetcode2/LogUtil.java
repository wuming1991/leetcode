package com.leetcode2;

/**
 * @Author: WuMing
 * @CreateDate: 2020/7/15 10:48
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class LogUtil {
	public static String getMethodName(Object o){
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		StackTraceElement traceElement = stackTrace[2];
		return traceElement.getClassName()+"."+traceElement.getMethodName();
	}
}
