package com.icloud.front.juhuasuan.bussiness;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.juhuasuan.util.UrlCodeUtil;
import com.icloud.stock.dao.IJuhuasuanSessionDao;
import com.icloud.stock.dao.IJuhuasuanUrlDao;
import com.icloud.stock.model.JuhuasuanDetail;
import com.icloud.stock.model.JuhuasuanSession;
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

	public Pagination<JuhuasuanUrl> searchJuhuasuanUrl(JuhuasuanUrl searchBean,
			String pageNo, int limit) {
		int pn = 0;
		try {
			pn = Integer.parseInt(pageNo);
		} catch (Exception e) {
			pn = 0;
		}
		List<String> paramsList = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		paramsList.add(IJuhuasuanUrlDao.USERID);
		values.add(searchBean.getUserId());

		if (ICloudUtils.isNotNull(searchBean.getName())) {
			paramsList.add(IJuhuasuanUrlDao.NAME);
			values.add(searchBean.getName());
		}

		if (ICloudUtils.isNotNull(searchBean.getSolidify())) {
			paramsList.add(IJuhuasuanUrlDao.SOLIDIFY);
			values.add(searchBean.getSolidify());
		}

		if (ICloudUtils.isNotNull(searchBean.getStatus())) {
			paramsList.add(IJuhuasuanUrlDao.STATUS);
			values.add(searchBean.getStatus());
		}

		if (ICloudUtils.isNotNull(searchBean.getType())) {
			paramsList.add(IJuhuasuanUrlDao.TYPE);
			values.add(searchBean.getType());
		}
		// String[] params = { IJuhuasuanUrlDao.USERID };
		// Object[] values = { userId };
		String[] params = new String[paramsList.size()];
		return searchJuhuasuanUrl(paramsList.toArray(params), values.toArray(),
				pn, limit);
	}

	public Pagination<JuhuasuanUrl> searchJuhuasuanUrl(String[] params,
			Object[] values, int pageNo, int limit) {
		Pagination<JuhuasuanUrl> pagination = new Pagination<JuhuasuanUrl>();
		pagination.setPageNo(pageNo);
		pagination.setPageSize(limit);

		if (pageNo < 0)
			pageNo = 0;
		if (limit <= 0)
			limit = 40;
		int start = limit * pageNo;
		List<JuhuasuanUrl> resultList = null;
		resultList = new ArrayList<JuhuasuanUrl>();
		long count = this.juhuasuanUrlService.countByProperty(params, values);
		pagination.setTotalItemCount(count);
		List<JuhuasuanUrl> findAll = this.juhuasuanUrlService.findByProperty(
				params, values, null, false, start, limit);
		for (JuhuasuanUrl cs : findAll) {
			resultList.add(cs);
		}
		pagination.setData(resultList);

		pagination.build();
		return pagination;
	}

	public JuhuasuanSession processJuhuasuanSession(JuhuasuanUrl url,
			String sessionId, String localip) {
		String[] params = { IJuhuasuanSessionDao.JUHUASUANID,
				IJuhuasuanSessionDao.SESSIONID };
		Object[] values = { url.getId(), sessionId };
		JuhuasuanSession juhuasuanSession = ICloudUtils
				.getFirstElement(this.juhuasuanSessionService.findByProperty(
						params, values, null, false, 0, 2));
		if (!ICloudUtils.isNotNull(juhuasuanSession)) {
			juhuasuanSession = new JuhuasuanSession();
			juhuasuanSession.setCount(1);
			juhuasuanSession.setCreateTime(new Date());
			juhuasuanSession.setJuhuasuanId(url.getId());
			juhuasuanSession.setUserId(url.getUserId());
			juhuasuanSession.setLastreadIp(localip);
			juhuasuanSession
					.setLastupdateTime(juhuasuanSession.getCreateTime());
			juhuasuanSession.setSessionId(sessionId);
			this.juhuasuanSessionService.save(juhuasuanSession);
		} else {
			juhuasuanSession.setLastreadIp(localip);
			juhuasuanSession.setLastupdateTime(new Date());
			juhuasuanSession.setCount(juhuasuanSession.getCount() + 1);
			this.juhuasuanSessionService.update(juhuasuanSession);
		}
		return juhuasuanSession;

	}

	public void processJuhuasuanDetail(HttpServletRequest request,
			String sessionId, JuhuasuanUrl url) {
		String perfer = url.getIcloudUrl();
		String perferHost = request.getHeader("Referer");
//		String localip = request.getRemoteAddr();
		String localip = request.getHeader("X-Real-IP");
		String code = url.getIcloudUrl();
		int user_id = url.getUserId();

		JuhuasuanDetail detail = new JuhuasuanDetail();
		detail.setCreateTime(new Date());
		detail.setOtherParam(sessionId);
		detail.setPerfer(perfer);
		detail.setPerferHost(perferHost);
		detail.setPerferIp(localip);
		detail.setUrlId(code);
		detail.setUserId(user_id);
		this.juhuasuanDetailService.save(detail);
	}
}
