package com.icloud.front.common.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.front.common.dict.MemberConstants;

public class MemberAuthUtils {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(MemberAuthUtils.class);
	private static final char DELIMITER = (char) 248; // 'ø'

	public static Cookie addRememberMeCookie(String token, int maxAge,
			boolean useSecure, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(getRememberMeCookieName(), token);
		cookie.setMaxAge(maxAge);
		// cookie.setPath("/icloud-stock-front");
		cookie.setPath("/");
		// cookie.setDomain(secLevelDomain(request.getRequestURL().toString()));
		// cookie.setDomain("localhost");
		// cookie.setSecure(useSecure);
		response.addCookie(cookie);
		logger.info("cookieName={}, token={}", getRememberMeCookieName(), token);
		;
		return cookie;
	}

	public static void removeRememberMeCookie(boolean useSecure,
			HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie(getRememberMeCookieName(), "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		// cookie.setDomain(secLevelDomain(request.getRequestURL().toString()));
		// cookie.setSecure(useSecure);
		response.addCookie(cookie);
	}

	public static String getRememberMeCookieName() {
		return MemberConstants.COOKIE_KEY_TOKEN;
	}

	public static String secLevelDomain(String url) {
		String host = url.replaceAll("https?:\\/{2,}", "").replaceAll(
				"(:\\d*)?(\\/.*)?", "");
		if (host.indexOf('.') < 0 || host.indexOf('.') == host.lastIndexOf('.')) {
			return host;
		} else {
			int idx = host.lastIndexOf('.');
			idx = host.lastIndexOf('.', --idx);
			return '.' + host.substring(++idx);
		}
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getSessionKey(HttpSession session) {
		return session == null ? null : "Session" + session.getId();
	}

	public static boolean isAjaxRequest(HttpServletRequest request) {
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

}
