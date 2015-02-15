package com.icloud.insurance.db.generate;

import java.io.IOException;

public class JavaGenerator {
	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		JavaCodeGenerator.generateConstant("com.icloud.insurance.model",
				"com.icloud.insurance.model.constant");
	}
}
