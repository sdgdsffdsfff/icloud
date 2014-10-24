package com.icloud.framework.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.dao.hibernate.HiberanateEnum.OperationEnum;
import com.icloud.framework.util.ICloudUtils;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月24日 下午5:00:12
 */
public class HiberanateParamters {
	List<String> paramList = new ArrayList<String>();
	List<OperationEnum> operationList = new ArrayList<OperationEnum>();
	List<Object> valueList = new ArrayList<Object>();

	public HiberanateParamters() {

	}

	public void addOperationsValue(String param, OperationEnum enumValue,
			Object value) {
		if (ICloudUtils.isNotNull(value)) {
			paramList.add(param);
			operationList.add(enumValue);
			valueList.add(value);
		}
	}

	public String[] getParams() {
		String[] params = new String[paramList.size()];
		paramList.toArray(params);
		return params;
	}

	public OperationEnum[] getOperations() {
		OperationEnum[] operations = new OperationEnum[operationList.size()];
		operations = operationList.toArray(operations);
		return operations;
	}

	public Object[] getValues() {
		Object[] values = valueList.toArray();
		return values;
	}
}
