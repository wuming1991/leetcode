package com.weixin.product;

import java.util.List;

/**
 * @ProjectName: study
 * @Package: com.weixin
 * @Class JsonRootBean
 * @Author: WuMing
 * @CreateDate: 2019/1/4 11:09
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class JsonRootBean {
	private List<Product_list> product_list;
	public void setProduct_list(List<Product_list> product_list) {
		this.product_list = product_list;
	}
	public List<Product_list> getProduct_list() {
		return product_list;
	}
}
