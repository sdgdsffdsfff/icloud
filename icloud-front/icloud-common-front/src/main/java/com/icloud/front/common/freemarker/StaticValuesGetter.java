package com.icloud.front.common.freemarker;

import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class StaticValuesGetter implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (ICloudUtils.isEmpty(args)) {
			return null;
		}
		String type = (String) args.get(0);
		String key = (String) args.get(1);
		if ("1".equalsIgnoreCase(type)) {
			return JuhuasuanConstants.JUHUASUANSTATUS.value(key).getName();
		} else if ("2".equalsIgnoreCase(type)) {
			return JuhuasuanConstants.JUHUASUANTYPE.value(key).getName();
		} else if ("3".equalsIgnoreCase(type)) {
			return JuhuasuanConstants.JUHUASUANSOLIDIFY.value(key).getName();
		}
		return null;	
	}

}
