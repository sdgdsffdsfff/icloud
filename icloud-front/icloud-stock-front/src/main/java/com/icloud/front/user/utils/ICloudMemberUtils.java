package com.icloud.front.user.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.icloud.framework.config.PropertiesUtil;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.MemberAuthUtils;
import com.icloud.front.user.pojo.LoginUser;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.stock.model.User;

public class ICloudMemberUtils {
	private static final String GUSTER_PREFIX = "guster";
	private static final String USER_PREFIX = "user";
	private static final String PER_USER_PREFIX = "preuser";
	private static final String TOKEN_SEP = "_";
	private static final int SESSION_TIMEOUT = PropertiesUtil
			.getPropertyForInt("properties/icloud-constants.properties",
					"session-timeout");

	public static Cookie addSession(HttpServletRequest request,
			HttpServletResponse response, LoginUser loginUser, User user) {
		if (ICloudUtils.isNotNull(request) && ICloudUtils.isNotNull(response)) {
			String token = generateToken(loginUser, user, request);
			if (ICloudUtils.isNotNull(loginUser)
					&& "on".equalsIgnoreCase(loginUser.getAutoLogin())) {
				return MemberAuthUtils.addRememberMeCookie(token, 2147483647,
						true, request, response);
			} else {
				return MemberAuthUtils.addRememberMeCookie(token,
						SESSION_TIMEOUT, true, request, response);
			}
		}
		return null;
	}

	/**
	 * 生成一个token 1. 如果都为空的时候，格式：guster_ 2. 如果临时登录的时候，格式:user_ 3.
	 * 如果自动登录的时候，格式：per_user_
	 * 
	 * 名字,IP,登录时间
	 * 
	 * @param loginUser
	 * @param user
	 * @return
	 */
	public static String generateToken(LoginUser loginUser, User user,
			HttpServletRequest request) {
		if (ICloudUtils.isNotNull(loginUser) && ICloudUtils.isNotNull(user)) {
			if ("on".equalsIgnoreCase(loginUser.getAutoLogin())) {
				return PER_USER_PREFIX + TOKEN_SEP + user.getId() + TOKEN_SEP
						+ user.getUserName();
			} else {
				return USER_PREFIX + TOKEN_SEP + user.getId() + TOKEN_SEP
						+ user.getUserName();
			}
		} else {
			return GUSTER_PREFIX + TOKEN_SEP
					+ MemberAuthUtils.getIpAddr(request) + TOKEN_SEP
					+ System.currentTimeMillis();
		}
	}

	/**
	 * 获得用户信息
	 */
	public static UserInfo getUserInfoFromToken(String token) {
		if (ICloudUtils.isNotNull(token) && !token.startsWith(GUSTER_PREFIX)) {
			String[] tokens = token.split(TOKEN_SEP);
			if (ICloudUtils.isNotNull(tokens) && tokens.length == 3) {
				int userId = Integer.parseInt(tokens[1]);
				String userName = tokens[2];
				UserInfo info = new UserInfo();
				info.setUserId(userId);
				info.setUserName(userName);
				return info;
			}
		}
		return null;
	}

	public static void removeSession(HttpServletRequest request,
			HttpServletResponse response) {
		String token = generateToken(null, null, request);
		MemberAuthUtils.addRememberMeCookie(token, SESSION_TIMEOUT, true,
				request, response);
	}
}
