package com.leetcode;

import java.util.Random;

/**
 * @ProjectName: study
 * @Package: com.leetcode
 * @Class RandPointGet
 * @Author: WuMing
 * @CreateDate: 2019/9/26 19:00
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class RandPointGet {
	
	double x_center;
	double y_center;
	double radius;
	
	public RandPointGet(double x_center, double y_center, double radius) {
		this.x_center = x_center;
		this.y_center = y_center;
		this.radius = radius;
	}
	
	public double[] randPoint() {
		Random random = new Random();
		double x = x_center-radius + 2*radius*random.nextDouble();
		double y = y_center-radius + 2*radius*random.nextDouble();
		while(Math.sqrt(Math.pow(x - x_center, 2) + Math.pow(y - y_center, 2)) > radius){
			x = x_center-radius + 2*radius*random.nextDouble();
			y = y_center-radius + 2*radius*random.nextDouble();
		}
		return new double[]{x, y};
	}
	
	public static void main(String[] args) {
		RandPointGet randPointGet = new RandPointGet(1, 1, 1);
		double[] doubles = randPointGet.randPoint();
		System.out.println(doubles[0] + " " + doubles[1]);
		doubles = randPointGet.randPoint();
		System.out.println(doubles[0] + " " + doubles[1]);
		doubles = randPointGet.randPoint();
		System.out.println(doubles[0] + " " + doubles[1]);
		doubles = randPointGet.randPoint();
		System.out.println(doubles[0] + " " + doubles[1]);
		doubles = randPointGet.randPoint();
		System.out.println(doubles[0] + " " + doubles[1]);
		
	}
}
