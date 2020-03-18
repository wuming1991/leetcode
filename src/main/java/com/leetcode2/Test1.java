package com.leetcode2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class Test1
 * @Author: WuMing
 * @CreateDate: 2020/1/16 11:07
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test1 {
	
	static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
	}
	
	public int minSetSize(int[] arr) {
		int[] count = new int[100001];
		for (int i : arr) {
			count[i]++;
		}
		int len = arr.length;
		int ret = 1, t = 0;
		Arrays.sort(count);
		for (int i = count.length - 1; i >= 0; i--) {
			t += count[i];
			if (t >= len / 2) {
				return ret;
			}
			ret++;
		}
		return ret;
	}
	
	public static void main(String[] args) throws ParseException {
		
		Test1 test = new Test1();
		test.longestValidParentheses(")()())(((((())))()()()((");
		
	}
	//1370
	public String sortString(String s) {
		int[] count = new int[26];
		int len = s.length();
		char[] ret = new char[len];
		for (int i = 0; i < len; i++) {
			count[s.charAt(i)-'a']++;
		}
		int c=0;
		while (c<len){
			for (int i = 0; i < 26; i++) {
				if(count[i]>0){
					ret[c++]=(char)(i+'a');
				}
			}
			for (int i = 26; i > 0; i--) {
				if(count[i]>0){
					ret[c++]=(char)(i+'a');
				}
			}
		}
		return new String(ret);
	}
	//面试题 01.06. 字符串压缩
	public String compressString(String S) {
		int len = S.length();
		if(len==0){
			return S;
		}
		char cur=S.charAt(0);
		int count=0;
		StringBuffer buffer = new StringBuffer();
		for (char c : S.toCharArray()) {
			if(c==cur){
				count++;
			}else{
				buffer.append(cur);
				buffer.append(count);
				cur=c;
				count=1;
				if(buffer.length()+2>=len){
					return S;
				}
			}
		}
		buffer.append(cur);
		buffer.append(count);
		if(buffer.length()>=len){
			return S;
		}else{
			return buffer.toString();
		}
	}
	//面试题 01.05. 一次编辑
	public boolean oneEditAway(String first, String second) {
		int l1 = first.length();
		int l2 = second.length();
		int c=0,i=0,j=0;
		if(l1-l2==0){//替换或不变
			for (; i < l1; i++) {
				if(first.charAt(i)!=second.charAt(i)){
					c++;
				}
				if(c>1){
					return false;
				}
			}
		}else if(l1-l2==-1){//插入
			 if(l1==0){
			 	return true;
			 }
			 while (i<l1&&first.charAt(i)==second.charAt(j)){
			 	i++;j++;
			 }
			 j++;
			 while (i<l1&&first.charAt(i)==second.charAt(j)){
			 	i++;j++;
			 }
			 if(i<l1){
			 	return false;
			 }
		}else if(l1-l2==1){//删除
			 return oneEditAway(second,first);
		}else{
			return false;
		}
		return true;
	}
	//面试题 01.04. 回文排列
	public boolean canPermutePalindrome(String s) {
		int[] count = new int[26];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			count[s.charAt(i)-'a']++;
		}
		int c=0;
		for (int i : count) {
			c+=(i&1);
			if(c>1){
				return false;
			}
		}
		return true;
	}
	//32
	public int longestValidParentheses(String s) {
		Stack<Integer> stack = new Stack<>();
		int ret = 0;
		int len = s.length();
		char c;
		stack.push(-1);
		int start=-1;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c == '(') {
				stack.push(i);
			} else {
				if(stack.peek()>=0){
					stack.pop();
					if(stack.peek()<0){
						ret= Math.max(ret,i-start);
					}else{
						ret= Math.max(ret,i-stack.peek());
					}
				}else{
					start=i;
				}
			}
		}
		return ret;
	}
	
	public int countCharacters(String[] words, String chars) {
		int[] count = new int[26];
		for (char c : chars.toCharArray()) {
			count[c - 'a']++;
		}
		char c;
		int ret = 0;
		for (String word : words) {
			int length = word.length(), i = 0;
			int[] copy = Arrays.copyOf(count, 26);
			for (; i < length; i++) {
				c = word.charAt(i);
				if (copy[c - 'a'] > 0) {
					copy[c - 'a']--;
				} else {
					break;
				}
			}
			if (i == length) {
				ret += length;
			}
		}
		return ret;
	}
	
	//1351
	public int countNegatives(int[][] grid) {
		int high = grid.length;
		int len = grid[0].length;
		int ret = 0, l, m, r;
		for (int i = 0; i < high; i++) {
			int[] ints = grid[i];
			l = 0;
			r = len - 1;
			if (ints[l] < 0) {
				ret += len;
				continue;
			}
			while (l <= r) {
				m = (l + r) / 2;
				if (ints[m] >= 0) {
					l = m + 1;
				} else {
					r = m - 1;
				}
			}
			ret += len - l;
		}
		return ret;
	}
	
	//1732
	int longestZigZag = 0;
	
	public int longestZigZag(TreeNode root) {
		if (root == null) {
			return 0;
		}
		longestZigZagHelper(root.left, 0, 0);
		longestZigZagHelper(root.right, 1, 0);
		return longestZigZag;
	}
	
	private void longestZigZagHelper(TreeNode root, int from, int count) {
		if (root == null) {
			longestZigZag = Math.max(longestZigZag, count);
			return;
		}
		longestZigZagHelper(root.left, 0, from == 1 ? count + 1 : 0);
		longestZigZagHelper(root.right, 1, from == 0 ? count + 1 : 0);
	}
	
	int maxSumBST = 0;
	
	//1373
	public int maxSumBST(TreeNode root) {
		int[] ret = maxSumBSTHelper(root);
		return maxSumBST;
	}
	
	private int[] maxSumBSTHelper(TreeNode root) {
		int[] ret = {root.val, root.val, root.val}, left, right;
		if (root.left != null) {
			left = maxSumBSTHelper(root.left);
			if (left[1] >= ret[0]) {
				ret = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
				//return ret;
			} else {
				ret[0] = left[0];
				ret[2] += left[2];
			}
		}
		if (root.right != null) {
			right = maxSumBSTHelper(root.right);
			if (ret[1] >= right[0]) {
				ret = new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, -1};
				//return ret;
			} else {
				ret[1] = right[1];
				ret[2] += right[2];
			}
		}
		if (ret[2] > maxSumBST) {
			maxSumBST = ret[2];
		}
		return ret;
	}
	
	//1375
	public int numTimesAllBlue(int[] light) {
		int blue = 0;
		int len = light.length;
		boolean[] flag = new boolean[len + 1];
		int ret = 0;
		for (int i = 0; i < len; i++) {
			flag[light[i]] = true;
			while (flag[blue]) {
				blue++;
			}
			if (blue == i + 1) {
				ret++;
			}
		}
		return ret;
	}
	
	//1379
	public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned,
		final TreeNode target) {
		LinkedList<Integer> list = new LinkedList<>();
		boolean find = originalPath(list, original, target);
		
		TreeNode ret = cloned;
		while (!list.isEmpty()) {
			Integer x = list.removeLast();
			if (x == 0) {
				ret = ret.left;
			} else {
				ret = ret.right;
			}
		}
		return ret;
		
	}
	
	private boolean originalPath(LinkedList<Integer> list, TreeNode root, TreeNode target) {
		if (root == null) {
			return false;
		}
		if (root == target) {
			return true;
		}
		if (originalPath(list, root.left, target)) {
			list.add(0);
			return true;
		}
		if (originalPath(list, root.right, target)) {
			list.add(1);
			return true;
		}
		return false;
	}
	
	//1380
	public List<Integer> luckyNumbers(int[][] matrix) {
		int high = matrix.length;
		int[] min = new int[high];
		int len = matrix[0].length;
		int[] max = new int[len];
		Arrays.fill(min, Integer.MAX_VALUE);
		int x;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				x = matrix[i][j];
				min[i] = Math.min(x, min[i]);
				max[j] = Math.max(x, max[j]);
			}
		}
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> list = new ArrayList<>();
		for (int i : max) {
			set.add(i);
		}
		for (int i : min) {
			if (!set.add(i)) {
				list.add(i);
			}
		}
		return list;
	}
	
	//1347
	public int minSteps(String s, String t) {
		int[] count = new int[26];
		int len = s.length();
		char c;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			count[c - 'a']++;
			c = t.charAt(i);
			count[c - 'a']--;
		}
		int ret = 0;
		for (int i : count) {
			if (i > 0) {
				ret += i;
			}
		}
		return ret;
	}
	
	//面试题28. 对称的二叉树
	public boolean isSymmetric(TreeNode root) {
		if (root == null) {
			return true;
		}
		return isSymmetricHelper(root.left, root.right);
	}
	
	private boolean isSymmetricHelper(TreeNode left, TreeNode right) {
		if (left == null && right == null) {
			return true;
		} else if (left == null || right == null) {
			return false;
		}
		return left.val == right.val && isSymmetricHelper(left.left, right.right)
			&& isSymmetricHelper(left.right, right.left);
	}
	
	//面试题56 - I. 数组中数字出现的次数
	public int[] singleNumbers(int[] nums) {
		int t = 0;
		for (int num : nums) {
			t ^= num;
		}
		int mask = 1;
		while ((t & mask) == 0) {
			mask <<= 1;
		}
		int a = 0;
		for (int num : nums) {
			if ((num & mask) > 0) {
				a ^= num;
			}
		}
		return new int[]{a, a ^ t};
	}
	
	TreeNode lowestCommonAncestor;
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		boolean[] result = lowestCommonAncestorHelper(root, p, q);
		return lowestCommonAncestor;
	}
	
	//面试题53 - II. 0～n-1中缺失的数字
	public int missingNumber(int[] nums) {
		int len = nums.length;
		int l = 0, r = len, m;
		while (l <= r) {
			m = (l + r) / 2;
			if (m == len) {
				return m;
			}
			if (nums[m] == m) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return l;
	}
	
	private boolean[] lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		boolean[] ret = new boolean[2];
		if (root == null) {
			return ret;
		}
		if (root == p) {
			ret[0] = true;
		} else if (root == q) {
			ret[1] = true;
		}
		boolean[] left = lowestCommonAncestorHelper(root.left, p, q);
		boolean[] right = lowestCommonAncestorHelper(root.right, p, q);
		ret[0] = ret[0] || left[0] || right[0];
		ret[1] = ret[1] || left[1] || right[1];
		if (lowestCommonAncestor == null && ret[0] && ret[1]) {
			lowestCommonAncestor = root;
		}
		return ret;
	}
	//1345
	
	public int minJumps1(int[] arr) {
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		int len = arr.length;
		Set<Integer> set, cur, next;
		for (int i = 0; i < len; i++) {
			if (map.containsKey(arr[i])) {
				set = map.get(arr[i]);
			} else {
				set = new HashSet<>();
				map.put(arr[i], set);
			}
			set.add(i);
		}
		boolean[] visit = new boolean[len];
		visit[0] = true;
		int ret = 0;
		cur = new HashSet<>();
		cur.add(0);
		HashSet<Integer> same = new HashSet<>();
		while (!visit[len - 1]) {
			ret++;
			next = new HashSet<>();
			for (Integer c : cur) {
				if (c > 0 && !visit[c - 1]) {
					visit[c - 1] = true;
					next.add(c - 1);
				}
				if (c + 1 < len && !visit[c + 1]) {
					visit[c + 1] = true;
					next.add(c + 1);
				}
				
				Set<Integer> s = map.get(arr[c]);
				if (s != null) {
					for (Integer x : s) {
						if (!visit[x]) {
							visit[x] = true;
							next.add(x);
						}
					}
					map.remove(arr[c]);
				}
				
			}
			cur = next;
		}
		return ret;
	}
	
	public int minJumps(int[] arr) {
		int len = arr.length;
		boolean[] visit = new boolean[len];
		//Arrays.fill(arr, Integer.MAX_VALUE);
		ArrayList<Integer> list = new ArrayList<>(), nlist;
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		HashSet<Integer> set = new HashSet<>();
		list.add(0);
		visit[0] = true;
		int ret = 0;
		while (!visit[len - 1]) {
			ret++;
			nlist = new ArrayList<>();
			set.clear();
			for (Integer c : list) {
				if (c > 0 && !visit[c - 1]) {
					visit[c - 1] = true;
					nlist.add(c - 1);
				}
				if (c + 1 < len && !visit[c + 1]) {
					nlist.add(c + 1);
					visit[c + 1] = true;
				}
				set.add(arr[c]);
			}
			for (int i = 0; i < len; i++) {
				if (!visit[i] && set.contains(arr[i])) {
					visit[i] = true;
					nlist.add(i);
				}
			}
			list = nlist;
		}
		return ret;
	}
	
	//1340
	public int maxJumps(int[] arr, int d) {
		int len = arr.length;
		int ret = 0;
		int[] count = new int[len];
		for (int i = 0; i < len; i++) {
			if (count[i] == 0) {
				count[i] = maxJumpsHelper(i, arr, count, d);
			}
			ret = Math.max(ret, count[i]);
		}
		return ret;
	}
	
	private int maxJumpsHelper(int c, int[] arr, int[] count, int d) {
		int ret = 0;
		int len = arr.length;
		if (count[c] > 0) {
			return count[c];
		}
		boolean l = false, r = false;
		for (int i = 1; i <= d; i++) {
			if (!l) {
				if (c - i >= 0 && arr[c] > arr[c - i]) {
					ret = Math.max(ret, 1 + maxJumpsHelper(c - i, arr, count, d));
				} else {
					l = true;
				}
			}
			if (!r) {
				if (c + i < len && arr[c] > arr[c + i]) {
					ret = Math.max(ret, 1 + maxJumpsHelper(c + i, arr, count, d));
				} else {
					r = true;
				}
			}
			if (l && r) {
				break;
			}
		}
		count[c] = ret;
		return ret;
	}
	
	//1329
	public int[][] diagonalSort(int[][] mat) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		int high = mat.length;
		int len = mat[0].length;
		List<Integer> list;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (map.containsKey(i - j)) {
					list = map.get(i - j);
				} else {
					list = new ArrayList<>();
					map.put(i - j, list);
				}
				list.add(mat[i][j]);
			}
		}
		for (int i = 1; i < high; i++) {
			int x = i, y = 0;
			list = map.get(x - y);
			list.sort((a, b) -> (a - b));
			for (Integer c : list) {
				mat[x][y] = c;
				x++;
				y++;
			}
		}
		for (int j = 0; j < len; j++) {
			int x = 0, y = j;
			list = map.get(x - y);
			list.sort((a, b) -> (a - b));
			for (Integer c : list) {
				mat[x][y] = c;
				x++;
				y++;
			}
		}
		return mat;
	}
	
	//
	public String generateTheString(int n) {
		char[] chars = new char[n];
		Arrays.fill(chars, 'a');
		if ((n & 1) != 0) {
			chars[0] = 'b';
		}
		return new String(chars);
	}
	
	//1339
	public int maxProduct(TreeNode root) {
		HashSet<Long> set = new HashSet<>();
		long total = maxProductHelper(root, set);
		long ret = 0, x;
		long mod = 1000000007;
		for (long i : set) {
			x = (total - i) * i;
			if (ret < x) {
				ret = x;
			}
		}
		
		return (int) (ret % mod);
	}
	
	private long maxProductHelper(TreeNode root, Set<Long> set) {
		if (root == null) {
			return 0;
		}
		long val = root.val + maxProductHelper(root.left, set) + maxProductHelper(root.right, set);
		set.add(val);
		return val;
		
		
	}
	
	//1376
	public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
		int[] cost = new int[n];
		int ret = 0;
		for (int i = 0; i < n; i++) {
			if (cost[i] == 0 && i != headID) {
				cost[i] = numOfMinutesHelper(manager[i], manager, informTime, cost);
			}
			ret = Math.max(ret, cost[i]);
		}
		return ret;
	}
	
	private int numOfMinutesHelper(int i, int[] manager, int[] informTime, int[] cost) {
		if (i < 0) {
			return 0;
		}
		if (cost[i] > 0) {
			return cost[i] + informTime[i];
		}
		cost[i] = numOfMinutesHelper(manager[i], manager, informTime, cost);
		return informTime[i] + cost[i];
	}
	
	int minCost;
	
	//1368:会超时
	public int minCost(int[][] grid) {
		int high = grid.length;
		int len = grid[0].length;
		minCost = high + len - 1;
		int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		boolean[][] mem = new boolean[high][len];
		mem[0][0] = true;
		minCostHelper(0, 0, 0, grid, high - 1, len - 1, ds, mem);
		return minCost;
	}
	
	private void minCostHelper(int i, int j, int cost, int[][] grid, int ti, int tj, int[][] ds,
		boolean[][] mem) {
		if (i == ti && j == tj) {
			minCost = Math.min(minCost, cost);
			return;
		} else if (cost >= minCost) {
			return;
		}
		int cur = grid[i][j] - 1, ni, nj;
		for (int k = 0; k < 4; k++) {
			ni = i + ds[k][0];
			nj = j + ds[k][1];
			if (ni > ti || nj > tj || ni < 0 || nj < 0 || mem[ni][nj]) {
				continue;
			} else {
				mem[ni][nj] = true;
				minCostHelper(ni, nj, cost + (cur == k ? 0 : 1), grid, ti, tj, ds, mem);
				mem[ni][nj] = false;
			}
		}
	}
	
	
	//1358
	public int numberOfSubstrings(String s) {
		int[] count = new int[3];
		int len = s.length();
		int l = 0, ret = 0;
		for (int i = 0; i < len; i++) {
			count[s.charAt(i) - 'a']++;
			while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
				ret += len - i;
				count[s.charAt(l) - 'a']--;
				l++;
			}
		}
		return ret;
	}
	
	//1366
	public String rankTeams(String[] votes) {
		HashMap<Character, int[]> map = new HashMap<>();
		int len = votes.length;
		char[] array = votes[0].toCharArray();
		int length = array.length;
		int[][] mem = new int[length][28];
		for (int i = 0; i < length; i++) {
			map.put(array[i], mem[i]);
			mem[i][27] = 'Z' - array[i];
		}
		int[] ints;
		for (int i = 0; i < len; i++) {
			array = votes[i].toCharArray();
			for (int j = 0; j < length; j++) {
				ints = map.get(array[j]);
				//ints[0]+=length-j;
				ints[j + 1]++;
			}
		}
		Arrays.sort(mem, (a, b) -> {
			int i = 0;
			while (a[i] == b[i]) {
				i++;
			}
			return b[i] - a[i];
		});
		StringBuffer buffer = new StringBuffer();
		for (int[] cur : mem) {
			buffer.append((char) ('Z' - cur[27]));
		}
		return buffer.toString();
	}
	
	//1365
	public int[] smallerNumbersThanCurrent(int[] nums) {
		int[] count = new int[101];
		for (int num : nums) {
			count[num]++;
		}
		int last = count[0], t;
		count[0] = 0;
		for (int i = 1; i < 101; i++) {
			t = count[i];
			count[i] = last;
			last += t;
		}
		int len = nums.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[i] = count[nums[i]];
		}
		return ret;
		
	}
	
	//1363
	public String largestMultipleOfThree(int[] digits) {
		int[] count = new int[10];
		int sum = 0;
		int c = 0;
		for (int digit : digits) {
			sum += digit;
			count[digit]++;
			if (digit > 0) {
				c++;
			}
		}
		if (sum % 3 != 0) {
			int i = 1;
			for (i = 1; i <= c; i++) {
				if (largestMultipleOfThreeHelper(count, 3 - sum % 3, i)) {
					break;
				}
			}
			if (i > c) {
				if (count[0] > 0) {
					return "0";
				}
				return "";
			}
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 9; i > 0; i--) {
			while (count[i] > 0) {
				buffer.append(i);
				count[i]--;
			}
		}
		if (count[0] > 0) {
			if (buffer.length() > 0) {
				while (count[0] > 0) {
					buffer.append(0);
					count[0]--;
				}
			} else {
				buffer.append(0);
			}
		}
		return buffer.toString();
	}
	
	private boolean largestMultipleOfThreeHelper(int[] count, int sum, int l) {
		if (l == 0) {
			return sum % 3 == 0;
		}
		for (int j = 1; j <= 9; j++) {
			if (count[j] > 0) {
				count[j]--;
				if (largestMultipleOfThreeHelper(count, sum + j, l - 1)) {
					return true;
				}
				count[j]++;
			}
		}
		return false;
	}
	
	public int[] sortByBits(int[] arr) {
		int len = arr.length, t, c;
		int[][] ints = new int[len][2];
		for (int i = 0; i < len; i++) {
			c = 0;
			t = arr[i];
			ints[i][0] = t;
			while (t > 0) {
				c += t & 1;
				t >>= 1;
			}
			ints[i][1] = c;
		}
		Arrays.sort(ints, (a, b) -> {
			if (a[1] == b[1]) {
				return a[0] - b[0];
			} else {
				return a[1] - b[1];
			}
		});
		for (int i = 0; i < len; i++) {
			arr[i] = ints[i][0];
		}
		return arr;
	}
	
	//1354
	public boolean isPossible(int[] target) {
		if (target.length < 2) {
			return target[0] == 1;
		}
		TreeSet<Integer> set = new TreeSet<>();
		int total = 0;
		for (int i : target) {
			set.add(i);
			total += i;
		}
		Integer last = set.last();
		while (last > 1) {
			set.remove(last);
			int other = total - last;
			int me = (last) % other;
			total -= (last / other) * other;
			
			if (1 == other) {
				return true;
			} else if (me == 0 || me == last) {
				return false;
			}
			if (me != 1 && set.contains(me)) {
				return false;
			}
			set.add(me);
			last = set.last();
		}
		return true;
	}
	
	//1361
	public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
		boolean[] flag = new boolean[n];
		//flag[0] = true;
		if (!validateBinaryTreeNodesHelper(0, leftChild, rightChild, flag)) {
			return false;
		}
		int root = 0, count = 0;
		
		for (int i = 0; i < n; i++) {
			if (flag[i]) {
				count++;
			} else if (leftChild[i] == root) {
				flag[i] = true;
				root = i;
				if (!validateBinaryTreeNodesHelper(rightChild[i], leftChild, rightChild, flag)) {
					return false;
				}
				i = 0;
				count = 1;
			} else if (rightChild[i] == root) {
				flag[i] = true;
				root = i;
				if (!validateBinaryTreeNodesHelper(leftChild[i], leftChild, rightChild, flag)) {
					return false;
				}
				i = 0;
				count = 1;
			}
		}
		return count == n;
	}
	
	private boolean validateBinaryTreeNodesHelper(int i, int[] leftChild, int[] rightChild,
		boolean[] flag) {
		if (i < 0) {
			return true;
		}
		if (flag[i]) {
			return false;
		}
		flag[i] = true;
		return validateBinaryTreeNodesHelper(leftChild[i], leftChild, rightChild, flag) &&
			validateBinaryTreeNodesHelper(rightChild[i], leftChild, rightChild, flag);
		
	}
	
	int deep = 0, deepSum = 0;
	
	public int deepestLeavesSum(TreeNode root) {
		deepestLeavesSumHelper(root, 0);
		return deepSum;
	}
	
	private void deepestLeavesSumHelper(TreeNode root, int i) {
		if (root == null) {
			return;
		}
		deepestLeavesSumHelper(root.left, i + 1);
		deepestLeavesSumHelper(root.right, i + 1);
		if (i > deep) {
			deep = i;
			deepSum = root.val;
		} else if (i == deep) {
			deepSum += root.val;
		}
	}
	
	public int[] arrayRankTransform(int[] arr) {
		int len = arr.length;
		int[][] copy = new int[len][2];
		for (int i = 0; i < len; i++) {
			copy[i][0] = arr[i];
			copy[i][1] = i;
		}
		Arrays.sort(copy, (a, b) -> (a[0] - b[0]));
		int rank = 1;
		int[] count = new int[arr.length];
		count[0] = 1;
		for (int i = 1; i < len; i++) {
			if (copy[i][0] == copy[i - 1][0]) {
				count[i] = rank;
			} else {
				rank++;
				count[i] = rank;
			}
		}
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[copy[i][1]] = count[i];
		}
		return ret;
	}
	
	
	public int minTaps1(int n, int[] ranges) {
		int[] mem = new int[n];
		int min, max;
		for (int i = 0; i < n; i++) {
			mem[i] = i;
		}
		for (int i = n; i >= 0; i--) {
			if (ranges[i] > 0) {
				min = Math.max(0, i - ranges[i]);
				max = i + ranges[i];
				for (int j = min; j < n && j < max; j++) {
					if (max > mem[j] + ranges[mem[j]]) {
						mem[j] = i;
					}
				}
			}
		}
		int cur = 0, next = 0, count = 0;
		while (cur < n) {
			next = mem[cur] + ranges[mem[cur]];
			if (next == cur) {
				return -1;
			}
			cur = next;
			count++;
		}
		return count;
		
	}
	
	public int minTaps(int n, int[] ranges) {
		return minTapsHelper(0, n, ranges, 0);
	}
	
	private int minTapsHelper(int x, int n, int[] ranges, int cur) {
		int max = x;
		int t = cur;
		if (x >= n) {
			return 0;
		}
		for (int i = cur; i <= n; i++) {
			if (ranges[i] != 0 && i - ranges[i] <= x && i + ranges[i] > max) {
				max = i + ranges[i];
				cur = i;
			}
		}
		if (t == cur) {
			return -1;
		}
		return 1 + minTapsHelper(max, n, ranges, cur);
	}
	
	//1324
	public List<String> printVertically(String s) {
		String[] split = s.split(" ");
		int max = 0;
		int len = split.length;
		int[] length = new int[len];
		int[] mlength = new int[len];
		for (int i = len - 1; i >= 0; i--) {
			length[i] = split[i].length();
			max = Math.max(max, split[i].length());
			mlength[i] = max;
		}
		ArrayList<String> ret = new ArrayList<>();
		StringBuffer buffer = new StringBuffer();
		String t;
		for (int i = 0; i < max; i++) {
			for (int j = 0; j < len; j++) {
				t = split[j];
				if (mlength[j] <= i) {
					break;
				} else if (length[j] <= i) {
					buffer.append(' ');
				} else {
					buffer.append(t.charAt(i));
				}
			}
			ret.add(buffer.toString());
			buffer.setLength(0);
		}
		return ret;
	}
	
	//1325
	public TreeNode removeLeafNodes(TreeNode root, int target) {
		if (removeLeafNodesHelper(root, target) && root.val == target) {
			return null;
		}
		return root;
	}
	
	private boolean removeLeafNodesHelper(TreeNode root, int target) {
		if (root == null) {
			return true;
		}
		boolean left = removeLeafNodesHelper(root.left, target);
		boolean right = removeLeafNodesHelper(root.right, target);
		if (left && right) {
			root.left = null;
			root.right = null;
			return root.val == target;
		} else if (left) {
			root.left = null;
		} else if (right) {
			root.right = null;
		}
		return false;
	}
	
	//1323
	public int maximum69Number(int num) {
		int t = num, mod = 1000;
		while (t > 0) {
			if (t / mod == 6) {
				return num + 3 * mod;
			}
			t %= mod;
			mod /= 10;
		}
		return num;
		
	}
	
	//928
	public int minMalwareSpread(int[][] graph, int[] initial) {
		TreeSet<Integer> set = new TreeSet<>();
		int len = graph.length;
		List<Integer>[] lists = new List[len];
		for (int i = 0; i < len; i++) {
			lists[i] = new ArrayList();
		}
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (graph[i][j] > 0) {
					lists[i].add(j);
					lists[j].add(i);
				}
			}
		}
		HashSet<Integer> cur = new HashSet<>(), next;
		for (int i : initial) {
			cur.add(i);
		}
		while (!cur.isEmpty()) {
			set.addAll(cur);
			next = new HashSet<>();
			for (Integer x : cur) {
				for (Integer n : lists[x]) {
					if (!set.contains(n)) {
						next.add(n);
					}
				}
			}
			if (next.size() == 1) {
				for (Integer integer : next) {
					int ret = -1;
					for (Integer a : lists[integer]) {
						if (set.contains(a)) {
							if (ret >= 0) {
								return integer;
							}
							ret = a;
						}
					}
					return ret;
				}
			}
			cur = next;
		}
		for (Integer integer : set) {
			return integer;
		}
		return 0;
	}
	
	int minCameraCover = 0;
	
	//968
	public int minCameraCover(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (minCameraCoverHelper(root) == 2) {
			return minCameraCover + 1;
		}
		return minCameraCover;
	}
	
	private int minCameraCoverHelper(TreeNode root) {
		if (root == null) {
			return 1;
		}
		int left = minCameraCoverHelper(root.left);
		int right = minCameraCoverHelper(root.right);
		if (left == 2 || right == 2) {//要求当前节点必须加
			minCameraCover++;
			return 0;
		} else if (left == 0 || right == 0) {//当前节点可被监控,不需要
			return 1;
		} else {//当前节点不加,但是要求父节点加
			return 2;
		}
	}
	
	//996
	public int numSquarefulPerms(int[] A) {
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		HashMap<Integer, Integer> count = new HashMap<>();
		int len = A.length;
		for (int i : A) {
			count.put(i, count.getOrDefault(i, 0) + 1);
			map.put(i, new HashSet<>());
		}
		for (int i = 0; i < len; i++) {
			Set<Integer> list = map.get(A[i]);
			for (int j = i + 1; j < len; j++) {
				if (list.contains(A[j])) {
					continue;
				}
				if (checkSquare(A[i] + A[j])) {
					list.add(A[j]);
					map.get(A[j]).add(A[i]);
				}
			}
			if (list.size() == 0) {
				return 0;
			}
		}
		int ret = 0;
		for (Entry<Integer, Set<Integer>> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Set<Integer> value = entry.getValue();
			count.put(key, count.get(key) - 1);
			for (Integer x : value) {
				if (count.get(x) > 0) {
					ret += numSquarefulPermsHelper(map, count, x, len - 1);
				}
			}
			count.put(key, count.get(key) + 1);
			
		}
		return ret;
	}
	
	private int numSquarefulPermsHelper(HashMap<Integer, Set<Integer>> map,
		HashMap<Integer, Integer> count, int x, int len) {
		if (len == 1) {
			return 1;
		}
		int ret = 0;
		Set<Integer> set = map.get(x);
		count.put(x, count.get(x) - 1);
		for (Integer next : set) {
			if (count.get(next) > 0) {
				ret += numSquarefulPermsHelper(map, count, next, len - 1);
			}
		}
		count.put(x, count.get(x) + 1);
		return ret;
	}
	
	private boolean checkSquare(int i) {
		int l = 0, r = i, m;
		while (l <= r) {
			m = (l + r) >> 1;
			if (m * m > i) {
				r = m - 1;
			} else if (m * m < i) {
				l = m + 1;
			} else {
				return true;
			}
		}
		return false;
	}
	
	//1163
	public String lastSubstring(String s) {
		int begin = 0;
		int len = s.length();
		for (int i = 1; i < len; i++) {
			int j = 0;
			for (j = 0; j + i < len; j++) {
				if (s.charAt(i + j) > s.charAt(begin + j)) {
					begin = i;
					break;
				} else if (s.charAt(i + j) < s.charAt(begin + j)) {
					break;
				}
			}
			if (j + i == len) {
				return s.substring(begin);
			}
		}
		return s.substring(begin);
	}
	
	//1106
	char[] exp;
	
	public boolean parseBoolExpr(String expression) {
		exp = expression.toCharArray();
		return parseBoolExprHelper(0, exp.length - 1);
	}
	
	private boolean parseBoolExprHelper(int l, int r) {
		if (exp[l] == '(') {
			l++;
			r--;
		}
		char c = exp[l];
		if (l == r) {
			return c == 't';
		}
		if (c == '!') {
			l++;
			return !parseBoolExprHelper(l, r);
		}
		int count = 0;
		int i = l + 2;
		boolean ret = true;
		if (c == '&') {
			l += 2;
			while (i <= r) {
				if (exp[i] == '(') {
					count++;
				} else if ((exp[i] == ',' && count == 0) || i == r) {
					ret = ret && parseBoolExprHelper(l, i - 1);
					if (!ret) {
						break;
					}
					l = i + 1;
				} else if (exp[i] == ')') {
					count--;
				}
				i++;
				
			}
		} else {
			l += 2;
			ret = false;
			while (i <= r) {
				if (exp[i] == '(') {
					count++;
				} else if ((exp[i] == ',' && count == 0) || i == r) {
					ret = ret || parseBoolExprHelper(l, i - 1);
					if (ret) {
						break;
					}
					l = i + 1;
				} else if (exp[i] == ')') {
					count--;
				}
				i++;
			}
		}
		return ret;
	}
	
	
}
