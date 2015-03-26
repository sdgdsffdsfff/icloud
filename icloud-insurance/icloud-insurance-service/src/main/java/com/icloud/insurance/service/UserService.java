package com.icloud.insurance.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.StringEncoder;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.front.user.pojo.RegisterUser;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.front.user.pojo.UserQueryBean;
import com.icloud.insurance.bo.UserInfoPo;
import com.icloud.insurance.dao.UserDao;
import com.icloud.insurance.model.User;
import com.icloud.insurance.model.constant.UserConstant;
import com.icloud.user.dict.UserConstants;
import com.icloud.user.dict.UserConstants.UserType;
import com.icloud.user.util.UserUtils;

@Service("userService")
public class UserService extends SqlBaseService<User> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(UserService.class);

	@Resource(name = "userDao")
	private UserDao userDao;

	@Override
	protected IHibernateBaseDao<User> getDao() {
		return userDao;
	}

	public User getUserByUserName(String userName) {
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				UserConstant.USERNAME, userName));
	}

	public User getUserByEmail(String email) {
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				UserConstant.USEREMAIL, email));
	}

	public User getUserByTelphone(String telphone) {
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				UserConstant.USERTEL, telphone));
	}

	public User getUser(String email, String password) {
		String[] paramNames = { UserConstant.USEREMAIL,
				UserConstant.USERPASSWORD };
		String[] values = { email, password };
		return ICloudUtils.getFirstElement(this.userDao.findByProperty(
				paramNames, values, null, true));
	}

	@Transactional
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

	@Transactional
	public void modifyBaseInfo(RegisterUser registerUser, User user) {
		if (ICloudUtils.isNotNull(user) && ICloudUtils.isNotNull(registerUser)) {
			logger.info("start to update: {}", registerUser);
			user.setChinaName(registerUser.getChinaName());
			user.setQq(registerUser.getQq());
			user.setUserSex(getUserSex(registerUser.getUsersex()));
			this.update(user);
			logger.info("end to update: {}", registerUser);
		}
	}

	public String getUserSex(String initStr) {
		if (!ICloudUtils.isNotNull(initStr)) {
			initStr = "0";
		}
		initStr = initStr.trim();
		int initSex = 0;
		try {
			initSex = Integer.parseInt(initStr);
		} catch (Exception e) {
			initSex = 0;
		}
		return initSex == 0 ? "男" : "女";
	}

	@Transactional
	public User addUser(String userName, String password) {
		if (ICloudUtils.isNotNull(userName) && ICloudUtils.isNotNull(password)) {
			password = StringEncoder.encrypt(password);// 加密
			User user = new User();
			user.setUserName(userName);
			user.setUserPassword(password);
			return this.save(user);
		}
		return null;
	}

	public User getUser(LoginUser loginUser) {
		if (ICloudUtils.isNotNull(loginUser)
				&& ICloudUtils.isNotNull(loginUser.getEmail())
				&& ICloudUtils.isNotNull(loginUser.getPassword())) {
			String password = StringEncoder.encrypt(loginUser.getPassword());// 加密
			return getUser(loginUser.getEmail(), password);
		}
		return null;
	}

	/**
	 * 增加一个
	 * 
	 * @param user
	 * @return
	 */
	@Transactional
	public User addUser(RegisterUser registerUser, String coming,
			User fatherUser) {
		if (ICloudUtils.isNotNull(registerUser)) {
			/**
			 * 校对用户名，电话号码，邮箱
			 */
			// && ICloudUtils.isNotNull(registerUser.getReadContract()
			if (ICloudUtils.isNotNull(registerUser.getUsername())
					&& ICloudUtils.isNotNull(registerUser.getEmail())
					&& ICloudUtils.isNotNull(registerUser.getTelphone())
					&& ICloudUtils.isNotNull(registerUser.getUsersex())
					&& ICloudUtils
							.isNotNull(registerUser.getConfirm_password())
					&& ICloudUtils.isNotNull(registerUser.getPassword())
					&& registerUser.getConfirm_password().equalsIgnoreCase(
							registerUser.getPassword())) {

				if (!ICloudUtils.isNotNull(getUserByUserName(registerUser
						.getUsername()))
						&& !ICloudUtils.isNotNull(getUserByEmail(registerUser
								.getEmail()))
						&& !ICloudUtils
								.isNotNull(getUserByTelphone(registerUser
										.getTelphone()))) {
					User user = new User();
					user.setCreateTime(new Date());
					user.setLastUpdateTime(user.getCreateTime());
					user.setUserComing(coming);
					user.setUserEmail(registerUser.getEmail());
					user.setUserName(registerUser.getUsername());
					user.setUserPassword(StringEncoder.encrypt(registerUser
							.getPassword()));// 对用户密码加密
					user.setUserSex(UserUtils.getUserSex(registerUser
							.getUsersex()));
					user.setUserTel(registerUser.getTelphone());
					user.setQq(registerUser.getQq());
					user.setOpen(UserConstants.OPEN_USER_OPER);
					user.setLevel(UserConstants.UserType.NORMAL_USER.getId());
					return this.save(user);
				}
			}
		}
		return null;
	}

	public UserInfo fillUserInfo(UserInfo info) {
		if (ICloudUtils.isNotNull(info) && info.getUserId() > 0) {
			User user = getById(info.getUserId());
			if (ICloudUtils.isNotNull(user)
					&& user.getUserName().equalsIgnoreCase(info.getUserName())) {
				info.setEmail(user.getUserEmail());
				if (user.getOpen() == 1) {
					info.setOpen(true);
				} else {
					info.setOpen(false);
				}
				info.setLevel(user.getLevel());
				return info;
			}
		}
		return null;
	}

	@Transactional
	public void updatePassword(User user, String password) {
		if (ICloudUtils.isNotNull(user) && ICloudUtils.isNotNull(password)) {
			logger.info("start to update : userName={},password={}",
					user.getUserName(), user.getUserPassword());
			user.setUserPassword(StringEncoder.encrypt(password));
			this.update(user);
			logger.info("end to update: userName={},password={}",
					user.getUserName(), user.getUserPassword());
		}

	}

	public User getUserByUserInfo(UserInfo userInfo) {
		if (ICloudUtils.isNotNull(userInfo)) {
			return getById(userInfo.getUserId());
		}
		return null;
	}

	public String getToken(User user) {
		if (ICloudUtils.isNotNull(user)
				&& ICloudUtils.isNotNull(user.getUserEmail())
				&& ICloudUtils.isNotNull(user.getUserName())
				&& ICloudUtils.isNotNull(user.getUserPassword())) {
			return StringEncoder.encrypt(user.getUserEmail() + "###"
					+ user.getUserName() + "###" + user.getUserPassword());
		}
		return null;
	}

	public boolean checkToken(User user, String token) {
		if (ICloudUtils.isNotNull(token)) {
			String token2 = getToken(user);
			if (token.equals(token2))
				return true;
		}
		return false;
	}

	public Pagination<UserInfoPo> queryUserList(int pageNo, int limit) {
		Pagination<UserInfoPo> pagination = Pagination.getInstance(pageNo,
				limit);
		long count = this.count();
		pagination.setTotalItemCount(count);
		List<User> resultList = this.findAll(pagination.getStart(),
				pagination.getPageSize());
		pagination.setData(UserInfoPo.converUser(resultList));
		pagination.build();
		return pagination;
	}

	public Pagination<UserInfoPo> queryUserListByUserId(String userId,
			int pageNo, int limit) {
		String[] params = { UserConstant.ID };
		Object[] values = { ICloudUtils.parseInt(userId, -1) };
		return queryUserList(params, values, pageNo, limit);
	}

	public Pagination<UserInfoPo> queryUserList(String[] params,
			Object[] values, int pageNo, int limit) {

		Pagination<UserInfoPo> pagination = Pagination.getInstance(pageNo,
				limit);
		long count = this.countByProperty(params, values);
		pagination.setTotalItemCount(count);
		List<User> resultList = this.findByProperty(params, values,
				UserConstant.ID, true, pagination.getStart(),
				pagination.getPageSize());
		pagination.setData(UserInfoPo.converUser(resultList));
		pagination.build();
		return pagination;
	}

	public Pagination<UserInfoPo> queryUserListByUserName(String userName,
			int pageNo, int limit) {
		String[] params = { UserConstant.USERNAME };
		Object[] values = { userName };
		return queryUserList(params, values, pageNo, limit);
	}

	public Pagination<UserInfoPo> queryUserList(UserQueryBean userBean) {
		if (ICloudUtils.isNotNull(userBean)) {
			List<String> params = new ArrayList<String>();
			List<Object> values = new ArrayList<Object>();
			if (ICloudUtils.isNotNull(userBean.getUserId())) {
				params.add(UserConstant.ID);
				values.add(ICloudUtils.parseInt(userBean.getUserId(), -1));
			}
			if (ICloudUtils.isNotNull(userBean.getUserId())) {
				params.add(UserConstant.USERNAME);
				values.add(ICloudUtils.parseInt(userBean.getUserName(), -1));
			}
			if (ICloudUtils.isNotNull(userBean.getUserType())) {
				if (-1 != ICloudUtils.parseInt(userBean.getUserType(), -1)) {
					params.add(UserConstant.LEVEL);
					values.add(ICloudUtils.parseInt(userBean.getUserType(), -1));
				}
			}
			if (params.size() > 0) {
				String[] a = new String[params.size()];
				return queryUserList(params.toArray(a), values.toArray(),
						userBean.getPageNo(), userBean.getLimit());
			}
		}
		return queryUserList(userBean.getPageNo(), userBean.getLimit());

	}
	
	@Transactional
	public void changeUserType(int uId, int oId) {
		User user = this.getById(uId);
		UserType userType = UserConstants.UserType.getById(oId);
		if (ICloudUtils.isNotNull(user)) {
			user.setLevel(userType.getId());
			this.update(user);
		}
	}
}
