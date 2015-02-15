package com.icloud.insurance.db.generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import com.icloud.framework.core.util.ClassUtil;
import com.icloud.framework.util.FileUtils;

public class JavaCodeGenerator {

	public static void generateDTO() {
		List<String> warnings = new ArrayList<String>();
		ConfigurationParser cp = new ConfigurationParser(warnings);

		boolean overwrite = true;
		// staticTableConfig.xml,dynamicTableConfig.xml
		File configFile = new File("config/generatorConfig.xml");
		try {
			Configuration config = cp.parseConfiguration(configFile);
			DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
					callback, warnings);
			myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void writeFile(File file, String content, String fileEncoding)
			throws IOException {
		FileOutputStream fos = new FileOutputStream(file, false);
		OutputStreamWriter osw;
		if (fileEncoding == null) {
			osw = new OutputStreamWriter(fos);
		} else {
			osw = new OutputStreamWriter(fos, fileEncoding);
		}

		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(content);
		bw.close();
	}

	public static void generateConstant(String srcPackageName,
			String destPackageName) throws ClassNotFoundException, IOException {
		String readContent = FileUtils.readContent("config/constant.txt");
		List<Class<?>> classes = ClassUtil.getClasses(srcPackageName, false);
		for (Class clas : classes) {
			String content = readContent;
			String shortClassName = ClassUtil.getShortClassName(clas.getName());
			String javaClassName = shortClassName + "Constant";

			content = content.replace("$packageName", destPackageName);
			content = content.replace("$className", javaClassName);
			String classContent = getClassContent(clas);
			content = content.replace("$content", classContent);
			String destPath = "src/main/java/"
					+ destPackageName.replace(".", "/");
			File file = new File(destPath + "/" + javaClassName + ".java");
			writeFile(file, content, "UTF-8");
		}
	}

	public static String getClassContent(Class cls) {
		StringBuffer sb = new StringBuffer();
		Field[] fields = ClassUtil.getDeclaredFields(cls);
		for (Field field : fields) {
			sb.append("\tpublic static final String "
					+ field.getName().toUpperCase() + " = \"" + field.getName()
					+ "\";" + "\n");
		}
		return sb.toString();
	}

	public static void main(String[] args) throws ClassNotFoundException,
			IOException {
		// generateDTO();
		// generateConstant();
	}
}
