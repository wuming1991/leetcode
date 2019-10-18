package com.wuming.pattern.Memento;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Memento
 * @Class Chessman
 * @Author: WuMing
 * @CreateDate: 2018/7/18 14:25
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Chessman {
	
	private String label;
	private int x;
	private int y;
	
	public ChessmanMemento save() {
		return new ChessmanMemento(label, x, y);
	}
	
	public void restore(ChessmanMemento memento) {
		label = memento.getLabel();
		x = memento.getX();
		y = memento.getY();
	}
	
	@Override
	public String toString() {
		return this.label + "当前位置为(" + this.getX() + "," + this.getY() + ")";
	}
	
	public Chessman(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
}

/**
 * 备忘录类由于该类为default所以只有当前包下的类可以操作该类,
 */
class ChessmanMemento {
	
	private String label;
	private int x;
	private int y;
	
	public ChessmanMemento(String label, int x, int y) {
		this.label = label;
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public String getLabel() {
		
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
}