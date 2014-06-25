package com.icloud.front.user.utils;

import javax.servlet.http.HttpSession;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.stock.model.User;

public class ICloudMemberUtils {
	public static void addSession(HttpSession session, LoginUser loginUser,
			User user) {
		if (ICloudUtils.isNotNull(session) && ICloudUtils.isNotNull(loginUser)
				&& ICloudUtils.isNotNull(user)) {
			
			
		}
	}
}
