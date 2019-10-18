package com.wuming;

import com.wuming.pattern.prototype.Entity;
import java.util.Comparator;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.wuming
 * @Interface lambdaA
 * @Author: WuMing
 * @CreateDate: 2018/6/29 16:59
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public interface LambdaA {
	
	void sort(List<Entity> list, Comparator<Entity> comparator,boolean l);
}
