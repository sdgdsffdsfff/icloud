package com.icloud.front.common.filter;

import java.io.IOException;
import java.util.Random;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

import com.icloud.framework.logger.ri.RequestIdentityHolder;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.common.utils.MemberAuthUtils;
import com.icloud.front.user.utils.ICloudMemberUtils;

public class RedisSessionFilter implements Filter {
	private static final Logger logger = RequestIdentityLogger
			.getLogger(RedisSessionFilter.class);
	private static Random RDM = new Random();

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		/**
		 * 获得cookie
		 */
		Cookie cookie = getCookie(req, res);
		/**
		 * 设置reqid
		 */
		setRequestIdentityHolder(cookie, res);
		logger.debug("RedisSessionFilter start");
		chain.doFilter(request, response);
		logger.debug("RedisSessionFilter end");
		removeRequestIdentity();
	}

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

	private void removeRequestIdentity() {
		RequestIdentityHolder.remove();
	}

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

	}

	@Override
	public void destroy() {
	}

}
