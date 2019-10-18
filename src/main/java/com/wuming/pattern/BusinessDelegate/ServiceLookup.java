package com.wuming.pattern.BusinessDelegate;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.BusinessDelegate
 * @Class ServiceLookup
 * @Author: WuMing
 * @CreateDate: 2018/7/24 15:12
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ServiceLookup {
	
	public Service getService(String type) {
		if (type.equals("this")) {
			return new ThisService();
		} else if (type.equals("that")) {
			return new ThatService();
		}
		
		return null;
	}
}
