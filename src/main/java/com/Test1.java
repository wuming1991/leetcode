package com;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ProjectName: study
 * @Package: com
 * @Class Test1
 * @Author: WuMing
 * @CreateDate: 2019/1/7 14:30
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test1 {
	
	private String[] a;
	
	public Test1(String[] a) {
		this.a = a;
	}
	
	public String getA(int i) {
		return a[i];
	}
	
	public void setA(String[] a) {
		this.a = a;
	}
	
	public static void main(String[] args) {
		/*String s= "{\"cartItems\":[{\"addFrom\":\"scfx1002u002\",\"areaType\":0,\"checkedService\":\"\",\"historyPrice\":1637.00,\"isChecked\":1,\"itemtype\":0,\"packageId\":0,\"productId\":41334318,\"quantity\":1},{\"addFrom\":\"scfx1002u002\",\"areaType\":4,\"checkedService\":\"\",\"historyPrice\":4999.00,\"isChecked\":1,\"itemtype\":0,\"packageId\":0,\"productId\":29554108,\"quantity\":1},{\"addFrom\":\"scfx1002u002\",\"areaType\":0,\"checkedService\":\"\",\"historyPrice\":1366.00,\"isChecked\":1,\"itemtype\":0,\"packageId\":0,\"productId\":10710962,\"quantity\":1}],\"userId\":461710468815,\"version\":1551667754452}";
		JSONObject obj = JSON.parseObject(s);
		JSONArray cartItems = obj.getJSONArray("cartItems");
		for (int i = 0; i < cartItems.size(); i++) {
			System.out.println(cartItems.getJSONObject(i).getLong("productId"));
		}*/
		String s="_2_3_3_";
		String[] split = s.split("_");
		for (String s1 : split) {
			System.out.println(s1);
		}
		/*List<AA> aas = new ArrayList<>(2);
		aas.add(new AA(1,2));
		aas.add(new AA(3,2));
		aas.add(new AA(2,2));
		HashMap<String, Object> map = new HashMap<>();
		map.put("aaa",aas);
		System.out.println(JSON.toJSONString(map));
		System.out.println(Integer.valueOf("01"));*/
		
	}
	
	public static int[] twoSum(int[] nums, int target) {
		int mask = 2048;
		int[] hash = new int[mask--];
		int first = nums[0];
		for (int i = 1; i < nums.length; i++) {
			int diff = target - nums[i];
			if (first + nums[i] == target) {
				return new int[]{0, i};
			} else {
				int index = hash[diff & mask];
				if (index > 0) {
					return new int[]{i, index};
				}
			}
			hash[nums[i] & mask] = i;
		}
		return new int[0];
	}
	
	static class AA {
		
		int anInt;
		int b;
		
		public AA(int anInt, int b) {
			this.anInt = anInt;
			this.b = b;
		}
	}
}
