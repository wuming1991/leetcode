package com.leetcode2;

import static com.base.Constant.ds_4;
import static com.base.Constant.stringToArr;

import com.base.ListNode;
import com.base.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ProjectName: leetcode
 * @Package: com.leetcode2
 * @Class Test8
 * @Author: WuMing
 * @CreateDate: 2020/9/24 17:00
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test8 {
	
	public static void main(String[] args) {
		Test8 test = new Test8();
		test.countEval("0&0&0&1^1|0",1);
	}
	//面试题 08.14. 布尔运算
	public int countEval(String s, int result) {
		int len = s.length();
		int nlen = (len + 1) / 2;
		char c;
		int[][][] mem = new int[nlen][nlen][2];
		for (int i = 0; i < len; i+=2) {
			mem[i/2][i/2][s.charAt(i)-'0']=1;
		}
		for (int i = 1; i < nlen; i++) {
			for (int j = 0; j+i <nlen ; j++) {
				for (int k = j; k <j+i ; k++) {
					c=s.charAt(k*2+1);
					if(c=='|'){
						mem[j][j+i][0]+=mem[j][k][0]*mem[k+1][j+i][0];
						mem[j][j+i][1]+=mem[j][k][1]*mem[k+1][j+i][0]+mem[j][k][0]*mem[k+1][j+i][1]+mem[j][k][1]*mem[k+1][j+i][1];
					}else if(c=='&'){
						mem[j][j+i][0]+=mem[j][k][1]*mem[k+1][j+i][0]+mem[j][k][0]*mem[k+1][j+i][1]+mem[j][k][0]*mem[k+1][j+i][0];
						mem[j][j+i][1]+=mem[j][k][1]*mem[k+1][j+i][1];
					}else{
						mem[j][j+i][0]+=mem[j][k][1]*mem[k+1][j+i][1]+mem[j][k][0]*mem[k+1][j+i][0];
						mem[j][j+i][1]+=mem[j][k][1]*mem[k+1][j+i][0]+mem[j][k][0]*mem[k+1][j+i][1];
					}
				}
			}
		}
		return mem[0][nlen-1][result];
	}
	public int smallestDistancePair(int[] nums, int k) {
		Arrays.sort(nums);
		int l=0,r = 1000000;
		int  m;
		long x;
		while (l<r) {
			m=(l+r)/2;
			x=smallestDistancePairHelper(nums,m,k);
			if(x>k){
				r=m;
			}else if(x<k){
				l=m+1;
			}else{
				return m;
			}
		}
		return l;
	}
	
	private long smallestDistancePairHelper(int[] nums, int m,int k) {
		int l=0,r=0;long ret=0;
		int len=nums.length;
		while (l<len){
			if(r<=l){
				r=l+1;
			}
			while (r<len&&nums[r]-nums[l]<=m){
				r++;
			}
			ret+=r-1-l;
			if(ret>k){
				return ret;
			}
			l++;
		}
		return ret;
	}
	
	//552. 学生出勤记录 II
	public int checkRecord(int n) {
		if (n < 2) {
			return 3 * n;
		}
		int[] cur = new int[]{1, 0, 1, 0, 0, 0, 1}, next = new int[7], t;
		//int[][] map=new int[][]{{0,2,4},{1,3,5,6},{0},{1,6},{2},{3},{0,2,4}};
		long x, mod = 1000000007;
		while (n > 1) {
			next[0] = cur[0] + cur[2] + cur[4];
			next[1] = cur[1] + cur[3] + cur[5] + cur[6];
			next[2] = cur[0];
			next[3] = cur[1] + cur[6];
			next[4] = cur[2];
			next[5] = cur[3];
			next[6] = cur[0] + cur[2] + cur[4];
			n--;
			t = cur;
			cur = next;
			next = t;
		}
		x = 0;
		for (int i : cur) {
			x += i;
		}
		return (int) (x % mod);
	}
	
	public boolean isStraight(int[] nums) {
		int[] mem = new int[14];
		int min = 13, max = 1;
		for (int num : nums) {
			mem[num]++;
			if (num > 0 && mem[num] > 1) {
				return false;
			}
			if (num > max) {
				max = num;
			}
			if (num < min && num > 0) {
				min = num;
			}
		}
		return max - min <= 4 || mem[0] == 5;
	}
	
	public int[] findSwapValues(int[] array1, int[] array2) {
		int x = 0;
		for (int i : array1) {
			x += i;
		}
		HashSet<Integer> set = new HashSet<>();
		for (int i : array2) {
			x -= i;
		}
		for (int i : array1) {
			if (set.contains(i + x)) {
				return new int[]{i, i + x};
			}
		}
		return new int[0];
	}
	
	//超时---分组方式需要改进
	public int[][] matrixRankTransform(int[][] matrix) {
		int high = matrix.length;
		int len = matrix[0].length;
		int[][] ret = new int[high][len];
		int[][] mem = new int[high * len][3];
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				mem[i * len + j] = new int[]{matrix[i][j], i, j};
			}
		}
		Arrays.sort(mem, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
		int[] row = new int[high];
		int[] col = new int[len];
		int v, x, y, xx, yy;
		HashSet<Integer> xset = new HashSet<>();
		HashSet<Integer> yset = new HashSet<>();
		int t, tt;
		for (int i = 0; i < high * len; i++) {
			v = mem[i][0];
			x = mem[i][1];
			y = mem[i][2];
			if (ret[x][y] == 0) {
				xset.add(x);
				yset.add(y);
				t = Math.max(row[x] + 1, col[y] + 1);
				for (int j = i + 1; j < high * len && v == mem[j][0]; j++) {
					xx = mem[j][1];
					yy = mem[j][2];
					if (xset.contains(xx) || yset.contains(yy)) {
						if (xset.add(xx) || yset.add(yy)) {
							j = i + 1;
						}
						tt = Math.max(row[xx] + 1, col[yy] + 1);
						t = Math.max(t, tt);
					}
				}
				for (int j = i; j < high * len && v == mem[j][0]; j++) {
					xx = mem[j][1];
					yy = mem[j][2];
					if (xset.contains(xx) || yset.contains(yy)) {
						ret[xx][yy] = t;
						if (t > row[xx]) {
							row[xx] = t;
						}
						if (t > col[yy]) {
							col[yy] = t;
						}
					}
				}
				xset.clear();
				yset.clear();
			}
		}
		return ret;
	}
	
	
	public char slowestKey(int[] releaseTimes, String keysPressed) {
		int cur = 0;
		int[] mem = new int[26];
		int[] idx = new int[26];
		for (int i = 0; i < 26; i++) {
			idx[i] = keysPressed.lastIndexOf(i + 'a');
		}
		int len = releaseTimes.length;
		for (int i = 0; i < len; i++) {
			mem[keysPressed.charAt(i) - 'a'] = Math
				.max(mem[keysPressed.charAt(i) - 'a'], releaseTimes[i] - cur);
			cur = releaseTimes[i];
		}
		int ret = 0;
		for (int i = 0; i < 26; i++) {
			if (mem[i] > mem[ret]) {
				ret = i;
			} else if (mem[i] == mem[ret] && idx[i] > idx[ret]) {
				ret = i;
			}
		}
		return (char) (ret + 'a');
	}
	
	public int minimumEffortPath(int[][] heights) {
		int high = heights.length;
		int len = heights[0].length;
		int[][] mem = new int[high][len];
		for (int i = 0; i < high; i++) {
			Arrays.fill(mem[i], Integer.MAX_VALUE);
		}
		mem[0][0] = 0;
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		int x, y, c, nx, ny, t;
		while (!list.isEmpty()) {
			c = list.removeFirst();
			x = c / 100;
			y = c % 100;
			c = mem[x][y];
			for (int[] d : ds_4) {
				nx = x + d[0];
				ny = y + d[1];
				if (nx < 0 || ny < 0 || nx >= high || ny >= len) {
					continue;
				}
				t = Math.max(c, Math.abs(heights[nx][ny] - heights[x][y]));
				if (t < mem[nx][ny]) {
					mem[nx][ny] = t;
					list.add(nx * 100 + ny);
				}
			}
		}
		return mem[high - 1][len - 1];
	}
	
	public int minKBitFlips(int[] A, int K) {
		int len = A.length;
		int[] mem = new int[len];
		int ret = 0, t = 0;
		for (int i = 0; i < len; i++) {
			t += mem[i];
			if (((A[i] + t) & 1) == 0) {
				t++;
				ret++;
				if (i + K - 1 >= len) {
					return -1;
				}
			}
		}
		return ret;
	}
	
	public boolean isPalindrome(ListNode head) {
		ListNode left = new ListNode(0);
		ListNode s = head, f = s, t;
		while (f != null) {
			f = f.next;
			t = s;
			s = s.next;
			if (f == null) {
				break;
			} else {
				f = f.next;
			}
			t.next = left.next;
			left.next = t;
		}
		left = left.next;
		while (s != null) {
			if (left.val == s.val) {
				return false;
			}
			left = left.next;
			s = s.next;
		}
		return true;
	}
	
	//828. 统计子串中的唯一字符
	public int uniqueLetterString(String s) {
		int[] x = new int[26];
		int[] y = new int[26];
		Arrays.fill(x, -1);
		Arrays.fill(y, -1);
		long ret = 0, mod = 1000000007;
		int len = s.length(), b = 0, c;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i) - 'A';
			b = b + (i - x[c]) - (x[c] - y[c]);
			ret += b;
			y[c] = x[c];
			x[c] = i;
			ret %= mod;
		}
		return (int) ret;
	}
	
	//1001. 网格照明
	public int[] gridIllumination(int N, int[][] lamps, int[][] queries) {
		HashMap<Integer, Integer> a = new HashMap<>();
		HashMap<Integer, Integer> b = new HashMap<>();
		HashMap<Integer, Integer> c = new HashMap<>();
		HashMap<Integer, Integer> d = new HashMap<>();
		HashSet<Long> set = new HashSet<>();
		int x, y;
		for (int[] lamp : lamps) {
			x = lamp[0];
			y = lamp[1];
			a.put(x, a.getOrDefault(x, 0) + 1);
			b.put(y, b.getOrDefault(y, 0) + 1);
			c.put(x - y, c.getOrDefault(x - y, 0) + 1);
			d.put(x + y, d.getOrDefault(x + y, 0) + 1);
			set.add((((long) x) << 32) + y);
		}
		int len = queries.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			x = queries[i][0];
			y = queries[i][1];
			if (a.getOrDefault(x, 0) > 0
				|| b.getOrDefault(y, 0) > 0
				|| c.getOrDefault(x - y, 0) > 0
				|| d.getOrDefault(x + y, 0) > 0) {
				ret[i] = 1;
			}
			for (int j = x - 1; j < x + 2 && j < N; j++) {
				if (j < 0) {
					continue;
				}
				for (int k = y - 1; k < y + 2 && k < N; k++) {
					if (k < 0) {
						continue;
					}
					if (set.remove((((long) j) << 32) + k)) {
						a.put(j, a.get(j) - 1);
						b.put(k, b.get(k) - 1);
						c.put(j - k, c.get(j - k) - 1);
						d.put(j + k, d.get(j + k) - 1);
					}
				}
			}
		}
		return ret;
	}
	
	//763. 划分字母区间
	public List<Integer> partitionLabels1(String S) {
		int[] right = new int[26];
		Arrays.fill(right, -1);
		int len = S.length();
		int l = 0, ret = 0, c, t, r = len - 1;
		ArrayList<Integer> list = new ArrayList<>();
		while (l < len) {
			for (int i = l; i <= ret; i++) {
				c = S.charAt(i) - 'a';
				while (right[c] < 0) {
					t = S.charAt(r) - 'a';
					right[t] = Math.max(r, right[t]);
					r--;
				}
				ret = Math.max(ret, right[c]);
			}
			list.add(ret - l + 1);
			ret++;
			l = ret;
		}
		return list;
	}
	
	//763. 划分字母区间
	public List<Integer> partitionLabels(String S) {
		int[] left = new int[26];
		Arrays.fill(left, Integer.MAX_VALUE);
		int[] right = new int[26];
		int len = S.length(), c;
		for (int i = 0; i < len; i++) {
			c = S.charAt(i) - 'a';
			left[c] = Math.min(left[c], i);
			right[c] = Math.max(right[c], i);
		}
		ArrayList<Integer> ret = new ArrayList<>();
		int l = -1, r = 0, nr = 0;
		while (r < len) {
			while (true) {
				for (int i = 0; i < 26; i++) {
					if (left[i] <= r) {
						nr = Math.max(nr, right[i]);
					}
				}
				if (nr == r) {
					break;
				}
				r = nr;
			}
			ret.add(r - l);
			l = r;
			r++;
		}
		return ret;
	}
	
	public int[] bestCoordinate(int[][] towers, int radius) {
		int[] ret = new int[2];
		int max = 0, cur;
		double[][] visited = new double[51][51];
		for (int i = 0; i < 51; i++) {
			for (int j = 0; j < 51; j++) {
				visited[i][j] = Math.sqrt(i * i + j * j);
			}
		}
		int x, y;
		for (int i = 0; i <= 50; i++) {
			for (int j = 0; j <= 50; j++) {
				cur = 0;
				for (int[] tower : towers) {
					x = Math.abs(i - tower[0]);
					y = Math.abs(j - tower[1]);
					if (visited[x][y] <= radius) {
						cur += tower[2] / (1 + visited[x][y]);
					}
				}
				System.out.println(i + " " + j + " " + cur);
				if (cur > max) {
					max = cur;
					ret[0] = i;
					ret[1] = j;
				}
			}
		}
		return ret;
	}
	
	
	public List<Boolean> areConnected(int n, int threshold, int[][] queries) {
		int[] mem = new int[n + 1];
		int x, y;
		for (int i = 1; i <= n; i++) {
			mem[i] = i;
		}
		for (int i = threshold + 1; i <= n; i++) {
			for (int j = i * 2; j <= n; j += i) {
				x = areConnectedHelper(i, mem);
				y = areConnectedHelper(j, mem);
				mem[x] = mem[y];
			}
		}
		int len = queries.length;
		Boolean[] ret = new Boolean[len];
		for (int i = 0; i < len; i++) {
			ret[i] =
				areConnectedHelper(queries[i][0], mem) == areConnectedHelper(queries[i][1], mem);
		}
		return Arrays.asList(ret);
	}
	
	private int areConnectedHelper(int i, int[] mem) {
		if (i != mem[i]) {
			mem[i] = areConnectedHelper(mem[i], mem);
		}
		return mem[i];
	}
	
	
	public int gcd(int x, int y) {
		return x == 0 ? y : gcd(y % x, x);
	}
	
	//143. 重排链表
	public void reorderList(ListNode head) {
		ListNode s = head, f = s.next;
		while (f != null) {
			f = f.next;
			if (f == null) {
				break;
			}
			s = s.next;
			f = f.next;
		}
		f = s.next;
		s.next = null;
		ListNode h = new ListNode(0), t;
		while (f != null) {
			t = f;
			f = f.next;
			t.next = h.next;
			h.next = t;
		}
		s = head;
		h = h.next;
		while (h != null) {
			t = h;
			h = h.next;
			t.next = s.next;
			s.next = t;
			s = t.next;
		}
	}
	
	//1626. 无矛盾的最佳球队
	public int bestTeamScore(int[] scores, int[] ages) {
		int len = scores.length;
		int[][] mem = new int[len][2];
		for (int i = 0; i < len; i++) {
			mem[i][0] = ages[i];
			mem[i][1] = scores[i];
		}
		Arrays.sort(mem, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
		TreeMap<Integer, Integer> map = new TreeMap<>();
		map.put(0, 0);
		int c, t, ret = 0;
		for (int i = 0; i < len; i++) {
			c = mem[i][1];
			t = 0;
			for (Entry<Integer, Integer> entry : map.entrySet()) {
				if (entry.getKey() > c) {
					break;
				}
				t = Math.max(t, c + entry.getValue());
			}
			ret = Math.max(ret, t);
			map.put(c, t);
		}
		return ret;
	}
	
	//1625. 执行操作后字典序最小的字符串
	public String findLexSmallestString(String s, int a, int b) {
		int len = s.length();
		boolean[] used = new boolean[len];
		int idx = 0, x, y;
		char[] ret = s.toCharArray();
		int[][] mem = new int[10][10];
		int[] min = new int[10];
		for (int i = 0; i < 10; i++) {
			x = i;
			for (int j = 1; j < 10; j++) {
				mem[i][j] = (i + a * j) % 10;
				if (x > mem[i][j]) {
					min[i] = j;
					x = mem[i][j];
				}
			}
		}
		while (!used[idx]) {
			used[idx] = true;
			if ((b & 1) == 0 && ((idx + b) & 1) == 0) {
				x = 0;
			} else {
				x = min[s.charAt((idx) % len) - '0'];
			}
			if ((b & 1) == 0 && ((idx + 1 + b) & 1) == 0) {
				y = 0;
			} else {
				y = min[s.charAt((1 + idx) % len) - '0'];
			}
			findLexSmallestStringCompare(ret, s, idx, x, y, a);
			idx += b;
			idx %= len;
		}
		return new String(ret);
	}
	
	private void findLexSmallestStringCompare(char[] ret, String s, int idx, int x, int y, int a) {
		int len = s.length(), t;
		for (int i = 0; i < len; i += 2, idx += 2) {
			t = (s.charAt(idx % len) - '0' + x * a) % 10;
			if (ret[i] - '0' < t) {
				return;
			} else if (ret[i] - '0' > t) {
				findLexSmallestStringHelper(ret, s, i, idx, x, y, a);
				return;
			} else {
				t = (s.charAt((idx + 1) % len) - '0' + y * a) % 10;
				if (ret[i + 1] - '0' < t) {
					return;
				} else if (ret[i] - '0' > t) {
					findLexSmallestStringHelper(ret, s, i, idx, x, y, a);
					return;
				}
			}
		}
	}
	
	private void findLexSmallestStringHelper(char[] ret, String s, int i, int idx, int x, int y,
		int a) {
		int len = ret.length;
		for (; i < len; i += 2) {
			ret[i] = (char) ('0' + ((s.charAt(idx % len) - '0' + a * x) % 10));
			idx++;
			ret[i + 1] = (char) ('0' + ((s.charAt(idx % len) - '0' + a * y) % 10));
			idx++;
		}
	}
	
	
	//1624. 两个相同字符之间的最长子字符串
	public int maxLengthBetweenEqualCharacters(String s) {
		int[] mem = new int[26];
		Arrays.fill(mem, -1);
		int len = s.length();
		int c, ret = 0;
		for (int i = 0; i < len; i++) {
			c = s.charAt(i) - 'a';
			if (mem[c] < 0) {
				mem[c] = i;
			} else {
				ret = Math.max(ret, i - mem[c] - 1);
			}
		}
		return ret;
	}
	
	public double trimMean(int[] arr) {
		Arrays.sort(arr);
		int len = arr.length;
		double sum = 0;
		for (int i = len * 5 / 100; i < len * 95 / 100; i++) {
			sum += arr[i];
		}
		return sum * 10 / (len * 9);
	}
	
	//面试题 17.05.  字母与数字
	public String[] findLongestSubarray(String[] array) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int max = 0, start = 0;
		int x = 0, t;
		int len = array.length;
		map.put(0, -1);
		for (int i = 0; i < len; i++) {
			t = array[i].charAt(0);
			if (t >= '0' && t <= '9') {
				x++;
			} else {
				x--;
			}
			if (map.containsKey(x)) {
				if (i - map.get(x) > max) {
					max = i - map.get(x);
					start = map.get(x);
				}
			} else {
				map.put(x, i);
			}
		}
		return Arrays.copyOfRange(array, start, start + max);
	}
	
	//剑指 Offer 12. 矩阵中的路径
	public boolean exist(char[][] board, String word) {
		int high = board.length;
		int len = board[0].length;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (existHelper(board, i, j, word, 0)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean existHelper(char[][] board, int i, int j, String word, int idx) {
		if (idx == word.length()) {
			return true;
		} else if (i < 0 || j < 0 || i >= board.length || j >= board[0].length) {
			return false;
		}
		if (board[i][j] == word.charAt(idx)) {
			char c = word.charAt(idx);
			board[i][j] = ' ';
			for (int[] d : ds_4) {
				if (existHelper(board, i + d[0], j + d[1], word, idx + 1)) {
					return true;
				}
			}
			board[i][j] = c;
		}
		return false;
	}
	
	//5527. 大小为 K 的不重叠线段的数目
	public int numberOfSets(int n, int k) {
		long[][] mem = new long[k + 1][n];
		Arrays.fill(mem[0], 1);
		long mod = 1000000007, t;
		for (int i = 1; i <= k; i++) {
			t = 1;
			for (int j = i; j < n; j++) {
				mem[i][j] = mem[i][j - 1] + t;
				mem[i][j] %= mod;
				t += mem[i - 1][j];
				t %= mod;
			}
		}
		return (int) mem[k][n - 1];
	}
	
	public int dayOfYear(String date) {
		int[] mem = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		String[] split = date.split("-" );
		int ret = 0;
		Integer month = Integer.valueOf(split[1]);
		for (int i = 1; i < month; i++) {
			ret += mem[i];
		}
		ret += Integer.valueOf(split[2]);
		if (month > 2) {
			Integer year = Integer.valueOf(split[0]);
			if (year % 4 == 0 && year % 100 != 0) {
				ret--;
			}
		}
		return ret;
	}
	
	public int[] sumEvenAfterQueries(int[] A, int[][] queries) {
		int val = 0;
		for (int i : A) {
			if ((i & 1) == 0) {
				val += i;
			}
		}
		int len = queries.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			if ((A[queries[i][1]] & 1) == 0) {
				val -= A[queries[i][1]];
			}
			A[queries[i][1]] += queries[i][0];
			if ((A[queries[i][1]] & 1) == 0) {
				val += A[queries[i][1]];
			}
			ret[i] = val;
		}
		return ret;
	}
	
	//剑指 Offer 57. 和为s的两个数字
	public int[] twoSum(int[] nums, int target) {
		int l = 0, r = nums.length - 1, t;
		while (l < r) {
			t = nums[l] + nums[r];
			if (t > target) {
				r--;
			} else if (t < target) {
				l++;
			} else {
				return new int[]{nums[l], nums[r]};
			}
		}
		return null;
	}
	
	//
	public int[] sortedSquares(int[] A) {
		int len = A.length;
		int l = len - 1, r = len;
		for (int i = 0; i < len; i++) {
			if (A[i] >= 0) {
				r = i;
				l = i - 1;
				break;
			}
		}
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			if (l >= 0 && r < len) {
				if (0 - A[l] < A[r]) {
					ret[i] = A[l] * A[l];
					l--;
				} else {
					ret[i] = A[r] * A[r];
					r++;
				}
			} else if (l >= 0) {
				ret[i] = A[l] * A[l];
				l--;
			} else {
				ret[i] = A[r] * A[r];
				r++;
			}
		}
		return ret;
	}
	
	//1499. 满足不等式的最大值
	public int findMaxValueOfEquation(int[][] points, int k) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
		queue.add(new int[]{points[0][1] - points[0][0], points[0][0]});
		int len = points.length, ret = Integer.MIN_VALUE;
		for (int i = 1; i < len; i++) {
			while (!queue.isEmpty() && points[i][0] - queue.peek()[1] > k) {
				queue.poll();
			}
			if (!queue.isEmpty()) {
				ret = Math.max(queue.peek()[0] + points[i][1] + points[i][0], ret);
			}
			queue.add(new int[]{points[i][1] - points[i][0], points[i][0]});
		}
		return ret;
	}
	
	class Node {
		
		public int val;
		public Node left;
		public Node right;
		public Node next;
		
		public Node() {
		}
		
		public Node(int _val) {
			val = _val;
		}
		
		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
	
	;
	
	//116. 填充每个节点的下一个右侧节点指针
	public Node connect(Node root) {
		if (root == null) {
			return root;
		}
		Node ret = root, cur;
		while (root.left != null) {
			cur = root;
			while (cur != null) {
				cur.left.next = cur.right;
				if (cur.next != null) {
					cur.right.next = cur.next.left;
				}
				cur = cur.next;
			}
			root = root.left;
		}
		return ret;
	}
	
	//1514. 概率最大的路径
	public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
		List<double[]>[] lists = new List[n];
		for (int i = 0; i < n; i++) {
			lists[i] = new ArrayList<>();
		}
		double[] mem = new double[n];
		int len = edges.length, x, y;
		for (int i = 0; i < len; i++) {
			x = edges[i][0];
			y = edges[i][1];
			lists[x].add(new double[]{y, succProb[i]});
			lists[y].add(new double[]{x, succProb[i]});
		}
		HashSet<Integer> cur = new HashSet<>(), next;
		cur.add(start);
		mem[start] = 1;
		while (!cur.isEmpty()) {
			next = new HashSet<>();
			for (int l : cur) {
				for (double[] r : lists[l]) {
					if (mem[(int) r[0]] < mem[l] * r[1]) {
						mem[(int) r[0]] = mem[l] * r[1];
						next.add((int) r[0]);
					}
				}
			}
			cur = next;
		}
		return mem[end];
	}
	
	//1521. 找到最接近目标值的函数值
	public int closestToTarget(int[] arr, int target) {
		int[] mem = new int[32];
		int ret = Integer.MAX_VALUE, cur = -1, cnt = 0;
		int len = arr.length, l = 0;
		for (int i = 0; i < len; i++) {
			if (arr[i] < target) {
				ret = Math.min(ret, target - arr[i]);
				Arrays.fill(mem, 0);
				cur = -1;
			} else {
				if (cur < 0) {
					cur = arr[i];
					closestToTargetHelper(arr[i], mem);
					cnt = 1;
					l = i;
					ret = Math.min(ret, -target + arr[i]);
				} else {
					cur &= arr[i];
					closestToTargetHelper(arr[i], mem);
					ret = Math.min(ret, cur - target);
					cnt++;
				}
				while (cur < target) {
					cur = closestToTargetHelper1(arr[l], mem, cnt - 1);
					ret = Math.min(ret, target = cur);
					l++;
					cnt--;
				}
				if (cur == target) {
					return 0;
				}
			}
		}
		return ret;
	}
	
	private int closestToTargetHelper1(int num, int[] mem, int cnt) {
		int ret = 0, t = 1;
		for (int i = 0; i < 32; i++) {
			mem[i] -= (num & 1);
			if (mem[i] == cnt - 1) {
				ret += t;
			}
			num >>= 1;
			t <<= 1;
		}
		return ret;
	}
	
	private void closestToTargetHelper(int num, int[] mem) {
		int i = 0;
		while (num > 0) {
			mem[i] += num & 1;
			num >>= 1;
			i++;
		}
	}
	
	//剑指 Offer 21. 调整数组顺序使奇数位于偶数前面
	public int[] exchange(int[] nums) {
		int l = 0, r = nums.length - 1, t;
		while (l < r) {
			while (l < r && (nums[l] & 1) == 1) {
				l++;
			}
			while (l < r && (nums[r] & 1) == 0) {
				r--;
			}
			if (l < r) {
				t = nums[l];
				nums[l] = nums[r];
				nums[r] = t;
				l++;
				r--;
			}
		}
		return nums;
	}
	
	//1002. 查找常用字符
	public List<String> commonChars(String[] A) {
		int[] count = new int[26];
		int[] cur = new int[26];
		Arrays.fill(count, Integer.MAX_VALUE);
		for (String s : A) {
			Arrays.fill(cur, Integer.MAX_VALUE);
			for (int i = 0; i < s.length(); i++) {
				cur[s.charAt(i) - 'a']++;
			}
			for (int i = 0; i < 26; i++) {
				count[i] = Math.min(count[i], cur[i]);
			}
		}
		ArrayList<String> ret = new ArrayList<>();
		for (int i = 0; i < 26; i++) {
			while (count[i] > 0) {
				ret.add((char) (i + 'a') + "" );
			}
		}
		return ret;
	}
	
	//1606. 找到处理最多请求的服务器
	public List<Integer> busiestServers(int k, int[] arrival, int[] load) {
		int[] mem = new int[k];
		int[] count = new int[k];
		int len = arrival.length, i = 0, cnt = 1, t;
		for (i = 0; i < k && i < len; i++) {
			mem[i] = arrival[i] + load[i];
			count[i]++;
		}
		for (; i < len; i++) {
			t = i % k;
			if (arrival[i] < mem[t]) {
				int x = t;
				t = (t + 1) % k;
				while (x != t && arrival[i] < mem[t]) {
					t = (t + 1) % k;
				}
				if (x == t) {
					continue;
				}
			}
			mem[t] = arrival[i] + load[i];
			count[t]++;
			if (cnt < count[t]) {
				cnt = count[t];
			}
		}
		ArrayList<Integer> ret = new ArrayList<>();
		for (int j = 0; j < k; j++) {
			if (cnt == count[j]) {
				ret.add(j);
			}
		}
		return ret;
	}
	
	public List<Integer> busiestServers1(int k, int[] arrival, int[] load) {
		int[] mem = new int[k];
		int[] count = new int[k];
		TreeSet<Integer> set = new TreeSet<>();
		PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> (mem[a] - mem[b]));
		int len = arrival.length, i;
		Integer t, cnt = 1;
		for (i = 0; i < len && i < k; i++) {
			mem[i] = arrival[i] + load[i];
			queue.add(i);
			count[i]++;
		}
		for (; i < len; i++) {
			t = arrival[i];
			while (!queue.isEmpty() && mem[queue.peek()] <= t) {
				set.add(queue.poll());
			}
			t = set.ceiling(i % k);
			if (t == null) {
				t = set.ceiling(0);
			}
			if (t != null) {
				mem[t] = arrival[i] + load[i];
				count[t]++;
				if (count[t] > cnt) {
					cnt++;
				}
				set.remove(t);
				queue.add(t);
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int j = 0; j < k; j++) {
			if (cnt == count[j]) {
				list.add(j);
			}
		}
		return list;
	}
	
	//24. 两两交换链表中的节点
	public ListNode swapPairs(ListNode head) {
		ListNode ret = new ListNode(0);
		ret.next = head;
		ListNode a = head, b, c, tail = ret;
		while (a != null) {
			b = a.next;
			if (b == null) {
				break;
			}
			c = b.next;
			tail.next = b;
			b.next = a;
			a.next = c;
			tail = a;
			a = c;
		}
		return ret.next;
	}
	
	public int[][] restoreMatrix(int[] rowSum, int[] colSum) {
		int high = rowSum.length;
		int len = colSum.length, x;
		int[][] ret = new int[high][len];
		for (int i = 0; i < high && rowSum[i] > 0; i++) {
			for (int j = 0; j < len && colSum[j] > 0; j++) {
				x = Math.min(rowSum[i], colSum[j]);
				ret[i][j] = x;
				rowSum[i] -= x;
				colSum[j] -= x;
			}
		}
		return ret;
	}
	
	public int maximalNetworkRank(int n, int[][] roads) {
		int[] mem = new int[n];
		boolean[][] cnt = new boolean[n][n];
		for (int[] road : roads) {
			mem[road[0]]++;
			mem[road[1]]++;
			cnt[road[0]][road[1]] = true;
			cnt[road[1]][road[0]] = true;
		}
		int f = mem[0], s = 0;
		ArrayList<Integer> fset = new ArrayList<>(), sset = new ArrayList<>();
		fset.add(0);
		for (int i = 1; i < n; i++) {
			if (mem[i] > f) {
				sset = fset;
				fset = new ArrayList<>();
				fset.add(i);
				s = f;
				f = mem[i];
			} else if (mem[i] == f) {
				fset.add(i);
			} else if (mem[i] > s) {
				s = mem[i];
				sset = new ArrayList<>();
				sset.add(i);
			} else if (mem[i] == s) {
				sset.add(i);
			}
		}
		int len = fset.size();
		if (len > 1) {
			for (int i = 0; i < len; i++) {
				Integer x = fset.get(i);
				for (int j = i + 1; j < len; j++) {
					if (!cnt[x][fset.get(j)]) {
						return f * 2;
					}
				}
			}
			return f * 2 - 1;
		} else {
			Integer x = fset.get(0);
			for (Integer y : sset) {
				if (!cnt[x][y]) {
					return f + s;
				}
			}
			return f + s - 1;
		}
	}
	
	//5535. 括号的最大嵌套深度
	public int maxDepth(String s) {
		int cur = 0, ret = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if ('(' == s.charAt(i)) {
				cur++;
				if (ret < cur) {
					ret = cur;
				}
			} else if (')' == s.charAt(i)) {
				cur--;
			}
		}
		return ret;
	}
	
	int min = Integer.MAX_VALUE;
	Integer left = null;
	
	public int getMinimumDifference(TreeNode root) {
		getMinimumDifferenceHelper(root);
		return min;
	}
	
	private void getMinimumDifferenceHelper(TreeNode root) {
		
		if (root == null) {
			return;
		}
		getMinimumDifferenceHelper(root.left);
		if (left != null) {
			min = Math.min(min, root.val - left);
		}
		left = root.val;
		getMinimumDifferenceHelper(root.right);
	}
	
	//1616. 分割两个字符串得到回文串
	public boolean checkPalindromeFormation(String a, String b) {
		int len = a.length();
		if (len < 2) {
			return true;
		}
		boolean[] mem = new boolean[len / 2];
		int l, r;
		l = (len - 2) / 2;
		r = (len + 1) / 2;
		boolean ax = true, bx = true;
		while (l >= 0 && (ax || bx)) {
			ax = ax && a.charAt(l) == a.charAt(r);
			bx = bx && b.charAt(l) == b.charAt(r);
			mem[l] = (ax || bx);
			l--;
			r++;
		}
		ax = true;
		bx = true;
		for (int i = 0; i < len / 2; i++) {
			if (mem[i]) {
				return true;
			}
			ax = ax && a.charAt(i) == b.charAt(len - i - 1);
			bx = bx && b.charAt(i) == a.charAt(len - i - 1);
			if (!(ax || bx)) {
				return false;
			}
		}
		return true;
	}
	
	//剑指 Offer 48. 最长不含重复字符的子字符串
	public int lengthOfLongestSubstring(String s) {
		int l = 0, r = 0;
		boolean[] used = new boolean[26];
		int len = s.length(), ret = 0;
		char c;
		while (r < len) {
			while (r < len && !used[s.charAt(r) - 'a']) {
				used[s.charAt(r) - 'a'] = true;
				r++;
			}
			if (r == len) {
				break;
			}
			c = s.charAt(r);
			while (c != s.charAt(l)) {
				used[s.charAt(l) - 'a'] = false;
				l++;
			}
			ret = Math.max(ret, r - l);
			r++;
			l++;
		}
		return ret;
	}
	
	//剑指 Offer 44. 数字序列中某一位的数字
	public int findNthDigit(int n) {
		if (n < 10) {
			return n;
		}
		int b = 1, c = 10, x = 1, cnt = 10, t;
		while (true) {
			x++;
			t = 10 * (x) * (c - b);
			if (t + cnt > n) {
				break;
			}
			b *= 10;
			c *= 10;
			cnt += t;
		}
		n -= cnt;
		c += n / x;
		String s = c + "";
		return s.charAt(n % x) - '0';
		
	}
	
	//剑指 Offer 38. 字符串的排列
	public String[] permutation(String s) {
		int[] mem = new int[26];
		int len = s.length();
		for (int i = 0; i < len; i++) {
			mem[s.charAt(i) - 'a']++;
		}
		ArrayList<String> list = new ArrayList<>();
		permutationHelper(new char[len], len - 1, list, mem);
		return list.toArray(new String[]{});
	}
	
	private void permutationHelper(char[] arr, int idx, ArrayList<String> list, int[] mem) {
		if (idx < 0) {
			list.add(new String(arr));
			return;
		}
		for (int i = 0; i < 26; i++) {
			if (mem[i] > 0) {
				mem[i]--;
				arr[idx] = (char) (i + 'a');
				permutationHelper(arr, idx - 1, list, mem);
				mem[i]++;
			}
		}
	}
	
	//1610. 可见点的最大数目
	public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
		int len = points.size();
		double[] mem = new double[len];
		int a = location.get(0), b = location.get(1), x, y, idx = 0, l = 0;
		for (List<Integer> point : points) {
			y = point.get(0) - a;
			x = point.get(1) - b;
			if (x == 0 && y == 0) {
				l++;
				continue;
			}
			mem[idx] = Math.atan2(x, y) * 180 / Math.PI;
			if (mem[idx] < 0) {
				mem[idx] += 360;
			}
			idx++;
		}
		Arrays.sort(mem);
		LinkedList<Double> list = new LinkedList<>();
		idx = 0;
		int ret = 0;
		for (int i = l; i < len; i++) {
			while (!list.isEmpty() && mem[i] - list.getFirst() > angle) {
				list.removeFirst();
			}
			list.add(mem[i]);
			ret = Math.max(ret, list.size());
		}
		for (int i = l; i < len; i++) {
			while (!list.isEmpty() && 360 + mem[i] - list.getFirst() > angle) {
				if (list.removeFirst() >= 360) {
					break;
				}
			}
			list.add(360 + mem[i]);
			ret = Math.max(ret, list.size());
		}
		return ret + l;
	}
	
	//1611. 使整数变为 0 的最少操作次数
	public int minimumOneBitOperations(int n) {
		HashSet<Integer> visited = new HashSet<>(), cur = new HashSet<>(), next;
		cur.add(n);
		int ret = 0, x, a;
		while (true) {
			if (cur.contains(0)) {
				return ret;
			}
			next = new HashSet<>();
			visited.addAll(cur);
			for (Integer t : cur) {
				x = t ^ 1;
				if (!visited.contains(x)) {
					next.add(x);
				}
				a = 1;
				while (t >= a) {
					if ((t & a) == a && (t | a) == t) {
						if (!visited.contains(t ^ (a << 1))) {
							next.add(t ^ (a << 1));
						}
						break;
					}
					a <<= 1;
				}
			}
			cur = next;
			ret++;
		}
	}
	
	public int minimumOneBitOperations1(int n) {
		int ret = 0, a = n, b = n, na, nb, t;
		while (true) {
			if (a == 0 || b == 0) {
				return ret;
			}
			na = b ^ 1;
			t = 1;
			while ((a & t) == 0) {
				t <<= 1;
			}
			nb = a ^ (t << 1);
			a = na;
			b = nb;
			ret++;
		}
	}
	
	//1608. 特殊数组的特征值
	public int specialArray(int[] nums) {
		Arrays.sort(nums);
		int len = nums.length;
		if (len <= nums[0]) {
			return len;
		}
		int i;
		for (i = len - 1; i > 0; i--) {
			if (i > nums[len - i - 1] && i <= nums[len - i]) {
				return i;
			}
		}
		return -1;
	}
	
	public List<String> alertNames(String[] keyName, String[] keyTime) {
		TreeMap<String, TreeSet<String>> map = new TreeMap<>();
		int len = keyName.length;
		TreeSet<String> set;
		String name;
		for (int i = 0; i < len; i++) {
			name = keyName[i];
			if (!map.containsKey(name)) {
				set = new TreeSet<>();
				map.put(name, set);
			} else {
				set = map.get(name);
			}
			set.add(keyTime[i]);
		}
		LinkedList<String> list = new LinkedList<>();
		LinkedList<String> ret = new LinkedList<>();
		for (Entry<String, TreeSet<String>> entry : map.entrySet()) {
			name = entry.getKey();
			set = entry.getValue();
			if (set.size() < 3) {
				continue;
			}
			list.clear();
			for (String s : set) {
				while (!list.isEmpty() && alertNamesHelper(list.getFirst(), s)) {
					list.removeFirst();
				}
				list.add(s);
				if (list.size() > 2) {
					ret.add(name);
					break;
				}
			}
		}
		return ret;
	}
	
	private boolean alertNamesHelper(String first, String s) {
		int a, b, c, d;
		a = s.charAt(0) - first.charAt(0);
		if (a > 1) {
			return true;
		}
		b = a * 10 + s.charAt(1) - first.charAt(1);
		if (b > 1) {
			return true;
		}
		c = s.charAt(3) - first.charAt(3);
		d = s.charAt(4) - first.charAt(4);
		if (b * 60 + (c * 10 + d) > 60) {
			return true;
		}
		return false;
	}
	
	//1609. 奇偶树
	public boolean isEvenOddTree(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		return isEvenOddTreeHelper(root, list, 0);
	}
	
	private boolean isEvenOddTreeHelper(TreeNode root, ArrayList<Integer> list, int level) {
		if (root == null) {
			return true;
		}
		if (list.size() == level) {
			list.add(root.val);
		} else {
			if ((level & 1) == 1) {
				if ((root.val & 1) == 1 || list.get(level) <= root.val) {
					return false;
				} else {
					list.set(level, root.val);
				}
			} else {
				if ((root.val & 1) == 0 || list.get(level) >= root.val) {
					return false;
				} else {
					list.set(level, root.val);
				}
			}
		}
		return isEvenOddTreeHelper(root.left, list, level + 1) && isEvenOddTreeHelper(root.right,
			list, level + 1);
	}
	
	//LCP 19. 秋叶收藏集--超时
	public int minimumOperations(String leaves) {
		int len = leaves.length();
		int[] mem = new int[len + 1];
		for (int i = 0; i < len; i++) {
			mem[i + 1] = mem[i] + (leaves.charAt(i) == 'r' ? 1 : 0);
		}
		int ret = len, i;
		int l, m, r;
		for (i = 1; i < len - 1; i++) {
			if (i - mem[i] >= ret) {
				break;
			}
			l = i - mem[i];
			for (int j = i + 1; j < len; j++) {
				m = mem[j] - mem[i];
				if (l + m >= ret) {
					break;
				}
				r = len - j - (mem[len] - mem[j]);
				ret = Math.min(ret, l + m + r);
			}
		}
		return ret;
	}
	
	//LCP 19. 秋叶收藏集
	public int minimumOperations2(String leaves) {
		int len = leaves.length();
		int[] mem = new int[len + 1];
		for (int i = 0; i < len; i++) {
			mem[i + 1] = mem[i] + (leaves.charAt(i) == 'y' ? 1 : 0);
		}
		int ret = len, i;
		int l, m, r;
		l = leaves.charAt(0) == 'y' ? 1 : 0;
		for (i = 1; i < len - 1; i++) {
			if (leaves.charAt(i) == 'y') {
				ret = Math.min(ret, l + mem[len] - mem[i + 1]);
			} else {
				ret = Math.min(ret, l + mem[len] - mem[i + 1] + 1);
				l = Math.min(l + 1, mem[i]);
			}
		}
		return ret;
	}
	
	public int minimumOperations3(String leaves) {
		int len = leaves.length();
		int sum = 0;
		for (int i = 0; i < len; i++) {
			if (leaves.charAt(i) == 'y') {
				sum++;
			}
		}
		int l = leaves.charAt(0) == 'y' ? 1 : 0;
		int lsum = l, ret = len;
		for (int i = 1; i < len - 1; i++) {
			if (leaves.charAt(i) == 'y') {
				lsum++;
				ret = Math.min(ret, l + sum - lsum);
			} else {
				ret = Math.min(ret, l + sum - lsum + 1);
				l = Math.min(l + 1, lsum);
			}
		}
		return ret;
	}
	
	int minimumOperations = Integer.MAX_VALUE;
	
	//LCP 19. 秋叶收藏集--超时
	public int minimumOperations1(String leaves) {
		minimumOperationsHelper(leaves, 0, 'r', 0, 0);
		return minimumOperations;
	}
	
	private void minimumOperationsHelper(String leaves, int idx, char r, int count, int part) {
		int len = leaves.length();
		char c;
		for (int i = idx; i < len; i++) {
			c = leaves.charAt(i);
			if (c != r) {
				if (part < 2) {
					minimumOperationsHelper(leaves, i, c, count, part + 1);
				}
				count++;
				if (count > minimumOperations) {
					return;
				}
			}
		}
		if (part == 2) {
			minimumOperations = Math.min(minimumOperations, count);
		}
	}
	
	//1498. 满足条件的子序列数目
	public int numSubseq(int[] nums, int target) {
		Arrays.sort(nums);
		int len = nums.length;
		int l = 0, r = len - 1;
		int mod = 1000000007;
		long ret = 0;
		HashMap<Integer, Long> map = new HashMap<>();
		map.put(0, 1L);
		while (l <= r) {
			while (r >= l && nums[r] + nums[l] > target) {
				r--;
			}
			if (l <= r) {
				ret += numSubseqHelper(r - l, map, mod);
				ret %= mod;
			}
			l++;
		}
		return (int) ret;
	}
	
	private long numSubseqHelper(int i, HashMap<Integer, Long> map, int mod) {
		if (map.containsKey(i)) {
			return map.get(i);
		}
		long ret;
		if ((i & 1) == 1) {
			long l = numSubseqHelper(i / 2, map, mod);
			ret = 2 * l * l % mod;
			map.put(i, ret);
		} else {
			long l = numSubseqHelper(i / 2, map, mod);
			ret = l * l % mod;
			map.put(i, ret);
		}
		return ret;
	}
	
	//5526. 最多可达成的换楼请求数目
	int maximumRequests;
	
	public int maximumRequests(int n, int[][] requests) {
		int[] mem = new int[n];
		maximumRequestsHelper(mem, requests, 0, 0, 0);
		return maximumRequests;
	}
	
	private void maximumRequestsHelper(int[] mem, int[][] requests, int idx, int count, int x) {
		if (x == 0) {
			maximumRequests = Math.max(maximumRequests, count);
		}
		int rlen = requests.length;
		if (idx < rlen) {
			if (requests[idx][0] == requests[idx][1]) {
				maximumRequestsHelper(mem, requests, idx + 1, count + 1, x);
			} else {
				for (int i = idx; i < rlen; i++) {
					int t = 0;
					mem[requests[i][0]]++;
					if (mem[requests[i][0]] == 0) {
						t--;
					} else if (mem[requests[i][0]] == 1) {
						t++;
					}
					mem[requests[i][1]]--;
					if (mem[requests[i][1]] == 0) {
						t--;
					} else if (mem[requests[i][1]] == -1) {
						t++;
					}
					
					maximumRequestsHelper(mem, requests, i + 1, count + 1, x + t);
					mem[requests[i][0]]--;
					mem[requests[i][1]]++;
				}
			}
		}
	}
	
	public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
		int sum = customers[0];
		int len = customers.length;
		int ret = 0, cur = 0, t, x = 0, count = 0;
		for (int i = 1; i < len; i++) {
			if (sum >= 4) {
				t = 4;
				sum -= 4;
			} else {
				t = sum;
				sum = 0;
			}
			x += t;
			cur = x * boardingCost - i * runningCost;
			if (cur > ret) {
				ret = cur;
				count = i;
			}
			ret = Math.max(ret, cur);
			sum += customers[i];
		}
		int v;
		if (sum > 0) {
			t = sum % 4;
			v = (x + (sum - t)) * boardingCost - ((sum / 4) + len - 1) * runningCost;
			if (ret < v) {
				ret = v;
				count = (sum / 4) + len - 1;
			}
			v = (x + sum) * boardingCost - ((sum / 4) + len) * runningCost;
			if (ret < v) {
				ret = v;
				count = (sum / 4) + len;
			}
		}
		return ret > 0 ? count : -1;
	}
	
	public int minOperations(String[] logs) {
		Stack<String> stack = new Stack<>();
		for (String log : logs) {
			if (log.equals("../" )) {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			} else if (log.equals("./" )) {
			
			} else {
				stack.push(log);
			}
		}
		return stack.size();
	}
	
	public double soupServings(int N) {
		if (N >= 4800) {
			return 1;
		}
		N = N / 25 + ((N % 25 == 0) ? 0 : 1);
		double dp[][] = new double[N + 1][N + 1];
		Arrays.fill(dp[0], 1);
		dp[0][0] = 0.5;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] =
					0.25 * (dp[Math.max(0, i - 4)][j] + dp[Math.max(0, i - 3)][Math.max(0, j - 1)]
						+ dp[Math.max(0, i - 2)][Math.max(0, j - 2)] + dp[Math.max(0, i - 1)][Math
						.max(0, j - 3)]);
			}
		}
		return dp[N][N];
	}
	
	private double soupServingsHelper(int a, int b) {
		if (a == 0 && b == 0) {
			return 0.5;
		} else if (a == 0) {
			return 1;
		} else if (b == 0) {
			return 0;
		}
		return 0.25 * soupServingsHelper(a - 100, b) + 0.25 * soupServingsHelper(a - 75, b - 25) +
			0.25 * soupServingsHelper(a - 50, b - 50) + 0.25 * soupServingsHelper(a - 25, b - 75);
	}
	
	//899. 有序队列
	public String orderlyQueue(String S, int K) {
		char[] arr = S.toCharArray();
		if (K > 1) {
			Arrays.sort(arr);
			return new String(arr);
		}
		char min = 'a';
		while (S.indexOf(min) < 0) {
			min++;
		}
		ArrayList<Integer> list = new ArrayList<>(), next;
		int t = S.indexOf(min);
		while (t > 0) {
			list.add(t);
			t = S.indexOf(min, t + 1);
		}
		int count = 1;
		int len = arr.length;
		char c, x;
		while (list.size() > 1 && count < len) {
			next = new ArrayList<>();
			c = 'z';
			for (Integer i : list) {
				x = arr[(i + count) % len];
				if (x < c) {
					next.clear();
					next.add(i);
					c = x;
				} else if (x == c) {
					next.add(i);
				}
			}
			list = next;
		}
		count = list.get(0);
		return S.substring(count) + S.substring(0, count);
	}
	
	//106. 从中序与后序遍历序列构造二叉树
	int in, post;
	
	public TreeNode buildTree1(int[] inorder, int[] postorder) {
		in = inorder.length - 1;
		post = postorder.length - 1;
		return recursive(inorder, postorder, Integer.MAX_VALUE);
	}
	
	public TreeNode recursive(int[] inorder, int[] postorder, int stop) {
		if (post < 0) {
			return null;
		}
		if (inorder[in] == stop) {
			in--;
			return null;
		}
		int curVal = postorder[post--];
		TreeNode cur = new TreeNode(curVal);
		//注意顺序，倒着的后序遍历是先走右节点的先序遍历
		cur.right = recursive(inorder, postorder, curVal);
		//当先序遍历（倒）开始返回时，中序遍历（倒）开始记录节点，也就是该节点没有右节点
		cur.left = recursive(inorder, postorder, stop);
		//当中序遍历(倒)下一个节点为该节点父节点时，该节点没有左节点
		return cur;
	}
	
	//106. 从中序与后序遍历序列构造二叉树
	int postIdx;
	
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		int len = postorder.length;
		postIdx = len - 1;
		return buildTreeHelper(inorder, postorder, 0, postIdx);
	}
	
	private TreeNode buildTreeHelper(int[] inorder, int[] postorder, int l, int r) {
		if (l > r) {
			return null;
		} /*else if (l == r) {
			return new TreeNode(inorder[l]);
		}*/
		int val = postorder[postIdx];
		postIdx--;
		TreeNode ret = new TreeNode(val);
		int i;
		for (i = l; i <= r; i++) {
			if (inorder[i] == val) {
				break;
			}
		}
		ret.right = buildTreeHelper(inorder, postorder, i + 1, r);
		ret.left = buildTreeHelper(inorder, postorder, l, i - 1);
		return ret;
	}
	
	//864. 获取所有钥匙的最短路径
	int shortestPathAllKeys = Integer.MAX_VALUE;
	
	public int shortestPathAllKeys(String[] grid) {
		int high = grid.length;
		int len = grid[0].length();
		char[][] chars = new char[high][];
		int x = 0, y = 0, key = 0;
		for (int i = 0; i < high; i++) {
			chars[i] = grid[i].toCharArray();
			for (int j = 0; j < len; j++) {
				char c = chars[i][j];
				if (c == '@') {
					x = i;
					y = j;
				} else if (c >= 'a' && c <= 'z') {
					key <<= 1;
					key++;
				}
			}
		}
		int[][][] mem = new int[key + 1][high][len];
		for (int i = 0; i <= key; i++) {
			for (int j = 0; j < high; j++) {
				Arrays.fill(mem[i][j], Integer.MAX_VALUE);
			}
		}
		mem[key][x][y] = 0;
		shortestPathAllKeysHelper(chars, x, y, key, mem);
		return shortestPathAllKeys == Integer.MAX_VALUE ? -1 : shortestPathAllKeys;
		
	}
	
	private void shortestPathAllKeysHelper(char[][] chars, int x, int y, int key, int[][][] mem) {
		if (key == 0) {
			shortestPathAllKeys = Math.min(shortestPathAllKeys, mem[key][x][y]);
			return;
		}
		ArrayList<Integer> cur = new ArrayList<>(), next;
		cur.add(x * 100 + y);
		int cnt = mem[key][x][y];
		int ci, cj, i, j;
		int high = chars.length, len = chars[0].length;
		while (!cur.isEmpty()) {
			cnt++;
			next = new ArrayList<>();
			for (Integer t : cur) {
				ci = t / 100;
				cj = t % 100;
				for (int[] d : ds_4) {
					i = ci + d[0];
					j = cj + d[1];
					if (i < 0 || j < 0 || i >= high || j >= len || chars[i][j] == '#'
						|| mem[key][i][j] <= cnt) {
						continue;
					}
					
					if (chars[i][j] >= 'A' && chars[i][j] <= 'Z') {
						int k = 1 << (chars[i][j] - 'A');
						if ((k & key) > 0) {
							continue;
						}
					} else if (chars[i][j] >= 'a' && chars[i][j] <= 'z') {
						int k = 1 << (chars[i][j] - 'a');
						if (mem[key ^ k][i][j] > cnt) {
							mem[key ^ k][i][j] = cnt;
							shortestPathAllKeysHelper(chars, i, j, key ^ k, mem);
						}
					}
					next.add(i * 100 + j);
					mem[key][i][j] = cnt;
				}
			}
			cur = next;
		}
	}
	
	public void findSecretWord(String[] wordlist, Master master) {
		int[] mem = new int[6];
		int len = wordlist.length;
		HashSet<String>[] set = new HashSet[6];
		for (int i = 0; i < 6; i++) {
			set[i] = new HashSet();
		}
		int[][] cnt = new int[6][26];
		for (String s : wordlist) {
			for (int i = 0; i < 6; i++) {
				cnt[i][s.charAt(i) - 'a']++;
			}
		}
		char[] chars = new char[6];
		for (int i = 0; i < 6; i++) {
			int max = 0;
			for (int j = 1; j < 26; j++) {
				if (cnt[i][max] < cnt[i][j]) {
					max = j;
				}
			}
			chars[i] = (char) ('a' + max);
		}
		String t = new String(chars);
		int max = 0, a;
		String t1 = wordlist[0];
		for (String s : wordlist) {
			a = 0;
			for (int i = 0; i < 6; i++) {
				if (t.charAt(i) == s.charAt(i)) {
					a++;
				}
			}
			if (a > max) {
				max = a;
				t1 = s;
			}
		}
		t = t1;
		int guess = master.guess(t);
		if (guess < 0) {
			for (int i = 0; i < 6; i++) {
				mem[i] |= (1 << (t.charAt(i) - 'a'));
			}
		} else if (guess < 6) {
			set[guess].add(t);
		} else {
			return;
		}
		int count = 1;
		for (int i = 0; i < len; i++) {
			if (findSecretWordHelper(mem, wordlist[i]) && findSecretWordHelper(set,
				wordlist[i])) {
				count++;
				System.out.println(count);
				guess = master.guess(wordlist[i]);
				if (guess < 0) {
					for (int j = 0; j < 6; j++) {
						mem[j] |= (1 << (wordlist[i].charAt(j) - 'a'));
					}
				} else if (guess < 6) {
					set[guess].add(wordlist[i]);
				} else {
					return;
				}
			}
		}
	}
	
	private boolean findSecretWordHelper(HashSet<String>[] sets, String s) {
		for (int i = 5; i > 0; i--) {
			for (String x : sets[i]) {
				int c = 0;
				for (int j = 0; j < 6; j++) {
					if (s.charAt(j) == x.charAt(j)) {
						c++;
					}
				}
				if (c != i) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean findSecretWordHelper(int[] mem, String s) {
		for (int i = 0; i < 6; i++) {
			if ((mem[i] & (1 << (s.charAt(i) - 'a'))) > 0) {
				/*for (int j = 0; j < 6; j++) {
					mem[j] |= (1 << (s.charAt(j) - 'a'));
				}*/
				return false;
			}
		}
		return true;
	}
}
