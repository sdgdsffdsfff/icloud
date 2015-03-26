package com.icloud.front.common.freemarker;

import java.util.List;

import com.icloud.framework.core.util.TZUtil;
import com.icloud.framework.util.ICloudUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月11日 下午3:59:08
 */
public class StringUtilsGetter implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (TZUtil.isEmpty(args) && args.size() != 3) {
			return null;
		}
		String value = (String) args.get(0);
		String limitStr = (String) args.get(1);
		int limit = ICloudUtils.parseInt(limitStr, -1);
		String pre = (String) args.get(2);
		if (limit > 0) {
			if ("1".equalsIgnoreCase(pre)) {
				if (value.length() > limit) {
					return value.substring(value.length() - limit);
				}
			} else {
				if (value.length() > limit) {
					return value.substring(0, limit);
				}
			}
		}
		return value;

	}

}
