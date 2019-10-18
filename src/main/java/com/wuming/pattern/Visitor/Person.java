package com.wuming.pattern.Visitor;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Visitor
 * @Class Persion
 * @Author: WuMing
 * @CreateDate: 2018/7/19 18:57
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface Person {
	void accept(PersonVisitor visitor);
}
