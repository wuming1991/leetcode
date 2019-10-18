package com.weixin.want;

import com.alibaba.fastjson.JSON;
import java.util.ArrayList;

/**
 * @ProjectName: study
 * @Package: com.weixin.want
 * @Class Test
 * @Author: WuMing
 * @CreateDate: 2019/1/4 14:25
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2019
 */
public class Test {
	
	public static void main(String[] args) {
		JsonRootBean jsonRootBean = new JsonRootBean();
		jsonRootBean.setUser_open_id("user_open_id");
		ArrayList<Sku_product_list> sku_product_lists = new ArrayList<>();
		jsonRootBean.setSku_product_list(sku_product_lists);
		Sku_product_list sku_product_list = new Sku_product_list();
		sku_product_lists.add(sku_product_list);
		sku_product_list.setDesc("product_description");
		sku_product_list.setTitle("product_name");
		sku_product_list.setItem_code("here_is_spu_id");
		ArrayList<String> category = new ArrayList<>();
		ArrayList<String> img = new ArrayList<>();
		sku_product_list.setCategory_list(category);
		sku_product_list.setImage_list(img);
		category.add("服装");
		category.add("上衣");
		category.add("短袖衬衫");
		img.add("image_url1");
		img.add("image_url2");
		sku_product_list.setSrc_wxapp_path("/detail?item_code=xxxx");
		ArrayList<Attr_list> attr_lists = new ArrayList<>();
		sku_product_list.setAttr_list(attr_lists);
		Attr_list attr_list1 = new Attr_list();
		Attr_list attr_list2 = new Attr_list();
		Attr_list attr_list3 = new Attr_list();
		attr_lists.add(attr_list1);
		attr_lists.add(attr_list2);
		attr_lists.add(attr_list3);
		attr_list1.setName("材质");
		attr_list2.setName("款式");
		attr_list3.setName("季度");
		attr_list1.setValue("纯棉");
		attr_list2.setValue("短袖");
		attr_list3.setValue("2018年秋");
		sku_product_list.setVersion(100);
		sku_product_list.setUpdate_time(1542868721L);
		Sku_info sku_info = new Sku_info();
		sku_product_list.setSku_info(sku_info);
		sku_info.setSku_id("sku_id2");
		sku_info.setPrice(10010);
		sku_info.setOriginal_price(20010);
		sku_info.setStatus(1);
		ArrayList<Sku_attr_list> sku_attr_lists = new ArrayList<>();
		sku_info.setSku_attr_list(sku_attr_lists);
		Sku_attr_list sku_attr_list1 = new Sku_attr_list();
		Sku_attr_list sku_attr_list2 = new Sku_attr_list();
		sku_attr_lists.add(sku_attr_list1);
		sku_attr_lists.add(sku_attr_list2);
		sku_attr_list1.setName("颜色");
		sku_attr_list1.setValue("黑色");
		sku_attr_list2.setValue("XXL");
		sku_attr_list2.setName("码数");
		sku_info.setVersion(1200);
		String s = JSON.toJSONString(jsonRootBean);
		System.out.println(s);
	}
}
