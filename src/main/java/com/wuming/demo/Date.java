package com.wuming.demo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @ProjectName: study
 * @Package: com.wuming.demo
 * @Class Date
 * @Author: WuMing
 * @CreateDate: 2018/8/24 9:59
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Date {
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
		String gtimelast = sdf.format(c.getTime()); //上月
		System.out.println(gtimelast);
		int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.println(lastMonthMaxDay);
		System.out.println(c.get(Calendar.MONTH));
		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), lastMonthMaxDay, 23, 59, 59);
		
		//按格式输出
		String gtime = sdf.format(c.getTime()); //上月最后一天
		System.out.println(gtime);
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-01  00:00:00");
		String gtime2 = sdf2.format(c.getTime()); //上月第一天
		System.out.println(gtime2);
		
		int businessMonth = 201807;
		int maxDay = 31;
		System.out.println(businessMonth * 100 + 1 + " 00:00:00");
		String start = businessMonth / 100 + "-" + businessMonth % 100 + "-" + maxDay + " 00:00:00";
		System.out.println(start);
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2018);
		calendar.set(Calendar.MONTH, 0);
		System.out.println(calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(100 + 1 + 10 + "111");
	}
}
