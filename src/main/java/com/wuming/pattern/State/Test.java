package com.wuming.pattern.State;

import java.util.Scanner;

/**
 * @ProjectName: study
 * @Package: com.wuming.pattern.State
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2018/7/18 21:30
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Test {
	
	public static void main(String[] args) {
		Door door = new Door();
		door.setState(new Closed(door));
		System.out.println("door is " + door.state.getClass().getSimpleName());
		Scanner in = new Scanner(System.in);
		while (true) {
			System.out.println("输入1:timeout,2:click,3:complete,控制门");
			int i = in.nextInt();
			switch (i) {
				case 1:
					door.timeout();
					break;
				case 2:
					door.click();
					break;
				case 3:
					door.complete();
					break;
				default:
					System.out.println("error input");
			}
		}
	}
}
