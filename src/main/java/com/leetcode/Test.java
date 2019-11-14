package com.leetcode;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2019/3/21 10:18
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test {
	
	public static void main(String[] args) {
		/*TreeNode treeNode = new TreeNode(3);
		treeNode.left = new TreeNode(0);
		treeNode.right = new TreeNode(20);
		treeNode.right.left = new TreeNode(15);
		//treeNode.right.left.left = new TreeNode(20);
		treeNode.right.right = new TreeNode(7);
		sumOfLeftLeaves(treeNode);*/
		//minDiffInBST(treeNode);
		//numberOfBoomerangs(new int[][]{{0, 0}, {1, 0}, {2, 0}});//, {0, 1},{0,-1}});
		//minMoves(new int[]{1, 2, 3, 5});
		Test test = new Test();
		test.longestWPI(new int[]{9, 9, 6, 0, 6, 6, 9});
	}
	
	public int longestWPI(int[] hours) {
		int len = hours.length;
		int[] count = new int[len];
		
		for (int i = 0; i < len; i++) {
			count[i]=hours[i]>8?1:-1;
		}
		int[] ints = new int[len + 1];
		for (int i = 0; i < len; i++) {
			ints[i+1]=ints[i]+count[i];
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(0);
		for (int i = 1; i <=len ; i++) {
			if(ints[i]<ints[stack.peek()]){
				stack.push(i);
			}
		}
		int ret=0;
		while (len>ret){
			while (!stack.empty()&&ints[len]>ints[stack.peek()]){
				ret=Math.max(ret,len-stack.pop());
			}
			len--;
		}
		return ret;
	}
	
	TreeNode ret;
	int maxDept = 0;
	
	public TreeNode lcaDeepestLeaves(TreeNode root) {
		lcaDeepestLeavesHelper(root, 0);
		return ret;
	}
	
	private int lcaDeepestLeavesHelper(TreeNode root, int level) {
		if (root.left == null && root.right == null) {
			if (level > maxDept) {
				ret = root;
				maxDept = level;
			}
			return level;
		}
		int l = 0, r = 0;
		if (root.left != null) {
			l = lcaDeepestLeavesHelper(root.left, level + 1);
		}
		if (root.right != null) {
			r = lcaDeepestLeavesHelper(root.right, level + 1);
		}
		if (l == r && l >= maxDept) {
			ret = root;
		}
		return Math.max(r, l);
	}
	
	public int bagOfTokensScore(int[] tokens, int P) {
		Arrays.sort(tokens);
		int l = 0, r = tokens.length - 1, ret = 0;
		while (l < r) {
			while (l <= r && P >= tokens[l]) {
				P -= tokens[l];
				ret++;
				l++;
			}
			
			if (l < r && ret > 0) {
				P += tokens[r];
				r--;
				ret--;
			} else {
				break;
			}
		}
		return ret;
	}
	
	public static int[] twoSum2(int[] nums, int target) {
		int mask = 2048;
		int[] hash = new int[mask--];
		int first = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int diff = target - nums[i];
			if (first + nums[i] == target) {
				return new int[]{0, i};
			} else {
				int index = hash[diff & mask];
				if (index > 0) {
					return new int[]{i, index};
				}
			}
			hash[nums[i] & mask] = i;
		}
		return new int[0];
	}
	
	public int[] relativeSortArray(int[] arr1, int[] arr2) {
		int[] count = new int[1001];
		for (int i : arr1) {
			count[i]++;
		}
		int idx = 0;
		for (int i : arr2) {
			while (count[i] > 0) {
				count[i]--;
				arr1[idx] = i;
				idx++;
			}
		}
		for (int i = 0; i < 1001; i++) {
			while (count[i] > 0) {
				count[i]--;
				arr1[idx] = i;
				idx++;
			}
		}
		return arr1;
	}
	
	public int[] maxDepthAfterSplit1(String seq) {
		int count = 0;
		int len = seq.length();
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			char c = seq.charAt(i);
			if (c == '(') {
				ret[i] = count;
				count++;
			} else {
				count--;
				ret[i] = count;
			}
		}
		for (int i = 0; i < len; i++) {
			ret[i] &= 1;
		}
		return ret;
	}
	
	public int[] maxDepthAfterSplit(String seq) {
		int len = seq.length();
		int[] ret = new int[len];
		char[] array = seq.toCharArray();
		int start = 0;
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (array[i] == '(') {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				maxDepthAfterSplitHelper(ret, start, i, array, 0);
				start = i + 1;
			}
		}
		return ret;
	}
	
	private void maxDepthAfterSplitHelper(int[] ret, int l, int r, char[] array, int val) {
		ret[l] = val;
		ret[r] = val;
		int start = l + 1;
		int count = 0;
		for (int i = l + 1; i < r; i++) {
			if (array[i] == '(') {
				count++;
			} else {
				count--;
			}
			if (count == 0) {
				maxDepthAfterSplitHelper(ret, start, i, array, val ^ 1);
				start = i + 1;
			}
		}
	}
	
	public boolean rotateString(String A, String B) {
		if (A.equals(B)) {
			return true;
		} else if (A.length() != B.length()) {
			return false;
		}
		
		char[] a = A.toCharArray();
		char[] b = B.toCharArray();
		int ex = B.indexOf(a[0]);
		while (ex > 0) {
			int i = 0;
			for (; i < a.length; i++) {
				if (a[i] != b[(ex + i) % b.length]) {
					break;
				}
			}
			if (i == a.length) {
				return true;
			} else {
				ex = B.indexOf(a[0], ex);
			}
		}
		return false;
	}
	
	static int sumOfLeftLeaves;
	
	public static int sumOfLeftLeaves(TreeNode root) {
		if (root.left == null && root.right == null) {
			sumOfLeftLeaves += root.val;
			return root.val;
		}
		if (root.left != null) {
			sumOfLeftLeaves(root.left);
		}
		while (root.right != null) {
			root = root.right;
			if (root.left != null) {
				sumOfLeftLeaves(root.left);
				break;
			}
		}
		return sumOfLeftLeaves;
	}
	
	public boolean isOneBitCharacter(int[] bits) {
		if (bits.length == 1) {
			return true;
		}
		
		return isOneBitCharacterHelper(bits, 0);
	}
	
	public static int minMoves(int[] nums) {
		int min = Integer.MAX_VALUE;
		for (int num : nums) {
			min = Math.min(min, num);
		}
		int sum = 0;
		for (int num : nums) {
			sum += (num - min);
		}
		return sum;
	}
	
	public boolean isOneBitCharacter1(int[] bits) {
		int i = 0;
		for (; i < bits.length; ) {
			
			if (bits[i] != 0) {
				i++;
			}
			i++;
			
		}
		if (i == bits.length) {
			return false;
		}
		return true;
	}
	
	private boolean isOneBitCharacterHelper(int[] bits, int i) {
		if (i == bits.length - 1) {
			return true;
		} else if (i > bits.length - 1) {
			return false;
		}
		if (bits[i] == 0) {
			return isOneBitCharacterHelper(bits, i + 1);
		} else {
			return isOneBitCharacterHelper(bits, i + 2);
		}
	}
	
	public boolean canConstruct(String ransomNote, String magazine) {
		int[] ints = new int['z' - 'A' + 1];
		char[] r = ransomNote.toCharArray();
		char[] m = magazine.toCharArray();
		for (char c : m) {
			ints[c - 'A']++;
		}
		for (char c : r) {
			ints[c - 'A']--;
			if (ints[c - 'A'] < 0) {
				return false;
			}
		}
		return true;
	}
	
	public static int largestSumAfterKNegations(int[] A, int K) {
		Arrays.sort(A);
		int i = 0;
		int l = 0;
		while (A[i] <= 0) {
			l++;
			i++;
		}
		
		int sum = 0;
		if (l >= K) {
			for (int j = 0; j < A.length; j++) {
				if (K > 0) {
					sum -= A[j];
					K--;
				} else {
					sum += A[j];
				}
			}
		} else {
			if ((K - l) % 2 == 0) {
				for (int j = 0; j < A.length; j++) {
					sum += A[j] > 0 ? A[j] : 0 - A[j];
				}
			} else {
				int k = -1;
				if (A[0] >= 0) {
					k = A[0];
				} else if (A[A.length - 1] <= 0) {
					k = 0 - A[A.length - 1];
				}
				
				for (int j = 0; j < A.length; j++) {
					if (k < 0 && j > 0 && A[j - 1] * A[j] <= 0) {
						k = A[j - 1] + A[j] > 0 ? 0 - A[j - 1] : A[j];
						
					}
					sum += A[j] > 0 ? A[j] : 0 - A[j];
				}
				sum -= k * 2;
			}
		}
		return sum;
	}
	
	public static int numberOfBoomerangs(int[][] points) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		int temp;
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				if (i != j) {
					temp =
						(points[j][0] - points[i][0]) * (points[j][0] - points[i][0])
							+ (points[j][1] - points[i][1]) * (points[j][1] - points[i][1]);
					Integer v = map.get(temp);
					if (v == null) {
						map.put(temp, 1);
					} else {
						map.put(temp, v + 1);
						sum += v << 1;
					}
				}
			}
			map.clear();
		}
		return sum;
	}
	
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
		if (p == null && q != null || p != null && q == null) {
			return false;
		} else if (p != null && q != null) {
			return isSameTree(p.left, q.left) && isSameTree(p.right, q.right) && p.val == q.val;
		} else {
			return true;
		}
	}
	
	public int firstUniqChar1(String s) {
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (s.lastIndexOf(chars[i]) == i) {
				return i;
			}
		}
		return -1;
	}
	
	public int firstUniqChar(String s) {
		int[] ints = new int[26];
		char[] chars = s.toCharArray();
		for (char aChar : chars) {
			ints[aChar - 'a']++;
		}
		for (int i = 0; i < chars.length; i++) {
			if (ints[chars[i] - 'a'] == 1) {
				return i;
			}
		}
		return 0;
	}
	
	public int findShortestSubArray(int[] nums) {
		int[] ints = new int[50000];
		for (int num : nums) {
			ints[num]++;
		}
		int du = 0;
		for (int i : ints) {
			du = Math.max(i, du);
		}
		
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] == du) {
				int r = nums.length - 1;
				int l = 0;
				while (r > l && nums[r] != i) {
					r--;
				}
				while (r > l && nums[l] != i) {
					l++;
				}
				minLen = Math.min(minLen, r - l + 1);
				if (minLen == du) {
					return minLen;
				}
			}
		}
		return minLen;
	}
	
	public static int[] twoSum(int[] numbers, int target) {
		int change = numbers[0] < 0 ? 0 - numbers[0] : 0;
		target += (change * 2);
		int[] ints = new int[target + 1];
		for (int i = 0; i < numbers.length; i++) {
			int j = numbers[i] + change;
			if (ints[j] > 0) {
				return new int[]{ints[j], i + 1};
			} else {
				ints[target - j] = i + 1;
			}
		}
		return new int[0];
	}
	
	public static int[] twoSum1(int[] numbers, int target) {
		int l = 0, r = numbers.length - 1, s;
		int[] ints = new int[2];
		while (l < r) {
			s = numbers[l] + numbers[r];
			if (s > target) {
				r--;
			} else if (s < target) {
				l++;
			} else {
				ints[0] = l + 1;
				ints[1] = r + 1;
			}
		}
		return ints;
	}
	
	public TreeNode sortedArrayToBST(int[] nums) {
		if (nums.length < 1) {
			return null;
		} else if (nums.length == 1) {
			return new TreeNode(nums[0]);
		} else {
			TreeNode treeNode = new TreeNode(nums[nums.length / 2]);
			sortedArrayToBSTHelper(treeNode, nums, 0, nums.length / 2 - 1);
			sortedArrayToBSTHelper(treeNode, nums, nums.length / 2 + 1, nums.length - 1);
			return treeNode;
		}
	}
	
	private void sortedArrayToBSTHelper(TreeNode treeNode, int[] nums, int l, int r) {
		if (l != r) {
			if (nums[(l + r) / 2] < treeNode.val) {
				treeNode.left = new TreeNode(nums[(l + r) / 2]);
				sortedArrayToBSTHelper(treeNode.left, nums, l, (l + r) / 2 - 1);
				sortedArrayToBSTHelper(treeNode.left, nums, (l + r) / 2 + 1, r);
			} else {
				treeNode.right = new TreeNode(nums[(l + r) / 2]);
				sortedArrayToBSTHelper(treeNode.right, nums, l, (l + r) / 2 - 1);
				sortedArrayToBSTHelper(treeNode.right, nums, (l + r) / 2 + 1, r);
			}
			
		}
	}
	
	static int minDiff = Integer.MAX_VALUE;
	
	public static int minDiffInBST(TreeNode root) {
		minDiffInBSTHelper(root);
		return minDiff;
	}
	
	private static int minDiffInBSTHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left != null) {
			int i = minDiffInBSTHelper(root.left);
			minDiff = Math.min(minDiff, root.val - i);
		}
		
		if (root.right != null) {
			int i = minDiffInBSTHelper(root.right);
			minDiff = Math.min(minDiff, i - root.val);
		}
		return root.val;
	}
	
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		int oldColor = image[sr][sc];
		if (oldColor == newColor) {
			return image;
		}
		floodFillHelper(image, sr, sc, oldColor, newColor);
		return image;
	}
	
	static int diff = Integer.MAX_VALUE;
	static int per = Integer.MAX_VALUE;
	
	public static int getMinimumDifference1(TreeNode root) {
		if (root == null) {
			return 0;
		}
		getMinimumDifference1(root.left);
		if (diff > root.val - per && per != Integer.MAX_VALUE) {
			diff = root.val - per;
		}
		per = root.val;
		getMinimumDifference1(root.right);
		return diff;
	}
	
	public static int getMinimumDifference(TreeNode root) {
		ArrayList<Integer> integers = new ArrayList<>();
		getMinimumDifferenceHelper(root, integers);
		int min = Integer.MAX_VALUE;
		for (int i = 1; i < integers.size(); i++) {
			min = Math.min(min, integers.get(i) - integers.get(i - 1));
		}
		return min;
	}
	
	public static void getMinimumDifferenceHelper(TreeNode root, List<Integer> list) {
		
		if (root.left == null && root.right == null) {
			list.add(root.val);
			return;
		}
		if (root.left != null) {
			getMinimumDifferenceHelper(root.left, list);
		}
		list.add(root.val);
		if (root.right != null) {
			getMinimumDifferenceHelper(root.right, list);
		}
		
	}
	
	
	private void floodFillHelper(int[][] image, int sr, int sc, int oldColor, int newColor) {
		if (sr < 0 || sc < 0 || sr >= image.length || sc >= image[0].length
			|| image[sr][sc] != oldColor) {
			return;
		}
		image[sr][sc] = newColor;
		floodFillHelper(image, sr + 1, sc, oldColor, newColor);
		floodFillHelper(image, sr - 1, sc, oldColor, newColor);
		floodFillHelper(image, sr, sc + 1, oldColor, newColor);
		floodFillHelper(image, sr, sc - 1, oldColor, newColor);
		
	}
	
	public static boolean lemonadeChange(int[] bills) {
		if (bills[0] != 5) {
			return false;
		}
		int sum5 = 0;
		int sum10 = 0;
		for (int bill : bills) {
			if (bill == 5) {
				sum5 += bill;
			} else if (bill == 10) {
				sum5 -= 5;
				sum10 += bill;
			} else {
				if (sum10 > 0) {
					bill -= 10;
					sum10 -= 10;
				}
				sum5 -= bill - 5;
			}
			if (sum5 < 0) {
				return false;
			}
		}
		return true;
	}
	
	public String tree2str(TreeNode t) {
		if (t == null) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		tree2strHelper(t, buffer);
		return buffer.toString();
	}
	
	public int titleToNumber(String s) {
		char[] chars = s.toCharArray();
		int sum = 0;
		for (char aChar : chars) {
			sum = sum * 26 + (aChar - 'A' + 1);
		}
		return sum;
	}
	
	private void tree2strHelper(TreeNode t, StringBuffer buffer) {
		
		if (t.left == null && t.right == null) {
			buffer.append(t.val);
			return;
		}
		buffer.append(t.val);
		if (t.left != null) {
			buffer.append("(");
			tree2strHelper(t.left, buffer);
			buffer.append(")");
		} else if (t.right != null) {
			buffer.append("()");
		}
		
		if (t.right != null) {
			buffer.append("(");
			tree2strHelper(t.right, buffer);
			buffer.append(")");
		}
	}
	
	public int romanToInt(String s) {
		char[] chars = s.toCharArray();
		int ret = 0;
		char after = 'I';
		for (int i = chars.length - 1; i >= 0; i--) {
			ret += (isadd(chars[i], after) ? romanMapper(chars[i]) : 0 - romanMapper(chars[i]));
			after = chars[i];
		}
		return ret;
	}
	
	static int convertBSTSum;
	
	public static TreeNode convertBST(TreeNode root) {
		convertBSTSum = 0;
		
		convertBSTHelper(root);
		
		return root;
	}
	
	private static void convertBSTHelper(TreeNode root) {
		if (root.left == null && root.right == null) {
			convertBSTSum += root.val;
			root.val = convertBSTSum;
			return;
		}
		if (root.right != null) {
			convertBSTHelper(root.right);
		}
		convertBSTSum += root.val;
		root.val = convertBSTSum;
		if (root.left != null) {
			convertBSTHelper(root.left);
		}
		
	}
	
	
	public boolean containsDuplicate(int[] nums) {
		Arrays.sort(nums);
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1] == nums[i]) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		char[] sc = s.toCharArray();
		char[] tc = t.toCharArray();
		Arrays.sort(sc);
		Arrays.sort(tc);
		for (int i = 0; i < sc.length; i++) {
			if (sc[i] != tc[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static int maxProfit(int[] prices) {
		boolean flag = false;
		int l = 0, sum = 0;
		for (int i = 0; i < prices.length; i++) {
			if (!flag) {
				if (i < prices.length - 1 && prices[i] < prices[i + 1]) {
					l = prices[i];
					flag = true;
				}
			} else {
				if (i < prices.length - 1 && prices[i] > prices[i + 1]) {
					sum += prices[i] - l;
					l = 0;
					flag = false;
				}
			}
		}
		sum += flag ? (prices[prices.length - 1] - l) : 0;
		return sum;
	}
	
	public static int maxProfit1(int[] prices) {
		int sum = 0;
		for (int i = 0; i < prices.length; i++) {
			if (i + 1 < prices.length && prices[i] < prices[i + 1]) {
				sum += prices[i + 1] - prices[i];
			}
		}
		return sum;
	}
	
	private int romanMapper(char aChar) {
		switch (aChar) {
			case 'I':
				return 1;
			case 'V':
				return 5;
			case 'X':
				return 10;
			case 'L':
				return 50;
			case 'C':
				return 100;
			case 'D':
				return 500;
			case 'M':
				return 1000;
			default:
				return 0;
		}
		
	}
	
	private boolean isadd(char before, char after) {
		if (before == 'I' && "VX".indexOf(after) >= 0) {
			return false;
		} else if (before == 'X' && "LC".indexOf(after) >= 0) {
			return false;
		} else if (before == 'C' && "DM".indexOf(after) >= 0) {
			return false;
		}
		return true;
	}
	
	public boolean findTarget(TreeNode root, int k) {
		ArrayList<Integer> integers = new ArrayList<>();
		return findTargetHelper(root, k, integers);
	}
	
	public int majorityElement(int[] nums) {
		int temp = nums[0], count = 1;
		for (int i = 1; i < nums.length; i++) {
			if (temp == nums[i]) {
				count++;
			} else {
				count--;
				if (count == 0) {
					i++;
					if (i < nums.length) {
						temp = nums[i];
						count++;
					}
				}
			}
		}
		return temp;
	}
	
	private boolean findTargetHelper(TreeNode root, int k, ArrayList<Integer> integers) {
		if (root == null) {
			return false;
		}
		if (integers.contains(root.val)) {
			return true;
		} else {
			integers.add(k - root.val);
			return findTargetHelper(root.left, k, integers) || findTargetHelper(root.right, k,
				integers);
		}
		
	}
	
	public void deleteNode(ListNode node) {
		node.val = node.next.val;
		node.next = node.next.next;
	}
	
	public boolean detectCapitalUse(String word) {
		char[] chars = word.toCharArray();
		int u = 0;
		for (char aChar : chars) {
			if (aChar - 'A' > 26) {
				u++;
			}
			
		}
		if (u == 0) {
			return true;
		} else if (u == 1 && chars[0] - 'A' < 27) {
			return true;
		} else if (u == word.length()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isCousins(TreeNode root, int x, int y) {
		int[] ints = new int[2];
		TreeNode fx = getFatherNode(root, x, 0, 0, ints);
		TreeNode fy = getFatherNode(root, y, 0, 1, ints);
		if (ints[0] != ints[1]) {
			return false;
		} else if (fx == fy) {
			return false;
		} else {
			return true;
		}
	}
	
	private static TreeNode getFatherNode(TreeNode root, int x, int i, int index, int[] ints) {
		TreeNode ret = null;
		if (root.left != null && root.left.val == x) {
			ints[index] = i + 1;
			ret = root;
		} else if (root.right != null && root.right.val == x) {
			ints[index] = i + 1;
			ret = root;
		} else {
			if (root.left != null) {
				ret = getFatherNode(root.left, x, i + 1, index, ints);
			}
			if (root.right != null && ret == null) {
				ret = getFatherNode(root.right, x, i + 1, index, ints);
			}
		}
		return ret;
	}
	
	public char findTheDifference(String s, String t) {
		char[] chars = s.toCharArray();
		char[] chars1 = t.toCharArray();
		int[] ints = new int[26];
		ints[chars1[chars1.length - 1] - 'a']--;
		for (int i = 0; i < chars.length; i++) {
			ints[chars[i] - 'a']++;
			ints[chars1[i] - 'a']--;
		}
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] != 0) {
				return (char) (i + 'a');
			}
		}
		return 'a';
	}
	
	class Employee {
		
		// It's the unique id of each node;
		// unique id of this employee
		public int id;
		// the importance value of this employee
		public int importance;
		// the id of direct subordinates
		public List<Integer> subordinates;
	}
	
	public List<Integer> findDisappearedNumbers(int[] nums) {
		int[] ints = new int[nums.length + 1];
		for (int num : nums) {
			ints[num]++;
		}
		ArrayList<Integer> integers = new ArrayList<>();
		for (int i = 1; i < ints.length; i++) {
			if (ints[i] == 0) {
				integers.add(i);
			}
		}
		return integers;
	}
	
	public static int countBinarySubstrings(String s) {
		if (s.length() < 2) {
			return 0;
		}
		char[] chars = s.toCharArray();
		char temp = chars[0];
		int num = 1, num1 = 0, sum = 0;
		int i = 1;
		while (i < chars.length && temp == chars[i]) {
			num++;
			i++;
		}
		temp = chars[i];
		for (; i < chars.length; i++) {
			while (i < chars.length && temp == chars[i]) {
				num1++;
				i++;
			}
			temp = chars[i];
			sum += Math.min(num, num1);
			num = num1;
			num1 = 1;
			
		}
		sum += Math.min(num, num1);
		return sum;
	}
	
	public int getImportance(List<Employee> employees, int id) {
		int index = 0;
		while (index < employees.size() && employees.get(index).id != id) {
			index++;
		}
		int sum = 0;
		if (index < employees.size()) {
			sum += getImportanceHelper(employees.get(index), employees);
		}
		return sum;
	}
	
	public int getImportance1(List<Employee> employees, int id) {
		HashMap<Integer, Employee> map = new HashMap<>(employees.size());
		for (Employee employee : employees) {
			map.put(employee.id, employee);
		}
		int sum = 0;
		sum += getImportanceHelper1(map, id);
		return sum;
		
	}
	
	private int getImportanceHelper1(HashMap<Integer, Employee> map, int id) {
		Employee employee = map.get(id);
		int importance = employee.importance;
		for (Integer subordinate : employee.subordinates) {
			importance += getImportanceHelper1(map, subordinate);
		}
		return importance;
	}
	
	private int getImportanceHelper(Employee employee, List<Employee> employees) {
		int importance = employee.importance;
		for (Employee e : employees) {
			if (employee.subordinates.contains(e.id)) {
				importance += getImportanceHelper(e, employees);
			}
		}
		return importance;
	}
	
	public boolean canThreePartsEqualSum(int[] A) {
		int sum = 0;
		for (int i : A) {
			sum += i;
		}
		if (sum % 3 != 0) {
			return false;
		}
		int temp = sum / 3;
		sum = 0;
		int k = 0;
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
			if (temp == sum) {
				sum = 0;
				k++;
			}
			if (k == 2) {
				return true;
			}
		}
		return false;
	}
	
	public ListNode reverseList(ListNode head) {
		ListNode ret = null;
		while (head != null) {
			if (ret == null) {
				ret = new ListNode(head.val);
			} else {
				ListNode listNode = new ListNode(head.val);
				listNode.next = ret;
				ret = listNode;
			}
			head = head.next;
		}
		return ret;
	}
	
	public int[] intersection(int[] nums1, int[] nums2) {
		HashSet<Integer> set = new HashSet<>();
		for (int i : nums1) {
			set.add(i);
		}
		HashSet<Integer> ret = new HashSet<>();
		for (int i : nums2) {
			if (set.contains(i)) {
				ret.add(i);
			}
		}
		Integer[] integers = ret.toArray(new Integer[]{});
		int[] ints = new int[integers.length];
		for (int i = 0; i < integers.length; i++) {
			ints[i] = integers[i];
		}
		return ints;
	}
	
	public int addDigits(int num) {
		if (num < 10) {
			return num;
		}
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return addDigits(sum);
	}
	
	public static void moveZeroes(int[] nums) {
		int d = 0;
		for (int i = 0; i < nums.length - d; i++) {
			if (nums[i] == 0) {
				d++;
			} else if (d > 0) {
				nums[i - d] = nums[i];
				nums[i] = 0;
			}
		}
	}
	
	public static String shortestCompletingWord(String licensePlate, String[] words) {
		int[] ints = new int[4];
		for (char c : licensePlate.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				ints[c - 'a']++;
			} else if (c >= 'A' && c <= 'Z') {
				ints[c - 'A']++;
			}
		}
		String ret = null;
		for (String word : words) {
			if (ret != null && ret.length() > word.length()) {
				int[] temp = new int[4];
				for (char c : word.toCharArray()) {
					temp[c - 'a']++;
				}
				int i = 0;
				while (i < 26 && temp[i] >= ints[i]) {
					i++;
				}
				if (i == ints.length) {
					ret = word;
				}
			}
			
		}
		return ret;
	}
	
	public int findMaxConsecutiveOnes(int[] nums) {
		int max = 0;
		int temp = max;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != 0) {
				temp++;
			} else {
				max = Math.max(max, temp);
				temp = 0;
			}
		}
		return max;
	}
	
	public boolean isMonotonic(int[] A) {
		boolean flag = true;
		int k = 0;
		while (k + 1 < A.length && A[k] != A[k + 1]) {
			flag = A[k] > A[k + 1];
		}
		if (k + 1 == A.length) {
			return true;
		}
		for (int i = k; i < A.length; i++) {
			if (A[i] != A[i + 1] && flag != (A[i] > A[i + 1])) {
				return false;
			}
			
		}
		return true;
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
	
	public Node4 construct(int[][] grid) {
		if (grid.length != grid[0].length) {
			return null;
		}
		int i = 0, j = 0;
		return constructHelper(grid, i, j, grid.length);
	}
	
	private Node4 constructHelper(int[][] grid, int i, int j, int length) {
		int temp = grid[i][j];
		boolean isleaf = true;
		for (int k = i; k < i + length; k++) {
			for (int l = j; l < j + length; l++) {
				if (grid[k][l] != temp) {
					isleaf = false;
					break;
				}
			}
			if (!isleaf) {
				break;
			}
		}
		if (isleaf) {
			return new Node4(temp == 1, isleaf, null, null, null, null);
		} else {
			return new Node4(temp == 1, isleaf,
				constructHelper(grid, i, j, length / 2),
				constructHelper(grid, i, j + length / 2, length / 2),
				constructHelper(grid, i + length / 2, j, length / 2),
				constructHelper(grid, i + length / 2, j + length / 2, length / 2)
			);
		}
	}
	
	
	public int largestPerimeter(int[] A) {
		Arrays.sort(A);
		for (int i = A.length - 1; i > 1; i--) {
			if (A[i] - A[i - 1] < A[i - 2]) {
				return A[i] + A[i - 1] + A[i - 2];
			}
		}
		return 0;
	}
	
	public List<String> letterCasePermutation(String S) {
		int index = 0;
		ArrayList<String> list = new ArrayList<>();
		list.add(S);
		letterCasePermutationHelper(list, S, index);
		return list;
	}
	
	private void letterCasePermutationHelper(ArrayList<String> list, String s, int index) {
		char[] chars = s.toCharArray();
		while (index < s.length()) {
			char c = chars[index];
			if (c >= 'a' && c <= 'z') {
				chars[index] = (char) (c - 32);
				String e = new String(chars);
				letterCasePermutationHelper(list, e, index + 1);
				list.add(e);
				chars[index] = c;
			} else if (c >= 'A' && c <= 'Z') {
				chars[index] = (char) (c + 32);
				String e = new String(chars);
				letterCasePermutationHelper(list, e, index + 1);
				list.add(e);
				chars[index] = c;
			}
			index++;
		}
	}
	
	public static int largestPerimeter2(int[] A) {
		int[] ints = new int[1000001];
		for (int i : A) {
			ints[i]++;
		}
		int c = getMax(ints, ints.length - 1);
		int b = getMax(ints, c);
		int a = getMax(ints, b);
		
		while (a > 0 && a + b < c) {
			c = b;
			b = a;
			a = getMax(ints, b);
		}
		
		if (a > 0) {
			return a + b + c;
		} else {
			return 0;
		}
	}
	
	public int findLUSlength(String a, String b) {
		if (a.equals(b)) {
			return -1;
		}
		return a.length() > b.length() ? a.length() : b.length();
	}
	
	public String reverseOnlyLetters(String S) {
		char[] chars = S.toCharArray();
		int l = 0;
		int r = chars.length - 1;
		char temp;
		while (l < r) {
			while (l < chars.length && l < r) {
				temp = Character.toLowerCase(chars[l]);
				if (temp >= 'a' && temp <= 'z') {
					break;
				}
				l++;
			}
			while (r >= 0 && l < r) {
				temp = Character.toLowerCase(chars[r]);
				if (temp >= 'a' && temp <= 'z') {
					break;
				}
				r--;
			}
			temp = chars[l];
			chars[l] = chars[r];
			chars[r] = temp;
			l++;
			r--;
		}
		return new String(chars);
	}
	
	public static boolean isAlienSorted(String[] words, String order) {
		int[] ints = new int[26];
		int i = 0;
		for (char c : order.toCharArray()) {
			ints[c - 'a'] += i;
			i++;
		}
		for (int j = 0; j < words.length - 1; j++) {
			if (!words[j].equals(words[j + 1])) {
				char[] before = words[j].toCharArray();
				char[] after = words[j + 1].toCharArray();
				for (int k = 0; k < before.length && k < after.length; k++) {
					if (ints[before[k] - 'a'] < ints[after[k] - 'a']) {
						break;
					} else if (ints[before[k] - 'a'] > ints[after[k] - 'a']) {
						return false;
					}
					if (k == after.length - 1) {
						return false;
					}
				}
				
			}
		}
		return true;
	}
	
	public int surfaceArea(int[][] grid) {
		int sum = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] > 0) {
					sum += (4 * grid[i][j]) + 2;
					if (i + 1 < grid.length && grid[i + 1][j] > 0) {
						sum -= Math.min(grid[i][j], grid[i + 1][j]) * 2;
					}
					if (j + 1 < grid[i].length && grid[i][j + 1] > 0) {
						sum -= Math.min(grid[i][j], grid[i][j + 1]) * 2;
					}
				}
			}
		}
		return sum;
	}
	
	private static int getMax(int[] a, int b) {
		while (b >= 0) {
			if (a[b] > 0) {
				a[b]--;
				return b;
			} else {
				b--;
			}
		}
		
		return 0;
	}
	
	public static int[] fairCandySwap(int[] A, int[] B) {
		int[] a = new int[27];
		int[] b = new int[27];
		int x = 0;
		for (int i = 0; i < A.length; i++) {
			a[A[i]]++;
			x += A[i];
		}
		for (int i = 0; i < B.length; i++) {
			b[B[i]]++;
			x -= B[i];
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i] > 0) {
				if (b[i - (x / 2)] > 0) {
					return new int[]{i, i - (x / 2)};
				}
			}
		}
		return null;
	}
	
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode right = root.right;
		root.right = root.left;
		root.left = right;
		invertTree(right);
		invertTree(root.right);
		return root;
	}
	
	public String toGoatLatin(String S) {
		int[] ints = {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0};
		StringBuffer ab = new StringBuffer("maa");
		StringBuffer sb = new StringBuffer();
		
		for (String s : S.split(" ")) {
			if (ints[Character.toLowerCase(s.charAt(0)) - 'a'] == 0) {
				sb.append(s.substring(1, s.length() - 1)).append(s.charAt(0)).append(ab.toString())
					.append(" ");
			} else {
				sb.append(s + ab.toString()).append(" ");
			}
			ab.append("a");
		}
		return sb.toString().substring(0, sb.length() - 2);
	}
	
	public List<Double> averageOfLevels(TreeNode root) {
		List<NumPair> list = new ArrayList<>();
		averageOfLevelsHelper(root, list, 0);
		ArrayList<Double> doubles = new ArrayList<>(list.size());
		for (NumPair numPair : list) {
			doubles.add(((double) numPair.sum) / numPair.num);
		}
		return doubles;
	}
	
	public boolean hasAlternatingBits(int n) {
		int temp = (n & 1);
		n >>= 1;
		while (n > 0) {
			if (temp == (n & 1)) {
				return false;
			}
			temp = (n & 1);
			n >>= 1;
		}
		return true;
	}
	
	public static int bitwiseComplement(int N) {
		int[] ints = new int[31];
		int index = 0;
		while (N > 0) {
			ints[index++] = (N & 1) == 1 ? 0 : 1;
			N >>= 1;
		}
		int ret = ints[--index];
		while (index > 0) {
			index--;
			ret <<= 1;
			ret += ints[index];
		}
		return ret;
	}
	
	private void averageOfLevelsHelper(TreeNode root, List<NumPair> list, int level) {
		if (root != null) {
			if (list.size() == level) {
				list.add(new NumPair(0, 0));
			}
			NumPair numPair = list.get(level);
			numPair.num++;
			numPair.sum += root.val;
			averageOfLevelsHelper(root.left, list, level + 1);
			averageOfLevelsHelper(root.right, list, level + 1);
		}
	}
	
	class NumPair {
		
		int num;
		double sum;
		
		public NumPair(int num, double sum) {
			this.num = num;
			this.sum = sum;
		}
	}
	
	public static int countPrimeSetBits(int L, int R) {
		int k;
		int num = 0;
		int sum = 0;
		int[] ints = new int[]{0, 0, 1, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 1, 0};
		for (int i = L; i <= R; i++) {
			k = i;
			while (k > 0) {
				num += ((k & 1) == 1 ? 1 : 0);
				k >>= 1;
			}
			sum += ints[num];
			num = 0;
		}
		return sum;
	}
	
	public int[][] matrixReshape(int[][] nums, int r, int c) {
		if (r * c != nums.length * nums[0].length) {
			return nums;
		}
		int x = 0;
		int y = 0;
		int[][] ints = new int[r][c];
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[i].length; j++) {
				ints[y][x++] = nums[i][j];
				if (x == c) {
					x = 0;
					y++;
				}
			}
		}
		
		return ints;
	}
	
	public List<List<Integer>> levelOrder(Node root) {
		List<List<Integer>> list = new ArrayList<>();
		levelOrderHelper(root, list, 1);
		return list;
	}
	
	private void levelOrderHelper(Node root, List<List<Integer>> lists,
		int level) {
		List<Integer> list;
		if (root != null) {
			if (lists.size() == level) {
				list = new ArrayList<>();
				lists.add(list);
			}
			list = lists.get(level);
			list.add(root.val);
			if (root.children.size() > 0) {
				for (Node child : root.children) {
					levelOrderHelper(child, lists, level + 1);
				}
			}
		}
	}
	
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
		int[] ints = new int[nums1.length];
		HashMap<Integer, Integer> map = new HashMap<>(nums2.length);
		for (int i = 0; i < nums2.length; i++) {
			map.put(nums2[i], i);
		}
		for (int i = 0; i < nums1.length; i++) {
			int temp = nums1[i];
			int index = map.get(temp);
			index++;
			while (index < nums2.length && nums2[index] < temp) {
				index++;
			}
			ints[i] = index < nums2.length ? nums2[index] : -1;
		}
		return ints;
	}
	
	public int singleNumber(int[] nums) {
		int ret = 0;
		for (int num : nums) {
			ret ^= num;
		}
		return ret;
	}
	
	public int distributeCandies(int[] candies) {
		HashSet<Integer> set = new HashSet<>();
		for (int candy : candies) {
			set.add(candy);
		}
		if (set.size() * 2 > candies.length) {
			return candies.length / 2;
		} else {
			return set.size();
		}
	}
	
	public List<String> fizzBuzz(int n) {
		ArrayList<String> list = new ArrayList<>(n);
		for (int i = 1; i <= n; i++) {
			String s = "";
			if (i % 3 == 0) {
				s += "Fizz";
			}
			if (i % 5 == 0) {
				s += "Buzz";
			}
			if ("".equals(s)) {
				s += i;
			}
			list.add(s);
		}
		return list;
	}
	
	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
		}
	}
	
	
	public int islandPerimeter(int[][] grid) {
		int sum = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] > 0) {
					sum += islandPerimeterHelper(i, j, grid);
				}
			}
		}
		return sum;
	}
	
	private int islandPerimeterHelper(int i, int j, int[][] grid) {
		int sum = 0;
		//shang
		if (i - 1 < 0) {
			sum++;
		} else {
			if (grid[i - 1][j] == 0) {
				sum++;
			}
		}
		//xia
		if (i + 1 == grid.length) {
			sum++;
		} else {
			if (grid[i + 1][j] == 0) {
				sum++;
			}
		}
		//zuo
		if (j - 1 < 0) {
			sum++;
		} else {
			if (grid[i][j - 1] == 0) {
				sum++;
			}
		}
		//you
		if (j + 1 == grid[i].length) {
			sum++;
		} else {
			if (grid[i][j + 1] == 0) {
				sum++;
			}
		}
		return sum;
	}
	
	public int projectionArea(int[][] grid) {
		
		int total = 0;
		int l;
		int r;
		for (int i = 0; i < grid.length; i++) {
			l = 0;
			r = 0;
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] != 0) {
					total++;
					if (grid[i][j] > l) {
						l = grid[i][j];
					}
					if (grid[j][i] > r) {
						r = grid[j][i];
					}
				}
			}
			total += (l + r);
		}
		
		return total;
	}
	
	public int maxDepth(Node root) {
		if (root == null) {
			return 0;
		} else {
			int max = 0;
			for (Node child : root.children) {
				int i = maxDepth(child);
				if (i > max) {
					max = i;
				}
			}
			return 1 + max;
		}
	}
	
	public List<String> subdomainVisits(String[] cpdomains) {
		HashMap<String, Integer> map = new HashMap<>();
		for (String cpdomain : cpdomains) {
			String[] split = cpdomain.split(" ");
			int times = Integer.valueOf(split[0]);
			String s = split[1];
			do {
				map.put(s, map.getOrDefault(s, 0) + times);
				s = s.substring(s.indexOf('.') + 1, s.length());
			} while (s.indexOf(".") > 0);
			
		}
		ArrayList<String> list = new ArrayList<>(map.size());
		Set<Entry<String, Integer>> entries = map.entrySet();
		for (Entry<String, Integer> entry : entries) {
			list.add(entry.getValue() + " " + entry.getKey());
		}
		for (String s : map.keySet()) {
			list.add(map.get(s) + " " + s);
		}
		return list;
	}
	
	/*TreeNode treeNode = new TreeNode(5);
		treeNode.left=new TreeNode(3);
		treeNode.right=new TreeNode(6);
		treeNode.right.right=new TreeNode(8);
		treeNode.right.right.left=new TreeNode(7);
		treeNode.right.right.right=new TreeNode(9);
		treeNode.left.left=new TreeNode(2);
		treeNode.left.right=new TreeNode(4);
		treeNode.left.left.left=new TreeNode(1);
		TreeNode treeNode1 = increasingBST(treeNode);
		System.out.println(treeNode1);*/
	public static TreeNode increasingBST(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode newRoot = root;
		
		if (root.left != null) {
			newRoot = increasingBST(root.left);
			TreeNode endOfChain = root.left;
			while (endOfChain.right != null) {
				endOfChain = endOfChain.right;
			}
			endOfChain.right = root;
			root.left = null;
		}
		
		if (root.right != null) {
			root.right = increasingBST(root.right);
		}
		return newRoot;
		
		
	}
	
	public int calPoints(String[] ops) {
		ArrayList<Integer> integers = new ArrayList<>();
		int sum = 0;
		for (String op : ops) {
			switch (op) {
				case "C":
					sum -= integers.get(integers.size() - 1);
					integers.remove(integers.size() - 1);
					break;
				case "D":
					integers.add(integers.get(integers.size() - 1) * 2);
					sum += integers.get(integers.size() - 1);
					break;
				case "+":
					integers
						.add(integers.get(integers.size() - 1) + integers.get(integers.size() - 2));
					sum += integers.get(integers.size() - 1);
					break;
				default:
					integers.add(Integer.valueOf(op));
					sum += integers.get(integers.size() - 1);
			}
		}
		
		return sum;
	}
	
	public String[] uncommonFromSentences(String A, String B) {
		String[] as = A.split(" ");
		String[] bs = B.split(" ");
		HashMap<String, Integer> map = new HashMap<>();
		for (String a : as) {
			map.put(a, map.getOrDefault(a, 0) + 1);
		}
		for (String b : bs) {
			map.put(b, map.getOrDefault(b, 0) + 1);
		}
		
		HashSet<String> set = new HashSet<>();
		for (String s : map.keySet()) {
			if (map.get(s) == 1) {
				set.add(s);
			}
		}
		String[] strings = new String[set.size()];
		strings = set.toArray(strings);
		return strings;
	}
	
	public boolean isToeplitzMatrix(int[][] matrix) {
		int index = 0;
		int temp;
		for (int i = 0; i < matrix.length; i++) {
			temp = matrix[i][index];
			int k = i + 1;
			index++;
			while (k < matrix.length && index < matrix[i].length) {
				if (temp != matrix[k++][index++]) {
					return false;
				}
			}
			index = 0;
		}
		for (int i = 0; i < matrix[0].length; i++) {
			temp = matrix[index][i];
			int k = i + 1;
			index++;
			while (k < matrix[0].length && index < matrix.length) {
				if (temp != matrix[index++][k++]) {
					return false;
				}
			}
			index = 0;
		}
		return true;
	}
	
	public List<String> commonChars(String[] A) {
		int[] ints = new int[26];
		char[] chars = A[0].toCharArray();
		for (char aChar : chars) {
			ints[aChar - 'a']++;
		}
		int temp;
		for (int i = 1; i < A.length; i++) {
			chars = A[i].toCharArray();
			for (int j = 0; j < ints.length; j++) {
				if (ints[j] != 0) {
					temp = 0;
					for (int k = 0; k < chars.length; k++) {
						if (chars[k] - 'a' == j) {
							temp++;
						}
					}
					if (temp < ints[j]) {
						ints[j] = temp;
					}
				}
			}
		}
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < ints.length; i++) {
			while (ints[i] > 0) {
				list.add((char) (i + 'a') + "");
				ints[i]--;
			}
		}
		return list;
	}
	
	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null) {
			return null;
		} else if (root.val == val) {
			return root;
		} else if (root.val > val) {
			return searchBST(root.left, val);
		} else {
			return searchBST(root.right, val);
		}
	}
	
	public static int numRookCaptures(char[][] board) {
		char l = '.';
		int x = 0, y = 0, total = 0;
		for (int i = 0; i < board.length; i++) {
			l = '.';
			for (int j = 0; j < board[i].length; j++) {
				
				if (board[i][j] == 'R') {
					x = j;
					y = i;
					if (l == 'p') {
						total++;
					}
					j++;
					while (j < board[i].length) {
						if (board[i][j] == 'B') {
							break;
						} else if (board[i][j] == 'p') {
							total++;
							break;
						} else {
							j++;
						}
					}
				} else if (board[i][j] != '.') {
					l = board[i][j];
				}
			}
			if (board[y][x] == 'R') {
				break;
			}
		}
		int u = y - 1, d = y + 1;
		while (u >= 0) {
			if (board[u][x] == 'B') {
				break;
			} else if (board[u][x] == 'p') {
				total++;
				break;
			} else {
				u--;
			}
		}
		while (d < board.length) {
			if (board[d][x] == 'B') {
				break;
			} else if (board[d][x] == 'p') {
				total++;
				break;
			} else {
				d++;
			}
		}
		return total;
	}
	
	class Node {
		
		public int val;
		public List<Node> children;
		
		public Node() {
		}
		
		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	}
	
	
	public List<Integer> preorder(Node root) {
		ArrayList<Integer> integers = new ArrayList<>();
		preorderHelper(root, integers);
		return integers;
	}
	
	void preorderHelper(Node root, List<Integer> list) {
		list.add(root.val);
		if (root.children.size() > 0) {
			for (Node child : root.children) {
				preorderHelper(child, list);
			}
		} else {
			return;
		}
	}
	
	public List<Integer> postorder(Node root) {
		ArrayList<Integer> integers = new ArrayList<>();
		postorderHelper(root, integers);
		return integers;
	}
	
	void postorderHelper(Node root, List<Integer> list) {
		if (root.children.size() > 0) {
			for (Node child : root.children) {
				postorderHelper(child, list);
				list.add(root.val);
			}
		}
	}
	
	public boolean leafSimilar(TreeNode root1, TreeNode root2) {
		ArrayList<Integer> l = new ArrayList<>();
		ArrayList<Integer> r = new ArrayList<>();
		leafSimilarHelper(root1, l);
		leafSimilarHelper(root2, r);
		if (l.size() != r.size()) {
			return false;
		} else {
			for (int i = 0; i < l.size(); i++) {
				if (!l.get(i).equals(r.get(i))) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void leafSimilarHelper(TreeNode root1, List<Integer> list) {
		
		if (root1.right == null && root1.left == null) {
			list.add(root1.val);
		}
		
		if (root1.left != null) {
			leafSimilarHelper(root1.left, list);
		}
		if (root1.right != null) {
			leafSimilarHelper(root1.right, list);
		}
	}
	
	
	public int hammingDistance(int x, int y) {
		x = x ^ y;
		y = 0;
		while (x > 0) {
			if (x % 2 == 1) {
				y++;
			}
			x = x >> 1;
		}
		return y;
	}
	
	public static int reverse(int x) {
		long l = 0;
		int flag = x > 0 ? 1 : -1;
		x = x * flag;
		while (x > 0) {
			l = l * 10 + x % 10;
			x = x / 10;
		}
		l *= flag;
		if (l > Integer.MAX_VALUE || l < Integer.MIN_VALUE) {
			return 0;
		} else {
			return (int) l;
		}
	}
	
	public static int numUniqueEmails(String[] emails) {
		Set<String> set = new TreeSet();
		for (String email : emails) {
			String[] split = email.split("@");
			String[] split1 = split[0].split("\\+");
			String replace = split1[0].replace(".", "");
			String s = replace + split[1];
			if (set.contains(s)) {
				set.remove(s);
			} else {
				set.add(s);
			}
			
		}
		return set.size();
	}
	
	public static int numJewelsInStones(String J, String S) {
		int i = 0;
		for (char c : S.toCharArray()) {
			i += J.indexOf(c) != -1 ? 1 : 0;
		}
		return i;
	}
	
	public int uniqueMorseRepresentations(String[] words) {
		String[] code = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
			"-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-",
			".--", "-..-", "-.--", "--.."};
		StringBuffer buffer = new StringBuffer();
		TreeSet<String> set = new TreeSet<>();
		for (String word : words) {
			char[] chars = word.toCharArray();
			for (char aChar : chars) {
				buffer.append(code[aChar - 'a']);
			}
			set.add(buffer.toString());
			buffer.setLength(0);
		}
		return set.size();
	}
	
	public int repeatedNTimes(int[] A) {
		HashSet<Integer> set = new HashSet<>();
		for (int i : A) {
			if (set.contains(i)) {
				return i;
			} else {
				set.add(i);
			}
		}
		return 0;
	}
	
	public int[] sortedSquares(int[] A) {
		int[] ints = new int[A.length];
		int index_0 = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] >= 0) {
				index_0 = i;
				if (i > 0) {
					if (Math.abs(A[index_0 - 1]) < A[index_0]) {
						index_0--;
					}
				}
				break;
			}
		}
		ints[0] = A[index_0] * A[index_0];
		int l = index_0 - 1;
		int r = index_0 + 1;
		int index = 1;
		while (l >= 0 || r < A.length) {
			if (l < 0) {
				ints[index] = A[r] * A[r];
				r++;
			} else if (r >= A.length) {
				ints[index] = A[l] * A[l];
				l--;
			} else {
				if (Math.abs(A[l]) < A[r]) {
					ints[index] = A[l] * A[l];
					l--;
				} else {
					ints[index] = A[r] * A[r];
					r++;
				}
			}
			index++;
		}
		return ints;
	}
	
	public int[] sortArrayByParity(int[] A) {
		int l = 0;
		int r = A.length - 1;
		int temp;
		while (l <= r) {
			if (A[l] % 2 == 0) {
				l++;
			} else if (A[r] % 2 != 0) {
				r--;
			} else {
				temp = A[l];
				A[l] = A[r];
				A[r] = temp;
			}
		}
		return A;
	}
	
	public int[][] flipAndInvertImage(int[][] A) {
		int temp;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < (A[i].length) / 2; j++) {
				if (j == A[i].length - 1 - j) {
					A[i][j] = A[i][j] ^ 1;
				} else {
					temp = A[i][j];
					A[i][j] = A[i][A.length - 1 - j] ^ 1;
					A[i][A.length - j - 1] = temp ^ 1;
				}
			}
		}
		return A;
	}
	
	public static boolean judgeCircle(String moves) {
		int u = 0;
		int d = 0;
		int l = 0;
		int r = 0;
		char[] chars = moves.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			switch (chars[i]) {
				case 'U':
					u++;
					break;
				case 'D':
					d++;
					break;
				case 'L':
					l++;
					break;
				case 'R':
					r++;
					break;
				
			}
		}
		return u - d == 0 && l - r == 0;
	}
	
	public int minDeletionSize(String[] A) {
		int r = 0;
		char[][] chars = new char[A.length][A[0].length()];
		for (int i = 0; i < A.length; i++) {
			chars[i] = A[i].toCharArray();
		}
		
		for (int j = 0; j < chars[0].length; j++) {
			for (int i = 0; i < chars.length - 1; i++) {
				if (chars[i][j] - chars[i + 1][j] > 0) {
					r++;
					break;
				}
			}
		}
		return r;
	}
	
	public int[] diStringMatch(String S) {
		int h = S.length();
		int l = 0;
		int[] ints = new int[h];
		int index = 0;
		for (char c : S.toCharArray()) {
			switch (c) {
				case 'I':
					ints[index++] = l++;
					break;
				case 'D':
					ints[index++] = h--;
					break;
			}
		}
		return ints;
	}
	
	public int[] numberOfLines(int[] widths, String S) {
		char[] chars = S.toCharArray();
		int l = 1;
		int index = 0;
		for (int i = 0; i < chars.length; i++) {
			index += chars[i];
			if (index > 100) {
				l++;
				i--;
				index = 0;
			}
		}
		return new int[]{l, index};
	}
	
	public int findComplement(int num) {
		int c = 1;
		int k = num;
		while (k > 0) {
			k = k >> 1;
			c = c << 1 + 1;
		}
		return num ^ c;
	}
	
	public String[] findWords(String[] words) {
		ArrayList<String> list = new ArrayList<>();
		
		return list.toArray(new String[list.size()]);
	}
	
	public int numSpecialEquivGroups(String[] A) {
		HashSet<String> strings = new HashSet<>();
		for (String s : A) {
			char[] e = new char[26];
			char[] o = new char[26];
			char[] chars = s.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if ((i & 1) == 1) {
					e[chars[i] - 'a']++;
				} else {
					o[chars[i] - 'a']++;
				}
			}
			strings.add(String.valueOf(e) + String.valueOf(o));
		}
		return strings.size();
	}
	
	
	public static List<Integer> selfDividingNumbers(int left, int right) {
		ArrayList<Integer> integers = new ArrayList<>();
		int k;
		int j;
		for (Integer i = left; i <= right; i++) {
			k = i;
			while (k > 0) {
				j = k % 10;
				if (j == 0 || i % j != 0) {
					break;
				}
				k /= 10;
			}
			if (k == 0) {
				integers.add(i);
			}
		}
		return integers;
	}
	
	public int peakIndexInMountainArray(int[] A) {
		int l = 0;
		int r = A.length - 1;
		int m = r / 2;
		while (l < r) {
			if (A[m] > A[m + 1] && A[m] > A[m - 1]) {
				return m;
			} else if (A[m] > A[m + 1] && A[m - 1] > A[m]) {
				r = m;
			} else {
				l = m;
			}
			m = (r + l) / 2;
		}
		return m;
	}
	
	public static int diameterOfBinaryTree(TreeNode root) {
		HashMap<String, Integer> map = new HashMap<>(2);
		
		max = 0;
		add(root);
		return max;
		
	}
	
	//diameterOfBinaryTree参数
	static int max;
	
	private static int add(TreeNode root) {
		if (root == null) {
			return 0;
		} else {
			int i = add(root.left);
			int j = add(root.right);
			if (max < i + j) {
				max = i + j;
			}
			return 1 + (i > j ? i : j);
		}
	}
	
	public static int arrayPairSum(int[] nums) {
		/*int[] ints = new int[9];
		for (int num : nums) {
			ints[num+4]++;
		}
		int sum=0;
		int k=1;
		for (int i = 0; i < ints.length; i++) {
			if(ints[i]!=0){
				sum+=(i-4)*((ints[i]+k)/2);
				k=(ints[i]+k)%2;
			}
		}*/
		Arrays.sort(nums);
		int sum = 0;
		for (int i = 0; i < nums.length; i += 2) {
			sum += nums[i];
		}
		return sum;
	}
	
	public static int fib(int N) {
		if (N < 2) {
			return N;
		}
		int fn_2 = 0;
		int fn_1 = 1;
		int fn = 1;
		for (int i = 2; i <= N; i++) {
			fn = fn_1 + fn_2;
			fn_2 = fn_1;
			fn_1 = fn;
		}
		return fn;
	}
	
	public int[] sortArrayByParityII(int[] A) {
		
		int e = 1;
		int o = 0;
		int temp;
		while (e < A.length && o < A.length) {
			while (e < A.length && (A[e] & 1) != 1) {
				e += 2;
			}
			while (o < A.length && (A[o] & 1) == 1) {
				o += 2;
			}
			temp = A[o];
			A[e] = A[o];
			A[o] = temp;
		}
		return A;
	}
	
	public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
		int[] ints = new int[queries.length];
		int sum = 0;
		for (int i : A) {
			if ((i & 1) != 1) {
				sum += i;
			}
		}
		int temp;
		int k;
		for (int i = 0; i < queries.length; i++) {
			k = queries[i][1];
			temp = queries[i][0] + A[k];//新值
			if ((A[k] & 1) == 1) {//原奇数
				if ((temp & 1) != 1) {//现偶数
					sum += temp;
					
				}
			} else {//原偶数
				sum -= A[k];
				if ((temp & 1) != 1) {//现偶数
					sum += temp;
				}
			}
			A[k] = temp;
			ints[i] = sum;
		}
		return ints;
	}
	
	public int smallestRangeI(int[] A, int K) {
		int max = A[0];
		int min = A[0];
		for (int i : A) {
			if (i > max) {
				max = i;
			}
			if (i < min) {
				min = i;
			}
		}
		max = (max - min - 2 * K);
		return max > 0 ? max : 0;
	}
	
	public ListNode middleNode(ListNode head) {
		if (head.next == null) {
			return head;
		}
		if (head.next.next == null) {
			return head.next;
		}
		ListNode s = head.next;
		ListNode f = head.next.next;
		while (f != null) {
			if (f.next != null) {
				s = s.next;
			}
			f = f.next.next;
		}
		return s;
	}
	
	public static String reverseWords(String s) {
		char[] chars = s.toCharArray();
		int begin = 0;
		int end = 0;
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != ' ') {
				end++;
			} else {
				revers(chars, begin, end - 1);
				begin = end + 1;
			}
		}
		return String.valueOf(chars);
	}
	
	private static void revers(char[] chars, int begin, int end) {
		char c;
		while (begin < end) {
			c = chars[begin];
			chars[begin] = chars[end];
			chars[end] = c;
			begin++;
			end--;
		}
	}
	
	public void reverseString(char[] s) {
		int l = 0;
		int r = s.length - 1;
		char c;
		while (l < r) {
			c = s[l];
			s[l] = s[r];
			s[r] = c;
		}
	}
	
	public static String reverseStr(String s, int k) {
		char[] chars = s.toCharArray();
		int index = 2 * k;
		while (index < chars.length - 1) {
			revers(chars, index - 2 * k, index - k - 1);
			index += (2 * k);
		}
		index -= (2 * k);
		
		if (chars.length - index >= k) {
			revers(chars, index, index + k - 1);
		} else {
			revers(chars, index, chars.length - 1);
		}
		
		return String.valueOf(chars);
	}
	
	public int[][] transpose(int[][] A) {
		int[][] ints = new int[A[0].length][A.length];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				ints[j][i] = A[i][j];
			}
		}
		return ints;
	}
	
	public int[] shortestToChar(String S, char C) {
		char[] chars = S.toCharArray();
		int[] ints = new int[chars.length];
		int begin = 0, end = 0;
		int j;
		int k = 1;
		int i = 0;
		while (chars[i] != C) {
			i++;
		}
		ints[i] = 0;
		j = i - 1;
		while (j >= 0) {
			ints[j] = k;
			k++;
			j--;
		}
		begin = i;
		i++;
		for (; i < chars.length; i++) {
			while (i < chars.length && chars[i] != C) {
				i++;
			}
			if (i >= chars.length) {
				break;
			}
			end = i;
			k = 1;
			begin += 1;
			end -= 1;
			while (begin <= end) {
				ints[begin] = k;
				ints[end] = k;
				begin++;
				end--;
				k++;
			}
			begin = i;
		}
		k = 1;
		while (begin < chars.length - 1) {
			begin++;
			ints[begin] = k;
			k++;
		}
		return ints;
	}
}

//Definition for a binary tree node.

class TreeNode {
	
	int val;
	TreeNode left;
	TreeNode right;
	
	TreeNode(int x) {
		val = x;
	}
}

class ListNode {
	
	int val;
	ListNode next;
	
	ListNode(int x) {
		val = x;
	}
}
