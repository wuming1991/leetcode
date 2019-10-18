package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeMap;
import sun.text.normalizer.CharTrie.FriendAgent;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test10
 * @Author: WuMing
 * @CreateDate: 2019/8/23 19:02
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test10 {
	
	public static void main(String[] args) {
		Test10 test = new Test10();
		//test.numMatchingSubseq( "abcde",new String[]{"a", "bb", "acd", "ace"});
		//test.shiftingLetters("abc", new int[]{3, 5, 9});
		//test.primePalindrome(930);
		test.numSubarraysWithSum(new int[]{0, 0, 0, 0, 1}, 0);
		test.reorderLogFiles(new String[]{
			"w 7 2", "l 1 0", "6 066", "o aay", "e yal"});
		
	}
	
	public String[] reorderLogFiles(String[] logs) {
		TreeMap<String, TreeMap<String, Integer>> map = new TreeMap<>();
		int len = logs.length;
		int idx = len - 1;
		for (int i = idx; i >= 0; i--) {
			String log = logs[i];
			int x = log.indexOf(' ');
			if (log.charAt(x + 1) < 'a') {
				logs[idx] = log;
				idx--;
			} else {
				String sub = log.substring(x + 1);
				TreeMap<String, Integer> submap;
				if (map.containsKey(sub)) {
					submap = map.get(sub);
				} else {
					submap = new TreeMap<>();
					map.put(sub, submap);
				}
				submap.put(log, 1);
			}
		}
		idx = 0;
		for (TreeMap<String, Integer> submap : map.values()) {
			for (String s : submap.keySet()) {
				logs[idx] = s;
				idx++;
			}
		}
		return logs;
	}
	
	public int knightDialer(int N) {
		int[][] n = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6},
			{1, 3}, {2, 4}};
		int[] ret = new int[10];
		Arrays.fill(ret, 1);
		int[] next;
		int mod = 1_000_000_007;
		for (int i = 0; i < N; i++) {
			next = new int[10];
			for (int j = 0; j < 10; j++) {
				for (int x : n[j]) {
					next[j] += ret[x];
					next[j] %= mod;
				}
			}
			ret = next;
		}
		int r = 0;
		for (int i = 0; i < 10; i++) {
			r += ret[i];
			r %= mod;
		}
		return r;
	}
	
	public int numSubarraysWithSum(int[] A, int S) {
		int ret = 0;
		if (S == 0) {
			int count = 0;
			for (int i : A) {
				if (i == 0) {
					count++;
				} else {
					count = 0;
				}
			}
			ret += count;
			return ret;
		}
		int[] ints = new int[A.length + 1];
		ints[0] = 1;
		int sum = 0;
		for (int i : A) {
			sum += i;
			ints[sum]++;
		}
		
		for (int i = sum; i >= S; i--) {
			ret += ints[i] * ints[sum - S];
		}
		return ret;
	}
	
	public int threeSumMulti1(int[] A, int target) {
		int[] count = new int[101];
		for (int i : A) {
			count[i]++;
		}
		long ret = 0;
		int mod = 1_000_000_007, x, y, z, last;
		//全不等
		for (x = 0; x < 101; x++) {
			if (count[x] == 0) {
				continue;
			}
			last = target - x;
			y = x + 1;
			z = 100;
			while (y < z) {
				if (y + z == last) {
					ret += count[x] * count[y] * count[z];
					ret %= mod;
					y++;
					z--;
				} else if (y + z > last) {
					z--;
				} else {
					y++;
				}
			}
		}
		//全等
		if (target % 3 == 0) {
			long t = count[target / 3];
			ret += t * (t - 1) * (t - 2) / 6;
			ret %= mod;
		}
		//前两个相等和后两个相等
		for (x = 0; 3 * x < target; x++) {
			if (count[x] == 0) {
				continue;
			}
			if (target - 2 * x < 101) {
				ret += count[x] * (count[x] - 1) * count[target - 2 * x] / 2;
				ret %= mod;
			}
			if (((target - x) & 1) == 0 && (target - x) / 2 < 101) {
				ret += count[x] * (count[(target - x) / 2]) * (count[(target - x) / 2] - 1) / 2;
				ret %= mod;
			}
		}
		return (int) ret;
	}
	
	public int threeSumMulti(int[] A, int target) {
		
		int[] twoSum = new int[target + 1];
		int[] oneSum = new int[target + 1];
		int len = A.length, cur, ret = 0, mod = 1000000007;
		for (int i = 0; i < len; i++) {
			cur = A[i];
			if (cur <= target) {
				ret += twoSum[target - cur];
				ret %= mod;
				for (int j = target - cur; j >= 0; j--) {
					twoSum[cur + j] += oneSum[j];
				}
				oneSum[cur]++;
			}
		}
		return ret;
	}
	
	public int maxSubarraySumCircular(int[] A) {
		int len = A.length;
		for (int i = 0; i < len - 1; i++) {
			A[i + 1] += A[i];
		}
		int sum = A[len - 1];
		int maxSum = sum;
		int min = sum;
		int max = sum;
		int minSum = 0;
		for (int i = len - 2; i >= 0; i--) {
			if (min - A[i] < minSum) {
				minSum = min - A[i];
			}
			if (max - A[i] > maxSum) {
				maxSum = max - A[i];
			}
			min = Math.min(A[i], min);
			max = Math.max(A[i], max);
		}
		return Math.max(sum - minSum, maxSum);
	}
	
	public int partitionDisjoint1(int[] A) {
		int lmax = A[0], rmin = A[0], ret = 0;
		int len = A.length;
		for (int i = 1; i < len; i++) {
			if (A[i] < rmin) {
				ret = i;
				rmin = A[i];
			} else if (A[i] > lmax) {
				lmax = A[i];
			}
		}
		return ret + 1;
	}
	
	public int partitionDisjoint(int[] A) {
		int len = A.length;
		int[] min = new int[len];
		min[len - 1] = A[len - 1];
		for (int i = len - 2; i >= 0; i--) {
			min[i] = Math.min(A[i], min[i + 1]);
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < len - 1; i++) {
			max = Math.max(max, A[i]);
			if (max <= min[i + 1]) {
				return i + 1;
			}
		}
		return 0;
	}
	
	public int smallestRangeII(int[] A, int K) {
		Arrays.sort(A);
		int len = A.length;
		int ret = A[len - 1] - A[0], min, max;
		for (int i = 1; i < len; i++) {
			min = Math.min(A[0] + K, A[i] - K);
			max = Math.max(A[len - 1] + K, A[i - 1] - K);
			ret = Math.min(ret, max - min);
		}
		return ret;
	}
	
	public int snakesAndLadders(int[][] board) {
		int x = board.length;
		int y = board[0].length;
		int[] road = new int[x * y + 1];
		int k = 1;
		for (int i = x - 1; i >= 0; i--) {
			for (int j = 0; j < y; j++) {
				road[k++] = board[i][j];
			}
			i--;
			if (i < 0) {
				break;
			}
			for (int j = y - 1; j >= 0; j--) {
				road[k++] = board[i][j];
			}
		}
		boolean[] visit = new boolean[x * y + 1];
		ArrayList<Integer> cur = new ArrayList<>(), next;
		cur.add(1);
		visit[1] = true;
		int ret = 0, c;
		while (!cur.isEmpty()) {
			ret++;
			next = new ArrayList<>();
			for (Integer b : cur) {
				for (int i = 1; i < 7; i++) {
					c = i + b;
					if (road[c] > 0) {
						c = road[c];
					}
					if (!visit[c]) {
						if (c >= x * y) {
							return ret;
						}
						visit[c] = true;
						next.add(c);
					}
				}
			}
			cur = next;
		}
		return -1;
	}
	
	public int sumSubarrayMins2(int[] A) {
		int len = A.length, ret = 0, mod = 1000000007;
		int[] left = new int[len];
		int[] right = new int[len];
		for (int i = 0; i < len; i++) {
			left[i] = i;
			while (left[i] > 0 && A[left[i] - 1] > A[i]) {
				left[i] = left[left[i] - 1];
			}
		}
		for (int i = len - 1; i >= 0; i--) {
			right[i] = i;
			while (right[i] < len - 1 && A[right[i] + 1] >= A[i]) {
				right[i] = right[right[i] + 1];
			}
		}
		for (int i = 0; i < len; i++) {
			ret += A[i] * (i - left[i] + 1) * (right[i] - i + 1);
			ret %= mod;
		}
		return ret;
	}
	
	public int sumSubarrayMins1(int[] A) {
		int len = A.length;
		int l, r, x, y, ret = 0, mod = 1000000007;
		for (int i = 0; i < len; i++) {
			l = i - 1;
			r = i + 1;
			x = 1;
			y = 1;
			while (l >= 0 && A[l] >= A[i]) {
				x++;
				l--;
			}
			while (r < len && A[r] > A[i]) {
				y++;
				r++;
			}
			ret += A[i] * x * y;
			ret %= mod;
		}
		return ret;
	}
	
	public int sumSubarrayMins(int[] A) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int ret = 0, mod = 1000000007;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i : A) {
			int count = 1;
			for (Integer x : map.keySet()) {
				if (x < i) {
					ret += x * map.get(x);
				} else {
					count += map.get(x);
					list.add(x);
				}
			}
			for (Integer integer : list) {
				map.remove(integer);
			}
			list.clear();
			ret += i * count;
			ret %= mod;
			map.put(i, count);
		}
		return ret;
	}
	
	public int subarrayBitwiseORs1(int[] A) {
		int len = A.length;
		int[] res = new int[len];
		int le = 0;
		int ri = 0;
		for (int num : A) {
			int start = ri;
			res[ri] = num;
			ri++;
			for (int i = le; i < ri; i++) {
				int temp = res[i] | num;
				if (temp > res[ri - 1]) {
					res[ri] = (temp);
					ri++;
				}
			}
			le = start;
		}
		HashSet<Integer> set = new HashSet<>();
		int ret = 0;
		for (int i = 0; i < ri; i++) {
			if (set.add(res[i])) {
				ret++;
			}
		}
		
		return ret;
	}
	
	public int subarrayBitwiseORs(int[] A) {
		HashSet<Integer> set = new HashSet<>();
		HashSet<Integer> next;
		HashSet<Integer> ret = new HashSet<>();
		for (int i : A) {
			next = new HashSet<>();
			next.add(i);
			for (Integer x : set) {
				next.add(x | i);
			}
			set = next;
			ret.addAll(set);
		}
		return ret.size();
	}
	
	int preIndex = 0, posIndex = 0;
	
	public TreeNode constructFromPrePost1(int[] pre, int[] post) {
		TreeNode root = new TreeNode(pre[preIndex++]);
		if (root.val != post[posIndex]) {
			root.left = constructFromPrePost(pre, post);
		}
		if (root.val != post[posIndex]) {
			root.right = constructFromPrePost(pre, post);
		}
		posIndex++;
		return root;
	}
	
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		
		int len = pre.length;
		if (len < 1) {
			return null;
		}
		int x = 1, y = len - 2;
		TreeNode root = new TreeNode(pre[0]);
		constructFromPrePostHelper(x, y, pre, post, root);
		return root;
	}
	
	private void constructFromPrePostHelper(int x, int y, int[] pre, int[] post, TreeNode root) {
		HashMap<Integer, TreeNode> map = new HashMap<>();
		map.put(pre[0], root);
		TreeNode cur = root;
		int len = pre.length;
		for (int i = x; i < len; i++) {
			TreeNode n = new TreeNode(pre[i]);
			while (post[y] != pre[i]) {
				if (map.containsKey(post[y])) {
					cur = map.get(post[y]);
				}
				y--;
				if (y < 0) {
					y = len - 1;
				}
			}
			map.put(pre[i], n);
			if (cur.left == null) {
				cur.left = n;
			} else {
				cur.right = n;
			}
			cur = n;
		}
	}
	
	public boolean possibleBipartition1(int N, int[][] dislikes) {
		int[] color = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			color[i] = i;
		}
		for (int[] dislike : dislikes) {
			int x = dislike[0];
			int y = dislike[1];
			if (color[x] == x && color[y] == y) {
				//x,y均未出现过,放入对立分组
				color[x] = y;
				color[y] = x;
			} else if (color[x] == x && color[y] != y) {
				//x未出现过,但是y已经有分组,将x放入y对立分组
				color[x] = color[color[y]];
			} else if (color[y] == y && color[x] != x) {
				//y未出现过,但是x已经有分组,将y放入x对立分组
				color[y] = color[color[x]];
			} else if (color[x] == color[y]) {
				//x,y都出现了,但是出现在同一个分组中
				return false;
			}
		}
		return true;
	}
	
	public boolean possibleBipartition(int N, int[][] dislikes) {
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		Set<Integer> set0;
		Set<Integer> set1;
		for (int[] dislike : dislikes) {
			if (map.containsKey(dislike[0])) {
				set0 = map.get(dislike[0]);
			} else {
				set0 = new HashSet<>();
				map.put(dislike[0], set0);
			}
			if (map.containsKey(dislike[1])) {
				set1 = map.get(dislike[1]);
			} else {
				set1 = new HashSet<>();
				map.put(dislike[1], set1);
			}
			set0.add(dislike[1]);
			set1.add(dislike[0]);
		}
		int[] flag = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			if (flag[i] == 0) {
				if (!possibleBipartitionHelper(flag, i, 1, map)) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean possibleBipartitionHelper(int[] flag, int i, int f,
		HashMap<Integer, Set<Integer>> map) {
		if (flag[i] == f) {
			return true;
		} else if (flag[i] != 0) {
			return false;
		}
		flag[i] = f;
		if (!map.containsKey(i)) {
			return true;
		}
		for (Integer integer : map.get(i)) {
			if (!possibleBipartitionHelper(flag, integer, f * -1, map)) {
				return false;
			}
		}
		return true;
	}
	
	public String decodeAtIndex1(String S, int K) {
		
		int len = S.length(), i;
		if (K == 1) {
			return S.charAt(0) + "";
		}
		long[] longs = new long[len];
		longs[0] = 1;
		for (i = 1; i < len; i++) {
			char c = S.charAt(i);
			if (c >= 'a') {
				longs[i] = longs[i - 1] + 1;
			} else {
				longs[i] = longs[i - 1] * (c - '0');
			}
			if (longs[i] >= K) {
				break;
			}
		}
		if (i == len) {
			K %= longs[len - 1];
		}
		while (true) {
			while (longs[i] > K) {
				i--;
			}
			if (longs[i] == K) {
				if (S.charAt(i) >= 'a') {
					return S.charAt(i) + "";
				} else {
					int num = S.charAt(i) - '0';
					K %= num;
					if (K == 0) {
						K = (int) longs[i - 1];
					}
				}
			} else {
				K %= longs[i];
				if (K == 0) {
					K = (int) longs[i];
				}
			}
		}
	}
	
	public String decodeAtIndex(String S, int K) {
		return decodeAtIndexHelper(S, 0, 0, K);
	}
	
	private String decodeAtIndexHelper(String S, long count, int idx, int k) {
		int c = 0, i = idx;
		int len = S.length();
		while (i < len && S.charAt(i) >= 'a') {
			c++;
			i++;
		}
		int num = 1;
		if (i < len) {
			num = S.charAt(i) - '0';
		}
		long total = num * (count + c);
		if (total >= k) {
			k %= (count + c);
			if (k == 0) {
				if (c > 0) {
					return S.charAt(i - 1) + "";
				}
				return decodeAtIndex(S, (int) count);
			} else if (k > count) {
				return S.charAt((int) (idx + k - count - 1)) + "";
			} else {
				return decodeAtIndex(S, k);
			}
		} else if (i < len) {
			return decodeAtIndexHelper(S, num * (count + c), i + 1, k);
		} else {
			return decodeAtIndex(S, k % (int) (count + c));
		}
	}
	
	public int[] advantageCount(int[] A, int[] B) {
		int len = A.length;
		int[] As = Arrays.copyOf(A, len);
		int[] Bs = Arrays.copyOf(B, len);
		Arrays.sort(As);
		Arrays.sort(Bs);
		HashMap<Integer, LinkedList<Integer>> map = new HashMap<>();
		LinkedList<Integer> little = new LinkedList<>();
		for (int i = 0, j = 0; i < len; i++) {
			if (As[i] > Bs[j]) {
				LinkedList<Integer> list;
				if (map.containsKey(Bs[j])) {
					list = map.get(Bs[j]);
				} else {
					list = new LinkedList<Integer>();
					map.put(Bs[j], list);
				}
				list.add(As[i]);
				j++;
			} else {
				little.add(As[i]);
			}
		}
		for (int i = 0; i < len; i++) {
			List<Integer> list = map.get(B[i]);
			if (list == null || list.size() < 1) {
				A[i] = little.removeFirst();
			} else {
				A[i] = list.remove(0);
			}
		}
		return A;
	}
	
	public int primePalindrome(int N) {
		if (N == 1) {
			return 2;
		} else if (N < 3) {
			return N;
		} else if (N < 6) {
			return 5;
		} else if (N < 8) {
			return 7;
		} else if (N < 12) {
			return 11;
		}
		int[] head = {1, 3, 7, 9};
		int i = 1, t = N;
		while (t > 10) {
			i++;
			t /= 10;
		}
		for (i = i / 2 + 1; ; i++) {
			int[] num = new int[i];
			for (int cur : head) {
				num[0] = cur;
				int ret = primePalindromeHelper(num, 1, N);
				if (ret > 0) {
					return ret;
				}
			}
		}
	}
	
	private int primePalindromeHelper(int[] num, int i, int n) {
		if (i == num.length) {
			int t = 0;
			for (int j = 0; j < i; j++) {
				t *= 10;
				t += num[j];
			}
			for (int j = i - 2; j >= 0; j--) {
				t *= 10;
				t += num[j];
			}
			if (t > n && isprime(t)) {
				return t;
			} else {
				return -1;
			}
		}
		for (int j = 0; j < 10; j++) {
			num[i] = j;
			int ret = primePalindromeHelper(num, i + 1, n);
			if (ret > 0) {
				return ret;
			}
		}
		return -1;
	}
	
	private boolean isprime(int t) {
		double sqrt = Math.sqrt(t);
		for (int i = 2; i <= sqrt; i++) {
			if (t % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	
	public int carFleet1(int target, int[] position, int[] speed) {
		TreeMap<Integer, Integer> map = new TreeMap<>();
		int len = position.length;
		for (int i = 0; i < len; i++) {
			map.put(position[i] - target, speed[i]);
		}
		int count = 0;
		double t = Double.MIN_VALUE;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			Integer d = entry.getKey();
			Integer s = entry.getValue();
			double time = 1.0 * d / s;
			if (time > t) {
				t = time;
				count++;
			}
		}
		return count;
	}
	
	public int carFleet(int target, int[] position, int[] speed) {
		
		int len = position.length;
		if (len < 1) {
			return 0;
		}
		int[][] ints = new int[len][2];
		for (int i = 0; i < len; i++) {
			ints[i][0] = position[i];
			ints[i][1] = speed[i];
		}
		Arrays.sort(ints, (a, b) -> (a[0] - b[0]));
		int l = 0, r = len - 1, m;
		while (l < r) {
			m = (l + r) / 2;
			int cur = ints[m][0];
			if (cur == target) {
				l = m;
				break;
			} else if (cur < target) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		
		int ret = 0;
		r = l + 1;
		for (int i = l; i >= 0; i--) {
			double t = ((double) (target - ints[i][0])) / ints[i][1];
			while (i > 0 && ints[i - 1][0] + ints[i - 1][1] * t >= target) {
				i--;
			}
			ret++;
		}
		for (int i = r; i < len; i++) {
			double t = ((double) (ints[i][0] - target)) / ints[i][1];
			while (i < len - 1 && ints[i + 1][0] - ints[i + 1][1] * t <= target) {
				i++;
			}
			ret++;
		}
		return ret;
	}
	
	public String shiftingLetters(String S, int[] shifts) {
		int len = shifts.length;
		char[] ret = new char[len];
		int k = 0;
		for (int i = len - 1; i >= 0; i--) {
			k += (shifts[i] % 26);
			ret[i] = (char) ((S.charAt(i) + k - 'a') % 26 + 'a');
		}
		return new String(ret);
	}
	
	public int longestMountain(int[] A) {
		int i = 0, l, ret = 0;
		int len = A.length;
		while (i < len - 1 && A[i] >= A[i + 1]) {
			i++;
		}
		while (i < len - 1) {
			l = i;
			while (i < len - 1 && A[i] < A[i + 1]) {
				i++;
			}
			if (i < len - 1 && A[i] > A[i + 1]) {
				while (i < len - 1 && A[i] > A[i + 1]) {
					i++;
				}
				ret = Math.max(ret, i - l + 1);
			}
			while (i < len - 1 && A[i] >= A[i + 1]) {
				i++;
			}
		}
		return ret;
	}
	
	public List<Integer> splitIntoFibonacci(String S) {
		char[] array = S.toCharArray();
		int len = array.length;
		int f = 0, s = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < len / 2; i++) {
			f *= 10;
			if (i > 0 && f <= 0) {
				return list;
			}
			f += array[i] - '0';
			list.add(f);
			s = 0;
			for (int j = i + 1; j < len - 1; j++) {
				s *= 10;
				if (j > i + 1 && s <= 0) {
					break;
				}
				s += array[j] - '0';
				list.add(s);
				if (splitIntoFibonacciHelper(array, j + 1, f, s, list)) {
					return list;
				}
				list.clear();
				list.add(f);
			}
			list.clear();
		}
		return list;
	}
	
	private boolean splitIntoFibonacciHelper(char[] array, int i, int f, int s,
		LinkedList<Integer> list) {
		int len = array.length;
		int t = array[i] - '0';
		i++;
		while (t < f + s && i < len && t > 0) {
			t *= 10;
			t += array[i] - '0';
			i++;
		}
		if (t != f + s) {
			return false;
		} else if (i < len) {
			list.add(t);
			return splitIntoFibonacciHelper(array, i, s, t, list);
		} else {
			list.add(t);
			return true;
		}
	}
	
	public double new21Game1(int N, int K, int W) {
		double[] rate = new double[N + 1];
		rate[0] = 1;
		double sum = 1;
		for (int i = 1; i <= N; i++) {
			rate[i] = sum / W;
			if (i < K) {
				sum += rate[i];
			}
			if (i >= W) {
				sum -= rate[i - W];
			}
		}
		double ret = 0;
		for (int i = K; i <= N; i++) {
			ret += rate[i];
		}
		return ret;
	}
	
	public double new21Game(int N, int K, int W) {
		double[] rate = new double[N + 1];
		for (int i = 1; i <= W && i <= N; i++) {
			rate[i] = 1.0 / W;
		}
		double sum = 0;
		for (int i = 0; i <= N; i++) {
			for (int j = i - 1; j > 0 && j + W >= i; j--) {
				if (j < K) {
					rate[i] += rate[j] / W;
				}
			}
			if (i >= K) {
				sum += rate[i];
			}
		}
		return sum;
	}
	
	public int numFriendRequests(int[] ages) {
		int[] count = new int[121];
		for (int age : ages) {
			count[age]++;
		}
		int[] sum = new int[121];
		for (int i = 1; i < 121; i++) {
			sum[i] = sum[i - 1] + count[i];
		}
		int ret = 0, min, max;
		for (int i = 1; i < 121; i++) {
			if (count[i] == 0) {
				continue;
			}
			max = i;
			min = (i + 1) / 2 + 7;
			if (max < min) {
				continue;
			} else {
				min = (i & 1) == 1 ? min - 1 : min;
				if (max != min) {
					ret += count[i] * (sum[max] - sum[(i & 1) == 1 ? min - 1 : min] - 1);
				}
			}
		}
		return ret;
	}
	
	public int flipgame(int[] fronts, int[] backs) {
		int[] ints = new int[2001];
		int len = fronts.length;
		for (int i = 0; i < len; i++) {
			if (fronts[i] == backs[i]) {
				ints[fronts[i]] = 2;
			} else {
				if (ints[fronts[i]] == 0) {
					ints[fronts[i]] = 1;
				}
				if (ints[backs[i]] == 0) {
					ints[backs[i]] = 1;
				}
			}
		}
		for (int i = 1; i < 2001; i++) {
			if (ints[i] == 1) {
				return i;
			}
		}
		return 0;
	}
	
	public int numFactoredBinaryTrees(int[] A) {
		Arrays.sort(A);
		int mod = 1000000007;
		int len = A.length;
		int[] count = new int[len];
		count[0] = 1;
		for (int i = 1; i < len; i++) {
			int cur = A[i];
			int l = 0, r = i - 1;
			int c = 1;
			while (l < r) {
				int x = A[l] * A[r];
				if (x == cur) {
					c += 2 * count[l] * count[r];
					c %= mod;
					l++;
					r--;
				} else if (x > cur) {
					r--;
				} else {
					l++;
				}
			}
			if (A[l] * A[l] == cur) {
				c += count[l] * count[l];
			}
			count[i] = c % mod;
		}
		int ret = 0;
		for (int i = 0; i < len; i++) {
			ret += count[i];
			ret %= mod;
		}
		return ret;
	}
	
	public List<String> ambiguousCoordinates(String S) {
		char[] array = S.toCharArray();
		int len = array.length;
		List<String> ret = new ArrayList<>();
		for (int i = 1; i < len - 2; i++) {
			List<String> left = ambiguousCoordinatesHelper(array, 1, i);
			
			List<String> right = ambiguousCoordinatesHelper(array, i + 1, len - 2);
			for (String l : left) {
				for (String r : right) {
					ret.add("(" + l + "," + r + ")");
				}
			}
		}
		return ret;
	}
	
	private List<String> ambiguousCoordinatesHelper(char[] array, int l, int r) {
		ArrayList<String> ret = new ArrayList<>();
		if (l == r) {
			ret.add(array[l] + "");
			return ret;
		}
		if (array[l] == '0' && array[r] == '0') {
			return ret;
		} else if (array[l] == '0') {
			ret.add(array[l] + "." + new String(array, l + 1, r - l));
		} else if (array[r] == '0') {
			ret.add(new String(array, l, r - l + 1));
		} else {
			ret.add(new String(array, l, r - l + 1));
			for (int i = l + 1; i <= r; i++) {
				ret.add(new String(array, l, i - l) + '.' + new String(array, i + 1, r - i));
			}
		}
		return ret;
		
	}
	
	public double largestTriangleArea(int[][] points) {
		int len = points.length;
		int max = 0;
		int x1, y1, x2, y2, x3, y3, s;
		for (int i = 0; i < len - 2; i++) {
			x1 = points[i][0];
			y1 = points[i][1];
			for (int j = i + 1; j < len - 1; j++) {
				x2 = points[j][0];
				y2 = points[j][1];
				for (int k = j + 1; k < len; k++) {
					x3 = points[k][0];
					y3 = points[k][1];
					s = x1 * y2 + x2 * y3 + x3 * y1 - x1 * y3 - x2 * y1 - x3 * y2;
					max = Math.max(max, Math.abs(s));
				}
			}
		}
		return (double) max / 2;
	}
	
	public int minSwap(int[] A, int[] B) {
		int len = A.length;
		int ret = 0, a = A[0], b = B[0], na, nb, max, count = 0, l = 0;
		for (int i = 1; i < len; i++) {
			na = A[i];
			nb = B[i];
			max = Math.max(a, b);
			if (na > max && nb > max) {
				ret += Math.min(i - l - count, count);
				count = 0;
				l = i;
				a = na;
				b = nb;
			} else if (na <= a || nb <= b) {
				count++;
				a = nb;
				b = na;
			} else {
				a = na;
				b = nb;
			}
		}
		return ret + Math.min(len - l - count, count);
	}
	
	public int minSwap1(int[] A, int[] B) {
		int keep = 0, swap = 1, t;
		int len = A.length;
		for (int i = 1; i < len; i++) {
			if (A[i] > A[i - 1] && B[i] > B[i - 1]) {
				if (A[i] > B[i - 1] && B[i] > A[i - 1]) {
					swap = Math.min(swap, keep) + 1;
					keep = swap - 1;
				} else {
					swap++;
				}
			} else {
				t = keep;
				keep = swap;
				swap = t + 1;
			}
		}
		return Math.min(keep, swap);
	}
	
	public double champagneTower(int poured, int query_row, int query_glass) {
		double[] cur = {poured};
		double[] next;
		double left, right;
		int l, r;
		for (int i = 1; i <= query_row; i++) {
			next = new double[i + 1];
			right = 0;
			for (int j = 0; j < i / 2 + 1; j++) {
				left = right;
				right = cur[j] > 1 ? cur[j] - 1 : 0;
				next[j] = (left + right) / 2;
			}
			l = 0;
			r = i;
			while (l < r) {
				next[r] = next[l];
				r--;
				l++;
			}
			cur = next;
		}
		return cur[query_glass] > 1 ? 1 : cur[query_glass];
	}
	
	public int numSubarrayBoundedMax1(int[] A, int L, int R) {
		int len = A.length;
		int ans = 0;
		int l = 0, r = 0;
		while (r < len) {
			if (L <= A[r] && A[r] <= R) { //情况一
				ans += r - l + 1;
				r++;
			} else if (A[r] < L) { // 情况二
				int t = r - 1;
				while (t >= l && A[t] < L) {
					t--;
				}
				ans += t - l + 1;
				r++;
			} else {  // 情况三
				l = r + 1;
				r++;
			}
		}
		return ans;
		
	}
	
	public int numSubarrayBoundedMax(int[] A, int L, int R) {
		return numSubarrayBoundedMaxHelper(A, R) - numSubarrayBoundedMaxHelper(A, L - 1);
	}
	
	private int numSubarrayBoundedMaxHelper(int[] a, int max) {
		int len = 0;
		int ret = 0;
		for (int i : a) {
			if (i > max) {
				len = 0;
			} else {
				len++;
			}
			ret += len;
		}
		return ret;
	}
	
	public boolean validTicTacToe(String[] board) {
		int oc = 0, xc = 0;
		for (String s : board) {
			for (int i = 0; i < 3; i++) {
				char c = s.charAt(i);
				if (c == 'O') {
					oc++;
				} else if (c == 'X') {
					xc++;
				}
			}
		}
		if (xc - oc > 1 || xc - oc < 0) {
			return false;
		}
		int[][] ints = new int[][]{
			{0, 0}, {0, 1}, {0, 2},
			{1, 0}, {1, 1}, {1, 2},
			{2, 0}, {2, 1}, {2, 2},
			{0, 0}, {1, 0}, {2, 0},
			{0, 1}, {1, 1}, {2, 1},
			{0, 2}, {1, 2}, {2, 2},
			{0, 0}, {1, 1}, {2, 2},
			{0, 2}, {1, 1}, {2, 0},
		};
		int ow = 0, xw = 0;
		for (int i = 0; i < 24; i += 3) {
			char a = board[ints[i][0]].charAt(ints[i][1]);
			char b = board[ints[i + 1][0]].charAt(ints[i + 1][1]);
			char c = board[ints[i + 2][0]].charAt(ints[i + 2][1]);
			if (a == b && b == c) {
				if (a == 'X') {
					xw++;
				} else if (a == 'O') {
					ow++;
				}
			}
		}
		if (ow > 0 && xw > 0) {
			return false;
		}
		if (ow > 0 && oc != xc) {
			return false;
		}
		if (xw > 0 && oc == xc) {
			return false;
		}
		return true;
	}
	
	public int numMatchingSubseq(String S, String[] words) {
		int ret = 0, i;
		for (String word : words) {
			i = 0;
			for (char c : word.toCharArray()) {
				i = S.indexOf(c, i);
				if (i < 0) {
					break;
				} else {
					i++;
				}
			}
			if (i > 0) {
				ret++;
			}
		}
		return ret;
	}
	
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		int[] cost = new int[n];
		Arrays.fill(cost, Integer.MAX_VALUE);
		
		HashMap<Integer, List<int[]>> map = new HashMap<>();
		for (int[] flight : flights) {
			List<int[]> list;
			if (map.containsKey(flight[0])) {
				list = map.get(flight[0]);
			} else {
				list = new ArrayList<>();
				map.put(flight[0], list);
			}
			list.add(flight);
		}
		ArrayList<int[]> cur = new ArrayList<>();
		cur.add(new int[]{src, 0});
		ArrayList<int[]> next;
		while (!cur.isEmpty() && K >= 0) {
			next = new ArrayList<>();
			for (int[] from : cur) {
				if (map.containsKey(from[0])) {
					int base = from[1];
					for (int[] flight : map.get(from[0])) {
						if (base + flight[2] < cost[flight[1]]) {
							next.add(new int[]{flight[1], base + flight[2]});
							cost[flight[1]] = base + flight[2];
						}
					}
				}
			}
			cur = next;
			K--;
		}
		return cost[dst] == Integer.MAX_VALUE ? -1 : cost[dst];
	}
	
	public int kthGrammar(int N, int K) {
		if (K == 1) {
			return 0;
		} else if (K == 2) {
			return 1;
		}
		while (K <= (1 << N) && N >= 0) {
			N--;
		}
		K -= (1 << N);
		return kthGrammar(N, K) == 0 ? 1 : 0;
	}
	
	public boolean isIdealPermutation(int[] A) {
		int maxIdx = 0;
		int second = Integer.MIN_VALUE;
		int len = A.length;
		for (int i = 1; i < len; i++) {
			if (A[i] > A[maxIdx]) {
				second = A[maxIdx];
				maxIdx = i;
			} else if (i - maxIdx > 1) {
				return false;
			} else if (A[i] <= second) {
				return false;
			}
		}
		return true;
	}
	
	public boolean canTransform(String start, String end) {
		char[] a = start.toCharArray();
		char[] b = end.toCharArray();
		int len = a.length;
		for (int i = 0; i < len; i++) {
			if (a[i] == b[i]) {
				continue;
			}
			if (b[i] == 'X') {
				if (a[i] == 'L') {
					return false;
				}
				int t = i;
				while (t < len && a[t] == 'R') {
					t++;
				}
				if (a[t] != 'X') {
					return false;
				} else {
					a[i] = 'X';
					a[t] = 'R';
				}
			} else if (b[i] == 'L') {
				if (a[i] == 'R') {
					return false;
				}
				int t = i;
				while (t < len && a[t] == 'X') {
					t++;
				}
				if (a[t] == 'R') {
					return false;
				} else {
					a[i] = 'L';
					a[t] = 'X';
				}
			} else {
				if (a[i] == 'L') {
					return false;
				}
				int t = i;
				while (t < len && a[t] == 'X') {
					t++;
				}
				if (t == len || a[t] == 'L') {
					return false;
				} else {
					a[i] = 'X';
					a[t] = 'R';
				}
			}
		}
		return true;
	}
	
	public int networkDelayTime(int[][] times, int N, int K) {
		HashMap<Integer, List<int[]>> map = new HashMap<>();
		for (int[] time : times) {
			List<int[]> ints;
			if (map.containsKey(time[0])) {
				ints = map.get(time[0]);
			} else {
				ints = new ArrayList<>();
				map.put(time[0], ints);
			}
			ints.add(time);
		}
		int[] ints = new int[N + 1];
		Arrays.fill(ints, Integer.MAX_VALUE);
		ints[0] = 0;
		ints[K] = 0;
		Stack<Integer> stack = new Stack<>();
		stack.push(K);
		while (!stack.empty()) {
			Integer cur = stack.pop();
			if (!map.containsKey(cur)) {
				continue;
			}
			List<int[]> list = map.get(cur);
			int base = ints[cur];
			for (int[] node : list) {
				if (base + node[2] < ints[node[1]]) {
					stack.push(node[1]);
					ints[node[1]] = base + node[2];
				}
			}
		}
		int max = 0;
		for (int i = 1; i <= N; i++) {
			if (ints[i] > max) {
				max = ints[i];
			}
		}
		return max == Integer.MAX_VALUE ? -1 : max;
	}
	
	public int monotoneIncreasingDigits(int N) {
		char[] array = (N + "").toCharArray();
		int len = array.length;
		char[] ret = new char[len];
		int i = 0;
		for (; i < len - 1; i++) {
			if (array[i] <= array[i + 1]) {
				ret[i] = array[i];
			} else {
				while (array[i - 1] == array[i]) {
					i--;
				}
				ret[i] = (char) (array[i] - 1);
				break;
			}
		}
		if (i == len - 1) {
			ret[i] = array[i];
		} else {
			i++;
			for (; i < len; i++) {
				ret[i] = '9';
			}
		}
		int ans = 0;
		for (int j = 0; j < len; j++) {
			ans *= 10;
			ans += (ret[j] - '0');
		}
		return ans;
	}
	
	public int[] asteroidCollision(int[] asteroids) {
		int len = asteroids.length;
		int[] ints = new int[len];
		int l = -1, r = -1;
		for (int i = 0; i < len; i++) {
			int cur = asteroids[i];
			if (cur < 0) {
				if (l > r) {
					l++;
					continue;
				}
				int j = r;
				for (; j > l; j--) {
					if (cur + asteroids[j] > 0) {
						asteroids[i] = 0;
						break;
					} else if (cur + asteroids[j] == 0) {
						asteroids[i] = 0;
						asteroids[j] = 0;
						break;
					}
					asteroids[j] = 0;
				}
				if (j == l) {
					l = i;
				} else {
					r = j;
				}
			} else {
				r = i;
			}
		}
		int i = 0;
		for (int asteroid : asteroids) {
			if (asteroid != 0) {
				ints[i] = asteroid;
				i++;
			}
		}
		
		return Arrays.copyOf(ints, i);
	}
	
	static class Node {
		
		public int val;
		public Node next;
		public Node random;
		
		public Node() {
		}
		
		public Node(int _val, Node _next, Node _random) {
			val = _val;
			next = _next;
			random = _random;
		}
	}
	
	
	Map<Node, Node> map = new HashMap();
	
	public Node copyRandomList(Node head) {
		if (head == null) {
			return head;
		}
		if (map.containsKey(head)) {
			return map.get(head);
		}
		Node node = new Node();
		map.put(head, node);
		node.val = head.val;
		node.next = copyRandomList(head.next);
		node.random = copyRandomList(head.random);
		return node;
	}
	
	public Node copyRandomList1(Node head) {
		Node temp = head;
		while (temp != null) {
			Node n = new Node();
			n.val = temp.val;
			n.next = temp.next;
			temp.next = n;
			temp = n.next;
		}
		temp = head;
		while (temp != null) {
			if (temp.random != null) {
				temp.next.random = temp.random.next;
			}
			temp = temp.next.next;
		}
		Node ret = new Node();
		Node b = ret;
		temp = head;
		while (temp != null) {
			b.next = temp.next;
			b = b.next;
			temp.next = b.next;
			temp = temp.next;
		}
		
		return ret.next;
	}
}
