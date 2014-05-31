package com.icloud.front.common.freemarker;

import java.util.List;

import com.icloud.framework.core.util.TZUtil;
import com.icloud.framework.util.ICloudUtils;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class DigitalGetter implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (TZUtil.isEmpty(args)) {
			return null;
		}
		Object key = args.get(0);
		if (key == null) {
			return null;
		}
		String keyStr = (String) key;
		double digital = 0;
		try {
			digital = Double.parseDouble(keyStr);
		} catch (Exception e) {
			digital = 0;
		}
		return ICloudUtils.getDigitalString(digital, 2);
	}

}
