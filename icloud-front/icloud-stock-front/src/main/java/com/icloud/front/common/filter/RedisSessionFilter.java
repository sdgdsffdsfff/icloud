package com.icloud.front.common.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.icloud.framework.logger.ri.RequestIdentityHolder;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.config.FilterNotMappingConfig;
import com.icloud.front.common.utils.ICloudUserContextHolder;
import com.icloud.front.common.utils.MemberAuthUtils;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.front.user.utils.ICloudMemberUtils;
import com.icloud.user.business.manager.UserAdminBusiness;

public class RedisSessionFilter implements Filter {
	private static final Logger logger = RequestIdentityLogger
			.getLogger(RedisSessionFilter.class);
	private static Random RDM = new Random();

	protected UserAdminBusiness userAdminBusiness;

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		boolean isFilter = isFileter(req, res);
		if (isFilter) {
			/**
			 * 获得cookie
			 */
			Cookie cookie = getCookie(req, res);
			/**
			 * 设置reqid
			 */
			setRequestIdentityHolder(cookie, res);
			/**
			 * 设置用户信息
			 */
			setUserInfoHolder(cookie);
		}
		// logger.debug("RedisSessionFilter start");
		chain.doFilter(request, response);
		// logger.debug("RedisSessionFilter end");

		if (isFilter) {
			/**
			 * 删除用户信息
			 */
			removeUserInfoHolder();
			/**
			 * 删除 reqid
			 */
			removeRequestIdentity();
		}
	}

	private boolean isFileter(HttpServletRequest req, HttpServletResponse res) {
		// logger.info(req.getContextPath() + "|"
		// + FilterNotMappingConfig.isInFilterUrl(req.getContextPath()));
		if (FilterNotMappingConfig.isInFilterUrl(req.getContextPath())) {
			return false;
		}
		return true;
	}

	/**
	 * 设置用户信息
	 */
	private void setUserInfoHolder(Cookie cookie) {
		if (ICloudUtils.isNotNull(cookie)) {
			UserInfo info = ICloudMemberUtils.getUserInfoFromToken(cookie
					.getValue());
			info = userAdminBusiness.fillUserInfo(info);
			if (ICloudUtils.isNotNull(info)) {
				ICloudUserContextHolder.set(info);
			}
		}
	}

	/**
	 * 删除用户信息
	 */
	private void removeUserInfoHolder() {
		ICloudUserContextHolder.remove();
	}

	/**
	 * 设置reqid
	 */
	private void setRequestIdentityHolder(Cookie cookie, HttpServletResponse res) {
		String reqId = null;
		if (ICloudUtils.isNotNull(cookie)) {
			reqId = cookie.getValue() + "_"
					+ String.format("%08x", RDM.nextInt());
		} else {
			reqId = String.format("%08x", RDM.nextInt());
		}
		RequestIdentityHolder.set(reqId);
		res.setHeader("ICloud-Request-Identity", reqId);
	}

	/**
	 * 删除 reqid
	 */
	private void removeRequestIdentity() {
		RequestIdentityHolder.remove();
	}

	/**
	 * 获得cookie
	 */
	private Cookie getCookie(HttpServletRequest req, HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		if (ICloudUtils.isNotNull(cookies)) {
			for (Cookie ck : cookies) {
				if (MemberAuthUtils.getRememberMeCookieName().equals(
						ck.getName())) {
					// logger.info("cookie = {}, cookieValue={}", ck.getName(),
					// ck.getValue());
					return ck;
				}
			}
		}
		/**
		 * 如果没有cookie,生成一个
		 */
		return ICloudMemberUtils.addSession(req, res, null, null);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		ServletContext context = filterConfig.getServletContext();
		ApplicationContext ctx = WebApplicationContextUtils
				.getWebApplicationContext(context);
		userAdminBusiness = (UserAdminBusiness) ctx
				.getBean("userAdminBusiness");
	}

	@Override
	public void destroy() {
	}

}
