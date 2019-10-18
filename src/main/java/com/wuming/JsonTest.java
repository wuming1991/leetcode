package com.wuming;

import com.alibaba.fastjson.JSON;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: study
 * @Package: com.wuming
 * @Class JsonTest
 * @Author: WuMing
 * @CreateDate: 2018/9/6 11:28
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class JsonTest {
	
	public static void main(String[] args) {
		HashMap<String, Object> shipping = new HashMap<String, Object>(4);
		shipping.put("name", "张三");
		shipping.put("mobile", 1777777777);
		shipping.put("address", "北京市,北京市,");
		shipping.put("status", 1);
		HashMap<String, Object> map = new HashMap<String, Object>(4);
		map.put("shipping", shipping);
		map.put("retCode", 0);
		map.put("retMsg", "请求成功");
		System.out.println(JSON.toJSONString(map));
	}
}
