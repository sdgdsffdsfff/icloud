package com.icloud.front.juhuasuan.bussiness;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.bussiness.UserAdminBusiness;
import com.icloud.stock.dao.IUserUrlAccessCountDao;
import com.icloud.stock.model.User;
import com.icloud.stock.model.UserUrlAccessCount;
import com.icloud.user.bussiness.po.AllUserPo;
import com.icloud.user.bussiness.po.ChildrenUserPo;
import com.icloud.user.bussiness.po.UserInfoPo;
import com.icloud.user.dict.UserConstants;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午2:42:51
 */
@Service("juhuasuanStatBusiness")
public class JuhuasuanStatBusiness extends BaseAction {
	@Resource(name = "juhuasuanBussiness")
	protected JuhuasuanBussiness juhuasuanBussiness;
	@Resource(name = "userAdminBusiness")
	protected UserAdminBusiness userAdminBusiness;

	public AllUserPo getAllUserPo() {
		AllUserPo allUserPo = new AllUserPo(this.userService, 20);
		return allUserPo;
	}

	@Transactional
	public void updateUserUrlAccessCount(Integer userId, Date startDate,
			Date tmpDate) {
		/**
		 * 需要保证唯一性
		 */
		UserUrlAccessCount userUrlAccessCount = this.userUrlAccessCountService
				.getUserAccessCountByUserIdAndDate(userId, startDate);
		long count = this.juhuasuanBussiness.getCountOfJuhusuanDetail(userId,
				startDate, tmpDate);
		int s = (int) count;
		if (ICloudUtils.isNotNull(userUrlAccessCount)) {
			userUrlAccessCount.setCount(s);
			userUrlAccessCount
					.setAllCount(IUserUrlAccessCountDao.DEF_ALL_COUNT);
			this.userUrlAccessCountService.update(userUrlAccessCount);
		} else {
			userUrlAccessCount = new UserUrlAccessCount();
			userUrlAccessCount.setCount(s);
			userUrlAccessCount.setCreateTime(startDate);
			userUrlAccessCount.setUserId(userId);
			userUrlAccessCount
					.setAllCount(IUserUrlAccessCountDao.DEF_ALL_COUNT);
			this.userUrlAccessCountService.save(userUrlAccessCount);
		}
	}

	@Transactional
	public void updateSingleAccessCount(Integer userId, Date startDate,
			Date endDate) {
		if (ICloudUtils.isNotNull(userId) && ICloudUtils.isNotNull(startDate)
				&& ICloudUtils.isNotNull(endDate)) {

			while (!startDate.after(endDate)) {
				Date tmpDate = DateUtils.addDays(startDate, 1);
				updateUserUrlAccessCount(userId, startDate, tmpDate);
				startDate = tmpDate;
			}
		}
	}

	@Transactional
	public void updateUserSingleAccessCount(User user, Date now) {
		if (!ICloudUtils.isNotNull(user)) {
			return;
		}
		Integer userId = user.getId();
		Date endDate = DateUtils.getDate(DateUtils.addDays(now, -1));

		Date startDate = user.getCreateTime();
		startDate = DateUtils.getDate(startDate);
		/**
		 * 获得数据最大时间
		 */
		Date maxStatTime = this.userUrlAccessCountService
				.getMaxStatTime(userId);
		if (ICloudUtils.isNotNull(maxStatTime)) {
			startDate = DateUtils.getDate(DateUtils.addDays(maxStatTime, 1));
		}
		updateSingleAccessCount(userId, startDate, endDate);
	}

	/**
	 * @param nextUser
	 * @param now
	 *            void
	 * @throws
	 */
	private void updateUserTotalAccessCount(User user) {
		if (!ICloudUtils.isNotNull(user)) {
			return;
		}
		int userId = user.getId();
		List<UserUrlAccessCount> list = this.userUrlAccessCountService
				.getUserAccessCountByNullTotalCount(userId);
		if (!ICloudUtils.isEmpty(list)) {
			boolean isSuper = false;
			String userIds = null;
			if (user.getLevel() == UserConstants.SUPER_USER) {
				isSuper = true;
			} else {
				ChildrenUserPo userPo = this.userAdminBusiness
						.getChildrenUserPo(user);
				List<UserInfoPo> allUserList = userPo.getAllUser();
				allUserList.add(UserInfoPo.convertUser(user));
				userIds = UserInfoPo.getUserIds(allUserList);
			}
			/**
			 * 获得所有用户的子用户
			 */
			for (UserUrlAccessCount count : list) {
				updateAllCount(count, userIds, isSuper);
			}
		}

	}

	/**
	 * @param count
	 * @param allUserList
	 *            void
	 * @throws
	 */
	@Transactional
	private void updateAllCount(UserUrlAccessCount count, String userIds,
			boolean isSuper) {
		if (isSuper) {
			int allCount = 0;
			allCount = this.userUrlAccessCountService.getCountOfAllUser(count
					.getCreateTime());
			count.setAllCount(allCount);
			this.userUrlAccessCountService.update(count);
		} else {
			int allCount = 0;
			allCount = this.userUrlAccessCountService.getCountOfUserIds(
					count.getCreateTime(), userIds);
			count.setAllCount(allCount);
			/**
			 * 批量操作
			 */
			this.userUrlAccessCountService.update(count);
		}

	}

	public void updateUserUrlAccessCountDaily() {
		/**
		 * 获得所有用户进行更新
		 */
		logger.info("start to updateUserUrlAccessCountDaily....");
		Date now = new Date();
		logger.info("start to process updateUserSingleAccessCount....");
		AllUserPo userPo = this.getAllUserPo();
		User nextUser = userPo.next();
		while (ICloudUtils.isNotNull(nextUser)) {
			/**
			 * 丰富这个用户的内容
			 */
			logger.info("updateUserSingleAccessCount, userId:{},userName:{}",
					nextUser.getId(), nextUser.getChinaName());
			updateUserSingleAccessCount(nextUser, now);
			nextUser = userPo.next();
		}
		logger.info("end to process updateUserSingleAccessCount!!!");
		/**
		 * 获得所有用户
		 */
		userPo = this.getAllUserPo();
		nextUser = userPo.next();
		while (ICloudUtils.isNotNull(nextUser)) {
			/**
			 * 丰富这个用户的内容
			 */
			logger.info("updateUserTotalAccessCount, userId:{},userName:{}",
					nextUser.getId(), nextUser.getChinaName());
			updateUserTotalAccessCount(nextUser);
			nextUser = userPo.next();
		}
		logger.info("end to updateUserUrlAccessCountDaily!!!");
		logger.info("end to updateUserUrlAccessCountDaily!!!");
	}

}
