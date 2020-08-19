package com.leetcode2;

import static com.base.Constant.ds_4;

import com.base.CharTreeNode;
import com.leetcode2.Codec.TreeNode;
import com.leetcode2.Test1.ListNode;
import com.sun.crypto.provider.PBEWithMD5AndDESCipher;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.wuming.pattern.FlyWeight.Tree;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import sun.awt.geom.AreaOp.EOWindOp;
import sun.plugin2.main.client.MessagePassingOneWayJSObject;

/**
 * @Author: WuMing
 * @CreateDate: 2020/5/20 17:33
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test4 {
	
	public static void main(String[] args) {
		Test4 test = new Test4();
		Codec codec = new Codec();
		test.addOperators1("2147483648",
			-2147483648);
		
		
	}
	
	//面试题 17.22. 单词转换
	public List<String> findLadders(String beginWord, String endWord, List<String> wordList) {
		HashSet<String> set = new HashSet<>(wordList);
		if (!set.contains(endWord)) {
			return new ArrayList<>();
		}
		set.remove(beginWord);
		char[] cur = beginWord.toCharArray();
		Stack<String> stack = new Stack<>();
		stack.add(beginWord);
		if(findLaddersHelper(cur, endWord, set,stack)){
			return new ArrayList<>(stack);
		}else{
			return new ArrayList<>();
		}
		
	}
	
	private boolean findLaddersHelper(char[] cur, String endWord, HashSet<String> set,Stack<String> stack) {
		int len = cur.length;
		for (int i = 0; i < len; i++) {
			char c = cur[i];
			for (char x = 'a'; x <= 'z'; x++) {
				if (x != c ) {
					cur[i]=x;
					String s = new String(cur);
					if(set.contains(s)){
						stack.push(s);
						if(s.equals(endWord)){
							return true;
						}
						set.remove(s);
						if(findLaddersHelper(cur,endWord,set,stack)){
							return true;
						}
						stack.pop();
					}
				}
			}
			 cur[i]=c;
		}
		return false;
	}
	
	//282. 给表达式添加运算符
	public List<String> addOperators1(String num, int target) {
		ArrayList<String> ret = new ArrayList<>();
		int length = num.length();
		if (length < 1) {
			return ret;
		}
		char[] buffer = new char[length * 2];
		int val = 0;
		for (int i = 0; i < length; i++) {
			val *= 10;
			buffer[i] = num.charAt(i);
			val += num.charAt(i) - '0';
			addOperators1Helper(ret, 0, val, target, i + 1, i + 1, buffer, num);
			if (val == 0) {
				break;
			}
		}
		return ret;
	}
	
	private void addOperators1Helper(ArrayList<String> ret, long beforeSum, long before, int target,
		int numIdx, int bufIdx, char[] buffer, String num) {
		int len = num.length();
		if (numIdx == len) {
			if (beforeSum + before == target) {
				ret.add(new String(buffer, 0, bufIdx));
			}
			return;
		}
		int val = 0;
		int opIdx = bufIdx++;
		for (int i = numIdx; i < len; i++) {
			val *= 10;
			buffer[bufIdx++] = num.charAt(i);
			val += num.charAt(i) - '0';
			buffer[opIdx] = '+';
			addOperators1Helper(ret, beforeSum + before, val, target, i + 1, bufIdx, buffer, num);
			buffer[opIdx] = '-';
			addOperators1Helper(ret, beforeSum + before, -val, target, i + 1, bufIdx, buffer, num);
			buffer[opIdx] = '*';
			addOperators1Helper(ret, beforeSum, before * val, target, i + 1, bufIdx, buffer, num);
			if (val == 0) {
				break;
			}
		}
	}
	
	public List<String> addOperators(String num, int target) {
		ArrayList<String> ret = new ArrayList<>();
		if (num.length() < 1) {
			return ret;
		}
		char[] buffer = new char[num.length() * 2];
		addOperatorsHelper(ret, 0, num, target, buffer, 0);
		return ret;
	}
	
	private void addOperatorsHelper(ArrayList<String> ret, int idx, String num, int target,
		char[] buffer, int bi) {
		int x = bi;
		int len = num.length();
		for (int i = idx; i < len; i++) {
			buffer[x++] = num.charAt(i);
			if (i == len - 1) {
				break;
			}
			buffer[x] = '+';
			addOperatorsHelper(ret, i + 1, num, target, buffer, x + 1);
			buffer[x] = '-';
			addOperatorsHelper(ret, i + 1, num, target, buffer, x + 1);
			buffer[x] = '*';
			addOperatorsHelper(ret, i + 1, num, target, buffer, x + 1);
			if (buffer[bi] == '0') {
				return;
			}
		}
		Stack<Long> stack = new Stack<>();
		long cur = 0;
		char op = '+';
		for (int i = 0; i <= x; i++) {
			if (i < x && buffer[i] >= '0' && buffer[i] <= '9') {
				cur *= 10;
				cur += buffer[i] - '0';
			} else {
				if (op == '*') {
					stack.push(stack.pop() * cur);
				} else if (op == '-') {
					stack.push(-cur);
				} else {
					stack.push(cur);
				}
				cur = 0;
				op = buffer[i];
			}
		}
		long sum = 0;
		for (long integer : stack) {
			sum += integer;
		}
		if (sum == target) {
			ret.add(new String(buffer, 0, x));
		}
	}
	
	
	//629. K个逆序对数组
	public int kInversePairs1(int n, int k) {
		long[] cur = new long[k + 1], next;
		cur[0] = 1;
		int mod = 1000000007;
		for (int i = 2; i <= n; i++) {
			next = new long[k + 1];
			next[0] = 1;
			for (int j = 1; j <= k && j <= i * (i - 1) / 2; j++) {
				next[j] = next[j - 1] + cur[j] - (j - i >= 0 ? cur[j - i] : 0);
				next[j] %= mod;
			}
			cur = next;
		}
		return (int) cur[k];
	}
	
	public int kInversePairs(int n, int k) {
		int[] cur = new int[k + 1], next;
		cur[0] = 1;//长度为1时只有逆序对为0的时候是1
		int mod = 1000000007;
		for (int i = 2; i <= n; i++) {
			next = new int[k + 1];
			for (int j = 0; j < i; j++) {
				for (int l = 0; l + j <= k && cur[l] > 0; l++) {
					next[l + j] += cur[l];//逆序数为l+j个
					next[l + j] %= mod;
				}
			}
			cur = next;
		}
		return cur[k];
	}
	
	//面试题 17.26. 稀疏相似度
	public List<String> computeSimilarities1(int[][] docs) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		int len = docs.length;
		int[][] mem = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int x : docs[i]) {
				if (map.containsKey(x)) {
					List<Integer> list = map.get(x);
					for (Integer t : list) {
						mem[t][i]++;
					}
					list.add(i);
				} else {
					ArrayList<Integer> list = new ArrayList<>();
					list.add(i);
					map.put(x, list);
				}
			}
		}
		ArrayList<String> ret = new ArrayList<>();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				if (mem[i][j] > 0) {
					buffer.append(i);
					buffer.append(',');
					buffer.append(j);
					buffer.append(':');
					buffer.append(String.format("%.4f",
						((double) mem[i][j] / (docs[i].length + docs[j].length - mem[i][j]))));
					//ret.add(i+","+j+":"+String.format("%.4f", ((double)mem[i][j]/(docs[i].length+docs[j].length-mem[i][j]))));
					ret.add(buffer.toString());
					buffer.setLength(0);
				}
			}
		}
		return ret;
	}
	
	public List<String> computeSimilarities(int[][] docs) {
		int len = 0;
		for (int[] doc : docs) {
			len += doc.length;
		}
		int[][] d = new int[len][2];
		int idx = 0;
		int length = docs.length;
		for (int i = 0; i < length; i++) {
			for (int x : docs[i]) {
				d[idx][0] = x;
				d[idx][1] = i;
				idx++;
			}
		}
		Arrays.sort(d, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
		int[][] mem = new int[length][length];
		int l = 0;
		for (int i = 1; i < len; i++) {
			if (d[i][0] == d[l][0]) {
				for (int j = l; j < i; j++) {
					mem[d[j][1]][d[i][1]]++;
				}
			} else {
				l = i;
			}
		}
		ArrayList<String> ret = new ArrayList<>();
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				if (mem[i][j] > 0) {
					buffer.append(i);
					buffer.append(',');
					buffer.append(j);
					buffer.append(':');
					buffer.append(String.format("%.4f",
						((double) mem[i][j] / (docs[i].length + docs[j].length - mem[i][j]))));
					//ret.add(i+","+j+":"+String.format("%.4f", ((double)mem[i][j]/(docs[i].length+docs[j].length-mem[i][j]))));
					ret.add(buffer.toString());
					buffer.setLength(0);
				}
			}
		}
		return ret;
	}
	
	class TireNode {
		
		int count = 0;
		TireNode[] children;
		
		public TireNode() {
			children = new TireNode[83];
		}
	}
	
	//1487. 保证文件名唯一
	public String[] getFolderNames(String[] names) {
		TireNode root = new TireNode();
		int length = names.length;
		String[] ret = new String[length];
		for (int i = 0; i < length; i++) {
			String name = names[i];
			TireNode cur = getTireNode(name, root);
			if (cur.count == 0) {
				ret[i] = name;
				cur.count++;
			} else {
				TireNode temp = cur;
				String tail = "";
				while (temp.count != 0) {
					tail = "(" + cur.count + ")";
					temp = getTireNode(tail, cur);
					cur.count++;
				}
				ret[i] = name + tail;
				temp.count++;
			}
		}
		return ret;
	}
	
	private TireNode getTireNode(String tail, TireNode cur) {
		for (char c : tail.toCharArray()) {
			if (cur.children[c - '('] == null) {
				cur.children[c - '('] = new TireNode();
			}
			cur = cur.children[c - '('];
		}
		return cur;
	}
	
	//1458. 两个子序列的最大点积
	public int maxDotProduct(int[] nums1, int[] nums2) {
		int len1 = nums1.length;
		int len2 = nums2.length;
		int[][] mem = new int[len1][len2];
		mem[0][0] = nums1[0] * nums2[0];
		for (int i = 1; i < len2; i++) {
			mem[0][i] = Math.max(nums1[0] * nums2[i], mem[0][i - 1]);
		}
		int x, b;
		for (int i = 1; i < len1; i++) {
			mem[i][0] = Math.max(nums1[i] * nums2[0], mem[i - 1][0]);
			for (int j = 1; j < len2; j++) {
				x = nums1[i] * nums2[j];
				b = mem[i - 1][j - 1];
				if (x >= 0 && b >= 0) {
					mem[i][j] = x + b;
				} else {
					mem[i][j] = Math.max(x, b);
				}
				mem[i][j] = Math.max(mem[i][j], Math.max(mem[i - 1][j], mem[i][j - 1]));
			}
		}
		return mem[len1 - 1][len2 - 1];
	}
	
	//1482. 制作 m 束花所需的最少天数
	public int minDays(int[] bloomDay, int m, int k) {
		int len = bloomDay.length;
		if (len < m * k) {
			return -1;
		}
		int max = bloomDay[0], min = bloomDay[0];
		for (int i : bloomDay) {
			if (i > max) {
				max = i;
			} else if (i < min) {
				min = i;
			}
		}
		if (m * k == len) {
			return max;
		}
		int mid;
		while (max >= min) {
			mid = (max + min) / 2;
			int count = 0, x = 0;
			for (int i : bloomDay) {
				if (i <= mid) {
					x++;
					if (x == k) {
						count++;
						x = 0;
						if (count >= m) {
							break;
						}
					}
				} else {
					x = 0;
				}
			}
			if (count >= m) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		return min;
	}
	
	
	public int xorOperation(int n, int start) {
		int ret = 0;
		for (int i = 0; i < n; i++) {
			ret ^= (2 * i + start);
		}
		return ret;
	}
	
	//1488. 避免洪水泛滥
	public int[] avoidFlood(int[] rains) {
		HashMap<Integer, Integer> map = new HashMap<>();
		TreeSet<Integer> set = new TreeSet<>();
		int len = rains.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			if (rains[i] > 0) {
				Integer x = map.get(rains[i]);
				if (x != null) {
					Integer ceiling = set.ceiling(x);
					if (ceiling == null) {
						return new int[0];
					} else {
						set.remove(ceiling);
						ret[ceiling] = rains[i];
					}
				}
				map.put(rains[i], i);
				
				ret[i] = -1;
			} else {
				set.add(i);
			}
		}
		for (int i = 0; i < len; i++) {
			if (ret[i] == 0) {
				ret[i] = 1;
			}
		}
		return ret;
	}
	
	//面试题 16.18. 模式匹配
	public boolean patternMatching(String pattern, String value) {
		char[] pat = pattern.toCharArray();
		int[] count = new int[2];
		for (char c : pat) {
			if (c == 'a') {
				count[0]++;
			} else {
				count[1]++;
			}
		}
		int len = value.length(), rest;
		if (len == 0) {
			if (count[0] == 0 || count[1] == 0) {
				return true;
			} else {
				return false;
			}
		} else if (pat.length == 0) {
			return false;
		}
		if (count[0] == 0 || count[1] == 0) {
			if (len % pat.length != 0) {
				return false;
			}
			int b = len / pat.length;
			for (int i = 0; i < b; i++) {
				char c = value.charAt(i);
				for (int j = i + b; j < len; j++) {
					if (c != value.charAt(j)) {
						return false;
					}
				}
			}
			return true;
		}
		for (int i = 0; count[0] * i <= len; i++) {
			rest = len - count[0] * i;
			if (rest % count[1] == 0 && patternMatchingHelper(pat, value, i, rest / count[1])) {
				return true;
			}
		}
		return false;
	}
	
	private boolean patternMatchingHelper(char[] pat, String value, int al, int bl) {
		String a = null;
		String b = null;
		int x = 0;
		for (char c : pat) {
			if (c == 'a') {
				if (a == null) {
					a = value.substring(x, x + al);
				} else if (!value.startsWith(a, x)) {
					return false;
				}
				x += al;
			} else {
				if (b == null) {
					b = value.substring(x, x + bl);
				} else if (!value.startsWith(b, x)) {
					return false;
				}
				x += bl;
			}
			
		}
		return true;
	}
	
	//1477. 找两个和为目标值且不重叠的子数组
	public int minSumOfLengths(int[] arr, int target) {
		int len = arr.length;
		int[] mem = new int[len];
		Arrays.fill(mem, len + 1);
		HashMap<Integer, Integer> map = new HashMap<>();
		int sum = 0;
		map.put(sum, -1);
		int ret = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			sum += arr[i];
			Integer x = map.get(sum - target);
			if (x != null) {
				if (x >= 0) {
					ret = Math.min(i - x + mem[x], ret);
				}
				mem[i] = Math.min(i - x, i > 0 ? mem[i - 1] : len + 1);
			} else {
				mem[i] = i > 0 ? mem[i - 1] : len + 1;
			}
			map.put(sum, i);
		}
		return ret > len ? -1 : ret;
	}
	
	//1481. 不同整数的最少数目
	public int findLeastNumOfUniqueInts(int[] arr, int k) {
		Arrays.sort(arr);
		int cur = arr[0], count = 1, total = 1;
		int len = arr.length;
		TreeMap<Integer, Integer> map = new TreeMap<>();
		for (int i = 1; i < len; i++) {
			if (arr[i] != cur) {
				total++;
				cur = arr[i];
				map.put(count, map.getOrDefault(count, 0) + 1);
				count = 1;
			} else {
				count++;
			}
		}
		map.put(count, map.getOrDefault(count, 0) + 1);
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if (key * value < k) {
				k -= key * value;
				total -= value;
			} else {
				int c = k / key;
				total -= c;
				break;
			}
		}
		return total;
	}
	
	//1480. 一维数组的动态和
	public int[] runningSum(int[] nums) {
		int len = nums.length;
		for (int i = 1; i < len; i++) {
			nums[i] += nums[i - 1];
		}
		return nums;
	}
	
	//面试题 17.04. 消失的数字
	public int missingNumber(int[] nums) {
		int len = nums.length;
		int ret = len;
		for (int i = 0; i < len; i++) {
			ret += i;
			ret -= nums[i];
		}
		return ret;
	}
	
	public List<List<Integer>> threeSum(int[] nums) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		int len = nums.length;
		if (len < 3) {
			return ret;
		}
		Arrays.sort(nums);
		if (nums[0] > 0 || nums[len - 1] < 0) {
			return ret;
		} else if (nums[0] == 0) {
			if (nums[1] == 0 && nums[2] == 0) {
				ret.add(Arrays.asList(0, 0, 0));
			}
			return ret;
		} else if (nums[len - 1] == 0) {
			if (nums[len - 2] == 0 && nums[len - 3] == 0) {
				ret.add(Arrays.asList(0, 0, 0));
			}
			return ret;
		}
		int a = 0, b;
		for (int i = 0; i < len - 2; i++) {
			while (i < len - 2 && a == nums[i]) {
				i++;
			}
			a = nums[i];
			if (a > 0) {
				break;
			}
			for (int j = i + 1; j < len - 1; j++) {
				b = nums[j];
				if (a + b > 0) {
					break;
				}
				int idx = Arrays.binarySearch(nums, j + 1, len, -a - b);
				if (idx > j) {
					ret.add(Arrays.asList(a, b, -a - b));
				}
				while (j + 1 < len && b == nums[j + 1]) {
					j++;
				}
			}
		}
		return ret;
	}
	
	//739. 每日温度
	public int[] dailyTemperatures(int[] T) {
		int len = T.length;
		int[] ret = new int[len];
		Stack<Integer> stack = new Stack<>();
		int t;
		for (int i = 0; i < len; i++) {
			t = T[i];
			while (!stack.isEmpty() && T[stack.peek()] < t) {
				Integer x = stack.pop();
				ret[x] = i - x;
			}
			stack.push(i);
		}
		return ret;
	}
	
	//233. 数字 1 的个数
	public int countDigitOne(int n) {
		if (n < 0) {
			return 0;
		}
		long[] mem = new long[100];
		mem[0] = 1;
		long t = 10;
		int idx = 1;
		while (n > t) {
			mem[idx] = 10 * mem[idx - 1] + t;
			idx++;
			t *= 10;
		}
		return countDigitOneHelper(mem, n);
	}
	
	private int countDigitOneHelper(long[] mem, long n) {
		if (n == 0) {
			return 0;
		} else if (n < 10) {
			return 1;
		}
		long t = 10;
		int idx = 0;
		while (n >= t) {
			t *= 10;
			idx++;
		}
		t /= 10;
		idx--;
		long x = n / t;
		long ret = x * mem[idx] + (x > 1 ? t : n % t + 1) + countDigitOneHelper(mem, n % t);
		return (int) ret;
	}
	
	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int t = x, r = 0;
		while (t > 0) {
			r *= 10;
			r += t % 10;
			t /= 10;
		}
		return t == x;
	}
	
	//1470. 重新排列数组
	public int[] shuffle(int[] nums, int n) {
		int[] copy = Arrays.copyOf(nums, n);
		int idx = 0;
		for (int i = 0; i < n; i++) {
			nums[idx++] = copy[i];
			nums[idx++] = copy[i + n];
		}
		return nums;
	}
	
	//面试题 01.08. 零矩阵
	public void setZeroes(int[][] matrix) {
		int high = matrix.length;
		int[] row = new int[high];
		Arrays.fill(row, 1);
		int len = matrix[0].length;
		int[] column = new int[len];
		Arrays.fill(column, 1);
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (matrix[i][j] == 0) {
					row[i] = 0;
					column[j] = 0;
				}
			}
		}
		for (int i = 0; i < high; i++) {
			if (row[i] == 0) {
				Arrays.fill(matrix[i], 0);
			} else {
				for (int j = 0; j < len; j++) {
					matrix[i][j] *= column[j];
				}
			}
		}
	}
	
	//面试题46. 把数字翻译成字符串
	public int translateNum(int num) {
		String s = num + "";
		int len = s.length();
		int[] mem = new int[len];
		mem[0] = 1;
		char[] arr = s.toCharArray();
		for (int i = 1; i < len; i++) {
			if (arr[i - 1] == '1' || (arr[i - 1] == '2' && arr[i] < '6')) {
				mem[i] += mem[i - 1];
				if (i > 1) {
					mem[i] += mem[i - 2];
				} else {
					mem[i] += 1;
				}
			} else {
				mem[i] = mem[i - 1];
			}
		}
		return mem[len - 1];
	}
	
	//1471. 数组中的 k 个最强值
	public int[] getStrongest(int[] arr, int k) {
		int len = arr.length;
		if (k >= len) {
			return arr;
		}
		Arrays.sort(arr);
		int m = arr[(len - 1) / 2];
		int[] ret = new int[k];
		int idx = 0, l = 0, r = len - 1, lv = Math.abs(arr[l] - m), rv = Math.abs(arr[r] - m);
		while (idx < k) {
			if (lv > rv) {
				ret[idx++] = arr[l];
				l++;
				lv = Math.abs(arr[l] - m);
			} else {
				ret[idx++] = arr[r];
				r--;
				rv = Math.abs(arr[r] - m);
			}
		}
		return ret;
	}
	
	//990. 等式方程的可满足性
	public boolean equationsPossible(String[] equations) {
		int[] mem = new int[26];
		for (int i = 0; i < 26; i++) {
			mem[i] = i;
		}
		for (String equation : equations) {
			if (equation.charAt(1) == '=') {
				equationsPossibleEqual(mem, equation.charAt(0) - 'a', equation.charAt(3) - 'a');
			}
		}
		for (String equation : equations) {
			if (equation.charAt(1) == '!') {
				if (equationsPossibleUnEqual(mem, equation.charAt(0) - 'a',
					equation.charAt(3) - 'a')) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean equationsPossibleUnEqual(int[] mem, int a, int b) {
		while (mem[a] != a) {
			a = mem[a];
		}
		while (mem[b] != b) {
			b = mem[b];
		}
		return a == b;
	}
	
	private void equationsPossibleEqual(int[] mem, int a, int b) {
		while (mem[a] != a) {
			a = mem[a];
		}
		while (mem[b] != b) {
			b = mem[b];
		}
		mem[a] = Math.min(a, b);
		mem[b] = Math.min(a, b);
	}
	
	//97. 交错字符串
	public boolean isInterleave(String s1, String s2, String s3) {
		int len1 = s1.length();
		int len2 = s2.length();
		int len3 = s3.length();
		if (len1 + len2 != len3) {
			return false;
		}
		boolean[][] mem = new boolean[len1 + 1][len2 + 1];
		for (int i = 0; i < len1; i++) {
			if (s1.charAt(i) == s3.charAt(i)) {
				mem[i + 1][0] = true;
			} else {
				break;
			}
		}
		for (int i = 0; i < len2; i++) {
			if (s2.charAt(i) == s3.charAt(i)) {
				mem[0][i + 1] = true;
			} else {
				break;
			}
		}
		char[] arr = s3.toCharArray();
		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if (mem[i - 1][j]) {
					mem[i][j] |= s1.charAt(i - 1) == arr[i + j - 1];
				}
				if (mem[i][j - 1]) {
					mem[i][j] |= s2.charAt(j - 1) == arr[i + j - 1];
				}
			}
		}
		return mem[len1][len2];
	}
	
	//44. 通配符匹配
	public boolean isPatternMatch(String s, String p) {
		int lens = s.length();
		int lenp = p.length();
		boolean[][] mem = new boolean[lens][lenp];
		for (int i = 0; i < lens; i++) {
			Arrays.fill(mem[i], true);
		}
		return isPatternMatchHelper(s.toCharArray(), p.toCharArray(), 0, lens, 0, lenp, mem);
	}
	
	private boolean isPatternMatchHelper(char[] s, char[] p, int si, int sl, int pi, int pl,
		boolean[][] mem) {
		if (pi == pl) {
			return si == sl;
		} else if (si == sl) {
			while (pi < pl) {
				if (p[pi] != '*') {
					return false;
				}
				pi++;
			}
			return true;
		}
		if (!mem[si][pi]) {
			return false;
		}
		if (p[pi] == '?' || p[pi] == s[si]) {
			mem[si][pi] = isPatternMatchHelper(s, p, si + 1, sl, pi + 1, pl, mem);
		} else if (p[pi] == '*') {
			for (int i = si; i <= sl; i++) {
				if (isPatternMatchHelper(s, p, i, sl, pi + 1, pl, mem)) {
					return true;
				}
				mem[i][pi] = false;
			}
		} else {
			mem[si][pi] = false;
		}
		return mem[si][pi];
	}
	
	//面试题29. 顺时针打印矩阵
	public int[] spiralOrder(int[][] matrix) {
		int high = matrix.length;
		int len = matrix[0].length;
		int[] ret = new int[high * len];
		int idx = 0;
		int i = 0, j = 0, u = 0, d = high - 1, l = 0, r = len - 1;
		int total = high * len;
		while (idx < total) {
			while (idx < total && j <= r) {
				ret[idx++] = matrix[i][j];
				j++;
			}
			u++;
			j--;
			i++;
			while (idx < total && i <= d) {
				ret[idx++] = matrix[i][j];
				i++;
			}
			r--;
			i--;
			j--;
			while (idx < total && j >= l) {
				ret[idx++] = matrix[i][j];
				j--;
			}
			d--;
			j++;
			i--;
			while (idx < total && i >= u) {
				ret[idx++] = matrix[i][j];
				i--;
			}
			l++;
			i++;
			j++;
		}
		return ret;
	}
	
	//115. 不同的子序列
	public int numDistinct1(String s, String t) {
		int lens = s.length();
		int lent = t.length();
		if (lens < lent) {
			return 0;
		}
		HashMap<Character, List<Integer>> map = new HashMap<>();
		List<Integer> list;
		for (int i = 0; i < lent; i++) {
			char c = t.charAt(i);
			if (map.containsKey(c)) {
				list = map.get(c);
			} else {
				list = new ArrayList<>();
				map.put(c, list);
			}
			list.add(i);
		}
		
		int[] cur = new int[lent], next;
		int x = s.indexOf(t.charAt(0));
		if (x < 0) {
			return 0;
		} else {
			cur[0] = 1;
		}
		for (int i = x + 1; i < lens; i++) {
			char c = s.charAt(i);
			list = map.get(c);
			if (list != null) {
				next = Arrays.copyOf(cur, lent);
				for (Integer idx : list) {
					if (idx == 0) {
						next[0]++;
					} else {
						next[idx] += cur[idx - 1];
					}
				}
				cur = next;
			}
		}
		return cur[lent - 1];
	}
	
	public int numDistinct(String s, String t) {
		int lens = s.length();
		int lent = t.length();
		int[][] mem = new int[lens][lent];
		if (s.charAt(0) == t.charAt(0)) {
			mem[0][0] = 1;
		}
		for (int i = 1; i < lens; i++) {
			char c = s.charAt(i);
			for (int j = 0; j < lent; j++) {
				mem[i][j] = mem[i - 1][j];
				if (t.charAt(j) == c) {
					if (j == 0) {
						mem[i][0]++;
					} else {
						mem[i][j] += mem[i - 1][j - 1];
					}
				}
			}
		}
		return mem[lens - 1][lent - 1];
	}
	
	//132. 分割回文串 II
	public int minCut(String s) {
		int len = s.length();
		int[] mem = new int[len];
		Arrays.fill(mem, len);
		mem[0] = 0;
		for (int i = 1; i < len; i++) {
			mem[i] = mem[i - 1] + 1;
			for (int j = i - 1; j >= 0; j--) {
				if (j > 0 && mem[j - 1] + 1 >= mem[i]) {
					continue;
				}
				if (minCutHelper(s, j, i)) {
					if (j == 0) {
						mem[i] = 0;
					} else {
						mem[i] = Math.min(mem[i], 1 + mem[j - 1]);
					}
				}
			}
		}
		return mem[len - 1];
	}
	
	private boolean minCutHelper(String s, int l, int r) {
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
	
	//212. 单词搜索 II
	public List<String> findWords(char[][] board, String[] words) {
		CharTreeNode root = new CharTreeNode();
		for (String word : words) {
			insertCharTree(word, root);
		}
		int high = board.length;
		int len = board[0].length;
		ArrayList<String> ret = new ArrayList<>();
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				searchCharTree(board, i, j, ret, root, new boolean[high][len]);
			}
		}
		return ret;
	}
	
	private void searchCharTree(char[][] board, int i, int j, ArrayList<String> ret,
		CharTreeNode root, boolean[][] visited) {
		if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || visited[i][j]) {
			return;
		}
		char c = board[i][j];
		if (root.children[c] == null) {
			return;
		}
		root = root.children[c];
		if (root.flag) {
			ret.add(root.val);
			root.flag = false;
		}
		visited[i][j] = true;
		for (int[] d : ds_4) {
			searchCharTree(board, i + d[0], j + d[1], ret, root, visited);
		}
		visited[i][j] = false;
	}
	
	public void insertCharTree(String s, CharTreeNode root) {
		char[] arr = s.toCharArray();
		CharTreeNode cur = root;
		for (int i = 0; i < arr.length; i++) {
			int x = arr[i] - 'a';
			if (cur.children[x] == null) {
				cur.children[x] = new CharTreeNode();
			}
			cur = cur.children[x];
		}
		cur.flag = true;
		cur.val = s;
	}
	
	int minimumHP = Integer.MAX_VALUE;
	
	public int calculateMinimumHP(int[][] dungeon) {
		calculateMinimumHPHelper(dungeon, 0, 0, 1, 1);
		return minimumHP;
	}
	
	private void calculateMinimumHPHelper(int[][] dungeon, int x, int y, int cur, int base) {
		int high = dungeon.length;
		int len = dungeon[0].length;
		if (cur + dungeon[x][y] <= 0) {
			base += 1 - (cur + dungeon[x][y]);
			cur = 1;
		} else {
			cur += dungeon[x][y];
		}
		if (base >= minimumHP) {
			return;
		} else if (x == high - 1 && y == len - 1) {
			minimumHP = base;
			return;
		}
		if (x + 1 < high) {
			calculateMinimumHPHelper(dungeon, x + 1, y, cur, base);
		}
		if (y + 1 < len) {
			calculateMinimumHPHelper(dungeon, x, y + 1, cur, base);
		}
	}
	
	//106. 从中序与后序遍历序列构造二叉树
	int idxi, idxp;
	
	public TreeNode buildTree1(int[] inorder, int[] postorder) {
		idxi = inorder.length - 1;
		idxp = idxi;
		return buildTree1Helper(inorder, postorder, (long) Integer.MAX_VALUE + 1);
	}
	
	private TreeNode buildTree1Helper(int[] inorder, int[] postorder, long l) {
		if (idxp < 0 || inorder[idxi] == l) {
			idxi--;
			return null;
		}
		int x = postorder[idxp];
		idxp--;
		TreeNode root = new TreeNode(x);
		root.right = buildTree1Helper(inorder, postorder, x);
		root.left = buildTree1Helper(inorder, postorder, l);
		return root;
	}
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		int len = inorder.length;
		return buildTreeHelper(inorder, postorder, 0, len - 1, 0, len - 1);
	}
	
	private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int il, int ir, int pl,
		int pr) {
		int x = postorder[pr];
		TreeNode root = new TreeNode(x);
		if (il == ir) {
			return root;
		}
		int i = 0;
		while (il + i < ir) {
			if (inorder[il + i] == x) {
				break;
			}
		}
		root.left = buildTreeHelper(inorder, postorder, il, il + i - 1, pl, pl + i - 1);
		root.right = buildTreeHelper(inorder, postorder, il + i + 1, ir, pl + i, pr - 1);
		return root;
	}
	
	//238. 除自身以外数组的乘积
	public int[] productExceptSelf(int[] nums) {
		int x = 1;
		int len = nums.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[i] = x;
			x *= nums[i];
		}
		x = 1;
		for (int i = len - 1; i >= 0; i--) {
			ret[i] *= x;
			x *= nums[i];
		}
		return ret;
	}
	
	//面试题19. 正则表达式匹配
	//10. 正则表达式匹配
	public boolean isMatch(String s, String p) {
		char[] sa = s.toCharArray();
		char[] pa = p.toCharArray();
		int sl = sa.length;
		int pl = pa.length;
		return isMatchHelper(sa, sl - 1, pa, pl - 1);
	}
	
	private boolean isMatchHelper(char[] sa, int si, char[] pa, int pi) {
		while (si >= 0 && pi >= 0 && (pa[pi] == '.' || sa[si] == pa[pi])) {
			si--;
			pi--;
		}
		if (pi < 0) {
			if (si < 0) {
				return true;
			} else {
				return false;
			}
		} else if (pa[pi] == '*') {
			while (true) {
				if (isMatchHelper(sa, si, pa, pi - 2)) {
					return true;
				}
				if (si < 0) {
					return false;
				} else if (pa[pi - 1] == '.' || sa[si] == pa[pi - 1]) {
					si--;
				} else {
					return false;
				}
			}
			
		} else {
			return false;
		}
	}
	
	//1467. 两个盒子中球的颜色数相同的概率
	double a, b;
	double[] A;
	
	public double getProbability(int[] balls) {
		int total = 0;
		for (int ball : balls) {
			total += ball;
		}
		A = new double[9];
		A[0] = 1;
		for (int i = 1; i < 9; i++) {
			A[i] = i * A[i - 1];
		}
		int len = balls.length;
		
		getProbabilityHelper(0, balls, new int[len], 0, len, 0, total);
		double ret = a;
		int j = 0;
		for (int i = total; i > 0; i--) {
			while (ret < i && j < len) {
				ret *= A[balls[j]];
				j++;
			}
			ret /= i;
		}
		return ret;
	}
	
	private long gcb(long x, long y) {
		long t;
		while (y != 0) {
			t = x % y;
			x = y;
			y = t;
		}
		return x;
	}
	
	private void getProbabilityHelper(int idx, int[] balls, int[] mem, int lc, int rc, int count,
		int total) {
		int len = balls.length;
		if (count * 2 == total) {
			if (lc == rc) {
				double x = 1, y = 1;
				for (int i = 1, j = 0; i <= count; i++) {
					x *= i;
					while (j < len && x % A[balls[j]] == 0) {
						x /= A[balls[j]];
						j++;
					}
				}
				for (int i = 1, j = 0; i <= count; i++) {
					y *= i;
					while (j < len && y % A[mem[j]] == 0) {
						y /= A[mem[j]];
						j++;
					}
				}
				a += x * y;
			}
			return;
		} else {
			if (count * 2 > total || lc > rc || idx == len) {
				return;
			}
		}
		getProbabilityHelper(idx + 1, balls, mem, lc, rc, count, total);
		while (balls[idx] > 1) {
			mem[idx]++;
			balls[idx]--;
			getProbabilityHelper(idx + 1, balls, mem, lc + 1, rc, count + mem[idx], total);
		}
		mem[idx]++;
		balls[idx]--;
		getProbabilityHelper(idx + 1, balls, mem, lc + 1, rc - 1, count + mem[idx], total);
		balls[idx] = mem[idx];
		mem[idx] = 0;
	}
	
	
	//1462. 课程安排 IV
	public List<Boolean> checkIfPrerequisite(int n, int[][] prerequisites, int[][] queries) {
		boolean[][] mem = new boolean[n][n];
		Set<Integer>[] sets = new Set[n];
		for (int i = 0; i < n; i++) {
			sets[i] = new HashSet<>();
		}
		int[] count = new int[n];
		for (int[] prerequisite : prerequisites) {
			count[prerequisite[1]]++;
			sets[prerequisite[1]].add(prerequisite[0]);
		}
		for (int i = 0; i < n; i++) {
			if (count[i] > 0) {
				boolean[] x = checkIfPrerequisiteHelper(count, i, sets, mem);
			}
		}
		ArrayList<Boolean> ret = new ArrayList<>();
		for (int[] query : queries) {
			ret.add(mem[query[1]][query[0]]);
		}
		return ret;
	}
	
	private boolean[] checkIfPrerequisiteHelper(int[] count, int i, Set<Integer>[] sets,
		boolean[][] mem) {
		if (count[i] == 0) {
			return mem[i];
		}
		count[i] = 0;
		int len = count.length;
		boolean[] ret = new boolean[len];
		for (Integer x : sets[i]) {
			ret[x] = true;
			boolean[] cur = checkIfPrerequisiteHelper(count, x, sets, mem);
			for (int j = 0; j < len; j++) {
				ret[j] |= cur[j];
			}
		}
		mem[i] = ret;
		return ret;
	}
	
	//837. 新21点
	public double new21Game(int N, int K, int W) {
		double[] mem = new double[N + 1];
		mem[0] = 1;
		double rate;
		for (int i = 0; i < K; i++) {
			rate = mem[i] / W;
			for (int j = 1; j <= W && i + j <= N; j++) {
				mem[i + j] += rate;
			}
		}
		double ret = 0;
		for (int i = K; i <= N; i++) {
			ret += mem[i];
		}
		return ret;
	}
	
	public int insertBits(int N, int M, int i, int j) {
		int[] mem = new int[32];
		for (int k = 0; k < 32; k++) {
			mem[k] = N & 1;
			N >>= 1;
		}
		for (int k = i; k <= j; k++) {
			mem[k] = M & 1;
			M >>= 1;
		}
		int ret = 0;
		for (int k = 31; k >= 0; k--) {
			ret <<= 1;
			ret += mem[k];
		}
		return ret;
	}
	
	//5425. 切割后面积最大的蛋糕
	public int maxArea1(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);
		long mh = 0, mw = 0;
		int l = 0, mod = 1000000007;
		for (int ch : horizontalCuts) {
			mh = Math.max(mh, ch - l);
			l = ch;
		}
		
		mh = Math.max(mh, h - l);
		l = 0;
		for (int cw : verticalCuts) {
			mw = Math.max(mw, cw - l);
			l = cw;
		}
		
		mw = Math.max(mw, w - l);
		return (int) (mh * mw % mod);
	}
	
	//5425. 切割后面积最大的蛋糕---超时
	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
		Arrays.sort(horizontalCuts);
		Arrays.sort(verticalCuts);
		int y = 0, x = 0, x1, y1;
		int max = 0;
		for (int i = 0; i <= horizontalCuts.length; i++) {
			y1 = (i == horizontalCuts.length) ? h : horizontalCuts[i];
			x = 0;
			for (int j = 0; j < verticalCuts.length; j++) {
				x1 = verticalCuts[j];
				max = Math.max(max, (x1 - x) * (y1 - y));
				x = x1;
			}
			max = Math.max(max, (w - x) * (y1 - y));
			y = y1;
		}
		return max;
	}
	
	//
	public boolean hasAllCodes(String s, int k) {
		if (s.length() < k) {
			return false;
		}
		boolean[] visit = new boolean[1 << k];
		int cur = 0, mask = 0, i;
		for (i = 0; i < k - 1; i++) {
			cur <<= 1;
			cur += s.charAt(i) - '0';
			mask <<= 1;
			mask++;
		}
		int last = 1 << k;
		for (; i < s.length(); i++) {
			cur <<= 1;
			cur += s.charAt(i) - '0';
			if (!visit[cur]) {
				last--;
				visit[cur] = true;
				if (last == 0) {
					return true;
				}
			}
			cur &= mask;
		}
		return false;
	}
	
	public int maxProduct(int[] nums) {
		int a, b;
		if (nums[0] > nums[1]) {
			a = nums[0];
			b = nums[1];
		} else {
			a = nums[1];
			b = nums[0];
		}
		int len = nums.length;
		for (int i = 2; i < len; i++) {
			if (nums[i] > a) {
				b = a;
				a = nums[i];
			} else if (nums[i] > b) {
				b = nums[i];
			}
		}
		return (a - 1) * (b - 1);
	}
	
	//面试题 17.13. 恢复空格
	class Tire {
		
		Tire[] child;
		boolean isword;
		
		public Tire() {
			this.child = new Tire[26];
		}
	}
	
	public int respace(String[] dictionary, String sentence) {
		Tire root = new Tire();
		for (String s : dictionary) {
			insertTree(s, root);
		}
		int len = sentence.length();
		int[] mem = new int[len + 1];
		Arrays.fill(mem, len + 1);
		mem[0] = 0;
		for (int i = 0; i < len; i++) {
			searchTree(root, sentence, i, mem);
		}
		return mem[len];
	}
	
	private void searchTree(Tire root, String sentence, int i, int[] mem) {
		int b = i;
		int len = sentence.length();
		for (; i < len; i++) {
			int x = sentence.charAt(i) - 'a';
			if (root.child[x] == null) {
				mem[i + 1] = Math.min(mem[i] + 1, mem[i + 1]);
				return;
			} else {
				if (root.child[x].isword) {
					mem[i + 1] = Math.min(mem[i + 1], mem[b]);
				} else {
					mem[i + 1] = Math.min(mem[i] + 1, mem[i + 1]);
				}
				root = root.child[x];
			}
		}
	}
	
	private void insertTree(String s, Tire root) {
		for (int i = 0; i < s.length(); i++) {
			int x = s.charAt(i) - 'a';
			if (root.child[x] == null) {
				root.child[x] = new Tire();
			}
			root = root.child[x];
		}
		root.isword = true;
	}
	
	
	//632. 最小区间
	public int[] smallestRange(List<List<Integer>> nums) {
		int len = 0;
		for (List<Integer> num : nums) {
			len += num.size();
		}
		int[][] mem = new int[len][2];
		int idx = 0;
		for (int i = 0; i < nums.size(); i++) {
			for (Integer x : nums.get(i)) {
				mem[idx++] = new int[]{x, i};
			}
		}
		Arrays.sort(mem, (a, b) -> (a[0] - b[0]));
		int size = nums.size();
		int[] count = new int[size];
		int left = 0;
		int[] ret = {0, 20000000};
		for (int i = 0; i < len; ) {
			int x = mem[i][0];
			while (i < len && mem[i][0] == x) {
				count[mem[i][1]]++;
				if (count[mem[i][1]] == 1) {
					size--;
				}
				i++;
			}
			if (size == 0) {
				while (count[mem[left][1]] > 1) {
					count[mem[left][1]]--;
					left++;
				}
				if (mem[i - 1][0] - mem[left][0] < ret[1] - ret[0]) {
					ret[1] = mem[i - 1][0];
					ret[0] = mem[left][0];
				}
				count[mem[left][1]]--;
				left++;
				size++;
			}
		}
		return ret;
	}
	
	//面试题 17.24. 最大子矩阵
	public int[] getMaxMatrix(int[][] matrix) {
		int max = matrix[0][0];
		int[] ret = new int[4];
		int high = matrix.length;
		int len = matrix[0].length;
		int[][] mem = new int[high][len + 1];
		for (int i = 0; i < high; i++) {
			int[] x = mem[i];
			int[] t = matrix[i];
			for (int j = 1; j <= len; j++) {
				x[j] += x[j - 1] + t[j - 1];
			}
		}
		int sum, r1, c1;
		for (int i = 1; i <= len; i++) {
			for (int j = 0; j + i <= len; j++) {
				sum = 0;
				r1 = 0;
				c1 = j;
				for (int k = 0; k < high; k++) {
					sum += mem[k][j + i] - mem[k][j];
					if (sum > max) {
						max = sum;
						ret = new int[]{r1, c1, k, j + i - 1};
					}
					if (sum < 0) {
						sum = 0;
						r1 = k + 1;
					}
				}
			}
		}
		return ret;
	}
	
	//面试题 04.01. 节点间通路
	public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
		boolean[] visited = new boolean[n];
		HashSet<Integer>[] sets = new HashSet[n];
		for (int i = 0; i < n; i++) {
			sets[i] = new HashSet<>();
		}
		for (int[] x : graph) {
			sets[x[0]].add(x[1]);
		}
		findWhetherExistsPathHelper(sets, start, target, visited);
		return visited[target];
	}
	
	private void findWhetherExistsPathHelper(HashSet<Integer>[] sets, int start, int target,
		boolean[] visited) {
		if (visited[target]) {
			return;
		} else if (sets[start].contains(target)) {
			visited[target] = true;
			return;
		}
		visited[start] = true;
		for (Integer x : sets[start]) {
			if (!visited[x]) {
				findWhetherExistsPathHelper(sets, x, target, visited);
			}
		}
	}
	
	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int[] ret = new int[k];
		ret = maxNumberHelper(nums1, 0, nums1.length, nums2, 0, nums2.length, ret, 0, k);
		return ret;
	}
	
	private int[] maxNumberHelper(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2,
		int[] ret, int idx, int k) {
		if (k == 0) {
			return ret;
		}
		if (l1 == r1) {
			return maxNumberHelperHelper(nums2, l2, r2, ret, idx);
		} else if (l2 == r2) {
			return maxNumberHelperHelper(nums1, l1, r1, ret, idx);
			
		}
		int last = r2 - l2 + r1 - l1;
		
		int max1 = l1;
		for (int i = l1 + 1, c = 0; i < r1 && c + k < last; i++, c++) {
			if (nums1[max1] < nums1[i]) {
				max1 = i;
			}
		}
		
		int max2 = l2;
		for (int i = l2 + 1, c = 0; i < r2 && c + k < last; i++) {
			if (nums2[max2] < nums2[i]) {
				max2 = i;
			}
		}
		
		if (nums2[max2] > nums1[max1]) {
			ret[idx] = nums2[max2];
			return maxNumberHelper(nums1, l1, r1, nums2, max2 + 1, r2, ret, idx + 1, k - 1);
		} else if (nums2[max2] < nums1[max1]) {
			ret[idx] = nums1[max1];
			return maxNumberHelper(nums1, max1 + 1, r1, nums2, l2, r2, ret, idx + 1, k - 1);
		} else {
			ret[idx] = nums2[max2];
			int len = ret.length;
			int[] a = Arrays.copyOf(ret, len);
			a = maxNumberHelper(nums1, l1, r1, nums2, max2 + 1, r2, a, idx + 1, k - 1);
			int[] b = Arrays.copyOf(ret, len);
			b = maxNumberHelper(nums1, max1 + 1, r1, nums2, l2, r2, b, idx + 1, k - 1);
			for (int i = idx + 1; i < len; i++) {
				if (a[i] > b[i]) {
					return a;
				} else if (b[i] > a[i]) {
					return b;
				}
			}
			return a;
		}
	}
	
	private int[] maxNumberHelperHelper(int[] nums2, int l2, int r2, int[] ret, int idx) {
		int len = ret.length;
		int need = len - idx;
		int delete = r2 - l2 - need;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = l2; i < r2; i++) {
			while (!list.isEmpty() && delete > 0 && list.getLast() < nums2[i]) {
				list.removeLast();
				delete--;
			}
			list.add(nums2[i]);
		}
		for (int i = idx; i < len; i++) {
			ret[i] = list.removeFirst();
		}
		return ret;
	}
	
	//394. 字符串解码
	public String decodeString(String s) {
		StringBuffer ret = decodeStringHelper(s, 0, s.length());
		return ret.toString();
	}
	
	private StringBuffer decodeStringHelper(String s, int l, int r) {
		StringBuffer ret = new StringBuffer();
		int i = l;
		while (i < r && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
			ret.append(s.charAt(i));
			i++;
		}
		int t = 0;
		while (i < r && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
			t *= 10;
			t += s.charAt(i) - '0';
			i++;
		}
		if (i < r) {
			int count = 1;
			i++;
			int nl = i;
			for (; i < r; i++) {
				if (s.charAt(i) == '[') {
					count++;
				} else if (s.charAt(i) == ']') {
					count--;
				}
				if (count == 0) {
					break;
				}
			}
			StringBuffer before = decodeStringHelper(s, nl, i);
			while (t > 0) {
				ret.append(before);
				t--;
			}
			ret.append(s, i + 1, r);
		}
		return ret;
	}
	
	//60. 第k个排列
	public String getPermutation(int n, int k) {
		boolean[] used = new boolean[n];
		int base = 1;
		for (int i = 1; i < n; i++) {
			base *= i;
		}
		StringBuffer buffer = new StringBuffer();
		getPermutationHelper(base, k, used, buffer, n - 1);
		return buffer.toString();
	}
	
	private void getPermutationHelper(int base, int k, boolean[] used, StringBuffer buffer, int n) {
		
		int len = used.length;
		for (int i = 0; i < len; i++) {
			if (used[i]) {
				continue;
			}
			if (k > base) {
				k -= base;
			} else {
				buffer.append(i + 1);
				used[i] = true;
				if (n > 0) {
					getPermutationHelper(base / n, k, used, buffer, n - 1);
				}
				return;
			}
		}
	}
	
	//93. 复原IP地址
	public List<String> restoreIpAddresses(String s) {
		char[] chars = s.toCharArray();
		int[] xs = new int[4];
		ArrayList<String> ret = new ArrayList<>();
		restoreIpAddressesHelper(chars, xs, ret, 0, 0);
		return ret;
	}
	
	private void restoreIpAddressesHelper(char[] chars, int[] xs, ArrayList<String> ret, int i,
		int idx) {
		if (i == chars.length) {
			return;
		}
		for (; i < chars.length; i++) {
			xs[idx] *= 10;
			xs[idx] += chars[i] - '0';
			if (xs[idx] > 255) {
				return;
			}
			if (xs[idx] == 0) {
				if (idx < 3) {
					xs[idx + 1] = 0;
					restoreIpAddressesHelper(chars, xs, ret, i + 1, idx + 1);
				} else if (i == chars.length - 1) {
					break;
				}
				return;
			}
			if (idx < 3) {
				xs[idx + 1] = 0;
				restoreIpAddressesHelper(chars, xs, ret, i + 1, idx + 1);
			}
		}
		if (idx < 3) {
			return;
		}
		StringBuffer buffer = new StringBuffer();
		for (int x : xs) {
			buffer.append(x);
			buffer.append('.');
		}
		buffer.deleteCharAt(buffer.length() - 1);
		ret.add(buffer.toString());
	}
	
	public int subarraysDivByK(int[] A, int K) {
		int[] mem = new int[K];
		mem[0] = 1;
		int ret = 0;
		int c = 0;
		for (int i : A) {
			c += i;
			c %= K;
			if (c < 0) {
				c += K;
			}
			ret += mem[c];
			mem[c]++;
		}
		return ret;
	}
	
	//1367. 二叉树中的列表
	public boolean isSubPath(ListNode head, TreeNode root) {
		return isSubPathHelper(head, root) || isSubPath(head, root.left) || isSubPath(head,
			root.right);
	}
	
	private boolean isSubPathHelper(ListNode head, TreeNode root) {
		if (head == null) {
			return true;
		} else if (root == null || root.val != head.val) {
			return false;
		}
		return isSubPathHelper(head.next, root.left) || isSubPathHelper(head.next, root.right);
	}
	
	//287. 寻找重复数--鴿籠
	public int findDuplicate(int[] nums) {
		int slow = nums[0], fast = nums[slow];
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		fast = 0;
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[fast];
		}
		return slow;
	}
	
	public int[] findDiagonalOrder1(List<List<Integer>> nums) {
		int size = nums.size();
		int[] idxs = new int[size];
		int[] lens = new int[size];
		int len = 0;
		for (int i = 0; i < size; i++) {
			lens[i] = nums.get(i).size();
			len += lens[i];
		}
		int[] ret = new int[len];
		int idx = 0;
		LinkedList<Integer> cur = new LinkedList<>();
		HashSet<Integer> deleted = new HashSet<>();
		int l = 1;
		cur.add(0);
		while (idx < len) {
			deleted.clear();
			for (Integer x : cur) {
				List<Integer> list = nums.get(x);
				if (idxs[x] == lens[x] - 1) {
					deleted.add(x);
				}
				ret[idx++] = list.get(idxs[x]);
				idxs[x]++;
			}
			if (l < size) {
				cur.addFirst(l);
				l++;
			}
			if (cur.size() < 2) {
				break;
			}
			if (!deleted.isEmpty()) {
				cur.removeAll(deleted);
			}
		}
		if (cur.size() == 1) {
			Integer x = cur.get(0);
			List<Integer> list = nums.get(x);
			for (int i = idxs[x]; i < list.size(); i++) {
				ret[idx++] = list.get(i);
			}
		}
		return ret;
	}
	
	//1424. Diagonal Traverse II--超時
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
		int size = nums.size();
		int[] idxs = new int[size];
		int len = 0;
		for (int i = 0; i < size; i++) {
			idxs[i] = 0 - i;
			len += nums.get(i).size();
		}
		int[] ret = new int[len];
		int idx = 0;
		while (idx < len) {
			for (int i = size - 1; i >= 0; i--) {
				if (idxs[i] == Integer.MIN_VALUE) {
					continue;
				}
				if (nums.get(i).size() == idxs[i]) {
					idxs[i] = Integer.MIN_VALUE;
					continue;
				} else if (idxs[i] >= 0) {
					ret[idx++] = nums.get(i).get(idxs[i]);
				}
				idxs[i]++;
			}
		}
		return ret;
	}
	
	//1451. 重新排列句子中的单词
	public String arrangeWords(String text) {
		TreeMap<Integer, StringBuffer> map = new TreeMap<>();
		int len = text.length();
		int l = 0;
		StringBuffer x = new StringBuffer();
		for (int i = 0; i <= len; i++) {
			if (i == len || text.charAt(i) == ' ') {
				StringBuffer buffer = map.get(i - l);
				if (buffer == null) {
					buffer = new StringBuffer();
					map.put(i - l, buffer);
				}
				buffer.append(x);
				buffer.append(' ');
				x.setLength(0);
				l = i + 1;
			} else {
				char c = text.charAt(i);
				if (c < 'a') {
					c += 'a' - 'A';
				}
				x.append(c);
			}
		}
		for (Entry<Integer, StringBuffer> entry : map.entrySet()) {
			x.append(entry.getValue());
		}
		CharSequence charSequence = x.subSequence(0, 1);
		char c = charSequence.charAt(0);
		x.setCharAt(0, (char) (c - ('a' - 'A')));
		x.deleteCharAt(x.length() - 1);
		return x.toString();
	}
	
	public int isPrefixOfWord(String sentence, String searchWord) {
		int i = sentence.indexOf(searchWord, 0);
		if (i == 0) {
			return 0;
		}
		while (i > 0) {
			if (sentence.charAt(i - 1) == ' ') {
				int ret = 1, c = sentence.indexOf(' ');
				while (c < i) {
					ret++;
					c = sentence.indexOf(' ', c + 1);
				}
				return ret;
			}
			i = sentence.indexOf(searchWord, i + 1);
		}
		return -1;
	}
	
	//5418. 二叉树中的伪回文路径
	public int pseudoPalindromicPaths(TreeNode root) {
		int[] count = new int[10];
		pseudoPalindromicPathsHelper(root, count);
		return count[0];
	}
	
	private void pseudoPalindromicPathsHelper(TreeNode root, int[] count) {
		if (root.left == null && root.right == null) {
			count[root.val]++;
			int c = 0;
			for (int i = 1; i < 10; i++) {
				c += (count[i] & 1);
			}
			if (c < 2) {
				count[0]++;
			}
			count[root.val]--;
			return;
		}
		count[root.val]++;
		if (root.left != null) {
			pseudoPalindromicPathsHelper(root.left, count);
		}
		if (root.right != null) {
			pseudoPalindromicPathsHelper(root.right, count);
		}
		count[root.val]--;
	}
	
	//5417. 定长子串中元音的最大数目
	public int maxVowels(String s, int k) {
		int cur = 0, ret = 0;
		for (int i = 0; i < k; i++) {
			if ("aeiou".indexOf(s.charAt(i)) >= 0) {
				cur++;
			}
		}
		ret = cur;
		int len = s.length();
		for (int i = k; i < len; i++) {
			char c = s.charAt(i);
			if ("aeiou".indexOf(c) >= 0) {
				cur++;
			}
			c = s.charAt(i - k);
			if ("aeiou".indexOf(c) >= 0) {
				cur--;
			}
			ret = Math.max(ret, cur);
		}
		return ret;
	}
	
	//166. 分数到小数
	public String fractionToDecimal2(int numerator, int denominator) {
		long a = numerator, b = denominator;
		int flag = 1;
		if (a < 0) {
			a *= -1;
			flag *= -1;
		}
		if (b < 0) {
			b *= -1;
			flag *= -1;
		}
		long x = a % b;
		if (x == 0) {
			return a / b + "";
		}
		StringBuffer before = new StringBuffer((flag < 0 ? "-" : "") + (a / b + "."));
		StringBuffer after = new StringBuffer();
		HashMap<Long, Integer> map = new HashMap<>();
		long t = x;
		int idx = 0;
		while (t != 0) {
			if (map.containsKey(t)) {
				break;
			} else {
				map.put(t, idx);
				idx++;
				t *= 10;
				after.append(t / b);
				t %= b;
			}
		}
		if (t != 0) {
			int d = map.get(t);
			after.insert(d, '(');
			after.append(')');
		}
		before.append(after);
		return before.toString();
	}
	
	public String fractionToDecimal1(int numerator, int denominator) {
		int flag = 1;
		if (numerator < 0) {
			numerator *= -1;
			flag *= -1;
		}
		if (denominator < 0) {
			denominator *= -1;
			flag *= -1;
		}
		int x = numerator % denominator;
		if (x == 0) {
			return numerator / denominator + "";
		}
		StringBuffer before = new StringBuffer(
			(flag < 0 ? "-" : "") + (numerator / denominator + "."));
		StringBuffer after = new StringBuffer();
		int[] idxs = new int[denominator];
		Arrays.fill(idxs, -1);
		int idx = 0;
		while (x != 0) {
			if (idxs[x] < 0) {
				idxs[x] = idx++;
				x *= 10;
				after.append(x / denominator);
				x %= denominator;
			} else {
				break;
			}
		}
		if (x != 0) {
			after.insert(idxs[x], '(');
			after.append(')');
		}
		before.append(after);
		return before.toString();
	}
	
	public String fractionToDecimal(int numerator, int denominator) {
		int x = numerator % denominator;
		if (x == 0) {
			return numerator / denominator + "";
		}
		StringBuffer before = new StringBuffer(numerator / denominator + ".");
		StringBuffer after = new StringBuffer();
		HashMap<Integer, Integer> map = new HashMap<>();
		int idx = 0;
		while (x != 0) {
			x *= 10;
			if (!map.containsKey(x)) {
				after.append(x / denominator);
				map.put(x, idx++);
				x %= denominator;
			} else {
				break;
			}
		}
		if (x != 0) {
			int t = map.get(x);
			after.insert(t, '(');
			after.append(')');
		}
		before.append(after);
		return before.toString();
		
	}
	
	//815. 公交路线
	public int numBusesToDestination1(int[][] routes, int S, int T) {
		int[] visit = new int[1000000];
		Arrays.fill(visit, Integer.MAX_VALUE);
		visit[S] = 0;
		int count = 1;
		int len = routes.length;
		boolean[] visitBus = new boolean[len];
		boolean flag = true;
		while (flag) {
			flag = false;
			for (int i = 0; i < len; i++) {
				if (visitBus[i]) {
					continue;
				}
				for (int x : routes[i]) {
					if (visit[x] < count) {
						visitBus[i] = true;
						break;
					}
				}
				if (!visitBus[i]) {
					continue;
				}
				for (int x : routes[i]) {
					if (visit[x] > count) {
						if (x == T) {
							return count;
						}
						flag = true;
						visit[x] = count;
					}
				}
			}
			count++;
		}
		return -1;
	}
	
	public int numBusesToDestination(int[][] routes, int S, int T) {
		HashSet<Integer> cur = new HashSet<>(), next;
		boolean[] visit = new boolean[1000000];
		visit[S] = true;
		int count = 1;
		cur.add(S);
		int len = routes.length;
		boolean[] visitBus = new boolean[len];
		while (!cur.isEmpty()) {
			next = new HashSet<>();
			for (int i = 0; i < len; i++) {
				if (visitBus[i]) {
					continue;
				}
				for (int x : routes[i]) {
					if (cur.contains(x)) {
						visitBus[i] = true;
						break;
					}
				}
				if (visitBus[i]) {
					for (int x : routes[i]) {
						if (!visit[x]) {
							if (x == T) {
								return count;
							}
							next.add(x);
							visit[x] = true;
						}
					}
				}
			}
			count++;
			cur = next;
		}
		return -1;
	}
	
	public boolean isStraight(int[] nums) {
		int[] x = new int[14];
		int min = 14;
		for (int num : nums) {
			x[num]++;
			if (num > 0) {
				if (x[num] > 1) {
					return false;
				}
				min = Math.min(min, num);
			}
		}
		for (int i = min; i <= 13 && i < min + 5; i++) {
			if (x[i] == 0) {
				if (x[0] == 0) {
					return false;
				} else {
					x[0]--;
				}
			}
		}
		return true;
	}
	
	//面试题63. 股票的最大利润
	public int maxProfit(int[] prices) {
		int ret = 0;
		int min = prices[0];
		for (int price : prices) {
			ret = Math.max(ret, price - min);
			min = Math.min(min, price);
		}
		return ret;
	}
	
	//面试题 16.22. 兰顿蚂蚁
	public List<String> printKMoves(int K) {
		class P {
			
			int x, y;
			
			public P(int x, int y) {
				this.x = x;
				this.y = y;
			}
			
			@Override
			public boolean equals(Object o) {
				P p = (P) o;
				return x == p.x && y == p.y;
			}
			
			@Override
			public int hashCode() {
				return Objects.hash(x, y);
			}
		}
		
		HashSet<P> black = new HashSet<>();
		int x = 0, y = 0;
		//0r,1d,2l,3u;
		char[] dc = new char[]{'R', 'D', 'L', 'U'};
		int[][] ds = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int f = 0;
		while (K > 0) {
			P p = new P(x, y);
			if (black.contains(p)) {
				black.remove(p);
				f += 3;
			} else {
				black.add(p);
				f++;
			}
			f %= 4;
			x += ds[f][0];
			y += ds[f][1];
			K--;
		}
		int l = y, r = y, u = x, d = x;
		for (P p : black) {
			u = Math.min(u, p.x);
			d = Math.max(d, p.x);
			l = Math.min(l, p.y);
			r = Math.max(r, p.y);
		}
		char[][] chars = new char[d - u + 1][r - l + 1];
		for (char[] cur : chars) {
			Arrays.fill(cur, '_');
		}
		for (P p : black) {
			chars[p.x - u][p.y - l] = 'X';
		}
		chars[x - u][y - l] = dc[f];
		ArrayList<String> ret = new ArrayList<>();
		for (char[] cur : chars) {
			ret.add(new String(cur));
		}
		return ret;
	}
	
	//面试题 16.24. 数对和
	public List<List<Integer>> pairSums1(int[] nums, int target) {
		Arrays.sort(nums);
		int len = nums.length;
		int i = 0, j = len - 1;
		ArrayList<List<Integer>> ret = new ArrayList<>();
		while (i < j) {
			int x = nums[j] + nums[i];
			if (x == target) {
				List<Integer> list = Arrays.asList(nums[j], nums[i]);
				ret.add(list);
				i++;
				j++;
			} else if (x < target) {
				i++;
			} else {
				j--;
			}
		}
		return ret;
	}
	
	public List<List<Integer>> pairSums(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		ArrayList<List<Integer>> ret = new ArrayList<>();
		for (int num : nums) {
			Integer x = map.getOrDefault(target - num, 0);
			if (x > 0) {
				List<Integer> list = Arrays.asList(num, target - num);
				ret.add(list);
				map.put(target - num, x - 1);
			} else {
				map.put(num, map.getOrDefault(num, 0) + 1);
			}
		}
		return ret;
	}
	
	//面试题 17.10. 主要元素
	public int majorityElement(int[] nums) {
		int cur = 0, count = 0;
		int len = nums.length;
		for (int num : nums) {
			if (count == 0) {
				cur = num;
				count++;
			} else if (cur == num) {
				count++;
			} else {
				count--;
			}
		}
		if (count == 0) {
			return -1;
		}
		count = 0;
		for (int num : nums) {
			if (num == cur) {
				count++;
			}
		}
		return count * 2 > len ? cur : -1;
	}
	
	//面试题 08.10. 颜色填充
	public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
		if (newColor == image[sr][sc]) {
			return image;
		}
		floodFillHelper(image, sr, sc, newColor, image[sr][sc]);
		return image;
	}
	
	private void floodFillHelper(int[][] image, int x, int y, int newColor, int oldColor) {
		image[x][y] = newColor;
		int i, j, high = image.length, len = image[0].length;
		for (int[] d : ds_4) {
			i = x + d[0];
			j = y + d[1];
			if (i < 0 || j < 0 || i >= high || j >= len || image[i][j] != oldColor) {
				continue;
			}
			floodFillHelper(image, i, j, newColor, oldColor);
		}
	}
}
