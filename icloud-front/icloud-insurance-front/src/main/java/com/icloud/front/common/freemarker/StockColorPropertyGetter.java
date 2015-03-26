package com.icloud.front.common.freemarker;

import java.util.List;

import com.icloud.framework.core.util.TZUtil;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 获得配置的颜色
 *
 * @author jiangning.cui
 */
public class StockColorPropertyGetter implements TemplateMethodModel {

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List args) throws TemplateModelException {
		if (TZUtil.isEmpty(args)) {
			return null;
		}
		Object key = args.get(0);
		if (key == null) {
			return null;
		}
		double ch = Double.parseDouble((String) key);
		if (ch > 0) {
			return "stock-color";
		}
		if (ch == 0.0) {
			return "nocolor";
		}
		return "stock-down";
	}

}
