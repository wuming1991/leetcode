package com.weixin.product;

import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.weixin
 * @Class Sku_list
 * @Author: WuMing
 * @CreateDate: 2019/1/4 11:11
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Sku_list {
	
	
	private String sku_id;
	private int price;
	private int original_price;
	private int status;
	private List<Sku_attr_list> sku_attr_list;
	private int version;
	private List<Poi_list> poi_list;
	
	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}
	
	public String getSku_id() {
		return sku_id;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public void setOriginal_price(int original_price) {
		this.original_price = original_price;
	}
	
	public int getOriginal_price() {
		return original_price;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setSku_attr_list(List<Sku_attr_list> sku_attr_list) {
		this.sku_attr_list = sku_attr_list;
	}
	
	public List<Sku_attr_list> getSku_attr_list() {
		return sku_attr_list;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setPoi_list(List<Poi_list> poi_list) {
		this.poi_list = poi_list;
	}
	
	public List<Poi_list> getPoi_list() {
		return poi_list;
	}
	
}

