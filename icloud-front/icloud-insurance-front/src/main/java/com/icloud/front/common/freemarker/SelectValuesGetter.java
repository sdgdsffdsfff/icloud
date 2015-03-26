package com.icloud.front.common.freemarker;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.vo.KeyValue;
import com.icloud.user.dict.UserConstants;
import com.icloud.user.dict.UserConstants.UserType;

import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

public class SelectValuesGetter implements TemplateMethodModel {

	@Override
	public Object exec(List args) throws TemplateModelException {
		if (ICloudUtils.isEmpty(args)) {
			return null;
		}
		Object key = args.get(0);
		String keyStr = (String) key;
		List<KeyValue> list = new ArrayList<KeyValue>();
		if (keyStr.equalsIgnoreCase("1")) {
			UserType[] values = UserConstants.UserType.values();
			for (UserType value : values) {
				KeyValue<String, String> kv = new KeyValue<String, String>();
				kv.setKey(value.getId() + "");
				kv.setValue(value.getUserType());
				list.add(kv);
			}
		} else if (keyStr.equalsIgnoreCase("2")) {

		} else if (keyStr.equalsIgnoreCase("3")) {

		} else if (keyStr.equalsIgnoreCase("4")) {

		}
		return list;
	}

}
