package com.icloud.stock.marketing.resouce;

import java.util.ArrayList;
import java.util.List;

import com.icloud.framework.file.TextFile;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.marketing.pojo.FromEmail;
import com.icloud.stock.marketing.pojo.Proxy;

public class EmailList {
	public List<FromEmail> list;
	public int size;

	public List<Proxy> proxylist;
	int proxySize = 0;

	public EmailList() {
		initEmailList();
	}

	void initEmailList() {
		list = new ArrayList<FromEmail>();
		proxylist = new ArrayList<Proxy>();
		TextFile emailFile = new TextFile(
				"/data/mywork/marketing/mail/list2.txt");
		int i = 0;
		for (String email : emailFile) {
			i++;
			if (i > 25)
				list.add(FromEmail.generateEmail(email));
		}
		// for (int i = 1; i < 2000; i++) {
		// list.add(FromEmail.generateEmail(i));
		// }
		size = list.size();
		TextFile proxyFile = new TextFile(
				"/data/mywork/marketing/resources/proxy.txt");
		for (String proxyStr : proxyFile) {
			// System.out.println(proxyStr);
			proxylist.add(Proxy.generate(proxyStr));
		}
		proxySize = proxylist.size();
	}

	public FromEmail getEmail() {
		int index = ICloudUtils.getRandom(size);
		return list.get(index);
	}

	public Proxy getProxy() {
		int index = ICloudUtils.getRandom(proxySize);
		return proxylist.get(index);
	}

	public static void main(String[] args) {
		EmailList list = new EmailList();
		list.getEmail();
	}
}
