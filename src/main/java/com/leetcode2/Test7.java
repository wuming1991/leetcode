package com.leetcode2;

import static com.base.Constant.ds_4;

import com.sun.xml.internal.ws.streaming.TidyXMLStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.LongToDoubleFunction;
import sun.security.util.Length;

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
		test.strangePrinter("absabewq");
		
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
				if(arr[l]==arr[r]){
					mem[l][r]=Math.min(mem[l+1][r],mem[l][r-1]);
				}else if(arr[l]==arr[l+1]){
					mem[l][r]=mem[l+1][r];
				}else if(arr[r]==arr[r-1]){
					mem[l][r]=mem[l][r-1];
				}else{
					mem[l][r]=mem[l][r-1]+1;
					for (int j = l; j<r ; j++) {
						mem[l][r]=Math.min(mem[l][r],mem[l][j]+mem[j+1][r]);
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
	
	public class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode() {
		}
		
		TreeNode(int val) {
			this.val = val;
		}
		
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
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
	
	
	//1563. 石子游戏 V
	public int stoneGameV(int[] stoneValue) {
		int sum = 0;
		for (int i : stoneValue) {
			sum += i;
		}
		return stoneGameVHelper(stoneValue, 0, stoneValue.length - 1, sum);
	}
	
	private int stoneGameVHelper(int[] stoneValue, int l, int r, int sum) {
		if (l >= r) {
			return 0;
		}
		int cur = 0, i = l;
		while (cur * 2 < sum) {
			cur += stoneValue[i];
			i++;
		}
		i--;
		int left =
			cur - stoneValue[i] + stoneGameVHelper(stoneValue, l, i - 1, cur - stoneValue[i]);
		int right = sum - cur + stoneGameVHelper(stoneValue, i + 1, r, sum - cur);
		return Math.max(left, right);
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
