package com.icloud.front.stock.pojo;

import java.util.Date;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.juhuasuan.pojo.JuhuasuanConstants;
import com.icloud.stock.model.JuhuasuanUrl;

public class JuhuasuanUrlBean {
	private String name;
	private String taobaoUrl;
	private String status;
	private String desText;
	private String type;
	private String solidify;
	private String id;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSolidify() {
		return solidify;
	}

	public void setSolidify(String solidify) {
		this.solidify = solidify;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public static JuhuasuanUrl convertJuhuasuanUrlBean(JuhuasuanUrlBean bean) {
		if (ICloudUtils.isNotNull(bean)) {
			JuhuasuanUrl url = new JuhuasuanUrl();
			url.setCreateTime(new Date());
			url.setDesText(bean.getDesText());
			url.setName(bean.getName());
			url.setTaobaoUrl(bean.getTaobaoUrl());
			url.setUpdateTime(url.getCreateTime());
			url.setStatus(JuhuasuanConstants.JUHUASUANSTATUS.value(
					bean.getStatus()).getId());
			url.setType(JuhuasuanConstants.JUHUASUANTYPE.value(bean.getType())
					.getId());
			url.setSolidify(JuhuasuanConstants.JUHUASUANSOLIDIFY.value(
					bean.getSolidify()).getId());
			if (ICloudUtils.isNotNull(bean.getId())) {
				url.setId(ICloudUtils.parseInt(bean.getId()));
			} else {
				url.setId(null);
			}
			return url;
		}
		return null;
	}

}
