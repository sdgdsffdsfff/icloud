package com.icloud.front.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.servlet.ModelAndView;

import com.icloud.framework.core.wrapper.PageView;
import com.icloud.framework.core.wrapper.Pagination;

public class ModelAndViewUtils {

	public static <T> void addPageView(ModelAndView modelAndView,
			Pagination<T> pagination) {
		modelAndView.addObject("pagination", pagination);
		PageView pageView = PageView.convertPage(pagination);
		modelAndView.addObject("pageView", pageView);
	}

}
