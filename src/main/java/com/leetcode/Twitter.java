package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class Twitter
 * @Author: WuMing
 * @CreateDate: 2019/9/10 21:26
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Twitter {
	
	List<Integer> total;
	int idx;
	Map<Integer, LinkedList<Integer>> tmap;
	Map<Integer, Set<Integer>> umap;
	
	public Twitter() {
		idx = 0;
		total = new ArrayList<>();
		tmap = new HashMap<>();
		umap = new HashMap<>();
	}
	
	/**
	 * Compose a new tweet.
	 */
	public void postTweet(int userId, int tweetId) {
		LinkedList<Integer> list;
		if (!tmap.containsKey(userId)) {
			list = new LinkedList<>();
			tmap.put(userId, list);
		} else {
			list = tmap.get(userId);
		}
		list.add(idx);
		if (list.size() > 10) {
			list.removeFirst();
		}
		total.add(tweetId);
		idx++;
	}
	
	/**
	 * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
	 */
	public List<Integer> getNewsFeed(int userId) {
		PriorityQueue<Integer> queue = new PriorityQueue<>();
		if (tmap.containsKey(userId)) {
			queue.addAll(tmap.get(userId));
		}
		if (umap.containsKey(userId)) {
			for (Integer integer : umap.get(userId)) {
				LinkedList<Integer> linkedList = tmap.get(integer);
				if (linkedList != null) {
					queue.addAll(linkedList);
					while (queue.size() > 10) {
						queue.poll();
					}
				}
			}
		}
		LinkedList<Integer> ret = new LinkedList<>();
		while (!queue.isEmpty()) {
			ret.addFirst(total.get(queue.poll()));
		}
		return ret;
	}
	
	/**
	 * Follower follows a followee. If the operation is invalid, it should be a no-op.
	 */
	public void follow(int followerId, int followeeId) {
		if(followeeId==followerId){
			return;
		}
		if (!umap.containsKey(followerId)) {
			HashSet<Integer> set = new HashSet<>();
			set.add(followeeId);
			umap.put(followerId, set);
		} else {
			umap.get(followerId).add(followeeId);
		}
	}
	
	/**
	 * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
	 */
	public void unfollow(int followerId, int followeeId) {
		Set<Integer> set = umap.get(followerId);
		if (set != null) {
			set.remove(followeeId);
		}
	}
	
	public static void main(String[] args) {
		Twitter twitter = new Twitter();
		twitter.postTweet(1,1);
		twitter.postTweet(1,2);
		twitter.postTweet(1,3);
		twitter.postTweet(1,4);
		twitter.postTweet(1,5);
		twitter.postTweet(1,6);
		twitter.postTweet(1,7);
		twitter.postTweet(1,8);
		twitter.postTweet(1,9);
		twitter.postTweet(1,10);
		twitter.postTweet(1,11);
		twitter.postTweet(2,12);
		twitter.follow(2,1);
		List<Integer> newsFeed1 = twitter.getNewsFeed(2);
		List<Integer> newsFeed = twitter.getNewsFeed(1);
		System.out.println(newsFeed);
		
	}
}
