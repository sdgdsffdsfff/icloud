package com.icloud.front.juhuasuan.vo;

import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants;
import com.icloud.stock.model.JuhuasuanUrl;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月10日 下午5:36:42
 */
public class JuhuasuanUrlUtil {
	public static void desc(JuhuasuanUrl url) {
		if (ICloudUtils.isNotNull(url)) {
			url.setType(JuhuasuanConstants.JUHUASUANTYPE.value(url.getType())
					.getName());
			url.setSolidify(JuhuasuanConstants.JUHUASUANSOLIDIFY.value(
					url.getSolidify()).getName());
			url.setStatus(JuhuasuanConstants.JUHUASUANSTATUS.value(
					url.getStatus()).getName());
			url.setIcloudUrl(url.getIcloudUrl());
		}
	}

	public static void desc(List<JuhuasuanUrl> urls) {
		if (ICloudUtils.isEmpty(urls)) {
			return;
		}
		for (JuhuasuanUrl url : urls) {
			desc(url);
		}
	}
}
