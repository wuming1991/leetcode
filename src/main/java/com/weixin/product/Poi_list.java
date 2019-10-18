package com.weixin.product;

/**
 * @ProjectName: study
 * @Package: com.weixin
 * @Class Poi_list
 * @Author: WuMing
 * @CreateDate: 2019/1/4 11:11
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Poi_list {

		private double longitude;
		private double latitude;
		private int radius;
		private String business_name;
		private String branch_name;
		public void setLongitude(double longitude) {
			this.longitude = longitude;
		}
		public double getLongitude() {
			return longitude;
		}
		
		public void setLatitude(double latitude) {
			this.latitude = latitude;
		}
		public double getLatitude() {
			return latitude;
		}
		
		public void setRadius(int radius) {
			this.radius = radius;
		}
		public int getRadius() {
			return radius;
		}
		
		public void setBusiness_name(String business_name) {
			this.business_name = business_name;
		}
		public String getBusiness_name() {
			return business_name;
		}
		
		public void setBranch_name(String branch_name) {
			this.branch_name = branch_name;
		}
		public String getBranch_name() {
			return branch_name;
		}
}
