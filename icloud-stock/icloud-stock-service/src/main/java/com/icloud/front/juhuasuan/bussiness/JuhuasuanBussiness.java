package com.icloud.front.juhuasuan.bussiness;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.juhuasuan.util.UrlCodeUtil;
import com.icloud.stock.dao.IJuhuasuanUrlDao;
import com.icloud.stock.model.JuhuasuanUrl;

@Service("juhuasuanBussiness")
public class JuhuasuanBussiness extends BaseAction {

	public JuhuasuanUrl saveJuhuasuanUrl(JuhuasuanUrl url) {
		if (ICloudUtils.isNotNull(url)) {
			url.setCreateTime(new Date());
			url.setUpdateTime(url.getCreateTime());
			this.juhuasuanUrlService.save(url);
			String icloudUrl = generateLocalUrl(url);
			url.setIcloudUrl(icloudUrl);
			this.juhuasuanUrlService.update(url);
			return url;
		}
		return null;
	}

	private String generateLocalUrl(JuhuasuanUrl url) {
		return UrlCodeUtil.generateJuhuasuanCode(url.getId());
	}

	public JuhuasuanUrl getJuhuasuanUrlByCode(String code) {
		return ICloudUtils.getFirstElement(this.juhuasuanUrlService
				.findByProperies(IJuhuasuanUrlDao.ICLOUDURL, code));
	}

	public JuhuasuanUrl getJuhuasuanUrlById(int id) {
		return this.juhuasuanUrlService.getById(id);
	}

	public JuhuasuanUrl updateJuhuasuanUrl(JuhuasuanUrl originJuhuasuanUrl,
			JuhuasuanUrl urlBean) {
		if (ICloudUtils.isNotNull(originJuhuasuanUrl)
				&& ICloudUtils.isNotNull(urlBean)) {
			if (ICloudUtils.isNotNull(urlBean.getDesText())) {
				originJuhuasuanUrl.setDesText(urlBean.getDesText());
			}
			if (ICloudUtils.isNotNull(urlBean.getSolidify())) {
				originJuhuasuanUrl.setSolidify(urlBean.getSolidify());
			}
			if (ICloudUtils.isNotNull(urlBean.getStatus())) {
				originJuhuasuanUrl.setStatus(urlBean.getStatus());
			}
			if (ICloudUtils.isNotNull(urlBean.getTaobaoUrl())) {
				originJuhuasuanUrl.setTaobaoUrl(urlBean.getTaobaoUrl());
			}
			if (ICloudUtils.isNotNull(urlBean.getType())) {
				originJuhuasuanUrl.setType(urlBean.getType());
			}
			originJuhuasuanUrl.setUpdateTime(new Date());
			this.juhuasuanUrlService.update(originJuhuasuanUrl);
			return originJuhuasuanUrl;
		}
		return null;
	}

}
