package com.wuming;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @ProjectName: study
 * @Package: com.wuming
 * @Class Demo
 * @Author: WuMing
 * @CreateDate: 2018/8/28 16:45
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class Demo {
	
	private Integer a;
	
	public Integer getA() {
		return a;
	}
	
	public void setA(Integer a) {
		this.a = a;
	}
	
	public static void main(String[] args) {
		Date date = new Date();
		int year = date.getYear();
		date.setYear(year + 1);
		System.out.println(date);
		String s = "{\"retCode\":1001,\"retMsg\":\"获取积分异常:Forbid consumer 10.0.9.166 access service com.secoo.user.api.UserService from registry 10.185.240.81:2181 use dubbo version 2.8.4, Please check registry access list (whitelist/blacklist).\"}";
		JSONObject jsonObject = JSON.parseObject(s);
		Integer retCode = jsonObject.getInteger("retCode");
		System.out.println(retCode);
		
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		System.out.println(now.format(dateTimeFormatter));
		LocalDateTime localDateTime = now.plusYears(1L);
		System.out.println(localDateTime.format(dateTimeFormatter));
		//System.out.println(new Date(new Date().getTime() + (365 * 24 * 60 * 60 * 1000)));
		System.out.println("--------");
		Date date1 = new Date();
		System.out.println(date1.getTime());
		System.out.println(365 * 24 * 60 * 60 * 1000);
		System.out.println((date1.getTime() + 365 * 24 * 60 * 60 * 1000));
		date1.setYear(date1.getYear() + 1);
		System.out.println(date1.getTime());
		System.out.println((date1.getTime() + 365 * 24 * 60 * 60 * 1000L));
		String substring = "201807".substring(0, 4);
		System.out.println(substring);
		System.out.println(Integer.valueOf("201807".substring(4)));
		DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyyMM");
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date parse = null;
		try {
			parse = format.parse("20180722");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(parse);
		year = 2018;
		int mon = 07;
		int start = 22;
		String startDate = year + "-" + mon + "-" + start + " 00:00:00";
		System.out.println(startDate);
		
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, 2018);
		a.set(Calendar.MONTH, 2 - 1);
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		System.out.println(maxDate);
	}
}
