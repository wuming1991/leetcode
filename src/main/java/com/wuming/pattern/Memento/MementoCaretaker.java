package com.wuming.pattern.Memento;

import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Memento
 * @Class MementoCaretaker
 * @Author: WuMing
 * @CreateDate: 2018/7/18 14:33
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class MementoCaretaker {
	
	private ArrayList<ChessmanMemento> mementolist = new ArrayList();
	
	public ChessmanMemento getMemento(int i) {
		return mementolist.get(i);
	}
	
	public void setMemento(ChessmanMemento memento) {
		mementolist.add(memento);
	}
}
