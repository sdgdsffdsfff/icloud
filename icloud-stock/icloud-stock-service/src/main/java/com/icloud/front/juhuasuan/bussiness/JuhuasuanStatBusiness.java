package com.icloud.front.juhuasuan.bussiness;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ch.qos.logback.classic.Logger;

import com.icloud.framework.util.DateUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.User;
import com.icloud.stock.model.UserUrlAccessCount;
import com.icloud.stock.service.IUserUrlAccessCountService;
import com.icloud.user.bussiness.po.AllUserPo;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午2:42:51
 */
@Service("juhuasuanStatBusiness")
public class JuhuasuanStatBusiness extends BaseAction {
	@Resource(name = "juhuasuanBussiness")
	protected JuhuasuanBussiness juhuasuanBussiness;

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
			this.userUrlAccessCountService.update(userUrlAccessCount);
		} else {
			userUrlAccessCount = new UserUrlAccessCount();
			userUrlAccessCount.setCount(s);
			userUrlAccessCount.setCreateTime(startDate);
			userUrlAccessCount.setUserId(userId);
			this.userUrlAccessCountService.save(userUrlAccessCount);
		}
	}

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

	public void updateUserUrlAccessCountDaily() {
		/**
		 * 获得所有用户进行更新
		 */
		Date now = new Date();
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
	}
}
