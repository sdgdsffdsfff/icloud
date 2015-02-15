package com.icloud.front.juhuasuan.bussiness;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.WebEnv;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.stock.dao.ITaobaoConstantDao;
import com.icloud.stock.model.TaobaoConstant;
import com.icloud.stock.service.ITaobaoConstantService;

@Service("juhuasuanConstantBussiness")
public class JuhuasuanConstantBussiness extends BaseBussiness {
	public static final String DEFAULT_TAOBAO_URL_NAME = "server.path.taobao.buuyuu";
	@Resource(name = "taobaoConstantService")
	protected ITaobaoConstantService taobaoConstantService;

	public String getTaobaoServerHost(UserInfo info) {
		TaobaoConstant taobaoConstant = getTaobaoConstant(info);
		return taobaoConstant.getValue();
	}

	private TaobaoConstant getTaobaoConstant(UserInfo info) {
		TaobaoConstant taobaoConstant = ICloudUtils
				.getFirstElement(taobaoConstantService.findByProperies(
						ITaobaoConstantDao.NAME, DEFAULT_TAOBAO_URL_NAME));
		if (ICloudUtils.isNotNull(taobaoConstant)) {

		} else {
			taobaoConstant = new TaobaoConstant();
			taobaoConstant.setName(DEFAULT_TAOBAO_URL_NAME);
			taobaoConstant.setValue(WebEnv.get(DEFAULT_TAOBAO_URL_NAME));
			taobaoConstant.setUpdateTime(new Date());
			taobaoConstant.setUserId(info.getUserId());
			taobaoConstant.setUserName(info.getUserName());
			this.taobaoConstantService.save(taobaoConstant);
		}
		return taobaoConstant;
	}

	public boolean update(String taobaoUrl, UserInfo info) {
		TaobaoConstant taobaoConstant = getTaobaoConstant(info);
		if (ICloudUtils.isNotNull(taobaoUrl)
				&& ICloudUtils.isNotNull(taobaoConstant)
				&& !taobaoUrl.equals(taobaoConstant.getValue())) {
			if (info.isUrlOper()) {
				taobaoConstant.setValue(taobaoUrl);
				taobaoConstant.setUpdateTime(new Date());
				taobaoConstant.setUserId(info.getUserId());
				taobaoConstant.setUserName(info.getUserName());
				this.taobaoConstantService.update(taobaoConstant);
				return true;
			}
		}
		return false;
	}

	public UserInfo fillTaobaoUrl(UserInfo info) {
		if (ICloudUtils.isNotNull(info)) {
			String url = getTaobaoServerHost(info);
			if (!ICloudUtils.isNotNull(url)) {
				url = WebEnv.get(DEFAULT_TAOBAO_URL_NAME);
			}
//			if (!url.endsWith("/"))
//				url = url + "/";
			info.setTaobaoUrl(url);
			String href = url;
			if (!href.startsWith("http://")) {
				href = "http://" + href;
			}
			info.setTaobaohosthref(href);
		}
		return info;

	}
}
