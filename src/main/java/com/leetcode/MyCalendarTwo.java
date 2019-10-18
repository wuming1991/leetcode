package com.leetcode;


/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class MyCalendarTwo
 * @Author: WuMing
 * @CreateDate: 2019/10/1 17:55
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class MyCalendarTwo {
	
	class Node {
		
		int start;
		int end;
		int count;
		Node next;
		
		public Node(int start, int end, int count) {
			this.start = start;
			this.end = end;
			this.count = count;
		}
	}
	
	Node head = null;
	
	public MyCalendarTwo() {
	
	}
	
	public boolean book(int start, int end) {
		if (head == null) {
			head = new Node(start, end, 1);
			return true;
		}
		Node before = null, first = head;
		
		while (first.end <= start) {
			if (first.next != null) {
				before = first;
				first = first.next;
			} else {
				break;
			}
		}
		if (first.end < start || check(first, end)) {
			insert(before, first, start, end);
			return true;
		}
		return false;
	}
	
	private void insert(Node before, Node cur, int start, int end) {
		if (end <= cur.start) {
			Node node = new Node(start, end, 1);
			node.next = cur;
			if (before == null) {
				head = node;
			} else {
				before.next = node;
			}
			return;
		}
		if (cur.end <= start) {
			if (cur.next == null) {
				cur.next = new Node(start, end, 1);
				return;
			} else {
				insert(cur, cur.next, start, end);
				return;
			}
		}
		if (start < cur.start) {
			Node node = new Node(start, cur.start, 1);
			node.next = cur;
			if (before == null) {
				head = node;
			} else {
				before.next = node;
			}
			insert(node, cur, cur.start, end);
			return;
		}
		if (start == cur.start) {
			if (end < cur.end) {
				Node node = new Node(end, cur.end, cur.count);
				node.next = cur.next;
				cur.next = node;
				cur.end = end;
				cur.count++;
				return;
			} else if (end == cur.end) {
				cur.count++;
				return;
			} else {
				insert(before, cur, cur.end, end);
				cur.count++;
				return;
			}
		}
		if (start > cur.start) {
			Node node = new Node(start, cur.end, cur.count);
			node.next = cur.next;
			cur.end = start;
			cur.next = node;
			insert(cur, node, start, end);
			return;
		}
	}
	
	private boolean check(Node cur, int end) {
		while (cur != null && cur.start < end) {
			if (cur.count == 2) {
				return false;
			}
			cur = cur.next;
		}
		return true;
	}
	
	public static void main(String[] args) {
		MyCalendarTwo calendarTwo = new MyCalendarTwo();
		calendarTwo.book(43, 44);
		calendarTwo.book(65, 66);
		calendarTwo.book(21, 22);
		calendarTwo.book(80, 81);
		calendarTwo.book(21, 22);
		calendarTwo.book(92, 93);
		calendarTwo.book(51, 52);
		calendarTwo.book(29, 30);
		calendarTwo.book(53, 54);
		calendarTwo.book(6, 7);
		calendarTwo.book(27, 28);
		calendarTwo.book(66, 67);
		calendarTwo.book(72, 73);
		calendarTwo.book(9, 10);
		calendarTwo.book(84, 85);
		calendarTwo.book(8, 9);
		calendarTwo.book(22, 23);
		calendarTwo.book(11, 12);
		calendarTwo.book(75, 76);
		calendarTwo.book(33, 34);
		calendarTwo.book(31, 32);
		calendarTwo.book(19, 20);
		calendarTwo.book(10, 11);
		calendarTwo.book(45, 46);
		calendarTwo.book(67, 68);
		calendarTwo.book(69, 70);
		calendarTwo.book(24, 25);
		calendarTwo.book(31, 32);
		calendarTwo.book(51, 52);
		calendarTwo.book(25, 26);
		calendarTwo.book(15, 16);
		calendarTwo.book(17, 18);
		calendarTwo.book(40, 41);
		calendarTwo.book(16, 17);
		calendarTwo.book(35, 36);
		calendarTwo.book(67, 68);
		calendarTwo.book(8, 9);
		calendarTwo.book(29, 30);
		calendarTwo.book(96, 97);
		calendarTwo.book(12, 13);
		calendarTwo.book(7, 8);
		calendarTwo.book(23, 24);
		calendarTwo.book(58, 59);
		calendarTwo.book(22, 23);
		calendarTwo.book(1, 2);
		calendarTwo.book(35, 36);
		calendarTwo.book(47, 48);
		calendarTwo.book(5, 6);
		calendarTwo.book(19, 20);
		calendarTwo.book(36, 37);
		calendarTwo.book(68, 69);
		calendarTwo.book(29, 30);
		calendarTwo.book(84, 85);
		calendarTwo.book(96, 97);
		calendarTwo.book(89, 90);
		calendarTwo.book(99, 100);
		calendarTwo.book(29, 30);
		calendarTwo.book(78, 79);
		calendarTwo.book(90, 91);
		calendarTwo.book(16, 17);
		calendarTwo.book(58, 59);
		calendarTwo.book(14, 15);
		calendarTwo.book(44, 45);
		calendarTwo.book(11, 12);
		calendarTwo.book(42, 43);
		calendarTwo.book(33, 34);
		calendarTwo.book(6, 7);
		calendarTwo.book(78, 79);
		calendarTwo.book(31, 32);
		calendarTwo.book(46, 47);
		calendarTwo.book(30, 31);
		calendarTwo.book(41, 42);
		calendarTwo.book(53, 54);
		calendarTwo.book(38, 39);
		calendarTwo.book(54, 55);
		calendarTwo.book(36, 37);
		calendarTwo.book(91, 92);
		calendarTwo.book(74, 75);
		calendarTwo.book(85, 86);
		calendarTwo.book(76, 77);
		calendarTwo.book(45, 46);
		calendarTwo.book(82, 83);
		calendarTwo.book(6, 7);
		calendarTwo.book(15, 16);
		calendarTwo.book(56, 57);
		calendarTwo.book(19, 20);
		calendarTwo.book(17, 18);
		calendarTwo.book(17, 18);
		calendarTwo.book(98, 99);
		calendarTwo.book(44, 45);
		calendarTwo.book(98, 99);
		calendarTwo.book(79, 80);
		calendarTwo.book(69, 70);
		calendarTwo.book(93, 94);
		calendarTwo.book(43, 44);
		calendarTwo.book(40, 41);
		calendarTwo.book(92, 93);
		calendarTwo.book(94, 95);
		calendarTwo.book(72, 73);
		calendarTwo.book(58, 59);
		calendarTwo.book(8, 9);
		calendarTwo.book(93, 94);
		calendarTwo.book(9, 10);
		calendarTwo.book(39, 40);
		calendarTwo.book(86, 87);
		calendarTwo.book(91, 92);
		calendarTwo.book(37, 38);
		calendarTwo.book(53, 54);
		calendarTwo.book(59, 60);
		calendarTwo.book(40, 41);
		calendarTwo.book(41, 42);
		calendarTwo.book(31, 32);
		calendarTwo.book(66, 67);
		calendarTwo.book(32, 33);
		calendarTwo.book(77, 78);
		calendarTwo.book(6, 7);
		calendarTwo.book(7, 8);
		calendarTwo.book(70, 71);
		calendarTwo.book(92, 93);
		calendarTwo.book(49, 50);
		calendarTwo.book(33, 34);
		calendarTwo.book(79, 80);
		calendarTwo.book(97, 98);
		calendarTwo.book(55, 56);
		calendarTwo.book(4, 5);
		calendarTwo.book(52, 53);
		calendarTwo.book(84, 85);
		calendarTwo.book(72, 73);
		calendarTwo.book(71, 72);
		calendarTwo.book(80, 81);
		calendarTwo.book(76, 77);
		calendarTwo.book(28, 29);
		calendarTwo.book(39, 40);
		calendarTwo.book(96, 97);
		calendarTwo.book(96, 97);
		calendarTwo.book(22, 23);
		calendarTwo.book(35, 36);
		calendarTwo.book(52, 53);
		calendarTwo.book(0, 1);
		calendarTwo.book(39, 40);
		calendarTwo.book(60, 61);
		calendarTwo.book(53, 54);
		calendarTwo.book(63, 64);
		calendarTwo.book(14, 15);
		calendarTwo.book(10, 11);
		calendarTwo.book(94, 95);
		calendarTwo.book(4, 5);
		calendarTwo.book(76, 77);
		calendarTwo.book(65, 66);
		calendarTwo.book(13, 14);
		calendarTwo.book(73, 74);
		calendarTwo.book(90, 91);
		calendarTwo.book(96, 97);
		calendarTwo.book(42, 43);
		calendarTwo.book(44, 45);
		calendarTwo.book(68, 69);
		calendarTwo.book(43, 44);
		calendarTwo.book(36, 37);
		calendarTwo.book(57, 58);
		calendarTwo.book(6, 7);
		calendarTwo.book(31, 32);
		calendarTwo.book(88, 89);
		calendarTwo.book(11, 12);
		calendarTwo.book(53, 54);
		calendarTwo.book(51, 52);
		calendarTwo.book(87, 88);
		calendarTwo.book(28, 29);
		calendarTwo.book(0, 1);
		calendarTwo.book(42, 43);
		calendarTwo.book(39, 40);
		calendarTwo.book(64, 65);
		calendarTwo.book(66, 67);
		calendarTwo.book(79, 80);
		calendarTwo.book(11, 12);
		calendarTwo.book(45, 46);
		calendarTwo.book(84, 85);
		calendarTwo.book(18, 19);
		calendarTwo.book(47, 48);
		calendarTwo.book(91, 92);
		calendarTwo.book(18, 19);
		calendarTwo.book(92, 93);
		calendarTwo.book(45, 46);
		calendarTwo.book(65, 66);
		calendarTwo.book(35, 36);
		calendarTwo.book(63, 64);
		calendarTwo.book(35, 36);
		calendarTwo.book(21, 22);
		calendarTwo.book(24, 25);
		calendarTwo.book(0, 1);
		calendarTwo.book(65, 66);
		calendarTwo.book(13, 14);
		calendarTwo.book(92, 93);
		calendarTwo.book(49, 50);
		calendarTwo.book(42, 43);
		calendarTwo.book(92, 93);
		calendarTwo.book(10, 11);
		calendarTwo.book(76, 77);
		calendarTwo.book(22, 23);
		calendarTwo.book(18, 19);
		calendarTwo.book(76, 77);
		calendarTwo.book(47, 48);
		calendarTwo.book(51, 52);
		calendarTwo.book(60, 61);
		calendarTwo.book(47, 48);
		calendarTwo.book(79, 80);
		calendarTwo.book(64, 65);
		calendarTwo.book(85, 86);
		calendarTwo.book(82, 83);
		calendarTwo.book(51, 52);
		calendarTwo.book(92, 93);
		calendarTwo.book(77, 78);
		calendarTwo.book(0, 1);
		calendarTwo.book(88, 89);
		calendarTwo.book(65, 66);
		calendarTwo.book(91, 92);
		calendarTwo.book(50, 51);
		calendarTwo.book(53, 54);
		calendarTwo.book(22, 23);
		calendarTwo.book(76, 77);
		calendarTwo.book(95, 96);
		calendarTwo.book(92, 93);
		calendarTwo.book(56, 57);
		calendarTwo.book(48, 49);
		calendarTwo.book(33, 34);
		calendarTwo.book(24, 25);
		calendarTwo.book(32, 33);
		calendarTwo.book(15, 16);
		calendarTwo.book(93, 94);
		calendarTwo.book(50, 51);
		calendarTwo.book(12, 13);
		calendarTwo.book(20, 21);
		calendarTwo.book(86, 87);
		calendarTwo.book(9, 10);
		calendarTwo.book(25, 26);
		calendarTwo.book(42, 43);
		calendarTwo.book(38, 39);
		calendarTwo.book(84, 85);
		calendarTwo.book(5, 6);
		calendarTwo.book(90, 91);
		calendarTwo.book(71, 72);
		calendarTwo.book(54, 55);
		calendarTwo.book(71, 72);
		calendarTwo.book(43, 44);
		calendarTwo.book(24, 25);
		calendarTwo.book(96, 97);
		calendarTwo.book(73, 74);
		calendarTwo.book(81, 82);
		calendarTwo.book(49, 50);
		calendarTwo.book(10, 11);
		calendarTwo.book(61, 62);
		calendarTwo.book(31, 32);
		calendarTwo.book(31, 32);
		calendarTwo.book(60, 61);
		calendarTwo.book(95, 96);
		calendarTwo.book(68, 69);
		calendarTwo.book(19, 20);
		calendarTwo.book(89, 90);
		calendarTwo.book(47, 48);
		calendarTwo.book(37, 38);
		calendarTwo.book(1, 2);
		calendarTwo.book(86, 87);
		calendarTwo.book(8, 9);
		calendarTwo.book(30, 31);
		calendarTwo.book(1, 2);
		calendarTwo.book(42, 43);
		calendarTwo.book(26, 27);
		calendarTwo.book(77, 78);
		calendarTwo.book(26, 27);
		calendarTwo.book(18, 19);
		calendarTwo.book(21, 22);
		calendarTwo.book(52, 53);
		calendarTwo.book(97, 98);
		calendarTwo.book(84, 85);
		calendarTwo.book(80, 81);
		calendarTwo.book(98, 99);
		calendarTwo.book(79, 80);
		calendarTwo.book(23, 24);
		calendarTwo.book(74, 75);
		calendarTwo.book(28, 29);
		calendarTwo.book(70, 71);
		calendarTwo.book(54, 55);
		calendarTwo.book(84, 85);
		calendarTwo.book(41, 42);
		calendarTwo.book(40, 41);
		calendarTwo.book(36, 37);
		calendarTwo.book(78, 79);
		calendarTwo.book(30, 31);
		calendarTwo.book(83, 84);
		calendarTwo.book(94, 95);
		calendarTwo.book(30, 31);
		calendarTwo.book(49, 50);
		calendarTwo.book(55, 56);
		calendarTwo.book(29, 30);
		calendarTwo.book(94, 95);
		calendarTwo.book(65, 66);
		calendarTwo.book(53, 54);
		calendarTwo.book(33, 34);
		calendarTwo.book(22, 23);
		calendarTwo.book(15, 16);
		calendarTwo.book(8, 9);
		calendarTwo.book(20, 21);
		calendarTwo.book(12, 13);
		calendarTwo.book(5, 6);
		calendarTwo.book(24, 25);
		calendarTwo.book(44, 45);
		calendarTwo.book(87, 88);
		calendarTwo.book(0, 1);
		calendarTwo.book(65, 66);
		calendarTwo.book(14, 15);
		calendarTwo.book(72, 73);
		calendarTwo.book(88, 89);
		calendarTwo.book(68, 69);
		calendarTwo.book(61, 62);
		calendarTwo.book(12, 13);
		calendarTwo.book(93, 94);
		calendarTwo.book(0, 1);
		calendarTwo.book(18, 19);
		calendarTwo.book(56, 57);
		calendarTwo.book(87, 88);
		calendarTwo.book(7, 8);
		calendarTwo.book(20, 21);
		calendarTwo.book(1, 2);
		calendarTwo.book(36, 37);
		calendarTwo.book(80, 81);
		calendarTwo.book(29, 30);
		calendarTwo.book(20, 21);
		calendarTwo.book(51, 52);
		calendarTwo.book(74, 75);
		calendarTwo.book(30, 31);
		calendarTwo.book(62, 63);
		calendarTwo.book(74, 75);
		calendarTwo.book(96, 97);
		calendarTwo.book(82, 83);
		calendarTwo.book(56, 57);
		calendarTwo.book(1, 2);
		calendarTwo.book(52, 53);
		calendarTwo.book(13, 14);
		calendarTwo.book(64, 65);
		calendarTwo.book(26, 27);
		calendarTwo.book(33, 34);
		calendarTwo.book(39, 40);
		calendarTwo.book(77, 78);
		calendarTwo.book(71, 72);
		calendarTwo.book(63, 64);
		calendarTwo.book(18, 19);
		calendarTwo.book(90, 91);
		calendarTwo.book(45, 46);
		calendarTwo.book(87, 88);
		calendarTwo.book(46, 47);
		calendarTwo.book(63, 64);
		calendarTwo.book(31, 32);
		calendarTwo.book(76, 77);
		calendarTwo.book(46, 47);
		calendarTwo.book(57, 58);
		calendarTwo.book(38, 39);
		calendarTwo.book(76, 77);
		calendarTwo.book(98, 99);
		calendarTwo.book(6, 7);
		calendarTwo.book(89, 90);
		calendarTwo.book(14, 15);
		calendarTwo.book(87, 88);
		calendarTwo.book(21, 22);
		calendarTwo.book(6, 7);
		calendarTwo.book(62, 63);
		calendarTwo.book(7, 8);
		calendarTwo.book(35, 36);
		calendarTwo.book(72, 73);
		calendarTwo.book(59, 60);
		calendarTwo.book(71, 72);
		calendarTwo.book(7, 8);
		calendarTwo.book(28, 29);
		calendarTwo.book(93, 94);
		calendarTwo.book(38, 39);
		calendarTwo.book(47, 48);
		calendarTwo.book(22, 23);
		calendarTwo.book(74, 75);
		calendarTwo.book(43, 44);
		calendarTwo.book(21, 22);
		calendarTwo.book(46, 47);
		calendarTwo.book(27, 28);
		calendarTwo.book(1, 2);
		calendarTwo.book(51, 52);
		calendarTwo.book(11, 12);
		calendarTwo.book(82, 83);
		calendarTwo.book(38, 39);
		calendarTwo.book(86, 87);
		calendarTwo.book(56, 57);
		calendarTwo.book(7, 8);
		calendarTwo.book(32, 33);
		calendarTwo.book(16, 17);
		calendarTwo.book(35, 36);
		calendarTwo.book(57, 58);
		calendarTwo.book(24, 25);
		calendarTwo.book(83, 84);
		calendarTwo.book(64, 65);
		calendarTwo.book(98, 99);
		calendarTwo.book(29, 30);
		calendarTwo.book(63, 64);
		calendarTwo.book(49, 50);
		calendarTwo.book(28, 29);
	}
}
