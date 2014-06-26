package com.icloud.user.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.StringEncoder;
import com.icloud.stock.model.User;
import com.icloud.user.dao.IUserDao;
import com.icloud.user.service.IUserService;

@Service("userService")
public class UserServiceImpl extends SqlBaseService<User> implements
		IUserService {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(UserServiceImpl.class);

	@Resource(name = "userDao")
	private IUserDao userDao;

	@Override
	protected IHibernateBaseDao<User> getDao() {
		return userDao;
	}

	@Override
	public User getUserByUserName(String userName) {
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				IUserDao.USERNAME, userName));
	}

	@Override
	public User getUserByEmail(String email) {
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				IUserDao.USERMAIL, email));
	}

	@Override
	public User getUserByTelphone(String telphone) {
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				IUserDao.USERTEL, telphone));
	}

	@Override
	public User getUser(String email, String password) {
		String[] paramNames = { IUserDao.USERMAIL, IUserDao.PASSWORD };
		String[] values = { email, password };
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				paramNames, values, null, true));
	}

	@Override
	public void resetPassword(User user) {
		if (ICloudUtils.isNotNull(user)) {
			String password = StringEncoder.encrypt(user.getUserName()
					+ "_123456");// 加密
			logger.info("start to update : userName={},password={}",
					user.getUserName(), user.getUserPassword());
			user.setUserPassword(password);
			this.update(user);
			logger.info("end to update: userName={},password={}",
					user.getUserName(), user.getUserPassword());
		}
	}

	@Override
	public void updatePassword(User user, String password) {
		if (ICloudUtils.isNotNull(user)) {
			logger.info("start to update : userName={},password={}",
					user.getUserName(), user.getUserPassword());
			user.setUserPassword(password);
			this.update(user);
			logger.info("end to update: userName={},password={}",
					user.getUserName(), user.getUserPassword());
		}
	}
}
