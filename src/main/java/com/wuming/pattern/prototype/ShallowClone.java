package com.wuming.pattern.prototype;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern
 * @Class ShallowClone
 * @Author: WuMing
 * @CreateDate: 2018/6/1 18:24
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class ShallowClone {
	
	private int num;
	private Entity entity;
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public Entity getEntity() {
		return entity;
	}
	
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	public ShallowClone(int num, Entity entity) {
		this.num = num;
		this.entity = entity;
	}
	
	public ShallowClone() {
	}
	
	
}
