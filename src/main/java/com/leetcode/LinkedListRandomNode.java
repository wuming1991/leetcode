package com.leetcode;

import java.util.Random;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class LinkedListRandomNode
 * @Author: WuMing
 * @CreateDate: 2019/5/20 18:28
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class LinkedListRandomNode {
	ListNode head;
	int length;
	Random random;
	public LinkedListRandomNode(ListNode head) {
		this.head=head;
		length=1;
		random=new Random();
		while (head.next!=null){
			length++;
			head=head.next;
		}
		head.next=this.head;
		
	}
	public int getRandom() {
		int i = random.nextInt(length);
		while (i>0){
			head=head.next;
			i--;
		}
		return head.val;
	}
}
