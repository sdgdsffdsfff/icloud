package com.icloud.front.common.freemarker;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.vo.KeyValue;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants.JUHUASUANSOLIDIFY;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants.JUHUASUANSTATUS;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants.JUHUASUANTYPE;
import com.icloud.front.juhuasuan.constant.JuhuasuanConstants.JUHUASUANURLTYPE;

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
			JUHUASUANSTATUS[] values = JuhuasuanConstants.JUHUASUANSTATUS
					.values();
			for (JUHUASUANSTATUS value : values) {
				KeyValue<String, String> kv = new KeyValue<String, String>();
				kv.setKey(value.getId());
				kv.setValue(value.getName());
				list.add(kv);
			}

		} else if (keyStr.equalsIgnoreCase("2")) {
			JUHUASUANTYPE[] values = JuhuasuanConstants.JUHUASUANTYPE.values();
			for (JUHUASUANTYPE value : values) {
				KeyValue<String, String> kv = new KeyValue<String, String>();
				kv.setKey(value.getId());
				kv.setValue(value.getName());
				list.add(kv);
			}

		} else if (keyStr.equalsIgnoreCase("3")) {
			JUHUASUANSOLIDIFY[] values = JuhuasuanConstants.JUHUASUANSOLIDIFY
					.values();
			for (JUHUASUANSOLIDIFY value : values) {
				KeyValue<String, String> kv = new KeyValue<String, String>();
				kv.setKey(value.getId());
				kv.setValue(value.getName());
				list.add(kv);
			}

		} else if (keyStr.equalsIgnoreCase("4")) {
			JUHUASUANURLTYPE[] values = JuhuasuanConstants.JUHUASUANURLTYPE
					.values();
			for (JUHUASUANURLTYPE value : values) {
				KeyValue<String, String> kv = new KeyValue<String, String>();
				kv.setKey(value.getId() + "");
				kv.setValue(value.getName());
				list.add(kv);
			}

		}
		return list;
	}

}
