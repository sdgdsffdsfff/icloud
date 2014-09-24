package com.icloud.front.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.user.pojo.UserInfo;
import com.icloud.front.user.utils.ICloudMemberUtils;
import com.icloud.user.business.manager.UserAdminBusiness;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月24日 上午11:19:38
 */
public class LogOnInterCeptor implements HandlerInterceptor {
	private String redirectUrl = null;

	@Resource(name = "userAdminBusiness")
	protected UserAdminBusiness userAdminBusiness;

	public void setRedirectUrl(String redirectUrl) {

		this.redirectUrl = redirectUrl;
	}

	/**
	 * 判断是否登陆，如果没有登陆，跳转到登陆页面
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		Cookie cookie = ICloudMemberUtils.getCookie(request, response);
		UserInfo info = ICloudMemberUtils.getUserInfoFromToken(cookie
				.getValue());
		info = userAdminBusiness.fillUserInfo(info);
		if (!ICloudUtils.isNotNull(info)) { // 如果没有登陆，返回登陆页面
			response.sendRedirect(request.getContextPath() + "/" + redirectUrl);
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler, ModelAndView mv)
			throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}