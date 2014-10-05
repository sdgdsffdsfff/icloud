package com.icloud.front.stock.pojo;

import java.util.Date;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.JuhuasuanUrl;

public class JuhuasuanUrlBean {
	private String name;
	private String taobaoUrl;
	private String status;
	private String desText;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTaobaoUrl() {
		return taobaoUrl;
	}

	public void setTaobaoUrl(String taobaoUrl) {
		this.taobaoUrl = taobaoUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesText() {
		return desText;
	}

	public void setDesText(String desText) {
		this.desText = desText;
	}

	public static JuhuasuanUrl convertJuhuasuanUrlBean(JuhuasuanUrlBean bean) {
		if(ICloudUtils.isNotNull(bean)){
			JuhuasuanUrl url = new JuhuasuanUrl();
			url.setCreateTime(new Date());
			url.setDesText(bean.getDesText());
			url.setName(bean.getName());
			url.setTaobaoUrl(bean.getTaobaoUrl());
			url.setUpdateTime(url.getCreateTime());
			url.setStatus(bean.getStatus());
		}
		return null;
	}

}
