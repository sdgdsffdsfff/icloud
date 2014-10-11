package com.icloud.front.common.freemarker;

import java.util.List;

import com.icloud.framework.core.util.TZUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class StockPageUrlGetter implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (TZUtil.isEmpty(args) && args.size() != 4) {
			return null;
		}
		String pageId = (String) args.get(0);
		String pageNo = (String) args.get(3);
		if ("2".equalsIgnoreCase(pageId)) {
			String stockCode = (String) args.get(1);
			return "href=\"stockBaseHistory?stockCode=" + stockCode
					+ "&pageNo=" + pageNo + "\"";
		} else if ("1".equalsIgnoreCase(pageId)) {
			String cateId = (String) args.get(1);
			return "onclick=\"stockListloading(" + cateId + "," + pageNo
					+ ")\" href=\"#\"";
		} else if ("3".equalsIgnoreCase(pageId)) {
			String cateId = (String) args.get(1);
			return "href=\"openStockList?cateId=" + cateId + "&pageNo="
					+ pageNo + "\"";
		} else if ("4".equalsIgnoreCase(pageId)) {
			return "onclick=\"juhuasuanSeachLoading(" + pageNo
					+ ")\" href=\"#\"";
		} else if ("5".equalsIgnoreCase(pageId)) {
			return "href=\"trafficCurrentDay?pageNo=" + pageNo + "\"";
		} else if ("6".equalsIgnoreCase(pageId)) {
			return "href=\"traffic30Day?pageNo=" + pageNo + "\"";
		}
		return "/stock/index";
	}
}
