package com.leetcode2;

import static com.base.Constant.ds_4;

import com.base.TreeNode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Author: WuMing
 * @CreateDate: 2020/8/31 15:20
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test7 {
	
	public static void main(String[] args) {
		Test7 test = new Test7();
		TreeNode root = new TreeNode(1);
		root.right=new TreeNode(2);
		root.right.left=new TreeNode(2);
		test.paintingPlan(6,27);
	}
	//LCP 22. 黑白方格画
	public int paintingPlan(int n, int k) {
		if(k<n){
			return 0;
		}else if(k==n*n){
			return 1;
		}else if(k==n){
			return 2*n;
		}
		int[][] mem = new int[n + 1][n + 1];
		for (int i = 1; i <=n ; i++) {
			mem[i][i] = 1;
			mem[i][1] = i;
			for (int j = 2; j < i; j++) {
				mem[i][j] = mem[i - 1][j] * i / (i - j);
			}
		}
		int ret=0;
		if(k%n==0){
			ret+= 2*mem[n][k/n];
		}
		for (int i = 1; i < n ; i++) {
			for (int j = 1; j < n; j++) {
				if(i*n+j*(n-i)==k){
					ret+=mem[n][i]*mem[n][j];
				}
			}
		}
		return ret;
	}
	//501. 二叉搜索树中的众数
	LinkedList<Integer> findMode=new LinkedList<>();
	public int[] findMode(TreeNode root) {
		findMode.add(0);
		int[] right=findModeHelper(root,root.val);
		if(right[1]>findMode.get(0)){
			findMode.clear();
			findMode.add(right[1]);
			findMode.add(right[0]);
		}else if(right[1] == findMode.get(0)){
			findMode.add(right[0]);
		}
		findMode.removeFirst();
		int size = findMode.size();
		int[] ret = new int[size];
		for (int i = 0; i <size ; i++) {
			ret[i]=findMode.get(i);
		}
		return ret;
	}
	
	private int[] findModeHelper(TreeNode root, int val) {
		int[] ret = new int[]{val,0};
		if(root==null){
			return ret;
		}
		if(root.val!=val){
			ret[0]=root.val;
		}
		ret[1]=1;
		int[] left = findModeHelper(root.left, val);
		if(left[0]==ret[0]){
			ret[1]+=left[1];
		}else if(left[1]>findMode.get(0)){
			findMode.clear();
			findMode.add(left[1]);
			findMode.add(left[0]);
		}else if(left[1]==findMode.get(0)){
			findMode.add(left[0]);
		}
		int[] right = findModeHelper(root.right, val);
		if(right[0] == ret[0]){
			ret[1]+=right[1];
		}else if(right[1]>findMode.get(0)){
			findMode.clear();
			findMode.add(right[1]);
			findMode.add(right[0]);
		}else if(right[1] == findMode.get(0)){
			findMode.add(right[0]);
		}
		
		return ret;
	}
	
	public int breakfastNumber(int[] staple, int[] drinks, int x) {
		Arrays.sort(staple);
		Arrays.sort(drinks);
		int s = staple.length - 1, d = 0;
		int len = drinks.length;
		long ret=0,mod=1000000007;
		while (s>=0){
			while (d < len && drinks[d] + staple[s] <= x){
				d++;
			}
			ret+=d;
			ret%=mod;
			s--;
		}
		return (int)ret;
	}
	
	//LCP 25. 古董键盘
	long keyboard;
	
	public int keyboard(int k, int n) {
		long[][] c = new long[n + 1][k + 1];
		for (int i = 1; i <= k && i <= n; i++) {
			c[i][i] = 1;
		}
		for (int i = 1; i <= n; i++) {
			c[i][1] = i;
			for (int j = 2; j < i && j <= k; j++) {
				c[i][j] = i * c[i - 1][j] / (i - j);
			}
		}
		int[] mem = new int[k + 1];
		keyboardHelper(26, n, k, mem, c, n);
		
		return (int) keyboard;
	}
	
	private void keyboardHelper(int last, int len, int max, int[] mem, long[][] c, int l) {
		if (len < 0) {
			return;
		} else if (len > 0) {
			for (int i = max; i > 0; i--) {
				if (len >= i) {
					mem[i]++;
					keyboardHelper(last - 1, len - i, i, mem, c, l);
					mem[i]--;
				}
			}
		} else if (last >= 0) {
			long ret = 1, r;
			int x = 26, mod = 1000000007;
			int length = l, t;
			for (int i = 1; i < mem.length; i++) {
				t = mem[i];
				r = 1;
				for (int j = 1; j <= mem[i]; j++) {
					r *= x;
					r /= j;
					x--;
				}
				ret *= r;
				ret %= mod;
				while (t > 0) {
					//ret*=x;
					ret *= c[length][i];
					//ret/=t;
					t--;
					length -= i;
					ret %= mod;
				}
			}
			keyboard += ret;
			keyboard %= mod;
		}
	}
	
	
	//LCP 25. 古董键盘--超时
	public int keyboard1(int k, int n) {
		HashMap<String, Long> cur = new HashMap<>(), next;
		cur.put("00000000000000000000000000", 1L);
		char[] arr;
		long val;
		char max = (char) ('1' + k);
		int mod = 1000000007;
		String key;
		for (int i = 0; i < n; i++) {
			next = new HashMap<>();
			for (Entry<String, Long> entry : cur.entrySet()) {
				arr = entry.getKey().toCharArray();
				val = entry.getValue();
				for (int j = 0; j < 26; j++) {
					if (arr[j] + 1 < max) {
						arr[j]++;
						key = new String(arr);
						if (!next.containsKey(key)) {
							next.put(key, val);
						} else {
							next.put(key, (val + next.get(key)) % mod);
						}
						arr[j]--;
					}
				}
			}
			cur = next;
		}
		val = 0;
		for (Long value : cur.values()) {
			val += value;
			val %= mod;
		}
		return (int) val;
	}
	
	//LCP 17. 速算机器人
	public int calculate(String s) {
		int x = 1, y = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if ('A' == s.charAt(i)) {
				x = 2 * x + y;
			} else {
				y = 2 * y + x;
			}
		}
		return x + y;
	}
	
	//1592. 重新排列单词间的空格
	public String reorderSpaces(String text) {
		int wc = 0, sc = 0;
		boolean flag = text.charAt(0) != ' ';
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c != ' ') {
				if (flag) {
					wc++;
					flag = false;
				}
			} else {
				flag = true;
				sc++;
			}
			
		}
		int count = wc > 1 ? sc / (wc - 1) : sc;
		StringBuffer buf = new StringBuffer();
		flag = false;
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c != ' ') {
				buf.append(c);
				flag = true;
			} else if (flag) {
				for (int j = 0; j < count && sc > 0; j++) {
					buf.append(' ');
					sc--;
				}
				flag = false;
			}
		}
		while (sc > 0) {
			buf.append(' ');
			sc--;
		}
		return buf.toString();
	}
	
	int maxUniqueSplit;
	
	public int maxUniqueSplit(String s) {
		char[] arr = s.toCharArray();
		maxUniqueSplitHelper(arr, 0, new HashSet<String>(), arr.length);
		return maxUniqueSplit;
	}
	
	private void maxUniqueSplitHelper(char[] arr, int i, HashSet<String> set, int length) {
		if (i == length) {
			maxUniqueSplit = Math.max(maxUniqueSplit, set.size());
			return;
		}
		StringBuffer buf = new StringBuffer();
		for (; i < length; i++) {
			buf.append(arr[i]);
			String cur = buf.toString();
			if (set.add(cur)) {
				maxUniqueSplitHelper(arr, i + 1, set, length);
				set.remove(cur);
			}
		}
	}
	
	//1594. 矩阵的最大非负积
	public int maxProductPath(int[][] grid) {
		int high = grid.length;
		int len = grid[0].length;
		long[][][] mem = new long[high][len][2];
		mem[0][0][0] = grid[0][0];
		mem[0][0][1] = grid[0][0];
		
		int mod = 1000000007;
		for (int i = 1; i < high; i++) {
			mem[i][0][0] = mem[i - 1][0][0] * grid[i][0];
			mem[i][0][1] = mem[i][0][0];
		}
		for (int i = 1; i < len; i++) {
			mem[0][i][0] = mem[0][i - 1][0] * grid[0][i];
			mem[0][i][1] = mem[0][i][0];
		}
		for (int i = 1; i < high; i++) {
			for (int j = 1; j < len; j++) {
				if (grid[i][j] < 0) {
					mem[i][j][0] = Math.min(mem[i - 1][j][1], mem[i][j - 1][1]) * grid[i][j];
					mem[i][j][1] = Math.max(mem[i - 1][j][0], mem[i][j - 1][0]) * grid[i][j];
				} else {
					mem[i][j][0] = Math.max(mem[i - 1][j][0], mem[i][j - 1][0]) * grid[i][j];
					mem[i][j][1] = Math.min(mem[i - 1][j][1], mem[i][j - 1][1]) * grid[i][j];
				}
			}
		}
		return mem[high - 1][len - 1][0] < 0 ? -1 : (int) (mem[high - 1][len - 1][0] % mod);
	}
	
	
	//1588. 所有奇数长度子数组的和
	public int sumOddLengthSubarrays(int[] arr) {
		int len = arr.length;
		int s1 = 0, c1 = 0, s2 = 0, c2 = 0, ret = 0, st, ct;
		for (int i = 0; i < len; i++) {
			
			ct = c1;
			st = s1;
			c1 = c2 + 1;
			s1 = c1 * arr[i] + s2;
			ret += s1;
			c2 = ct;
			s2 = c2 * arr[i] + st;
			
		}
		return ret;
	}
	
	//5504. 使数组和能被 P 整除
	public int minSubarray(int[] nums, int p) {
		int x = 0;
		for (int num : nums) {
			x += num;
			x %= p;
		}
		if (x == 0) {
			return 0;
		}
		int len = nums.length;
		int ret = len;
		HashMap<Integer, Integer> mem = new HashMap<>();
		mem.put(0, -1);
		int sum = 0;
		for (int i = 0; i < len; i++) {
			sum += nums[i];
			sum %= p;
			int t = sum < x ? sum - x + p : sum - x;
			Integer cur = mem.get(t);
			if (cur != null) {
				ret = Math.min(ret, i - cur);
			}
			mem.put(sum, i);
		}
		return ret == len ? -1 : ret;
	}
	
	public int maxSumRangeQuery(int[] nums, int[][] requests) {
		Arrays.sort(requests, (a, b) -> (a[0] - b[0]));
		int len = nums.length;
		long[] count = new long[len];
		int l = requests.length;
		int j = 0;
		while (j < l && requests[j][0] == 0) {
			count[0]++;
			if (requests[j][1] + 1 < len) {
				count[requests[j][1] + 1]--;
			}
			j++;
		}
		int mod = 1000000007;
		for (int i = 1; i < len; i++) {
			while (j < l && requests[j][0] == i) {
				if (requests[j][1] + 1 < len) {
					count[requests[j][1] + 1]--;
				}
				count[i]++;
				j++;
			}
			count[i] += count[i - 1];
		}
		Arrays.sort(nums);
		Arrays.sort(count);
		long ret = 0;
		for (int i = 0; i < len; i++) {
			ret += count[i] * nums[i] % mod;
			ret %= mod;
		}
		return (int) ret;
	}
	
	
	//5506. 奇怪的打印机 II
	public boolean isPrintable(int[][] targetGrid) {
		int[][] mem = new int[61][5];
		boolean[] use = new boolean[61];
		int count = 0;
		for (int i = 0; i < 61; i++) {
			mem[i][1] = Integer.MAX_VALUE;
			mem[i][2] = Integer.MAX_VALUE;
		}
		int high = targetGrid.length;
		int len = targetGrid[0].length;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (!use[targetGrid[i][j]]) {
					use[targetGrid[i][j]] = true;
					count++;
				}
				int[] cur = mem[targetGrid[i][j]];
				cur[0]++;
				cur[1] = Math.min(i, cur[1]);
				cur[2] = Math.min(j, cur[2]);
				cur[3] = Math.max(i, cur[3]);
				cur[4] = Math.max(j, cur[4]);
			}
		}
		int i = 1;
		while (count > 0 && i < 61) {
			i = 1;
			for (; i <= 60; i++) {
				if (use[i] && mem[i][0] == (mem[i][3] - mem[i][1] + 1) * (mem[i][4] - mem[i][2]
					+ 1)) {
					isPrintableHelper(mem, mem[i][1], mem[i][3], mem[i][2], mem[i][4], i,
						targetGrid);
					count--;
					use[i] = false;
					break;
				}
			}
		}
		return count == 0;
	}
	
	private void isPrintableHelper(int[][] mem, int l, int r, int u, int d, int val,
		int[][] target) {
		for (int i = l; i <= r; i++) {
			for (int j = u; j <= d; j++) {
				if (target[i][j] == val) {
					for (int[] cur : mem) {
						if (cur[1] <= i && cur[2] <= j && cur[3] >= i && cur[4] >= j) {
							cur[0]++;
						}
					}
				}
			}
		}
	}
	
	//面试题 16.20. T9键盘
	class CharTree {
		
		String val;
		CharTree[] child;
		
		public CharTree() {
			this.child = new CharTree[26];
		}
	}
	
	public List<String> getValidT9Words(String num, String[] words) {
		int[][] mem = {{}, {}, {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {9, 10, 11}, {12, 13, 14},
			{15, 16, 17, 18}, {19, 20, 21}, {22, 23, 24, 25}};
		int len = num.length();
		CharTree root = new CharTree();
		int c;
		for (String word : words) {
			if (word.length() == len) {
				CharTree cur = root;
				for (int i = 0; i < len; i++) {
					c = word.charAt(i) - 'a';
					if (cur.child[c] == null) {
						cur.child[c] = new CharTree();
					}
					cur = cur.child[c];
				}
				cur.val = word;
			}
		}
		ArrayList<String> ret = new ArrayList<>();
		getValidT9WordsHelper(num, 0, ret, root, mem);
		return ret;
	}
	
	private void getValidT9WordsHelper(String num, int i, ArrayList<String> ret, CharTree root,
		int[][] mem) {
		if (root == null) {
			return;
		}
		if (i == num.length()) {
			ret.add(root.val);
			return;
		}
		int c = num.charAt(i) - '0';
		for (int t : mem[c]) {
			getValidT9WordsHelper(num, i + 1, ret, root.child[t], mem);
		}
	}
	
	//
	public int[] findRedundantDirectedConnection(int[][] edges) {
		int len = edges.length;
		int[] mem = new int[len + 1];
		for (int i = 0; i <= len; i++) {
			mem[i] = i;
		}
		int x, y;
		int[] ret = null;
		for (int i = 0; i < len; i++) {
			int[] edge = edges[i];
			x = edge[0];
			y = edge[1];
			if (circleCheck(mem, x, y)) {
				ret = edge;
			} else if (sameFather(mem, x, y)) {
				ret = edge;
			} else {
				if (mem[y] != y) {
					if (ret == null && sameSon(edges, i + 1, mem)) {
						return edge;
					} else {
						return new int[]{mem[y], y};
					}
				}
				mem[y] = x;
			}
		}
		return ret;
	}
	
	private boolean sameSon(int[][] edges, int i, int[] mem) {
		int len = edges.length, x, y;
		for (; i < len; i++) {
			int[] edge = edges[i];
			x = edge[0];
			y = edge[1];
			if (circleCheck(mem, x, y) || sameFather(mem, x, y)) {
				return false;
			} else {
				mem[y] = x;
			}
		}
		boolean flag = false;
		for (int j = 1; j < len; j++) {
			if (mem[j] == j) {
				if (flag) {
					return false;
				}
				flag = true;
			}
		}
		return true;
	}
	
	private boolean sameFather(int[] mem, int x, int y) {
		while (mem[x] != x) {
			x = mem[x];
		}
		while (mem[y] != y) {
			y = mem[y];
		}
		return x == y;
	}
	
	private boolean circleCheck(int[] mem, int cur, int father) {
		while (mem[cur] != cur) {
			if (cur == father) {
				return true;
			}
			cur = mem[cur];
		}
		return cur == father;
	}
	
	//675. 为高尔夫比赛砍树
	public int cutOffTree(List<List<Integer>> forest) {
		int high = forest.size();
		int len = forest.get(0).size();
		//forest.get(0).set(0,1);
		int[][] map = new int[high][len];
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
		for (int i = 0; i < high; i++) {
			List<Integer> list = forest.get(i);
			for (int j = 0; j < len; j++) {
				Integer cur = list.get(j);
				if (cur > 1) {
					queue.add(new int[]{i, j, cur});
				} else if (cur == 0) {
					map[i][j] = -1;
				}
			}
		}
		int ret = 0, x = 0, y = 0, d, idx = 1;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			if (x == cur[0] && y == cur[1]) {
				continue;
			}
			d = cutOffTreeHelper(map, x, y, cur[0], cur[1], idx);
			if (d > 0) {
				ret += d;
				x = cur[0];
				y = cur[1];
				idx++;
			} else {
				return -1;
			}
		}
		return ret;
	}
	
	private int cutOffTreeHelper(int[][] clone, int x, int y, int tx, int ty, int m) {
		clone[x][y] = m;
		ArrayList<Integer> list = new ArrayList<>(), next = new ArrayList<Integer>();
		list.add(x * 100 + y);
		int ret = 0, nx, ny;
		int high = clone.length;
		int len = clone[0].length;
		while (!list.isEmpty()) {
			ret++;
			for (Integer cur : list) {
				x = cur / 100;
				y = cur % 100;
				for (int[] d : ds_4) {
					nx = x + d[0];
					ny = y + d[1];
					if (nx < 0 || nx >= high || ny < 0 || ny >= len || clone[nx][ny] < 0
						|| clone[nx][ny] == m) {
						continue;
					}
					if (nx == tx && ny == ty) {
						return ret;
					}
					clone[nx][ny] = m;
					next.add(nx * 100 + ny);
				}
			}
			list = next;
			next = new ArrayList<>();
		}
		return -1;
	}
	
	//1583. 统计不开心的朋友
	public int unhappyFriends(int n, int[][] preferences, int[][] pairs) {
		int[][] mem = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - 1; j++) {
				mem[i][preferences[i][j]] = j;
			}
		}
		int[] p = new int[n];
		for (int[] pair : pairs) {
			p[pair[0]] = pair[1];
			p[pair[1]] = pair[0];
		}
		int ret = 0, u, x_y, u_x, u_v;
		for (int i = 0; i < n; i++) {
			x_y = mem[i][p[i]];
			//现在x-y组合的关系
			for (int j = 0; j < x_y; j++) {
				u = preferences[i][j];
				//与x组合关系更好的u
				u_x = mem[u][i];
				//u-x的关系
				if (u_x != n - 2) {
					//如果u-x的关系不是最差x就不高兴
					ret++;
					break;
				}
				/*u_v=mem[u][p[u]];
				if(u_x>u_v){
					ret++;
					break;
				}*/
			}
		}
		return ret;
	}
	
	//1563. 石子游戏 V
	public int stoneGameV(int[] stoneValue) {
		int sum = 0;
		for (int i : stoneValue) {
			sum += i;
		}
		int len = stoneValue.length;
		int[][] mem = new int[len][len];
		return stoneGameVHelper(stoneValue, 0, len - 1, sum, mem);
	}
	
	private int stoneGameVHelper(int[] stoneValue, int l, int r, int sum, int[][] mem) {
		if (l >= r) {
			return 0;
		} else if (mem[l][r] > 0) {
			return mem[l][r];
		}
		int leftsum = 0, ret = 0;
		for (int i = l; i < r; i++) {
			leftsum += stoneValue[i];
			if (leftsum * 2 < sum) {
				ret = Math.max(leftsum + stoneGameVHelper(stoneValue, l, i, leftsum, mem), ret);
			} else if (leftsum * 2 == sum) {
				ret = Math.max(leftsum + stoneGameVHelper(stoneValue, l, i, leftsum, mem), ret);
				ret = Math.max(leftsum + stoneGameVHelper(stoneValue, i + 1, r, leftsum, mem), ret);
			} else {
				ret = Math
					.max(sum - leftsum + stoneGameVHelper(stoneValue, i + 1, r, sum - leftsum, mem),
						ret);
			}
		}
		mem[l][r] = ret;
		return mem[l][r];
	}
	
	public boolean reachingPoints(int sx, int sy, int tx, int ty) {
		return reachingPointsHelper(sx, sy, tx, ty, new HashSet<>());
	}
	
	public boolean reachingPointsHelper(long sx, long sy, long tx, long ty, Set<Long> visited) {
		if (tx < sx || ty < sy || visited.contains((tx << 32) + ty)) {
			return false;
		} else if (sx == tx) {
			return (ty - sy) % sx == 0;
		} else if (sy == ty) {
			return (sy - sx) % sy == 0;
		}
		long x = tx, y = ty;
		while (x > y) {
			x -= ty;
			visited.add((((x) << 32) + y));
			if (reachingPointsHelper(sx, sy, x, y, visited)) {
				return true;
			}
		}
		while (y > x) {
			y -= tx;
			visited.add((((x) << 32) + y));
			if (reachingPointsHelper(sx, sy, x, y, visited)) {
				return true;
			}
		}
		return false;
	}
	
	//1582. 二进制矩阵中的特殊位置
	public int numSpecial(int[][] mat) {
		int high = mat.length;
		int len = mat[0].length;
		int[] a = new int[high];
		int[] b = new int[len];
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				a[i] += mat[i][j];
				b[j] += mat[i][j];
			}
		}
		int ret = 0;
		for (int i = 0; i < high; i++) {
			if (a[i] != 1) {
				continue;
			}
			for (int j = 0; j < len; j++) {
				if (mat[i][j] == 1 && b[j] == 1) {
					ret++;
				}
			}
		}
		return ret;
	}
	
	//37. 解数独
	public void solveSudoku(char[][] board) {
		int[] a = new int[9], b = new int[9], c = new int[9];//a横b竖c九宫
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				int t = 1 << (board[i][j] - '0');
				a[i] += t;
				b[j] += t;
				c[(i / 3) * 3 + (j / 3)] += t;
			}
		}
		solveSudokuHelper(board, a, b, c, 0, 0);
	}
	
	private boolean solveSudokuHelper(char[][] board, int[] a, int[] b, int[] c, int i, int j) {
		for (; i < 9; i++, j = 0) {
			for (; j < 9; j++) {
				if (board[i][j] == '.') {
					for (int k = 1; k < 10; k++) {
						int t = 1 << k;
						if ((a[i] & t) == 0 && (b[j] & t) == 0
							&& (c[(i / 3) * 3 + (j / 3)] & t) == 0) {
							a[i] += t;
							b[j] += t;
							c[(i / 3) * 3 + (j / 3)] += t;
							board[i][j] = (char) (k + '0');
							if (solveSudokuHelper(board, a, b, c, i, j)) {
								return true;
							}
							a[i] -= t;
							b[j] -= t;
							c[(i / 3) * 3 + (j / 3)] -= t;
							board[i][j] = '.';
						}
					}
					board[i][j] = '.';
					return false;
				}
			}
		}
		return true;
	}
	
	
	//1585. 检查字符串是否可以通过排序子字符串得到另一个字符串
	public boolean isTransformable(String s, String t) {
		int[] mem = new int[10];
		Arrays.fill(mem, Integer.MAX_VALUE);
		int len = s.length(), x, a;
		for (int i = 0; i < 10; i++) {
			x = s.indexOf((char) ('0' + i));
			mem[i] = x < 0 ? len : x;
		}
		for (int i = 0; i < len; i++) {
			a = t.charAt(i) - '0';
			x = Integer.MAX_VALUE;
			for (int j = 0; j <= a; j++) {
				x = Math.min(x, mem[j]);
			}
			if (x == mem[a]) {
				x = s.indexOf((char) ('0' + a), x + 1);
				mem[a] = x < 0 ? len : x;
			} else {
				return false;
			}
		}
		return true;
	}
	
	//1585. 检查字符串是否可以通过排序子字符串得到另一个字符串--错解
	public boolean isTransformable1(String s, String t) {
		int[] count = new int[10];
		int len = s.length();
		int l = 0, r = 0;
		char a, b;
		for (int i = 0; i < len; i++) {
			a = s.charAt(i);
			b = t.charAt(i);
			if (a == b) {
				continue;
			} else if (a < b) {
				return false;
			} else {
				r = i + 1;
				count[a - '0']++;
				l = 0;
				while (i < r) {
					while (r < len && (a = s.charAt(r)) > b) {
						count[a - '0']++;
						r++;
					}
					if (a == b) {
						i++;
						r++;
						for (; i < r; i++) {
							while (count[l] == 0) {
								l++;
							}
							b = t.charAt(i);
							if (l == b - '0') {
								count[l]--;
							} else if (l > b - '0') {
								break;
							} else {
								return false;
							}
						}
					} else {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	//1584. 连接所有点的最小费用
	public int minCostConnectPoints(int[][] points) {
		PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> (a[2] - b[2]));
		int len = points.length;
		int d;
		int[] tree = new int[len];
		for (int i = 0; i < len; i++) {
			tree[i] = i;
			for (int j = i + 1; j < len; j++) {
				d = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
				queue.add(new int[]{i, j, d});
			}
		}
		int l, r, ret = 0, min, max;
		while (len > 1) {
			int[] x = queue.poll();
			l = x[0];
			r = x[1];
			while (tree[l] != l) {
				l = tree[l];
			}
			while (tree[r] != r) {
				r = tree[r];
			}
			if (l != r) {
				min = Math.min(l, r);
				max = Math.max(l, r);
				ret += x[2];
				tree[max] = min;
				tree[x[0]] = min;
				tree[x[1]] = min;
				len--;
			}
		}
		return ret;
	}
	
	//664. 奇怪的打印机
	public int strangePrinter(String s) {
		char[] arr = s.toCharArray();
		int len = arr.length;
		int[][] mem = new int[len][len];
		for (int i = 0; i < len; i++) {
			mem[i][i] = 1;
		}
		for (int i = 1; i < len; i++) {
			for (int l = 0, r = i; r < len; l++, r++) {
				if (arr[l] == arr[r]) {
					mem[l][r] = Math.min(mem[l + 1][r], mem[l][r - 1]);
				} else if (arr[l] == arr[l + 1]) {
					mem[l][r] = mem[l + 1][r];
				} else if (arr[r] == arr[r - 1]) {
					mem[l][r] = mem[l][r - 1];
				} else {
					mem[l][r] = mem[l][r - 1] + 1;
					for (int j = l; j < r; j++) {
						mem[l][r] = Math.min(mem[l][r], mem[l][j] + mem[j + 1][r]);
					}
				}
			}
		}
		return mem[0][len - 1];
	}
	
	private int strangePrinterHelper(char[] arr, int[][] mem, int l, int r) {
		
		int left = l;
		while (l <= r && arr[left] == arr[l]) {
			l++;
		}
		if (l > r) {
			return 1;
		}
		int t;
		int ret = 1 + mem[l][r];
		for (int i = l; i <= r; i++) {
			if (arr[i] == arr[left]) {
				t = mem[l][i - 1];
				while (i <= r && arr[i] == arr[left]) {
					i++;
				}
				if (i < r) {
					r += mem[i][r];
				}
				ret = Math.min(ret, 1 + t);
			}
		}
		return ret;
	}
	
	
	//1572. 矩阵对角线元素的和
	public int diagonalSum(int[][] mat) {
		int len = mat.length;
		int ret = 0;
		if ((len & 1) > 0) {
			ret -= mat[len / 2][len / 2];
		}
		for (int i = 0; i < len; i++) {
			ret += mat[i][i];
			ret += mat[i][len - 1 - i];
		}
		return ret;
	}
	
	public List<String> braceExpansionII(String expression) {
		char[] arr = expression.toCharArray();
		List<String> strings = braceExpansionIISplit(arr, 0, arr.length - 1);
		strings.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		return strings;
	}
	
	private List<String> braceExpansionIISplit(char[] arr, int l, int r) {
		int count = 0;
		HashSet<String> ret = new HashSet<>();
		for (int i = l; i <= r; i++) {
			if (arr[i] == '{') {
				count++;
			} else if (arr[i] == '}') {
				count--;
			} else if (arr[i] == ',' && count == 0) {
				ret.addAll(braceExpansionIIHelper(arr, l, i - 1));
				l = i + 1;
			}
			if (i == r) {
				ret.addAll(braceExpansionIIHelper(arr, l, r));
			}
		}
		
		return new ArrayList<>(ret);
	}
	
	private Set<String> braceExpansionIIHelper(char[] arr, int l, int r) {
		HashSet<String> cur = new HashSet<>(), next;
		if (l == r) {
			cur.add(arr[l] + "");
			return cur;
		}
		int count = 0;
		for (int i = l; i <= r; i++) {
			if (arr[i] == '{') {
				count++;
			} else if (arr[i] == '}') {
				count--;
			}
			if (count == 0 || i == r) {
				List<String> list;
				if (l + 1 <= i - 1) {
					list = braceExpansionIISplit(arr, l + 1, i - 1);
				} else {
					list = new ArrayList<>();
					list.add(arr[l] + "");
				}
				if (cur.isEmpty()) {
					cur.addAll(list);
				} else {
					next = new HashSet<>();
					for (String a : cur) {
						for (String b : list) {
							next.add(a + b);
						}
					}
					cur = next;
				}
				l = i + 1;
			}
		}
		return cur;
	}
	
	//1577. 数的平方等于两数乘积的方法数
	public int numTriplets(int[] nums1, int[] nums2) {
		HashMap<Integer, Integer> a = new HashMap<>();
		HashMap<Integer, Integer> b = new HashMap<>();
		for (int i : nums1) {
			a.put(i, a.getOrDefault(i, 0) + 1);
		}
		for (int i : nums2) {
			b.put(i, b.getOrDefault(i, 0) + 1);
		}
		int ret = 0;
		ret += numTripletsHelper(a, b);
		ret += numTripletsHelper(b, a);
		return ret;
		
	}
	
	private int numTripletsHelper(HashMap<Integer, Integer> a, HashMap<Integer, Integer> b) {
		int ret = 0, t = 0;
		for (Entry<Integer, Integer> ae : a.entrySet()) {
			Integer key = ae.getKey();
			Integer value = ae.getValue();
			long x = (long) key * key;
			for (Entry<Integer, Integer> be : b.entrySet()) {
				if (x % be.getKey() == 0) {
					if (be.getKey().equals(key)) {
						t += value * be.getValue() * (be.getValue() - 1) / 2;
					} else {
						ret += value * be.getValue() * b.getOrDefault((int) (x / be.getKey()), 0);
					}
				}
			}
		}
		return ret / 2 + t;
	}
	
	public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		getAllElementsHelper(root1, left);
		getAllElementsHelper(root2, right);
		ArrayList<Integer> ret = new ArrayList<>();
		int l = 0, r = 0, x, y;
		while (l < left.size() && r < right.size()) {
			x = left.get(l);
			y = right.get(r);
			if (x < y) {
				ret.add(x);
				l++;
			} else if (x > y) {
				ret.add(y);
				r++;
			} else {
				ret.add(x);
				l++;
				ret.add(y);
				r++;
			}
		}
		while (l < left.size()) {
			ret.add(left.get(l));
			l++;
		}
		while (r < right.size()) {
			ret.add(right.get(r));
			r++;
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
	
	//1576. 替换所有的问号
	public String modifyString(String s) {
		char[] arr = s.toCharArray();
		int len = s.length();
		for (int i = 0; i < len; i++) {
			if (arr[i] == '?') {
				for (char j = 'a'; j <= 'z'; j++) {
					arr[i] = j;
					if ((i > 0 && arr[i] == arr[i - 1]) || (i + 1 < len && arr[i] == arr[i + 1])) {
						continue;
					} else {
						break;
					}
				}
			}
		}
		return new String(arr);
	}
	
	//1579. 保证图可完全遍历
	int maxNumEdgesToRemove = 0;
	
	public int maxNumEdgesToRemove(int n, int[][] edges) {
		int[] a = new int[n + 1];
		for (int i = 0; i <= n; i++) {
			a[i] = i;
		}
		for (int[] edge : edges) {
			if (edge[0] == 3) {
				maxNumEdgesToRemoveHelper(a, edge[1], edge[2]);
			}
		}
		int[] b = Arrays.copyOf(a, n + 1);
		for (int[] edge : edges) {
			if (edge[0] == 1) {
				maxNumEdgesToRemoveHelper(a, edge[1], edge[2]);
			} else if (edge[0] == 2) {
				maxNumEdgesToRemoveHelper(b, edge[1], edge[2]);
			}
		}
		int x = 0, y = 0;
		for (int i = 1; i <= n; i++) {
			x += (a[i] == i ? 1 : 0);
			y += (b[i] == i ? 1 : 0);
		}
		
		return x > 1 || y > 1 ? -1 : maxNumEdgesToRemove;
	}
	
	private void maxNumEdgesToRemoveHelper(int[] a, int i, int j) {
		int oi = i, oj = j;
		while (i != a[i]) {
			i = a[i];
		}
		while (j != a[j]) {
			j = a[j];
		}
		int max = Math.max(i, j);
		int min = Math.min(i, j);
		if (a[max] == min) {
			maxNumEdgesToRemove++;
		} else {
			a[oi] = min;
			a[oj] = min;
			a[max] = min;
		}
	}
	
	//1578. 避免重复字母的最小删除成本
	public int minCost(String s, int[] cost) {
		int len = s.length();
		char c, t;
		int max, sum, ret = 0;
		for (int i = 0; i < len; ) {
			c = s.charAt(i);
			max = cost[i];
			sum = max;
			i++;
			while (i < len && c == s.charAt(i)) {
				max = Math.max(cost[i], max);
				sum += cost[i];
				i++;
			}
			ret += sum - max;
		}
		return ret;
	}
	
	
	//1575. 统计所有可行路径
	public int countRoutes(int[] locations, int start, int finish, int fuel) {
		int len = locations.length;
		long[][] mem = new long[len][fuel + 1];
		int[][] dis = new int[len][len];
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j < len; j++) {
				dis[i][j] = Math.abs(locations[i] - locations[j]);
				dis[j][i] = Math.abs(locations[i] - locations[j]);
			}
		}
		mem[start][0] = 1;
		int t;
		int mod = 1000000007;
		long ret = mem[finish][0];
		for (int k = 1; k <= fuel; k++) {
			for (int i = 0; i < len; i++) {
				for (int j = i + 1; j < len; j++) {
					t = dis[j][i];
					if (k >= t) {
						mem[i][k] += mem[j][k - t];
						mem[i][k] %= mod;
						mem[j][k] += mem[i][k - t];
						mem[j][k] %= mod;
					}
					
				}
			}
			ret += mem[finish][k];
			ret %= mod;
		}
		return (int) ret;
	}
	
	//LCP 14. 切分数组--超时
	public int splitArray(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int len = nums.length;
		//int[] mem = new int[len+1];
		int x, cur, min, t, ret = 0;
		/*ArrayList<Integer> list = new ArrayList<>();
		list.add(2);
		for (int i = 0; i <= ; i++) {
		
		}*/
		for (int i = 0; i < len; i++) {
			cur = nums[i];
			x = 2;
			min = ret + 1;
			while (cur >= x * x) {
				if (cur % x == 0) {
					t = Math.min(map.getOrDefault(x, Integer.MAX_VALUE), ret);
					map.put(x, t);
					min = Math.min(min, t + 1);
					cur /= x;
					while (cur % x == 0) {
						cur /= x;
					}
				}
				x++;
			}
			if (cur != 1) {
				t = Math.min(map.getOrDefault(cur, Integer.MAX_VALUE), ret);
				map.put(cur, t);
				min = Math.min(min, t + 1);
			}
			ret = min;
		}
		return ret;
	}
	
	//LCP 14. 切分数组--Pollard rho--有問題
	public int splitArray1(int[] nums) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int len = nums.length;
		//int[] mem = new int[len+1];
		int x, cur, min, t, ret = 0, x1;
		for (int i = 0; i < len; i++) {
			cur = nums[i];
			min = ret + 1;
			while (cur != 1) {
				x = splitArrayHelper(cur);
				if (!map.containsKey(x)) {
					x1 = splitArrayHelper(x);
					while (x != x1) {
						x = x1;
						x1 = splitArrayHelper(x);
					}
				}
				t = Math.min(map.getOrDefault(x, Integer.MAX_VALUE), ret);
				map.put(x, t);
				min = Math.min(min, t + 1);
				while (cur % x == 0) {
					cur /= x;
				}
			}
			ret = min;
		}
		return ret;
	}
	
	private int splitArrayHelper(int cur) {
		long a = f(cur, cur), b = f(f(cur, cur), cur);
		int ret;
		while (a != b) {
			ret = gcd((int) (Math.abs(a - b)), cur);
			if (ret > 1) {
				return ret;
			}
			a = f(a, cur);
			b = f(f(b, cur), cur);
		}
		return cur;
	}
	
	public int gcd(int x, int y) {
		return x == 0 ? y : gcd(y % x, x);
	}
	
	private long f(long x, long cur) {
		long ret = x * x + 1;
		return ret % cur;
	}
	
	public int[] topKFrequent(int[] nums, int k) {
		HashMap<Integer, Integer> map = new HashMap();
		for (int n : nums) {
			Integer num = map.getOrDefault(n, 0);
			map.put(n, num + 1);
			
		}
		ArrayList<Integer>[] db = new ArrayList[nums.length + 1];
		for (Integer i : map.keySet()) {
			Integer count = map.get(i);
			if (db[count] == null) {
				db[count] = new ArrayList<>();
			}
			db[count].add(i);
		}
		//ArrayList<Integer> list = new ArrayList<>();
		int[] ret = new int[k];
		int idx = 0;
		for (int i = db.length - 1; i > 0; i--) {
			if (db[i] != null) {
				for (Integer integer : db[i]) {
					ret[idx++] = integer;
					if (idx == k) {
						return ret;
					}
				}
			}
		}
		
		return ret;
	}
	
	//1574. 删除最短的子数组使剩余数组有序
	public int findLengthOfShortestSubarray(int[] arr) {
		int len = arr.length;
		int l = 0, r = len - 1;
		for (int i = 1; i < len; i++) {
			if (arr[i] >= arr[i - 1]) {
				l++;
			} else {
				break;
			}
		}
		if (l == len - 1) {
			return 0;
		}
		for (int i = r; i > 0; i--) {
			if (arr[i] >= arr[i - 1]) {
				r--;
			} else {
				break;
			}
		}
		int ret = Math.min(len - l - 1, r);
		for (int i = 0; i <= l && r < len; i++) {
			while (r < len && arr[r] < arr[i]) {
				r++;
			}
			ret = Math.min(ret, r - i - 1);
		}
		return ret;
	}
	
	//5492. 分割字符串的方案数
	public int numWays(String s) {
		int len = s.length();
		if (len < 3) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < len; i++) {
			if (s.charAt(i) == '1') {
				count++;
			}
		}
		long ret;
		if (count % 3 != 0) {
			return 0;
		} else if (count == 0) {
			ret = (long) (len - 1) * (len - 2) / 2 % 1000000007;
			return (int) ret;
		} else {
			int a = 1, b = 1, x = 0;
			count /= 3;
			for (int i = 0; i < len; i++) {
				if (s.charAt(i) == '1') {
					x++;
				} else if (count == x) {
					a++;
				} else if (count * 2 == x) {
					b++;
				} else if (x > count * 2) {
					break;
				}
			}
			ret = a * b % 1000000007;
			return (int) ret;
		}
	}
	
	
	//493. 翻转对
	public int reversePairs1(int[] nums) {
		return reversePairsHelper(nums, 0, nums.length - 1);
	}
	
	private int reversePairsHelper(int[] nums, int l, int r) {
		if (l >= r) {
			return 0;
		}
		int m = (l + r) >> 1;
		int ret = reversePairsHelper(nums, l, m) + reversePairsHelper(nums, m + 1, r);
		int[] copy = Arrays.copyOfRange(nums, l, m + 1);
		int[] copyb = Arrays.copyOfRange(nums, m + 1, r + 1);
		int len = copy.length;
		int a = 0, a1 = 0, count = m - l + 1, b = m + 1;
		long x = 2 * (long) nums[b];
		for (int i = l; i <= r; i++) {
			while (count > 0 && b <= r && copy[a1] <= x) {
				count--;
				a1++;
			}
			
			if (a < len && (b > r || copy[a] < nums[b])) {
				nums[i] = copy[a];
				a++;
			} else {
				ret += count;
				nums[i] = nums[b];
				b++;
				if (b <= r) {
					x = 2 * (long) nums[b];
				}
			}
		}
		return ret;
	}
	
	//493. 翻转对--超时
	class TNode {
		
		int val;
		int count = 1;
		TNode left, right;
		
		public TNode(int val) {
			this.val = val;
		}
	}
	
	public int reversePairs(int[] nums) {
		TNode root = new TNode(nums[0]);
		int len = nums.length, x;
		int ret = 0;
		for (int i = 1; i < len; i++) {
			x = nums[i];
			ret += searchT(root, 2 * (long) x);
			insertT(root, x);
		}
		return ret;
	}
	
	private int searchT(TNode root, long l) {
		if (root == null) {
			return 0;
		}
		if (root.val == l) {
			return root.right != null ? root.right.count : 0;
		} else if (root.val < l) {
			return searchT(root.right, l);
		} else {
			if (root.left == null) {
				return root.count;
			}
			return root.count - root.left.count + searchT(root.left, l);
		}
	}
	
	private void insertT(TNode root, int x) {
		root.count++;
		if (root.val > x) {
			if (root.left == null) {
				root.left = new TNode(x);
			} else {
				insertT(root.left, x);
			}
		} else if (root.val < x) {
			if (root.right == null) {
				root.right = new TNode(x);
			} else {
				insertT(root.right, x);
			}
		}
	}
	
	
	//51. N 皇后
	public List<List<String>> solveNQueens(int n) {
		char[][] mem = new char[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(mem[i], '.');
		}
		//boolean[] a = new boolean[n];//--
		boolean[] b = new boolean[n];//|
		boolean[] c = new boolean[2 * n];//\
		boolean[] d = new boolean[2 * n];///
		ArrayList<List<String>> ret = new ArrayList<>();
		solveNQueensHelper(mem, n, 0, ret, b, c, d);
		return ret;
	}
	
	private void solveNQueensHelper(char[][] mem, int n, int idx, ArrayList<List<String>> ret,
		boolean[] b, boolean[] c,
		boolean[] d) {
		if (idx == n) {
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				list.add(new String(mem[i]));
			}
			ret.add(list);
			return;
		}
		for (int i = 0; i < n; i++) {
			if (b[i] || c[idx - i + n] || d[idx + i]) {
				continue;
			}
			mem[idx][i] = 'Q';
			b[i] = true;
			c[idx - i + n] = true;
			d[idx + i] = true;
			solveNQueensHelper(mem, n, idx + 1, ret, b, c, d);
			b[i] = false;
			c[idx - i + n] = false;
			d[idx + i] = false;
			mem[idx][i] = '.';
		}
	}
	
	//1284. 转化为全零矩阵的最少反转次数
	public int minFlips(int[][] mat) {
		HashSet<Integer> masks = new HashSet<>();
		int base = 0;
		int high = mat.length;
		int len = mat[0].length;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				base <<= 1;
				base += mat[i][j];
				masks.add(getMask(high, len, i, j));
			}
		}
		int count = 0, t;
		if (base == 0) {
			return count;
		}
		ArrayList<Integer> list = new ArrayList<>(), next;
		list.add(base);
		//HashSet<Integer> visited = new HashSet<>();
		//visited.add(base);
		boolean[] visited = new boolean[1 << (high * len)];
		visited[base] = true;
		while (!list.isEmpty()) {
			count++;
			next = new ArrayList<>();
			for (Integer x : list) {
				for (Integer mask : masks) {
					t = mask ^ x;
					if (t == 0) {
						return count;
					} else if (!visited[t]) {
						next.add(t);
						visited[t] = true;
					}
				}
			}
			list = next;
		}
		return -1;
	}
	
	private Integer getMask(int high, int len, int x, int y) {
		int ret = 0;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				ret <<= 1;
				if (i == x && Math.abs(y - j) < 2) {
					ret += 1;
				} else if (y == j && Math.abs(i - x) < 2) {
					ret += 1;
				}
			}
		}
		return ret;
	}
	
	public boolean containsPattern(int[] arr, int m, int k) {
		if (arr.length < k * m) {
			return false;
		}
		if (k == 1) {
			return true;
		}
		int len = arr.length, sum = 0;
		int[][] mem = new int[len][2];
		int i = 0;
		for (; i < m; i++) {
			sum += arr[i];
			mem[i][0] = sum;
			mem[i][1] = 1;
		}
		for (; i < len; i++) {
			sum -= arr[i - m];
			sum += arr[i];
			if (sum == mem[i - m][0] && containsPatternHelper(arr, i, m)) {
				mem[i][0] = sum;
				mem[i][1] = 1 + mem[i - m][1];
				if (mem[i][1] == k) {
					return true;
				}
			} else {
				mem[i][0] = sum;
				mem[i][1] = 1;
			}
		}
		return false;
	}
	
	private boolean containsPatternHelper(int[] arr, int idx, int k) {
		int i = idx, j = idx - k;
		if (idx + 1 < 2 * k) {
			return false;
		}
		for (int l = 0; l < k; l++) {
			if (arr[i - l] != arr[j - l]) {
				return false;
			}
		}
		return true;
	}
	
	//剑指 Offer 20. 表示数值的字符串
	public boolean isNumber(String s) {
		s = s.trim();
		int x = s.indexOf('e');
		int y = s.indexOf('E');
		if (x >= 0 && y >= 0) {
			return false;
		}
		char[] arr = s.toCharArray();
		int len = s.length();
		if (x >= 0) {
			return isNumberHelper(arr, 0, x - 1, false) && isNumberHelper(arr, x + 1, len - 1,
				true);
		} else if (y >= 0) {
			return isNumberHelper(arr, 0, y - 1, false) && isNumberHelper(arr, y + 1, len - 1,
				true);
		} else {
			return isNumberHelper(arr, 0, len - 1, false);
		}
	}
	
	private boolean isNumberHelper(char[] arr, int l, int r, boolean flag) {
		if (l > r) {
			return false;
		}
		if (arr[l] == '+') {
			if (flag) {
				return false;
			}
			l++;
		} else if (arr[l] == '-') {
			l++;
		}
		boolean f = false;
		if (l <= r && arr[l] == '.') {
			if (flag) {
				return false;
			}
			f = true;
			l++;
		}
		if (l > r) {
			return false;
		}
		while (l <= r) {
			if (arr[l] == '.') {
				if (f || flag) {
					return false;
				}
				f = true;
			} else if (arr[l] < '0' || arr[l] > '9') {
				return false;
			}
			l++;
		}
		return true;
	}
	
	class TireNode {
		
		int val;
		TireNode left, right;
		
		public TireNode(int val) {
			this.val = val;
		}
		
	}
	
	//1569. 将子数组重新排序得到同一个二叉查找树的方案数
	public int numOfWays(int[] nums) {
		TireNode root = new TireNode(nums[0]);
		int len = nums.length;
		for (int i = 1; i < len; i++) {
			insertTree(root, nums[i]);
		}
		long[][] mem = new long[len][len];
		Arrays.fill(mem[0], 1);
		for (int i = 1; i < len; i++) {
			mem[i][0] = 1;
			for (int j = 1; j < len; j++) {
				mem[i][j] = mem[i - 1][j] + mem[i][j - 1];
				mem[i][j] %= 1000000007;
			}
		}
		long[] ret = numOfWaysHelper(root, mem);
		return (int) ret[0] - 1;
	}
	
	private long[] numOfWaysHelper(TireNode root, long[][] mem) {
		long[] ret = new long[2];
		if (root == null) {
			ret[0] = 1;
			return ret;
		}
		long[] left = numOfWaysHelper(root.left, mem);
		long[] right = numOfWaysHelper(root.right, mem);
		ret[0] = mem[(int) left[1]][(int) right[1]];
		ret[0] *= left[0];
		ret[0] %= 1000000007;
		ret[0] *= right[0];
		ret[0] %= 1000000007;
		ret[1] = left[1] + right[1] + 1;
		return ret;
	}
	
	private void insertTree(TireNode root, int num) {
		if (root.val > num) {
			if (root.left != null) {
				insertTree(root.left, num);
			} else {
				root.left = new TireNode(num);
			}
		} else {
			if (root.right != null) {
				insertTree(root.right, num);
			} else {
				root.right = new TireNode(num);
			}
		}
	}
	
	//1568. 使陆地分离的最少天数
	public int minDays(int[][] grid) {
		int high = grid.length;
		int len = grid[0].length;
		int[][] mem = new int[high][len];
		boolean flag = false;
		int sum = 0;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 1) {
					sum++;
					if (mem[i][j] == -1) {
						continue;
					} else if (flag) {
						return 0;
					} else {
						flag = true;
						minDaysHelper(i, j, mem, grid, -1);
					}
				}
			}
		}
		if (sum <= 2) {
			return sum;
		}
		int count = 0, ni, nj, val = 0;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] == 1) {
					/*count=0;
					for (int[] x : ds_4) {
						ni=i+x[0];
						nj=j+x[1];
						if(ni<0||nj<0||ni>=high||nj>=len||grid[ni][nj]!=1){
							continue;
						}
						count++;
					}
					
					if(count==2){*/
					flag = false;
					for (int[] x : ds_4) {
						ni = i + x[0];
						nj = j + x[1];
						if (ni < 0 || nj < 0 || ni >= high || nj >= len || grid[ni][nj] != 1) {
							continue;
						}
						if (flag) {
							if (mem[ni][nj] != val) {
								return 1;
							}
						} else {
							val = i * 100 + j + 100;
							mem[i][j] = val;
							minDaysHelper(ni, nj, mem, grid, val);
							flag = true;
						}
					}
					//}
				}
			}
		}
		return 2;
	}
	
	private void minDaysHelper(int i, int j, int[][] mem, int[][] grid, int value) {
		mem[i][j] = value;
		int ni, nj, high = mem.length, len = mem[0].length;
		for (int[] x : ds_4) {
			ni = i + x[0];
			nj = j + x[1];
			if (ni < 0 || nj < 0 || ni >= high || nj >= len || grid[ni][nj] != 1
				|| mem[ni][nj] == value) {
				continue;
			}
			minDaysHelper(ni, nj, mem, grid, value);
		}
	}
}
