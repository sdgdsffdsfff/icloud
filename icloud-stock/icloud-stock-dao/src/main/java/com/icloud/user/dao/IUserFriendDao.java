package com.icloud.user.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.UserFriend;

public interface IUserFriendDao extends StockBaseDao<UserFriend> {
	public static final String USERID = "userId";
	public static final String FRIENDID = "friendId";
	public static final String STATUS = "status";
	public static final String CREATETIME = "createTime";
	public static final String UPDATETIME = "updateTime";

	public static final String DESCRIPTION = "description";
	public static final String USERNAMEE = "userName";
	public static final String USERRELATION = "userRelation";

}
