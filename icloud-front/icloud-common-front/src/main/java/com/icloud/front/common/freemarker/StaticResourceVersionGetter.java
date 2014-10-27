package com.icloud.front.common.freemarker;

import java.util.List;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class StaticResourceVersionGetter implements TemplateMethodModel {

	// / 1000 / 60 / 60 / 24
	private static final String VERSION = Long.toHexString(System
			.currentTimeMillis());

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		return VERSION;
	}

}
