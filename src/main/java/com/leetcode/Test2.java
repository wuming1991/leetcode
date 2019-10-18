package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test2
 * @Author: WuMing
 * @CreateDate: 2019/4/15 16:18
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */

public class Test2 {
	
	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(2);
		treeNode.left = new TreeNode(1);
		//treeNode.right = new TreeNode(-3);
		//treeNode.right.right = new TreeNode(11);
		treeNode.left.right = new TreeNode(2);
		treeNode.left.right.left = new TreeNode(1);
		treeNode.left.right.left.right = new TreeNode(2);
		//findMode(treeNode);
		//treeNode.left.right.right = new TreeNode(1);
		//treeNode.left.left = new TreeNode(3);
		//treeNode.left.left.left = new TreeNode(3);
		//treeNode.left.left.right = new TreeNode(-2);
		//pathSum(treeNode, 8);*/
		//	String[] split = "Bob hit a ball, the hit BALL flew far after it was hit.".split(" ");
		//pivotIndex(new int[]{-1, -1, -1, -1, -1, 0});
		//maxDistToClosest(new int[]{1,1,1,0,0,1,1,1,1,0});
		//licenseKeyFormatting("2-4A0r7-4k", 4);
		//dominantIndex(new int[]{1, 0});
		//countAndSay(4);
		//powerfulIntegers(2, 3, 10);
		//findMaxAverage(new int[]{9, 7, 3, 5, 6, 2, 0, 8, 1, 9}, 6);
		//arrangeCoins(1804289383);
		/*ListNode listNode = new ListNode(1);
		listNode.next = new ListNode(2);
		listNode.next.next = new ListNode(1);
		isPalindrome(listNode);*/
		//merge(new int[]{4, 5, 6, 0, 0, 0}, 3, new int[]{1, 2, 3}, 3);
		//	numMagicSquaresInside(new int[][]{{4, 7, 8}, {9, 5, 1}, {2, 3, 6}});
		largestTimeFromDigits(new int[]{1,9,6,0});
	}
	
	public static String largestTimeFromDigits(int[] A) {
		
		int hour = 23;
		while (hour >= 0) {
			int[] ints = new int[10];
			for (int i : A) {
				ints[i]++;
			}
			if (hour > 9) {
				if (ints[hour / 10] > 0) {
					ints[hour / 10]--;
				} else {
					hour--;
					continue;
				}
				if (ints[hour % 10] > 0) {
					ints[hour % 10]--;
				} else {
					hour--;
					continue;
				}
			} else {
				if (ints[0] > 0) {
					ints[0]--;
				} else {
					hour--;
					continue;
				}
				if (ints[hour] > 0) {
					ints[hour]--;
				} else {
					hour--;
					continue;
				}
			}
			int min = 0;
			for (int i = 9; i >= 0; i--) {
				int t = ints[i];
				while (t > 0) {
					min = min * 10 + i;t--;
				}
			}
			if (min < 60) {
				return (hour > 9 ? hour : "0" + hour) + ":" + (min > 9 ? min : "0" + min);
			}
			min=0;
			for (int i = 0; i < 10; i++) {
				int t = ints[i];
				while (t > 0) {
					min = min * 10 + i;t--;
				}
			}
			if (min < 60) {
				return (hour > 9 ? hour : "0" + hour) + ":" + (min > 9 ? min : "0" + min);
			}
			hour--;
		}
		return "";
	}
	
	public boolean isPalindrome(String s) {
		char[] array = s.toCharArray();
		int l = 0;
		int r = array.length - 1;
		char cl;
		char rl;
		String temp = "1234567890abcdefghijklmnopqrstuvwxyz";
		while (l < r) {
			while (l < r && temp.indexOf(Character.toLowerCase(array[l])) < 0) {
				l++;
			}
			cl = Character.toLowerCase(array[l]);
			while (l < r && temp.indexOf(Character.toLowerCase(array[r])) < 0) {
				r--;
			}
			rl = Character.toLowerCase(array[r]);
			if (l < r && cl != rl) {
				return false;
			} else {
				l++;
				r--;
			}
		}
		return true;
	}
	
	public boolean validPalindrome(String s) {
		char[] array = s.toCharArray();
		int i = 0;
		int j = array.length - 1;
		while (i < j) {
			if (array[i] != array[j]) {
				return validPalindromeHelper(array, i + 1, j) || validPalindromeHelper(array, i,
					j - 1);
				
			}
			i++;
			j--;
		}
		return true;
	}
	
	private boolean validPalindromeHelper(char[] array, int i, int j) {
		while (i < j) {
			if (array[i] != array[j]) {
				return false;
			}
			i++;
			j--;
		}
		return true;
	}
	
	public boolean checkPerfectNumber(int num) {
		int i = 1;
		int sum = 0;
		while (i <= num / i) {
			if (num % i == 0) {
				sum += i;
				sum += (num / i);
			}
			i++;
		}
		return sum == num;
	}
	
	public boolean hasGroupsSizeX(int[] deck) {
		if (deck.length < 2) {
			return false;
		}
		int[] ints = new int[10001];
		for (int i : deck) {
			ints[i]++;
		}
		int g = 0;
		for (int anInt : ints) {
			if (anInt > 0) {
				if (g == 0) {
					g = anInt;
				} else {
					g = gcd(g, anInt);
				}
			}
		}
		return g >= 2;
	}
	
	public int gcd(int x, int y) {
		return x == 0 ? y : gcd(y % x, x);
	}
	
	public boolean wordPattern(String pattern, String str) {
		String[] strings = str.split(" ");
		if (pattern.length() != strings.length) {
			return false;
		}
		int[] ints = new int[26];
		HashMap<String, Character> map = new HashMap<>();
		for (int i = 0; i < strings.length; i++) {
			Character c = map.get(strings[i]);
			if (c == null) {
				if (ints[pattern.charAt(i) - 'a'] == 0) {
					map.put(strings[i], pattern.charAt(i));
					ints[pattern.charAt(i) - 'a']++;
				} else {
					return false;
				}
			} else if (c != pattern.charAt(i)) {
			
			}
		}
		return true;
	}
	
	public static boolean validMountainArray1(int[] A) {
		if (A.length < 3) {
			return false;
		}
		boolean u = false, d = false;
		int i = 1;
		
		while (i < A.length && A[i - 1] < A[i]) {
			i++;
			u = true;
		}
		while (i < A.length && A[i - 1] > A[i]) {
			i++;
			d = true;
		}
		
		return i == A.length - 1 && u && d;
	}
	
	public static boolean validMountainArray(int[] A) {
		if (A.length < 3) {
			return false;
		}
		boolean u = false, d = false, p = false;
		int i = 1;
		while (i < A.length && A[i - 1] == A[i]) {
			i++;
		}
		int temp = A[i - 1];
		while (i < A.length && temp < A[i]) {
			u = true;
			temp = A[i];
			while (i < A.length - 1 && A[i] == A[i + 1]) {
				i++;
				p = true;
			}
			if (p) {
				if (A[i + 1] < temp) {
					return false;
				} else {
					p = false;
				}
			} else {
				i++;
			}
		}
		while (i < A.length && temp >= A[i]) {
			d = true;
			i++;
		}
		if (i < A.length) {
			return false;
		}
		return u && d;
	}
	
	public static boolean containsNearbyDuplicate1(int[] nums, int k) {
		
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				return true;
			} else {
				set.add(nums[i]);
			}
			if (i >= k) {
				set.remove(nums[i - k]);
			}
		}
		return false;
	}
	
	public static boolean containsNearbyDuplicate(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int i = 0;
		while (i < k && i < nums.length) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
			if (map.get(nums[i]) > 1) {
				return true;
			}
			i++;
		}
		
		for (; i < nums.length; i++) {
			map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
			if (map.get(nums[i]) > 1) {
				return true;
			} else {
				map.put(nums[i - k], map.get(nums[i - k]) - 1);
			}
		}
		return false;
	}
	
	int miniDep = Integer.MAX_VALUE;
	
	public int minDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		minDepthHelper(root.left, 1);
		minDepthHelper(root.right, 1);
		
		return miniDep;
	}
	
	private void minDepthHelper(TreeNode root, int i) {
		if (root.left == null && root.right == null) {
			miniDep = Math.min(miniDep, 1 + i);
		}
		if (root.left != null) {
			minDepthHelper(root.left, 1 + i);
		}
		if (root.right != null) {
			minDepthHelper(root.right, 1 + i);
		}
	}
	
	public static int numMagicSquaresInside(int[][] grid) {
		if (grid.length < 3 || grid[0].length < 3) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < grid.length - 2; i++) {
			for (int j = 0; j < grid[i].length - 2; j++) {
				if (numMagicSquaresInsideHelp(grid, i, j)) {
					count++;
					i++;
				}
			}
		}
		return count;
	}
	
	private static boolean numMagicSquaresInsideHelp(int[][] grid, int i, int j) {
		if (grid[i + 1][j + 1] != 5) {
			return false;
		} else {
			if (grid[i][j] % 2 != 0) {
				return false;
			} else {
				return grid[i][j] + grid[i + 1][j] + grid[i + 2][j] == 15 &&
					grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1] == 15 &&
					grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2] == 15 &&
					grid[i][j] + grid[i][j + 1] + grid[i][j + 2] == 15 &&
					grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2] == 15 &&
					grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2] == 15;
			}
		}
	}
	
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums2.length < 1) {
			return;
		}
		int a, b, c = Math.min(nums1[0], nums1[0]);
		for (int i = m + n - 1; i >= 0; i--) {
			a = m > 0 ? nums1[m - 1] : c;
			b = nums2[n - 1];
			if (a > b) {
				nums1[i] = a;
				nums1[m - 1] = c;
				m--;
			} else {
				nums1[i] = b;
				n--;
			}
			if (n == 0) {
				break;
			}
		}
	}
	
	public ListNode removeElements(ListNode head, int val) {
		while (head != null && head.val == val) {
			head = head.next;
		}
		if (head == null) {
			return head;
		}
		ListNode temp = head;
		while (temp.next != null) {
			if (temp.next.val == val) {
				temp.next = temp.next.next;
			} else if (temp.next != null) {
				temp = temp.next;
			}
		}
		return head;
	}
	
	public static boolean isPalindrome1(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ArrayList<Integer> list = new ArrayList<>();
		list.add(head.val);
		int index = 0;
		while (head.next != null && (!isPalindromeHelper(head.next, list, index))) {
			head = head.next;
			list.add(head.val);
			index++;
		}
		if (head.next == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public static boolean isPalindrome(ListNode head) {
		if (head == null || head.next == null) {
			return true;
		}
		ListNode fast = head;
		ListNode reverse = null;
		while (fast != null) {
			if (fast.next == null) {
				head = head.next;
				break;
			} else {
				fast = fast.next.next;
			}
			ListNode temp = head.next;
			head.next = reverse;
			reverse = head;
			head = temp;
		}
		while (reverse != null) {
			if (reverse.val != head.val) {
				return false;
			}
			reverse = reverse.next;
			head = head.next;
		}
		return true;
	}
	
	private static boolean isPalindromeHelper(ListNode node, ArrayList<Integer> list, int index) {
		boolean a1 = true;
		boolean a2 = true;
		while (node != null && index >= 0 && (a1 || a2)) {
			if (a1) {
				if (node.val != list.get(index)) {
					a1 = false;
				}
			}
			if (a2) {
				if (node.next == null) {
					a2 = false;
				} else {
					if (node.next.val != list.get(index)) {
						a2 = false;
					}
				}
			}
			index--;
			node = node.next;
		}
		return (a1 && node == null || a2 && node.next == null) && index == -1;
	}
	
	public static boolean isValid(String s) {
		if (s.length() < 1 || s.length() % 2 != 0) {
			return false;
		}
		ArrayList<Character> list = new ArrayList<>();
		String l = "([{";
		int index = -1;
		for (char c : s.toCharArray()) {
			if (l.indexOf(c) >= 0) {
				list.add(c);
				index++;
			} else if (index < 0) {
				return false;
			} else {
				switch (c) {
					case ')':
						if (list.get(index) == '(') {
							list.remove(index);
							index--;
						} else {
							return false;
						}
						break;
					case '}':
						if (list.get(index) == '{') {
							list.remove(index);
							index--;
						} else {
							return false;
						}
						break;
					case ']':
						if (list.get(index) == '[') {
							list.remove(index);
							index--;
						} else {
							return false;
						}
						break;
				}
			}
		}
		
		return index == -1;
	}
	
	
	public boolean hasCycle(ListNode head) {
		if (head.next == null || head == null) {
			return false;
		}
		ListNode s = head;
		ListNode f = head.next;
		while (s != f) {
			s = s.next;
			if (f.next != null) {
				f = f.next.next;
			} else {
				return false;
			}
		}
		return true;
	}
	
	public static List<Integer> findAnagrams(String s, String p) {
		ArrayList<Integer> list = new ArrayList<>();
		if (s.length() < p.length() || p.length() < 1) {
			return list;
		}
		int[] pi = new int[5];
		int[] si = new int[5];
		for (char c : p.toCharArray()) {
			pi[c - 'a']++;
		}
		char[] sc = s.toCharArray();
		int i = 0;
		for (; i < p.length(); i++) {
			si[sc[i] - 'a']++;
		}
		int k = 0;
		
		for (; i < sc.length; i++) {
			while (k < pi.length) {
				if (pi[k] != si[k]) {
					break;
				}
				k++;
			}
			if (k == pi.length) {
				list.add(i - p.length());
			}
			k = 0;
			si[sc[i] - 'a']++;
			si[sc[i - p.length()] - 'a']--;
		}
		while (k < pi.length) {
			if (pi[k] != si[k]) {
				break;
			}
			k++;
		}
		if (k == pi.length) {
			list.add(i - p.length());
		}
		return list;
	}
	
	public int trailingZeroes(int n) {
		if (n == 0) {
			return 0;
		}
		return n / 5 + trailingZeroes(n / 5);
	}
	
	public boolean isIsomorphic(String s, String t) {
		if (s.length() < 2) {
			return true;
		}
		char[] sc = s.toCharArray();
		char[] tc = t.toCharArray();
		char max = sc[0];
		char min = sc[0];
		for (char c : sc) {
			if (c > max) {
				max = c;
			} else if (c < min) {
				min = c;
			}
		}
		char[] chars = new char[max - min + 1];
		max = tc[0];
		char bmin = tc[0];
		for (char c : tc) {
			if (c > max) {
				max = c;
			} else if (c < bmin) {
				bmin = c;
			}
		}
		int[] ints = new int[max - bmin + 1];
		for (int i = 0; i < sc.length; i++) {
			if (chars[sc[i] - min] == (char) 0) {
				if (ints[tc[i] - bmin] == 0) {
					ints[tc[i] - bmin] = 1;
					chars[sc[i] - min] = tc[i];
				} else {
					return false;
				}
			} else if (chars[sc[i] - min] != tc[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static int compress(char[] chars) {
		if (chars.length < 2) {
			return chars.length;
		}
		char temp = chars[0];
		int k = 0;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			if (temp == chars[i]) {
				k++;
			} else {
				if (k > 1) {
					chars[index++] = temp;
					for (char c : String.valueOf(k).toCharArray()) {
						chars[index++] = c;
					}
				} else {
					chars[index] = temp;
					index++;
				}
				temp = chars[i];
				k = 1;
			}
		}
		if (k > 1) {
			chars[index++] = temp;
			for (char c : String.valueOf(k).toCharArray()) {
				chars[index++] = c;
			}
		} else {
			chars[index] = temp;
			index++;
		}
		return index - 1;
	}
	
	public boolean hasPathSum(TreeNode root, int sum) {
		if (root == null) {
			return false;
		} else if (root.val == sum && root.left == null && root.right == null) {
			return true;
		} else {
			return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
		}
	}
	
	public static int arrangeCoins(int n) {
		int l = 1;
		int r = n;
		int m = (l + (r - l) / 2) > 1024 * 64 ? 1024 * 64 : (l + (r - l) / 2);
		long temp;
		while (r - l != 1 && r > l) {
			if ((m & 1) == 1) {
				temp = (m + 1) / 2 * m;
			} else {
				temp = m / 2 * (m + 1);
			}
			if (temp > n || temp < 0) {
				r = m;
			} else if (temp < n) {
				l = m;
			} else {
				return m;
			}
			m = (l + (r - l) / 2);
		}
		return l;
	}
	
	/*public int guessNumber(int n) {
		int start = 1;
		int end = n;
		int mid = start + (end - start) / 2;
		int g = guess(mid);
		while (g != 0) {
			if (g == -1) {
				end = mid;
			} else {
				start = mid + 1;
			}
			mid = start + (end - start) / 2;
			g = guess(mid);
		}
		return mid;
	}*/
	public String addBinary(String a, String b) {
		if (a.length() < b.length()) {
			return addBinary(b, a);
		}
		if (b.length() < 1) {
			return a;
		}
		char[] ac = a.toCharArray();
		char[] bc = b.toCharArray();
		
		int j = bc.length - 1;
		int temp = 0;
		for (int i = ac.length - 1; i >= 0; i--) {
			if (j >= 0) {
				temp += bc[j] - '0';
				j--;
			}
			temp += ac[i];
			if (temp == '3') {
				ac[i] = '1';
				
				temp = 1;
			} else if (temp == '2') {
				ac[i] = '0';
				
				temp = 1;
			} else {
				ac[i] = (char) temp;
				
				temp = 0;
			}
			if (j < 0 && temp == 0) {
				return new String(ac);
			}
		}
		
		return 1 + new String(ac);
	}
	
	static int findModeMax;
	static Set<Integer> set = new HashSet<>();
	static Integer temp;
	static Integer tempCount;
	
	public static int[] findMode(TreeNode root) {
		findModeHelper(root);
		
		int[] ints = new int[set.size()];
		int k = 0;
		for (Integer integer : set) {
			ints[k++] = integer;
		}
		return ints;
	}
	
	private static void findModeHelper(TreeNode root) {
		if (root == null) {
			return;
		}
		findModeHelper(root.left);
		if (temp == null || temp != root.val) {
			temp = root.val;
			tempCount = 1;
		} else {
			tempCount++;
		}
		if (tempCount > findModeMax) {
			findModeMax = tempCount;
			set.clear();
			set.add(temp);
		} else if (tempCount == findModeMax) {
			set.add(temp);
		}
		findModeHelper(root.right);
	}
	
	
	public boolean repeatedSubstringPattern(String s) {
		char[] array = s.toCharArray();
		for (int i = 2; i <= array.length; i++) {
			if (array.length % i == 0) {
				int j = array.length / i;
				for (; j < array.length; j++) {
					if (array[j % (array.length / i)] != array[j]) {
						break;
					}
				}
				if (j == array.length) {
					return true;
				}
			}
		}
		return false;
	}
	
	public static List<Integer> powerfulIntegers(int x, int y, int bound) {
		
		HashSet<Integer> set = new HashSet<>();
		for (int i = 1; i < bound; i *= x) {
			for (int j = 1; j < bound; j *= y) {
				if (i + j < bound) {
					set.add(i + j);
				}
				if (y == 1) {
					break;
				}
			}
			if (x == 1) {
				break;
			}
		}
		return new ArrayList<>(set);
	}
	
	public static double findMaxAverage(int[] nums, int k) {
		int i = 0;
		int max = 0;
		int sum = 0;
		while (i < k && i < nums.length) {
			sum += nums[i++];
		}
		if (k >= nums.length) {
			return sum * 1.0 / k;
		}
		max = sum;
		for (; i < nums.length; i++) {
			sum = sum + nums[i] - nums[i - k];
			max = Math.max(sum, max);
		}
		return max * 1.0 / k;
	}
	
	public boolean isPerfectSquare(int num) {
		if (num == 1) {
			return true;
		}
		int sqrt = num / 2;
		while (num / sqrt < sqrt) {
			sqrt >>= 1;
		}
		for (int i = sqrt; i < num / sqrt; i++) {
			if (i * i == num) {
				return true;
			}
		}
		return false;
	}
	
	class Node4 {
		
		public boolean val;
		public boolean isLeaf;
		public Node4 topLeft;
		public Node4 topRight;
		public Node4 bottomLeft;
		public Node4 bottomRight;
		
		public Node4() {
		}
		
		public Node4(boolean _val, boolean _isLeaf, Node4 _topLeft, Node4 _topRight,
			Node4 _bottomLeft, Node4 _bottomRight) {
			val = _val;
			isLeaf = _isLeaf;
			topLeft = _topLeft;
			topRight = _topRight;
			bottomLeft = _bottomLeft;
			bottomRight = _bottomRight;
		}
	}
	
	public boolean isPowerOfFour(int num) {
		while (num > 4 && num % 4 == 0) {
			num /= 4;
		}
		if (num == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public static String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		String s = "1";
		char temp;
		int k = 0;
		StringBuffer buffer = new StringBuffer();
		for (int i = 1; i < n; i++) {
			char[] array = s.toCharArray();
			temp = array[0];
			k = 0;
			for (int j = 0; j < array.length; ) {
				while (j < array.length && temp == array[j]) {
					k++;
					j++;
				}
				buffer.append(k).append(temp);
				if (j < array.length) {
					temp = array[j];
					k = 0;
				}
			}
			s = buffer.toString();
			buffer.setLength(0);
		}
		return s;
	}
	
	public Node4 intersect(Node4 quadTree1, Node4 quadTree2) {
		Node4 ret;
		if (quadTree1.isLeaf && quadTree2.isLeaf) {
			ret = new Node4(quadTree1.val || quadTree2.val, true, null, null, null, null);
		} else if (quadTree1.isLeaf) {
			if (quadTree1.val) {
				ret = new Node4(quadTree1.val, true, null, null, null, null);
			} else {
				ret = quadTree2;
			}
		} else if (quadTree2.isLeaf) {
			if (quadTree2.val) {
				
				ret = new Node4(quadTree2.val, true, null, null, null, null);
			} else {
				ret = quadTree1;
			}
		} else {
			ret = new Node4();
			Node4 topLeft = intersect(quadTree1.topLeft, quadTree2.topLeft);
			Node4 topRight = intersect(quadTree1.topRight, quadTree2.topRight);
			Node4 bottomLeft = intersect(quadTree1.bottomLeft, quadTree2.bottomLeft);
			Node4 bottomRight = intersect(quadTree1.bottomRight, quadTree2.bottomRight);
			if (topLeft.isLeaf && topRight.isLeaf && bottomLeft.isLeaf && bottomRight.isLeaf) {
				if (topLeft.val && topRight.val && bottomLeft.val && bottomRight.val) {
					ret.isLeaf = true;
					ret.val = true;
					return ret;
				} else if (!(topLeft.val || topRight.val || bottomLeft.val || bottomRight.val)) {
					ret.isLeaf = true;
					ret.val = false;
					return ret;
				}
			}
			ret.isLeaf = false;
			ret.topLeft = topLeft;
			ret.topRight = topRight;
			ret.bottomLeft = bottomLeft;
			ret.bottomRight = bottomRight;
		}
		return ret;
	}
	
	public static int searchInsert(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] >= target) {
				return i;
			}
		}
		return nums.length;
	}
	
	public int removeDuplicates(int[] nums) {
		if (nums.length < 1) {
			return 0;
		}
		int index = 1;
		int temp = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (temp != nums[i]) {
				temp = nums[i];
				nums[index++] = nums[i];
			}
		}
		return index + 1;
	}
	
	public static int dominantIndex(int[] nums) {
		if (nums.length < 3) {
			if (nums.length == 1) {
				return 0;
			} else {
				if (nums[0] * 2 <= nums[1]) {
					return 1;
				} else if (nums[1] * 2 <= nums[0]) {
					return 0;
				} else {
					return -1;
				}
			}
		}
		int i = 0;
		while (i < nums.length && nums[i] == nums[0]) {
			i++;
		}
		if (i == nums.length) {
			return -1;
		}
		int f = nums[0] > nums[i] ? 0 : i;
		int s = nums[0] < nums[i] ? 0 : i;
		i++;
		for (; i < nums.length; i++) {
			if (nums[i] > nums[f]) {
				s = f;
				f = i;
			} else if (nums[i] > nums[s]) {
				s = i;
			}
		}
		
		if (nums[s] * 2 <= nums[f]) {
			return f;
		} else {
			return -1;
		}
		
	}
	
	public boolean isUgly(int num) {
		if (num <= 0) {
			return false;
		} else {
			while (num % 2 == 0) {
				num >>= 1;
			}
			while (num % 3 == 0) {
				num /= 3;
			}
			while (num % 5 == 0) {
				num /= 5;
			}
			if (num == 1) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public int[] findErrorNums(int[] nums) {
		int[] ints = new int[nums.length];
		for (int num : nums) {
			ints[num - 1]++;
		}
		int[] ret = new int[2];
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] == 2) {
				ret[0] = i + 1;
			} else if (ints[i] == 0) {
				ret[1] = i + 1;
			}
		}
		return ret;
	}
	
	public static String licenseKeyFormatting(String S, int K) {
		
		String s = S.replace("-", "");
		String s1 = s.toUpperCase();
		if (s.length() < K) {
			return s1;
		}
		StringBuffer buffer = new StringBuffer();
		char[] array = s1.toCharArray();
		int k;
		String substring = s1.substring(0, s1.length() % K);
		buffer.append(substring);
		k = 0;
		for (int i = s1.length() % K; i < array.length; i++) {
			if (k % K == 0) {
				buffer.append('-');
			}
			buffer.append(array[i]);
			k++;
		}
		String s2 = buffer.toString();
		if (s1.length() % K == 0) {
			s2.substring(1, s2.length());
		}
		
		return s2;
	}
	
	public static int maxDistToClosest(int[] seats) {
		int l = 0;
		int max = 0;
		while (l < seats.length && seats[l] == 0) {
			l++;
		}
		max = l;
		int r = l + 1;
		
		for (; r < seats.length; r++) {
			while (r < seats.length && seats[r] == 0) {
				r++;
			}
			if (r < seats.length) {
				max = Math.max(max, (r - l) / 2);
				l = r;
			}
		}
		max = Math.max(max, (r - l - 2));
		return max;
	}
	
	public boolean isBalanced(TreeNode root) {
		if (root == null) {
			return true;
		}
		return Math.abs(isBalancedHelper(root.left) - isBalancedHelper(root.right)) < 2
			&& isBalanced(root.left) && isBalanced(root.right);
	}
	
	private int isBalancedHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return Math.max(isBalancedHelper(root.left), isBalancedHelper(root.right)) + 1;
	}
	
	public static int pivotIndex(int[] nums) {
		if (nums.length < 3) {
			if (nums.length == 1) {
				return 0;
			} else {
				return -1;
			}
		}
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		int lsum = 0;
		for (int i = 0; i < nums.length; i++) {
			
			if (lsum == (sum - lsum - nums[i])) {
				return i;
			}
			lsum += nums[i];
		}
		return -1;
	}
	
	public static int rob(int[] nums) {
		if (nums.length > 2) {
			int[] ints = new int[nums.length];
			ints[0] = nums[0];
			ints[1] = Math.max(nums[0], nums[1]);
			int index = 2;
			while (index < nums.length) {
				ints[index] = Math.max(ints[index - 1], nums[index] + ints[index - 2]);
				index++;
			}
			return ints[nums.length - 1];
		} else {
			if (nums.length == 0) {
				return 0;
			}
			if (nums.length == 1) {
				return nums[0];
			}
			
			return Math.max(nums[0], nums[1]);
			
			
		}
	}
	
	public String reverseVowels(String s) {
		String reg = "aeiouAEIOU";
		char[] array = s.toCharArray();
		int l = 0, r = array.length - 1;
		char temp;
		while (l < r) {
			while (l < r && reg.indexOf(array[l]) < 0) {
				l++;
			}
			while (l < r && reg.indexOf(array[r]) < 0) {
				r--;
			}
			temp = array[l];
			array[l] = array[r];
			array[r] = temp;
		}
		return new String(array);
	}
	
	public boolean isSubtree(TreeNode s, TreeNode t) {
		if (t == null) {
			return true;
		}
		if (s == null) {
			return false;
		}
		return isSubtreeHelper(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
	}
	
	public int[] plusOne(int[] digits) {
		int[] ints = new int[digits.length + 1];
		int k = 1;
		for (int i = digits.length - 1; i >= 0; i--) {
			k += digits[i];
			if (k > 0) {
				digits[i] = k % 10;
				ints[i + 1] = k % 10;
				k = 1;
			} else {
				digits[i] = k;
				return digits;
			}
		}
		ints[0] = k;
		return ints;
	}
	
	private boolean isSubtreeHelper(TreeNode s, TreeNode t) {
		if (s == null && t == null) {
			return true;
		} else if (s == null || t == null || s.val != t.val) {
			return false;
		} else {
			return isSubtreeHelper(s.left, t.left) && isSubtreeHelper(s.right, t.right);
		}
		
		
	}
	
	
	public static boolean isPowerOfThree(int n) {
		if (n > 1) {
			while (n > 1) {
				if (n % 3 != 0) {
					return false;
				}
				n /= 3;
			}
		} else if (n == 1) {
			return true;
		}
		
		return false;
	}
	
	public static String toHex(int num) {
		boolean flag = num < 0;
		int[] ints = new int[32];
		num = Math.abs(num);
		int index = 31;
		while (num > 0) {
			ints[index] = num % 2;
			num >>= 1;
			index--;
		}
		int temp = 0;
		String s = "";
		if (flag) {
			for (int i = 0; i < ints.length; i++) {
				ints[i] = ints[i] ^ 1;
			}
			int i = ints.length - 1;
			int k = 1;
			while (k == 1 && ints[i] == 1 && i >= 0) {
				if (ints[i] > 0) {
					ints[i] = 0;
				} else {
					ints[i] = 1;
					k = 0;
				}
				
			}
			
		}
		
		for (int i = 0; i < ints.length; i++) {
			switch (i % 4) {
				case 0:
					temp += (ints[i]) * 8;
					break;
				case 1:
					temp += (ints[i]) * 4;
					break;
				case 2:
					temp += (ints[i]) * 2;
					break;
				default:
					temp += (ints[i]) * 1;
					if (temp != 0 || s.length() > 0) {
						switch (temp) {
							case 10:
								s = 'a' + s;
								break;
							case 11:
								s = 'b' + s;
								break;
							case 12:
								s = 'c' + s;
								break;
							case 13:
								s = 'd' + s;
								break;
							case 14:
								s = 'e' + s;
								break;
							case 15:
								s = 'f' + s;
								break;
							default:
								s = temp + s;
						}
						temp = 0;
					}
			}
			
		}
		return s.length() == 0 ? "0" : s;
	}
	
	public boolean isPowerOfTwo(int n) {
		if (n > 0) {
			if (n == 1) {
				return true;
			}
			n = n - 1;
			while (n > 0) {
				if ((n & 1) != 1) {
					return false;
				}
				n >>= 1;
			}
			return true;
		} else {
			return false;
		}
	}
	
	public String mostCommonWord(String paragraph, String[] banned) {
		HashSet<String> set = new HashSet<>();
		HashMap<String, Integer> map = new HashMap<>();
		for (String s : banned) {
			set.add(s);
		}
		StringBuffer buffer = new StringBuffer();
		String s1;
		char[] array = paragraph.toLowerCase().toCharArray();
		for (int i = 0; i < array.length; i++) {
			while (i < array.length && array[i] >= 'a' && array[i] <= 'z') {
				buffer.append(array[i]);
				i++;
			}
			if (buffer.length() > 0) {
				s1 = buffer.toString();
				if (!set.contains(s1)) {
					map.put(s1, map.getOrDefault(s1, 0) + 1);
				}
				buffer.setLength(0);
			}
		}
		int max = 0;
		s1 = null;
		for (String s : map.keySet()) {
			if (map.get(s) > max) {
				max = map.get(s);
				s1 = s;
			}
		}
		return s1;
	}
	
	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		char[] array = (x + "").toCharArray();
		int l = 0, r = array.length - 1;
		while (l < r) {
			if (array[l] != array[r]) {
				return false;
			}
		}
		return true;
	}
	
	public ListNode deleteDuplicates(ListNode head) {
		ListNode ret = head;
		while (head.next != null) {
			if (head.val == head.next.val) {
				head.next = head.next.next;
			} else {
				head = head.next;
			}
		}
		return ret;
	}
	
	
	static int pathSum;
	
	public static int pathSum(TreeNode root, int sum) {
		if (root == null) {
			return 0;
		}
		pathSum(root.left, sum);
		pathSum(root.right, sum);
		pathSumHelper(root, sum);
		return pathSum;
	}
	
	private static void pathSumHelper(TreeNode root, int sum) {
		if (root == null) {
			return;
		}
		if (sum - root.val == 0) {
			pathSum++;
		}
		pathSumHelper(root.left, sum - root.val);
		pathSumHelper(root.right, sum - root.val);
	}
	
	public int hammingWeight(int n) {
		int count = 0;
		int k = 32;
		while (k > 0) {
			if ((n & 1) == 1) {
				count++;
			}
			n >>= 1;
			k--;
		}
		return count;
	}
	
	public static List<Integer> getRow(int rowIndex) {
		ArrayList<Integer> a = null;
		ArrayList<Integer> b = new ArrayList<>();
		b.add(1);
		b.add(1);
		if (rowIndex == 1) {
			return b;
		} else {
			for (int i = 2; i <= rowIndex; i++) {
				a = new ArrayList<>();
				a.add(1);
				for (int j = 0; j < b.size() - 1; j++) {
					a.add(b.get(j) + b.get(j + 1));
				}
				a.add(1);
				b = a;
			}
		}
		return a;
	}
}
