package com.wuming.pattern.Memento.ddd;

import com.wuming.pattern.Memento.Chessman;
import com.wuming.pattern.Memento.MementoCaretaker;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.Memento.ddd
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/18 15:27
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Chessman chessman = new Chessman("1", 1, 1);
		new MementoCaretaker().setMemento(chessman.save());
		
	}
}
