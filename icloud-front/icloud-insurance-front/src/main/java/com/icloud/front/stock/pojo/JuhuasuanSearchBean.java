package com.icloud.front.stock.pojo;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.JuhuasuanUrl;

public class JuhuasuanSearchBean extends JuhuasuanUrlBean {
	private int pageNo;
	private int limit = 20;
	private String memberId;
	private String date;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public static JuhuasuanUrl convert(JuhuasuanSearchBean searchBean) {
		if (ICloudUtils.isNotNull(searchBean)) {
			JuhuasuanUrl url = new JuhuasuanUrl();
			url.setName(searchBean.getName());
			if (ICloudUtils.isNotNull(searchBean.getStatus())
					&& !"-1".equalsIgnoreCase(searchBean.getStatus())) {
				url.setStatus(searchBean.getStatus());
			}
			if (ICloudUtils.isNotNull(searchBean.getType())
					&& !"-1".equalsIgnoreCase(searchBean.getType())) {
				url.setType(searchBean.getType());
			}
			if (ICloudUtils.isNotNull(searchBean.getSolidify())
					&& !"-1".equalsIgnoreCase(searchBean.getSolidify())) {
				url.setSolidify(searchBean.getSolidify());
			}
			return url;
		}
		return null;
	}
}
