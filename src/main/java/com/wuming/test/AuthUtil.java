package com.wuming.test;

/**
 * @ProjectName: study
 * @Package: com.wuming.test
 * @Class AuthUtil
 * @Author: WuMing
 * @CreateDate: 2018/7/31 21:34
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class AuthUtil {
	
	static class Auth {
		
		private Long id;
		private String name;
		
		public Long getId() {
			
			return id;
		}
		
		public String getName() {
			return name;
		}
	}
	
	public static Auth getCurrentAuthInfo(long l, String s) {
		//Manager manager = RequestUtils.getCurrentLoginingManager(request);
		Auth auth = new Auth();
		auth.name = s;
		auth.id = l;
		return auth;
	}
}

