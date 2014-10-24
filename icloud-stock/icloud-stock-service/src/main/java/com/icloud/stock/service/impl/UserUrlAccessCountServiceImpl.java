package com.icloud.stock.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.HiberanateParamters;
import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.dao.hibernate.HiberanateEnum.OperationEnum;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.dao.IJuhuasuanDetailDao;
import com.icloud.stock.dao.IUserUrlAccessCountDao;
import com.icloud.stock.model.UserUrlAccessCount;
import com.icloud.stock.service.IUserUrlAccessCountService;

@Service("userUrlAccessCountService")
public class UserUrlAccessCountServiceImpl extends
		SqlBaseService<UserUrlAccessCount> implements
		IUserUrlAccessCountService {

	@Resource(name = "userUrlAccessCountDao")
	private IUserUrlAccessCountDao userUrlAccessCountDao;

	@Override
	protected IHibernateBaseDao<UserUrlAccessCount> getDao() {
		// TODO Auto-generated method stub
		return userUrlAccessCountDao;
	}

	@Override
	public Date getMaxStatTime(Integer userId) {
		return this.userUrlAccessCountDao.getMaxStatTime(userId);
	}

	@Override
	public UserUrlAccessCount getUserAccessCountByUserIdAndDate(Integer userId,
			Date startDate) {
		HiberanateParamters hiberanateParamters = new HiberanateParamters();
		hiberanateParamters.addOperationsValue(IUserUrlAccessCountDao.USERID,
				OperationEnum.EQUALS, userId);
		hiberanateParamters.addOperationsValue(
				IUserUrlAccessCountDao.CREATETIME, OperationEnum.EQUALS,
				startDate);
		return ICloudUtils.getFirstElement(findByProperty(
				hiberanateParamters.getParams(),
				hiberanateParamters.getOperations(),
				hiberanateParamters.getValues(), null, false, 0, 20));
	}

}
