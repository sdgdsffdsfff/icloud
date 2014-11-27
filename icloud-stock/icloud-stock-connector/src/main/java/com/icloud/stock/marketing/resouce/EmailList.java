package com.icloud.stock.marketing.resouce;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.file.TextFile;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.marketing.pojo.FromEmail;

public class EmailList {
	public List<FromEmail> list;
	public int size;

	public EmailList() {
		initEmailList();
	}

	void initEmailList() {
		list = new ArrayList<FromEmail>();
		TextFile emailFile = new TextFile(
				"/data/mywork/marketing/mail/list.txt");
		for (String email : emailFile) {
			list.add(FromEmail.generateEmail(email));
		}
		size = list.size();
	}

	public FromEmail getEmail() {
		int index = ICloudUtils.getRandom(size);
		return list.get(index);
	}

	public static void main(String[] args) {
		EmailList list = new EmailList();
		list.getEmail();
	}
}
