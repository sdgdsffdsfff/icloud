package com.icloud.user.bussiness.po;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.icloud.framework.core.wrapper.Pagination;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.bussiness.UserAdminBusiness;
import com.icloud.stock.model.User;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午3:07:49
 */
public class ChildrenUserPo {
	protected UserAdminBusiness userAdminBusiness;
	private int pageNo = 0;
	private int limit = 20;
	private int pageCount;
	private List<UserInfoPo> users;
	private User user;

	public ChildrenUserPo(UserAdminBusiness userAdminBusiness, User user,
			int limit) {
		this.userAdminBusiness = userAdminBusiness;
		this.limit = limit;
		users = new ArrayList<UserInfoPo>();
		this.user = user;
		Pagination<UserInfoPo> pagination = this.userAdminBusiness
				.getUsersByUser(user, pageNo, limit);
		pageCount = pagination.getTotalPageCount();
		if (!ICloudUtils.isEmpty(pagination.getData())) {
			users = (List<UserInfoPo>) pagination.getData();
		}
		pageNo = pageNo++;
	}

	public UserInfoPo next() {
		if (users.size() > 0) {
			return users.remove(0);
		}
		if (pageNo < pageCount) {
			Pagination<UserInfoPo> pagination = this.userAdminBusiness
					.getUsersByUser(user, pageNo, limit);
			pageNo = pageNo++;
			if (ICloudUtils.isNotNull(pagination)
					&& !ICloudUtils.isEmpty(pagination.getData())) {
				users.addAll(pagination.getData());
				return users.remove(0);
			}
		}
		return null;
	}

	@Transactional
	public List<UserInfoPo> getAllUser() {
		List<UserInfoPo> users = new ArrayList<UserInfoPo>();
		UserInfoPo user = next();
		while (ICloudUtils.isNotNull(user)) {
			users.add(user);
			user = next();
		}
		return users;
	}
}
