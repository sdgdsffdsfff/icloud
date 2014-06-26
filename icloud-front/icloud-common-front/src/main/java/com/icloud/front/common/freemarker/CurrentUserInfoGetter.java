package com.icloud.front.common.freemarker;

import java.util.List;

import com.icloud.front.common.utils.ICloudUserContextHolder;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class CurrentUserInfoGetter implements TemplateMethodModel {

	@Override
	public Object exec(List arguments) throws TemplateModelException {
		return ICloudUserContextHolder.get();
	}

}
