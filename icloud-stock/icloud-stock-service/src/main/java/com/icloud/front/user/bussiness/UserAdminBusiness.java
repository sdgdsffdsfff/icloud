package com.icloud.front.user.bussiness;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.StringEncoder;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanBussiness;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.front.user.pojo.RegisterUser;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.stock.model.User;
import com.icloud.user.bussiness.po.ChildrenUserPo;
import com.icloud.user.bussiness.po.UserInfoPo;
import com.icloud.user.dao.IUserDao;
import com.icloud.user.dict.UserConstants;
import com.icloud.user.util.UserUtils;

@Service("userAdminBusiness")
public class UserAdminBusiness extends UserBusiness {
	@Resource(name = "juhuasuanBussiness")
	protected JuhuasuanBussiness juhuasuanBussiness;

	public User addUser(String userName, String password) {
		if (ICloudUtils.isNotNull(userName) && ICloudUtils.isNotNull(password)) {
			password = StringEncoder.encrypt(password);// 加密
		}
		return null;
	}

	public User getUser(int userId) {
		return this.userService.getById(userId);
	}

	public User getUser(LoginUser loginUser) {
		if (ICloudUtils.isNotNull(loginUser)
				&& ICloudUtils.isNotNull(loginUser.getEmail())
				&& ICloudUtils.isNotNull(loginUser.getPassword())) {
			String password = StringEncoder.encrypt(loginUser.getPassword());// 加密
			return this.userService.getUser(loginUser.getEmail(), password);
		}
		return null;
	}

	public User getUserByUserName(String userName) {
		if (ICloudUtils.isNotNull(userName)) {
			return this.userService.getUserByUserName(userName);
		}
		return null;
	}

	public User getUserByEmail(String email) {
		if (ICloudUtils.isNotNull(email)) {
			return this.userService.getUserByEmail(email);
		}
		return null;
	}

	public User getUserByTelphone(String telphone) {
		if (ICloudUtils.isNotNull(telphone)) {
			return this.userService.getUserByTelphone(telphone);
		}
		return null;
	}

	/**
	 * 增加一个
	 * 
	 * @param user
	 * @return
	 */
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
					if (ICloudUtils.isNotNull(fatherUser)) {
						user.setFatherId(fatherUser.getId());
						user.setOpen(1);
						user.setLevel(fatherUser.getLevel() + 1);
						user.setPromotion(1);
						user.setFatherName(fatherUser.getUserName());
					}
					return this.userService.save(user);
				}
			}
		}
		return null;
	}

	public UserInfo fillUserInfo(UserInfo info) {
		if (ICloudUtils.isNotNull(info) && info.getUserId() > 0) {
			User user = userService.getById(info.getUserId());
			if (ICloudUtils.isNotNull(user)
					&& user.getUserName().equalsIgnoreCase(info.getUserName())) {
				info.setEmail(user.getUserEmail());
				if (user.getLevel() == UserConstants.USER_LEVEL_LIMIT
						|| user.getPromotion() == 0) {
					info.setAddUser(false);
				} else {
					info.setAddUser(true);
				}
				if (user.getOpen() == 1) {
					info.setOpen(true);
				} else {
					info.setOpen(false);
				}
				return info;
			}
		}
		return null;
	}

	public void resetPassword(User user) {
		this.userService.resetPassword(user);
	}

	public void updatePassword(User user, String password) {
		this.userService.updatePassword(user, StringEncoder.encrypt(password));
	}

	public User getUserByUserInfo(UserInfo userInfo) {
		if (ICloudUtils.isNotNull(userInfo)) {
			return this.userService.getById(userInfo.getUserId());
		}
		return null;
	}

	public void modifyBaseInfo(RegisterUser registerUser, User user) {
		this.userService.modifyBaseInfo(registerUser, user);
	}

	@Transactional
	public List<User> getChildrenUser(int fatherId) {
		return this.userService.findByProperies(IUserDao.FATHERID, fatherId);
	}

	public ChildrenUserPo getChildrenUserPo(User user) {
		ChildrenUserPo userPo = new ChildrenUserPo(this, user, 20);
		return userPo;
	}

	public Pagination<UserInfoPo> getUsersByUser(User user, int pageNo,
			int limit) {
		if (ICloudUtils.isNotNull(user)) {
			if (user.getLevel() == UserConstants.SUPER_USER) {
				return getAllUsers(pageNo, limit);
			} else {
				return getChidrenUser(user.getId(), pageNo, limit);
			}
		}
		return null;

	}

	public Pagination<UserInfoPo> getChidrenUser(int fatherId, int pageNo,
			int limit) {
		Pagination<UserInfoPo> pagination = Pagination.getInstance(pageNo,
				limit);

		List<User> users = getChildrenUser(fatherId);
		if (!ICloudUtils.isEmpty(users)) {
			Deque<User> deque = new ArrayDeque<User>();
			deque.addAll(users);
			Queue<User> queue = Collections.asLifoQueue(deque);
			User peekUser = queue.remove();
			while (ICloudUtils.isNotNull(peekUser)) {
				List tmpUsers = getChildrenUser(peekUser.getId());
				if (!ICloudUtils.isEmpty(tmpUsers)) {
					users.addAll(tmpUsers);
					queue.addAll(tmpUsers);
				}
				if (queue.size() > 0) {
					peekUser = queue.remove();
				} else {
					peekUser = null;
				}
			}
		}
		int count = users.size();
		long count2 = count;
		pagination.setTotalItemCount(count2);
		if (count < pagination.getStart()) {
			int end = (pagination.getStart() + limit) > count ? count
					: (pagination.getStart() + limit);
			users = users.subList(pagination.getStart(), end);
		}
		pagination.setData(UserInfoPo
				.converUser(users, this.juhuasuanBussiness));
		pagination.build();
		return pagination;
	}

	public Pagination<UserInfoPo> getAllUsers(int pageNo, int limit) {
		Pagination<UserInfoPo> pagination = Pagination.getInstance(pageNo,
				limit);
		long count = this.userService.count();
		pagination.setTotalItemCount(count);
		List<User> resultList = this.userService.findAll(pagination.getStart(),
				pagination.getPageSize());
		pagination.setData(UserInfoPo.converUser(resultList,
				this.juhuasuanBussiness));
		pagination.build();
		return pagination;
	}

	/**
	 * @param user
	 * @param tmpUser
	 * @return List<User>
	 * @throws
	 */
	public List<User> findParentsUsers(User user, User childUser) {
		List<User> list = new LinkedList<User>();
		if (ICloudUtils.isNotNull(user) && ICloudUtils.isNotNull(childUser)) {
			// list.add(childUser);
			// User tmp = childUser;
			while (user.getId().intValue() != childUser.getId().intValue()
					&& ICloudUtils.isNotNull(childUser.getFatherId())
					&& childUser.getFatherId() != 0) {
				User father = this.userService.getById(childUser.getFatherId());
				if (ICloudUtils.isNotNull(father)) {
					list.add(0, father);
					childUser = father;
				} else {
					break;
				}
			}
			return list;
		}
		return null;
	}

	public boolean auth(User user, User operationUser) {
		if (ICloudUtils.isNotNull(user) && ICloudUtils.isNotNull(operationUser)) {
			if (operationUser.getId() == 1)
				return false;
			if (user.getId() == operationUser.getId())
				return true;
			if (user.getId() == 1)
				return true;

			List<User> list = this.findParentsUsers(user, operationUser);
			for (User tmp : list) {
				if (tmp.getId().intValue() == user.getId().intValue())
					return true;
			}
		}
		return false;
	}

	public void update(User user) {
		this.userService.update(user);
	}
}
