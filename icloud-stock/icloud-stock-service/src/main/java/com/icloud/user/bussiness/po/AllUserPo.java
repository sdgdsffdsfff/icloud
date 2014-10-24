package com.icloud.user.bussiness.po;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.User;
import com.icloud.user.service.IUserService;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午3:07:49
 */
public class AllUserPo {
	protected IUserService userService;
	private int start = 0;
	private int limit = 20;
	private long count;
	private List<User> users;

	public AllUserPo(IUserService userService, int limit) {
		this.userService = userService;
		this.limit = limit;
		this.start = 0;
		count = this.userService.count();
		users = new ArrayList<User>();
	}

	public User next() {
		if (users.size() > 0) {
			return users.remove(0);
		}
		if (start < count) {
			List<User> partUserList = this.userService.findAll(start, limit);
			if (ICloudUtils.isNotNull(partUserList)) {
				users.addAll(partUserList);
				return users.remove(0);
			}
			start = start + limit;
		}
		return null;
	}
}
