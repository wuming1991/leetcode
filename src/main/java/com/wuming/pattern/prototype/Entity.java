package com.wuming.pattern.prototype;

import java.util.Optional;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.prototype
 * @Class Entity
 * @Author: WuMing
 * @CreateDate: 2018/6/1 18:33
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Entity {
	
	private String str;
	
	public Optional<String> getStr() {
		return Optional.ofNullable(str);
	}
	
	public void setStr(String str) {
		this.str = str;
	}
	
	public Entity() {
	}
	
	public Entity(String str) {
		this.str = str;
	}
	
	@Override
	public String toString() {
		return "Entity{" +
			"str='" + str + '\'' +
			'}';
	}
}
