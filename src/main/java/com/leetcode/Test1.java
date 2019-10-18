package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test1
 * @Author: WuMing
 * @CreateDate: 2019/4/11 10:02
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test1 {
	
	public static void main(String[] args) {
		//	imageSmoother(new int[][]{{2, 3, 4}, {5, 6, 7}, {8, 9, 10}, {11, 12, 13}, {14, 15, 16}});
		//largeGroupPositions("abccccdd");
		//minCostClimbingStairs(new int[]{0, 0, 1, 2});
		//orangesRotting(new int[][]{{2, 1, 1}, {1, 1, 0}, {0, 1, 1}});
		//maximumProduct(new int[]{-1000, -1000, -1000});
		//generate(5);
		//numPairsDivisibleBy60(new int[]{60, 60, 60});
		/*String[] a = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
		Arrays.sort(a);
		for (String s : a) {
			System.out.println(s);
		}*/
		//removeElement(new int[]{1}, 1);
		TreeNode treeNode = new TreeNode(2);
		treeNode.left = new TreeNode(2);
		treeNode.right = new TreeNode(5);
		treeNode.right.left = new TreeNode(5);
		treeNode.right.right = new TreeNode(7);
		findLHS(
			new int[]{4289383, 46930886, 81692777, 14636915, 57747793, 24238335, 19885386, 49760492,
				96516649, 89641421});
	}
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val > q.val) {
			return lowestCommonAncestor(root, q, p);
		}
		if (q.val == root.val || p.val == root.val || (p.val < root.val && q.val > root.val)) {
			return root;
		} else if (q.val < root.val && p.val < root.val) {
			return lowestCommonAncestor(root.left, p, q);
		} else {
			return lowestCommonAncestor(root.right, p, q);
		}
		
	}
	
	static int firstMin = Integer.MAX_VALUE;
	static Integer secondMin = null;
	
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		} else if (root.left == null && root.right == null) {
			return true;
		} else {
			return isSymmetricHelper(root.left, root.right);
		}
	}
	
	private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		} else if (left == null || right == null) {
			return false;
		}
		if (left.val == right.val) {
			return isSymmetricHelper(left.left, right.right) && isSymmetricHelper(left.right,
				right.left);
		} else {
			return false;
		}
	}
	
	public int maxSubArray(int[] nums) {
		int max = nums[0];
		int cur_max = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (cur_max > 0) {
				cur_max += nums[i];
			} else {
				cur_max = nums[i];
			}
			max = Math.max(max, cur_max);
		}
		return max;
	}
	
	public static int findLHS(int[] nums) {
		
		Arrays.sort(nums);
		int temp;
		int k;
		int l = 0;
		int max = 0;
		int i = 0;
		for (; i < nums.length; ) {
			temp = nums[i];
			k = 0;
			while (i < nums.length && temp == nums[i]) {
				k++;
				i++;
			}
			if (l > 0) {
				max = Math.max(max, l + k);
			}
			if (i < nums.length) {
				if (nums[i] - temp == 1) {
					l = k;
				} else {
					l = 0;
				}
			}
			
		}
		return max;
	}
	
	public String addStrings(String num1, String num2) {
		if (num1.length() < num2.length()) {
			return addStrings(num2, num1);
		}
		char[] a = num1.toCharArray();
		char[] b = num2.toCharArray();
		int i = a.length - 1, j = b.length - 1, temp = 0;
		for (; i >= 0; i--) {
			if (j >= 0) {
				temp = a[i] + b[j] - '0' - '0' + temp;
			} else if (temp == 1) {
				temp = a[i] - '0' + temp;
			} else {
				break;
			}
			if (temp >= 10) {
				a[i] = (char) (temp % 10 + '0');
				temp = 1;
			} else {
				a[i] = (char) (temp + '0');
				temp = 0;
			}
			j--;
		}
		if (temp == 1) {
			return "1" + String.valueOf(a);
		} else {
			return String.valueOf(a);
		}
	}
	
	public static int findSecondMinimumValue(TreeNode root) {
		if (root.val < secondMin) {
			if (root.val < firstMin) {
				secondMin = firstMin;
				firstMin = root.val;
			} else {
				secondMin = root.val;
			}
		}
		if (root.right != null) {
			findSecondMinimumValue(root.right);
		}
		if (root.left != null) {
			findSecondMinimumValue(root.left);
		}
		return secondMin != null ? secondMin : -1;
	}
	
	public char nextGreatestLetter(char[] letters, char target) {
		int index = 0;
		while (index < letters.length && target - letters[index] > 0) {
			index++;
		}
		if (letters.length == index) {
			return letters[0];
		} else {
			return letters[index];
		}
	}
	
	public int climbStairs(int n) {
		if (n <= 2) {
			return n;
		}
		int[] ints = new int[n];
		ints[0] = 1;
		ints[1] = 2;
		for (int i = 2; i < n; i++) {
			ints[i] = ints[i - 1] + ints[i - 2];
		}
		return ints[n - 1];
	}
	
	public int findLengthOfLCIS(int[] nums) {
		if (nums.length < 1) {
			return 0;
		}
		int ret = 1, k;
		for (int i = 1; i < nums.length; i++) {
			k = 1;
			while (i < nums.length && nums[i] > nums[i - 1]) {
				k++;
				i++;
			}
			if (k > ret) {
				ret = k;
			}
		}
		return ret;
	}
	
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		if (p.val > q.val) {
			return lowestCommonAncestor(root, q, p);
		}
		while (!(q.val == root.val || p.val == root.val || (p.val < root.val
			&& q.val > root.val))) {
			if (q.val < root.val && p.val < root.val) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		return root;
	}
	
	public static int removeElement(int[] nums, int val) {
		if (nums.length == 0) {
			return 0;
		}
		int l = 0, r = nums.length - 1, k = 0;
		while (l <= r) {
			while (l < nums.length && nums[l] != val) {
				l++;
			}
			while (r >= 0 && nums[r] == val) {
				r--;
				k++;
			}
			if (l < r) {
				nums[l] = nums[r];
				nums[r] = val;
				l++;
			}
			
		}
		return nums.length - k;
	}
	
	public boolean isLongPressedName(String name, String typed) {
		if (name.equals(typed)) {
			return true;
		}
		if (typed.length() < name.length()) {
			return false;
		}
		char[] a = name.toCharArray();
		char[] b = typed.toCharArray();
		int i = 0, j = 0, k, l;
		char temp;
		while (i < a.length && j < b.length) {
			k = 0;
			l = 0;
			temp = a[i];
			while (i < a.length && temp == a[i]) {
				k++;
				i++;
			}
			while (j < b.length && temp == b[j]) {
				l++;
				j++;
			}
			if (k > l) {
				return false;
			}
		}
		if (i != a.length || j != b.length) {
			return false;
		}
		return true;
	}
	
	public String longestWord(String[] words) {
		Arrays.sort(words);
		HashSet<String> set = new HashSet<>();
		String s = "";
		for (String word : words) {
			if (word.length() == 1) {
				set.add(word);
				if (word.length() > s.length()) {
					s = word;
				}
			} else {
				if (set.contains(word.substring(0, word.length() - 2))) {
					set.add(word);
					if (word.length() > s.length()) {
						s = word;
					}
				}
			}
			
		}
		return s;
	}
	
	public static int numPairsDivisibleBy601(int[] time) {
		int[] count = new int[60];
		int k;
		int sum = 0;
		for (int i = time.length - 1; i >= 0; i--) {
			k = time[i] % 60;
			sum += k == 0 ? count[0] : count[60 - k];
			count[k]++;
		}
		return sum;
	}
	
	int sumRootToLeaf;
	
	public int sumRootToLeaf(TreeNode root) {
		sumRootToLeafHelper(root, 0);
		return sumRootToLeaf;
	}
	
	private void sumRootToLeafHelper(TreeNode root, int val) {
		if (root.left == null && root.right == null) {
			sumRootToLeaf += val * 2 + root.val;
		}
		if (root.left != null) {
			sumRootToLeafHelper(root.left, val * 2 + root.val);
		}
		if (root.right != null) {
			sumRootToLeafHelper(root.right, val * 2 + root.val);
		}
	}
	
	public static int numPairsDivisibleBy60(int[] time) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int k;
		for (int i = time.length - 1; i >= 0; i--) {
			k = time[i] % 60;
			Integer integer = map.get((60 - k) % 60);
			if (integer != null) {
				sum += integer;
			}
			map.put(k, map.getOrDefault(k, 0) + 1);
		}
		return sum;
	}
	
	public static boolean isHappy(int n) {
		HashSet<Integer> set = new HashSet<>();
		int k;
		while (n != 1) {
			if (set.contains(n)) {
				return false;
			}
			set.add(n);
			k = 0;
			while (n > 0) {
				k += (n % 10) * (n % 10);
				n /= 10;
			}
			n = k;
		}
		return true;
	}
	
	public boolean isHappyHelp(int n, Set<Integer> set) {
		if (n == 1) {
			return true;
		} else if (set.contains(n)) {
			return false;
		} else {
			set.add(n);
		}
		int k = 0;
		while (n > 0) {
			k += (n % 10) * (n % 10);
			n /= 10;
		}
		return isHappy(k);
	}
	
	public String convertToBase7(int num) {
		if (num == 0) {
			return "0";
		}
		String flag = "";
		if (num < 0) {
			flag = "-";
			num = 0 - num;
		}
		String ret = "";
		while (num > 0) {
			ret = ret + num % 7;
			num /= 7;
		}
		return flag + ret;
	}
	
	public List<Boolean> prefixesDivBy5(int[] A) {
		ArrayList<Boolean> booleans = new ArrayList<>(A.length);
		
		return booleans;
	}
	
	public List<Integer> addToArrayForm(int[] A, int K) {
		int length = 6 > A.length ? 6 : A.length + 1;
		int[] ints = new int[length];
		int a;
		int k;
		int temp = 0;
		int s = 10;
		int i = 0;
		for (; i < length; i++) {
			a = i < A.length ? A[A.length - 1 - i] : 0;
			k = 0;
			if (K > 0) {
				k = K % s;
				K /= 10;
			}
			temp += (a + k);
			ints[length - i - 1] = temp % 10;
			temp /= 10;
		}
		ArrayList<Integer> integers = new ArrayList<>(ints.length);
		i = 0;
		while (ints[i] == 0 && i < ints.length) {
			i++;
		}
		
		for (; i < ints.length; i++) {
			integers.add(ints[i]);
		}
		if (integers.size() == 0) {
			integers.add(0);
		}
		return integers;
	}
	
	public boolean checkRecord(String s) {
		int k = 0;
		int a = 0;
		for (char c : s.toCharArray()) {
			if (c == 'A') {
				a++;
				k = 0;
			} else if (c == 'L') {
				k++;
			} else {
				k = 0;
			}
			if (a > 1 || k > 2) {
				return false;
			}
		}
		return true;
	}
	
	public static List<String> readBinaryWatch(int num) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		int k, j;
		int[] hours = new int[12];
		for (int i = 0; i < 60; i++) {
			j = i;
			k = 0;
			while (j > 0) {
				k += j % 2;
				j >>= 1;
			}
			if (i < 12) {
				hours[i] = k;
			}
			if (map.get(k) == null) {
				map.put(k, new ArrayList<>());
			}
			List<Integer> list = map.get(k);
			list.add(i);
		}
		
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < hours.length; i++) {
			List<Integer> minutes = map.get(num - hours[i]);
			if (minutes != null) {
				for (Integer minute : minutes) {
					if (minute > 9) {
						list.add(i + ":" + minute);
					} else {
						list.add(i + ":0" + minute);
					}
				}
			}
		}
		return list;
	}
	
	public static List<List<Integer>> generate(int numRows) {
		ArrayList<List<Integer>> lists = new ArrayList<>();
		if (numRows < 1) {
			return lists;
		} else if (numRows < 2) {
			ArrayList<Integer> integers = new ArrayList<>();
			integers.add(1);
			lists.add(integers);
			return lists;
		} else {
			ArrayList<Integer> first = new ArrayList<>();
			first.add(1);
			lists.add(first);
			for (int i = 1; i < numRows; i++) {
				ArrayList<Integer> integers = new ArrayList<>();
				integers.add(1);
				List<Integer> up = lists.get(i - 1);
				int temp = 1;
				while (temp < i) {
					integers.add(up.get(temp - 1) + up.get(temp));
					temp++;
				}
				integers.add(1);
				lists.add(integers);
			}
			return lists;
		}
	}
	
	public List<String> binaryTreePaths(TreeNode root) {
		List<String> list = new ArrayList<>();
		if (root == null) {
			return list;
		}
		binaryTreePathsHelper(root, "" + root.val, list);
		return list;
	}
	
	private void binaryTreePathsHelper(TreeNode root, String s, List<String> list) {
		if (root.left == null && root.right == null) {
			list.add(s + "->" + root.val);
		}
		if (root.left != null) {
			binaryTreePathsHelper(root.left, s + "->" + root.val, list);
		}
		if (root.right != null) {
			binaryTreePathsHelper(root.right, s + "->" + root.val, list);
		}
	}
	
	public boolean backspaceCompare(String S, String T) {
		char[] s = S.toCharArray();
		char[] t = T.toCharArray();
		int ti = 0;
		int si = 0;
		for (int i = 0; i < s.length; i++) {
			if (s[i] != '#') {
				s[si++] = s[i];
			} else if (si > 0) {
				si--;
			}
		}
		for (int i = 0; i < t.length; i++) {
			if (t[i] != '#') {
				t[ti++] = t[i];
			} else if (ti > 0) {
				ti--;
			}
		}
		if (ti != si) {
			return false;
		} else if (ti == 0) {
			return true;
		} else {
			ti--;
			si--;
			while (ti >= 0) {
				if (s[ti] != t[ti]) {
					return false;
				}
				ti--;
				
			}
			return true;
		}
	}
	
	public static int maximumProduct(int[] nums) {
		
		int[] min = new int[2];
		int[] max = new int[3];
		int[] ints = new int[2002];
		for (int num : nums) {
			ints[num + 1000]++;
		}
		int i = 0, j = ints.length - 1, k;
		k = 0;
		int temp;
		while (k < 2 && i < ints.length) {
			if (ints[i] > 0) {
				temp = ints[i];
				while (k < 2 && temp > 0) {
					min[k++] = i - 1000;
					temp--;
				}
			}
			i++;
		}
		k = 0;
		while (j >= 0 && k < 3) {
			if (ints[j] > 0) {
				temp = ints[j];
				while (k < 3 && temp > 0) {
					max[k++] = j - 1000;
					temp--;
				}
			}
			j--;
		}
		
		return Math.max(min[0] * min[1] * max[0], max[0] * max[1] * max[2]);
	}
	
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		ArrayList<List<Integer>> lists = new ArrayList<>();
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		levelOrderBottomHelper(root, map, 0);
		Set<Integer> integers = map.keySet();
		for (int i = integers.size() - 1; i >= 0; i--) {
			lists.add(map.get(i));
		}
		return lists;
	}
	
	private void levelOrderBottomHelper(TreeNode root, HashMap<Integer, List<Integer>> map,
		int i) {
		if (root != null) {
			if (map.get(i) == null) {
				ArrayList<Integer> list = new ArrayList<>();
				map.put(i, list);
			}
			List<Integer> integers = map.get(i);
			integers.add(root.val);
			levelOrderBottomHelper(root.left, map, i + 1);
			levelOrderBottomHelper(root.right, map, i + 1);
		}
	}
	
	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		
		return !(rec1[0] >= rec2[2] || rec1[2] <= rec2[0] || rec1[1] >= rec2[3]
			|| rec1[3] <= rec2[1]);
		
	}
	
	public static int orangesRotting(int[][] grid) {
		int total = 0;
		int old = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] > 0) {
					total++;
					if (grid[i][j] > 1) {
						old++;
					}
				}
			}
		}
		int temp = 0;
		int time = 0;
		while (old < total) {
			for (int i = 0; i < grid.length; i++) {
				for (int j = 0; j < grid[i].length; j++) {
					if (grid[i][j] == 1) {
						temp += orangesRottingHelper(grid, i, j, time);
					}
				}
			}
			if (temp == 0) {
				return -1;
			}
			old += temp;
			temp = 0;
			time++;
		}
		return time;
	}
	
	private static int orangesRottingHelper(int[][] grid, int i, int j, int time) {
		if (i > 0 && grid[i - 1][j] == 2 + time) {
			grid[i][j] = grid[i - 1][j] + 1;
			return 1;
		}
		if (i < grid.length - 1 && grid[i + 1][j] == 2 + time) {
			grid[i][j] = grid[i + 1][j] + 1;
			return 1;
		}
		if (j > 0 && grid[i][j - 1] == 2 + time) {
			grid[i][j] = grid[i][j - 1] + 1;
			return 1;
		}
		if (j < grid[i].length - 1 && grid[i][j + 1] == 2 + time) {
			grid[i][j] = grid[i][j + 1] + 1;
			return 1;
		}
		return 0;
	}
	
	public int search(int[] nums, int target) {
		return binarySearch(nums, target, 0, nums.length - 1);
	}
	
	private int binarySearch(int[] nums, int target, int l, int r) {
		if (l > r) {
			return -1;
		} else if (target == nums[(l + r) / 2]) {
			return (l + r) / 2;
		} else if (target > nums[(l + r) / 2]) {
			return binarySearch(nums, target, (l + r) / 2 + 1, r);
		} else {
			return binarySearch(nums, target, l, (l + r) / 2 - 1);
		}
	}
	
	
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l1;
		}
		if (l2 == null) {
			return l2;
		}
		if (l1.val > l2.val) {
			return mergeTwoLists(l2, l1);
		}
		ListNode l;
		l = l1;
		ListNode temp;
		while (l1 != null && l2 != null) {
			if (l1.next != null) {
				
				if (l1.next.val <= l2.val) {
					l1 = l1.next;
				} else {
					temp = l2;
					l2 = l2.next;
					temp.next = l1.next;
					l1.next = temp;
				}
			} else {
				l1.next = l2;
			}
		}
		return l;
	}
	
	public static int minCostClimbingStairs(int[] cost) {
		return Math
			.min(minCostClimbingStairsHelper(cost, 0), minCostClimbingStairsHelper(cost, 1));
	}
	
	private static int minCostClimbingStairsHelper(int[] cost, int i) {
		if (i < cost.length - 1) {
			return 0;
		}
		return cost[i] + Math
			.min(minCostClimbingStairsHelper(cost, i + 1),
				minCostClimbingStairsHelper(cost, i + 2));
	}
	
	int tilt;
	
	public int findTilt(TreeNode root) {
		findTiltHelper(root);
		return tilt;
	}
	
	public int findTiltHelper(TreeNode root) {
		if (root.left == null && root.right == null) {
			return root.val;
		}
		int l = 0;
		if (root.left != null) {
			l = findTiltHelper(root.left);
		}
		int r = 0;
		if (root.right != null) {
			r = findTiltHelper(root.right);
		}
		tilt += Math.abs(l - r);
		return root.val + l + r;
	}
	
	public static int maxProfit(int[] prices) {
		int max = Integer.MIN_VALUE;
		for (int price : prices) {
			max = Math.max(max, price);
		}
		int[] a = new int[max + 1];
		int[] b = new int[max + 1];
		for (int i = 0; i < prices.length; i++) {
			if (a[prices[i]] == 0) {
				b[prices[i]] = i + 1;
			}
			a[prices[i]] = i + 1;
		}
		int ret = 0, j;
		for (int i = 0; i < a.length; i++) {
			if (b[i] > 0) {
				j = a.length - 1;
				while (b[i] > a[j] && j > i) {
					j--;
				}
				ret = Math.max(ret, j - i);
			}
		}
		return ret;
	}
	
	public static List<List<Integer>> largeGroupPositions(String S) {
		List<List<Integer>> lists = new ArrayList<>();
		char[] chars = S.toCharArray();
		int l = 0;
		int same = 1;
		for (int i = 1; i < chars.length; i++) {
			if (chars[l] == chars[i]) {
				same++;
			} else {
				if (same > 2) {
					ArrayList<Integer> integers = new ArrayList<>();
					integers.add(l);
					integers.add(l + same - 1);
					lists.add(integers);
				}
				l = i;
				same = 1;
			}
		}
		if (same > 2) {
			ArrayList<Integer> integers = new ArrayList<>();
			integers.add(l);
			integers.add(l + same - 1);
			lists.add(integers);
		}
		return lists;
	}
	
	public int[] intersect(int[] nums1, int[] nums2) {
		if (nums1.length > nums2.length) {
			intersect(nums2, nums1);
		}
		int[] ints = new int[nums1.length];
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < nums1.length && j < nums2.length) {
			if (nums1[i] == nums2[j]) {
				ints[k++] = nums1[i];
				i++;
				j++;
			} else if (nums1[i] < nums2[j]) {
				i++;
			} else {
				j++;
			}
		}
		return Arrays.copyOf(ints, k);
	}
	
	public String[] findRestaurant(String[] list1, String[] list2) {
		if (list1.length < list2.length) {
			return findRestaurant(list2, list1);
		}
		HashMap<String, Integer> map = new HashMap<>();
		for (int i = 0; i < list1.length; i++) {
			map.put(list1[i], i);
		}
		int min = Integer.MAX_VALUE;
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < list2.length; i++) {
			Integer integer = map.get(list2[i]);
			if (integer != null) {
				if (i + integer < min) {
					list.clear();
					list.add(list2[i]);
					min = i + integer;
				} else if (i + integer == min) {
					list.add(list2[i]);
				}
			}
		}
		
		return list.toArray(new String[0]);
	}
	
	public int missingNumber(int[] nums) {
		int sum1 = nums.length - 1;
		int sum2 = 0;
		for (int i = 0; i < nums.length; i++) {
			sum1 += i;
			sum2 += nums[i];
		}
		return sum1 - sum2;
	}
	
	public static int longestPalindrome(String s) {
		int[] ints = new int['z' - 'A'];
		for (char c : s.toCharArray()) {
			ints[c - 'A']++;
		}
		boolean haveEven = false;
		int sum = 0;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] > 0) {
				if ((ints[i] & 1) == 1) {
					haveEven = true;
					sum--;
				}
				sum += ints[i];
				
			}
		}
		return haveEven ? sum + 1 : sum;
	}
	
	public String[] findRelativeRanks(int[] nums) {
		int max = Integer.MIN_VALUE;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		int[] ints = new int[max + 1];
		for (int i = 0; i < nums.length; i++) {
			ints[nums[i]] = i + 1;
		}
		String[] strings = new String[nums.length];
		int i = 1;
		for (int k = max; k >= 0; k--) {
			if (ints[k] > 0) {
				if (i > 3) {
					strings[ints[k] - 1] = String.valueOf(k);
				} else if (i == 3) {
					strings[ints[k] - 1] = "Bronze Medal";
				} else if (i == 2) {
					strings[ints[k] - 1] = "Silver Medal";
				} else if (i == 1) {
					strings[ints[k] - 1] = "Gold Medal";
				}
				i++;
			}
		}
		return strings;
	}
	
	public int maxCount(int m, int n, int[][] ops) {
		int minX = m;
		int minY = n;
		for (int i = 0; i < ops.length; i++) {
			if (ops[i][0] != 0 && ops[i][1] != 0) {
				minX = Math.min(minX, ops[i][0]);
				minY = Math.min(minY, ops[i][1]);
			}
		}
		return minX * minY;
	}
	
	public int[] constructRectangle(int area) {
		Double sqrt = Math.sqrt(area);
		int i = sqrt.intValue();
		for (int j = i; j < area; j++) {
			if (area % i == 0 && area / i < i) {
				return new int[]{i, area / i};
			}
		}
		return null;
	}
	
	public int findContentChildren(int[] g, int[] s) {
		Arrays.sort(g);
		Arrays.sort(s);
		int count = 0;
		for (int i = 0, j = 0; i < s.length && j < g.length; i++, j++) {
			while (i < s.length && s[i] < g[j]) {
				i++;
			}
			if (s[i] >= g[i]) {
				count++;
			}
		}
		return count;
	}
	
	public int findJudge(int N, int[][] trust) {
		int[][] ints = new int[N + 1][2];
		for (int[] t : trust) {
			ints[t[0]][0]++;
			ints[t[1]][1]++;
		}
		for (int i = 1; i < ints.length; i++) {
			if (ints[i][0] == 0 && ints[i][1] == (N - 1)) {
				return i;
			}
		}
		return -1;
	}
	
	public static int[][] imageSmoother(int[][] M) {
		int[] ints = new int[M.length * M[0].length];
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				ints[i * M[i].length + j] = M[i][j];
			}
		}
		int num;
		int sum;
		int temp;
		int index;
		for (int i = 0; i < M.length; i++) {
			for (int j = 0; j < M[i].length; j++) {
				index = i * M[i].length + j;
				num = 1;
				sum = ints[index];
				if (i > 0) {
					temp = index - M[i].length;
					sum += ints[temp];
					num++;
					if (j > 0) {
						temp = index - M[i].length - 1;
						sum += ints[temp];
						num++;
					}
					if (j < M[i].length - 1) {
						temp = index - M[i].length + 1;
						sum += ints[temp];
						num++;
					}
				}
				if (i < M.length - 1) {
					temp = index + M[i].length;
					sum += ints[temp];
					num++;
					if (j > 0) {
						temp = index + M[i].length - 1;
						sum += ints[temp];
						num++;
					}
					if (j < M[i].length - 1) {
						temp = index + M[i].length + 1;
						sum += ints[temp];
						num++;
					}
				}
				if (j > 0) {
					temp = index - 1;
					sum += ints[temp];
					num++;
				}
				if (j < M[i].length - 1) {
					temp = index + 1;
					sum += ints[temp];
					num++;
				}
				M[i][j] = sum / num;
			}
		}
		return M;
	}
}
