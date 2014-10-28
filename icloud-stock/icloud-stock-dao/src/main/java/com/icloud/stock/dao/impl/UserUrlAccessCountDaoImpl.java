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
				+ " as model where model.createTime = ? and model.userId "
				+ "in ?";
		Object[] values = { createTime, userIds };
		if (isSingle(userIds)) {
			hql = "select sum(model.count) from " + domainClass.getName()
					+ " as model where model.createTime = ? and model.userId "
					+ "= ?";
			Object[] values2 = { createTime, ICloudUtils.parseInt(userIds) };
			values = values2;
		}
		List list = getHibernateTemplate().find(hql, values);
		if (ICloudUtils.isEmpty(list)) {
			return -1;
		} else {
			long count = (Long) list.get(0);
			int countS = (int) count;
			return countS;
		}
	}

	@Override
	public List<UserUrlAccessCount> getUserAccessCountDetailByUserIdAndDate(
			String userIds, Date createTime) {
		if (isSingle(userIds)) {
			String hql = "from " + domainClass.getName()
					+ " as model where model.createTime = ? and model.userId "
					+ "= ?";
			Object[] values = { createTime, ICloudUtils.parseInt(userIds) };
			return this.findByProperty(hql, values);
		} else {
			String hql = "from " + domainClass.getName()
					+ " as model where model.createTime = ? and model.userId "
					+ "in ?";
			Object[] values = { createTime, userIds };
			return this.findByProperty(hql, values);
		}

	}

	private boolean isSingle(String userIds) {

		if (ICloudUtils.isNotNull(userIds)) {
			String[] list = userIds.split(",");
			int count = 0;
			for (String str : list) {
				if (ICloudUtils.isNotNull(str)) {
					count++;
					if (count > 1)
						return false;
				}
			}
			return true;
		}
		return true;
	}
}
