package com.leetcode;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.xml.crypto.Data;
import sun.swing.MenuItemLayoutHelper.ColumnAlignment;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Test11
 * @Author: WuMing
 * @CreateDate: 2019/10/22 9:17
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test11 {
	
	public static void main(String[] args) {
		
		Test11 test = new Test11();
		/*test.equationsPossible(new String[]{"b!=f", "c!=e", "f==f", "d==f", "b==f", "a==f"});
		test.brokenCalc(2, 10);
		test.numMovesStones(7, 4, 1);
		test.isBoomerang(new int[][]{{1, 1}, {2, 2}, {3, 3}});*/
		//test.lastStoneWeightII(new int[]{21, 26, 33, 31, 40});
		//test.addNegabinary(new int[]{0}, new int[]{0});
		//Data data = new Data();
		test.defangIPaddr("1.1.1.1");
	}
	
	public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
		HashSet<Integer> set = new HashSet<>();
		for (int i : to_delete) {
			set.add(i);
		}
		ArrayList<TreeNode> ret = new ArrayList<>();
		TreeNode node = delNodesHelper(ret, root, set);
		if (node != null) {
			if(!set.contains(node.val)){
				ret.add(node);
			}
		}
		return ret;
	}
	
	private TreeNode delNodesHelper(ArrayList<TreeNode> ret, TreeNode root, HashSet<Integer> set) {
		TreeNode left = null, right = null;
		if (root.left != null) {
			left = delNodesHelper(ret, root.left, set);
		}
		if (root.right != null) {
			right = delNodesHelper(ret, root.right, set);
		}
		if (set.contains(root.val)) {
			if (left != null) {
				ret.add(left);
			}
			if (right != null) {
				ret.add(right);
			}
			return null;
		} else {
			root.left = left;
			root.right = right;
			return root;
		}
	}
	
	public int[] corpFlightBookings(int[][] bookings, int n) {
		int[] ints = new int[n + 2];
		for (int[] booking : bookings) {
			ints[booking[0]] += booking[2];
			ints[booking[1] + 1] -= booking[2];
		}
		int[] ret = new int[n];
		int sum = 0;
		for (int i = 0; i < n; i++) {
			sum += ints[i + 1];
			ret[i] = sum;
		}
		return ret;
	}
	
	public String defangIPaddr(String address) {
		StringBuffer buffer = new StringBuffer();
		for (char c : address.toCharArray()) {
			if (c == '.') {
				buffer.append("[.]");
			} else {
				buffer.append(c);
			}
		}
		return buffer.toString();
	}
	
	public int minHeightShelves(int[][] books, int shelf_width) {
		int len = books.length;
		int[] hight = new int[len];
		hight[0] = books[0][1];
		int h, w;
		for (int i = 1; i < len; i++) {
			h = books[i][1];
			w = books[i][0];
			hight[i] = h + hight[i - 1];
			for (int j = i - 1; j >= 0; j--) {
				if (w + books[j][0] <= shelf_width) {
					h = Math.max(h, books[j][1]);
					hight[i] = Math.min(hight[i], j > 0 ? h + hight[j - 1] : h);
					w += books[j][0];
				} else {
					break;
				}
			}
		}
		return hight[len - 1];
	}
	
	public List<Integer> pathInZigZagTree(int label) {
		int i = 1;
		while (label > (1 << i) - 1) {
			i++;
		}
		LinkedList<Integer> ret = new LinkedList<>();
		ret.add(label);
		int lab = label, idx;
		while (lab != 1) {
			idx = getUpIdxByLabel(i, lab);
			i--;
			lab = getCurLabelByIdx(i, idx);
			ret.addFirst(lab);
		}
		return ret;
		
	}
	
	private int getCurLabelByIdx(int i, int idx) {
		if ((i & 1) != 1) {
			return (1 << i) - idx - 1;
		} else {
			return (1 << (i - 1)) + idx;
		}
	}
	
	private int getUpIdxByLabel(int i, int label) {
		if ((i & 1) != 1) {
			return ((1 << i) - 1 - label) >> 1;
		} else {
			return (label - (1 << (i - 1))) >> 1;
		}
	}
	
	public int[] distributeCandies(int candies, int num_people) {
		int[] ret = new int[num_people];
		int l = 1, r = num_people;
		int cur = num_people * (l + r) / 2;
		int base = 0, last = candies, time = 0;
		while (cur < last) {
			last -= cur;
			base += l;
			l = r + 1;
			r = r + num_people;
			cur = num_people * (l + r) / 2;
			time++;
		}
		if (base > 0) {
			for (int i = 0; i < num_people; i++) {
				ret[i] = base + i * time;
			}
		}
		for (int i = 0; i < num_people; i++) {
			if (last > l) {
				ret[i] += l;
				last -= l;
				l++;
			} else {
				ret[i] += last;
				return ret;
			}
		}
		return ret;
	}
	
	public boolean carPooling(int[][] trips, int capacity) {
		Arrays.sort(trips, (a, b) -> (a[1] - b[1]));
		int[] ints = new int[1001];
		int idx = 0;
		int len = trips.length;
		for (int i = 0; i < 1001; i++) {
			capacity += ints[i];
			while (idx < len && trips[idx][1] == i) {
				capacity -= trips[idx][0];
				if (capacity < 0) {
					return false;
				}
				ints[trips[idx][2]] += trips[idx][0];
				idx++;
			}
			if (idx == len) {
				break;
			}
		}
		return true;
	}
	
	public int shortestPathBinaryMatrix(int[][] grid) {
		if (grid[0][0] == 1) {
			return -1;
		}
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		list.add(0);
		grid[0][0] = -1;
		int[][] ds = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
		int i, j, x, y, hi = grid.length, len = grid[0].length;
		while (!list.isEmpty()) {
			i = list.removeFirst();
			j = list.removeFirst();
			for (int[] d : ds) {
				x = i + d[0];
				y = j + d[1];
				if (x < 0 || y < 0 || x >= hi || y >= len || grid[x][y] != 0) {
					continue;
				}
				grid[x][y] = grid[i][j] - 1;
				list.add(x);
				list.add(y);
				if (x == hi - 1 && y == len - 1) {
					break;
				}
			}
		}
		int ret = grid[hi - 1][len - 1];
		return ret < 0 ? -ret : -1;
	}
	
	public int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {
		int len = values.length;
		largestValsFromLabelsSort(values, labels, 0, len - 1);
		int ret = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < len; i++) {
			if (num_wanted == 0) {
				break;
			}
			if (map.containsKey(labels[i])) {
				Integer count = map.get(labels[i]);
				if (count < use_limit) {
					num_wanted--;
					ret += values[i];
					map.put(labels[i], count + 1);
				}
			} else {
				num_wanted--;
				ret += values[i];
				map.put(labels[i], 1);
			}
		}
		return ret;
	}
	
	private void largestValsFromLabelsSort(int[] values, int[] labels, int start, int end) {
		if (end <= start) {
			return;
		}
		int cur = start;
		int l = start + 1, r = end;
		while (l <= r) {
			while (l <= r && values[cur] >= values[r]) {
				r--;
			}
			if (l <= r) {
				largestValsFromLabelsSwap(values, labels, cur, r);
				cur = r;
			}
			while (l <= r && values[cur] < values[l]) {
				l++;
			}
			if (l <= r) {
				largestValsFromLabelsSwap(values, labels, cur, l);
				cur = l;
			}
		}
		largestValsFromLabelsSort(values, labels, start, cur - 1);
		largestValsFromLabelsSort(values, labels, cur + 1, end);
	}
	
	private void largestValsFromLabelsSwap(int[] values, int[] labels, int cur, int r) {
		int x = values[cur];
		values[cur] = values[r];
		values[r] = x;
		x = labels[cur];
		labels[cur] = labels[r];
		labels[r] = x;
	}
	
	public String smallestSubsequence1(String text) {
		int[] last = new int[26];
		int len = text.length();
		for (int i = 0; i < len; i++) {
			last[text.charAt(i) - 'a'] = i;
		}
		char[] ret = new char[26];
		int idx = 0;
		boolean[] flag = new boolean[26];
		for (int i = 0; i < len; i++) {
			char c = text.charAt(i);
			if (flag[c - 'a']) {
				continue;
			}
			while (idx > 0 && ret[idx - 1] > c && last[ret[idx - 1] - 'a'] > i) {
				flag[ret[idx - 1] - 'a'] = false;
				idx--;
			}
			ret[idx] = c;
			flag[c - 'a'] = true;
			idx++;
			
		}
		return new String(ret, 0, idx);
	}
	
	public String smallestSubsequence(String text) {
		int[] count = new int[26];
		char[] array = text.toCharArray();
		for (char c : array) {
			count[c - 'a']++;
		}
		char[] ret = new char[26];
		boolean[] flag = new boolean[26];
		ret[0] = array[0];
		flag[ret[0] - 'a'] = true;
		count[array[0] - 'a']--;
		int idx = 0;
		int len = array.length;
		for (int i = 1; i < len; i++) {
			while (idx >= 0 && array[i] < ret[idx] && count[ret[idx] - 'a'] > 0) {
				flag[ret[idx] - 'a'] = false;
				idx--;
			}
			if (idx < 0 || !flag[array[i] - 'a']) {
				idx++;
				ret[idx] = array[i];
				flag[array[i] - 'a'] = true;
			}
			count[array[i] - 'a']--;
		}
		return new String(ret, 0, idx);
	}
	
	int numTilePossibilities = 0;
	
	public int numTilePossibilities(String tiles) {
		int[] count = new int[26];
		for (char c : tiles.toCharArray()) {
			count[c - 'A']++;
		}
		numTilePossibilitiesHelper(count);
		return numTilePossibilities;
	}
	
	public TreeNode sufficientSubset(TreeNode root, int limit) {
		int x = sufficientSubsetHelper(root, 0, limit);
		if (x < limit) {
			return null;
		} else {
			return root;
		}
	}
	
	private int sufficientSubsetHelper(TreeNode root, int i, int limit) {
		if (root.left == null && root.right == null) {
			return i + root.val;
		}
		if (root.left != null) {
			if (sufficientSubsetHelper(root.left, i + root.val, limit) < limit) {
				root.left = null;
			}
		}
		if (root.right != null) {
			if (sufficientSubsetHelper(root.right, i + root.val, limit) < limit) {
				root.right = null;
			}
		}
		if (root.left == null && root.right == null) {
			return limit - 1;
		}
		return Integer.MAX_VALUE;
	}
	
	private void numTilePossibilitiesHelper(int[] count) {
		for (int i = 0; i < 26; i++) {
			if (count[i] > 0) {
				numTilePossibilities++;
				count[i]--;
				numTilePossibilitiesHelper(count);
				count[i]++;
			}
		}
	}
	
	public void duplicateZeros(int[] arr) {
		int len = arr.length;
		int count = 0;
		int i = 0;
		while (count < len) {
			if (arr[i] == 0) {
				count++;
			}
			count++;
			i++;
		}
		if (i == count) {
			return;
		}
		i--;
		int j = len - 1;
		if (count > len) {
			arr[j] = 0;
			i--;
			j--;
		}
		for (; j > i; j--) {
			if (arr[i] == 0) {
				arr[j] = arr[i];
				j--;
			}
			arr[j] = arr[i];
			i--;
		}
		
	}
	
	public String[] findOcurrences1(String text, String first, String second) {
		String target = first + ' ' + second + ' ';
		int maxl = text.length();
		int len = target.length();
		ArrayList<String> ret = new ArrayList<>();
		if (text.startsWith(target)) {
			int end = len;
			while (end < maxl && text.charAt(end) != ' ') {
				end++;
			}
			ret.add(text.substring(len, end));
		}
		target = ' ' + target;
		len++;
		int begin = 0;
		int idx = text.indexOf(target, begin);
		while (idx > 0) {
			int l = idx + len, r = l + 1;
			while (r < maxl && text.charAt(r) != ' ') {
				r++;
			}
			ret.add(text.substring(l, r));
			begin = idx + 1;
			idx = text.indexOf(target, begin);
		}
		return ret.toArray(new String[0]);
	}
	
	public String[] findOcurrences(String text, String first, String second) {
		int len1 = text.length();
		int len2 = first.length();
		int len3 = second.length();
		ArrayList<String> ret = new ArrayList<>();
		for (int i = 0; i < len1; ) {
			int j = 0;
			if (text.charAt(i) == ' ') {
				i++;
			}
			for (; j < len2 && i < len1; j++) {
				if (text.charAt(i) != first.charAt(j)) {
					break;
				}
				i++;
			}
			if (j < len2 || text.charAt(i) != ' ') {
				while (i < len1 && text.charAt(i) != ' ') {
					i++;
				}
				continue;
			} else {
				i++;
			}
			int k = 0, t = i;
			for (; k < len3 && t < len1; k++) {
				if (text.charAt(t) != second.charAt(k)) {
					break;
				}
				t++;
			}
			if (k < len3 || text.charAt(t) != ' ') {
				continue;
			}
			while (t < len2 && text.charAt(t) == ' ') {
				t++;
			}
			i = t;
			while (t < len2 && text.charAt(t) != ' ') {
				t++;
			}
			if (i < t) {
				ret.add(text.substring(i, t));
			}
		}
		return ret.toArray(new String[0]);
	}
	
	public int[] addNegabinary(int[] arr1, int[] arr2) {
		int len1 = arr1.length;
		int len2 = arr2.length;
		if (len1 < len2) {
			return addNegabinary(arr2, arr1);
		}
		int[] ret = new int[len1 + 2];
		int i = len2 - 1, j = len1 - 1, k = len1 + 1;
		for (; i >= 0; i--, j--, k--) {
			ret[k] = ret[k] + arr1[j] + arr2[i];
			if (ret[k] == 4) {
				ret[k] = 0;
				ret[k - 2]++;
			} else if (ret[k] == 3) {
				ret[k] = 1;
				ret[k - 1]++;
				ret[k - 2]++;
			} else if (ret[k] == 2) {
				ret[k] = 0;
				ret[k - 1]++;
				ret[k - 2]++;
			}
		}
		for (; j >= 0; j--, k--) {
			ret[k] = ret[k] + arr1[j];
			if (ret[k] == 4) {
				ret[k] = 0;
				ret[k - 2]++;
			} else if (ret[k] == 3) {
				ret[k] = 1;
				ret[k - 1]++;
				ret[k - 2]++;
			} else if (ret[k] == 2) {
				ret[k] = 0;
				ret[k - 1]++;
				ret[k - 2]++;
			}
		}
		for (; k >= 0; k--) {
			if (ret[k] == 2) {
				ret[k - 1]--;
			}
		}
		int x = 0;
		while (x < len1 + 2 && ret[x] == 0) {
			x++;
		}
		if (x == len1 + 2) {
			return new int[]{0};
		}
		return x == 0 ? ret : Arrays.copyOfRange(ret, x, len1 + 2);
	}
	
	public int maxEqualRowsAfterFlips2(int[][] matrix) {
		int len = matrix[0].length, ret = 0;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int[] row : matrix) {
			if (row[0] == 1) {
				for (int i = 0; i < len; i++) {
					row[i] ^= 1;
				}
			}
			int key = Arrays.hashCode(row);
			Integer count = map.getOrDefault(key, 0);
			map.put(key, count + 1);
			ret = Math.max(ret, count + 1);
		}
		return ret;
	}
	
	public int maxEqualRowsAfterFlips1(int[][] matrix) {
		int hi = matrix.length;
		int len = matrix[0].length;
		for (int i = 0; i < hi; i++) {
			if (matrix[i][0] == 0) {
				continue;
			}
			for (int j = 0; j < len; j++) {
				matrix[i][j] ^= 1;
			}
		}
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < hi; i++) {
			list.add(i);
		}
		return maxEqualRowsAfterFlips1Helper(matrix, list, 1, len);
	}
	
	private int maxEqualRowsAfterFlips1Helper(int[][] matrix, ArrayList<Integer> list, int i,
		int len) {
		if (i == len || list.size() < 2) {
			return list.size();
		}
		ArrayList<Integer> a = new ArrayList<>();
		ArrayList<Integer> b = new ArrayList<>();
		for (Integer r : list) {
			if (matrix[r][i] == 0) {
				a.add(r);
			} else {
				b.add(r);
			}
		}
		ArrayList<Integer> c;
		int sizeA = a.size();
		int sizeB = b.size();
		if (sizeA < sizeB) {
			c = a;
			a = b;
			b = c;
		}
		int x = maxEqualRowsAfterFlips1Helper(matrix, a, i + 1, len);
		if (x >= b.size()) {
			return x;
		}
		return Math.max(x, maxEqualRowsAfterFlips1Helper(matrix, b, i + 1, len));
	}
	
	public int maxEqualRowsAfterFlips(int[][] matrix) {
		int len = matrix.length;
		boolean[] flag = new boolean[len];
		int max = 0, count;
		for (int i = 0; i < len; i++) {
			if (flag[i]) {
				continue;
			}
			count = 1;
			for (int j = i + 1; j < len; j++) {
				if (flag[j]) {
					continue;
				}
				if (maxEqualRowsAfterFlipsHelper(matrix[i], matrix[j])) {
					count++;
					flag[j] = true;
				}
			}
			max = Math.max(max, count);
		}
		return max;
	}
	
	private boolean maxEqualRowsAfterFlipsHelper(int[] a, int[] b) {
		boolean flag = a[0] == b[0];
		for (int i = 1; i < a.length; i++) {
			if (flag != (a[i] == b[i])) {
				return false;
			}
		}
		return true;
	}
	
	public String gcdOfStrings(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		char[] s1 = str1.toCharArray();
		char[] s2 = str2.toCharArray();
		if (gcdOfStringsHelper(s1, len1, s2, len2)) {
			return str2;
		}
		for (int i = 2; i <= len2 / 2; i++) {
			if (gcdOfStringsHelper(s2, len2, s2, len2 / i)) {
				if (gcdOfStringsHelper(s1, len1, s2, len2 / i)) {
					return new String(s2, 0, len2 / i);
				}
			}
		}
		return "";
	}
	
	private boolean gcdOfStringsHelper(char[] s1, int len1, char[] s2, int len2) {
		if (len1 % len2 != 0) {
			return false;
		}
		int step = len1 / len2;
		for (int i = 0; i < len2; i++) {
			char c = s2[i];
			for (int j = 0; j < step; j++) {
				if (s1[i + j * len2] != c) {
					return false;
				}
			}
		}
		return true;
	}
	
	public int[] rearrangeBarcodes1(int[] barcodes) {
		int[] count = new int[10001];
		int max = 0;
		for (int barcode : barcodes) {
			count[barcode]++;
			if (count[barcode] > count[max]) {
				max = barcode;
			}
		}
		int i = 0, len = barcodes.length;
		while (count[max] > 0) {
			barcodes[i] = max;
			i += 2;
			count[max]--;
		}
		int cur = 0;
		while (i < len) {
			while (count[cur] == 0) {
				cur++;
			}
			barcodes[i] = cur;
			i += 2;
			count[cur]--;
		}
		i = 1;
		while (i < len) {
			while (count[cur] == 0) {
				cur++;
			}
			barcodes[i] = cur;
			i += 2;
			count[cur]--;
		}
		return barcodes;
	}
	
	public int[] rearrangeBarcodes(int[] barcodes) {
		int[][] count = new int[10001][2];
		int len = barcodes.length, i;
		
		for (int barcode : barcodes) {
			count[barcode][0] = barcode;
			count[barcode][1]++;
		}
		Arrays.sort(count, (a, b) -> (b[1] - a[1]));
		i = 0;
		int cur = 0;
		while (i < len) {
			while (i < len && count[cur][1] > 0) {
				barcodes[i] = count[cur][0];
				i += 2;
				count[cur][1]--;
			}
			if (i < len) {
				cur++;
			}
		}
		i = 1;
		while (i < len) {
			while (i < len && count[cur][1] > 0) {
				barcodes[i] = count[cur][0];
				i += 2;
				count[cur][1]--;
			}
			if (i < len) {
				cur++;
			}
		}
		return barcodes;
	}
	
	public int maxSatisfied(int[] customers, int[] grumpy, int X) {
		int base = 0;
		int len = customers.length;
		for (int i = 0; i < len; i++) {
			if (grumpy[i] == 0) {
				base += customers[i];
			}
		}
		int max = 0, cur = 0;
		for (int i = 0; i < X; i++) {
			if (grumpy[i] == 1) {
				cur += customers[i];
			}
		}
		max = cur;
		for (int i = X; i < len; i++) {
			if (grumpy[i - X] == 1) {
				cur -= customers[i - X];
			}
			if (grumpy[i] == 1) {
				cur += customers[i];
			}
			max = Math.max(max, cur);
		}
		return base + max;
	}
	
	public int heightChecker(int[] heights) {
		int len = heights.length, ret = 0;
		int[] ints = Arrays.copyOf(heights, len);
		Arrays.sort(ints);
		for (int i = 0; i < len; i++) {
			if (heights[i] != ints[i]) {
				ret++;
			}
		}
		return ret;
	}
	
	public int lastStoneWeightII(int[] stones) {
		int sum = 0;
		for (int stone : stones) {
			sum += stone;
		}
		int[] dp = new int[(sum >> 1) + 1];
		for (int i = 0; i < stones.length; i++) {
			for (int j = sum >> 1; j >= stones[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - stones[i]] + stones[i]);
			}
		}
		return Math.abs(sum - (dp[sum >> 1] << 1));
	}
	
	public String removeDuplicates(String S) {
		int len = S.length();
		char[] ret = new char[len];
		ret[0] = S.charAt(0);
		int idx = 0;
		char cur;
		for (int i = 1; i < len; i++) {
			cur = S.charAt(i);
			if (idx < 0 || ret[idx] != cur) {
				idx++;
				ret[idx] = cur;
			} else {
				idx--;
			}
		}
		return new String(ret, 0, idx);
	}
	
	public int lastStoneWeight1(int[] stones) {
		int[] count = new int[1001];
		for (int stone : stones) {
			count[stone]++;
		}
		int s = 1000, c = 0, len = stones.length, y, x;
		while (count[s] == 0) {
			s--;
		}
		count[s]--;
		y = s;
		while (len - c >= 2) {
			while (count[s] == 0) {
				s--;
			}
			count[s]--;
			x = s;
			if (y > x) {
				c++;
				if (y - x >= s) {
					y = y - x;
				} else {
					count[y - x]++;
					while (count[s] == 0) {
						s--;
					}
					count[s]--;
					y = s;
				}
			} else {
				c += 2;
				while (s > 0 && count[s] == 0) {
					s--;
				}
				count[s]--;
				y = s;
			}
		}
		return y;
	}
	
	public int lastStoneWeight(int[] stones) {
		PriorityQueue<Integer> queue = new PriorityQueue<>(
			(a, b) -> (b - a)
		);
		for (int stone : stones) {
			queue.add(stone);
		}
		while (queue.size() > 1) {
			Integer y = queue.poll();
			Integer x = queue.poll();
			if (y > x) {
				queue.add(y - x);
			}
		}
		return queue.isEmpty() ? 0 : queue.poll();
	}
	
	public int maxSumAfterPartitioning1(int[] A, int K) {
		int len = A.length;
		int[] max = new int[len];
		int m = Integer.MIN_VALUE;
		for (int i = 0; i < K && i < len; i++) {
			m = Math.max(m, A[i]);
			max[i] = m * (i + 1);
		}
		for (int i = K; i < len; i++) {
			m = A[i];
			max[i] = max[i - 1] + m;
			for (int j = 2; j <= K; j++) {
				m = Math.max(m, A[i - j + 1]);
				max[i] = Math.max(max[i], max[i - j] + m * j);
			}
		}
		return max[len - 1];
	}
	
	public int maxSumAfterPartitioning(int[] A, int K) {
		int len = A.length;
		int[] max = new int[len];
		int m;
		for (int i = 0; i < len; i++) {
			m = A[i];
			for (int j = 1; j <= K && i - j + 1 >= 0; j++) {
				m = Math.max(m, A[i - j - 1]);
				max[i] = Math.max(max[i], j * m + i < j ? 0 : max[i - j]);
			}
		}
		return max[len - 1];
	}
	
	public int[] gardenNoAdj(int N, int[][] paths) {
		int[] flag = new int[N];
		int[] ret = new int[N];
		List<Integer>[] lists = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			lists[i] = new ArrayList();
		}
		for (int[] path : paths) {
			lists[path[0] - 1].add(path[1] - 1);
			lists[path[1] - 1].add(path[0] - 1);
		}
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (ret[i] == 0) {
				list.add(i);
				while (!list.isEmpty()) {
					Integer cur = list.removeFirst();
					int type = 0;
					while ((flag[cur] & (1 << type)) > 0) {
						type++;
					}
					ret[cur] = type + 1;
					for (Integer x : lists[cur]) {
						flag[x] |= (1 << type);
						if (ret[x] == 0) {
							list.add(x);
							ret[x] = -1;
						}
					}
				}
			}
		}
		
		return ret;
	}
	
	public int[] numMovesStonesII(int[] stones) {
		Arrays.sort(stones);
		int len = stones.length;
		int max = stones[len - 1] - stones[0] + 1 - len - Math
			.min(stones[len - 1] - stones[len - 2] - 1, stones[1] - stones[0] - 1);
		int min = Integer.MAX_VALUE;
		for (int i = 0, j = 0; i < len; i++) {
			while (j + 1 < len && stones[j + 1] - stones[i] < len) {
				j++;
			}
			int count = len - (j - i + 1);
			if (count == 1 && stones[j] - stones[i] == len - 2) {
				count = 2;
			}
			min = Math.min(min, count);
		}
		return new int[]{min, max};
	}
	
	public boolean isRobotBounded(String instructions) {
		int[][] ints = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		int cur = 0;
		int x = 0, y = 0;
		char[] array = instructions.toCharArray();
		for (int i = 0; i < 4; i++) {
			for (char c : array) {
				if (c == 'G') {
					x += ints[cur][0];
					y += ints[cur][1];
				} else if (c == 'L') {
					cur--;
					if (cur < 0) {
						cur += 4;
					}
				} else {
					cur++;
					if (cur > 3) {
						cur -= 4;
					}
				}
			}
			if (x == 0 && y == 0) {
				return true;
			}
		}
		return false;
	}
	
	public int minScoreTriangulation(int[] A) {
		int len = A.length;
		int[][] ints = new int[len][len];
		for (int i = 2; i < len; i++) {
			for (int j = i - 2; j >= 0; j--) {
				int min = Integer.MAX_VALUE;
				for (int k = j + 1; k < i; k++) {
					min = Math.min(min, ints[j][k] + ints[k][i] + A[i] * A[j] * A[k]);
				}
				ints[j][i] = min;
			}
		}
		return ints[0][len - 1];
	}
	
	public int brokenCalc(int x, int y) {
		if (x >= y) {
			return x - y;
		} else {
			return brokenCalc(x, (y + 1) >> 1) + (y & 1) + 1;
		}
	}
	
	public boolean isBoomerang(int[][] points) {
		int x1, y1, x2, y2, x3, y3;
		x1 = points[0][0];
		y1 = points[0][1];
		x2 = points[1][0];
		y2 = points[1][1];
		x3 = points[2][0];
		y3 = points[2][1];
		return (x1 * y2 + y1 * x3 + x2 * y3 - x1 * y3 - x2 * y1 - x3 * y2) != 0;
	}
	
	public TreeNode bstToGst(TreeNode root) {
		bstToGstHelper(root, 0);
		return root;
	}
	
	private int bstToGstHelper(TreeNode root, int i) {
		if (root == null) {
			return i;
		}
		root.val = i + bstToGstHelper(root.right, i);
		return bstToGstHelper(root.left, root.val);
	}
	
	public int[][] colorBorder(int[][] grid, int r0, int c0, int color) {
		int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		colorBorderHelper(grid, r0, c0, d, grid[r0][c0]);
		d = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
		int high = grid.length;
		int len = grid[0].length;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] < 0 && colorBorderHelper1(grid, i, j, d)) {
					grid[i][j] = -color;
				}
			}
		}
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (grid[i][j] < 0) {
					grid[i][j] *= -1;
				}
			}
		}
		return grid;
	}
	
	private boolean colorBorderHelper1(int[][] grid, int i, int j, int[][] ds) {
		int high = grid.length;
		int len = grid[0].length;
		int x, y;
		for (int[] d : ds) {
			x = i + d[0];
			y = j + d[1];
			if (x < 0 || y < 0 || x >= high || y >= len || grid[x][y] > 0) {
				return true;
			}
		}
		return false;
	}
	
	private void colorBorderHelper(int[][] grid, int i, int j, int[][] ds, int color) {
		int high = grid.length;
		int len = grid[0].length;
		grid[i][j] *= -1;
		int x, y;
		for (int[] d : ds) {
			x = i + d[0];
			y = j + d[1];
			if (x < 0 || y < 0 || x >= high || y >= len || grid[x][y] != color) {
				continue;
			}
			colorBorderHelper(grid, x, y, ds, color);
		}
	}
	
	public int[] numMovesStones(int a, int b, int c) {
		int[] ints = {a, b, c};
		Arrays.sort(ints);
		int min = 0;
		if (ints[1] - ints[0] == 2 || ints[2] - ints[1] == 2) {
			min = 1;
		} else {
			if (ints[1] - ints[0] > 2) {
				min++;
			}
			if (ints[2] - ints[1] > 2) {
				min++;
			}
		}
		int max = 0;
		max += ints[1] - ints[0] - 1;
		max += ints[2] - ints[1] - 1;
		return new int[]{min, max};
	}
	
	public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
		int len = R * C;
		int[][] ret = new int[len][2];
		int l = 0, r = 1, idx = 1;
		ret[0] = new int[]{r0, c0};
		boolean[][] visit = new boolean[R][C];
		visit[r0][c0] = true;
		int[][] ds = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
		while (idx < len) {
			for (int i = l; i < r; i++) {
				for (int[] d : ds) {
					int x = ret[i][0] + d[0];
					int y = ret[i][1] + d[1];
					if (x < 0 || y < 0 || x >= R || y >= C || visit[x][y]) {
						continue;
					}
					visit[x][y] = true;
					ret[idx][0] = x;
					ret[idx][1] = y;
					idx++;
				}
			}
			l = r;
			r = idx;
		}
		return ret;
	}
	
	public int smallestRepunitDivByK(int K) {
		boolean[] booleans = new boolean[K];
		int cur = 1, ret = 0;
		while (true) {
			ret++;
			cur %= K;
			if (cur == 0) {
				return ret;
			}
			if (booleans[cur]) {
				return -1;
			}
			booleans[cur] = true;
			cur *= 10;
			cur++;
		}
	}
	
	public boolean equationsPossible(String[] equations) {
		int[] ints = new int[26];
		for (int i = 0; i < 26; i++) {
			ints[i] = i;
		}
		for (String equation : equations) {
			if (equation.charAt(1) == '=') {
				int l = equation.charAt(0) - 'a';
				int r = equation.charAt(3) - 'a';
				int lb = equationsPossibleHelper(ints, l);
				int rb = equationsPossibleHelper(ints, r);
				ints[lb] = Math.min(lb, rb);
				ints[rb] = Math.min(lb, rb);
			}
		}
		for (String equation : equations) {
			if (equation.charAt(1) == '!') {
				int l = equation.charAt(0) - 'a';
				int r = equation.charAt(3) - 'a';
				int lb = equationsPossibleHelper(ints, l);
				int rb = equationsPossibleHelper(ints, r);
				if (lb == rb) {
					return false;
				}
			}
		}
		return true;
	}
	
	private int equationsPossibleHelper(int[] ints, int x) {
		while (ints[x] != x) {
			x = ints[x];
		}
		return x;
	}
	
	public String strWithout3a3b(int A, int B) {
		char[] array = new char[A + B];
		if (A > B) {
			strWithout3a3bHelper(array, A, B, 'a', 'b');
		} else {
			strWithout3a3bHelper(array, B, A, 'b', 'a');
		}
		return new String(array);
	}
	
	public List<List<Integer>> verticalTraversal2(TreeNode root) {
		TreeSet<Integer> set = new TreeSet<>();
		verticalTraversal1Helper(root, set, 0, 1000);
		TreeMap<Integer, ArrayList<Integer>> retMap = new TreeMap<>();
		for (Integer key : set) {
			int val = key % 10000;
			int col = (key / 10000) % 10000;
			ArrayList<Integer> list;
			if (retMap.containsKey(col)) {
				list = retMap.get(col);
			} else {
				list = new ArrayList<>();
				retMap.put(col, list);
			}
			list.add(val);
		}
		ArrayList<List<Integer>> ret = new ArrayList<>();
		ret.addAll(retMap.values());
		return ret;
	}
	
	private void verticalTraversal1Helper(TreeNode root, TreeSet<Integer> set, int row,
		int col) {
		if (root == null) {
			return;
		}
		int key = (row * 10000 + col) * 10000 + root.val;
		set.add(key);
		verticalTraversal1Helper(root.left, set, row + 1, col - 1);
		verticalTraversal1Helper(root.right, set, row + 1, col + 1);
	}
	
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		int high = getHeight(root, 0) - 1;
		TreeMap<Integer, ArrayList<Integer>> map = new TreeMap<>();
		verticalTraversalHelper(map, root, 1, (1 << high) + 1);
		ArrayList<List<Integer>> ret = new ArrayList<>();
		ret.addAll(map.values());
		return ret;
	}
	
	private void verticalTraversalHelper(TreeMap<Integer, ArrayList<Integer>> map, TreeNode root,
		int l, int r) {
		int m = (l + r) / 2;
		ArrayList<Integer> list;
		if (map.containsKey(m)) {
			list = map.get(m);
		} else {
			list = new ArrayList<>();
			map.put(m, list);
		}
		list.add(root.val);
		if (root.left != null) {
			verticalTraversalHelper(map, root.left, l, (m & 1) == 1 ? m : m - 1);
		}
		if (root.right != null) {
			verticalTraversalHelper(map, root.right, (m & 1) == 1 ? m : m + 1, r);
		}
	}
	
	private int getHeight(TreeNode root, int i) {
		if (root == null) {
			return i;
		}
		int l = getHeight(root.left, i + 1);
		int r = getHeight(root.right, i + 1);
		return Math.max(l, r);
	}
	
	private void strWithout3a3bHelper(char[] array, int a, int b, char a1, char b1) {
		int len = array.length;
		for (int i = 0; i < len; ) {
			if (a > b) {
				if (i + 3 < len) {
					array[i++] = a1;
					array[i++] = a1;
					array[i++] = b1;
				} else {
					if (i < len) {
						array[i++] = a1;
					}
					if (i < len) {
						array[i++] = a1;
					}
				}
				a--;
			} else {
				if (i + 2 < len) {
					array[i++] = a1;
					array[i++] = b1;
				} else {
					array[i++] = a1;
				}
			}
			a--;
			b--;
		}
	}
	
	public int subarraysDivByK(int[] A, int K) {
		int[] count = new int[K];
		int ret = 0, sum = 0, t;
		count[0] = 1;
		for (int i : A) {
			sum += i;
			sum %= K;
			t = sum < 0 ? sum + K : sum;
			ret += count[t];
			count[t]++;
		}
		return ret;
	}
	
	public void hanoiTower(int n, char a, char b, char c) {
		if (n == 1) {
			System.out.println(a + "-->" + c);
		} else {
			hanoiTower(n - 1, a, c, b);
			System.out.println(a + "-->" + c);
			hanoiTower(n - 1, b, a, c);
		}
	}
	
	int x;
	
	public List<Integer> flipMatchVoyage(TreeNode root, int[] voyage) {
		ArrayList<Integer> ret = new ArrayList<>();
		x = 1;
		if (root.val != voyage[0] || !flipMatchVoyageHelper(root, voyage, voyage.length, ret)
			|| x != voyage.length) {
			ret.clear();
			ret.add(-1);
		}
		return ret;
	}
	
	private boolean flipMatchVoyageHelper(TreeNode root, int[] voyage, int length,
		ArrayList<Integer> ret) {
		if (x == length || root == null) {
			return true;
		}
		boolean b = true;
		TreeNode left = root.left;
		TreeNode right = root.right;
		if (left != null && left.val != voyage[x]) {
			ret.add(root.val);
			root.left = right;
			root.right = left;
		}
		if (root.left != null) {
			if (root.left.val == voyage[x]) {
				x++;
				if (!flipMatchVoyageHelper(root.left, voyage, length, ret)) {
					return false;
				}
			} else {
				return false;
			}
		}
		if (root.right != null) {
			if (root.right.val == voyage[x]) {
				x++;
				if (!flipMatchVoyageHelper(root.right, voyage, length, ret)) {
					return false;
				}
			} else {
				return false;
			}
		}
		return true;
	}
	
	public int[] numsSameConsecDiff(int N, int K) {
		if (N == 1) {
			return new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		}
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i < 10; i++) {
			numsSameConsecDiffHelper(list, i, N - 1, K);
		}
		int len = list.size();
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}
	
	private void numsSameConsecDiffHelper(LinkedList<Integer> list, int cur, int last, int k) {
		if (last == 0) {
			list.add(cur);
			return;
		}
		int x = cur % 10;
		if (k == 0) {
			numsSameConsecDiffHelper(list, cur * 10 + x, last - 1, k);
			return;
		}
		if (x >= k) {
			numsSameConsecDiffHelper(list, cur * 10 + x - k, last - 1, k);
		}
		if (x + k < 10) {
			numsSameConsecDiffHelper(list, cur * 10 + x + k, last - 1, k);
		}
	}
	
	class Tire {
		
		int idx;
		Tire[] next;
		
		public Tire(int idx) {
			this.idx = idx;
			this.next = new Tire[58];
		}
	}
	
	Tire root;
	String vowel = "aeiouAEIOU";
	
	public String[] spellchecker(String[] wordlist, String[] queries) {
		root = new Tire(Integer.MAX_VALUE);
		int len = wordlist.length;
		for (int i = 0; i < len; i++) {
			addInTire(wordlist[i].toCharArray(), i);
		}
		len = queries.length;
		for (int i = 0; i < len; i++) {
			int idx = findInTire1(queries[i].toCharArray());
			if (idx < Integer.MAX_VALUE) {
				queries[i] = wordlist[idx];
				continue;
			}
			idx = findInTire2(root, queries[i].toCharArray(), 0, queries[i].length());
			if (idx < Integer.MAX_VALUE) {
				queries[i] = wordlist[idx];
				continue;
			}
			idx = findInTire3(root, queries[i].toCharArray(), 0, queries[i].length());
			if (idx < Integer.MAX_VALUE) {
				queries[i] = wordlist[idx];
				continue;
			}
			queries[i] = "";
			
		}
		return queries;
	}
	
	private int findInTire3(Tire cur, char[] chars, int i, int length) {
		if (i == length) {
			return cur.idx;
		}
		char c = chars[i];
		int ret = Integer.MAX_VALUE;
		if (vowel.indexOf(c) < 0) {
			if (cur.next[c - 'A'] != null) {
				ret = Math.min(ret, findInTire3(cur.next[c - 'A'], chars, i + 1, length));
			}
			if (c < 'a') {
				c += 32;
			} else {
				c -= 32;
			}
			if (cur.next[c - 'A'] != null) {
				ret = Math.min(ret, findInTire3(cur.next[c - 'A'], chars, i + 1, length));
			}
			
		} else {
			for (int j = 0; j < 10; j++) {
				c = vowel.charAt(j);
				if (cur.next[c - 'A'] != null) {
					ret = Math.min(ret, findInTire3(cur.next[c - 'A'], chars, i + 1, length));
				}
			}
		}
		return ret;
	}
	
	private int findInTire2(Tire cur, char[] chars, int i, int length) {
		if (i == length) {
			return cur.idx;
		}
		int ret = Integer.MAX_VALUE;
		char c = chars[i];
		if (cur.next[c - 'A'] != null) {
			ret = Math.min(ret, findInTire2(cur.next[c - 'A'], chars, i + 1, length));
		}
		if (c < 'a') {
			c += 32;
		} else {
			c -= 32;
		}
		if (cur.next[c - 'A'] != null) {
			ret = Math.min(ret, findInTire2(cur.next[c - 'A'], chars, i + 1, length));
		}
		return ret;
	}
	
	private int findInTire1(char[] chars) {
		Tire cur = root;
		for (char c : chars) {
			if (cur.next[c - 'A'] != null) {
				cur = cur.next[c - 'A'];
			} else {
				return Integer.MAX_VALUE;
			}
		}
		return cur.idx;
	}
	
	private void addInTire(char[] chars, int idx) {
		int c;
		int len = chars.length;
		Tire cur = root;
		for (int i = 0; i < len; i++) {
			c = chars[i] - 'A';
			if (null == cur.next[c]) {
				cur.next[c] = new Tire(Integer.MAX_VALUE);
			}
			cur = cur.next[c];
		}
		cur.idx = idx;
	}
}
