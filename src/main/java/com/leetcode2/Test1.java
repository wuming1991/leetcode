package com.leetcode2;

import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class Test1
 * @Author: WuMing
 * @CreateDate: 2020/1/16 11:07
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test1 {
	
	public static void main(String[] args) {
		Test1 test = new Test1();
		test.parseBoolExpr("|(&(t,!f,t),!(t))");
	}
	
	//1163
	public String lastSubstring(String s) {
		int begin = 0;
		int len = s.length();
		for (int i = 1; i < len; i++) {
			for (int j = 0; j + i < len; j++) {
				if (s.charAt(i + j) > s.charAt(begin + j)) {
					begin = i;
					break;
				} else if (s.charAt(i + j) < s.charAt(begin + j)) {
					break;
				}
			}
		}
		return s.substring(begin);
	}
	
	//1106
	char[] exp;
	
	public boolean parseBoolExpr(String expression) {
		exp = expression.toCharArray();
		return parseBoolExprHelper(0, exp.length - 1);
	}
	
	private boolean parseBoolExprHelper(int l, int r) {
		if (exp[l] == '(') {
			l++;
			r--;
		}
		char c = exp[l];
		if (l == r) {
			return c == 't';
		}
		if (c == '!') {
			l++;
			return !parseBoolExprHelper(l, r);
		}
		int count = 0;
		int i = l + 2;
		boolean ret = true;
		if (c == '&') {
			l+=2;
			while (i <= r) {
				if (exp[i] == '(') {
					count++;
				} else if ((exp[i] == ',' && count == 0) || i == r) {
					ret = ret && parseBoolExprHelper(l, i - 1);
					if (!ret) {
						break;
					}
					l = i + 1;
				}else if (exp[i] == ')') {
					count--;
				}
				i++;
				
			}
		} else {
			l+=2;
			ret = false;
			while (i <= r) {
				if (exp[i] == '(') {
					count++;
				}else if ((exp[i] == ',' && count == 0) || i == r) {
					ret = ret || parseBoolExprHelper(l, i - 1);
					if (ret) {
						break;
					}
					l = i + 1;
				} else if (exp[i] == ')') {
					count--;
				}
				i++;
			}
		}
		return ret;
	}
}
