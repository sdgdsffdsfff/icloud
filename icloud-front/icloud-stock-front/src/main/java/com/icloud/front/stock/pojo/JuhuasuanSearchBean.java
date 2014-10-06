package com.icloud.front.stock.pojo;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.JuhuasuanUrl;

public class JuhuasuanSearchBean extends JuhuasuanUrlBean {
	private String pageNo;
	private int limit = 2;

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public static JuhuasuanUrl convert(JuhuasuanSearchBean searchBean) {
		if (ICloudUtils.isNotNull(searchBean)) {
			JuhuasuanUrl url = new JuhuasuanUrl();
			url.setName(searchBean.getName());
			if (ICloudUtils.isNotNull(searchBean.getStatus())) {
				url.setStatus(searchBean.getStatus());
			}
			if (ICloudUtils.isNotNull(searchBean.getType())) {
				url.setType(searchBean.getType());
			}
			if (ICloudUtils.isNotNull(searchBean.getSolidify())) {
				url.setSolidify(searchBean.getSolidify());
			}
			return url;
		}
		return null;
	}

}
