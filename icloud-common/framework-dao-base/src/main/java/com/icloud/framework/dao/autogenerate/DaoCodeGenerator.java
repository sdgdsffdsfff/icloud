package com.icloud.framework.dao.autogenerate;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.Set;

import org.slf4j.Logger;

import com.icloud.framework.util.ClassUtils;
import com.icloud.framework.util.FileUtils;

public class DaoCodeGenerator {
	private final String bodyStr;

	public DaoCodeGenerator() throws UnsupportedEncodingException {
		bodyStr = FileUtils.readContentFromReSource("properties/dao.template");
	}

	public void generateCode(String srcPack, String destPack, String baseDaoPath) {
		String destPackPath = generateFolder(destPack);
		Set<Class<?>> classes = ClassUtils.getClasses(srcPack);
		String baseDaoName = getClassName(baseDaoPath);
		for (Class cz : classes) {
			generateSingleClassCode(cz, destPackPath, destPack, baseDaoPath,
					baseDaoName);
		}
	}

	private String getClassName(String baseDaoPath) {
		return baseDaoPath.substring(baseDaoPath.lastIndexOf(".") + 1);
	}

	private String generateFolder(String pack) {
		return generateFolder(pack, "src/main/java");
	}

	private String generateFolder(String pack, String prefix) {
		pack = pack.replace(".", File.separator);
		String root = System.getProperty("user.dir") + File.separator + prefix
				+ File.separator + pack;
		File myPackageFiles = new File(root);
		if (!myPackageFiles.exists()) {
			System.out.println("包路径创建情况：" + myPackageFiles.mkdirs());
		} else {
			System.out.println("存在目录：" + myPackageFiles.getAbsolutePath());
		}
		return root;
	}

	private void generateSingleClassCode(Class cz, String destPack,
			String destPackName, String baseDaoPath, String baseDaoName) {
		String destStr = bodyStr;
		String modelPath = cz.getName();
		String modelDaoName = "I" + getClassName(cz.getName()) + "Dao";
		String modelName = getClassName(cz.getName());

		destStr = destStr.replace("$destPack", destPackName);
		destStr = destStr.replace("$baseDaoPath", baseDaoPath);
		destStr = destStr.replace("$modelPath", modelPath);
		destStr = destStr.replace("$modelDaoName", modelDaoName);
		destStr = destStr.replace("$baseDaoName", baseDaoName);
		destStr = destStr.replace("$modelName", modelName);

		StringBuffer allContext = new StringBuffer();

		Field[] fields = cz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			allContext.append("\tpublic static final String "
					+ field.getName().toUpperCase() + " = \"" + field.getName()
					+ "\";");
			if (i < fields.length - 1) {
				allContext.append("\n");
			}
		}
		destStr = destStr.replace("$allContext", allContext.toString());

		String destPath = destPack + File.separator + modelDaoName + ".java";
		FileUtils.writep(destStr, new File(destPath), false);
		System.out.println("write " + destPath);
	}
}
