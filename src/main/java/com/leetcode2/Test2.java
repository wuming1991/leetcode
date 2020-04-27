package com.leetcode2;

import com.leetcode2.Test1.ListNode;
import com.sun.corba.se.spi.orbutil.proxy.LinkedInvocationHandler;
import com.sun.deploy.panel.ITreeNode;
import com.sun.javafx.sg.prism.web.NGWebView;
import com.sun.org.apache.xpath.internal.FoundIndex;
import com.sun.org.apache.xpath.internal.functions.FuncFalse;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.InterningXmlVisitor;
import java.lang.management.MemoryNotificationInfo;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.swing.MenuElement;
import sun.misc.InnocuousThread;

/**
 * @ProjectName: study
 * @Package: com.leetcode2
 * @Class Test2
 * @Author: WuMing
 * @CreateDate: 2020/3/27 10:06
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2020
 */
public class Test2 {
	
	public static void main(String[] args) throws Exception {
		Test2 test = new Test2();
		 test.generateParenthesis(3);
	}
	//面试题 05.07. 配对交换
	public int exchangeBits(int num) {
		 int a=1,b=2;
		 int ret=0;
		 int base=1,x,y;
		 while (num>0){
		 	x=a&num;
		 	y=b&num;
		 	ret+=y*base;
			 base<<=1;
		 	ret+=x*base;
			 base<<=1;
		 	num>>=2;
		 }
		 return ret;
	}
	//面试题 08.09. 括号
	public List<String> generateParenthesis(int n) {
		StringBuffer buffer = new StringBuffer();
		ArrayList<String> ret = new ArrayList<>();
		generateParenthesisHelper(ret,buffer,n,n);
		return ret;
	}
	
	private void generateParenthesisHelper(ArrayList<String> ret, StringBuffer buffer, int l,
		int r) {
		if(l>r){
			return;
		}
		if(l==0&&r==0){
			ret.add(buffer.toString());
		}
		if(l>0){
			buffer.append("(");
			generateParenthesisHelper(ret,buffer,l-1,r);
			buffer.deleteCharAt(buffer.length()-1);
		}
		if(r>0){
			buffer.append(")");
			generateParenthesisHelper(ret,buffer,l,r-1);
			buffer.deleteCharAt(buffer.length()-1);
		}
	}
	//5393. 可获得的最大点数
	public int maxScore(int[] cardPoints, int k) {
		int len = cardPoints.length;
		int last=len-k;
		if(last==0){
			int ret=0;
			for (int cardPoint : cardPoints) {
				ret+=cardPoint;
			}
			return ret;
		}
		for (int i = 1; i < last; i++) {
			cardPoints[i]+=cardPoints[i-1];
		}
		int min=cardPoints[last-1];
		for (int i = last; i < len; i++) {
			cardPoints[i]+=cardPoints[i-1];
			min=Math.min(min,cardPoints[i]-cardPoints[i-last]);
		}
		return cardPoints[len-1]-min;
	}
	//5394. 对角线遍历 II
	public int[] findDiagonalOrder1(List<List<Integer>> nums) {
		int len = nums.size(),total=0;
		int[] mem = new int[len];
		for (int i = 0; i < len; i++) {
			mem[i]=nums.get(i).size();
			total+=mem[i];
		}
		int[] ret = new int[total];
		int idx,l;
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < nums.get(i).size(); j++) {
				l=Math.min(len-1,i+j);
				idx=0;
				for (int k = l,x=i+j<len?1:(i+j-len+2); k >=0 ; k--,x++) {
					if(k>i){
						idx+=Math.min(x,mem[k]);
					}else{
						idx+=Math.min(x-1,mem[k]);
					}
				}
				ret[idx++]=nums.get(i).get(j);
			}
		}
		return ret;
	}
	public int[] findDiagonalOrder(List<List<Integer>> nums) {
		int len = nums.size();
		int[] mem = new int[len];
		int[] last = new int[len];
		int total=0,t;
		for (int i = 0; i < len; i++) {
			mem[i]=-i;
			t=nums.get(i).size();
			total+=t;
			last[i]=t;
		}
		int idx=0;
		int[] ret = new int[total];
		while (idx<total){
			for (int i =len-1; i>=0; i--) {
				if(last[i]>0){
					if(mem[i]>=0){
						ret[idx++]=nums.get(i).get(mem[i]);
						last[i]--;
					}
					mem[i]++;
				}
			}
		}
		return ret;
	}
	//5392. 分割字符串的最大得分
	public int maxScore(String s) {
		int len = s.length();
		int[] mem = new int[len];
		int count=0;
		for (int i = 0; i < len; i++) {
			count+=s.charAt(i)=='0'?1:0;
			mem[i]=count;
		}
		int ret =len-count;
		for (int i = 0; i < len; i++) {
			ret= Math.max(ret,mem[i]+len-count-(i+1-mem[i]));
		}
		return ret;
	}
	//23. 合并K个排序链表
	public ListNode mergeKLists(ListNode[] lists) {
		PriorityQueue<ListNode> queue = new PriorityQueue<>((a,b)->(a.val-b.val)) ;
		for (ListNode list : lists) {
			if(list!=null){
				queue.add(list);
			}
		}
		if(queue.size()<1){
			return null;
		}
		ListNode head = queue.poll(),next,cur=head;
		while (queue.size()>0){
			next=cur.next;
			cur.next=null;
			if(next!=null){
				queue.add(next);
			}
			ListNode poll = queue.poll();
			cur.next=poll;
			cur=cur.next;
		}
		return head;
	}
	//LCP 09. 最小跳跃次数--超时间
	public int minJump1(int[] jump) {
		int len = jump.length;
		int[] count = new int[len];
		Arrays.fill(count, len);
		count[0] = 0;
		int r, c, ret = Integer.MAX_VALUE, maxr = 0, maxl = -1;
		HashMap<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i <len; i++) {
			if(!map.containsKey(count[i])){
				map.put(count[i],i);
			}
			r = i + jump[i];
			if (r >= len) {
				ret = Math.min(ret, count[i] + 1);
			} else  {
				maxr = r;
				count[r] =Math.min(count[r],count[i] + 1);
				c = count[r] + 1;
				maxl=map.getOrDefault(c+1,i);
				for (int j = r - 1; j >= maxl; j--) {
					if (count[j] > c) {
						count[j] = c;
					}
				}
			}/** else {
			 count[r] = Math.min(count[r], count[i] + 1);
			 }**/
		}
		return ret;
	}
	
	
	static class TireNode {
		
		int val;
		int count;
		TireNode left;
		TireNode right;
		
		public TireNode(int val, int count) {
			this.val = val;
			this.count = count;
		}
	}
	
	//面试题51. 数组中的逆序对
	public int reversePairs1(int[] nums) {
		return reversePairsHelper(nums, 0, nums.length - 1);
	}
	
	private int reversePairsHelper(int[] nums, int l, int r) {
		if (l >= r) {
			return 0;
		}
		int m = (l + r) >> 1;
		int ret = reversePairsHelper(nums, l, m) + reversePairsHelper(nums, m + 1, r);
		int count = 0, a = 0, b = m + 1;
		int[] copy = Arrays.copyOfRange(nums, l, m + 1);
		for (int i = l; i <= r; i++) {
			if (b > r || (a <= m - l && copy[a] > nums[b])) {
				nums[i] = copy[a];
				count++;
				a++;
			} else {
				nums[i] = nums[b];
				ret += count;
				b++;
			}
		}
		return ret;
	}
	
	public int reversePairs(int[] nums) {
		int ret = 0;
		TireNode root = new TireNode(nums[0], 1);
		int len = nums.length;
		for (int i = 1; i < len; i++) {
			insertTree(root, nums[i]);
			int x = searchTree(root, nums[i]);
			ret += x;
		}
		return ret;
	}
	
	private int searchTree(TireNode root, int num) {
		if (root == null) {
			return 0;
		} else if (root.val > num) {
			return root.count + searchTree(root.left, num) + searchTree(root.right, num);
		} else {
			return searchTree(root.right, num);
		}
		
	}
	
	private TireNode insertTree(TireNode root, int num) {
		if (root == null) {
			return new TireNode(num, 1);
		} else if (root.val < num) {
			root.right = insertTree(root.right, num);
		} else if (root.val > num) {
			root.left = insertTree(root.left, num);
		} else {
			root.count++;
		}
		return root;
	}
	
	//面试题 02.06. 回文链表
	public boolean isPalindrome(ListNode head) {
		ListNode f = head, s = f, r = null, t;
		while (f != null) {
			f = f.next;
			if (f != null) {
				f = f.next;
				t = s;
				s = s.next;
				t.next = r;
				r = t;
			} else {
				s = s.next;
			}
		}
		while (s != null) {
			if (r.val != s.val) {
				return false;
			}
			r = r.next;
			s = s.next;
		}
		return true;
	}
	
	//面试题 02.01. 移除重复节点
	public ListNode removeDuplicateNodes(ListNode head) {
		if (head == null) {
			return head;
		}
		boolean[] flag = new boolean[20001];
		flag[head.val] = true;
		ListNode cur = head;
		while (cur.next != null) {
			if (flag[cur.next.val]) {
				cur.next = cur.next.next;
			} else {
				flag[cur.next.val] = true;
				cur = cur.next;
			}
		}
		return head;
	}
	
	//面试题 08.11. 硬币
	public int waysToChange2(int n) {
		int[] count = new int[n + 1];
		Arrays.fill(count, 1);
		int[] coin = {1, 5, 10, 25};
		int x, mod = 1000000007;
		for (int i = 1; i < 4; i++) {
			x = coin[i];
			for (int j = x; j <= n; j++) {
				count[j] += count[j - x];
				count[j] %= mod;
			}
		}
		return count[n];
	}
	
	public int waysToChange1(int n) {
		int[] coin = {1, 5, 10, 25};
		long[][] count = new long[4][n + 1];
		Arrays.fill(count[0], 1);
		int x, mod = 1000000007;
		for (int i = 1; i < 4; i++) {
			x = coin[i];
			for (int j = 0; j <= n; j++) {
				if (j < x) {
					count[i][j] = count[i - 1][j];
				} else {
					count[i][j] = count[i - 1][j] + count[i][j - x];
				}
			}
		}
		return (int) count[3][n];
	}
	
	public int waysToChange(int n) {
		int[] coin = {1, 5, 10, 25};
		long[][] count = new long[n + 1][4];
		waysToChangeHelper(n, coin, count, 3);
		return (int) count[n][3];
	}
	
	private long waysToChangeHelper(int n, int[] coin, long[][] count, int i) {
		if (i == 0) {
			return 1;
		} else if (count[n][i] > 0) {
			return count[n][i];
		}
		int ret = 0, mod = 1000000007;
		for (int j = 0; j <= n / coin[i]; j++) {
			ret += waysToChangeHelper(n - j * coin[i], coin, count, i - 1);
			ret %= mod;
		}
		count[n][i] = ret;
		return ret;
	}
	
	//LCP 07. 传递信息
	public int numWays(int n, int[][] relation, int k) {
		HashMap<Integer, Set<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			map.put(i, new HashSet<>());
		}
		for (int[] ints : relation) {
			map.get(ints[0]).add(ints[1]);
		}
		HashMap<Integer, Integer> cur = new HashMap<>(), next;
		cur.put(0, 1);
		while (k > 0) {
			k--;
			next = new HashMap<>();
			for (Entry<Integer, Integer> entry : cur.entrySet()) {
				Integer value = entry.getValue();
				for (Integer x : map.get(entry.getKey())) {
					next.put(x, next.getOrDefault(x, 0) + value);
				}
			}
			cur = next;
		}
		return cur.getOrDefault(n - 1, 0);
	}
	
	//LCP 06. 拿硬币
	public int minCount(int[] coins) {
		int ret = 0;
		for (int coin : coins) {
			ret += ((coin + 1) >> 1);
		}
		return ret;
	}
	
	//199. 二叉树的右视图
	public List<Integer> rightSideView(TreeNode root) {
		ArrayList<Integer> list = new ArrayList<>();
		rightSideViewHelper(list, root, 0);
		return list;
	}
	
	private void rightSideViewHelper(ArrayList<Integer> list, TreeNode root, int level) {
		if (root == null) {
			return;
		} else if (level == list.size()) {
			list.add(root.val);
		}
		rightSideViewHelper(list, root.right, level + 1);
		rightSideViewHelper(list, root.left, level + 1);
	}
	
	//TODO 1418. 点菜展示表
	public List<List<String>> displayTable(List<List<String>> orders) {
		Map<String, Map<String, Integer>> map = new TreeMap<>();
		TreeSet<Integer> tSet = new TreeSet<>();
		String food, table;
		for (List<String> order : orders) {
			food = order.get(2);
			table = order.get(1);
			tSet.add(Integer.valueOf(table));
			if (map.containsKey(food)) {
				Map<String, Integer> tmap = map.get(food);
				tmap.put(table, tmap.getOrDefault(table, 0) + 1);
			} else {
				HashMap<String, Integer> tmap = new HashMap<>();
				map.put(food, tmap);
				tmap.put(table, 1);
			}
		}
		return null;
	}
	
	//1416. 恢复数组
	public int numberOfArrays(String s, int k) {
		int len = s.length();
		if (k < 10) {
			if (s.charAt(0) >= 0) {
				return 0;
			} else if (k < 9 && s.charAt((char) ('1' + k)) >= 0) {
				return 0;
			} else {
				return 1;
			}
		}
		int[] count = new int[len];
		int x, base;
		for (int i = 0; i < len; i++) {
			x = s.charAt(i) - '0';
			if (x != 0) {
				base = i > 0 ? count[i - 1] : 1;
				numberOfArraysHelper(s, i, k, base, count);
			} else if (count[i] == 0) {
				return 0;
			}
		}
		return count[len - 1];
	}
	
	private void numberOfArraysHelper(String s, int begin, int k, int base, int[] count) {
		int len = s.length(), mod = 1000000007;
		int x = 0;
		for (int i = begin; i < len; i++) {
			x = x * 10 + s.charAt(i) - '0';
			if (x <= k) {
				count[i] += base;
				count[i] %= mod;
			} else {
				break;
			}
		}
	}
	
	//1415. 长度为 n 的开心字符串中字典序第 k 小的字符串
	public String getHappyString(int n, int k) {
		int i = 1 << (n - 1);
		if (k > 3 * i) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		int cur;
		if (k <= i) {
			cur = 0;
		} else if (k <= 2 * i) {
			cur = 1;
		} else {
			cur = 2;
		}
		k %= i;
		i /= 2;
		n--;
		builder.append((char) ('a' + cur));
		while (n > 0) {
			if (k == 0) {
				if (cur == 2) {
					cur = 1;
					builder.append('b');
				} else {
					cur = 2;
					builder.append('c');
				}
			} else {
				if (k <= i) {
					cur = cur == 0 ? 1 : 0;
				} else {
					cur = cur == 2 ? 1 : 2;
				}
				builder.append((char) ('a' + cur));
				k %= i;
				i >>= 1;
			}
			n--;
		}
		return builder.toString();
	}
	
	//1419. 数青蛙
	public int minNumberOfFrogs(String croakOfFrogs) {
		int c = 0, r = 0, o = 0, a = 0, ret = 0;
		int len = croakOfFrogs.length();
		for (int i = 0; i < len; i++) {
			switch (croakOfFrogs.charAt(i)) {
				case 'c':
					c++;
					break;
				case 'r':
					r++;
					break;
				case 'o':
					o++;
					break;
				case 'a':
					a++;
					break;
				default:
					ret = Math.max(ret, c);
					c--;
					r--;
					o--;
					a--;
			}
			if (c < 0 || c < r || r < o || o < a) {
				return -1;
			}
		}
		return c == 0 ? ret : -1;
	}
	
	//5373. 和为 K 的最少斐波那契数字数目
	int count;
	HashMap<Integer, Integer> map;
	
	public int findMinFibonacciNumbers(int k) {
		count = k;
		map = new HashMap<>();
		int[] ints = new int[1000];
		ints[0] = 1;
		int a = 1, b = 1, c = 2, idx = 1;
		map.put(1, 1);
		while (c <= k) {
			ints[idx++] = c;
			map.put(c, 1);
			a = b;
			b = c;
			c = a + b;
		}
		return findMinFibonacciNumbersHelper(k, ints, idx - 1);
	}
	
	private int findMinFibonacciNumbersHelper(int k, int[] ints, int idx) {
		if (map.containsKey(k)) {
			return map.get(k);
		}
		int ret = Integer.MAX_VALUE;
		for (int i = idx; i >= 0; i--) {
			if (k > 2 * ints[i]) {
				break;
			} else if (k > ints[i]) {
				ret = Math.min(ret, 1 + findMinFibonacciNumbersHelper(k - ints[i], ints, i));
			}
		}
		map.put(k, ret);
		return ret;
	}
	
	//5388. 重新格式化字符串
	public String reformat(String s) {
		int len = s.length();
		char[] chars;
		int a = 0, b = 1;
		char c;
		if ((len & 1) == 1) {
			chars = new char[len + 1];
		} else {
			chars = new char[len];
		}
		for (int i = 0; i < len; i++) {
			c = s.charAt(i);
			if (c >= 'a' && c <= 'z') {
				if (a >= len) {
					return "";
				}
				chars[a] = c;
				a += 2;
			} else {
				if (b > len) {
					return "";
				}
				chars[b] = c;
				b += 2;
			}
		}
		if ((len & 1) == 1) {
			if (chars[len] >= '0' && chars[len] <= '9') {
				return chars[len] + new String(chars, 0, len - 1);
			} else {
				return new String(chars, 0, len);
			}
		} else {
			return new String(chars);
		}
	}
	
	//5372. 逐步求和得到正数的最小值
	public int minStartValue(int[] nums) {
		int min = 0;
		int sum = 0;
		for (int num : nums) {
			sum += num;
			min = Math.min(sum, min);
		}
		return 1 - min > 0 ? 1 - min : 1;
	}
	
	//面试题 04.10. 检查子树
	public boolean checkSubTree(TreeNode t1, TreeNode t2) {
		if (t2 == null) {
			return true;
		} else if (t1 == null) {
			return false;
		} else {
			if (t1.val == t2.val && checkSame(t1, t2)) {
				return true;
			} else {
				return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
			}
		}
	}
	
	private boolean checkSame(TreeNode t1, TreeNode t2) {
		if (t1 == null && t2 == null) {
			return true;
		} else if (t1 == null || t2 == null) {
			return false;
		} else if (t1.val != t2.val) {
			return false;
		}
		return checkSame(t1.left, t2.left) && checkSame(t1.right, t2.right);
	}
	
	//面试题 04.04. 检查平衡性
	//boolean flag=true;
	public boolean isBalanced(TreeNode root) {
		flag = true;
		if (root == null) {
			return true;
		}
		int x = isBalancedHelper(root);
		return flag;
	}
	
	private int isBalancedHelper(TreeNode root) {
		if (!flag || root == null) {
			return 0;
		}
		int l = isBalancedHelper(root.left) + 1;
		int r = isBalancedHelper(root.right) + 1;
		if (Math.abs(l - r) < 2) {
			return Math.max(l, r);
		} else {
			flag = false;
			return Math.max(l, r);
		}
	}
	
	//1403. 非递增顺序的最小子序列
	public List<Integer> minSubsequence(int[] nums) {
		int[] count = new int[101];
		int sum = 0;
		for (int num : nums) {
			count[num]++;
			sum += num;
		}
		int cur = 0;
		sum >>= 1;
		ArrayList<Integer> ret = new ArrayList<>();
		for (int i = 100; i > 0; i--) {
			while (count[i] > 0) {
				cur += i;
				ret.add(i);
				count[i]--;
				if (cur > sum) {
					return ret;
				}
			}
		}
		return ret;
	}
	
	//1402. 做菜顺序
	public int maxSatisfaction(int[] satisfaction) {
		Arrays.sort(satisfaction);
		int ret = 0;
		int sum = 0, total = 0;
		int len = satisfaction.length;
		for (int i = len; i >= 0; i--) {
			sum += satisfaction[i];
			total += sum;
			ret = Math.max(ret, total);
		}
		return ret;
	}
	
	//1409. 查询带键的排列
	public int[] processQueries(int[] queries, int m) {
		ArrayList<Integer> list = new ArrayList<>();
		int len = queries.length;
		int[] ret = new int[len];
		for (int i = 1; i <= m; i++) {
			list.add(i);
		}
		int x;
		for (int i = 0; i < len; i++) {
			x = queries[i];
			ret[i] = list.indexOf(x);
			list.remove(ret[i]);
			list.add(0, x);
		}
		return ret;
	}
	
	//1408. 数组中的字符串匹配
	public List<String> stringMatching(String[] words) {
		int len = words.length;
		boolean[] flag = new boolean[len];
		ArrayList<String> ret = new ArrayList<>();
		String cur;
		for (int i = 0; i < len; i++) {
			if (flag[i]) {
				continue;
			}
			cur = words[i];
			for (int j = 0; j < len; j++) {
				if (flag[j] || j == i) {
					continue;
				}
				if (cur.indexOf(words[j]) >= 0) {
					ret.add(words[j]);
					flag[j] = true;
				}
			}
		}
		return ret;
	}
	
	//445. 两数相加 II
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode l1r = revertHelper(l1);
		ListNode l2r = revertHelper(l2), ret = null;
		int x = 0;
		while (l1r != null || l2r != null) {
			if (l1r != null) {
				x += l1r.val;
				l1r = l1r.next;
			}
			if (l2r != null) {
				x += l2r.val;
				l2r = l2r.next;
			}
			ListNode cur = new ListNode(x % 10);
			cur.next = ret;
			ret = cur;
			x /= 10;
		}
		if (x > 0) {
			ListNode cur = new ListNode(1);
			cur.next = ret;
			ret = cur;
		}
		return ret;
	}
	
	private ListNode revertHelper(ListNode l1) {
		ListNode ret = null, next;
		while (l1 != null) {
			next = l1.next;
			l1.next = ret;
			ret = l1;
			l1 = next;
		}
		return ret;
	}
	
	//1406. 石子游戏 III
	public String stoneGameIII(int[] stoneValue) {
		int len = stoneValue.length;
		int[] mem = new int[len];
		Arrays.fill(mem, Integer.MIN_VALUE);
		stoneGameIIIHelper(stoneValue, mem, 0);
		return mem[0] == 0 ? "Tie" : (mem[0] > 0 ? "Alice" : "Bob");
	}
	
	private int stoneGameIIIHelper(int[] stoneValue, int[] mem, int i) {
		int ret = Integer.MIN_VALUE;
		int len = stoneValue.length;
		if (i >= len) {
			return 0;
		} else if (mem[i] > ret) {
			return mem[i];
		}
		ret = Math.max(ret, stoneValue[i] - stoneGameIIIHelper(stoneValue, mem, i + 1));
		if (i + 1 < len) {
			
			ret = Math.max(ret,
				stoneValue[i] + stoneValue[i + 1] - stoneGameIIIHelper(stoneValue, mem, i + 2));
		}
		if (i + 2 < len) {
			ret = Math.max(ret,
				stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - stoneGameIIIHelper(
					stoneValue,
					mem, i + 3));
		}
		mem[i] = ret;
		return ret;
		
	}
	
	//1404. 将二进制表示减到 1 的步骤数
	public int numSteps(String s) {
		int len = s.length();
		int[] num = new int[len];
		for (int i = 0; i < len; i++) {
			num[i] = s.charAt(i) - '0';
		}
		int i = len - 1, ret = 0;
		while (i >= 0) {
			while (i > 0 && num[i] == 0) {
				ret++;
				i--;
			}
			if (i > 0 && num[i] == 1) {
				ret++;
			} else if (i == 0 && num[i] == 0) {
				ret++;
				return ret;
			} else if (i == 0 && num[i] == 1) {
				return ret;
			}
			for (int j = i; j >= 0; j--) {
				if (num[j] > 0) {
					num[j] = 0;
				} else {
					num[j] = 1;
					break;
				}
			}
		}
		return ret;
	}
	
	//1400. 构造 K 个回文字符串
	public boolean canConstruct(String s, int k) {
		int[] count = new int[26];
		int len = s.length();
		if (len < k) {
			return false;
		}
		for (int i = 0; i < len; i++) {
			count[s.charAt(i) - 'a']++;
		}
		int x = 0;
		for (int i : count) {
			x += (i & 1);
		}
		if (x > k) {
			return false;
		}
		return true;
	}
	
	//1399. 统计最大组的数目
	public int countLargestGroup(int n) {
		int[] count = new int[10];
		int x, c;
		for (int i = 1; i <= n; i++) {
			x = 0;
			c = i;
			while (c > 0) {
				x += c % 10;
				c /= 10;
			}
			count[x]++;
		}
		int ret = 0, max = 0;
		for (int i : count) {
			if (i > max) {
				ret = 1;
				max = i;
			} else if (i == max) {
				ret++;
			}
		}
		return ret;
	}
	
	//面试题 01.07. 旋转矩阵
	public void rotate(int[][] matrix) {
		int len = matrix.length;
		for (int i = 0; i < len / 2; i++) {
			for (int j = 0; j < len / 2; j++) {
				rotateHelp(matrix, i, j, len - 1, 0);
			}
		}
		if ((len & 1) == 1) {
			int i = len / 2;
			for (int j = 0; j < i; j++) {
				rotateHelp(matrix, i, j, len, 0);
			}
		}
	}
	
	private int rotateHelp(int[][] matrix, int i, int j, int len, int count) {
		int ret = matrix[len - j][i];
		if (count == 4) {
			return ret;
		}
		rotateHelp(matrix, len - j, i, len, count + 1);
		matrix[i][j] = ret;
		return ret;
	}
	
	//面试题03. 数组中重复的数字
	public int findRepeatNumber(int[] nums) {
		int len = nums.length, c, t;
		for (int i = 0; i < len; i++) {
			c = nums[i];
			while (c != i) {
				t = nums[c];
				if (t == c) {
					return t;
				}
				nums[c] = c;
				c = t;
			}
			nums[i] = c;
		}
		return -1;
	}
	
	//8
	public int myAtoi(String str) {
		int len = str.length();
		long ret = 0;
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (c == ' ') {
				continue;
			} else if (c == '+') {
				ret = myAtoiHelper(str, i + 1);
				break;
			} else if (c == '-') {
				ret = -1 * myAtoiHelper(str, i + 1);
				break;
			} else if (c >= '0' && c <= '9') {
				ret = myAtoiHelper(str, i);
				break;
			} else {
				return 0;
			}
		}
		if (ret > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (ret < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else {
			return (int) ret;
		}
	}
	
	private long myAtoiHelper(String str, int s) {
		int len = str.length();
		long ret = 0, count = 0;
		for (int i = s; i < len && count < 11; i++) {
			if (ret > 0) {
				count++;
			}
			char c = str.charAt(i);
			if (c >= '0' && c <= '9') {
				ret = ret * 10 + c - '0';
			} else {
				break;
			}
		}
		return ret;
	}
	
	//面试题 17.19. 消失的两个数字
	public int[] missingTwo(int[] nums) {
		int total = (1 + nums.length + 2) * (nums.length + 2) / 2;
		int sum = 0;
		for (int num : nums) {
			sum += num;
		}
		int x = total - sum;
		int half = x / 2;
		int ls = 0;
		for (int num : nums) {
			if (num <= half) {
				ls += num;
			}
		}
		int a = (1 + half) * half / 2 - ls;
		int b = x - a;
		return new int[]{a, b};
	}
	
	//面试题59 - I. 滑动窗口的最大值
	public int[] maxSlidingWindow(int[] nums, int k) {
		LinkedList<Integer> list = new LinkedList<>();
		int i = 1;
		list.add(nums[0]);
		while (i < k) {
			while (!list.isEmpty() && list.getLast() < nums[i]) {
				list.removeLast();
			}
			list.add(nums[i]);
			i++;
		}
		int len = nums.length;
		int[] ret = new int[len - k + 1];
		for (; i < len; i++) {
			ret[i - k] = list.getFirst();
			if (nums[i - k] == list.getFirst()) {
				list.removeFirst();
			}
			while (!list.isEmpty() && list.getLast() < nums[i]) {
				list.removeLast();
			}
			list.add(nums[i]);
		}
		ret[len - k] = list.getFirst();
		return ret;
	}
	
	//面试题50. 第一个只出现一次的字符
	public char firstUniqChar(String s) {
		int[][] count = new int[256][2];
		int len = s.length();
		int[] cur;
		for (int i = 0; i < len; i++) {
			cur = count[s.charAt(i)];
			cur[0] = i;
			cur[1]++;
		}
		int c = -1;
		for (int i = 0; i < 256; i++) {
			cur = count[i];
			if (cur[1] == 1) {
				if (c < 0 || count[c][0] > cur[0]) {
					c = i;
				}
			}
		}
		return c >= 0 ? (char) c : ' ';
	}
	
	//面试题61. 扑克牌中的顺子
	public boolean isStraight(int[] nums) {
		int[] count = new int[14];
		for (int num : nums) {
			count[num]++;
		}
		for (int i = 1; i <= 13; i++) {
			if (count[i] == 1) {
				for (int j = i + 1; j < i + 5; j++) {
					if (count[i] == 0) {
						if (count[0] == 0) {
							return false;
						} else {
							count[0]--;
						}
					}
				}
				break;
			} else if (count[i] > 1) {
				return false;
			}
		}
		return true;
	}
	
	//面试题66. 构建乘积数组
	public int[] constructArr(int[] a) {
		int t = 1;
		int len = a.length;
		int[] ret = new int[len];
		for (int i = 0; i < len; i++) {
			ret[i] = t;
			t *= a[i];
		}
		t = 1;
		for (int i = len - 1; i >= 0; i--) {
			ret[i] *= t;
			t *= a[i];
		}
		return ret;
	}
	
	//面试题39. 数组中出现次数超过一半的数字
	public int majorityElement(int[] nums) {
		int val = 0, count = 0;
		for (int num : nums) {
			if (count == 0) {
				val = num;
				count++;
			} else if (num == val) {
				count++;
			} else {
				count--;
			}
		}
		return val;
	}
	
	//面试题27. 二叉树的镜像
	public TreeNode mirrorTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		TreeNode ret = new TreeNode(root.val);
		ret.left = mirrorTree(root.right);
		ret.right = mirrorTree(root.left);
		return ret;
	}
	
	//面试题15. 二进制中1的个数
	public int hammingWeight(int n) {
		int ret = 0;
		while (n > 0) {
			ret += (n & 1);
			n >>= 1;
		}
		return ret;
	}
	
	//面试题10- II. 青蛙跳台阶问题
	public int numWays(int n) {
		if (n == 0) {
			return 1;
		} else if (n < 3) {
			return n;
		}
		long a = 1, b = 2, c = 3, t = 3;
		int mod = 1000000007;
		while (t <= n) {
			c = a + b;
			c %= mod;
			a = b;
			b = c;
			t++;
		}
		return (int) c;
	}
	
	//面试题06. 从尾到头打印链表
	public int[] reversePrint(ListNode head) {
		int count = 0;
		ListNode t = head;
		while (t != null) {
			count++;
			t = t.next;
		}
		int[] ret = new int[count];
		while (count > 0) {
			count--;
			ret[count] = head.val;
			head = head.next;
		}
		return ret;
	}
	
	
	public List<String> getValidT9Words(String num, String[] words) {
		int[] map = {2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
		ArrayList<String> ret = new ArrayList<>();
		int len = num.length();
		int i = 0;
		int[] nums = new int[len];
		for (; i < len; i++) {
			nums[i] = num.charAt(i) - '0';
		}
		
		for (String word : words) {
			if (word.length() == len) {
				for (i = 0; i < len; i++) {
					if (map[word.charAt(i) - 'a'] != nums[i]) {
						break;
					}
				}
				if (i == len) {
					ret.add(word);
				}
			}
		}
		return ret;
	}
	
	public int maxSubArray(int[] nums) {
		int sum = 0, ret = Integer.MIN_VALUE;
		for (int num : nums) {
			sum += num;
			ret = Math.max(ret, sum);
			if (sum < 0) {
				sum = 0;
			}
		}
		return ret;
	}
	
	public void gameOfLife(int[][] board) {
		int[] sta = {0, 1, 1, 0};
		int[][] ds = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0},
			{1, 1}};
		int high = board.length;
		int len = board[0].length;
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				int count = gameOfLifeHelper(board, ds, i, j, sta);
				if (board[i][j] == 0) {
					if (count == 3) {
						board[i][j] = 3;
					}
				} else {
					if (count < 2 || count > 3) {
						board[i][j] = 2;
					}
				}
			}
		}
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				board[i][j] &= 1;
			}
		}
	}
	
	private int gameOfLifeHelper(int[][] board, int[][] ds, int i, int j, int[] sta) {
		int ret = 0, ni, nj;
		int high = board.length;
		int len = board[0].length;
		for (int[] d : ds) {
			ni = i + d[0];
			nj = j + d[1];
			if (ni < 0 || nj < 0 || ni >= high || nj >= len) {
				continue;
			}
			ret += sta[board[ni][nj]];
		}
		return ret;
	}
	
	//面试题 16.19. 水域大小
	int pondSize;
	
	public int[] pondSizes(int[][] land) {
		int high = land.length;
		int len = land[0].length;
		int[][] ds = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0},
			{1, 1}};
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < high; i++) {
			for (int j = 0; j < len; j++) {
				if (land[i][j] == 0) {
					pondSize = 1;
					land[i][j] = 1;
					pondSizesHelper(land, i, j, ds);
					list.add(pondSize);
				}
			}
		}
		int size = list.size();
		int[] ret = new int[size];
		for (int i = 0; i < size; i++) {
			ret[i] = list.get(i);
		}
		Arrays.sort(ret);
		return ret;
	}
	
	private void pondSizesHelper(int[][] land, int i, int j, int[][] ds) {
		int ni, nj;
		int high = land.length;
		int len = land[0].length;
		for (int[] d : ds) {
			ni = i + d[0];
			nj = j + d[1];
			if (ni < 0 || nj < 0 || ni >= high || nj >= len || land[ni][nj] > 0) {
				continue;
			}
			pondSize++;
			land[ni][nj] = 1;
			pondSizesHelper(land, ni, nj, ds);
		}
	}
	
	//面试题 16.10. 生存人数
	public int maxAliveYear(int[] birth, int[] death) {
		int[] count = new int[102];
		int len = birth.length;
		for (int i = 0; i < len; i++) {
			count[birth[i] - 1900]++;
			count[death[i] - 1899]--;
		}
		int idx = 0, max = count[0], sum = max;
		int l = count.length;
		for (int i = 1; i < l; i++) {
			sum += count[i];
			if (sum > max) {
				max = sum;
				idx = i;
			}
		}
		return idx + 1900;
	}
	
	//面试题 16.06. 最小差
	public int smallestDifference(int[] a, int[] b) {
		Arrays.sort(a);
		Arrays.sort(b);
		int la = a.length;
		int lb = b.length;
		int ai = 0, bi = 0;
		long min = Integer.MAX_VALUE;
		while (ai < la && bi < lb) {
			while (bi < lb && ai < la && a[ai] < b[bi]) {
				ai++;
			}
			if (ai > 0 && bi < lb) {
				min = Math.min(min, (long) b[bi] - a[ai - 1]);
			}
			if (ai < la && bi < lb) {
				min = Math.min(min, -b[bi] + a[ai]);
			}
			while (ai < la && bi < lb && b[bi] < a[ai]) {
				bi++;
			}
			if (bi > 0 && ai < la) {
				min = Math.min(min, a[ai] - b[bi - 1]);
			}
			if (bi < lb && ai < la) {
				min = Math.min(min, -a[ai] + b[bi]);
			}
			if (ai < la && bi < lb && a[ai] == b[bi]) {
				return 0;
			}
		}
		return (int) min;
	}
	
	//面试题 10.11. 峰与谷
	public void wiggleSort(int[] nums) {
		int len = nums.length;
		int[] copy = Arrays.copyOf(nums, len);
		Arrays.sort(copy);
		int j = 0;
		for (int i = 0; i < len; i += 2) {
			nums[i] = copy[j++];
		}
		for (int i = 1; i < len; i += 2) {
			nums[i] = copy[j++];
		}
	}
	
	//面试题 08.04. 幂集
	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> ret = new ArrayList<>();
		ret.add(new ArrayList<>());
		LinkedList<Integer> list = new LinkedList<>();
		for (int i = 1; i <= nums.length; i++) {
			subsetsHelper(ret, list, i, 0, nums);
		}
		return ret;
	}
	
	//面试题 17.08. 马戏团人塔
	public int bestSeqAtIndex(int[] height, int[] weight) {
		int len = height.length;
		int[][] ints = new int[len][2];
		for (int i = 0; i < len; i++) {
			ints[i][0] = height[i];
			ints[i][1] = weight[i];
		}
		Arrays.sort(ints, (a, b) -> {
			if (a[0] == b[0]) {
				return a[1] - b[1];
			} else {
				return a[0] - b[0];
			}
		});
		int[] ret = new int[len];
		Arrays.fill(ret, -1);
		int result = 0;
		for (int i = 0; i < len; i++) {
			if (ret[i] < 0) {
				bestSeqAtIndexHelper(ret, ints, i);
			}
			result = Math.max(result, ret[i]);
		}
		return result + 1;
	}
	
	private int bestSeqAtIndexHelper(int[] ret, int[][] ints, int cur) {
		if (ret[cur] >= 0) {
			return ret[cur];
		}
		int x = 0;
		for (int i = cur + 1; i < ints.length; i++) {
			if (ints[cur][1] < ints[i][1]) {
				x = Math.max(x, 1 + bestSeqAtIndexHelper(ret, ints, i));
			}
		}
		ret[cur] = x;
		return x;
	}
	
	private void subsetsHelper(List<List<Integer>> ret, LinkedList<Integer> list, int last, int s,
		int[] num) {
		int len = num.length;
		if (last == 0) {
			ArrayList<Integer> x = new ArrayList<>();
			x.addAll(list);
			ret.add(x);
			return;
		} else if (last > len - s) {
			return;
		}
		
		for (int j = s; j < len; j++) {
			list.add(num[j]);
			subsetsHelper(ret, list, last - 1, j + 1, num);
			list.removeLast();
		}
	}
	
	//面试题 04.06. 后继者
	TreeNode inorderSuccessor = null;
	boolean flag = false;
	
	public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
		inorderSuccessorHelper(root, p);
		return inorderSuccessor;
	}
	
	private void inorderSuccessorHelper(TreeNode root, TreeNode p) {
		if (root == null) {
			return;
		}
		inorderSuccessorHelper(root.left, p);
		if (p == root) {
			flag = true;
		} else if (flag) {
			if (inorderSuccessor == null) {
				inorderSuccessor = root;
			}
			return;
		}
		inorderSuccessorHelper(root.right, p);
	}
	
	//面试题 04.02. 最小高度树
	public TreeNode sortedArrayToBST(int[] nums) {
		return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
	}
	
	private TreeNode sortedArrayToBSTHelper(int[] nums, int l, int r) {
		if (l > r) {
			return null;
		} else if (l == r) {
			return new TreeNode(nums[l]);
		}
		int m = (l + r) / 2;
		TreeNode ret = new TreeNode(nums[m]);
		ret.left = sortedArrayToBSTHelper(nums, l, m - 1);
		ret.right = sortedArrayToBSTHelper(nums, m + 1, r);
		return ret;
	}
	
	
	//面试题 05.07. 配对交换
	public int exchangeBits1(int num) {
		int x = 1, a, b;
		while (num >= x) {
			a = num & x;
			x <<= 1;
			b = num & x;
			x <<= 1;
			if (a == 0 && b != 0) {
				num -= (b >> 1);
			} else if (a != 0 && b == 0) {
				num += a;
			}
		}
		return num;
	}
	
	//面试题 04.01. 节点间通路
	public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
		boolean[] visited = new boolean[n];
		visited[start] = true;
		HashSet<Integer>[] sets = new HashSet[n];
		for (int i = 0; i < n; i++) {
			sets[i] = new HashSet();
		}
		for (int[] x : graph) {
			sets[x[0]].add(x[1]);
		}
		HashSet<Integer> cur = new HashSet(), next;
		cur.add(0);
		while (!cur.isEmpty()) {
			next = new HashSet<>();
			for (Integer c : cur) {
				for (Integer x : sets[c]) {
					if (!visited[x]) {
						visited[x] = true;
						next.add(x);
					}
				}
			}
			if (visited[target]) {
				return true;
			}
			cur = next;
		}
		return false;
	}
	
	int pathSumCount = 0;
	
	//面试题 04.12. 求和路径
	public int pathSum(TreeNode root, int sum) {
		LinkedList<Integer> list = new LinkedList<>();
		list.add(0);
		pathSumHelper(root, list, sum);
		return pathSumCount;
	}
	
	private void pathSumHelper(TreeNode root, LinkedList<Integer> list, int sum) {
		if (root == null) {
			return;
		}
		int cur = list.getLast() + root.val;
		for (Integer x : list) {
			if (cur - x == sum) {
				pathSumCount++;
			}
		}
		list.add(cur);
		pathSumHelper(root.left, list, sum);
		pathSumHelper(root.right, list, sum);
		list.removeLast();
	}
	
	public int pathSum1(TreeNode root, int sum) {
		HashMap<Integer, Integer> map = new HashMap<>();
		map.put(0, 1);
		pathSumHelper(root, map, sum, 0);
		return pathSumCount;
	}
	
	private void pathSumHelper(TreeNode root, HashMap<Integer, Integer> map, int sum, int before) {
		if (root == null) {
			return;
		}
		int cur = before + root.val;
		pathSumCount += map.getOrDefault(cur - sum, 0);
		map.put(cur, map.getOrDefault(cur, 0) + 1);
		pathSumHelper(root.left, map, sum, cur);
		pathSumHelper(root.right, map, sum, cur);
		map.put(cur, map.get(cur) - 1);
	}
	
	public ListNode[] listOfDepth(TreeNode tree) {
		ArrayList<ListNode> heads = new ArrayList<>();
		ArrayList<ListNode> tails = new ArrayList<>();
		listOfDepthHelper(tree, heads, tails, 0);
		int size = heads.size();
		ListNode[] ret = new ListNode[size];
		for (int i = 0; i < size; i++) {
			ret[i] = heads.get(i);
		}
		return ret;
	}
	
	public boolean canPermutePalindrome(String s) {
		int len = s.length();
		int[] count = new int[26];
		for (int i = 0; i < len; i++) {
			count[s.charAt(i) - 'a']++;
		}
		int x = 0;
		for (int i : count) {
			x += (i & 1);
		}
		return x < 2;
	}
	
	public boolean CheckPermutation(String s1, String s2) {
		int len = s1.length();
		if (len != s2.length()) {
			return false;
		}
		int[] count = new int[26];
		for (int i = 0; i < len; i++) {
			count[s1.charAt(i) - 'a']++;
		}
		for (int i = 0; i < len; i++) {
			char c = s2.charAt(i);
			if (count[c - 'a'] > 0) {
				count[c - 'a']--;
			} else {
				return false;
			}
		}
		return true;
	}
	
	private void listOfDepthHelper(TreeNode root, ArrayList<ListNode> heads,
		ArrayList<ListNode> tails, int level) {
		if (root == null) {
			return;
		}
		if (heads.size() == level) {
			ListNode node = new ListNode(root.val);
			heads.add(node);
			tails.add(node);
		} else {
			ListNode node = new ListNode(root.val);
			ListNode cur = tails.get(level);
			cur.next = node;
			tails.set(level, node);
		}
		listOfDepthHelper(root.left, heads, tails, level + 1);
		listOfDepthHelper(root.right, heads, tails, level + 1);
	}
	
	public static class TreeNode {
		
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int x) {
			val = x;
		}
	}
	
	TreeNode lowestCommonAncestor = null;
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		int i = lowestCommonAncestorHelper(root, p, q);
		return lowestCommonAncestor;
	}
	
	private int lowestCommonAncestorHelper(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || lowestCommonAncestor != null) {
			return 0;
		}
		int ret = 0;
		if (root.val == p.val) {
			ret |= 1;
		} else if (root.val == q.val) {
			ret |= 2;
		}
		ret |= lowestCommonAncestorHelper(root.left, p, q);
		ret |= lowestCommonAncestorHelper(root.right, p, q);
		if (lowestCommonAncestor == null && ret == 3) {
			lowestCommonAncestor = root;
		}
		return ret;
	}
	
	//面试题 08.01. 三步问题
	public int waysToStep(int n) {
		if (n == 3) {
			return 4;
		} else if (n == 2) {
			return 2;
		} else if (n == 1) {
			return 1;
		}
		int a = 1, b = 2, c = 4, t = 3, x, mod = 1000000007;
		while (t < n) {
			x = a + b + c;
			x %= mod;
			a = b;
			b = c;
			c = x;
			t++;
		}
		return c;
	}
	
	static class Level {
		
		int id;
		String name;
		int parentId;
		List<Level> children;
		
		public Level(int id, String name, int parentId) {
			this.id = id;
			this.name = name;
			this.parentId = parentId;
			children = new ArrayList<>();
		}
	}
	
	
	//1390
	public int sumFourDivisors(int[] nums) {
		int max = 0;
		for (int num : nums) {
			max = Math.max(max, num);
		}
		TreeSet<Integer> set = new TreeSet<>();
		set.add(2);
		double x = Math.pow(max, (double) 1 / 3);
		boolean flag;
		for (int i = 3; i < max / 2; i++) {
			flag = true;
			for (Integer c : set) {
				if (i % c == 0) {
					flag = false;
					break;
				}
			}
			if (flag) {
				set.add(i);
			}
		}
		int ret = 0;
		for (int num : nums) {
			for (Integer prim : set) {
				if (prim * prim >= num) {
					break;
				} else if (num % prim == 0) {
					if (prim < x && prim * prim * prim == num) {
						ret += (1 + prim + prim * prim + num);
						break;
					} else if (set.contains(num / prim)) {
						ret += (1 + prim + num / prim + num);
						break;
					}
				}
			}
		}
		return ret;
	}
	
	//1394
	public int findLucky(int[] arr) {
		int len = arr.length;
		int[] count = new int[len + 1];
		int ret = -1;
		for (int i : arr) {
			if (i <= len) {
				count[i]++;
			}
		}
		for (int i = 1; i < len + 1; i++) {
			if (i == count[i]) {
				ret = Math.max(ret, i);
			}
		}
		return ret;
	}
	
	//1395
	public int numTeams(int[] rating) {
		int len = rating.length;
		int[] bigger = new int[len];
		int[] smaller = new int[len];
		for (int i = 0; i < len; i++) {
			int cur = rating[i], l = 0, r = 0;
			for (int j = i + 1; j < len; j++) {
				if (rating[j] > cur) {
					smaller[j]++;
				} else {
					bigger[j]++;
				}
			}
		}
		int ret = 0;
		for (int i = 2; i < len; i++) {
			int cur = rating[i];
			for (int j = 1; j < i; j++) {
				if (cur > rating[j]) {
					ret += smaller[j];
				} else {
					ret += bigger[j];
				}
			}
		}
		return ret;
	}
	
	//面试题62. 圆圈中最后剩下的数字--约瑟夫环
	public int lastRemaining(int n, int m) {
		if (n == 1) {
			return 0;
		}
		return (lastRemaining(n - 1, m) + m) % n;
	}
	
	public int lastRemaining1(int n, int m) {
		int ret = 0;
		for (int i = 2; i <= n; i++) {
			ret = (ret + m) % i;
		}
		return ret;
	}
	
	//交换两个integer的值
	private static void swap(Integer a, Integer b) throws Exception {
		Field value = Integer.class.getDeclaredField("value");
		value.setAccessible(true);
		Integer t = new Integer(a.intValue());
		value.set(a, b.intValue());
		value.set(b, t);
	}
	
	public boolean hasGroupsSizeX(int[] deck) {
		int[] count = new int[10000];
		int l = count[0], r = l;
		for (int i : deck) {
			count[i]++;
			if (i < l) {
				l = i;
			} else if (i > r) {
				r = i;
			}
		}
		int g = count[r];
		for (int i = l; i < r; i++) {
			if (count[i] > 0) {
				g = gcd(g, count[i]);
			}
		}
		return g > 1;
	}
	
	private int gcd(int a, int b) {
		return a == 0 ? b : gcd(b % a, a);
	}
	
	//1375
	public int numTimesAllBlue(int[] light) {
		int max = 0, ret = 0;
		int len = light.length;
		for (int i = 0; i < len; i++) {
			max = Math.max(max, light[i]);
			if (max == i + 1) {
				ret++;
			}
		}
		return ret;
	}
}
