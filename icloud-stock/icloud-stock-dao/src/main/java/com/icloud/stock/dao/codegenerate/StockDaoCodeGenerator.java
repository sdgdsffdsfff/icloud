package com.icloud.stock.dao.codegenerate;

import java.io.UnsupportedEncodingException;

import com.icloud.framework.dao.autogenerate.DaoCodeGenerator;

public class StockDaoCodeGenerator {

	public static void main(String[] args) throws UnsupportedEncodingException {
		DaoCodeGenerator daoCodeGenerator = new DaoCodeGenerator();
		String srcPack = "com.icloud.stock.model";
		String destPack = "com.icloud.stock.haha.dao";
		String baseDaoPath = "com.icloud.dao.StockBaseDao";
		daoCodeGenerator.generateCode(srcPack, destPack, baseDaoPath);
	}
}
