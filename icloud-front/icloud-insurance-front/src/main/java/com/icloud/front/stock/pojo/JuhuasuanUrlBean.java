package com.icloud.front.stock.pojo;

import java.util.Date;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants;
import com.icloud.stock.dao.IJuhuasuanUrlDao;
import com.icloud.stock.model.JuhuasuanUrl;

public class JuhuasuanUrlBean {
	private String name;
	private String taobaoUrl;
	private String status;
	private String desText;
	private String type;
	private String solidify;
	private String id;
	private String originUrl;
	private String moreflag;
	private String moreUrl;

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

	public String getOriginUrl() {
		return originUrl;
	}

	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}

	public String getMoreflag() {
		return moreflag;
	}

	public void setMoreflag(String moreflag) {
		this.moreflag = moreflag;
	}

	public String getMoreUrl() {
		return moreUrl;
	}

	public void setMoreUrl(String moreUrl) {
		this.moreUrl = moreUrl;
	}

	public static JuhuasuanUrl convertJuhuasuanUrlBean(JuhuasuanUrlBean bean) {
		if (ICloudUtils.isNotNull(bean)) {
			JuhuasuanUrl url = new JuhuasuanUrl();
			url.setCreateTime(new Date());
			url.setDesText(bean.getDesText());
			url.setName(bean.getName());
			url.setTaobaoUrl(bean.getTaobaoUrl());
			url.setUpdateTime(url.getCreateTime());
			url.setOriginUrl(bean.getOriginUrl());
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
			String moreUrl = bean.getMoreUrl();
			if (ICloudUtils.isNotNull(moreUrl)) {
				if (moreUrl.endsWith(IJuhuasuanUrlDao.URL_SEP)) {
					moreUrl = moreUrl.substring(0, moreUrl.length()
							- IJuhuasuanUrlDao.URL_SEP.length());
				}
			}
			url.setMoreFlag(JuhuasuanConstants.JUHUASUANURLTYPE.value(
					ICloudUtils.parseInt(bean.getMoreflag(), 0)).getId());
			url.setMoreUrl(moreUrl);
			return url;
		}
		return null;
	}
}
