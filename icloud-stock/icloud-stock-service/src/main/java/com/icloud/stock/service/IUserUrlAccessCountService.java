package com.icloud.stock.service;

import java.util.Date;

import com.icloud.framework.service.ISqlBaseService;
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

}
