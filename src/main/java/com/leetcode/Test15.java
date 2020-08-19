package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test15
 * @Author: WuMing
 * @CreateDate: 2019/12/9 11:04
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test15 {
	
	public static void main(String[] args) {
		Test15 t = new Test15();
		/*t.palindromePartition(
			"fyhowoxzyrincxivwarjuwxrwealesxsimsepjdqsstfggjnjhilvrwwytbgsqbpnwjaojfnmiqiqnyzijfmvekgakefjaxryyml"
			, 32);*/
		//t.shortestPath(new int[][]{{0}}, 1);
		long time = System.currentTimeMillis();
		System.out.println(time);
		String key = MD5Util
			.encoder((MD5Util.encoder("asdfsdsSDSD12") + "wxf9d093e94ba38998") + time);
		System.out.println(key);
		//	t.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
		
		
		
	}
	
	
	//1307
	
	
	//1306
	public boolean canReach(int[] arr, int start) {
		if (arr[start] == 0) {
			return true;
		}
		int len = arr.length;
		boolean[] flag = new boolean[len];
		flag[start] = true;
		int l = 0, r = 1;
		int[] ints = new int[len];
		ints[0] = start;
		int cur, next;
		while (l < r) {
			cur = ints[l];
			l++;
			next = cur + arr[cur];
			if (next < len && !flag[next]) {
				if (arr[next] == 0) {
					return true;
				} else {
					ints[r++] = next;
					flag[next] = true;
				}
			}
			next = cur - arr[cur];
			if (next >= 0 && !flag[next]) {
				if (arr[next] == 0) {
					return true;
				} else {
					ints[r++] = next;
					flag[next] = true;
				}
			}
		}
		return false;
	}
	
	int dl = 0, dsum = 0;
	
	public int deepestLeavesSum(TreeNode root) {
		deepestLeavesSumHelper(root, 0);
		return dsum;
	}
	
	private void deepestLeavesSumHelper(TreeNode root, int level) {
		if (root == null) {
			return;
		}
		deepestLeavesSumHelper(root.left, level + 1);
		if (level > dl) {
			dl = level;
			dsum = root.val;
		} else if (level == dl) {
			dsum += root.val;
		}
		deepestLeavesSumHelper(root.right, level + 1);
	}
	
	//1299
	public int[] replaceElements(int[] arr) {
		int cur = Integer.MIN_VALUE, before = -1;
		for (int i = arr.length-1; i >= 0; i--) {
			cur = Math.max(arr[i], cur);
			arr[i] = before;
			before = cur;
		}
		return arr;
	}
	
	//1300
	public int findBestValue(int[] arr, int target) {
		Arrays.sort(arr);
		int len = arr.length;
		int[] sum = new int[len];
		sum[0] = arr[0];
		for (int i = 1; i < len; i++) {
			sum[i] = sum[i - 1] + arr[i];
		}
		if (sum[len - 1] <= target) {
			return arr[len - 1];
		} else if (arr[0] * len >= target) {
			return target / len;
		}
		int l = 0, r = arr[len - 1], m;
		int ret = arr[len - 1], diff = sum[len - 1] - target;
		while (l <= r) {
			m = (l + r) >> 1;
			int idx = findBestValueHelper(arr, m);
			int val = (idx < 1 ? 0 : sum[idx - 1]) + (len - idx) * m;
			int newdiff = Math.abs(target - val);
			if (newdiff < diff) {
				ret = m;
				diff = newdiff;
			} else if (newdiff == diff && m < ret) {
				ret = m;
			}
			if (val < target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ret;
	}
	
	private int findBestValueHelper(int[] arr, int t) {
		int l = 0, r = arr.length - 1, m;
		while (l <= r) {
			m = (l + r) >> 1;
			if (arr[m] > t) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
	
	//1301
	public int[] pathsWithMaxScore(List<String> board) {
		int hi = board.size();
		char[][] b = new char[hi][];
		for (int i = 0; i < hi; i++) {
			b[i] = board.get(i).toCharArray();
		}
		int len = b[0].length;
		b[0][0] = '0';
		b[hi - 1][len - 1] = '0';
		int[][][] mem = new int[hi][len][2];
		mem[0][0][1] = 1;
		int val, mod = 1000000007;
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				if (b[i][j] != 'X') {
					if (i + 1 < hi && b[i + 1][j] != 'X') {
						val = mem[i][j][0] + b[i + 1][j] - '0';
						if (val > mem[i + 1][j][0]) {
							mem[i + 1][j][0] = val;
							mem[i + 1][j][1] = mem[i][j][1];
						} else if (val == mem[i + 1][j][0]) {
							mem[i + 1][j][1] += mem[i][j][1];
							mem[i + 1][j][1] %= mod;
						}
					}
					if (j + 1 < len && b[i][j + 1] != 'X') {
						val = mem[i][j][0] + b[i][j + 1] - '0';
						if (val > mem[i][j + 1][0]) {
							mem[i][j + 1][0] = val;
							mem[i][j + 1][1] = mem[i][j][1];
						} else if (val == mem[i][j + 1][0]) {
							mem[i][j + 1][1] += mem[i][j][1];
							mem[i][j + 1][1] %= mod;
						}
					}
					if (i + 1 < hi && j + 1 < len && b[i + 1][j + 1] != 'X') {
						val = mem[i][j][0] + b[i + 1][j + 1] - '0';
						if (val > mem[i + 1][j + 1][0]) {
							mem[i + 1][j + 1][0] = val;
							mem[i + 1][j + 1][1] = mem[i][j][1];
						} else if (val == mem[i + 1][j][0]) {
							mem[i + 1][j + 1][1] += mem[i][j][1];
							mem[i + 1][j + 1][1] %= mod;
						}
					}
				}
			}
		}
		int x = mem[hi - 1][len - 1][1];
		return x > 0 ? mem[hi - 1][len - 1] : new int[]{0, 0};
	}
	
	//超时
	public int[] pathsWithMaxScore1(List<String> board) {
		int hi = board.size();
		char[][] b = new char[hi][];
		for (int i = 0; i < hi; i++) {
			b[i] = board.get(i).toCharArray();
		}
		int len = b[0].length;
		b[0][0] = '0';
		b[hi - 1][len - 1] = '0';
		int[][][] mem = new int[hi][len][2];
		mem[hi - 1][len - 1][1] = 1;
		int[] ret = pathsWithMaxScoreHelper(b, mem, 0, 0);
		if (ret[1] > 0) {
			return ret;
		} else {
			return new int[]{0, 0};
		}
		
	}
	
	private int[] pathsWithMaxScoreHelper(char[][] b, int[][][] mem, int i, int j) {
		int[] ret = new int[2];
		if (i == b.length || j == b[0].length || b[i][j] == 'X') {
			return ret;
		}
		if (mem[i][j][1] > 0) {
			return mem[i][j];
		}
		pathsWithMaxScoreCheck(
			pathsWithMaxScoreHelper(b, mem, i + 1, j), ret);
		pathsWithMaxScoreCheck(
			pathsWithMaxScoreHelper(b, mem, i + 1, j + 1), ret);
		pathsWithMaxScoreCheck(
			pathsWithMaxScoreHelper(b, mem, i, j + 1), ret);
		ret[0] += b[i][j] - '0';
		mem[i][j] = ret;
		return ret;
	}
	
	private void pathsWithMaxScoreCheck(int[] cur, int[] ret) {
		if (cur[0] > ret[0]) {
			ret[0] = cur[0];
			ret[1] = cur[1];
		} else if (ret[0] == cur[0]) {
			ret[1] += cur[1];
			ret[1] %= 1000000007;
		}
	}
	
	
	//1305
	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		getAllElementsHelper(root1, a);
		getAllElementsHelper(root2, b);
		int i = 0, j = 0, as = a.size(), bs = b.size();
		if (as == 0) {
			return b;
		} else if (bs == 0) {
			return a;
		}
		ArrayList<Integer> ret = new ArrayList<>(as + bs);
		int va = a.get(0), vb = b.get(0);
		while (i < as || j < bs) {
			if (va < vb) {
				i++;
				ret.add(va);
				va = i == as ? Integer.MAX_VALUE : a.get(i);
			} else {
				j++;
				ret.add(vb);
				vb = j == bs ? Integer.MAX_VALUE : b.get(j);
			}
		}
		return ret;
		
	}
	
	private void getAllElementsHelper(TreeNode root, ArrayList<Integer> list) {
		if (root == null) {
			return;
		}
		getAllElementsHelper(root.left, list);
		list.add(root.val);
		getAllElementsHelper(root.right, list);
		
	}
	
	//1304
	public int[] sumZero(int n) {
		int[] ret = new int[n];
		for (int i = 1; i <= n / 2; i++) {
			ret[i - 1] = i;
			ret[n - i] = -i;
		}
		return ret;
	}
	
	public int[] sumZero1(int n) {
		int[] ret = new int[n];
		int sum = 0;
		for (int i = 0; i < n - 1; i++) {
			ret[i] = i;
			sum -= i;
		}
		ret[n] = sum;
		return ret;
	}
	
	//37
	//TODO
	public void solveSudoku(char[][] board) {
	
	}
	
	public void solveSudoku3(char[][] board) {
		int[] row = new int[9];
		Arrays.fill(row, (1 << 9) - 1);
		int[] col = new int[9];
		Arrays.fill(col, (1 << 9) - 1);
		int[][] block = new int[3][3];
		for (int[] ints : block) {
			Arrays.fill(ints, (1 << 9) - 1);
			
		}
		HashMap<Integer, Integer> amap = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			amap.put(i + 1, (1 << 9) - 1 - (1 << i));
		}
		int val, count = 81;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					val = amap.get(board[i][j] - '0');
					row[i] &= val;
					col[j] &= val;
					block[i / 3][j / 3] &= val;
					count--;
				}
			}
		}
		HashMap<Integer, Integer> bmap = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			bmap.put(1 << i, i + 1);
		}
		while (count > 0) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][j] == '.') {
						val = row[i] & col[j] & block[i / 3][j / 3];
						if (bmap.containsKey(val)) {
							count--;
							board[i][j] = (char) (bmap.get(val) + '0');
							row[i] &= amap.get(bmap.get(val));
							col[j] &= amap.get(bmap.get(val));
							block[i / 3][j / 3] &= amap.get(bmap.get(val));
						}
					}
				}
			}
		}
	}
	
	public void solveSudoku2(char[][] board) {
		int[] row = new int[9];
		int[] col = new int[9];
		int[][] block = new int[3][3];
		int val, count = 81;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					val = 1 << (board[i][j] - '1');
					row[i] |= val;
					col[j] |= val;
					block[i / 3][j / 3] |= val;
					count--;
				}
			}
		}
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < 9; i++) {
			map.put((1 << 9) - 1 - (1 << i), i + 1);
		}
		while (count > 0) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (board[i][j] == '.') {
						val = row[i] | col[j] | block[i / 3][j / 3];
						if (map.containsKey(val)) {
							count--;
							board[i][j] = (char) (map.get(val) + '0');
							row[i] |= 1 << (map.get(val) - 1);
							col[j] |= 1 << (map.get(val) - 1);
							block[i / 3][j / 3] |= 1 << (map.get(val) - 1);
						}
					}
				}
			}
		}
	}
	
	//这个算法有问题
	public void solveSudoku1(char[][] board) {
		int[][][] mem = new int[9][9][3];
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					mem[i][j] = new int[]{9, (1 << 10) - 1, 45};
				} else {
					list.add(i);
					list.add(j);
				}
			}
		}
		int x, y, cur, mask;
		int l, r, u, d;
		while (!list.isEmpty()) {
			x = list.removeFirst();
			y = list.removeFirst();
			cur = board[x][y] - '0';
			mask = 1 << (cur - 1);
			for (int i = 0; i < 9; i++) {
				if ((mem[x][i][1] & mask) > 0) {
					mem[x][i][1] -= mask;
					mem[x][i][0]--;
					mem[x][i][2] -= cur;
					if (mem[x][i][0] == 1) {
						board[x][i] = (char) (mem[x][i][2] + '0');
						list.add(x);
						list.add(i);
					}
				}
				if ((mem[i][y][1] & mask) > 0) {
					mem[i][y][1] -= mask;
					mem[i][y][0]--;
					mem[i][y][2] -= cur;
					if (mem[i][y][0] == 1) {
						board[i][y] = (char) (mem[i][y][2] + '0');
						list.add(i);
						list.add(y);
					}
				}
			}
			if (y < 3) {
				l = 0;
				r = 3;
			} else if (y < 6) {
				l = 3;
				r = 6;
			} else {
				l = 6;
				r = 9;
			}
			if (x < 3) {
				u = 0;
				d = 3;
			} else if (x < 6) {
				u = 3;
				d = 6;
			} else {
				u = 6;
				d = 9;
			}
			for (int i = u; i < d; i++) {
				for (int j = l; j < r; j++) {
					if ((mem[i][j][1] & mask) > 0) {
						mem[i][j][1] -= mask;
						mem[i][j][0]--;
						mem[i][j][2] -= cur;
						if (mem[i][j][0] == 1) {
							board[i][j] = (char) (mem[i][j][2] + '0');
							list.add(i);
							list.add(j);
						}
					}
				}
			}
		}
		int i = 0;
		i++;
	}
	
	//30
	public List<Integer> findSubstring(String s, String[] words) {
		int len = words.length;
		ArrayList<Integer> ret = new ArrayList<>();
		if (len == 0) {
			return ret;
		}
		char[] array = s.toCharArray();
		int length = words[0].length();
		if (array.length < len * length) {
			return ret;
		}
		int[] count = new int[26];
		int[] cur = new int[26];
		for (String word : words) {
			for (int i = 0; i < length; i++) {
				count[word.charAt(i) - 'a']++;
			}
		}
		int i = 0;
		for (; i < len * length - 1; i++) {
			cur[s.charAt(i) - 'a']++;
		}
		HashMap<String, Integer> map = new HashMap<>();
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		
		for (; i < array.length; i++) {
			cur[array[i] - 'a']++;
			if (Arrays.hashCode(cur) == Arrays.hashCode(count) && findSubstringHelper1(array,
				i + 1 - len * length,
				length, map
				, len)) {
				ret.add(i + 1 - len * length);
			}
			cur[array[i + 1 - len * length] - 'a']--;
		}
		return ret;
	}
	
	private boolean findSubstringHelper1(char[] array, int idx, int len,
		HashMap<String, Integer> map, int count) {
		if (count == 0) {
			return true;
		}
		String s = new String(array, idx, len);
		;
		Integer c = map.get(s);
		if (c != null && c > 0) {
			map.put(s, c - 1);
			if (findSubstringHelper1(array, idx + len, len, map, count - 1)) {
				map.put(s, c);
				return true;
			} else {
				map.put(s, c);
			}
		}
		
		return true;
	}
	
	private boolean findSubstringHelper(int[] cur, int[] count) {
		for (int i = 0; i < 26; i++) {
			if (cur[i] != count[i]) {
				return false;
			}
		}
		return true;
	}
	
	public List<Integer> findSubstring1(String s, String[] words) {
		if (words.length == 0) {
			return new ArrayList<>();
		}
		
		int listLen = words.length;
		int wordLen = words[0].length();
		if (s.length() < listLen * wordLen) {
			return new ArrayList<>();
		}
		
		List<Integer> result = new ArrayList<>();
		Map<String, Integer> wordMap = new HashMap<>();
		
		for (String word : words) {
			wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
		}
		
		for (int i = 0; i < wordLen; i++) {
			for (int j = i; j <= s.length() - wordLen * listLen; j += wordLen) {
				Map<String, Integer> current = new HashMap<>();
				
				for (int k = listLen - 1; k >= 0; k--) {
					String temp = s.substring(j + k * wordLen, j + k * wordLen + wordLen);
					int occurrences = current.getOrDefault(temp, 0) + 1;
					
					if (occurrences > wordMap.getOrDefault(temp, 0)) {
						j += k * wordLen;
						break;
					}
					current.put(temp, occurrences);
					
					// last iteration
					if (k == 0) {
						result.add(j);
					}
				}
			}
		}
		
		return result;
	}
	
	
	//45
	public int jump(int[] nums) {
		int len = nums.length;
		boolean[] visited = new boolean[len];
		visited[0] = true;
		int ret = 0;
		ArrayList<Integer> cur = new ArrayList<>(), next;
		cur.add(0);
		while (true) {
			ret++;
			next = new ArrayList<>();
			for (Integer i : cur) {
				for (int j = 1; j <= nums[i]; j++) {
					if (!visited[j + i] && i + j < len - 1) {
						visited[j + i] = true;
						next.add(i + j);
					} else if (i + j == len - 1) {
						return ret;
					}
				}
			}
			cur = next;
		}
	}
	
	public int jump1(int[] nums) {
		
		int len = nums.length;
		if (len == 1) {
			return 0;
		}
		boolean[] visited = new boolean[len];
		visited[0] = true;
		int ret = 0;
		int[] idx = new int[len];
		int l = 0, r = 1, t, x;
		while (true) {
			ret++;
			t = r;
			for (int i = l; i < t; i++) {
				x = idx[i];
				if (x + nums[x] >= len - 1) {
					return ret;
				}
				for (int j = nums[x]; j > 0; j--) {
					if (!visited[x + j]) {
						visited[x + j] = true;
						idx[r] = x + j;
						r++;
					}
				}
			}
			l = t;
		}
	}
	
	public int jump2(int[] nums) {
		int end = 0, maxPosition = 0, steps = 0;
		int len = nums.length;
		if (len == 1) {
			return 0;
		}
		for (int i = 0; i < len; ++i) {
			maxPosition = Math.max(maxPosition, i + nums[i]);
			if (maxPosition >= len - 1) {
				return steps + 1;
			}
			if (i == end) {
				end = maxPosition;
				++steps;
			}
		}
		return steps;
	}
	
	//42
	public int trap(int[] height) {
		int h = 0;
		int len = height.length;
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum -= height[i];
			if (height[i] > height[h]) {
				h = i;
			}
		}
		int max = 0;
		for (int i = 0; i < h; i++) {
			max = Math.max(max, height[i]);
			sum += max;
		}
		max = 0;
		for (int i = len - 1; i >= h; i--) {
			max = Math.max(max, height[i]);
			sum += max;
		}
		return sum;
	}
	
	//1269
	public int numWays2(int steps, int arrLen) {
		int len = Math.min(steps, arrLen);
		int[] cur = new int[len + 2];
		cur[1] = 1;
		int[] next = cur.clone();
		int mod = 1000000007;
		for (int i = 1; i <= steps; i++) {
			for (int j = 1; j < len + 1; j++) {
				next[j] += cur[j - 1] + cur[j + 1];
				next[j] %= mod;
			}
			cur = next.clone();
		}
		return cur[1];
	}
	
	public int numWays1(int steps, int arrLen) {
		if (arrLen == 1) {
			return 1;
		}
		int len = Math.min(steps, arrLen);
		int[] cur = new int[len], next = new int[len];
		cur[0] = 1;
		next[0] = 1;
		
		int mod = 1000000007;
		for (int i = 1; i <= steps; i++) {
			for (int j = 0; j <= Math.min(i, len - 1); j++) {
				if (j > 0) {
					next[j] += cur[j - 1];
					next[j] %= mod;
				}
				if (j < len - 1) {
					next[j] += cur[j + 1];
					next[j] %= mod;
				}
			}
			cur = next.clone();
		}
		return (int) cur[0];
	}
	
	//steps=500,arrLen=100000会超时,超过steps永远不会用到
	public int numWays(int steps, int arrLen) {
		if (arrLen == 1) {
			return 1;
		}
		long[] cur = new long[arrLen], next;
		cur[0] = 1;
		int mod = 1000000007;
		for (int i = 0; i < steps; i++) {
			next = new long[arrLen];
			next[0] = cur[0] + cur[1];
			next[0] %= mod;
			for (int j = 1; j < Math.min(i + 1, arrLen - 1); j++) {
				next[j] = cur[j] + cur[j - 1] + cur[j + 1];
				next[j] %= mod;
			}
			next[arrLen - 1] = cur[arrLen - 2] + cur[arrLen - 1];
			next[arrLen - 1] %= mod;
			cur = next;
		}
		return (int) cur[0];
	}
	
	public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes,
		int[] initialBoxes) {
		int len = status.length;
		int[] flag = new int[len];
		for (int i = 0; i < len; i++) {
			if (status[i] == 1) {
				flag[i] |= 1;
			}
		}
		int ret = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int initialBox : initialBoxes) {
			list.add(initialBox);
			flag[initialBox] = 4;
			ret += candies[initialBox];
		}
		while (!list.isEmpty()) {
			Integer cur = list.removeFirst();
			for (int i : keys[cur]) {
				flag[i] |= 1;
				if (flag[i] == 3) {
					list.add(i);
					flag[i] = 4;
					ret += candies[i];
				}
			}
			for (int i : containedBoxes[cur]) {
				flag[i] |= 2;
				if (flag[i] == 3) {
					list.add(i);
					flag[i] = 4;
					ret += candies[i];
				}
			}
		}
		return ret;
	}
	
	
	//1296
	public boolean isPossibleDivide1(int[] nums, int k) {
		if (nums.length % k != 0) {
			return false;
		}
		Arrays.sort(nums);
		LinkedList<int[]> list = new LinkedList<>();
		int cur = 0;
		
		int count = 0;
		for (int num : nums) {
			if (num != cur) {
				list.add(new int[]{cur, count});
				cur = num;
				count = 1;
			} else {
				count++;
			}
		}
		list.add(new int[]{cur, count});
		//TODO
		return true;
	}
	
	//1296
	public boolean isPossibleDivide(int[] nums, int k) {
		if (nums.length % k != 0) {
			return false;
		}
		TreeMap<Integer, Integer> map = new TreeMap<>();
		
		for (int num : nums) {
			map.put(num, map.getOrDefault(num, 0) + 1);
		}
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() > 0) {
				if (!isPossibleDivideHelper(entry.getKey(), entry.getValue(), map, k)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean isPossibleDivideHelper(Integer key, Integer val, TreeMap<Integer, Integer> map,
		int k) {
		for (int i = key; i < key + k; i++) {
			Integer x = map.get(i);
			if (x == null || x < val) {
				return false;
			}
			map.put(i, x - val);
		}
		return true;
	}
	
	//1295
	public int findNumbers(int[] nums) {
		int ret = 0;
		for (int num : nums) {
			
			if (num < 10) {
			
			} else if (num < 100) {
				ret++;
			} else if (num < 1000) {
			
			} else if (num < 10000) {
				ret++;
			} else if (num < 100000) {
			
			} else if (num < 1000000) {
				ret++;
			}
		}
		return ret;
	}
	
	//1291
	public List<Integer> sequentialDigits(int low, int high) {
		int cur, x;
		x = low;
		int i = 0;
		while (x > 0) {
			i++;
			x /= 10;
		}
		ArrayList<Integer> ret = new ArrayList<>();
		for (; i < 10; i++) {
			for (int j = 1; j <= 10 - i; j++) {
				cur = j;
				x = 0;
				for (int k = 0; k < i; k++, cur++) {
					x *= 10;
					x += cur;
				}
				if (x > high) {
					break;
				}
				if (x >= low) {
					ret.add(x);
				}
			}
			if (x > high) {
				break;
			}
		}
		return ret;
	}
	
	//1288
	public int removeCoveredIntervals(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> {
			if (a[0] == b[0]) {
				return b[1] - a[1];
			} else {
				return a[0] - b[0];
			}
		});
		int len = intervals.length;
		int ret = len;
		boolean[] flag = new boolean[len];
		int r;
		for (int i = 0; i < len; i++) {
			if (flag[i]) {
				continue;
			}
			r = intervals[i][1];
			for (int j = i + 1; j < len; j++) {
				if (!flag[j] && intervals[j][1] <= r) {
					ret--;
					flag[j] = true;
				} else if (intervals[j][0] >= r) {
					break;
				}
			}
		}
		return ret;
	}
	
	
	public int removeCoveredIntervals1(int[][] intervals) {
		Arrays.sort(intervals, (a, b) -> {
			if (a[0] == b[0]) {
				return b[1] - a[1];
			} else {
				return a[0] - b[0];
			}
		});
		int len = intervals.length;
		int ret = len;
		
		int right = 0;
		for (int[] interval : intervals) {
			if (interval[1] <= right) {
				ret--;
			} else {
				right = interval[1];
			}
		}
		return ret;
	}
	
	public int maxSideLength(int[][] mat, int threshold) {
		int hi = mat.length;
		int len = mat[0].length;
		int ret = 0;
		int[][] sum = new int[hi][len + 1];
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				sum[i][j + 1] = sum[i][j] + mat[i][j];
				if (ret == 0 && mat[i][j] <= threshold) {
					ret = 1;
				}
			}
		}
		if (ret == 0) {
			return 0;
		}
		int l = 1, r = Math.min(hi, len), m;
		while (l <= r) {
			m = (l + r) / 2;
			if (maxSideLengthHelper(sum, m, threshold)) {
				ret = m;
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return ret;
	}
	
	private boolean maxSideLengthHelper(int[][] sum, int x, int threshold) {
		int hi = sum.length;
		int len = sum[0].length;
		int[] dp = new int[hi + 1];
		
		for (int j = 0; j < len - x; j++) {
			int i = 0;
			for (; i < x - 1; i++) {
				dp[i + 1] = dp[i] + sum[i][j + x] - sum[i][j];
			}
			for (; i < hi; i++) {
				dp[i + 1] = dp[i] + sum[i][j + x] - sum[i][j];
				if (dp[i + 1] - dp[i + 1 - x] <= threshold) {
					return true;
				}
			}
		}
		return false;
	}
	
	public int shortestPath(int[][] grid, int k) {
		int hi = grid.length;
		int len = grid[0].length;
		if (hi == 1 && len == 1) {
			return 0;
		}
		int[][][] mem = new int[hi][len][k + 1];
		for (int i = 0; i < hi; i++) {
			for (int j = 0; j < len; j++) {
				Arrays.fill(mem[i][j], Integer.MAX_VALUE);
			}
		}
		Arrays.fill(mem[0][0], 0);
		LinkedList<int[]> list = new LinkedList<>();
		list.add(new int[]{0, 0, k});
		int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		int l, x, y, v;
		int[] cur;
		while (!list.isEmpty()) {
			cur = list.removeFirst();
			l = cur[2];
			v = mem[cur[0]][cur[1]][l];
			for (int[] d : ds) {
				x = cur[0] + d[0];
				y = cur[1] + d[1];
				if (x < 0 || y < 0 || x >= hi || y >= len || l < grid[x][y]) {
					continue;
				}
				if (x == hi - 1 && y == len - 1) {
					return v + 1;
				}
				int i = l - grid[x][y];
				for (; i <= k; i++) {
					if (v + 1 >= mem[x][y][i]) {
						break;
					}
				}
				if (i > k) {
					mem[x][y][l - grid[x][y]] = v + 1;
					list.add(new int[]{x, y, l - grid[x][y]});
				}
			}
		}
		return -1;
	}
	
	public int getDecimalValue(ListNode head) {
		int ret = 0;
		while (head != null) {
			ret <<= 1;
			ret += head.val;
			head = head.next;
		}
		return ret;
	}
	
	int minFallingPathSum = Integer.MAX_VALUE;
	
	public int minFallingPathSum1(int[][] arr) {
		int[] before = minFallingPathSumHelper(arr[0]), cur;
		int hi = arr.length;
		int len = arr[0].length;
		int[] ret = new int[len], next;
		Arrays.fill(ret, Integer.MAX_VALUE);
		ret[before[0]] = arr[0][before[0]];
		ret[before[1]] = arr[0][before[1]];
		for (int i = 1; i < hi; i++) {
			cur = minFallingPathSumHelper(arr[i]);
			next = new int[len];
			Arrays.fill(next, Integer.MAX_VALUE);
			for (int x : cur) {
				for (int y : before) {
					if (x != y) {
						next[x] = Math.min(next[x], ret[y] + arr[i][x]);
					}
				}
			}
			before = cur;
			ret = next;
		}
		int min = Integer.MAX_VALUE;
		for (int i : ret) {
			min = Math.min(i, min);
		}
		return min;
	}
	
	private int[] minFallingPathSumHelper(int[] arr) {
		int[] ret;
		if (arr[0] < arr[1]) {
			ret = new int[]{0, 1};
		} else {
			ret = new int[]{1, 0};
		}
		int len = arr.length;
		for (int i = 2; i < len; i++) {
			if (arr[i] < arr[ret[0]]) {
				ret[1] = ret[0];
				ret[0] = i;
			} else if (arr[i] < arr[ret[1]]) {
				ret[1] = i;
			}
		}
		return ret;
	}
	
	
	public int minFallingPathSum(int[][] arr) {
		int hi = arr.length;
		int len = arr[0].length;
		int[] min = new int[len], next;
		for (int i = 0; i < hi; i++) {
			next = new int[len];
			Arrays.fill(next, Integer.MAX_VALUE);
			for (int j = 0; j < len; j++) {
				for (int k = 0; k < len; k++) {
					if (j != k) {
						next[j] = Math.min(next[j], min[j] + arr[i][k]);
					}
				}
			}
			min = next;
		}
		int ret = Integer.MAX_VALUE;
		for (int i : min) {
			ret = Math.min(i, ret);
		}
		return ret;
	}
	
	public int findSpecialInteger(int[] arr) {
		int length = arr.length > 4 ? arr.length : 5;
		int cur = -1;
		int count = 0;
		for (int i : arr) {
			if (i > cur) {
				cur = i;
				count = 1;
			} else {
				count++;
			}
			if (count * 4 > length) {
				return cur;
			}
		}
		return cur;
	}
	
	public boolean canCross1(int[] stones) {
		int len = stones.length;
		if (len > 1 && stones[1] != 1) {
			return false;
		}
		boolean[][] dp = new boolean[len][len];
		return canCrossHelper(stones, 1, 1, dp);
	}
	
	private boolean canCrossHelper(int[] stones, int cur, int jump, boolean[][] dp) {
		int len = stones.length;
		int ds = stones[len - 1] - stones[cur];
		if (ds <= jump + 1 && ds >= jump - 1) {
			return true;
		}
		if (dp[cur][jump]) {
			return false;
		}
		for (int i = cur + 1; i < len; i++) {
			ds = stones[i] - stones[cur];
			if (ds > jump + 1) {
				break;
			} else if (ds >= jump - 1) {
				if (canCrossHelper(stones, i, ds, dp)) {
					return true;
				}
			}
		}
		dp[cur][jump] = false;
		return false;
	}
	
	
	public boolean canCross(int[] stones) {
		HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
		for (int stone : stones) {
			map.put(stone, new HashSet<>());
		}
		map.get(0).add(0);
		int len = stones.length;
		int target = stones[len - 1];
		int next;
		int[] t = {-1, 0, 1};
		for (int stone : stones) {
			for (Integer cur : map.get(stone)) {
				for (int i : t) {
					next = stone + i + cur;
					if (next == target) {
						return true;
					} else if (map.containsKey(next) && next != stone) {
						map.get(next).add(cur + i);
					}
				}
			}
		}
		return false;
	}
	
	public int atMostNGivenDigitSet(String[] D, int N) {
		int t = N, c = 0;
		while (t > 0) {
			c++;
			t /= 10;
		}
		int[] count = new int[c + 1];
		int[] num = new int[c + 1];
		t = N;
		for (int i = c; i >= 0; i--) {
			num[i] = t % 10;
			t /= 10;
		}
		int l = D.length;
		count[c] = l;
		for (int i = c - 1; i >= 1; i--) {
			count[i] = l * count[i + 1];
		}
		int[] ints = new int[l];
		for (int i = 0; i < l; i++) {
			ints[i] = Integer.valueOf(D[i]);
		}
		int ret = atMostNGivenDigitSetHelper(ints, count, num, 1);
		for (int i = 2; i <= c; i++) {
			ret += count[i];
		}
		return ret;
	}
	
	private int atMostNGivenDigitSetHelper(int[] d, int[] count, int[] num, int i) {
		int ret = 0;
		int len = count.length;
		if (i == len) {
			return 1;
		}
		for (int cur : d) {
			if (cur < num[i]) {
				ret += i + 1 < len ? count[i + 1] : 1;
			} else if (cur == num[i]) {
				ret += atMostNGivenDigitSetHelper(d, count, num, i + 1);
			} else {
				break;
			}
		}
		return ret;
	}
	
	public int subarraysWithKDistinct(int[] A, int K) {
		int len = A.length;
		int[] count = new int[1 + len];
		HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
		int i = 0, j = 0, k = 0, c = 0, ret = 0;
		for (; i < len; i++) {
			count[A[i]]++;
			if (count[A[i]] == 1) {
				c++;
			}
			if (map.containsKey(A[i])) {
				map.get(A[i]).add(i);
			} else {
				LinkedList<Integer> list = new LinkedList<>();
				list.add(i);
				map.put(A[i], list);
			}
			if (c == K) {
				k = i;
				i++;
				break;
			}
		}
		LinkedList<Integer> l;
		for (; i < len; i++) {
			count[A[i]]++;
			if (count[A[i]] == 1) {
				c++;
			}
			if (map.containsKey(A[i])) {
				map.get(A[i]).add(i);
			} else {
				LinkedList<Integer> list = new LinkedList<>();
				list.add(i);
				map.put(A[i], list);
			}
			while (c > K) {
				ret += (i - k);
				count[A[j]]--;
				l = map.get(A[j]);
				l.removeFirst();
				if (count[A[j]] == 0) {
					c--;
					k = i;
				} else {
					k = Math.max(k, l.getFirst());
				}
				j++;
			}
		}
		while (c == K) {
			ret += (i - k);
			count[A[j]]--;
			l = map.get(A[j]);
			l.removeFirst();
			if (count[A[j]] == 0) {
				c--;
			} else {
				k = Math.max(k, l.getFirst());
			}
			j++;
		}
		return ret;
	}
	
	public int palindromePartition(String s, int k) {
		int len = s.length();
		int[][] mem = new int[len][len];
		for (int i = 0; i < len; i++) {
			palindromePartitionHelper(mem, i - 1, i + 1, s);
			palindromePartitionHelper(mem, i, i + 1, s);
		}
		int[] min = new int[len], next;
		for (int i = 0; i < len; i++) {
			min[i] = mem[0][i];
		}
		for (int i = 1; i < k; i++) {
			next = new int[len];
			Arrays.fill(next, len);
			for (int j = 0; j < len; j++) {
				for (int l = 0; l < j; l++) {
					next[j] = Math.min(next[j], min[l] + mem[l + 1][j]);
				}
			}
			min = next;
		}
		return min[len - 1];
	}
	
	
	private void palindromePartitionHelper(int[][] mem, int l, int r, String s) {
		int len = s.length();
		int count = 0;
		while (l >= 0 && r < len) {
			if (s.charAt(l) != s.charAt(r)) {
				count++;
			}
			mem[l][r] = count;
			l--;
			r++;
		}
	}
}
