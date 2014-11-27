package com.icloud.stock.marketing.task;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.icloud.framework.util.FileUtils;
import com.icloud.stock.marketing.content.EmailContentTemplate;

public class TestContentTemplate implements EmailContentTemplate {
	private String titleTemplate = "";
	private String contentTemplate = "";

	public TestContentTemplate() throws FileNotFoundException,
			UnsupportedEncodingException {
		titleTemplate = FileUtils
				.readString("/data/mywork/marketing/template/test/title.txt");
		contentTemplate = FileUtils
				.readContent("/data/mywork/marketing/template/test/content.html");
	}

	@Override
	public String getTilte(String email, String channel, String ditch) {
		return titleTemplate;
	}

	@Override
	public String getContent(String email, String channle, String ditch) {
		return contentTemplate;
	}

	public static void main(String[] args) throws FileNotFoundException,
			UnsupportedEncodingException {
		TestContentTemplate template = new TestContentTemplate();
		String email = "cuijiangning@163.com";
		String channel = "1";
		String ditch = "oschina";
		System.out.println("----");
		System.out.println(template.getTilte(email, channel, ditch));
		System.out.println("----");
		System.out.println(template.getContent("cuijiangning", "0", "oschina"));
		System.out.println("----");
	}
}
