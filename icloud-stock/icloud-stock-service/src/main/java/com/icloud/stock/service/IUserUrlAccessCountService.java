package com.icloud.stock.service;

import java.util.Date;
import java.util.List;

import com.icloud.framework.service.ISqlBaseService;
import com.icloud.front.juhuasuan.bussiness.po.UserUrlAccessCountPo;
import com.icloud.stock.model.UserUrlAccessCount;

public interface IUserUrlAccessCountService extends
		ISqlBaseService<UserUrlAccessCount> {

	/**
	 * @param id
	 * @return Date
	 * @throws
	 */
	Date getMaxStatTime(Integer userId);

	/**
	 * @param userId
	 * @param startDate
	 * @return Object
	 * @throws
	 */
	UserUrlAccessCount getUserAccessCountByUserIdAndDate(Integer userId,
			Date startDate);

	/** 
	 * @param userId
	 * @return
	 * UserUrlAccessCount
	 * @throws 
	 */
	List<UserUrlAccessCount> getUserAccessCountByNullTotalCount(int userId);

	/** 
	 * @param createTime
	 * @return
	 * int
	 * @throws 
	 */
	int getCountOfAllUser(Date createTime);

	/** 
	 * @param createTime
	 * @param userIds
	 * @return
	 * int
	 * @throws 
	 */
	int getCountOfUserIds(Date createTime, List<Integer> userIds);

	/** 
	 * @param userIds
	 * @param date
	 * @return
	 * List<UserUrlAccessCountPo>
	 * @throws 
	 */
	List<UserUrlAccessCount> getUserAccessCountDetailByUserIdAndDate(
			List<Integer> userIds, Date date);

}
