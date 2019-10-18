package com.leetcode;

import com.sun.org.apache.regexp.internal.RE;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import javafx.beans.binding.When;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test8
 * @Author: WuMing
 * @CreateDate: 2019/8/21 22:01
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test8 {
	
	public static void main(String[] args) throws ParseException {
		
		/*SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date parse = format.parse("2019-09-24 00:00:00");
		long time1 = parse.getTime();
		long day = 86400000;
		time1-=time1%day;
		time1-=day/3;
		System.out.println(format.format(new Date(time1)));
		
		Date date = new Date();
		System.out.println(format.format(date));
		long time = date.getTime();
		time -= time % day;
		time-=day/3;
		System.out.println(format.format(new Date(time)));*/
		Test8 test = new Test8();
		/*test.canPartition(new int[]{1, 5, 2, 2, 3, 1, 2, 8});
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.right = new TreeNode(6);
		root.right.right = new TreeNode(7);
		
		test.deleteNode(root, 3);*/
		test.findSubstringInWraproundString("zab");
		
	}
	
	public int findSubstringInWraproundString(String p) {
		char[] array = p.toCharArray();
		int[] ints = new int[26];
		int len = p.length();
		for (int i = 0; i < len; i++) {
			int begin = array[i] - 'a';
			char cur = array[i];
			int count = 1;
			while (i < len - 1) {
				if (array[i + 1] - cur == 1) {
					count++;
				} else if (cur == 'z' && array[i + 1] == 'a') {
					count++;
				} else {
					break;
				}
				cur=array[++i];
			}
			for (int j = 0; j < 26; j++) {
				int t = begin + j;
				if (begin + j >= 26) {
					t -= 26;
				}
				if(ints[t]<count){
					ints[t]=count;
					count--;
				}else{
					break;
				}
			}
		}
		int ret=0;
		for (int i = 0; i < 26; i++) {
			ret+=ints[i];
		}
		return ret;
	}
	
	public boolean circularArrayLoop(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return false;
		}
		int next;
		for (int i = 0; i < len; i++) {
			nums[i] %= len;
			if (nums[i] > 0) {
				next = (i + nums[i]);
				if (next >= len) {
					next -= len;
				}
				if (nums[next] <= 0) {
					nums[i] = 0;
				}
			} else if (nums[i] < 0) {
				next = (i + nums[i]);
				if (next < 0) {
					next += len;
				}
				if (nums[next] >= 0) {
					nums[i] = 0;
				}
			}
		}
		for (int i = 0; i < len; i++) {
			if (nums[i] == 0 || nums[i] >= len) {
				continue;
			}
			if (circularArrayLoopHelper(i, nums, len, len + i)) {
				return true;
			}
		}
		return false;
	}
	
	private boolean circularArrayLoopHelper(int i, int[] nums, int len, int flag) {
		int next = i, t;
		while (nums[next] != 0 && nums[next] < len) {
			t = next;
			next = t + nums[t];
			nums[t] = flag;
			if (next < 0) {
				next += len;
			} else if (next >= len) {
				next -= len;
			}
		}
		
		return flag == nums[next];
	}
	
	public boolean find132pattern(int[] nums) {
		
		int len = nums.length, idx = 0, t;
		int[][] ints = new int[len][2];
		int i = 0;
		while (i + 1 < len && nums[i] >= nums[i + 1]) {
			i++;
		}
		if (len - i < 3) {
			return false;
		}
		ints[idx][0] = nums[i++];
		ints[idx][1] = nums[i++];
		for (; i < len; i++) {
			t = nums[i];
			if (ints[idx][0] > t) {
				idx++;
				ints[idx][0] = t;
				ints[idx][1] = t;
				continue;
			} else if (t > ints[idx][1]) {
				ints[idx][1] = t;
			}
			int j = idx;
			while (j >= 0 && ints[j][1] <= t) {
				j--;
			}
			if (j >= 0 && ints[j][0] < t) {
				return true;
			}
		}
		return false;
		
	}
	
	private boolean find132patternHelper(int min, int max, int[] nums, int l, int r) {
		int m, nm;
		while (l <= r) {
			m = (l + r) / 2;
			nm = nums[m];
			if (nm > min && nm < max) {
				return true;
			} else if (nm <= max) {
				r = m - 1;
			} else {
				l = m + 1;
			}
		}
		return false;
	}
	
	
	public TreeNode deleteNode(TreeNode root, int key) {
		TreeNode ret = new TreeNode(0);
		ret.left = root;
		TreeNode cur = root;
		TreeNode before = ret;
		boolean l = true;
		while (cur != null) {
			if (cur.val == key) {
				break;
			} else if (cur.val > key) {
				l = true;
				before = cur;
				cur = cur.left;
			} else {
				l = false;
				before = cur;
				cur = cur.right;
			}
		}
		if (cur == null) {
			return ret.left;
		}
		if (cur.right == null) {
			if (l) {
				before.left = cur.left;
			} else {
				before.right = cur.left;
			}
			return ret.left;
		} else {
			TreeNode be = cur;
			TreeNode re = cur.right;
			boolean flag = true;
			while (re.left != null) {
				flag = false;
				be = re;
				re = re.left;
			}
			int val = re.val;
			if (flag) {
				be.right = re.right;
			} else {
				be.left = re.right;
			}
			cur.val = val;
			return ret.left;
		}
	}
	
	public int characterReplacement(String s, int k) {
		
		int len = s.length();
		if (len < 2) {
			return len;
		}
		int[] ints = new int[26];
		int max = s.charAt(0) - 'A';
		int l = 0, r = 1;
		ints[max]++;
		int ret = 0;
		while (r < len) {
			while (r < len && ints[max] + k >= r - l) {
				int i = s.charAt(r) - 'A';
				ints[i]++;
				if (ints[i] > ints[max]) {
					
					max = i;
				}
				r++;
			}
			if (ints[max] + k >= r - l) {
				ret = Math.max(ret, r - l);
				break;
			} else {
				ret = Math.max(ret, r - l - 1);
			}
			int i = s.charAt(l) - 'A';
			ints[i]--;
			if (i == max) {
				for (int j = 0; j < 26; j++) {
					if (ints[j] > ints[max]) {
						max = j;
					}
				}
			}
			l++;
		}
		return Math.max(ret, r - l);
	}
	
	public List<List<Integer>> pacificAtlantic(int[][] matrix) {
		int h = matrix.length;
		if (h < 1) {
			return new ArrayList<>();
		}
		int l = matrix[0].length;
		if (l < 1) {
			return new ArrayList<>();
		}
		boolean[][] p = new boolean[h][l];
		p[0][0] = true;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i < h; i++) {
			p[i][0] = true;
			list.add(i);
			list.add(0);
		}
		for (int i = 1; i < l; i++) {
			p[0][i] = true;
			list.add(0);
			list.add(i);
		}
		while (!list.isEmpty()) {
			int i = list.removeFirst();
			int j = list.removeFirst();
			pacificAtlanticHelper(list, i, j, p, matrix);
		}
		boolean[][] a = new boolean[h][l];
		a[h - 1][l - 1] = true;
		for (int i = 0; i < h - 1; i++) {
			a[i][l - 1] = true;
			list.add(i);
			list.add(l - 1);
		}
		for (int i = 0; i < l - 1; i++) {
			a[h - 1][i] = true;
			list.add(h - 1);
			list.add(i);
		}
		while (!list.isEmpty()) {
			int i = list.removeFirst();
			int j = list.removeFirst();
			pacificAtlanticHelper(list, i, j, a, matrix);
		}
		ArrayList<List<Integer>> ret = new ArrayList<>();
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < l; j++) {
				if (p[i][j] && a[i][j]) {
					ret.add(Arrays.asList(new Integer[]{i, j}));
				}
			}
		}
		return ret;
	}
	
	private void pacificAtlanticHelper(LinkedList<Integer> list, int i, int j, boolean[][] p,
		int[][] matrix) {
		int h = p.length;
		int l = p[i].length;
		if (i > 0 && matrix[i - 1][j] >= matrix[i][j] && !p[i - 1][j]) {
			p[i - 1][j] = true;
			list.add(i - 1);
			list.add(j);
		}
		if (i + 1 < h && matrix[i + 1][j] >= matrix[i][j] && !p[i + 1][j]) {
			p[i + 1][j] = true;
			list.add(i + 1);
			list.add(j);
		}
		if (j > 0 && matrix[i][j - 1] >= matrix[i][j] && !p[i][j - 1]) {
			p[i][j - 1] = true;
			list.add(i);
			list.add(j - 1);
		}
		if (j + 1 < l && matrix[i][j + 1] >= matrix[i][j] && !p[i][j + 1]) {
			p[i][j + 1] = true;
			list.add(i);
			list.add(j + 1);
		}
	}
	
	public boolean canPartition1(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if ((sum & 1) == 1) {
			return false;
		}
		return canPartitionHelper(nums, nums.length - 1, 0, sum / 2);
	}
	
	private boolean canPartitionHelper(int[] nums, int i, int sum, int target) {
		if (i < 0 || sum > target || nums[i] > target) {
			return false;
		}
		if (sum == target) {
			return true;
		}
		for (int j = i; j >= 0; j--) {
			if (sum + nums[j] > target) {
				break;
			} else if (canPartitionHelper(nums, j - 1, sum + nums[j], target)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean canPartition(int[] nums) {
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		if ((sum & 1) == 1) {
			return false;
		}
		int len = nums.length;
		int half = sum / 2, i = 0;
		Arrays.sort(nums);
		HashSet<Integer> set = new HashSet<>();
		set.add(0);
		HashSet<Integer> newset = new HashSet<>();
		int cur = nums[i];
		while (half - cur > 0) {
			half -= cur;
			for (Integer integer : set) {
				newset.add(integer + cur);
			}
			set.addAll(newset);
			newset.clear();
			cur = nums[++i];
		}
		
		while (i < len - 1) {
			if (set.contains(cur - half)) {
				return true;
			}
			cur = nums[++i];
		}
		return set.contains(cur - half);
		
	}
	
	public String removeKdigits(String num, int k) {
		int len = num.length();
		if (k == 0) {
			return num;
		}
		if (k == len) {
			return "0";
		}
		char[] array = num.toCharArray();
		StringBuffer buffer = new StringBuffer();
		int l = 0, min;
		while (k > 0 && l < len - k) {
			min = l;
			for (int i = l + 1; i < l + k + 1 && i < len; i++) {
				if (array[i] < array[min]) {
					min = i;
					if (array[min] == '0') {
						break;
					}
				}
			}
			if (buffer.length() > 0) {
				buffer.append(array[min]);
			} else if (array[min] != '0') {
				buffer.append(array[min]);
			}
			k -= (min - l);
			l = min + 1;
		}
		if (buffer.length() < 1) {
			while (l < len && array[l] == '0') {
				l++;
			}
			if (l < len) {
				return new String(array, l, len - l);
			} else {
				return "0";
			}
		}
		return buffer.toString() + (k > 0 ? "" : new String(array, l, len - l));
	}
	
	int longestSubstringRet;
	
	public int longestSubstring(String s, int k) {
		char[] array = s.toCharArray();
		longestSubstringRet = 0;
		longestSubstringHelper(0, array.length - 1, k, array);
		return longestSubstringRet;
	}
	
	private void longestSubstringHelper(int l, int r, int k, char[] array) {
		if (r - l + 1 < longestSubstringRet || r - l + 1 < k) {
			return;
		}
		int[] ints = new int[26];
		for (int i = l; i <= r; i++) {
			ints[array[i] - 'a']++;
		}
		int i = 0;
		while (i < 26) {
			if (ints[i] != 0 && ints[i] < k) {
				break;
			}
			i++;
		}
		if (i == 26) {
			longestSubstringRet = r - l + 1;
			return;
		}
		int t = i + 'a';
		for (i = l; i <= r; i++) {
			if (array[i] == t) {
				longestSubstringHelper(l, i - 1, k, array);
				l = i + 1;
			}
		}
		longestSubstringHelper(l, r, k, array);
	}
	
	public int maxRotateFunction(int[] A) {
		int max = 0, sum = 0;
		int len = A.length;
		for (int i = 0; i < len; i++) {
			sum += A[i];
			max += i * A[i];
		}
		int next = max, ret = max;
		for (int i = 0; i < len - 1; i++) {
			next = next - sum + len * A[i];
			if (next > ret) {
				ret = next;
			}
		}
		return ret;
	}
	
	public int integerReplacement2(int n) {
		if (n == Integer.MAX_VALUE) {
			return 32;
		}
		int mask = 3;
		int ret = 0;
		while (n > 3) {
			if ((n & 1) == 1) {
				if ((n & mask) > 1) {
					n++;
				} else {
					n--;
				}
			} else {
				n >>= 1;
			}
			ret++;
		}
		ret += (n - 1);
		return ret;
	}
	
	public int integerReplacement(int n) {
		HashSet<Long> set = new HashSet<>();
		HashSet<Long> next;
		set.add((long) n);
		int ret = 0;
		boolean flag = true;
		while (flag) {
			next = new HashSet<>();
			for (Long i : set) {
				if (i == 1) {
					flag = false;
					break;
				} else if ((i & 1) == 1) {
					next.add(i + 1);
					next.add(i - 1);
				} else {
					next.add(i >> 1);
				}
			}
			if (flag) {
				ret++;
				set = next;
			}
		}
		return ret;
	}
	
	public int wiggleMaxLength(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return len;
		}
		int i = 1;
		int ret = 1;
		while (i < len && nums[i] == nums[i - 1]) {
			i++;
		}
		boolean flag = false;
		while (i < len && nums[i] <= nums[i - 1]) {
			flag = true;
			i++;
		}
		if (flag) {
			ret++;
		}
		for (; i < len; i++) {
			ret++;
			while (i < len && nums[i] >= nums[i - 1]) {
				i++;
			}
			if (i < len) {
				ret++;
			}
			while (i < len && nums[i] <= nums[i - 1]) {
				i++;
			}
			if (i == len - 1) {
				ret++;
			}
		}
		return ret;
	}
	
	int getMoneyAmount2(int n) {
		int[][] ints = new int[n + 1][n + 1];
		int t;
		for (int i = 1; i < n + 1; i++) {
			for (int x = 1, j = i + 1; j < n + 1; j++, x++) {
				t = Math.min(x + ints[x + 1][j], j + ints[x][j - 1]);
				for (int k = x + 1; k < j; k++) {
					t = Math.min(t, k + Math.max(ints[x][k - 1], ints[k + 1][j]));
				}
				ints[x][j] = t;
			}
		}
		return ints[1][n];
	}
	
	public int getMoneyAmount(int n) {
		if (n < 2) {
			return 0;
		}
		int[] ints = new int[n + 1];
		ints[1] = 0;
		int x, min, last, next, cur;
		for (int i = 2; i < n + 1; i++) {
			min = i + ints[i - 1];
			x = 2;
			last = 0;
			cur = i - 1;
			while (cur > 0) {
				next = cur + Math.max(ints[cur - 1], last);
				if (next < min) {
					min = next;
				} else {
					break;
				}
				last += cur;
				x <<= 1;
				cur = i - x + 1;
			}
			ints[i] = min;
		}
		return ints[n];
	}
	
	public int getSum(int a, int b) {
		int c;
		while (b != 0) {
			c = a & b << 1;
			a = a ^ b;
			b = c;
		}
		return a;
	}
	
	public List<Integer> largestDivisibleSubset2(int[] nums) {
		int len = nums.length;
		if (len < 1) {
			return new ArrayList<>();
		}
		Arrays.sort(nums);
		int[][] ints = new int[len][2];
		int i = len - 1;
		ints[i][0] = len;
		ints[i][1] = 1;
		int max = nums[i] / 2;
		i--;
		while (i >= 0 && nums[i] > max) {
			ints[i][0] = len;
			ints[i][1] = 1;
			i--;
		}
		int head = len - 1;
		for (; i >= 0; i--) {
			int min = nums[i] * 2;
			int maxlen = 0, idx = len;
			for (int j = len - 1; j > i; j--) {
				if (min > nums[j]) {
					break;
				} else if (ints[j][1] > maxlen && nums[j] % nums[i] == 0) {
					maxlen = ints[j][1];
					idx = j;
				}
			}
			ints[i][0] = idx;
			ints[i][1] = maxlen + 1;
			if (maxlen + 1 > ints[head][1]) {
				head = i;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		while (head < len) {
			list.add(nums[head]);
			head = ints[head][0];
		}
		return list;
	}
	
	public List<Integer> largestDivisibleSubset(int[] nums) {
		int len = nums.length;
		if (len < 1) {
			return new ArrayList<>();
		}
		int[][] ints = new int[len][2];
		int max = 0;
		ints[0] = new int[]{-1, 1};
		for (int i = 1; i < len; i++) {
			int t = i - 1, l = i;
			int cur = nums[i];
			int x = 0;
			while (t >= 0) {
				if (ints[t][1] > x && (nums[t] % cur == 0 || cur % nums[t] == 0)) {
					l = t;
					x = ints[l][1];
				}
				t--;
			}
			if (l == i) {
				ints[i][1] = 1;
				ints[i][0] = -1;
			} else {
				ints[i][0] = l;
				int z = ints[l][1] + 1;
				ints[i][1] = z;
				if (z > ints[max][1]) {
					max = i;
				}
			}
		}
		LinkedList<Integer> ret = new LinkedList<>();
		while (max >= 0) {
			ret.addFirst(nums[max]);
			max = ints[max][0];
		}
		return ret;
	}
	
	public boolean canMeasureWater(int x, int y, int z) {
		if (x > y) {
			return canMeasureWater(y, x, z);
		}
		if (y < z) {
			return false;
		}
		if (x == z || y == z || z == 0) {
			return true;
		}
		int base = 0;
		boolean[] booleans = new boolean[y + 1];
		while (!booleans[base]) {
			booleans[base] = true;
			base += x;
			base %= y;
		}
		while (y >= 0) {
			booleans[y] = true;
			y -= x;
		}
		return booleans[z];
	}
	
	public boolean increasingTriplet(int[] nums) {
		int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE;
		for (int num : nums) {
			if (num <= a) {
				a = num;
			} else if (num <= b) {
				b = num;
			} else if (num > b) {
				return true;
			}
		}
		return false;
	}
	
	private Map<String, PriorityQueue<String>> map = new HashMap<>();
	
	private List<String> resList = new LinkedList<>();
	
	public List<String> findItinerary(List<List<String>> tickets) {
		for (List<String> ticket : tickets) {
			String src = ticket.get(0);
			String dst = ticket.get(1);
			if (!map.containsKey(src)) {
				PriorityQueue<String> pq = new PriorityQueue<>();
				map.put(src, pq);
			}
			map.get(src).add(dst);
		}
		dfs("JFK");
		return resList;
	}
	
	private void dfs(String src) {
		PriorityQueue<String> pq = map.get(src);
		while (pq != null && !pq.isEmpty()) {
			dfs(pq.poll());
		}
		((LinkedList<String>) resList).addFirst(src);
	}
	
	
	public boolean isValidSerialization(String preorder) {
		String[] strings = preorder.split(",");
		int i = 0;
		int len = strings.length;
		int l = 1, k;
		ArrayList<String> list = new ArrayList<>();
		while (l != 0) {
			k = 0;
			if (len - i + 1 < 2 * l) {
				return false;
			}
			for (int j = 0; j < l; j++) {
				if (!strings[i + j].equals("#")) {
					k += 2;
				}
			}
			i += l;
			l = k;
		}
		return i == len;
	}
	
	public void wiggleSort(int[] nums) {
		if (nums.length < 2) {
			return;
		}
		Arrays.sort(nums);
		int len = nums.length;
		int[] ints = Arrays.copyOf(nums, len);
		int l = (len + 1) >> 1, r = len;
		for (int i = 0; i < len; i++) {
			if ((i & 1) == 1) {
				nums[i] = ints[--r];
			} else {
				nums[i] = ints[--l];
			}
		}
		
	}
	
	public int coinChange(int[] coins, int amount) {
		if (amount == 0) {
			return amount;
		}
		int[] ints = new int[amount + 1];
		Arrays.sort(coins);
		Arrays.fill(ints, amount + 1);
		ints[0] = 0;
		int len = coins.length;
		for (int i = 1; i < amount + 1; i++) {
			for (int j = 0; j < len; j++) {
				if (i >= coins[j]) {
					ints[i] = Math.min(ints[i], ints[i - coins[j]] + 1);
				} else {
					break;
				}
			}
			
		}
		return ints[amount] == amount + 1 ? -1 : ints[amount];
	}
	
	
	public List<Integer> findMinHeightTrees(int n, int[][] edges) {
		ArrayList<Integer> ret = new ArrayList<>();
		if (n < 2) {
			ret.add(0);
			return ret;
		}
		int[] ints = new int[n];
		List<Integer>[] lists = new ArrayList[n];
		for (int i = 0; i < n; i++) {
			lists[i] = new ArrayList<>();
		}
		for (int[] edge : edges) {
			ints[edge[0]]++;
			ints[edge[1]]++;
			lists[edge[0]].add(edge[1]);
			lists[edge[1]].add(edge[0]);
		}
		ArrayList<Integer> cur = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			if (ints[i] == 1) {
				cur.add(i);
			}
		}
		
		ArrayList<Integer> next;
		while (!cur.isEmpty()) {
			ret.clear();
			ret.addAll(cur);
			next = new ArrayList<>();
			for (Integer i : cur) {
				for (Integer k : lists[i]) {
					ints[k]--;
					if (ints[k] == 1) {
						next.add(k);
					}
				}
			}
			cur = next;
		}
		return ret;
	}
	
	public int lengthOfLIS1(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return len;
		}
		int[] ints = new int[len];
		int ret = 1;
		ints[0] = nums[0];
		int l, r, m, cur;
		for (int i = 1; i < len; i++) {
			cur = nums[i];
			if (cur > ints[ret - 1]) {
				ints[ret] = cur;
				ret++;
			} else {
				l = 0;
				r = ret;
				while (l <= r) {
					m = (l + r) / 2;
					if (ints[m] > cur) {
						r = m - 1;
					} else if (ints[m] == cur) {
						l = m;
						break;
					} else {
						l = m + 1;
					}
				}
				ints[l] = cur;
			}
		}
		return ret;
	}
	
	
	public int lengthOfLIS(int[] nums) {
		int len = nums.length;
		if (len < 1) {
			return 0;
		}
		int[] ints = new int[len];
		ints[0] = 1;
		int ret = 1, t;
		for (int i = 1; i < len; i++) {
			t = 0;
			
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] < nums[i]) {
					if (ints[j] > t) {
						t = ints[j];
					}
				} else if (ints[j] <= t) {
					break;
				}
			}
			t++;
			ints[i] = t;
			if (t > ret) {
				ret = t;
			}
		}
		return ret;
	}
	
	public int hIndex2(int[] citations) {
		int len = citations.length;
		if (len == 0) {
			return 0;
		}
		int l = 0, r = len - 1, m;
		while (l <= r) {
			m = l + (r - l) / 2;
			if (citations[m] > len - m) {
				r = m - 1;
			} else if (citations[m] == len - m) {
				return len - m;
			} else {
				l = m + 1;
			}
		}
		return len - l;
	}
	
	public int hIndex1(int[] citations) {
		int len = citations.length;
		int[] ints = new int[len + 1];
		for (int c : citations) {
			if (c > len) {
				ints[len]++;
			} else {
				ints[c]++;
			}
		}
		int ret = 0;
		while (ret + ints[len] < len) {
			ret += ints[len];
			len--;
		}
		return len;
	}
	
	public int hIndex(int[] citations) {
		Arrays.sort(citations);
		int ret = 0;
		for (int i = citations.length - 1; i >= 0; i--) {
			if (citations[i] > ret) {
				ret++;
			} else {
				break;
			}
		}
		return ret;
	}
	
	public int nthUglyNumber1(int n) {
		if (n < 7) {
			return n;
		}
		int[] ints = new int[n];
		ints[0] = 1;
		int n2 = 0, n3 = 0, n5 = 0, i2 = 0, i3 = 0, i5 = 0;
		int before = 1;
		for (int i = 1; i < n; i++) {
			while (n2 <= before) {
				n2 = 2 * ints[i2];
				i2++;
			}
			while (n3 <= before) {
				n3 = 3 * ints[i3];
				i3++;
			}
			while (n5 <= before) {
				n5 = 5 * ints[i5];
				i5++;
			}
			before = Math.min(n2, Math.min(n3, n5));
			ints[i] = before;
		}
		return ints[n - 1];
	}
	
	public int nthUglyNumber(int n) {
		if (n < 7) {
			return n;
		}
		int[] ints = new int[n];
		ints[0] = 1;
		int[] next = new int[]{2, 3, 5};
		int[] base = new int[]{2, 3, 5};
		int[] idx = new int[]{1, 1, 1};
		int t, j;
		for (int i = 1; i < n; i++) {
			t = 0;
			j = 1;
			for (; j < 3; j++) {
				if (next[j] < next[t]) {
					t = j;
				}
			}
			ints[i] = next[t];
			for (j = 0; j < 3; j++) {
				t = next[j];
				if (t <= ints[i]) {
					while (t <= ints[i]) {
						t = base[j] * ints[++idx[j]];
					}
					next[j] = t;
				}
			}
		}
		return ints[n - 1];
	}
	
	public boolean searchMatrix2(int[][] matrix, int target) {
		int rowlen = matrix.length;
		if (rowlen == 0) {
			return false;
		}
		
		int collen = matrix[0].length;
		
		if (collen == 0) {
			return false;
		}
		int col = collen - 1, row = 0, x;
		while (row < rowlen && col >= 0) {
			x = matrix[row][col];
			if (x == target) {
				return true;
			} else if (x < target) {
				row++;
			} else {
				col--;
			}
		}
		return false;
	}
	
	public boolean searchMatrix1(int[][] matrix, int target) {
		int h = matrix.length;
		if (h < 1) {
			return false;
		}
		int l = matrix[0].length;
		if (l < 1) {
			return false;
		}
		int i = binaryS(matrix, 0, h - 1, l - 1, target);
		if (matrix[i][l - 1] == target) {
			return true;
		} else if (matrix[i][l - 1] < target) {
			i++;
		}
		int a, b = l - 1, m;
		for (; i < h && b > 0; i++) {
			a = 0;
			if (matrix[i][a] == target) {
				return true;
			}
			while (a < b) {
				m = (a + b) / 2;
				int x = matrix[i][m];
				if (x > target) {
					b = m;
				} else if (x == target) {
					return true;
				} else {
					a = m + 1;
				}
			}
		}
		if (i < h) {
			i = binaryS(matrix, i, h, 0, target);
		}
		return i < h && matrix[i][0] == target;
	}
	
	private int binaryS(int[][] matrix, int l, int r, int k, int target) {
		int m, t;
		while (l < r) {
			m = l + (r - l) / 2;
			t = matrix[m][k];
			if (t > target) {
				r = m;
			} else if (t == target) {
				return m;
			} else {
				l = m + 1;
			}
		}
		return l;
	}
	
	public boolean searchMatrix(int[][] matrix, int target) {
		int h = matrix.length - 1;
		if (h < 0) {
			return false;
		}
		int l = matrix[0].length - 1;
		if (l < 0) {
			return false;
		}
		return searchMatrixHelper(matrix, 0, l, 0, h, target);
	}
	
	private boolean searchMatrixHelper(int[][] matrix, int l, int r, int u, int d, int target) {
		if (l > r || u > d) {
			return false;
		}
		int a = (l + r) / 2;
		int b = (u + d) / 2;
		int x = matrix[b][a];
		if (x == target) {
			return true;
		} else if (x > target) {
			return searchMatrixHelper(matrix, l, a - 1, u, b - 1, target)
				|| searchMatrixHelper(matrix, l, a - 1, b, d, target)
				|| searchMatrixHelper(matrix, a, r, u, b - 1, target);
		} else {
			return searchMatrixHelper(matrix, a + 1, r, u, b, target)
				|| searchMatrixHelper(matrix, l, a, b + 1, d, target)
				|| searchMatrixHelper(matrix, a + 1, r, b + 1, d, target);
		}
	}
	
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return root;
		}
		if (root == p || root == q) {
			return root;
		}
		int left = lowestCommonAncestorHelper1(root.left, p, q);
		if (left > 1) {
			return lowestCommonAncestor1(root.left, p, q);
		} else if (left == 1) {
			return root;
		} else {
			return lowestCommonAncestor1(root.right, p, q);
		}
	}
	
	private int lowestCommonAncestorHelper1(TreeNode r, TreeNode p, TreeNode q) {
		if (r == null) {
			return 0;
		}
		int ret = 0;
		if (r == p || r == q) {
			ret++;
		}
		ret += lowestCommonAncestorHelper1(r.left, p, q);
		if (ret > 1) {
			return 2;
		} else {
			ret += lowestCommonAncestorHelper1(r.right, p, q);
			return ret;
		}
		
		
	}
	
	public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}
		TreeNode left = lowestCommonAncestor2(root.left, p, q);
		TreeNode right = lowestCommonAncestor2(root.right, p, q);
		if (left == null) {
			return right;
		} else if (right == null) {
			return left;
		} else {
			return root;
		}
	}
	
	TreeNode ret;
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return root;
		}
		if (root == p || root == q) {
			return root;
		}
		ret = null;
		int i = lowestCommonAncestorHelper(root, p, q);
		return ret;
	}
	
	private int lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		int cur = 0;
		if (root == null) {
			return cur;
		}
		if (root == p || root == q) {
			cur++;
		}
		cur += lowestCommonAncestorHelper(root.left, p, q);
		if (cur == 2) {
			if (ret == null) {
				ret = root;
			}
			return 2;
		}
		cur += lowestCommonAncestorHelper(root.right, p, q);
		if (cur == 2) {
			if (ret == null) {
				ret = root;
			}
			return 2;
		}
		return cur;
	}
	
	public List<Integer> majorityElement(int[] nums) {
		int a = 0, b = 0, ac = -1, bc = -1;
		boolean flag = true;
		
		for (int num : nums) {
			if (ac < 0 && num != b) {
				ac++;
				a = num;
			} else if (bc < 0 && num != a) {
				bc++;
				b = num;
			} else if (num == a) {
				ac++;
			} else if (num == b) {
				bc++;
			} else {
				ac--;
				bc--;
			}
		}
		
		ac = 0;
		bc = 0;
		for (int num : nums) {
			if (num == a) {
				ac++;
			} else if (num == b) {
				bc++;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		if (3 * ac > nums.length) {
			list.add(a);
		}
		if (3 * bc > nums.length) {
			list.add(b);
		}
		return list;
	}
	
	public List<String> summaryRanges(int[] nums) {
		int len = nums.length;
		ArrayList<String> list = new ArrayList<>();
		if (len < 1) {
			return list;
		} else if (len < 2) {
			list.add(String.valueOf(nums[0]));
			return list;
		}
		int begin = nums[0];
		for (int i = 1; i < len; i++) {
			while (i < len && nums[i - 1] + 1 == nums[i]) {
				i++;
			}
			if (begin != nums[i - 1]) {
				list.add(begin + "->" + nums[i - 1]);
			} else {
				list.add(String.valueOf(begin));
			}
			if (i < len - 1) {
				begin = nums[i];
			} else if (i == len - 1) {
				list.add(String.valueOf(nums[i]));
			}
		}
		return list;
	}
	
	public int calculate(String s) {
		char[] array = s.toCharArray();
		
		int len = array.length;
		LinkedList<Integer> num = new LinkedList<>();
		LinkedList<Character> op = new LinkedList<>();
		num.add(0);
		op.add('+');
		int before, cur;
		for (int i = 0; i < len; i++) {
			while (i < len && array[i] == ' ') {
				i++;
			}
			if (i == len) {
				break;
			}
			cur = 0;
			while (i < len && array[i] >= '0' && array[i] <= '9') {
				cur *= 10;
				cur += (array[i] - '0');
				i++;
			}
			Character lop = op.getLast();
			if (lop == '*') {
				cur = cur * num.removeLast();
				op.removeLast();
			} else if (lop == '/') {
				cur = num.removeLast() / cur;
				op.removeLast();
			}
			num.add(cur);
			while (i < len && array[i] == ' ') {
				i++;
			}
			if (i == len) {
				break;
			}
			op.add(array[i]);
		}
		int ret = num.removeFirst();
		while (!op.isEmpty() && !num.isEmpty()) {
			Character c = op.removeFirst();
			if (c == '+') {
				ret += num.removeFirst();
			} else {
				ret -= num.removeFirst();
			}
		}
		return ret;
	}
	
	int countNodes = 0;
	
	public int countNodes(TreeNode root) {
		if (root == null) {
			return countNodes;
		}
		TreeNode t = root;
		int k = 1;
		while (t.right != null) {
			k++;
			t = t.right;
		}
		countNodes = (1 << (k + 1)) - 1;
		countNodesHelper(root, k);
		return countNodes;
	}
	
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int ret = (C - A) * (D - B) + (G - E) * (H - F);
		if (C <= E || A >= G || B >= H || D <= F) {
			return ret;
		} else {
			return ret - (Math.min(G, C) - Math.max(A, E)) * (Math.min(D, H) - Math.max(B, F));
		}
	}
	
	private void countNodesHelper(TreeNode root, int i) {
		if (i == 1) {
			if (root.left == null) {
				countNodes--;
			}
			if (root.right == null) {
				countNodes--;
			}
			return;
		}
		TreeNode t = root.left;
		int k = 1;
		while (t.right != null) {
			k++;
			t = t.right;
		}
		if (k == i) {
			countNodesHelper(root.right, i - 1);
		} else {
			countNodes -= (1 << (i - 1));
			countNodesHelper(root.left, i - 1);
		}
	}
	
	public int maximalSquare(char[][] matrix) {
		
		int m = matrix.length;
		if (m == 0) {
			return 0;
		}
		int n = matrix[0].length;
		int ret = 0, cur;
		for (int i = 0; i < n - ret; i++) {
			for (int j = 0; j < m - ret; j++) {
				if (matrix[i][j] == '1') {
					cur = maximalSquareHelper(i, j, matrix);
					if (ret < cur) {
						ret = cur;
					}
				}
			}
		}
		return ret;
	}
	
	private int maximalSquareHelper(int i, int j, char[][] matrix) {
		int ret = 1;
		int m = matrix.length - i;
		int n = matrix[0].length - j;
		while (ret + 1 < m && ret + 1 < n) {
			int nj = j + ret;
			int ni = i + ret;
			int k = 0;
			for (; k <= ret; k++) {
				if (matrix[i + k][nj] != '1' || matrix[ni][j + k] != '1') {
					break;
				}
			}
			if (k > ret) {
				ret = k;
			} else {
				break;
			}
		}
		return ret;
	}
	
	public int rob(int[] nums) {
		int length = nums.length;
		if (length == 0) {
			return 0;
		}
		if (length == 1) {
			return nums[0];
		}
		if (length == 2) {
			return Math.max(nums[0], nums[1]);
		}
		int a = nums[0];
		int a1 = Math.max(nums[0], nums[1]);
		int b = nums[1];
		int b1 = Math.max(nums[1], nums[2]);
		int a2 = Math.max(a + nums[2], a1), b2 = 0;
		a = a1;
		a1 = a2;
		for (int i = 3; i < length; i++) {
			a2 = Math.max(a1, a + nums[i]);
			b2 = Math.max(b1, b + nums[i]);
			a = a1;
			a1 = a2;
			b = b1;
			b1 = b2;
		}
		return Math.max(a, b1);
	}
	
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] count = new int[numCourses];
		int[] ret = new int[numCourses];
		int idx = 0;
		List<Integer>[] pre = new List[numCourses];
		for (int i = 0; i < numCourses; i++) {
			pre[i] = new ArrayList<Integer>();
		}
		for (int[] p : prerequisites) {
			count[p[0]]++;
			pre[p[1]].add(p[0]);
		}
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (count[i] == 0) {
				list.add(i);
				ret[idx] = i;
				idx++;
			}
		}
		while (!list.isEmpty()) {
			Integer i = list.removeFirst();
			for (Integer x : pre[i]) {
				count[x]--;
				if (count[x] == 0) {
					list.add(x);
					ret[idx] = x;
					idx++;
				}
			}
		}
		return idx == numCourses ? ret : new int[0];
		
	}
	
	public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
		int length = nums.length;
		int[][] map = new int[length][2];
		for (int i = 0; i < length; i++) {
			map[i][0] = nums[i];
			map[i][1] = i;
		}
		sortArray(map, 0, length - 1);
		long l, r, c;
		int n;
		for (int i = 0; i < length - 1; i++) {
			n = i + 1;
			l = map[i][1] - k;
			r = map[i][1] + k;
			c = map[i][0];
			while (n < length && (map[n][0] - c) <= t) {
				if (map[n][1] >= l && map[n][1] <= r) {
					return true;
				} else {
					n++;
				}
			}
		}
		return false;
	}
	
	private void sortArray(int[][] map, int l, int r) {
		if (l > r) {
			return;
		}
		int[] x = map[l];
		int nl = l + 1, nr = r;
		int c = l;
		while (nl <= nr) {
			while (nl <= nr && map[nr][0] > x[0]) {
				nr--;
			}
			if (nl <= nr) {
				map[c] = map[nr];
				c = nr;
				nr--;
			}
			while (nl <= nr && map[nl][0] < x[0]) {
				nl++;
			}
			if (nl <= nr) {
				map[c] = map[nl];
				c = nl;
				nl++;
			}
		}
		map[c] = x;
		sortArray(map, l, c - 1);
		sortArray(map, c + 1, r);
	}
	
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		
		HashMap<Long, Integer> map = new HashMap<>();
		int length = nums.length;
		if (k >= length) {
			k = length - 1;
		}
		long l, r, cur;
		int i = 0;
		Integer c;
		for (; i < k; i++) {
			cur = nums[i];
			l = cur - t;
			r = cur + t;
			while (l <= r) {
				c = map.get(l);
				if (c != null) {
					return true;
				}
				l++;
			}
			map.put(cur, map.getOrDefault(cur, 0) + 1);
		}
		
		for (; i < length; i++) {
			cur = nums[i];
			l = cur - t;
			r = cur + t;
			while (l <= r) {
				c = map.get(l);
				if (c != null && c > 0) {
					return true;
				}
				l++;
			}
			cur = nums[i];
			map.put(cur, map.getOrDefault(cur, 0) + 1);
			cur = nums[i - k];
			map.put(cur, map.getOrDefault(cur, 0) - 1);
		}
		return false;
	}
	
	public int minSubArrayLen(int s, int[] nums) {
		if (nums.length < 1) {
			return 0;
		}
		int l = 0, r = 0, sum = 0, ret = Integer.MAX_VALUE;
		int length = nums.length;
		while (r < length) {
			while (r < length && sum < s) {
				sum += nums[r];
				r++;
			}
			if (sum >= s && ret > (r - l)) {
				ret = r - l;
			}
			sum -= nums[l];
			l++;
		}
		while (l < length && sum - nums[l] >= s) {
			sum -= nums[l];
			l++;
		}
		if (sum >= s) {
			if (ret > (r - l)) {
				ret = r - l;
			}
		}
		return ret == Integer.MAX_VALUE ? 0 : ret;
	}
	
	public boolean canFinish2(int numCourses, int[][] prerequisites) {
		List<Integer>[] map = new List[numCourses];
		for (int i = 0; i < numCourses; i++) {
			map[i] = new ArrayList<>();
		}
		int[] ints = new int[numCourses];
		for (int[] p : prerequisites) {
			ints[p[1]]++;
			map[p[0]].add(p[1]);
		}
		LinkedList<Integer> list = new LinkedList<>();
		int sum = 0;
		for (int i = 0; i < numCourses; i++) {
			if (ints[i] == 0) {
				list.add(i);
				sum++;
			}
		}
		while (!list.isEmpty()) {
			Integer i = list.removeFirst();
			for (Integer x : map[i]) {
				ints[x]--;
				if (ints[x] == 0) {
					list.add(x);
					sum++;
				}
			}
		}
		return sum == numCourses;
	}
	
	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		int[] ints = new int[numCourses];
		boolean[][] booleans = new boolean[numCourses][numCourses];
		for (int[] p : prerequisites) {
			ints[p[1]]++;
			booleans[p[1]][p[0]] = true;
		}
		int sum = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (ints[i] == 0) {
				list.add(i);
				sum++;
			}
		}
		while (list.size() > 0) {
			Integer i = list.removeFirst();
			for (int j = 0; j < numCourses; j++) {
				if (booleans[j][i]) {
					ints[j]--;
					if (ints[j] == 0) {
						list.add(j);
						sum++;
					}
					
				}
			}
		}
		return sum == numCourses;
	}
	
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		int[] ints = new int[numCourses];
		for (int[] p : prerequisites) {
			ints[p[1]]++;
		}
		int sum = 0;
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < numCourses; i++) {
			if (ints[i] == 0) {
				list.add(i);
				sum++;
			}
		}
		while (list.size() > 0) {
			Integer i = list.removeFirst();
			for (int[] p : prerequisites) {
				if (p[0] == i) {
					ints[p[1]]--;
					if (ints[p[1]] == 0) {
						list.add(p[1]);
						sum++;
					}
				}
			}
		}
		return sum == numCourses;
	}
	
	public int rangeBitwiseAnd1(int m, int n) {
		int count = 0;
		while (m != n) {
			count++;
			m >>= 1;
			n >>= 1;
		}
		return m << count;
	}
	
	public int rangeBitwiseAnd(int m, int n) {
		
		int ret = m;
		long i = 1;
		while (i <= m && ret > 0) {
			if ((m & i) > 0) {
				if (ret + i <= n) {
					ret -= i;
				}
			}
			i <<= 1;
		}
		return ret;
	}
	
	public List<String> findRepeatedDnaSequences1(String s) {
		if (s.length() <= 10) {
			return new ArrayList<>();
		}
		int[] ints = new int[26];
		ints['C' - 'A'] = 1;
		ints['G' - 'A'] = 2;
		ints['T' - 'A'] = 3;
		int i = 0;
		int base = 0, mask = 3;
		for (; i < 9; i++) {
			base <<= 2;
			mask <<= 2;
			base += ints[s.charAt(i) - 'A'];
			mask += 3;
		}
		int length = s.length();
		HashSet<Integer> set = new HashSet<>();
		HashSet<String> ret = new HashSet<>();
		for (; i < length; i++) {
			base <<= 2;
			base += ints[s.charAt(i) - 'A'];
			base = (base & mask);
			if (!set.add(base)) {
				ret.add(s.substring(i - 9, i));
			}
		}
		return new ArrayList<>(ret);
	}
	
	public List<String> findRepeatedDnaSequences(String s) {
		char[] array = s.toCharArray();
		HashSet<String> a = new HashSet<>();
		HashSet<String> b = new HashSet<>();
		for (int i = 0; i < array.length - 9; i++) {
			String t = new String(array, i, 10);
			if (a.contains(t)) {
				b.add(t);
			} else {
				a.add(t);
			}
		}
		return new ArrayList<>(b);
	}
	
	public String largestNumber(int[] nums) {
		if (nums.length < 1) {
			return "";
		}
		String[] strings = new String[nums.length];
		int length = nums.length;
		for (int i = 0; i < length; i++) {
			strings[i] = String.valueOf(nums[i]);
		}
		largestNumberSort(strings, 0, length - 1);
		if ("0".equals(strings[0])) {
			return "0";
		}
		StringBuffer buffer = new StringBuffer();
		for (String s : strings) {
			buffer.append(s);
		}
		return buffer.toString();
	}
	
	private void largestNumberSort(String[] strings, int l, int r) {
		if (l >= r) {
			return;
		}
		int nl = l + 1, nr = r;
		String cur = strings[l];
		int c = l;
		while (nl <= nr) {
			while (nl <= nr && largestNumberCompare(cur, 0, strings[nr], 0)) {
				nr--;
			}
			if (nl <= nr) {
				strings[c] = strings[nr];
				c = nr;
				nr--;
			}
			while (nl <= nr && largestNumberCompare(strings[nl], 0, cur, 0)) {
				nl++;
			}
			if (nl <= nr) {
				strings[c] = strings[nl];
				c = nl;
				nl++;
			}
		}
		strings[c] = cur;
		largestNumberSort(strings, l, c - 1);
		largestNumberSort(strings, c + 1, r);
	}
	
	private boolean largestNumberCompare(String l, int i, String r, int j) {
		int m = l.length();
		int n = r.length();
		while (i < m && j < n && l.charAt(i) == r.charAt(j)) {
			i++;
			j++;
		}
		if (i < m && j < n) {
			return l.charAt(i) > r.charAt(j);
		} else if (i < m) {
			return largestNumberCompare(l, i, r, 0);
		} else if (j < n) {
			return largestNumberCompare(l, 0, r, j);
		} else {
			return true;
		}
	}
	
	public int compareVersion(String version1, String version2) {
		char[] a = version1.toCharArray();
		char[] b = version2.toCharArray();
		int i = 0, j = 0, l, r;
		int al = a.length;
		int bl = b.length;
		while (i < al && j < bl) {
			l = a[i++] - '0';
			r = b[j++] - '0';
			while (i < al && a[i] != '.') {
				l = l * 10 + a[i] - '0';
				i++;
			}
			while (j < bl && b[j] != '.') {
				r = r * 10 + b[j] - '0';
				j++;
			}
			if (l != r) {
				return l < r ? -1 : 1;
			}
			i++;
			j++;
		}
		if (i < al) {
			while (i < al) {
				if (a[i] == '0' || a[i] == '.') {
					i++;
				} else {
					return 1;
				}
			}
		}
		if (j < bl) {
			while (j < bl) {
				if (b[j] == '0' || b[j] == '.') {
					j++;
				} else {
					return -1;
				}
			}
		}
		return 0;
	}
	
	public int maxProduct(int[] nums) {
		int length = nums.length;
		if (length < 1) {
			return 0;
		}
		if (length < 2) {
			return nums[0];
		}
		int ret = 0, cur = 0, a = 0;
		for (int i = 0; i < length; i++) {
			if (nums[i] == 0) {
				cur = a = 0;
			} else {
				if (cur == 0) {
					cur = nums[i];
				} else {
					cur *= nums[i];
				}
				if (cur < 0) {
					if (a == 0) {
						a = cur;
					} else {
						ret = Math.max(cur / a, ret);
					}
				} else {
					ret = Math.max(cur, ret);
				}
			}
		}
		return ret;
	}
	
	public String reverseWords(String s) {
		char[] array = s.toCharArray();
		int idx = array.length - 1;
		StringBuffer buffer = new StringBuffer();
		int r;
		while (idx >= 0) {
			if (array[idx] == ' ') {
				idx--;
				continue;
			}
			r = 1;
			idx--;
			while (idx >= 0 && array[idx] != ' ') {
				r++;
				idx--;
			}
			if (idx >= 0) {
				buffer.append(array, idx, r + 1);
			} else {
				buffer.append(" ");
				buffer.append(array, idx + 1, r);
			}
		}
		if (buffer.length() > 0) {
			buffer.deleteCharAt(0);
		}
		return buffer.toString();
	}
	
	class Tire {
		
		String cur;
		int val;
		Tire left;
		Tire right;
		
		public Tire(String cur) {
			this.cur = cur;
		}
	}
	
	int idx;
	
	public int evalRPN(String[] tokens) {
		idx = tokens.length - 2;
		HashSet<String> set = new HashSet();
		set.add("+");
		set.add("-");
		set.add("*");
		set.add("/");
		if (!set.contains(tokens[idx + 1])) {
			return Integer.parseInt(tokens[idx + 1]);
		}
		Tire root = new Tire(tokens[idx + 1]);
		evalRPNBuildTree(root, set, tokens);
		return evalRPNHelper(root);
	}
	
	private int evalRPNHelper(Tire root) {
		if (root.left == null && root.right == null) {
			return root.val;
		}
		int l, r;
		l = evalRPNHelper(root.left);
		r = evalRPNHelper(root.right);
		switch (root.cur) {
			case "+":
				root.val = l + r;
				break;
			case "-":
				root.val = l - r;
				break;
			case "*":
				root.val = l * r;
				break;
			case "/":
				root.val = l / r;
		}
		return root.val;
	}
	
	private void evalRPNBuildTree(Tire root, HashSet<String> set, String[] tokens) {
		if (idx >= 0) {
			if (set.contains(tokens[idx])) {
				root.right = new Tire(tokens[idx]);
				idx--;
				evalRPNBuildTree(root.right, set, tokens);
			} else {
				root.right = new Tire(tokens[idx]);
				root.right.val = Integer.valueOf(tokens[idx]);
				idx--;
			}
		}
		if (idx >= 0) {
			if (set.contains(tokens[idx])) {
				root.left = new Tire(tokens[idx]);
				idx--;
				evalRPNBuildTree(root.left, set, tokens);
			} else {
				root.left = new Tire(tokens[idx]);
				root.left.val = Integer.valueOf(tokens[idx]);
				idx--;
				return;
			}
		}
	}
	
	public ListNode sortList2(ListNode head) {
		ListNode f = head;
		ListNode s = head;
		while (f.next != null && f.next.next != null) {
			f = f.next.next;
			s = s.next;
		}
		
		ListNode r = s.next;
		s.next = null;
		ListNode l = head;
		l = sortList2(l);
		r = sortList2(r);
		return sortListHelper(l, r);
	}
	
	private ListNode sortListHelper(ListNode l, ListNode r) {
		if (l == null) {
			return r;
		} else if (r == null) {
			return l;
		}
		ListNode ret = new ListNode(0);
		ListNode t = ret;
		while (l != null && r != null) {
			if (l.val < r.val) {
				t.next = l;
				l = l.next;
			} else {
				t.next = r;
				r = r.next;
			}
			t = t.next;
		}
		if (l == null) {
			t.next = r;
		} else {
			t.next = l;
		}
		return ret.next;
	}
	
	public ListNode sortList1(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode m = head;
		ListNode next = head.next;
		ListNode l = new ListNode(0);
		ListNode l1 = l;
		ListNode r = new ListNode(0);
		ListNode r1 = r;
		while (next != null) {
			if (next.val < m.val) {
				l1.next = next;
				l1 = l1.next;
			} else if (next.val > m.val) {
				r1.next = next;
				r1 = r1.next;
			} else {
				m.next = next;
				m = m.next;
			}
			next = next.next;
		}
		l1.next = null;
		r1.next = null;
		if (l.next == null) {
			m.next = sortList1(r.next);
			return head;
		} else {
			ListNode x = sortList1(l.next);
			ListNode t = x;
			while (t.next != null) {
				t = t.next;
			}
			t.next = head;
			m.next = sortList1(r.next);
			return x;
		}
	}
	
	public ListNode sortList(ListNode head) {
		ListNode t = head;
		ArrayList<Integer> list = new ArrayList<>();
		while (t != null) {
			list.add(t.val);
			t = t.next;
		}
		Integer[] ints = new Integer[list.size()];
		list.toArray(ints);
		Arrays.sort(ints);
		t = head;
		for (Integer val : ints) {
			t.val = val;
			t = t.next;
		}
		return head;
	}
	
	public ListNode insertionSortList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode next, temp, before;
		ListNode cur = head.next;
		head.next = null;
		while (cur != null) {
			next = cur.next;
			temp = head;
			before = null;
			while (temp != null && temp.val < cur.val) {
				before = temp;
				temp = temp.next;
			}
			if (before == null) {
				cur.next = head;
				head = cur;
			} else if (temp != cur) {
				before.next = cur;
				cur.next = temp;
			}
			cur = next;
		}
		return head;
	}
	
	public boolean wordBreak(String s, List<String> wordDict) {
		if (wordDict.size() == 0) {
			return s.length() == 0;
		}
		boolean[] flag = new boolean[s.length() + 1];
		flag[0] = true;
		HashSet<String> dict = new HashSet<>(wordDict);
		int min = wordDict.get(0).length();
		int max = wordDict.get(0).length();
		for (String t : dict) {
			int length = t.length();
			if (length > max) {
				max = length;
			} else if (length < min) {
				min = length;
			}
		}
		char[] array = s.toCharArray();
		int length = flag.length;
		for (int i = 0; i < length; i++) {
			if (flag[i]) {
				int l = min;
				while (i + l < length && l <= max) {
					if (!flag[i + l]) {
						String t = new String(array, i, l);
						if (dict.contains(t)) {
							flag[i + l] = true;
							if (i + l == length - 1) {
								return true;
							}
						}
					}
					l++;
				}
			}
		}
		return flag[s.length()];
	}
	
	public void solve(char[][] board) {
		int n = board.length;
		if (n < 1) {
			return;
		}
		int m = board[0].length;
		if (m < 1) {
			return;
		}
		for (int i = 0; i < n; i++) {
			if (board[i][0] == 'O') {
				solveHelper(i, 0, board);
			}
			if (board[i][m - 1] == 'O') {
				solveHelper(i, m - 1, board);
			}
		}
		for (int i = 0; i < m; i++) {
			if (board[0][i] == 'O') {
				solveHelper(0, i, board);
			}
			if (board[n - 1][i] == 'O') {
				solveHelper(n - 1, i, board);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == 'A') {
					board[i][j] = 'O';
				} else if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
	}
	
	private void solveHelper(int i, int j, char[][] board) {
		if (board[i][j] == 'A') {
			return;
		}
		board[i][j] = 'A';
		if (i > 0 && board[i - 1][j] == 'O') {
			solveHelper(i - 1, j, board);
			
		}
		if (i < board.length - 1 && board[i + 1][j] == 'O') {
			solveHelper(i + 1, j, board);
		}
		if (j > 0 && board[i][j - 1] == 'O') {
			solveHelper(i, j - 1, board);
			
		}
		if (j < board[0].length - 1 && board[i][j + 1] == 'O') {
			solveHelper(i, j + 1, board);
		}
	}
	
	public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
		HashSet<String> dict = new HashSet<>(wordList);
		if (!dict.contains(endWord)) {
			return 0;
		}
		dict.remove(endWord);
		HashSet<String> a = new HashSet<>();
		a.add(beginWord);
		HashSet<String> b = new HashSet<>();
		HashSet<String> temp = new HashSet<>();
		b.add(endWord);
		int ret = 1;
		int l = beginWord.length();
		while (a.size() > 0) {
			ret++;
			for (String s : a) {
				char[] arr = s.toCharArray();
				for (int i = 0; i < l; i++) {
					char c = arr[i];
					for (char j = 'a'; j <= 'z'; j++) {
						arr[i] = j;
						String t = new String(arr);
						if (b.contains(t)) {
							return ret;
						} else if (dict.contains(t)) {
							temp.add(t);
							dict.remove(t);
						}
					}
					arr[i] = c;
				}
			}
			if (temp.size() > b.size()) {
				a = b;
				b = temp;
			} else {
				a = temp;
			}
			temp = new HashSet<>();
		}
		return 0;
	}
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		if (!wordList.contains(endWord)) {
			return 0;
		}
		ArrayList<String> cur = new ArrayList<>();
		int ret = 1;
		cur.add(beginWord);
		ArrayList<String> next = new ArrayList<>();
		int k = beginWord.length(), c;
		while (cur.size() > 0) {
			ret++;
			for (String s : cur) {
				for (String w : wordList) {
					c = 0;
					for (int i = 0; i < k; i++) {
						if (s.charAt(i) != w.charAt(i)) {
							c++;
							if (c > 1) {
								break;
							}
						}
					}
					if (c == 1) {
						if (w.equals(endWord)) {
							return ret;
						}
						next.add(w);
					}
				}
			}
			wordList.removeAll(next);
			cur = next;
			next = new ArrayList<>();
		}
		return ret;
	}
	
	int maxPathSum = 0;
	
	public int maxPathSum(TreeNode root) {
		maxPathSum = root.val;
		maxPathSumHelper(root);
		return maxPathSum;
	}
	
	public int minimumTotal(List<List<Integer>> triangle) {
		if (triangle == null) {
			return 0;
		}
		int size = triangle.size();
		
		int[] a = new int[size];
		int[] b = new int[size];
		int[] t;
		a[0] = triangle.get(0).get(0);
		for (int i = 1; i < size; i++) {
			List<Integer> list = triangle.get(i);
			int s = list.size() - 1;
			b[0] = a[0] + list.get(0);
			b[s] = a[s - 1] + list.get(s);
			for (int j = 1; j < s; j++) {
				b[j] = list.get(j) + Math.min(a[j], a[j - 1]);
			}
			t = a;
			a = b;
			b = t;
		}
		int ret = a[0];
		for (int i : a) {
			if (ret < i) {
				ret = i;
			}
		}
		return ret;
	}
	
	public int canCompleteCircuit1(int[] gas, int[] cost) {
		int t = 0;
		int cur = 0;
		int ret = 0;
		for (int i = 0; i < gas.length; i++) {
			int tmp = gas[i] - cost[i];
			t += tmp;
			cur += tmp;
			if (cur < 0) {
				cur = 0;
				ret = i + 1;
			}
		}
		return t > 0 ? ret : -1;
	}
	
	public int canCompleteCircuit(int[] gas, int[] cost) {
		int length = gas.length;
		for (int i = 0; i < length; i++) {
			gas[i] -= cost[i];
		}
		for (int i = 0; i < length; i++) {
			if (gas[i] >= 0) {
				int n = (i + 1) % length;
				int cur = gas[i];
				while (n != i) {
					cur += gas[n];
					if (cur < 0) {
						break;
					}
					n++;
					n %= length;
				}
				if (i == n) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public Node connect2(Node root) {
		if (root == null) {
			return null;
		}
		Node ret = root;
		while (root != null) {
			Node head = new Node();
			Node tail = head;
			while (root != null) {
				if (root.left != null) {
					tail.next = root.left;
					tail = tail.next;
				}
				if (root.right != null) {
					tail.next = root.right;
					tail = tail.next;
				}
				root = root.next;
			}
			root = head.next;
		}
		return ret;
	}
	
	private int maxPathSumHelper(TreeNode root) {
		if (root == null) {
			return 0;
		}
		int l = maxPathSumHelper(root.left);
		int r = maxPathSumHelper(root.right);
		int max = root.val;
		if (l > 0) {
			max += l;
		}
		if (r > 0) {
			max += r;
		}
		if (max > maxPathSum) {
			maxPathSum = max;
		}
		int x = Math.max(l, r);
		return root.val + (x > 0 ? x : 0);
	}
	
	class Node {
		
		public int val;
		public Node left;
		public Node right;
		public Node next;
		
		public Node() {
		}
		
		public Node(int _val, Node _left, Node _right, Node _next) {
			val = _val;
			left = _left;
			right = _right;
			next = _next;
		}
	}
	
	public static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
		
	}
	
	public Node connect1(Node root) {
		if (root == null) {
			return null;
		}
		if (root.left == null && root.right == null) {
			return root;
		}
		root.left.next = root.right;
		if (root.next != null) {
			root.right.next = root.next.left;
		}
		
		connect1(root.left);
		connect1(root.right);
		
		return root;
	}
	
	public Node connect(Node root) {
		if (root == null) {
			return root;
		}
		HashMap<Integer, Node> map = new HashMap<>();
		connectHelper(root, 0, map);
		return root;
	}
	
	private void connectHelper(Node root, int i, HashMap<Integer, Node> map) {
		if (root.left != null) {
			if (map.get(i) == null) {
				map.put(i, root.left);
			} else {
				map.get(i).next = root.left;
				map.put(i, root.left);
			}
			connectHelper(root.left, i + 1, map);
		}
		if (root.right != null) {
			if (map.get(i) == null) {
				map.put(i, root.right);
			} else {
				map.get(i).next = root.right;
				map.put(i, root.right);
			}
			connectHelper(root.right, i + 1, map);
		}
	}
}
