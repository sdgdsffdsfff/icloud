package com.icloud.stock.marketing.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.icloud.framework.file.TextFile;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.framework.util.LogFileWriter;
import com.icloud.stock.ctx.BaseServiceImporter;

//extends BaseServiceImporter
public class ChinanetEmailImporter {
	Iterator<String> iterator;
	LogFileWriter writer;

	public ChinanetEmailImporter() {
		TextFile textFile = new TextFile(
				"/home/cuijiangning/Desktop/share/www.csdn.net.sql");
		iterator = textFile.iterator();
		writer = new LogFileWriter("/data/mywork/marketing/oschina.txt");

	}

	public String readerLine() {
		if (iterator.hasNext()) {
			return iterator.next();
		}
		return null;
	}

	public List<String> readerLine(int n) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		while (iterator.hasNext() && i < n) {
			list.add(iterator.next());
			i++;
		}
		if (ICloudUtils.isEmpty(list)) {
			return null;
		}
		return list;
	}

	public void importEmail() {
		int n = 1000;
		List<String> lines = readerLine(n);

		int count = 0;
		int sum = 0;
		while (!ICloudUtils.isEmpty(lines)) {
			/**
			 * 找到最后的一个@,然后遍历
			 */
			List<String> emails = getEmailFromText(lines);
			if (!ICloudUtils.isEmpty(emails)) {
				// System.out.println("===>" + email + "<<<<<" + line);
				// this.marketingBusiness.saveMarketingEmail(emails,
				// "chinanet");
				writer.apendln(emails);
				sum = sum + emails.size();
			} else {
				// System.out.println(line);
			}
			count += lines.size();
			if (count % 10000 == 0) {
				System.out.println("count=" + count + ";sum=" + sum);
			}

			lines = readerLine(n);

		}
		System.out.println("count=" + count + ";sum=" + sum);
		writer.close();
	}

	public List<String> getEmailFromText(List<String> text) {
		List<String> list = new ArrayList<String>();
		for (String str : text) {
			str = getEmailFromText(str);
			if (ICloudUtils.isNotNull(str)) {
				list.add(str);
			}
		}
		return list;
	}

	public String getEmailFromText(String text) {
		if (ICloudUtils.isNotNull(text)) {
			int lastIndex = text.lastIndexOf("@");
			if (lastIndex != -1) {
				int startIndex = lastIndex - 1;
				while (startIndex != -1) {
					if (text.charAt(startIndex) == ' ') {
						break;
					}
					startIndex--;
				}
				if (startIndex != -1) {
					return text.substring(startIndex + 1).replace("___csdn_1",
							"");
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		ChinanetEmailImporter os = new ChinanetEmailImporter();
		os.importEmail();
	}
}
