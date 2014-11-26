package com.icloud.stock.marketing.email;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.core.util.FileUtil;
import com.icloud.framework.util.ICloudUtils;

public class EmailFileOperation {
	/**
	 * 读1个东西出来，并放到另外一个地方, 并且删除这部分内容
	 */
	public EmailFileOperation() {

	}

	public synchronized List<String> readerLines(String srcPath,
			String destPath, int number) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream(srcPath)));
		// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
		FileWriter destWriter = new FileWriter(destPath, true);
		String tmpPath = srcPath + ".tmp";
		FileWriter tmpWriter = new FileWriter(tmpPath, false);

		String line = br.readLine();
		int count = 0;
		List<String> list = new ArrayList<String>();
		while (ICloudUtils.isNotNull(line)) {
			if (count >= number) {
				tmpWriter.write(line + "\n");
			} else {
				count++;
				list.add(line);
				destWriter.write(line + "\n");
			}
			line = br.readLine();
		}

		destWriter.close();
		br.close();
		tmpWriter.close();

		/**
		 * 删除os
		 */
		FileUtil.rename(tmpPath, srcPath);
		return list;
	}

	public static void main(String[] args) throws IOException {
		String srcPath = "/data/mywork/task/emailtask1/os.txt";
		String destPath = "/data/mywork/task/emailtask1/work.txt";
		EmailFileOperation operation = new EmailFileOperation();
		operation.readerLines(srcPath, destPath, 100);
	}
}
