package com.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test3
 * @Author: WuMing
 * @CreateDate: 2019/4/19 21:10
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test3 {
	
	public static void main(String[] args) {
		TreeNode treeNode = new TreeNode(5);
		treeNode.right = new TreeNode(5);
		treeNode.right.left = new TreeNode(5);
		treeNode.left = new TreeNode(4);
		treeNode.left.left = new TreeNode(1);
		treeNode.left.right = new TreeNode(1);
		
		/*treeNode.right.right = new TreeNode(1);
		treeNode.right.left = new TreeNode(1);
		treeNode.right.left.left = new TreeNode(1);
		treeNode.right.left.right = new TreeNode(1);*/
		//longestUnivaluePath(treeNode);
		
		//longestCommonPrefix(new String[]{"flower", "floweer", "flowerht"});
		//reachNumber(5);
		/*robotSim(new int[]{7, -2, -2, 7, 5},
			new int[][]{{-3, 2}, {-2, 1}, {0, 1}, {-2, 4}, {-1, 0}, {-2, -3}, {0, -3}, {4, 4},
				{-3, 3}, {2, 2}});*/
		//mySqrt(2);
		//findNthDigit(2147483647);
		//findUnsortedSubarray(new int[]{1, 2, 4, 5, 3});
		//checkPossibility(new int[]{4, 2, 3});
		//rotate(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 4);
		//findPairs(new int[]{-1, 0, 1, -2, 0}, 2);
		//countPrimes(17);
		//removeOuterParentheses("(()())(())");
		/*twoCitySchedCost(
			new int[][]{{33, 135}, {849, 791}, {422, 469}, {310, 92}, {253, 489}, {995, 760},
				{852, 197}, {658, 216}, {679, 945}, {197, 341}, {362, 648}, {22, 324}, {408, 25},
				{505, 734}, {463, 279}, {885, 512}, {922, 850}, {784, 500}, {557, 860}, {528, 367},
				{877, 741}, {554, 545}, {598, 888}, {558, 104}, {426, 427}, {449, 189}, {113, 51},
				{201, 221}, {251, 62}, {981, 897}, {392, 519}, {115, 70}, {961, 109}, {512, 678},
				{476, 708}, {28, 902}, {763, 282}, {787, 774}, {925, 475}, {253, 532}, {100, 502},
				{110, 857}, {822, 942}, {231, 186}, {869, 491}, {651, 344}, {239, 806}, {651, 193},
				{830, 360}, {427, 69}, {776, 875}, {466, 81}, {520, 959}, {798, 775}, {875, 199},
				{110, 396}}
		);*/
		/*findAndReplacePattern(
			new String[]{"juaxv", "mclpg", "muxov", "cwxev", "wwnpf", "skadk", "xoxyc", "pinlm",
				"cvmqm", "eoewv"}, "qzcrp");*/
		//partitionLabels("ababcbacadefegdehijhklij");
		//sortArray(new int[]{5, 3, 7, 2, 6});
		//matrixScore(new int[][]{{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}});
		//complexNumberMultiply("6+-6i", "1+1i");
		//spiralMatrixIII(1, 4, 0, 0);
		//queryString("0110", 4);
		//regionsBySlashes(new String[]{"/\\", "\\/"});
		//pancakeSort(new int[]{3, 2, 4, 1});
		//String s = customSortString1("cba", "abcd");
		//System.out.println(s);
		//stoneGame1(new int[]{3,2,10,4});
		//findDuplicates(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
		ArrayList<Integer> integers = new ArrayList<>();
		ArrayList<Integer> integers1 = new ArrayList<>();
		ArrayList<Integer> integers2 = new ArrayList<>();
		ArrayList<Integer> integers3 = new ArrayList<>();
		ArrayList<List<Integer>> lists = new ArrayList<>();
		lists.add(integers);
		lists.add(integers1);
		lists.add(integers2);
		lists.add(integers3);
		integers.add(1);
		integers1.add(2);
		integers2.add(3);
		//canVisitAllRooms(lists);
		//dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
		//reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
		//validateStackSequences(new int[]{8, 9, 2, 3, 7, 0, 5, 4, 6, 1},
		//	new int[]{6, 8, 2, 1, 3, 9, 0, 7, 4, 5});
		//mincostTickets(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 31}, new int[]{2, 7, 15});
		/*camelMatch(new String[]{"FooBar", "FooBarTest", "FootBall", "FrameBuffer", "ForceFeedBack"},
			"FB");*/
		//maxAreaOfIsland(new int[][]{{0,0,1,1},{1,0,1,0},{1,1,0,0}});
		//singleNumber1(new int[]{1, 2, 1, 4, 3, 3, 2, 5});
		//frequencySort("treeeww");
		//numberOfArithmeticSlices(new int[]{1, 2, 3, 4});
		//baseNeg2(77);
		System.out.println(Integer.toBinaryString(1431655765));
		maxSumTwoNoOverlap(new int[]{1, 0, 3}, 1, 2);
	}
	
	public TreeNode subtreeWithAllDeepest(TreeNode root) {
		int l = subtreeWithAllDeepestHelper(root.left, 0);
		int r = subtreeWithAllDeepestHelper(root.right, 0);
		if (l == r) {
			return root;
		} else if (l > r) {
			return subtreeWithAllDeepest(root.left);
		} else {
			return subtreeWithAllDeepest(root.right);
		}
	}
	
	public String optimalDivision(int[] nums) {
		if (nums.length == 2) {
			return nums[0] + "/" + nums[1];
		}else{
			StringBuffer buffer = new StringBuffer();
			buffer.append(nums[0]).append("/(").append(nums[1]);
			for (int i = 2; i <nums.length ; i++) {
				buffer.append("/").append(nums[2]);
			}
			buffer.append(")");
			return buffer.toString();
		}
		
	}
	
	
	
	
	private int subtreeWithAllDeepestHelper(TreeNode root, int i) {
		if (root == null) {
			return i;
		} else {
			return Math.max(subtreeWithAllDeepestHelper(root.left, i + 1),
				subtreeWithAllDeepestHelper(root.right, i + 1));
		}
	}
	
	public static int maxSumTwoNoOverlap(int[] A, int L, int M) {
		if (L > M) {
			return maxSumTwoNoOverlap(A, M, L);
		}
		int[] ls = new int[A.length - L + 1];
		int[] ms = new int[A.length - M + 1];
		for (int i = 0; i < M; i++) {
			if (i < L) {
				ls[0] += A[i];
			} else {
				ms[0] += A[i];
			}
		}
		ms[0] += ls[0];
		for (int i = 0; i < ls.length - 1; i++) {
			ls[i + 1] = ls[i] + A[i + L] - A[i];
			if (i < ms.length - 1) {
				ms[i + 1] = ms[i] + A[i + M] - A[i];
			}
		}
		int max = 0;
		for (int i = 0; i < ls.length; i++) {
			for (int j = 0; j < ms.length; j++) {
				if (j > i + L || j + M < i) {
					max = Math.max(max, ls[i] + ms[j]);
				}
			}
		}
		return max;
	}
	
	public static String baseNeg2(int N) {
		return Integer.toBinaryString(1431655765 ^ (1431655765 - N));
	}
	
	public static int numberOfArithmeticSlices(int[] A) {
		if (A.length < 3) {
			return 0;
		}
		return numberOfArithmeticSlicesHelper(A, 0, A.length);
	}
	
	private static int numberOfArithmeticSlicesHelper(int[] a, int l, int length) {
		if (l + 2 < length) {
			int x = a[l + 1] - a[l];
			if (x == a[l + 2] - a[l + 1]) {
				int sum = 0;
				int count = 1;
				int i = l + 3;
				for (; i < length; i++) {
					if (a[i] - a[i - 1] == x) {
						count++;
					} else {
						break;
					}
				}
				for (int j = 1; j <= count; j++) {
					sum += j;
				}
				return sum + numberOfArithmeticSlicesHelper(a, i - 1, length);
			} else {
				return numberOfArithmeticSlicesHelper(a, l + 1, length);
			}
		} else {
			return 0;
		}
	}
	
	public static String frequencySort(String s) {
		if (s.length() < 2) {
			return s;
		}
		char[] array = s.toCharArray();
		int[] counter = new int[128];
		for (char c : array) {
			counter[c]++;
		}
		StringBuffer buffer = new StringBuffer();
		int max = -1;
		
		while (true) {
			max = 0;
			int i = 0;
			for (; i < counter.length; i++) {
				if (counter[i] > counter[max]) {
					max = i;
				}
			}
			if (counter[max] == 0) {
				break;
			}
			char c = (char) max;
			for (int j = 0; j < counter[max]; j++) {
				buffer.append(c);
			}
			counter[max] = 0;
			
		}
		return buffer.toString();
	}
	
	public int scoreOfParentheses(String S) {
		S = '(' + S + ')';
		return scoreOfParenthesesHelper(S.toCharArray(), 0, S.length() - 1);
	}
	
	private int scoreOfParenthesesHelper(char[] chars, int l, int r) {
		if (r - l == 1) {
			return 1;
		} else {
			int count = 0, sum = 0, begin = l + 1;
			for (int i = l + 1; i < r; i++) {
				if (chars[i] == '(') {
					count++;
				} else {
					count--;
					if (count == 0) {
						sum += scoreOfParenthesesHelper(chars, begin, i);
						begin = i + 1;
					}
				}
			}
			return 2 * sum;
		}
	}
	
	public List<Integer> inorderTraversal(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		inorderTraversalHelper(root, list);
		return list;
	}
	
	int[] nextLarger;
	
	public int[] nextLargerNodes1(ListNode head) {
		
		extLargerNodesHelper(head, 0);
		return nextLarger;
	}
	
	public int[] nextLargerNodes2(ListNode head) {
		ArrayList<Integer> list = new ArrayList<>();
		while (head != null) {
			list.add(head.val);
			head = head.next;
		}
		int[] ret = new int[list.size()];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < list.size(); i++) {
			while (!stack.empty() && list.get(i) > list.get(stack.peek())) {
				ret[stack.pop()] = list.get(i);
			}
			stack.push(i);
		}
		return ret;
	}
	
	private ListNode extLargerNodesHelper(ListNode head, int index) {
		if (head == null) {
			nextLarger = new int[index];
			return null;
		}
		ListNode temp = extLargerNodesHelper(head.next, index + 1);
		while (temp != null && head.val >= temp.val) {
			temp = temp.next;
		}
		nextLarger[index] = (temp == null ? 0 : temp.val);
		head.next = temp;
		return head;
	}
	
	public List<Integer> inorderTraversal1(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curr = root;
		while (curr != null || !stack.isEmpty()) {
			while (curr != null) {
				stack.push(curr);
				curr = curr.left;
			}
			curr = stack.pop();
			res.add(curr.val);
			curr = curr.right;
		}
		return res;
		
	}
	
	private void inorderTraversalHelper(TreeNode root, ArrayList<Integer> list) {
		if (root == null) {
			return;
		}
		list.add(root.val);
		if (root.left != null) {
			inorderTraversalHelper(root.left, list);
		}
		if (root.right != null) {
			inorderTraversalHelper(root.right, list);
		}
	}
	
	public static int countSubstrings1(String s) {
		char[] array = s.toCharArray();
		int sum = 1;
		for (int i = 1; i < array.length; i++) {
			sum++;
			int l = i - 1, r = i;
			while (l < r && l >= 0 && r < array.length && array[l] == array[r]) {
				sum++;
				l--;
				r++;
			}
			l = i - 1;
			r = i + 1;
			while (l < r && l >= 0 && r < array.length && array[l] == array[r]) {
				sum++;
				l--;
				r++;
			}
		}
		return sum;
	}
	
	public static int countSubstrings(String s) {
		return countSubstringsHelper(s.toCharArray(), 0);
	}
	
	private static int countSubstringsHelper(char[] chars, int i) {
		if (i < chars.length) {
			int k = 1;
			for (int j = i + 1; j < chars.length; j++) {
				int l = i, r = j;
				while (l < r) {
					if (chars[l] == chars[r]) {
						l++;
						r--;
					} else {
						break;
					}
				}
				if (l >= r) {
					k++;
				}
			}
			return k + countSubstringsHelper(chars, i + 1);
		}
		return 0;
	}
	
	public static int maxAreaOfIsland(int[][] grid) {
		int maxArea = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					maxArea = Math.max(maxArea, maxAreaOfIslandHelper(grid, i, j));
				}
			}
		}
		return maxArea;
	}
	
	public static int[] singleNumber1(int[] nums) {
		int a = 0;
		for (int num : nums) {
			a ^= num;
		}
		a = Integer.highestOneBit(a);
		int[] ints = new int[2];
		for (int num : nums) {
			if ((a & num) == 0) {
				ints[0] ^= num;
			}
		}
		ints[1] = a ^ ints[0];
		return ints;
	}
	
	public int[] singleNumber(int[] nums) {
		HashSet<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num)) {
				set.remove(num);
			} else {
				set.add(num);
			}
		}
		int[] ints = new int[2];
		int i = 0;
		for (Integer integer : set) {
			ints[i++] = integer;
		}
		return ints;
	}
	
	private static int maxAreaOfIslandHelper(int[][] grid, int i, int j) {
		int k = 0;
		if (grid[i][j] == 1) {
			k += 1;
			grid[i][j] = 0;
			if (i > 0 && grid[i - 1][j] == 1) {
				k += maxAreaOfIslandHelper(grid, i - 1, j);
			}
			if (i < grid.length - 1 && grid[i + 1][j] == 1) {
				k += maxAreaOfIslandHelper(grid, i + 1, j);
			}
			if (j > 0 && grid[i][j - 1] == 1) {
				k += maxAreaOfIslandHelper(grid, i, j - 1);
			}
			if (j < grid[i].length - 1 && grid[i][j + 1] == 1) {
				k += maxAreaOfIslandHelper(grid, i, j + 1);
			}
		}
		return k;
	}
	
	public static List<Boolean> camelMatch(String[] queries, String pattern) {
		ArrayList<Boolean> booleans = new ArrayList<>();
		for (String query : queries) {
			int q = 0;
			int p = 0;
			while (q < query.length() && p < pattern.length()) {
				char c = query.charAt(q);
				if (c == pattern.charAt(p)) {
					p++;
				} else if (c > 'A' && c < 'Z') {
					break;
				}
				q++;
			}
			if (p < pattern.length()) {
				booleans.add(false);
			} else {
				while (q < query.length()) {
					char c = query.charAt(q);
					if (c > 'A' && c < 'Z') {
						break;
					}
					q++;
				}
				if (q < query.length()) {
					booleans.add(false);
				} else {
					booleans.add(true);
				}
			}
		}
		return booleans;
	}
	
	public static int mincostTickets(int[] days, int[] costs) {
		int[] cost = new int[366];
		int index = 0;
		int day = 1;
		while (index < days.length) {
			if (day != days[index]) {
				cost[day] = cost[day - 1];
				day++;
				continue;
			} else {
				cost[day] = cost[day - 1] + costs[0];
				cost[day] = Math.min(cost[day - 1] + costs[0],
					Math.min(cost[day - 7 > 0 ? day - 7 : 0] + costs[1],
						cost[day - 30 > 0 ? day - 30 : 0] + costs[2]));
				index++;
				day++;
			}
		}
		return cost[day - 1];
	}
	
	public int singleNonDuplicate(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}
		for (int i = 0; i < nums.length - 1; i += 2) {
			if (nums[i] != nums[i + 1]) {
				return nums[i];
			}
		}
		return nums[nums.length - 1];
	}
	
	public List<Integer> largestValues(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		largestValuesHelper(root, list, 1);
		return list;
	}
	
	private void largestValuesHelper(TreeNode root, ArrayList<Integer> list, int level) {
		if (root == null) {
			return;
		}
		if (list.size() < level) {
			list.add(root.val);
		} else {
			list.set(level - 1, Math.max(list.get(level - 1), root.val));
		}
		largestValuesHelper(root.left, list, level + 1);
		largestValuesHelper(root.right, list, level + 1);
	}
	
	public static boolean validateStackSequences(int[] pushed, int[] popped) {
		int[] stack = new int[popped.length];
		int push = 0;
		int index = 0;
		int pop = 0;
		while (pop < popped.length) {
			while (push < pushed.length && pushed[push] != popped[pop]) {
				stack[index] = pushed[push];
				index++;
				push++;
			}
			if (push < pushed.length) {
				push++;
			}
			pop++;
			while (index > 0 && stack[index - 1] == popped[pop]) {
				index--;
				pop++;
			}
			if (push == pushed.length && pop != popped.length) {
				return false;
			}
		}
		return true;
	}
	
	int maxAncestor;
	
	public int maxAncestorDiff(TreeNode root) {
		if (root == null) {
			return 0;
		}
		maxAncestorDiffHelper(root.left, root.val, root.val);
		maxAncestorDiffHelper(root.right, root.val, root.val);
		return maxAncestor;
	}
	
	private void maxAncestorDiffHelper(TreeNode root, int min, int max) {
		if (root == null) {
			return;
		}
		if (root.val > max) {
			max = root.val;
		} else if (root.val < min) {
			min = root.val;
		}
		maxAncestor = Math.max(maxAncestor, max - root.val);
		maxAncestor = Math.max(maxAncestor, root.val - min);
		maxAncestorDiffHelper(root.left, min, max);
		maxAncestorDiffHelper(root.right, min, max);
	}
	
	int bottomLeftValue;
	int bottomLeftValueLevel;
	
	public int findBottomLeftValue(TreeNode root) {
		bottomLeftValue = root.val;
		bottomLeftValueLevel = 0;
		findBottomLeftValueHelper(root.left, 1);
		findBottomLeftValueHelper(root.right, 1);
		return bottomLeftValue;
	}
	
	private void findBottomLeftValueHelper(TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (root.left == null) {
			if (level > bottomLeftValueLevel) {
				bottomLeftValueLevel = level;
				bottomLeftValue = root.val;
			}
		}
		findBottomLeftValueHelper(root.left, level + 1);
		findBottomLeftValueHelper(root.right, level + 1);
		
	}
	
	public int minFallingPathSum(int[][] A) {
		int[][] min = new int[A[0].length][2];
		for (int i = 0; i < A[0].length; i++) {
			min[i][1] = A[0][i];
			min[i][0] = A[0][i];
		}
		for (int i = 1; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				int l = j > 0 ? min[j - 1][0] : Integer.MAX_VALUE;
				int m = min[j][0];
				int r = j < A[i].length - 1 ? min[j + 1][0] : Integer.MAX_VALUE;
				min[j][1] = A[i][j] + Math.min(l, Math.min(m, r));
			}
			for (int[] ints : min) {
				ints[0] = ints[1];
			}
		}
		int mins = Integer.MAX_VALUE;
		for (int[] ints : min) {
			if (ints[1] < mins) {
				mins = ints[1];
			}
		}
		return mins;
	}
	
	public static int[][] reconstructQueue(int[][] people) {
		Arrays.sort(people, (a, b) -> (a[0] - b[0]));
		int[][] ret = new int[people.length][2];
		int[] ints = new int[people.length];
		for (int[] person : people) {
			int index = person[1];
			int h = person[0];
			int i = 0;
			for (; i < ret.length; i++) {
				if (ints[i] == 0 || ret[i][0] == h) {
					if (index == 0) {
						break;
					}
					index--;
				}
			}
			if (i < ret.length) {
				ret[i] = person;
				ints[i] = 1;
			}
		}
		return ret;
	}
	
	
	public static int[] dailyTemperatures(int[] T) {
		int max = 0;
		int[] ints = new int[101];
		int[] ret = new int[T.length];
		for (int i = T.length - 1; i >= 0; i--) {
			int temp = T[i];
			if (temp > max) {
				max = temp;
			}
			ints[temp] = i;
			temp++;
			int min = T.length;
			while (temp < max) {
				if (ints[temp] > 0) {
					min = Math.min(min, ints[temp]);
				}
				temp++;
			}
			if (min < T.length) {
				ret[i] = min - i;
			} else {
				ret[i] = 0;
			}
		}
		return ret;
	}
	
	public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
		boolean[] keys = new boolean[rooms.size()];
		canVisitAllRoomsHelper(0, rooms, keys);
		for (boolean key : keys) {
			if (!key) {
				return false;
			}
		}
		return true;
	}
	
	private static void canVisitAllRoomsHelper(int i, List<List<Integer>> rooms, boolean[] keys) {
		keys[i] = true;
		for (Integer integer : rooms.get(i)) {
			if (!keys[integer]) {
				canVisitAllRoomsHelper(integer, rooms, keys);
			}
		}
	}
	
	public static List<Integer> findDuplicates(int[] nums) {
		ArrayList<Integer> integers = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0 && i + 1 != nums[i]) {
				int temp = nums[i];
				nums[i] = 0;
				while (temp != 0 && nums[temp - 1] != temp) {
					int k = nums[temp - 1];
					nums[temp - 1] = temp;
					temp = k;
				}
				if (temp != 0) {
					integers.add(temp);
				}
			}
		}
		return integers;
	}
	
	public static String customSortString1(String S, String T) {
		int[] count = new int[26];
		for (char c : T.toCharArray()) {
			count[c - 'a']++;
		}
		char[] chars = new char[T.length()];
		int index = 0;
		for (char c : S.toCharArray()) {
			while (count[c - 'a'] > 0) {
				chars[index++] = c;
				count[c - 'a']--;
			}
		}
		for (int j = 0; j < count.length; j++) {
			while (count[j] > 0) {
				chars[index++] = (char) (j + 'a');
				count[j]--;
			}
			
		}
		return new String(chars);
	}
	
	public boolean stoneGame(int[] piles) {
		int sum = 0;
		for (int pile : piles) {
			sum += pile;
		}
		int win = sum / 2;
		return stoneGameHelper(piles, 0, piles.length, win, 0);
	}
	
	private boolean stoneGameHelper(int[] piles, int l, int r, int win, int sum) {
		if (l < r) {
			if (win < sum) {
				return true;
			} else {
				return stoneGameHelper(piles, l + 1, r, win, sum + piles[l]) || stoneGameHelper(
					piles, l, r - 1, win, sum + piles[r]);
			}
		} else {
			return win < sum;
		}
	}
	
	public static String customSortString(String S, String T) {
		
		int[] ints = new int[26];
		int[] count = new int[26];
		int i = 1;
		for (char c : S.toCharArray()) {
			ints[c - 'a'] = i++;
		}
		for (char c : T.toCharArray()) {
			count[c - 'a']++;
		}
		StringBuffer buffer = new StringBuffer();
		
		for (int j = 1; j <= S.length(); j++) {
			i = 0;
			while (i < ints.length && ints[i] != j) {
				i++;
			}
			while (i < ints.length && count[i] > 0) {
				
				buffer.append((char) (i + 'a'));
				count[i]--;
			}
		}
		for (int j = 0; j < count.length; j++) {
			
			while (count[j] > 0) {
				buffer.append((char) (j + 'a'));
				count[j]--;
			}
			
		}
		return buffer.toString();
	}
	
	public static List<Integer> pancakeSort(int[] A) {
		ArrayList<Integer> list = new ArrayList<>();
		return pancakeSortHelper(A, A.length, list);
	}
	
	private static List<Integer> pancakeSortHelper(int[] a, int length, ArrayList<Integer> list) {
		if (length == 1) {
			return list;
		}
		int max = 0;
		for (int i = 0; i < length; i++) {
			if (a[max] < a[i]) {
				max = i;
			}
		}
		if (max == length - 1) {
			return pancakeSortHelper(a, length - 1, list);
		} else if (max == 0) {
			list.add(length);
			int len = length - 1;
			while (max < len) {
				int temp = a[len];
				a[len] = a[max];
				a[max] = temp;
				max++;
				len--;
			}
			return pancakeSortHelper(a, length - 1, list);
		} else {
			int temp;
			int i = 0;
			list.add(max + 1);
			while (i < max) {
				temp = a[i];
				a[i] = a[max];
				a[max] = temp;
				i++;
				max--;
			}
			list.add(length);
			max = length - 1;
			i = 0;
			while (i < max) {
				temp = a[i];
				a[i] = a[max];
				a[max] = temp;
				i++;
				max--;
			}
			return pancakeSortHelper(a, length - 1, list);
		}
	}
	
	
	public static TreeNode insertIntoMaxTree(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		}
		if (root.val < val) {
			TreeNode tree = new TreeNode(val);
			tree.left = root;
			return tree;
		} else {
			root.right = insertIntoMaxTree(root.right, val);
			return root;
		}
	}
	
	public static int regionsBySlashes(String[] grid) {
		int len = grid[0].length();
		int[][] ints = new int[len * 3][len * 3];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length(); j++) {
				char c = grid[i].charAt(j);
				if (c == '\\') {
					ints[i * 3 + 2][j * 3 + 2] = 1;
					ints[i * 3 + 1][j * 3 + 1] = 1;
					ints[i * 3][j * 3] = 1;
				} else if (c == '/') {
					ints[i * 3][j * 3 + 2] = 1;
					ints[i * 3 + 1][j * 3 + 1] = 1;
					ints[i * 3 + 2][j * 3] = 1;
				}
			}
		}
		int count = 0;
		for (int i = 0; i < ints.length; i++) {
			for (int j = 0; j < ints[i].length; j++) {
				if (ints[i][j] == 0) {
					regionsBySlashesHelper(ints, i, j);
					count++;
				}
			}
		}
		return count;
	}
	
	private static void regionsBySlashesHelper(int[][] ints, int i, int j) {
		ints[i][j] = 1;
		if (j < ints.length - 1 && ints[i][j + 1] == 0) {
			regionsBySlashesHelper(ints, i, j + 1);
		}
		if (j > 0 && ints[i][j - 1] == 0) {
			regionsBySlashesHelper(ints, i, j - 1);
		}
		if (i > 0 && ints[i - 1][j] == 0) {
			regionsBySlashesHelper(ints, i - 1, j);
		}
		if (i < ints.length - 1 && ints[i + 1][j] == 0) {
			regionsBySlashesHelper(ints, i + 1, j);
		}
		
	}
	
	public static boolean queryString(String S, int N) {
		int l = 1;
		int r = N / 2;
		while (l < r) {
			l <<= 1;
		}
		while (l <= N) {
			String s = Integer.toBinaryString(l);
			if (S.indexOf(s) < 0) {
				return false;
			}
			l++;
		}
		return true;
	}
	
	public static int[][] intervalIntersection(int[][] A, int[][] B) {
		int ai = 0;
		int bi = 0;
		int l, h;
		ArrayList<int[]> ret = new ArrayList<>();
		while (ai < A.length && bi < B.length) {
			l = Math.max(A[ai][0], B[bi][0]);
			h = Math.min(A[ai][1], B[bi][1]);
			if (l <= h) {
				ret.add(new int[]{l, h});
			}
			if (h == A[ai][1]) {
				ai++;
			} else {
				bi++;
			}
		}
		return ret.toArray(new int[0][]);
	}
	
	public int[][] kClosest(int[][] points, int K) {
		Arrays.sort(points, (a, b) -> (a[0] * a[0] + a[1] * a[1] - b[0] * b[0] - b[1] * b[1]));
		return Arrays.copyOfRange(points, 0, K);
	}
	
	public static int[][] spiralMatrixIII(int R, int C, int r0, int c0) {
		int total = R * C;
		int[][] ints = new int[total][2];
		int i = 0;
		int direct = 0;//0right;1down;2left;3up
		int len = 1;
		int moved = 0;
		int[] dx = {1, 0, -1, 0};
		int[] dy = {0, 1, 0, -1};
		while (i < total) {
			if (r0 >= 0 && r0 < R && c0 >= 0 && c0 < C) {
				ints[i][0] = r0;
				ints[i][1] = c0;
				i++;
			}
			c0 += dx[direct];
			r0 += dy[direct];
			moved++;
			if (moved == len) {
				direct++;
				moved = 0;
				direct %= 4;
				if (direct == 2 || direct == 0) {
					len++;
				}
			}
		}
		return ints;
	}
	
	
	public int[] countBits(int num) {
		int[] ints = new int[num + 1];
		for (int i = 1; i < num + 1; i++) {
			ints[i] = ints[i / 2] + (i & 1);
		}
		return ints;
	}
	
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		} else if (root1 == null || root2 == null) {
			return false;
		}
		if (root1.val == root2.val) {
			return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
				|| flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
		} else {
			return false;
		}
	}
	
	public static String complexNumberMultiply(String a, String b) {
		int l1, l2, r1, r2;
		String[] split = a.split("\\+");
		String[] split1 = a.split("\\+");
		l1 = Integer.valueOf(split[0]);
		r1 = Integer.valueOf(split1[0]);
		l2 = Integer.valueOf(split[1].substring(0, split[1].length() - 1));
		r2 = Integer.valueOf(split1[1].substring(0, split1[1].length() - 1));
		
		return (l1 * r1 - (l2 * r2)) + "+" + (l1 * r2 + l2 * r1) + "i";
	}
	
	public int countBattleships(char[][] board) {
		int count = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == 'X') {
					if (i > 0 && j > 0) {
						if (board[i - 1][j] == '.' && board[i][j - 1] == '.') {
							count++;
						}
					} else if (i == 0 && j == 0) {
						count++;
					} else if (i == 0) {
						if (board[i][j - 1] == '.') {
							count++;
						}
					} else {
						if (board[i - 1][j] == '.') {
							count++;
						}
					}
				}
			}
		}
		return count;
	}
	
	int distribute;
	
	public int distributeCoins(TreeNode root) {
		int count = distributeCoinsHelper(root);
		return distribute;
	}
	
	private int distributeCoinsHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int l = distributeCoinsHelper(root.left);
		int r = distributeCoinsHelper(root.right);
		distribute += Math.abs(l) + Math.abs(r);
		return l + r + root.val - 1;
	}
	
	public static int matrixScore(int[][] A) {
		int k = A.length;
		int[] ints = new int[A[0].length];
		ints[0] = k;
		for (int i = 0; i < A.length; i++) {
			if (A[i][0] != 1) {
				for (int j = 0; j < A[i].length; j++) {
					A[i][j] = A[i][j] ^ 1;
				}
			}
		}
		for (int j = 1; j < A[0].length; j++) {
			int num = 0;
			for (int i = 0; i < A.length; i++) {
				num += A[i][j];
			}
			ints[j] = Math.max(num, k - num);
		}
		int total = 0;
		int temp = 1;
		for (int i = ints.length - 1; i >= 0; i--) {
			total += (temp * ints[i]);
			temp <<= 1;
		}
		return total;
	}
	
	public static int[] sortArray1(int[] nums) {
		int[] ints = new int[100001];
		for (int num : nums) {
			ints[num + 50000]++;
		}
		
		int index = 0;
		for (int i = 0; i < ints.length; i++) {
			while (ints[i] > 0) {
				nums[index++] = i - 50000;
				ints[i]--;
			}
		}
		return nums;
	}
	
	public static int[] sortArray(int[] nums) {
		sortArrayHelper(nums, 0, nums.length - 1);
		return nums;
	}
	
	private static void sortArrayHelper(int[] nums, int l, int r) {
		if (l > r) {
			return;
		}
		int low = l;
		int high = r;
		int mid = nums[l];
		while (l < r) {
			while (l < r && mid <= nums[r]) {
				r--;
			}
			if (l < r) {
				nums[l] = nums[r];
				l++;
			}
			while (l < r && mid >= nums[l]) {
				l++;
			}
			if (l < r) {
				nums[r] = nums[l];
				r--;
			}
		}
		nums[l] = mid;
		sortArrayHelper(nums, low, l - 1);
		sortArrayHelper(nums, l + 1, high);
		
	}
	
	public int minAddToMakeValid(String S) {
		int l = 0;
		int ret = 0;
		char[] array = S.toCharArray();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == ')') {
				if (l > 0) {
					l--;
				} else {
					ret++;
				}
			} else {
				l++;
			}
		}
		return ret + l;
	}
	
	public static List<Integer> partitionLabels(String S) {
		if (S.length() < 1) {
			return new ArrayList<>();
		}
		char[] s = S.toCharArray();
		char c = s[0];
		int k = 1;
		ArrayList<Integer> integers = new ArrayList<>();
		for (int i = 1; i < S.length(); i++) {
			int index = S.lastIndexOf(c);
			while (i <= index) {
				c = s[i];
				if (index < S.lastIndexOf(c)) {
					index = S.lastIndexOf(c);
				}
				k++;
				i++;
			}
			if (i < S.length()) {
				integers.add(k);
				c = s[i];
				k = 1;
			}
		}
		integers.add(k);
		return integers;
	}
	
	public List<List<Integer>> allPathsSourceTarget1(int[][] graph) {
		ArrayList<List<Integer>> lists = new ArrayList<>();
		ArrayList<Integer> path = new ArrayList<>();
		path.add(0);
		allPathsSourceTarget1Helper(graph, lists, path, 0);
		return lists;
	}
	
	private void allPathsSourceTarget1Helper(int[][] graph,
		ArrayList<List<Integer>> lists, ArrayList<Integer> path, int i) {
		path.add(i);
		if (graph[i].length < 1) {
			lists.add(new ArrayList<>(path));
		}
		for (int j = 0; j < graph[i].length; j++) {
			allPathsSourceTarget1Helper(graph, lists, path, graph[i][j]);
		}
		path.remove(path.size() - 1);
	}
	
	public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
		ArrayList<List<Integer>> lists = new ArrayList<>();
		List<List<Integer>> next = allPathsSourceTargetHelper(graph, 0);
		for (List<Integer> integers : next) {
			ArrayList<Integer> list = new ArrayList<>();
			list.add(0);
			list.addAll(integers);
			lists.add(list);
		}
		return lists;
	}
	
	HashMap<Integer, List<List<Integer>>> allPathsSource = new HashMap();
	
	private List<List<Integer>> allPathsSourceTargetHelper(int[][] graph, int i) {
		
		ArrayList<List<Integer>> lists = new ArrayList<>();
		if (graph[i].length < 1) {
			return lists;
		}
		if (allPathsSource.get(i) != null) {
			return allPathsSource.get(i);
		}
		for (int j = 0; j < graph[i].length; j++) {
			List<List<Integer>> next = allPathsSourceTargetHelper(graph, graph[i][j]);
			if (next.size() > 0) {
				for (List<Integer> integers : next) {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(graph[i][j]);
					list.addAll(integers);
					lists.add(list);
				}
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				list.add(graph[i][j]);
				lists.add(list);
			}
		}
		allPathsSource.put(i, lists);
		return lists;
	}
	
	Map<Integer, List<TreeNode>> allPossibleMap = new HashMap<>();
	
	public List<TreeNode> allPossibleFBT(int N) {
		ArrayList<TreeNode> list = new ArrayList<>();
		if ((N & 1) == 0) {
			return list;
		}
		if (allPossibleMap.get(N) != null) {
			return allPossibleMap.get(N);
		}
		if (N == 1) {
			list.add(new TreeNode(0));
		} else {
			for (int i = 1; i < N - 1; i += 2) {
				List<TreeNode> left;
				List<TreeNode> right;
				left = allPossibleFBT(i);
				right = allPossibleFBT(N - 1 - i);
				if (left.size() > 0 && right.size() > 0) {
					for (int k = 0; k < left.size(); k++) {
						for (int j = 0; j < right.size(); j++) {
							TreeNode treeNode = new TreeNode(0);
							treeNode.left = left.get(k);
							treeNode.right = right.get(j);
							list.add(treeNode);
						}
					}
				}
			}
		}
		allPossibleMap.put(N, list);
		return list;
	}
	
	public TreeNode pruneTree(TreeNode root) {
		int i = pruneTreeHelper(root);
		if (i == 0) {
			return null;
		} else {
			return root;
		}
	}
	
	private int pruneTreeHelper(TreeNode root) {
		if (root.left == null && root.right == null) {
			return root.val;
		}
		int l = 0, r = 0;
		if (root.left != null) {
			l += pruneTreeHelper(root.left);
			if (l == 0) {
				root.left = null;
			}
		}
		if (root.right != null) {
			r += pruneTreeHelper(root.right);
			if (r == 0) {
				root.right = null;
			}
		}
		return root.val + l + r;
	}
	
	public static List<String> findAndReplacePattern(String[] words, String pattern) {
		
		char[] pat = pattern.toCharArray();
		ArrayList<String> list = new ArrayList<>();
		for (String word : words) {
			if (word.length() == pattern.length()) {
				int[] mapper = new int[26];
				int[] used = new int[26];
				char[] wd = word.toCharArray();
				int i = 0;
				for (; i < wd.length; i++) {
					if (mapper[pat[i] - 'a'] == 0 && used[wd[i] - 'a'] == 0) {
						mapper[pat[i] - 'a'] = wd[i];
						used[wd[i] - 'a'] = 1;
					} else if (mapper[pat[i] - 'a'] != wd[i]) {
						break;
					}
				}
				if (i == wd.length) {
					list.add(word);
				}
			}
		}
		return list;
	}
	
	class linkNode {
		
		int val;
		linkNode left;
		linkNode right;
		
		public linkNode(int val) {
			this.val = val;
		}
	}
	
	public int[] deckRevealedIncreasing(int[] deck) {
		Arrays.sort(deck);
		int index = deck.length - 1;
		linkNode node = new linkNode(deck[index--]);
		node.left = node;
		node.right = node;
		linkNode head = node;
		linkNode tail = node;
		
		while (index >= 0) {
			linkNode newNode = new linkNode(deck[index--]);
			head = tail;
			tail = tail.left;
			head.left = newNode;
			tail.right = newNode;
			newNode.left = tail;
			newNode.right = head;
		}
		node = head.left;
		int[] ints = new int[deck.length];
		for (int i = 0; i < ints.length; i++) {
			ints[i] = node.val;
			node = node.right;
		}
		return ints;
	}
	
	public TreeNode bstFromPreorder(int[] preorder) {
		if (preorder.length < 1) {
			return null;
		}
		TreeNode root = new TreeNode(preorder[0]);
		for (int i = 1; i < preorder.length; i++) {
			bstFromPreorderHelper(root, preorder[i]);
		}
		return root;
	}
	
	private void bstFromPreorderHelper(TreeNode root, int i) {
		if (root.val < i) {
			if (root.right == null) {
				root.right = new TreeNode(i);
			} else {
				bstFromPreorderHelper(root.right, i);
			}
		} else {
			if (root.left == null) {
				root.left = new TreeNode(i);
			} else {
				bstFromPreorderHelper(root.left, i);
			}
		}
	}
	
	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null) {
			return new TreeNode(val);
		}
		if (root.val > val) {
			root.left = insertIntoBST(root.left, val);
			return root;
		} else {
			root.right = insertIntoBST(root.right, val);
			return root;
		}
	}
	
	public TreeNode constructMaximumBinaryTree(int[] nums) {
		int index = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > nums[index]) {
				index = i;
			}
		}
		TreeNode treeNode = new TreeNode(nums[index]);
		treeNode.left = constructMaximumBinaryTreeHelper(nums, 0, index - 1);
		treeNode.right = constructMaximumBinaryTreeHelper(nums, index + 1, nums.length - 1);
		return treeNode;
	}
	
	private TreeNode constructMaximumBinaryTreeHelper(int[] nums, int l, int r) {
		if (l < r) {
			return null;
		}
		int index = l;
		for (int i = l + 1; i <= r; i++) {
			if (nums[i] > nums[index]) {
				index = i;
			}
		}
		TreeNode treeNode = new TreeNode(nums[index]);
		treeNode.left = constructMaximumBinaryTreeHelper(nums, l, index - 1);
		treeNode.right = constructMaximumBinaryTreeHelper(nums, index + 1, r);
		return treeNode;
	}
	
	int rangeSum;
	
	public int rangeSumBST(TreeNode root, int L, int R) {
		if (root == null) {
			return 0;
		}
		if (root.val >= L && root.val <= R) {
			rangeSum += root.val;
			rangeSumBST(root.left, L, R);
			rangeSumBST(root.right, L, R);
		} else if (root.val < L) {
			rangeSumBST(root.right, L, R);
		} else {
			rangeSumBST(root.left, L, R);
		}
		return rangeSum;
	}
	
	public int maxIncreaseKeepingSkyline(int[][] grid) {
		int[] y = new int[grid.length];
		int[] x = new int[grid[0].length];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				int temp = grid[i][j];
				y[i] = Math.max(y[i], temp);
				x[j] = Math.max(x[j], temp);
			}
		}
		int total = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				total += (Math.min(y[i], x[j]) - grid[i][j]);
			}
		}
		return total;
	}
	
	public static int twoCitySchedCost(int[][] costs) {
		Arrays.sort(costs, (a, b) -> (a[0] - a[1] - b[0] + b[1]));
		int total = 0;
		for (int i = 0; i < costs.length / 2; i++) {
			total = total + costs[i][0] + costs[costs.length - i - 1][1];
		}
		return total;
	}
	
	public int binaryGap(int N) {
		int max = 0;
		int i = 0;
		while (N > 0) {
			if ((N & 1) == 1) {
				max = Math.max(max, i);
				i = 0;
			} else {
				i++;
			}
			N >>= 1;
		}
		return max;
	}
	
	public TreeNode trimBST(TreeNode root, int L, int R) {
		if (root == null) {
			return root;
		}
		if (root.val >= L && root.val <= R) {
			root.left = trimBST(root.left, L, R);
			root.right = trimBST(root.right, L, R);
			return root;
		} else if (root.val < L) {
			return trimBST(root.right, L, R);
		} else {
			return trimBST(root.left, L, R);
		}
	}
	
	public static String removeOuterParentheses(String S) {
		char[] array = S.toCharArray();
		int num = 0;
		int l = 0, r = 0;
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == '(') {
				num++;
			} else {
				num--;
			}
			if (num == 0) {
				buffer.append(S.substring(l + 1, i));
				l = i + 1;
			}
		}
		return buffer.toString();
	}
	
	public boolean buddyStrings(String A, String B) {
		if (A.length() != B.length()) {
			return false;
		}
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();
		for (int i = 0; i < a.length; i++) {
			if (a[i] != b[i]) {
				return buddyStringsHelper(a, b, i);
			}
		}
		int[] ints = new int[26];
		for (char c : a) {
			ints[c - 'a']++;
			if (ints[c - 'a'] > 1) {
				return true;
			}
		}
		return false;
	}
	
	private boolean buddyStringsHelper(char[] a, char[] b, int i) {
		boolean flag = false;
		for (int j = i + 1; j < a.length; j++) {
			if (a[j] != b[j]) {
				if (flag) {
					return false;
				} else {
					if (a[j] == b[i] && a[i] == b[j]) {
						flag = true;
					} else {
						return false;
					}
				}
			}
		}
		return true && flag;
	}
	
	public static int countPrimes(int n) {
		if (n < 2) {
			return 0;
		}
		int[] ints = new int[n];
		int count = 0;
		for (int i = 2; i < ints.length; i++) {
			if (ints[i] != 1) {
				count++;
				for (int j = i * i; j * i < n; j++) {
					ints[j * i] = 1;
				}
			}
		}
		return count;
	}
	
	public String convertToTitle(int n) {
		String s = "";
		n--;
		while (n > 0) {
			int k = n % 26;
			s = (char) (k + 'a') + s;
			k /= 26;
		}
		return s;
	}
	
	public int thirdMax(int[] nums) {
		int min = nums[0];
		int max = nums[0];
		for (int num : nums) {
			if (num < min) {
				min = num;
			} else if (max < num) {
				max = num;
			}
		}
		if (max == min) {
			return min;
		}
		int max2 = min;
		for (int num : nums) {
			if (num < max && max2 < num) {
				max2 = num;
			}
		}
		if (max2 == min) {
			return min;
		}
		int max3 = min;
		for (int num : nums) {
			if (num < max2 && max3 < num) {
				max3 = num;
			}
		}
		return max3;
	}
	
	public static int findPairs(int[] nums, int k) {
		Arrays.sort(nums);
		HashSet<Long> longs = new HashSet<>();
		int r = 1;
		for (int i = 0; i < nums.length; i++) {
			while (r < nums.length && nums[i] + k > nums[r]) {
				r++;
			}
			if (r < nums.length && nums[i] + k == nums[r] && r != i) {
				longs.add((long) (Math.abs(nums[i]) << 16) + nums[r]);
			}
		}
		return longs.size();
	}
	
	public int firstBadVersion(int n) {
		int l = 1;
		int r = n;
		while (l < r) {
			int mid = l + (r - l) / 2;
			boolean bad = isBadVersion(mid);
			if (bad) {
				if (isBadVersion(mid - 1)) {
					r = mid - 1;
				} else {
					return mid;
				}
			} else {
				if (isBadVersion(mid + 1)) {
					return mid + 1;
				} else {
					l = mid + 1;
				}
				
			}
		}
		return r;
	}
	
	boolean isBadVersion(int version) {
		return true;
	}
	
	public static void rotate(int[] nums, int k) {
		if (nums.length < 1) {
			return;
		}
		int temp;
		k = k % nums.length;
		if (k == 0) {
			return;
		}
		int i = 0;
		int t = 0;
		while (t < nums.length) {
			int index = i;
			int before = nums[i];
			do {
				index = (index + k) % nums.length;
				temp = nums[index];
				nums[index] = before;
				t++;
				before = temp;
			} while (index != i);
			i++;
		}
	}
	
	public static boolean checkPossibility(int[] nums) {
		if (nums.length < 3) {
			return true;
		}
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] > nums[i]) {
				return checkPossibilityHelper(nums, i) || checkPossibilityHelper(nums, i - 1);
			}
		}
		return true;
	}
	
	private static boolean checkPossibilityHelper(int[] nums, int i) {
		if (i < nums.length - 1) {
			if (i == 0 || nums[i - 1] < nums[i + 1]) {
				for (int j = i + 1; j < nums.length - 1; j++) {
					if ((nums[j] > nums[j + 1])) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	public static int findUnsortedSubarray(int[] nums) {
		int min = nums[0];
		int max = nums[0];
		for (int num : nums) {
			if (min > num) {
				min = num;
			} else if (max < num) {
				max = num;
			}
		}
		int[] ints = new int[max - min + 1];
		for (int num : nums) {
			ints[num - min]++;
		}
		
		int minI = 0;
		int i = 0;
		for (; i < nums.length; i++) {
			while (ints[minI] == 0) {
				minI++;
			}
			if (nums[i] - min != minI) {
				break;
			}
			ints[minI]--;
		}
		if (i == nums.length) {
			return 0;
		}
		int maxI = ints.length - 1;
		int j = nums.length - 1;
		for (; j > i; j--) {
			while (ints[maxI] == 0) {
				maxI--;
			}
			if (nums[j] - min != maxI) {
				break;
			}
			ints[maxI]--;
		}
		return j - i + 1;
	}
	
	public static int findNthDigit(int n) {
		long i = 9;
		int k = 1;
		while (n > i) {
			i = i * 10 + 9;
			k++;
		}
		i /= 10;
		int x = (int) (n - i) % k;
		int y = (int) (n - i) / k;
		int num = 1;
		while (k > 1) {
			num = num * 10;
			k--;
		}
		num += y;
		if (x > 0) {
			return (int) ((num + "").charAt(x - 1));
		} else {
			return (num - 1) % 10;
		}
	}
	
	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		if (n == 0) {
			return true;
		}
		if (flowerbed.length < 2) {
			if (flowerbed[0] == 0) {
				n--;
			}
			return n <= 0;
		}
		if (flowerbed[0] == 0 && flowerbed[1] == 0) {
			flowerbed[0] = 1;
			n--;
			if (n == 0) {
				return true;
			}
		}
		for (int i = 1; i < flowerbed.length - 1; i++) {
			if (flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
				flowerbed[i] = 1;
				n--;
				if (n == 0) {
					return true;
				}
			}
		}
		if (flowerbed[flowerbed.length - 2] == 0 && flowerbed[flowerbed.length - 1] == 0) {
			n--;
		}
		return n <= 0;
	}
	
	public static int mySqrt(int x) {
		int l = 0;
		int r = x;
		int mid = l + (r - l) / 2;
		while (l <= r) {
			mid = l + (r - l) / 2;
			if (mid == x / mid) {
				return mid;
			}
			if (mid < x / mid) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return r;
	}
	
	public int reverseBits(int n) {
		int ret = 0;
		for (int i = 0; i < 32; i++) {
			int temp = n & 1;
			n >>= 1;
			temp <<= (31 - i);
			ret += temp;
		}
		return ret;
	}
	
	public int repeatedStringMatch(String A, String B) {
		int index = A.indexOf(B.charAt(0));
		char[] array1 = A.toCharArray();
		char[] array = B.toCharArray();
		while (index >= 0) {
			int i = 0;
			for (; i < array.length; i++) {
				if (array1[(i + index) % A.length()] != array[i]) {
					break;
				}
			}
			if (i == array.length) {
				int ret = (i - 1 + index) / A.length() + 1;
				return ret;
			}
			index = A.indexOf(B.charAt(0), index + 1);
		}
		return index;
	}
	
	public static int robotSim(int[] commands, int[][] obstacles) {
		int direct = 0;
		//0:up,1:right,2down,3left
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		int x = 0, y = 0, max = 0;
		HashSet<Long> longs = new HashSet<>();
		for (int[] obstacle : obstacles) {
			longs.add(((long) (obstacle[0] + 30000) << 16) + (long) (obstacle[1] + 30000));
		}
		for (int command : commands) {
			switch (command) {
				case -2:
					direct = (direct + 3) % 4;
					break;
				case -1:
					direct = (direct + 1) % 4;
					break;
				default:
					for (int j = 0; j < command; j++) {
						int tx = x + dx[direct];
						int ty = y + dy[direct];
						if (!longs.contains(((long) (tx + 30000) << 16) + (long) (ty + 30000))) {
							x = tx;
							y = ty;
							max = Math.max(max, x * x + y * y);
						}
					}
				
				
			}
		}
		return max;
	}
	
	public static int findRadius(int[] houses, int[] heaters) {
		Arrays.sort(houses);
		Arrays.sort(heaters);
		int index = 0;
		int l = 0 - heaters[0];
		int r = heaters[0];
		int min = 0;
		for (int i = 0; i < houses.length; i++) {
			while (houses[i] > r && index < heaters.length) {
				index++;
				if (index < heaters.length) {
					l = r;
					r = heaters[index];
				} else {
					l = heaters[index - 1];
					r = Integer.MAX_VALUE;
				}
			}
			if (houses[i] > l && houses[i] < r) {
				int min1 = Math.min(houses[i] - l, r - houses[i]);
				min = Math.max(min, min1);
			}
			
		}
		return min;
	}
	
	public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
		int la = 0, lb = 0;
		ListNode tempA = headA;
		ListNode tempB = headB;
		while (tempA != null) {
			tempA = tempA.next;
			la++;
		}
		while (tempB != null) {
			tempB = tempB.next;
			lb++;
		}
		while (la > lb) {
			headA = headA.next;
			la--;
		}
		while (la < lb) {
			headB = headB.next;
			lb--;
		}
		while (headA != headB && headA != null) {
			headA = headA.next;
			headB = headB.next;
		}
		return headA;
	}
	
	public static int reachNumber(int target) {
		target = Math.abs(target);
		int total = 0;
		int i = 0;
		while (total < target) {
			i++;
			total += i;
		}
		while (((total - target) & 1) == 1) {
			total += i;
			i++;
		}
		return i;
	}
	
	public int lengthOfLastWord(String s) {
		char[] array = s.toCharArray();
		int x;
		int index = array.length - 1;
		while (index > 0 && array[index] == ' ') {
			index--;
		}
		x = index;
		while (index > 0 && array[index] != ' ') {
			index--;
		}
		return x - index;
	}
	
	public boolean judgeSquareSum(int c) {
		int l = 0, r = (int) Math.sqrt(c), k;
		while (l <= r) {
			k = l * l + r * r;
			if (k == c) {
				return true;
			} else if (k > c) {
				r--;
			} else {
				l++;
			}
			
		}
		return false;
	}
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode tempA;
		ListNode tempA1;
		ListNode tempB;
		ListNode tempB1;
		while (headA != null) {
			tempA1 = headA;
			tempB1 = headB;
			while (tempB1 != null) {
				tempA = tempA1;
				tempB = tempB1;
				while (tempA == tempB) {
					if (tempA.next == null && tempB.next == null) {
						return tempB1;
					} else if (tempA.next == null || tempB.next == null) {
						break;
					} else {
						tempA = tempA.next;
						tempB = tempB.next;
					}
				}
				tempB1 = tempB1.next;
			}
			headA = headA.next;
		}
		return null;
	}
	
	public static String longestCommonPrefix(String[] strs) {
		if (strs.length < 1) {
			return "";
		}
		char[] array = strs[0].toCharArray();
		int index = array.length;
		for (int i = 1; i < strs.length; i++) {
			int k = 0;
			char[] array1 = strs[i].toCharArray();
			while (k < array1.length && k < index) {
				if (array[k] != array1[k]) {
					k++;
					break;
				}
				k++;
			}
			index = k;
			if (index < 0) {
				return "";
			}
		}
		return strs[0].substring(0, index);
	}
	
	static int longestUnivalue;
	
	
	public static int longestUnivaluePath(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int l =
			longestUnivaluePathHelper(root.left, root.val) + longestUnivaluePathHelper(
				root.right,
				root.val);
		longestUnivalue = Math.max(longestUnivalue, l);
		return longestUnivalue;
	}
	
	private static int longestUnivaluePathHelper(TreeNode root, int val) {
		if (root == null) {
			return 0;
		}
		int l, r;
		if (root.val != val) {
			l = longestUnivaluePathHelper(root.left, root.val);
			r = longestUnivaluePathHelper(root.right, root.val);
			longestUnivalue = Math.max(longestUnivalue, l + r);
			return 0;
		} else {
			l = longestUnivaluePathHelper(root.left, val);
			r = longestUnivaluePathHelper(root.right, val);
			longestUnivalue = Math.max(longestUnivalue, l + r);
			return 1 + Math.max(l, r);
		}
		
	}
}
