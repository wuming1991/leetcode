package com.wuming.pattern.Memento;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Memento
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/18 14:36
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	private static int index = -1; //定义一个索引来记录当前状态所在位置
	private static MementoCaretaker mc = new MementoCaretaker();
	
	public static void main(String[] args) {
		Chessman soldier = new Chessman("兵", 1, 1);
		play(soldier);
		soldier.setX(2);
		play(soldier);
		soldier.setY(2);
		play(soldier);
		undo(soldier);
		undo(soldier);
		redo(soldier);
		System.out.println(soldier);
	}
	
	private static void redo(Chessman soldier) {
		System.out.println("撤销悔棋");
		soldier.restore(mc.getMemento(++index));
		System.out.println(soldier);
	}
	
	private static void undo(Chessman soldier) {
		System.out.println("悔棋");
		soldier.restore(mc.getMemento(--index));
		System.out.println(soldier);
	}
	
	private static void play(Chessman soldier) {
		mc.setMemento(soldier.save());
		index++;
		System.out.println(soldier);
	}
	
}
