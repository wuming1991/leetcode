package com.weixin.want;

import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.weixin.want
 * @Class Sku_product_list
 * @Author: WuMing
 * @CreateDate: 2019/1/4 11:49
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Sku_product_list {
	
	private String item_code;
	private String title;
	private String desc;
	private List<String> category_list;
	private List<String> image_list;
	private String src_wxapp_path;
	private List<Attr_list> attr_list;
	private int version;
	private long update_time;
	private Sku_info sku_info;
	
	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}
	
	public String getItem_code() {
		return item_code;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void setCategory_list(List<String> category_list) {
		this.category_list = category_list;
	}
	
	public List<String> getCategory_list() {
		return category_list;
	}
	
	public void setImage_list(List<String> image_list) {
		this.image_list = image_list;
	}
	
	public List<String> getImage_list() {
		return image_list;
	}
	
	public void setSrc_wxapp_path(String src_wxapp_path) {
		this.src_wxapp_path = src_wxapp_path;
	}
	
	public String getSrc_wxapp_path() {
		return src_wxapp_path;
	}
	
	public void setAttr_list(List<Attr_list> attr_list) {
		this.attr_list = attr_list;
	}
	
	public List<Attr_list> getAttr_list() {
		return attr_list;
	}
	
	public void setVersion(int version) {
		this.version = version;
	}
	
	public int getVersion() {
		return version;
	}
	
	public void setUpdate_time(long update_time) {
		this.update_time = update_time;
	}
	
	public long getUpdate_time() {
		return update_time;
	}
	
	public void setSku_info(Sku_info sku_info) {
		this.sku_info = sku_info;
	}
	
	public Sku_info getSku_info() {
		return sku_info;
	}
	
}
