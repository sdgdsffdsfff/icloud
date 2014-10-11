package com.icloud.framework.dao.hibernate;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月11日 下午2:16:16
 */
public class HiberanateEnum {
	public enum OperationEnum {
		EQUALS("="), LESS("<"), BIGGER(">"), LESS_ADN_EQUALS("<="), BIGGER_ADN_EQUALS(
				">=");
		private String opeationValue;

		private OperationEnum(String opeationValue) {
			this.opeationValue = opeationValue;
		}

		public String getOpeationValue() {
			return this.opeationValue;
		}
	}
}
