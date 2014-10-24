package com.icloud.stock.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.dao.IUserUrlAccessCountDao;
import com.icloud.stock.model.UserUrlAccessCount;

@Repository("userUrlAccessCountDao")
public class UserUrlAccessCountDaoImpl extends
		StockBaseDaoImpl<UserUrlAccessCount> implements IUserUrlAccessCountDao {

	@Override
	public Date getMaxStatTime(Integer userId) {
		String hql = "select max(createTime) from " + domainClass.getName()
				+ " x where userId = " + userId;
		List list = getHibernateTemplate().find(hql);
		if (ICloudUtils.isEmpty(list)) {
			return null;
		} else {
			Date date = (Date) list.get(0);
			return date;
		}
	}

	@Override
	public int getCountOfAllUser(Date createTime) {
		String hql = "select sum(model.count) from " + domainClass.getName()
				+ " as model where model.createTime = ?";
		List list = getHibernateTemplate().find(hql, createTime);
		if (ICloudUtils.isEmpty(list)) {
			return -1;
		} else {
			long count = (Long) list.get(0);
			int countS = (int) count;
			return countS;
		}
	}

	@Override
	public int getCountOfUserIds(Date createTime, String userIds) {
		String hql = "select sum(model.count) from " + domainClass.getName()
				+ " as model where model.createTime = ? and model.userId in ?";
		Object[] values = { createTime, userIds };
		List list = getHibernateTemplate().find(hql, values);
		if (ICloudUtils.isEmpty(list)) {
			return -1;
		} else {
			long count = (Long) list.get(0);
			int countS = (int) count;
			return countS;
		}
	}
}
