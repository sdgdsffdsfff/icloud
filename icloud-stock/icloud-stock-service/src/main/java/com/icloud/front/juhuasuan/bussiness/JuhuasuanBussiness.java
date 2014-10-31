package com.icloud.front.juhuasuan.bussiness;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.dao.hibernate.HiberanateEnum.OperationEnum;
import com.icloud.framework.dao.hibernate.HiberanateParamters;
import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.juhuasuan.bussiness.po.UserUrlAccessCountPo;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants;
import com.icloud.front.juhusuan.pojo.JuhuasuanFrontSession;
import com.icloud.front.user.bussiness.UserAdminBusiness;
import com.icloud.juhuasuan.util.ReferUtil;
import com.icloud.juhuasuan.util.UrlCodeUtil;
import com.icloud.stock.dao.IJuhuasuanDetailDao;
import com.icloud.stock.dao.IJuhuasuanSessionDao;
import com.icloud.stock.dao.IJuhuasuanUrlDao;
import com.icloud.stock.dao.IUserUrlAccessCountDao;
import com.icloud.stock.dict.StockConstants;
import com.icloud.stock.model.JuhuasuanDetail;
import com.icloud.stock.model.JuhuasuanSession;
import com.icloud.stock.model.JuhuasuanUrl;
import com.icloud.stock.model.User;
import com.icloud.stock.model.UserUrlAccessCount;
import com.icloud.user.bussiness.po.UserInfoPo;

@Service("juhuasuanBussiness")
public class JuhuasuanBussiness extends BaseAction {
	@Resource(name = "userAdminBusiness")
	protected UserAdminBusiness userAdminBusiness;

	/**
	 * 这个是不需要加入锁机制的。
	 * 
	 * @param url
	 * @return JuhuasuanUrl
	 * @throws
	 */
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
			if (ICloudUtils.isNotNull(urlBean.getName())) {
				originJuhuasuanUrl.setName(urlBean.getName());
			}
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
			if (ICloudUtils.isNotNull(urlBean.getOriginUrl())) {
				originJuhuasuanUrl.setOriginUrl(urlBean.getOriginUrl());
			}
			originJuhuasuanUrl.setUpdateTime(new Date());
			this.juhuasuanUrlService.update(originJuhuasuanUrl);
			return originJuhuasuanUrl;
		}
		return null;
	}

	public List<JuhuasuanUrl> searchAllJuhuasuanUrl(JuhuasuanUrl searchBean) {
		Pagination<JuhuasuanUrl> pagination = searchJuhuasuanUrl(searchBean, 0,
				20);
		// long count = pagination.getTotalItemCount();
		int pageCount = pagination.getTotalPageCount();
		List<JuhuasuanUrl> list = new ArrayList<JuhuasuanUrl>();
		list.addAll(pagination.getData());
		for (int i = 1; i < pageCount; i++) {
			pagination = searchJuhuasuanUrl(searchBean, i, 1);
			list.addAll(pagination.getData());
		}
		// JuhuasuanUrlUtil.desc(list);
		return list;
	}

	public Pagination<JuhuasuanUrl> searchJuhuasuanUrl(JuhuasuanUrl searchBean,
			int pn, int limit) {
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
		String[] params = new String[paramsList.size()];
		return searchJuhuasuanUrl(paramsList.toArray(params), values.toArray(),
				pn, limit);
	}

	public Pagination<JuhuasuanUrl> searchJuhuasuanUrl(String[] params,
			Object[] values, int pageNo, int limit) {
		Pagination<JuhuasuanUrl> pagination = Pagination.getInstance(pageNo,
				limit);
		List<JuhuasuanUrl> resultList = null;
		resultList = new ArrayList<JuhuasuanUrl>();
		long count = this.juhuasuanUrlService.countByProperty(params, values);
		pagination.setTotalItemCount(count);
		List<JuhuasuanUrl> findAll = this.juhuasuanUrlService.findByProperty(
				params, values, IJuhuasuanUrlDao.UPDATETIME, false,
				pagination.getPageNo() * pagination.getPageSize(),
				pagination.getPageSize());
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
		// String localip = request.getRemoteAddr();
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
		detail.setValid(ReferUtil.isValid(perferHost));
		this.juhuasuanDetailService.save(detail);
	}

	/**
	 * @param urlBean
	 * @param pageNo
	 * @param limit
	 * @return Pagination<JuhuasuanUrlVO>
	 * @throws
	 */
	public Pagination<JuhuasuanUrl> searchJuhuasuanUrl(JuhuasuanUrl urlBean,
			String pageNo, int limit) {
		int pn = ICloudUtils.parseInt(pageNo, 0);
		return searchJuhuasuanUrl(urlBean, pn, limit);
	}

	/**
	 * @param userId
	 * @param pageNo
	 * @param limit
	 *            void
	 * @throws
	 */
	public Pagination<JuhuasuanDetail> getDurrentDayJuhuasuanDetailByUserId(
			int userId, int pageNo, int limit) {
		Date startDate = DateUtils.getDate(new Date(),
				StockConstants.STOCK_HISTORY_STRING);
		return getJuhuasuanDetail(startDate, null, userId, pageNo, limit);
		// return getJuhuasuanDetail(null, null, userId, pageNo, limit);
	}

	public Pagination<JuhuasuanDetail> get30DaysJuhuasuanDetailByUserId(
			int userId, int pageNo, int limit) {
		Date date = new Date();
		date = DateUtils.addDays(date, -30);
		Date startDate = DateUtils.getDate(date,
				StockConstants.STOCK_HISTORY_STRING);
		Date endDate = DateUtils.addDays(startDate, 1);
		// return getJuhuasuanDetail(startDate, null, userId, pageNo, limit);
		return getJuhuasuanDetail(null, null, userId, pageNo, limit);
	}

	public long getCountOfJuhusuanDetailInCurrentDay(int userId) {
		Date startDate = DateUtils.getDate(new Date(),
				StockConstants.STOCK_HISTORY_STRING);
		Date endDate = DateUtils.addDays(startDate, 1);
		return getCountOfJuhusuanDetail(userId, startDate, endDate);
	}

	public long getValidCountOfJuhusuanDetailInCurrentDay(int userId) {
		Date startDate = DateUtils.getDate(new Date(),
				StockConstants.STOCK_HISTORY_STRING);
		Date endDate = DateUtils.addDays(startDate, 1);
		return getValidCountOfJuhusuanDetail(userId, startDate, endDate);
	}

	public long getCountOfJuhusuanDetailInLastDay(int userId) {
		Date date = new Date();
		date = DateUtils.addDays(date, -1);
		Date startDate = DateUtils.getDate(date,
				StockConstants.STOCK_HISTORY_STRING);
		Date endDate = DateUtils.addDays(startDate, 1);
		return getCountOfJuhusuanDetail(userId, startDate, endDate);
	}

	public long getValidCountOfJuhusuanDetailInLastDay(int userId) {
		Date date = new Date();
		date = DateUtils.addDays(date, -1);
		Date startDate = DateUtils.getDate(date,
				StockConstants.STOCK_HISTORY_STRING);
		Date endDate = DateUtils.addDays(startDate, 1);
		return getValidCountOfJuhusuanDetail(userId, startDate, endDate);
	}

	public Pagination<JuhuasuanDetail> getJuhuasuanDetail(Date startDate,
			Date endDate, int userId, int pageNo, int limit) {
		Pagination<JuhuasuanDetail> pagination = Pagination.getInstance(pageNo,
				limit);

		HiberanateParamters hiberanateParamters = new HiberanateParamters();
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.USERID,
				OperationEnum.EQUALS, userId);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.BIGGER_ADN_EQUALS, startDate);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.LESS, endDate);

		long count = this.juhuasuanDetailService.countByProperty(
				hiberanateParamters.getParams(),
				hiberanateParamters.getOperations(),
				hiberanateParamters.getValues());
		pagination.setTotalItemCount(count);

		List<JuhuasuanDetail> detailList = this.juhuasuanDetailService
				.findByProperty(hiberanateParamters.getParams(),
						hiberanateParamters.getOperations(),
						hiberanateParamters.getValues(),
						IJuhuasuanDetailDao.CREATETIME, false,
						pagination.getPageNo() * pagination.getPageSize(),
						pagination.getPageSize());

		pagination.setData(detailList);
		pagination.build();
		return pagination;
	}

	/**
	 * @param userId
	 * @param startDate
	 * @param endDate
	 * @return long
	 * @throws
	 */
	@Transactional
	public long getCountOfJuhusuanDetail(int userId, Date startDate,
			Date endDate) {
		HiberanateParamters hiberanateParamters = new HiberanateParamters();

		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.USERID,
				OperationEnum.EQUALS, userId);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.BIGGER_ADN_EQUALS, startDate);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.LESS, endDate);

		return this.juhuasuanDetailService.countByProperty(
				hiberanateParamters.getParams(),
				hiberanateParamters.getOperations(),
				hiberanateParamters.getValues());
	}

	@Transactional
	public long getValidCountOfJuhusuanDetail(int userId, Date startDate,
			Date endDate) {
		HiberanateParamters hiberanateParamters = new HiberanateParamters();

		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.USERID,
				OperationEnum.EQUALS, userId);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.BIGGER_ADN_EQUALS, startDate);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.LESS, endDate);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.VALID,
				OperationEnum.EQUALS, 1);

		return this.juhuasuanDetailService.countByProperty(
				hiberanateParamters.getParams(),
				hiberanateParamters.getOperations(),
				hiberanateParamters.getValues());
	}

	public Pagination<JuhuasuanFrontSession> getJuhuaSessionByUserId(
			int userId, int pageNo, int limit) {
		Pagination<JuhuasuanFrontSession> pagination = Pagination.getInstance(
				pageNo, limit);
		String countHql = "select count(distinct juhuasuanId) from JuhuasuanSession as js where js.userId = "
				+ userId;
		long count = this.juhuasuanSessionService.count(countHql);
		pagination.setTotalItemCount(count);

		String hql = "select id,juhuasuanId,userId,sessionId,sum(js.count) as totalCount from JuhuasuanSession as js where js.userId = "
				+ userId
				+ " group by js.juhuasuanId order by sum(js.count) desc";
		List<JuhuasuanSession> tmpList = this.juhuasuanSessionService
				.findByProperty(hql,
						pagination.getPageNo() * pagination.getPageSize(),
						pagination.getPageSize());
		List<JuhuasuanFrontSession> sessionList = new ArrayList<JuhuasuanFrontSession>();
		for (Object object : tmpList) {
			Object[] objs = (Object[]) object;
			JuhuasuanFrontSession session = new JuhuasuanFrontSession();
			session.setId((Integer) objs[0]);
			Integer juhuasuanId = (Integer) objs[1];
			JuhuasuanUrl url = this.juhuasuanUrlService.getById(juhuasuanId);
			String code = juhuasuanId + "";
			if (ICloudUtils.isNotNull(url)) {
				code = url.getIcloudUrl();
			}
			session.setIcloudUrl(code);
			session.setUserId((Integer) objs[2]);
			session.setSessionId((String) objs[3]);
			session.setCount((Long) objs[4]);
			sessionList.add(session);
		}
		pagination.setData(sessionList);
		pagination.build();
		return pagination;
	}

	public String batchUpdateUrl(List<JuhuasuanUrl> list, int userId) {
		int errorCount = 0;
		int updateCount = 0;
		int addCount = 0;
		int notUpdateCount = 0;
		if (!ICloudUtils.isEmpty(list)) {
			for (JuhuasuanUrl url : list) {
				if (ICloudUtils.isNotNull(url.getIcloudUrl())) {
					JuhuasuanUrl newUrl = getJuhuasuanUrlByCode(url
							.getIcloudUrl());
					if (initUpdateUrl(newUrl, url, userId)) {
						newUrl.setUpdateTime(new Date());
						this.juhuasuanUrlService.update(newUrl);
						updateCount++;
					} else {
						notUpdateCount++;
					}
				} else {// 新增
					if (ICloudUtils.isNotNull(url.getName())
							&& ICloudUtils.isNotNull(url.getOriginUrl())) {
						url.setStatus(JuhuasuanConstants.JUHUASUANSTATUS.RUNNING
								.getId());
						url.setSolidify(JuhuasuanConstants.JUHUASUANSOLIDIFY.SOLIDIFY
								.getId());
						url.setType(JuhuasuanConstants.JUHUASUANTYPE.SITE
								.getId());
						url.setUserId(userId);
						saveJuhuasuanUrl(url);
						addCount++;
					} else {
						errorCount++;
					}
				}

			}
		}
		return "成功添加：" + addCount + "; 成功更新:" + updateCount + "; 不用更新:"
				+ notUpdateCount + "; 出错:" + errorCount;
	}

	private boolean initUpdateUrl(JuhuasuanUrl newUrl, JuhuasuanUrl url,
			int userId) {
		if (!ICloudUtils.isNotNull(newUrl))
			return false;
		if (!ICloudUtils.isNotNull(url))
			return false;
		if (userId != newUrl.getUserId())
			return false;
		boolean flag = false;
		if (ICloudUtils.isNotNull(url.getName())
				&& !ICloudUtils.isSame(url.getName(), newUrl.getName())) {
			flag = true;
			newUrl.setName(url.getName());
		}
		if (ICloudUtils.isNotNull(url.getTaobaoUrl())
				&& !ICloudUtils.isSame(url.getTaobaoUrl(),
						newUrl.getTaobaoUrl())) {
			flag = true;
			newUrl.setTaobaoUrl(url.getTaobaoUrl());
		}

		if (!ICloudUtils.isSame(url.getDesText(), newUrl.getDesText())) {
			flag = true;
			newUrl.setDesText(url.getDesText());
		}

		if (!ICloudUtils.isSame(url.getOriginUrl(), newUrl.getOriginUrl())) {
			flag = true;
			newUrl.setOriginUrl(url.getOriginUrl());
		}
		return flag;
	}

	/**
	 * @param userId
	 * @param pageNo
	 * @param limit
	 *            void
	 * @throws
	 */
	public Pagination<UserUrlAccessCountPo> getJuhuaSuanUserAccessCountByUserId(
			User user, int pageNo, int limit) {
		Pagination<UserUrlAccessCountPo> pagination = Pagination.getInstance(
				pageNo, limit);
		HiberanateParamters hiberanateParamters = new HiberanateParamters();

		hiberanateParamters.addOperationsValue(IUserUrlAccessCountDao.USERID,
				OperationEnum.EQUALS, user.getId());
		long count = this.userUrlAccessCountService.countByProperty(
				hiberanateParamters.getParams(),
				hiberanateParamters.getOperations(),
				hiberanateParamters.getValues());
		pagination.setTotalItemCount(count);
		List<UserUrlAccessCount> list = this.userUrlAccessCountService
				.findByProperty(hiberanateParamters.getParams(),
						hiberanateParamters.getOperations(),
						hiberanateParamters.getValues(),
						IUserUrlAccessCountDao.CREATETIME, false,
						pagination.getStart(), pagination.getPageSize());
		pagination.setData(UserUrlAccessCountPo.convertUserUrlAccessCount(list,
				user));
		pagination.build();
		return pagination;
	}

	/**
	 * @param tmpUser
	 * @param date
	 * @param pageNo
	 * @param limit
	 * @return Pagination<UserUrlAccessCountPo>
	 * @throws
	 */
	public Pagination<UserUrlAccessCountPo> getJuhuaSuanUserAccessCountDetaiByUserIdAndDate(
			User tmpUser, Date date, int pageNo, int limit) {
		Pagination<UserInfoPo> p = userAdminBusiness.getUsersByUser(tmpUser,
				pageNo, limit);
		Pagination<UserUrlAccessCountPo> pagination = Pagination.getInstance(
				pageNo, limit);
		pagination.setTotalItemCount(p.getTotalItemCount());
		if (!ICloudUtils.isEmpty(p.getData())) {
			List<Integer> userIds = UserInfoPo.getUserIds(p.getData());
			List<UserUrlAccessCount> list = this.userUrlAccessCountService
					.getUserAccessCountDetailByUserIdAndDate(userIds, date);
			HashMap<Integer, UserInfoPo> map = new HashMap<Integer, UserInfoPo>();
			for (UserInfoPo user : p.getData()) {
				map.put(user.getUserId(), user);
			}
			List<UserUrlAccessCountPo> userUrlAccessCountPos = new ArrayList<UserUrlAccessCountPo>();
			for (UserUrlAccessCount count : list) {
				userUrlAccessCountPos.add(UserUrlAccessCountPo
						.convertUserUrlAccessCount(count,
								map.get(count.getUserId())));
			}
			pagination.setData(userUrlAccessCountPos);
		}
		pagination.build();
		return pagination;
	}

	public void deleteUrl(JuhuasuanUrl url) {
		this.juhuasuanUrlService.delete(url);
	}

	public void updateAllJuhuasuanDetail() {
		List<JuhuasuanDetail> list = this.juhuasuanDetailService.findAll();
		for (JuhuasuanDetail detail : list) {
			detail.setValid(ReferUtil.isValid(detail.getPerferHost()));
			this.updateJuhuasuanDetail(detail);
		}
	}

	private void updateJuhuasuanDetail(JuhuasuanDetail detail) {
		this.juhuasuanDetailService.update(detail);
	}

	public long getCountOfJuhusuanDetailInValid(Integer userId, Date startDate,
			Date endDate) {
		HiberanateParamters hiberanateParamters = new HiberanateParamters();

		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.USERID,
				OperationEnum.EQUALS, userId);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.BIGGER_ADN_EQUALS, startDate);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.CREATETIME,
				OperationEnum.LESS, endDate);
		hiberanateParamters.addOperationsValue(IJuhuasuanDetailDao.VALID,
				OperationEnum.EQUALS, 1);

		return this.juhuasuanDetailService.countByProperty(
				hiberanateParamters.getParams(),
				hiberanateParamters.getOperations(),
				hiberanateParamters.getValues());
	}

}
