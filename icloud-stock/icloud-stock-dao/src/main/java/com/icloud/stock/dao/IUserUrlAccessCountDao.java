package com.icloud.stock.dao;

import java.util.Date;
import java.util.List;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.UserUrlAccessCount;

public interface IUserUrlAccessCountDao extends
		StockBaseDao<UserUrlAccessCount> {
	public static final String ID = "id";
	public static final String USERID = "userId";
	public static final String COUNT = "count";
	public static final String ALLCOUNT = "allCount";
	public static final String CREATETIME = "createTime";

	public static final int DEF_ALL_COUNT = -1;

	/**
	 * @param userId2
	 * @return Date
	 * @throws
	 */
	Date getMaxStatTime(Integer userId);

	/**
	 * @param createTime2
	 * @return int
	 * @throws
	 */
	int getCountOfAllUser(Date createTime);

	/**
	 * @param createTime2
	 * @param userIds
	 * @return int
	 * @throws
	 */
	int getCountOfUserIds(Date createTime, List<Integer> userIds);

	/**
	 * @param userIds
	 * @param date
	 * @return List<UserUrlAccessCountPo>
	 * @throws
	 */
	List<UserUrlAccessCount> getUserAccessCountDetailByUserIdAndDate(
			List<Integer> userIds, Date date);
}
