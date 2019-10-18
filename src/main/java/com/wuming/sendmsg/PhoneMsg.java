package com.wuming.sendmsg;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: study
 * @Package: com.wuming.sendmsg
 * @Class PhoneMsg
 * @Author: WuMing
 * @CreateDate: 2018/9/5 12:01
 * @Description: java类作用描述
 * @Version: 1.0
 * Copyright: Copyright (c) 2018
 */
public class PhoneMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String phone;
	private String content;
	private String templateId;
	private Map<String, String> params = new HashMap<String, String>();
	
	public PhoneMsg() {
	}
	
	public PhoneMsg(String phone, String content, String templateId) {
		this.phone = phone;
		this.content = content;
		this.templateId = templateId;
	}
	
	public void putParams(String key, String value) {
		params.put(key, value);
	}
	
	public Map<String, String> getParams() {
		return this.params;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTemplateId() {
		return templateId;
	}
	
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	
	@Override
	public String toString() {
		return "PhoneMsg [phone=" + phone + ", content=" + content
			+ ", templateId=" + templateId + "]";
	}
	
	public static String getRandom4() {
		return String.valueOf((int) ((Math.random() * 9 + 1) * 1000));
	}
	
	public static String getRandom6() {
		return String.valueOf((int) ((Math.random() * 9 + 1) * 100000));
	}
	
}
