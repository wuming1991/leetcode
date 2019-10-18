package com.weixin.want;

import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.weixin.want
 * @Class JsonRootBean
 * @Author: WuMing
 * @CreateDate: 2019/1/4 11:47
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class JsonRootBean {
	
	
	private String user_open_id;
	private List<Sku_product_list> sku_product_list;
	
	public void setUser_open_id(String user_open_id) {
		this.user_open_id = user_open_id;
	}
	
	public String getUser_open_id() {
		return user_open_id;
	}
	
	public void setSku_product_list(List<Sku_product_list> sku_product_list) {
		this.sku_product_list = sku_product_list;
	}
	
	public List<Sku_product_list> getSku_product_list() {
		return sku_product_list;
	}
	
	
}
