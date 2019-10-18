package com.wuming.pattern.State;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.State
 * @Interface State
 * @Author: WuMing
 * @CreateDate: 2018/7/18 21:05
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface State {
	
	void click();
	
	void complete();
	
	void timeout();
}
