package com.leetcode2;

import static com.base.Constant.mod;

import com.base.ListNode;
import com.leetcode2.Codec.TreeNode;
import com.sun.deploy.security.MSCryptoDSASignature.NONEwithDSA;
import com.wuming.pattern.Memento.MementoCaretaker;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.commons.codec.digest.DigestUtils;
import sun.font.TrueTypeFont;

/**
 * @Author: WuMing
 * @CreateDate: 2020/6/16 14:29
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test5 {
	
	public static void main(String[] args) {
		Test5 test = new Test5();
		System.out.println("".compareTo("0"));
		System.out.println("1".compareTo("0"));
		test.reachingPoints1(6,5,11,16);
	}
	
	
	//780. 到达终点
	public boolean reachingPoints1(int sx, int sy, int tx, int ty) {
		return reachingPointsHelper1(sx, sy, tx, ty, new HashSet<Long>());
	}
	
	private boolean reachingPointsHelper1(long sx, long sy, long tx, long ty, HashSet<Long> set) {
		if (tx < sx || ty < sy || set.contains((tx << 32) + ty)) {
			return false;
		} else if (sx == tx) {
			return (ty - sy) % sx == 0;
		} else if (sy == ty) {
			return (tx - sx) % sy == 0;
		}
		set.add((tx << 32) + ty);
		if (tx < ty) {
			long x = ty % tx;
			ty = ((sy / tx) + 1) * tx + x;
		} else if (tx > ty) {
			long x = tx % ty;
			tx = ((sx / ty) + 1) * ty + x;
		}
		if (reachingPointsHelper1(sx, sy, tx - ty, ty, set) || reachingPointsHelper1(sx, sy, tx,
			ty - tx, set)) {
			return true;
		} else {
			set.add((tx << 32) + ty);
			return false;
		}
	}
	//64. 最小路径和
	public int minPathSum(int[][] grid) {
		int high = grid.length;
		int len = grid[0].length;
		for (int i = 1; i < high; i++) {
			grid[i][0]+=grid[i-1][0];
		}
		for (int i = 1; i < len; i++) {
			grid[0][i]+=grid[0][i-1];
		}
		for (int i = 1; i < high; i++) {
			for (int j = 1; j < len; j++) {
				grid[i][j]+=Math.min(grid[i-1][j],grid[i][j-1]);
			}
		}
		return grid[high-1][len-1];
	}
	//446. 等差数列划分 II - 子序列
	public int numberOfArithmeticSlices(int[] A) {
		int len = A.length;
		HashMap<Long, Integer>[] maps = new HashMap[len];
		for (int i = 0; i < len; i++) {
			maps[i] = new HashMap<>();
		}
		int ret = 0,b;long x;
		for (int i = 1; i < len; i++) {
			for (int j = 0; j < i; j++) {
				x=(long)A[i]-(long)A[j];
				b=maps[j].getOrDefault(x,0);
				ret+=b;
				maps[i].put(x,maps[i].getOrDefault(x,0)+b+1);
			}
		}
		return ret;
	}
	
	//502. IPO
	public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
		int len = Profits.length;
		int[][] mem = new int[len][2];
		for (int i = 0; i < len; i++) {
			mem[i][0] = Capital[i];
			mem[i][1] = Profits[i];
		}
		Arrays.sort(mem, (a, b) -> (a[0] - b[0]));
		for (int i = 0; i <len ; i++) {
			Profits[i]=mem[i][1];
		}
		LinkedList<int[]> list = new LinkedList<>();
		int left=0,right=0,max=0;
		int[] m=new int[2];
		while (k>0){
			while (right<len&&mem[right][0]<=W){
				right++;
			}
			if(left<right){
				Arrays.sort(Profits,left,right);
				list.add(new int[]{left,right-1});
			}
			//m=new int[2];
			max=0;
			for (int[] ints : list) {
				if(ints[1]>=ints[0]&&Profits[ints[1]]>max){
					max=Profits[ints[1]];
					m=ints;
				}
			}
			W+=max;m[1]--;left=right;k--;
		}
		return W;
	}
	
	//514. 自由之路
	public int findRotateSteps(String ring, String key) {
		List<Integer>[] mem = new List[26];
		for (int i = 0; i < 26; i++) {
			mem[i] = new ArrayList();
		}
		int len1 = ring.length();
		int len2 = key.length();
		for (int i = 0; i < len1; i++) {
			mem[ring.charAt(i) - 'a'].add(i);
		}
		ArrayList<int[]> cur = new ArrayList<>(), next;
		int x = key.charAt(0) - 'a';
		for (Integer t : mem[x]) {
			cur.add(new int[]{t, Math.min(t, len1 - t)});
		}
		int idx = 1, min, l;
		while (idx < len2) {
			next = new ArrayList<>();
			x = key.charAt(idx) - 'a';
			for (Integer t : mem[x]) {
				min = Integer.MAX_VALUE;
				for (int[] ints : cur) {
					l = Math.abs(ints[0] - t);
					l = Math.min(l, len1 - l);
					min = Math.min(min, ints[1] + l);
				}
				next.add(new int[]{t, min});
			}
			cur = next;
			idx++;
		}
		min = Integer.MAX_VALUE;
		for (int[] ints : cur) {
			min = Math.min(min, ints[1]);
		}
		return min + len2;
	}
	
	public int shortestSubarray(int[] A, int K) {
		int len = A.length;
		int[] mem = new int[len + 1];
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		int sum = 0, ret = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			sum += A[i];
			mem[i + 1] = sum;
			while (!list.isEmpty() && mem[list.getLast()] >= sum) {
				list.removeLast();
			}
			while (!list.isEmpty() && sum - mem[list.getFirst()] >= K) {
				ret = Math.min(ret, i + 1 - list.removeFirst());
			}
			list.add(i + 1);
		}
		return ret == Integer.MAX_VALUE ? -1 : ret;
	}
	
	
	
	//780. 到达终点--超时
	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		return reachingPointsHelper(sx, sy, tx, ty, new HashSet<Long>());
	}
	
	private boolean reachingPointsHelper(long sx, long sy, long tx, long ty, HashSet<Long> set) {
		if (sx > tx || sy > ty || set.contains((sx << 32) + sy)) {
			return false;
		} else if (sx == tx && sy == ty) {
			return true;
		}
		if (reachingPointsHelper(sx + sy, sy, tx, ty, set) || reachingPointsHelper(sx, sx + sy, tx,
			ty, set)) {
			return true;
		} else {
			set.add((sx << 32) + sy);
			return false;
		}
	}
	
	public int[] countSubTrees(int n, int[][] edges, String labels) {
		HashMap<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(i, new ArrayList<>());
		}
		for (int[] edge : edges) {
			map.get(edge[0]).add(edge[1]);
			map.get(edge[1]).add(edge[0]);
		}
		int[] ret = new int[n];
		countSubTreesHelper(map, 0, ret, labels);
		return ret;
	}
	
	private int[] countSubTreesHelper(HashMap<Integer, List<Integer>> map, Integer i, int[] ret,
		String labels) {
		List<Integer> list = map.get(i);
		int[] count = new int[26];
		count[labels.charAt(i) - 'a']++;
		if (list.size() == 0) {
			ret[i] = 1;
			return count;
		}
		for (Integer x : list) {
			map.get(x).remove(i);
			int[] cur = countSubTreesHelper(map, x, ret, labels);
			for (int j = 0; j < 26; j++) {
				count[j] += cur[j];
			}
		}
		ret[i] = count[labels.charAt(i) - 'a'];
		return count;
	}
	
	
	public int[] twoSum(int[] numbers, int target) {
		int l = 0, r = numbers.length - 1, sum;
		while (l <= r) {
			sum = numbers[l] + numbers[r];
			if (sum == target) {
				return new int[]{l + 1, r + 1};
			} else if (sum < target) {
				l++;
			} else {
				r--;
			}
		}
		return new int[]{};
	}
	
	//1518. 换酒问题
	public int numWaterBottles(int numBottles, int numExchange) {
		return numBottles + numWaterBottlesHelper(numBottles, numExchange);
	}
	
	private int numWaterBottlesHelper(int numBottles, int numExchange) {
		if (numBottles < numExchange) {
			return 0;
		}
		return numBottles / numExchange + numWaterBottlesHelper(
			numBottles / numExchange + numBottles % numExchange, numExchange);
	}
	
	//1444. 切披萨的方案数
	public int ways(String[] pizza, int k) {
		int high = pizza.length;
		int len = pizza[0].length();
		HashMap<Integer, Integer> mem = new HashMap<>();
		//int[][][][][] mem = new int[high][len][high][len][k+1];
		return waysHelper(pizza, mem, 0, 0, high - 1, len - 1, k);
	}
	
	private int waysHelper(String[] pizza, Map<Integer, Integer> mem, int x1, int y1, int x2,
		int y2,
		int k) {
		int key = x1 * 100000000 + y1 * 1000000 + x2 * 10000 + y2 * 100 + k;
		if (mem.containsKey(key)) {
			return mem.get(key);
		}
		int ret = 0, mod = 1000000007;
		if (k == 1) {
			for (int i = x1; i <= x2; i++) {
				int x = pizza[i].indexOf('A', y1);
				if (x <= y2 && x >= y1) {
					mem.put(key, 1);
					return 1;
				}
			}
			mem.put(key, 0);
			return 0;
		}
		for (int i = x1; i < x2; i++) {
			if (waysHelper(pizza, mem, x1, y1, i, y2, 1) > 0) {
				ret += waysHelper(pizza, mem, i + 1, y1, x2,
					y2, k - 1);
				ret %= mod;
			}
		}
		for (int i = y1; i < y2; i++) {
			if (waysHelper(pizza, mem, x1, y1, x2, i, 1) > 0) {
				ret += waysHelper(pizza, mem, x1, i + 1, x2, y2, k - 1);
				ret %= mod;
			}
		}
		mem.put(key, ret);
		return ret;
	}
	
	//818. 赛车
	public int racecar(int target) {
		int[] mem = new int[32];
		int[] min = new int[target + 1];
		int idx = 1, m = 0;
		int speed = 1, position = 0;
		while (position < target) {
			position += speed;
			m++;
			mem[idx++] = position;
			if (position <= target) {
				min[position] = m;
			}
			speed <<= 1;
		}
		if (position == target) {
			return idx - 1;
		}
		return racecarHelper(mem, target, min);
	}
	
	private int racecarHelper(int[] mem, int target, int[] min) {
		if (min[target] > 0) {
			return min[target];
		}
		int ret = Integer.MAX_VALUE;
		int idx = 0;
		while (mem[idx] < target) {
			idx++;
			if (mem[idx] < target) {
				ret = Math.min(ret, idx + 2 + racecarHelper(mem, target - mem[idx], min));
				for (int i = 0; i < idx; i++) {
					ret = Math.min(ret,
						idx + i + 2 + racecarHelper(mem, target - mem[idx] + mem[i], min));
				}
			} else if (mem[idx] > target) {
				ret = Math.min(ret, idx + 1 + racecarHelper(mem, mem[idx] - target, min));
			} else {
				ret = Math.min(ret, idx);
			}
		}
		min[target] = ret;
		return ret;
	}
	
	//798. 得分最高的最小轮调
	public int bestRotation(int[] A) {
		int len = A.length, cur;
		int[] mem = new int[len];
		for (int i = 0; i < len; i++) {
			cur = A[i];
			for (int j = len - 1, idx = i + 1; j >= cur; j--, idx++) {
				mem[idx % len]++;
			}
		}
		int ret = 0;
		for (int i = 1; i < len; i++) {
			if (mem[i] > mem[ret]) {
				ret = i;
			}
		}
		return ret;
	}
	
	//768. 最多能完成排序的块 II
	public int maxChunksToSorted(int[] arr) {
		int max = arr[0];
		int len = arr.length, x;
		int[] mem = new int[len];
		int idx = 1, j;
		mem[0] = max;
		for (int i = 1; i < len; i++) {
			x = arr[i];
			max = Math.max(max, x);
			for (j = 0; j < idx; j++) {
				if (x < mem[j]) {
					mem[j] = max;
					idx = j + 1;
					break;
				}
			}
			if (j == idx) {
				mem[idx] = max;
				idx++;
			}
		}
		return idx;
	}
	
	//1460. 通过翻转子数组使两个数组相等
	public boolean canBeEqual(int[] target, int[] arr) {
		System.out.println(
			"class:{" + this.getClass().getName() + "},method:{" + Thread.currentThread()
				.getStackTrace()[1].getMethodName() + "},param:{},error:{}");
		Arrays.sort(target);
		Arrays.sort(arr);
		int len = target.length;
		for (int i = 0; i < len; i++) {
			if (target[i] != arr[i]) {
				return false;
			}
		}
		return true;
	}
	
	//1508. 子数组和排序后的区间和
	public int rangeSum(int[] nums, int n, int left, int right) {
		try {
			int i = 1 / 0;
		} catch (Exception e) {
			System.out.println(LogUtil.getMethodName(this));
			System.out.println(
				"class:{" + this.getClass().getName() + "},method:{" + Thread.currentThread()
					.getStackTrace()[1].getMethodName() + "},param:{},error:{" + e.getMessage()
					+ "}");
		}
		int[] count = new int[100001];
		//Arrays.sort(nums);
		int[] mem = new int[n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				mem[j] += nums[i];
				count[mem[j]]++;
			}
		}
		int ret = 0, idx = 1;
		left--;
		while (right > 0) {
			while (count[idx] == 0) {
				idx++;
			}
			if (left >= count[idx]) {
				left -= count[idx];
				right -= count[idx];
			} else {
				if (left > 0) {
					count[idx] -= left;
					right -= left;
					left = 0;
				}
				if (count[idx] > right) {
					ret += right * idx;
				} else {
					ret += count[idx] * idx;
				}
				right -= count[idx];
			}
			idx++;
		}
		return ret;
	}
	
	//1418. 点菜展示表
	public List<List<String>> displayTable(List<List<String>> orders) {
		TreeMap<String, HashMap<Integer, Integer>> map = new TreeMap<>();
		HashMap<Integer, Integer> subMap;
		TreeSet<Integer> set = new TreeSet<>();
		int table;
		String food;
		for (List<String> order : orders) {
			table = Integer.parseInt(order.get(1));
			food = order.get(2);
			set.add(table);
			subMap = map.computeIfAbsent(food, k -> new HashMap<>());
			subMap.put(table, subMap.getOrDefault(table, 0) + 1);
		}
		ArrayList<List<String>> ret = new ArrayList<>();
		ArrayList<String> list = new ArrayList<>(), cur;
		list.add("Table");
		list.addAll(map.keySet());
		int size = list.size();
		ret.add(list);
		for (Integer t : set) {
			cur = new ArrayList<>();
			cur.add(t.toString());
			for (int i = 1; i < size; i++) {
				food = list.get(i);
				cur.add(String.valueOf(map.get(food).getOrDefault(t, 0)));
			}
		}
		return ret;
	}
	
	//三次操作后最大值与最小值的最小差
	public int minDifference(int[] nums) {
		int len = nums.length;
		if (len < 5) {
			return 0;
		}
		Arrays.sort(nums);
		int ret = Integer.MAX_VALUE;
		for (int i = 0; i <= 3; i++) {
			ret = Math.min(nums[len - 4 + i] - nums[i], ret);
		}
		return ret;
	}
	
	
	//1510. 石子游戏 IV
	public boolean winnerSquareGame(int n) {
		int len = (int) Math.sqrt(n);
		if (len * len == n) {
			return true;
		}
		int[] mem = new int[len + 1];
		Boolean[] ret = new Boolean[n + 1];
		for (int i = 0; i <= len; i++) {
			mem[i] = i * i;
			ret[i * i] = true;
		}
		for (int i = 1; i <= len; i++) {
			if (!winnerSquareGameHelper(ret, n - i * i, mem)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean winnerSquareGameHelper(Boolean[] ret, int n, int[] mem) {
		if (ret[n] != null) {
			return ret[n];
		}
		for (int i = 1; i * i <= n; i++) {
			if (!winnerSquareGameHelper(ret, n - i * i, mem)) {
				ret[n] = true;
				return true;
			}
		}
		ret[n] = false;
		return false;
	}
	
	//5461. 仅含 1 的子串数
	public int numSub(String s) {
		int len = s.length();
		int[] mem = new int[len + 1];
		mem[1] = 1;
		int idx = 1, count = 0, ret = 0, mod = 1000000007;
		for (int i = 0; i <= len; i++) {
			if (i < len && s.charAt(i) == '1') {
				count++;
			} else {
				while (count > idx) {
					idx++;
					mem[idx] = idx + mem[idx - 1];
					mem[idx] %= mod;
				}
				ret += mem[count];
				ret %= mod;
				count = 0;
			}
		}
		return ret;
	}
	
	public int numIdenticalPairs(int[] nums) {
		int[] mem = new int[101];
		int ret = 0;
		for (int num : nums) {
			ret += mem[num];
			mem[num]++;
		}
		return ret;
	}
	
	//1335. 工作计划的最低难度
	public int minDifficulty(int[] jobDifficulty, int d) {
		int len = jobDifficulty.length;
		if (len < d) {
			return -1;
		}
		int[][][] mem = new int[len][len][d];
		for (int i = 0; i < len; i++) {
			mem[i][i][0] = jobDifficulty[i];
			for (int j = i - 1; j >= 0; j--) {
				mem[j][i][0] = Math.max(mem[j][i - 1][0], mem[j + 1][i][0]);
			}
		}
		return minDifficultyHelper(0, len - 1, d - 1, mem);
	}
	
	private int minDifficultyHelper(int l, int r, int x, int[][][] mem) {
		if (x == 0 || mem[l][r][x] > 0) {
			return mem[l][r][x];
		}
		int ret = Integer.MAX_VALUE;
		for (int i = l; i <= r - x; i++) {
			ret = Math.min(mem[l][i][0] + minDifficultyHelper(i + 1, r, x - 1, mem), ret);
		}
		mem[l][r][x] = ret;
		return ret;
	}
	
	//129. 求根到叶子节点数字之和
	public int sumNumbers(TreeNode root) {
		if (root == null) {
			return 0;
		}
		return sumNumbersHelper(root, 0);
	}
	
	private int sumNumbersHelper(TreeNode root, int cur) {
		cur *= 10;
		cur += root.val;
		if (root.left == null && root.right == null) {
			return cur;
		}
		return (root.left != null ? sumNumbersHelper(root.left, cur) : 0)
			+ (root.right != null ? sumNumbersHelper(root.right, cur) : 0);
		
	}
	
	//330. 按要求补齐数组--存在问题n值会非常大
	public int minPatches(int[] nums, int n) {
		boolean[] mem = new boolean[n + 1];
		mem[0] = true;
		int max = 0, min = 0;
		for (int num : nums) {
			for (int i = max; i >= min; i--) {
				mem[i + num] |= mem[i];
			}
			max += num;
		}
		int ret = 0;
		for (int i = 0; i <= n; ) {
			if (!mem[i]) {
				ret++;
				for (int j = Math.min(n - i, max); j >= min; j--) {
					mem[i + j] |= mem[j];
				}
				max += i;
				min = i + i - 1;
				i += i - 1;
			} else {
				i++;
			}
		}
		return ret;
	}
	
	//354. 俄罗斯套娃信封问题
	public int maxEnvelopes(int[][] envelopes) {
		Arrays.sort(envelopes, Comparator.comparingInt(a -> a[0]));
		int len = envelopes.length, ret = 0;
		int[] mem = new int[len];
		for (int i = 0; i < len; i++) {
			if (mem[i] == 0) {
				maxEnvelopesHelper(envelopes, mem, i);
				ret = Math.max(ret, mem[i]);
			}
		}
		return ret;
	}
	
	private int maxEnvelopesHelper(int[][] envelopes, int[] mem, int i) {
		if (mem[i] > 0) {
			return mem[i];
		}
		int ret = 1, x = envelopes[i][0], y = envelopes[i][1], t = i;
		int len = envelopes.length;
		for (; i < len; i++) {
			if (envelopes[i][0] > x && envelopes[i][1] > y) {
				ret = Math.max(ret, 1 + maxEnvelopesHelper(envelopes, mem, i));
			}
		}
		mem[t] = ret;
		return ret;
	}
	
	//480. 滑动窗口中位数
	public double[] medianSlidingWindow(int[] nums, int k) {
		PriorityQueue<Integer> small = new PriorityQueue<>((a, b) -> (b - a));
		PriorityQueue<Integer> big = new PriorityQueue<>();
		int len = nums.length, i;
		for (i = 0; i < k; i++) {
			medianSlidingWindowAdd(small, big, nums[i]);
		}
		double[] ret = new double[len - k + 1];
		for (; i < len; i++) {
			ret[i - k] = ((k & 1) == 0) ? (double) (small.peek() + big.peek()) / 2 : small.peek();
			medianSlidingWindowRemove(small, big, nums[i - k]);
			medianSlidingWindowAdd(small, big, nums[i]);
		}
		ret[len - k] = ((k & 1) == 0) ? (double) (small.peek() + big.peek()) / 2 : small.peek();
		return ret;
	}
	
	private void medianSlidingWindowRemove(PriorityQueue<Integer> small, PriorityQueue<Integer> big,
		int num) {
		if (small.isEmpty() || num > small.peek()) {
			big.remove(num);
		} else {
			small.remove(num);
		}
	}
	
	private void medianSlidingWindowAdd(PriorityQueue<Integer> small, PriorityQueue<Integer> big,
		int num) {
		if (small.isEmpty() || num < small.peek()) {
			small.add(num);
		} else {
			big.add(num);
		}
		while (small.size() > big.size() + 1) {
			big.add(small.poll());
		}
		while (big.size() > small.size()) {
			small.add(big.poll());
		}
	}
	
	//1092. 最短公共超序列---有问题"aabbabaa","aabbbbbbaa"
	public String shortestCommonSupersequence(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int[] mem2 = new int[len2];
		int[] mem1 = new int[len1];
		char[] arr2 = str2.toCharArray();
		char[] arr1 = str1.toCharArray();
		char c;
		for (int i = 0; i < len1; i++) {
			c = arr1[i];
			for (int j = 0; j < len2 && j + mem2[j] < len2; j++) {
				if (arr2[j + mem2[j]] == c) {
					mem2[j]++;
				}
			}
		}
		int max2 = 0;
		for (int i = 0; i < len2; i++) {
			if (mem2[i] > mem2[max2]) {
				max2 = i;
			}
		}
		for (int i = 0; i < len2; i++) {
			c = arr2[i];
			for (int j = 0; j < len1 && j + mem1[j] < len1; j++) {
				if (arr1[j + mem1[j]] == c) {
					mem1[j]++;
				}
			}
		}
		int max1 = 0;
		for (int i = 0; i < len1; i++) {
			if (mem1[i] > mem1[max1]) {
				max1 = i;
			}
		}
		String a = str1.substring(0, max1) + str2 + str1.substring(max1 + mem1[max1], len1);
		System.out.println(a);
		String b = str2.substring(0, max2) + str1 + str2.substring(max2 + mem2[max2], len2);
		System.out.println(b);
		if (mem1[max1] < mem2[max2]) {
			return a;
		} else {
			return b;
		}
	}
	
	//1425. 带限制的子序列和
	public int constrainedSubsetSum(int[] nums, int k) {
		LinkedList<Integer> list = new LinkedList<>();
		int len = nums.length;
		int[] max = new int[len];
		int ret = Integer.MIN_VALUE, cur;
		for (int i = 0; i < len; i++) {
			while (!list.isEmpty() && i - list.getFirst() > k) {
				list.removeFirst();
			}
			cur = nums[i] + (list.isEmpty() ? 0 : max[list.getFirst()]);
			ret = Math.max(cur, ret);
			if (cur > 0) {
				while (!list.isEmpty() && max[list.getLast()] <= cur) {
					list.removeLast();
				}
				list.add(i);
				max[i] = cur;
			}
		}
		return ret;
	}
	
	//1383. 最大的团队表现值
	public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
		int[][] mem = new int[n][2];
		for (int i = 0; i < n; i++) {
			mem[i][0] = efficiency[i];
			mem[i][1] = speed[i];
		}
		Arrays.sort(mem, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		long curE = mem[n - 1][0], sumSpeed = 0, ret = 0;
		for (int i = n - 1; i >= 0; i--) {
			queue.add(mem[i][1]);
			sumSpeed += mem[i][1];
			if (queue.size() > k) {
				Integer x = queue.poll();
				if (x != mem[i][1]) {
					curE = mem[i][0];
				}
				sumSpeed -= x;
			} else {
				curE = mem[i][0];
			}
			System.out.println(curE * sumSpeed + "---" + ret);
			ret = Math.max(curE * sumSpeed, ret);
		}
		return (int) (ret % mod);
	}
	
	//685. 冗余连接 II
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int len = edges.length, t;
		int[] mem = new int[len + 1];
		int[] ret = null, circles = null;
		for (int[] edge : edges) {
			if (mem[edge[1]] > 0) {
				//节点有两个父节点,记录后出现的边
				ret = edge;
				continue;
			}
			if (mem[edge[0]] == edge[1]) {
				circles = edge;//最后入环边
			}
			t = edge[0];
			while (mem[t] > 0) {
				if (circles != null && (t == circles[0] || t == circles[1])) {
					break;
				}
				t = mem[t];
			}
			if (edge[1] == t) {
				circles = edge;//最后入环边
			}
			mem[edge[1]] = t;
		}
		if (circles == null) {
			return ret;//无环多父节点
		} else if (ret == null) {
			return circles;//有环无多父节点
		} else {
			//有环有多个父节点
			for (int[] edge : edges) {
				if (edge[1] == ret[1]) {
					return edge;
				}
			}
		}
		return null;
	}
	
	//1499. 满足不等式的最大值
	public int findMaxValueOfEquation(int[][] points, int k) {
		LinkedList<Integer> list = new LinkedList<>();
		int len = points.length;
		int ret = Integer.MIN_VALUE, x, t;
		for (int i = 0; i < len; i++) {
			
			while (!list.isEmpty() && points[i][0] - points[list.getFirst()][0] > k) {
				list.removeFirst();
			}
			if (!list.isEmpty()) {
				t = list.getFirst();
				ret = Math.max(points[i][0] + points[i][1] + points[t][1] - points[t][0], ret);
			}
			x = -points[i][0] + points[i][1];
			while (!list.isEmpty()) {
				t = list.getLast();
				if (points[t][1] - points[t][0] < x) {
					list.removeLast();
				} else {
					break;
				}
			}
			list.add(i);
		}
		return ret;
	}
	
	//718. 最长重复子数组
	public int findLength(int[] A, int[] B) {
		int alen = A.length;
		int blen = B.length;
		int[][] mem = new int[alen][blen];
		int ret = 0;
		for (int i = 0; i < alen; i++) {
			for (int j = 0; j < blen; j++) {
				if (A[i] == B[j]) {
					mem[i][j] = 1 + (i == 0 || j == 0 ? 0 : mem[i - 1][j - 1]);
					ret = Math.max(ret, mem[i][j]);
				}
			}
		}
		return ret;
	}
	
	//773. 滑动谜题--BFS
	public int slidingPuzzle1(int[][] board) {
		int target = 1234505;
		int x = 0, idx = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				x *= 10;
				x += board[i][j];
				if (board[i][j] == 0) {
					idx = i * 3 + j;
				}
			}
		}
		x *= 10;
		x += idx;
		if (x == target) {
			return 0;
		}
		int ret = 0;
		HashSet<Integer> set = new HashSet<>();
		ArrayList<Integer> cur = new ArrayList<>(), next;
		int[][] pat = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
		cur.add(x);
		set.add(x);
		while (!cur.isEmpty()) {
			next = new ArrayList<>();
			for (Integer t : cur) {
				if (t == target) {
					return ret;
				}
				int idx0 = t % 10;
				int m0 = (int) Math.pow(10, 5 - idx0), mi;
				t /= 10;
				int val, k;
				for (int i : pat[idx0]) {
					val = t;
					mi = (int) Math.pow(10, 6 - i);
					val %= mi;
					mi /= 10;
					val /= mi;
					k = (t + val * (m0 - mi)) * 10 + i;
					if (set.add(k)) {
						next.add(k);
					}
				}
			}
			cur = next;
			ret++;
		}
		return -1;
	}
	
	//773. 滑动谜题--DFS
	public int slidingPuzzle(int[][] board) {
		int[] mem = new int[600000];
		Arrays.fill(mem, Integer.MAX_VALUE);
		int target = 123450;
		int cur = 0, idx = 0;
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				cur *= 10;
				cur += board[i][j];
				if (board[i][j] == 0) {
					idx = i * 3 + j;
				}
				
			}
		}
		
		slidingPuzzleHelper(mem, cur, idx, 0);
		return mem[target];
	}
	
	private void slidingPuzzleHelper(int[] mem, int cur, int idx, int count) {
		if (mem[cur] <= count) {
			return;
		}
		mem[cur] = count;
		int[][] pat = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};
		mem[cur] = -1;
		int val;
		int m0 = (int) Math.pow(10, 5 - idx), me;
		for (int ex : pat[idx]) {
			val = cur;
			me = (int) Math.pow(10, 6 - ex);
			val %= me;
			me /= 10;
			val /= me;
			slidingPuzzleHelper(mem, cur + val * (m0 - me), ex, count + 1);
		}
	}
	
	//剑指 Offer 52. 两个链表的第一个公共节点
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		ListNode a = headA;
		ListNode b = headB;
		int al = 0, bl = 0;
		while (a != null) {
			al++;
			a = a.next;
		}
		while (b != null) {
			bl++;
			b = b.next;
		}
		a = headA;
		b = headB;
		while (al > bl) {
			al--;
			a = a.next;
		}
		while (al < bl) {
			bl--;
			b = b.next;
		}
		while (a != null) {
			if (a == b) {
				return a;
			}
			a = a.next;
			b = b.next;
		}
		return null;
	}
	
	//面试题 17.11. 单词距离
	public int findClosest(String[] words, String word1, String word2) {
		int len = words.length;
		int a = -1, b = -1, ret = Integer.MAX_VALUE;
		String s;
		for (int i = 0; i < len; i++) {
			s = words[i];
			if (word1.equals(s)) {
				a = i;
				if (b >= 0) {
					ret = Math.min(ret, a - b);
				}
			} else if (word2.equals(s)) {
				b = i;
				if (a >= 0) {
					ret = Math.min(ret, b - a);
				}
			}
		}
		return ret;
	}
	
	//1383. 最大的团队表现值---理解错误是小于等于k个人,不是等于k个人
	public int maxPerformance1(int n, int[] speed, int[] efficiency, int k) {
		int[][] mem = new int[n][2];
		for (int i = 0; i < n; i++) {
			mem[i][0] = efficiency[i];
			mem[i][1] = speed[i];
		}
		Arrays.sort(mem, (a, b) -> (a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]));
		int[] speedCount = new int[100001];
		for (int i : speed) {
			speedCount[i]++;
		}
		int idx = 100000;
		long speedSum = 0, ret = 0;
		while (k > 1) {
			while (speedCount[idx] == 0) {
				idx--;
			}
			speedSum += idx;
			speedCount[idx]--;
			k--;
		}
		int e, s;
		for (int i = 0; i < n; i++) {
			e = mem[i][0];
			s = mem[i][1];
			if (speedCount[s] == 0) {
				speedSum -= s;
				while (idx >= 0 && speedCount[idx] == 0) {
					idx--;
				}
				if (idx < 0) {
					break;
				}
				speedCount[idx]--;
				speedSum += idx;
			} else {
				speedCount[s]--;
			}
			ret = Math.max(ret, (speedSum + s) * e % mod);
		}
		return (int) ret;
	}
	
	//1497. 检查数组对是否可以被 k 整除
	public boolean canArrange(int[] arr, int k) {
		int[] mem = new int[k];
		int t;
		for (int i : arr) {
			t = i % k;
			if (t < 0) {
				t += k;
			}
			mem[t]++;
		}
		if ((mem[0] & 1) != 0) {
			return false;
		}
		int l = 1, r = k - 1;
		while (l < r) {
			if (mem[l] != mem[r]) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
	
	//1496. 判断路径是否相交
	public boolean isPathCrossing(String path) {
		int x = 10000, y = 10000;
		int len = path.length();
		char c;
		HashSet<Integer> set = new HashSet<>();
		set.add(x * 100000 + y);
		for (int i = 0; i < len; i++) {
			c = path.charAt(i);
			switch (c) {
				case 'N':
					x++;
					break;
				case 'S':
					x--;
					break;
				case 'E':
					y++;
					break;
				case 'W':
					y--;
					break;
			}
			if (!set.add(x * 100000 + y)) {
				return true;
			}
		}
		return false;
	}
	
	//5434. 删掉一个元素以后全为 1 的最长子数组
	public int longestSubarray(int[] nums) {
		int len = nums.length;
		int max = 0, cur = 0, bIdx = -1, i;
		for (i = 0; i < len; i++) {
			if (nums[i] == 1) {
				cur++;
			} else if (bIdx < 0) {
				bIdx = i;
			} else {
				max = Math.max(max, cur);
				cur = i - bIdx - 1;
				bIdx = i;
			}
		}
		max = Math.max(max, cur);
		return max;
	}
	
	public int kthFactor(int n, int k) {
		int[] meml = new int[k];
		int[] memr = new int[k];
		int idx = 0, i;
		for (i = 1; i * i < n; i++) {
			if ((n % i) == 0) {
				meml[idx] = i;
				memr[idx] = n / i;
				idx++;
				if (idx == k) {
					return i;
				}
			}
		}
		int c = idx;
		if (i * i == n) {
			if (idx == k - 1) {
				return i;
			}
			c++;
		}
		idx--;
		while (idx >= 0) {
			c++;
			if (c == k) {
				return memr[idx];
			}
			idx--;
		}
		return -1;
	}
	
	//5432. 去掉最低工资和最高工资后的工资平均值
	public double average(int[] salary) {
		int len = salary.length;
		int min = salary[0], max = min, sum = 0;
		for (int i : salary) {
			if (i > max) {
				max = i;
			} else if (i < min) {
				min = i;
			}
			sum += i;
		}
		return (double) (sum - min - max) / (len - 2);
	}
	
	//689. 三个无重叠子数组的最大和
	public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
		int len = nums.length;
		int[] mem = new int[len + 1];
		for (int i = 1; i <= len; i++) {
			mem[i] = mem[i - 1] + nums[i - 1];
		}
		int[][] ret = new int[len + 1][5];
		int max = Integer.MIN_VALUE;
		int[] result = null;
		for (int i = k; i <= len; i++) {
			int cur = mem[i] - mem[i - k];
			ret[i] = Arrays.copyOf(ret[i - 1], 5);
			if (cur > ret[i - 1][0]) {
				ret[i][0] = cur;
				ret[i][1] = i - k;
			}
			if (i >= 2 * k && cur + ret[i - k][0] > ret[i - 1][2]) {
				ret[i][2] = cur + ret[i - k][0];
				ret[i][3] = ret[i - k][1];
				ret[i][4] = i - k;
			}
			if (i >= 3 * k && cur + ret[i - k][2] > max) {
				result = new int[]{ret[i - k][3], ret[i - k][4], i - k};
				max = cur + ret[i - k][2];
			}
		}
		return result;
	}
	
	//面试题 17.08. 马戏团人塔
	public int bestSeqAtIndex(int[] height, int[] weight) {
		int len = height.length;
		int[][] mem = new int[len][2];
		for (int i = 0; i < len; i++) {
			mem[i][0] = height[i];
			mem[i][1] = weight[i];
		}
		Arrays.sort(mem, (a, b) -> (a[1] != b[1] ? a[1] - b[1] : a[0] - b[0]));
		int[][] ret = new int[len][2];
		ret[0][0] = 1;
		ret[0][1] = -1;
		int b, max, mb;
		for (int i = 1; i < len; i++) {
			b = -1;
			max = 0;
			mb = -1;
			for (int j = i - 1; j > b; j--) {
				if (mem[j][0] < mem[i][0] && mem[j][1] < mem[i][1]) {
					if (ret[j][0] > max) {
						max = ret[j][0];
						mb = j;
						b = ret[j][1];
					}
				}
			}
			ret[i][0] = max + 1;
			ret[i][1] = mb;
		}
		return ret[len - 1][0];
	}
	
	//464. 我能赢吗
	public boolean canIWin1(int maxChoosableInteger, int desiredTotal) {
		if (maxChoosableInteger * (maxChoosableInteger + 1) < 2 * desiredTotal) {
			return false;
		}
		HashMap<Integer, Boolean> map = new HashMap<>();
		return desiredTotal <= maxChoosableInteger || canIWin1Helper(maxChoosableInteger, 0,
			desiredTotal, map);
	}
	
	private boolean canIWin1Helper(int maxChoosableInteger, int mask, int desiredTotal,
		HashMap<Integer, Boolean> map) {
		if (desiredTotal <= 0) {
			return false;
		} else if (map.containsKey(mask)) {
			return map.get(mask);
		}
		int cur = maxChoosableInteger;
		while (cur > 0) {
			if ((mask & (1 << cur)) == 0) {
				if (!canIWin1Helper(maxChoosableInteger, mask + (1 << cur), desiredTotal - cur,
					map)) {
					map.put(mask, true);
					return true;
				}
			}
			cur--;
		}
		map.put(mask, false);
		return false;
	}
	
	
	public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
		if (maxChoosableInteger * (maxChoosableInteger + 1) < 2 * desiredTotal) {
			return false;
		}
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		return desiredTotal == 0 || (!canIWinHelper(maxChoosableInteger, 0, desiredTotal, map));
	}
	
	private boolean canIWinHelper(int maxChoosableInteger, int mask, int desiredTotal,
		HashMap<Integer, Set<Integer>> map) {
		if (desiredTotal <= 0) {
			return true;
		} else {
			Set<Integer> set = map.get(desiredTotal);
			if (set != null && set.contains(mask)) {
				return true;
			}
		}
		int cur = maxChoosableInteger;
		while (cur > 0) {
			if ((mask & (1 << cur)) == 0) {
				if (canIWinHelper(maxChoosableInteger, mask + (1 << cur), desiredTotal - cur,
					map)) {
					return false;
				}
			}
			cur--;
		}
		map.computeIfAbsent(desiredTotal, k -> new HashSet<>()).add(mask);
		return true;
	}
	
	public int minSubArrayLen(int s, int[] nums) {
		int sum = 0, l = 0, r = 0, ret = Integer.MAX_VALUE;
		int len = nums.length;
		while (r < len || sum >= s) {
			if (sum < s) {
				sum += nums[r++];
			} else {
				ret = Math.min(ret, r - l);
				sum -= nums[l++];
			}
		}
		return ret == Integer.MAX_VALUE ? 0 : ret;
	}
	
	//336. 回文对
	public List<List<Integer>> palindromePairs(String[] words) {
		HashMap<String, Integer> map = new HashMap<>();
		int len = words.length;
		for (int i = 0; i < len; i++) {
			map.put(words[i], i);
		}
		StringBuffer buffer = new StringBuffer();
		String word, s;
		ArrayList<List<Integer>> ret = new ArrayList<>();
		for (Integer i = 0; i < len; i++) {
			word = words[i];
			int length = word.length();
			if (length == 0) {
				for (int j = 0; j < len; j++) {
					if (i != j && palindromePairsHelper(words[j], 0, words[j].length() - 1)) {
						ret.add(Arrays.asList(j, i));
						ret.add(Arrays.asList(i, j));
					}
				}
				continue;
			}
			buffer.setLength(0);
			buffer.append(word);
			buffer.reverse();
			s = buffer.toString();
			if (map.containsKey(s) && !s.equals(word)) {
				ret.add(Arrays.asList(i, map.get(s)));
			}
			for (int j = 0; j < length - 1; j++) {
				if (palindromePairsHelper(word, 0, j)) {
					buffer.setLength(0);
					buffer.append(word.substring(j + 1));
					buffer.reverse();
					s = buffer.toString();
					if (map.containsKey(s)) {
						ret.add(Arrays.asList(map.get(s), i));
					}
				}
				if (palindromePairsHelper(word, j + 1, length - 1)) {
					buffer.setLength(0);
					buffer.append(word, 0, j + 1);
					buffer.reverse();
					s = buffer.toString();
					if (map.containsKey(s)) {
						ret.add(Arrays.asList(i, map.get(s)));
					}
				}
			}
		}
		return ret;
	}
	
	private boolean palindromePairsHelper(String word, int l, int r) {
		while (l < r) {
			if (word.charAt(l) != word.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}
	
	public int[] shortestSeq(int[] big, int[] small) {
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i : small) {
			map.put(i, -1);
		}
		int len = big.length;
		int retl = 0, retr = len + 1;
		int l = 0, cur, rest = small.length;
		Integer c;
		for (int i = 0; i < len; i++) {
			cur = big[i];
			c = map.get(cur);
			if (c != null) {
				if (c < l) {
					rest--;
				}
				map.put(cur, i);
				if (rest == 0) {
					int min = len + 1;
					for (Integer t : map.values()) {
						min = Math.min(min, t);
					}
					l = min;
					if (i - l < retr - retl) {
						retl = l;
						retr = i;
					}
					l++;
					rest++;
				}
			}
		}
		int[] ret = new int[retr - retl + 1];
		for (int i = retl, j = 0; i <= retr; j++, i++) {
			ret[j] = big[i];
		}
		return ret;
	}
	
	//剑指 Offer 49. 丑数
	public int nthUglyNumber(int n) {
		int[] mem = new int[n];
		mem[0] = 1;
		int x2 = 2, x3 = 3, x5 = 5;
		int i2 = 0, i3 = 0, i5 = 0;
		int cur;
		for (int i = 1; i < n; i++) {
			cur = Math.min(x2, Math.min(x3, x5));
			mem[i] = cur;
			while (x2 <= cur) {
				i2++;
				x2 = 2 * mem[i2];
			}
			while (x3 <= cur) {
				i3++;
				x3 = 3 * mem[i3];
			}
			while (x5 <= cur) {
				i5++;
				x5 = 5 * mem[i5];
			}
		}
		return mem[n - 1];
	}
	
	//剑指 Offer 35. 复杂链表的复制
	class Node {
		
		int val;
		Node next;
		Node random;
		
		public Node(int val) {
			this.val = val;
			this.next = null;
			this.random = null;
		}
	}
	
	public Node copyRandomList(Node head) {
		Node cur = head;
		while (cur != null) {
			Node node = new Node(cur.val);
			Node next = cur.next;
			cur.next = node;
			node.next = next;
			cur = next;
		}
		cur = head;
		while (cur != null) {
			Node random = cur.random;
			Node next = cur.next;
			next.random = random == null ? null : random.next;
			cur = cur.next.next;
		}
		Node ret = new Node(0), node;
		cur = head;
		node = ret;
		while (cur != null) {
			Node next = cur.next;
			cur.next = next.next;
			node.next = next;
			node = next;
			cur = cur.next;
		}
		return ret.next;
	}
	
	int pre = 0, in = 0;
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {
		return buildTreeHelper(preorder, inorder, Integer.MAX_VALUE);
	}
	
	private TreeNode buildTreeHelper(int[] preorder, int[] inorder, int rootVal) {
		if (preorder.length == pre || inorder[in] == rootVal) {
			return null;
		}
		int val = preorder[pre++];
		TreeNode root = new TreeNode(val);
		root.left = buildTreeHelper(preorder, inorder, val);
		in++;
		root.left = buildTreeHelper(preorder, inorder, rootVal);
		return root;
	}
	
	//面试题15- II. 剪绳子
	public int cuttingRope1(int n) {
		if (n <= 4) {
			return n == 4 ? 4 : n - 1;
		}
		long ret = 1, mod = 1000000007;
		while (n > 4) {
			ret *= 3;
			ret %= mod;
			n -= 4;
		}
		return (int) (ret * n % mod);
	}
	
	//面试题14- I. 剪绳子
	public int cuttingRope(int n) {
		int[] mem = new int[n + 1];
		mem[2] = 1;
		mem[1] = 1;
		int ret = cuttingRopeHelper(n, mem);
		return ret;
	}
	
	private int cuttingRopeHelper(int n, int[] mem) {
		if (mem[n] > 0) {
			return mem[n];
		}
		int ret = (n / 2) * (n - n / 2);
		for (int i = 1; i <= n / 2; i++) {
			ret = Math.max(ret, i * cuttingRopeHelper(n - i, mem));
		}
		mem[n] = ret;
		return ret;
	}
	
	class Tire {
		
		ArrayList<Integer> list;
		Tire[] child;
		
		public Tire() {
			this.list = new ArrayList<>();
			this.child = new Tire[26];
		}
	}
	
	public int[][] multiSearch(String big, String[] smalls) {
		Tire root = new Tire();
		char[] arr = big.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			insertTire(root, arr, i, i);
		}
		int len = smalls.length;
		int[][] ret = new int[len][];
		int idx = 0;
		for (String small : smalls) {
			ret[idx++] = searchTire(root, small, 0);
		}
		return ret;
	}
	
	private int[] searchTire(Tire root, String s, int i) {
		if (root == null) {
			return new int[0];
		} else if (i == s.length()) {
			int size = root.list.size();
			int[] ret = new int[size];
			for (int j = 0; j < size; j++) {
				ret[j] = root.list.get(j);
			}
			return ret;
		}
		return searchTire(root.child[s.charAt(i) - 'a'], s, i + 1);
	}
	
	private void insertTire(Tire root, char[] arr, int i, int x) {
		if (i == arr.length) {
			return;
		}
		if (root.child[arr[i] - 'a'] == null) {
			root.child[arr[i] - 'a'] = new Tire();
		}
		root.child[arr[i] - 'a'].list.add(x);
		insertTire(root.child[arr[i] - 'a'], arr, i + 1, x);
	}
	
	//面试题63. 股票的最大利润
	public int maxProfit(int[] prices) {
		int min = prices[0];
		int ret = 0;
		for (int price : prices) {
			ret = Math.max(ret, price - min);
			min = Math.min(min, price);
		}
		return ret;
	}
	
	//面试题06. 从尾到头打印链表
	public int[] reversePrint(ListNode head) {
		int[] ints = new int[10001];
		int idx = 10000;
		while (head != null) {
			ints[idx] = head.val;
			idx++;
			head = head.next;
		}
		int[] ret = new int[10000 - idx];
		for (int i = idx + 1, j = 1; i < 10001; i++, j++) {
			ret[j] = ints[i];
		}
		return ret;
	}
	
	//面试题32 - II. 从上到下打印二叉树 II
	public List<List<Integer>> levelOrder(TreeNode root) {
		ArrayList<List<Integer>> ret = new ArrayList<>();
		levelOrderHelper(ret, root, 0);
		return ret;
	}
	
	private void levelOrderHelper(ArrayList<List<Integer>> ret, TreeNode root, int level) {
		if (root == null) {
			return;
		}
		if (ret.size() == level) {
			ret.add(new ArrayList<>());
		}
		ret.get(level).add(root.val);
		levelOrderHelper(ret, root.left, level + 1);
		levelOrderHelper(ret, root.right, level + 1);
	}
	
	//面试题39. 数组中出现次数超过一半的数字
	public int majorityElement(int[] nums) {
		int ret = nums[0], count = 0;
		for (int num : nums) {
			if (num == ret) {
				count++;
			} else {
				if (count > 0) {
					count--;
				} else {
					ret = num;
					count = 1;
				}
			}
		}
		return ret;
	}
	
	//1028. 从先序遍历还原二叉树
	int idx = 0;
	int level = 0;
	
	public TreeNode recoverFromPreorder(String S) {
		return recoverFromPreorderHelper(S, 0);
	}
	
	private TreeNode recoverFromPreorderHelper(String s, int curLevel) {
		if (level != curLevel || idx == s.length()) {
			return null;
		}
		int val = 0;
		while (idx < s.length() && s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
			val *= 10;
			val += s.charAt(idx) - '0';
			idx++;
		}
		TreeNode ret = new TreeNode(val);
		level = 0;
		while (idx < s.length() && s.charAt(idx) == '-') {
			level++;
			idx++;
		}
		ret.left = recoverFromPreorderHelper(s, curLevel + 1);
		ret.right = recoverFromPreorderHelper(s, curLevel + 1);
		return ret;
	}
	
	//829. 连续整数求和
	public int consecutiveNumbersSum(int N) {
		int ret = 0;
		int sum = 0, count = 1;
		while (N > sum) {
			sum += count;
			if ((N - sum) % count == 0) {
				ret++;
			}
			count++;
		}
		return ret;
	}
	
	//1377. T 秒后青蛙的位置
	public double frogPosition(int n, int[][] edges, int t, int target) {
		double[] mem = new double[n + 1];
		List<Integer>[] map = new List[n];
		for (int i = 0; i <= n; i++) {
			map[i] = new ArrayList<>();
		}
		for (int[] edge : edges) {
			map[edge[0]].add(edge[1]);
			map[edge[1]].add(edge[0]);
		}
		mem[1] = 1;
		ArrayList<Integer> cur = new ArrayList<>(), next;
		cur.add(1);
		int count;
		while (t > 0 && !cur.isEmpty()) {
			next = new ArrayList<>();
			for (Integer x : cur) {
				count = 0;
				double rate = mem[x];
				for (Integer c : map[x]) {
					if (mem[c] == 0) {
						count++;
						next.add(c);
					}
				}
				if (count > 0) {
					mem[x] = -1;
					rate /= count;
					int len = next.size() - 1;
					while (count > 0) {
						mem[next.get(len - count)] = rate;
						count--;
					}
				}
			}
			cur = next;
			t--;
		}
		return mem[target] > 0 ? mem[target] : 0;
	}
	
	//1379. 找出克隆二叉树中的相同节点
	public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned,
		final TreeNode target) {
		if (original == null) {
			return null;
		}
		if (original == target) {
			return cloned;
		}
		TreeNode targetCopy = getTargetCopy(original.left, cloned.left, target);
		if (targetCopy != null) {
			return targetCopy;
		}
		return getTargetCopy(original.right, cloned.right, target);
	}
	
	//1014. 最佳观光组合
	public int findIntegers1(int num) {
		int[] mem = new int[40];
		mem[0] = 2;
		mem[1] = 3;
		for (int i = 2; (1 << i) <= num; i++) {
			mem[i] = mem[i - 1] + mem[i - 2];
		}
		int ret = findIntegers1Helper(num, mem);
		return ret;
	}
	
	private int findIntegers1Helper(int num, int[] mem) {
		if (num == 0) {
			return 1;
		}
		int ret = 0, x = 1, mask = 0, base = 2;
		int i = 0;
		while (x <= num) {
			mask <<= 1;
			base <<= 1;
			mask++;
			x <<= 1;
			if ((i & 1) != 0) {
				x++;
			}
			i++;
		}
		System.out.println("n" + Integer.toBinaryString(num));
		System.out.println("x" + Integer.toBinaryString(x));
		System.out.println("b" + Integer.toBinaryString(base));
		System.out.println("m" + Integer.toBinaryString(mask));
		ret = mem[i - 1];
		if ((num & (base)) > 0) {
			ret += findIntegers1Helper((num & mask), mem);
		} else if ((num & x) > (base >> 1)) {
			ret += findIntegers1Helper((num & mask), mem);
		}
		return ret;
	}
	
	//不含连续1的非负整数---超时
	public int findIntegers(int num) {
		int ret = 1;
		for (int i = 0; i < 32; i++) {
			int x = findIntegersHelper(num, 1, i, true);
			if (x == 0) {
				break;
			}
			ret += x;
		}
		return ret;
	}
	
	private int findIntegersHelper(int num, int i, int c, boolean flag) {
		int ret = 0;
		if (c == 0 && i <= num) {
			ret++;
		} else if (i > num) {
			return ret;
		}
		if (c > 0 && !flag) {
			ret += findIntegersHelper(num, (i << 1) + 1, c - 1, true);
		}
		ret += findIntegersHelper(num, i << 1, c, false);
		return ret;
	}
	
	public int maxScoreSightseeingPair(int[] A) {
		int max = A[0];
		int len = A.length, ret = 0;
		for (int i = 1; i < len; i++) {
			ret = Math.max(ret, max + A[i] - i);
			max = Math.max(max, A[i] + i);
		}
		return ret;
	}
	//621. 任务调度器
	
	public int leastInterval(char[] tasks, int n) {
		int total = tasks.length, ret = 0;
		int[][] count = new int[26][2];
		for (char task : tasks) {
			count[task - 'A'][0]++;
		}
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
		for (int i = 0; i < 26; i++) {
			if (count[i][0] > 0) {
				queue.add(count[i]);
			}
		}
		LinkedList<int[]> list = new LinkedList<>();
		while (total > 0) {
			ret++;
			if (!queue.isEmpty()) {
				int[] poll = queue.poll();
				poll[0]--;
				if (poll[0] > 0) {
					poll[1] = ret + n;
					list.add(poll);
				}
				total--;
			} else {
				int[] next = list.removeFirst();
				queue.add(next);
				ret = next[1];
			}
			if (!list.isEmpty() && list.getFirst()[1] == ret) {
				queue.add(list.removeFirst());
			}
		}
		return ret;
	}
	
	
	//1342. 将数字变成 0 的操作次数
	public int numberOfSteps(int num) {
		int ret = 0;
		while (num > 1) {
			if ((num & 1) == 1) {
				ret++;
			}
			num >>= 1;
			ret++;
		}
		if (num > 0) {
			ret++;
		}
		return ret;
	}
	
	//1337. 方阵中战斗力最弱的 K 行
	public int[] kWeakestRows(int[][] mat, int k) {
		int len = mat.length;
		int[] mem = new int[len];
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum = i;
			for (int x : mat[i]) {
				sum += x * 1000;
			}
			mem[i] = sum;
		}
		Arrays.sort(mem);
		int[] ret = new int[k];
		for (int i = 0; i < k; i++) {
			ret[i] = mem[i] % 1000;
		}
		return ret;
	}
	
	//1328. 破坏回文串
	public String breakPalindrome(String palindrome) {
		int len = palindrome.length();
		if (len < 2) {
			return "";
		}
		char[] chars = palindrome.toCharArray();
		int i = 0;
		for (; i < len / 2; i++) {
			if (chars[i] != 'a') {
				chars[i] = 'a';
				break;
			}
		}
		if (i == len / 2) {
			chars[len - 1] = 'b';
		}
		return new String(chars);
	}
	
	//1478. 安排邮筒
	public int minDistance(int[] houses, int k) {
		int len = houses.length;
		if (len <= k) {
			return 0;
		}
		Arrays.sort(houses);
		int[][] mem = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				mem[i][j] = minDistanceHelper(houses, i, j);
			}
		}
		int[] cur = mem[0], next;
		for (int i = 1; i < k; i++) {
			next = Arrays.copyOf(cur, len);
			next[i] = 0;
			for (int j = i + 1; j < len; j++) {
				for (int l = i; l <= j; l++) {
					next[j] = Math.min(next[j], cur[l - 1] + mem[l][j]);
				}
			}
			cur = next;
		}
		return cur[len - 1];
	}
	
	private int minDistanceHelper(int[] houses, int l, int r) {
		int ret = 0;
		while (l < r) {
			ret += houses[r] - houses[l];
			l++;
			r--;
		}
		return ret;
	}
}
