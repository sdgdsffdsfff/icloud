package com.icloud.stock.marketing.task;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

import com.icloud.framework.util.FileUtils;
import com.icloud.stock.marketing.content.EmailContentTemplate;

public class TestContentTemplate implements EmailContentTemplate {
	private String titleTemplate = "";
	private String qqtitleTemplate = "";
	private String contentTemplate = "";
	private String qqcontentTemplate = "";

	public TestContentTemplate() throws FileNotFoundException,
			UnsupportedEncodingException {
		titleTemplate = FileUtils
				.readString("/data/mywork/marketing/template/test/title.txt");
		qqtitleTemplate = FileUtils
				.readString("/data/mywork/marketing/template/test/qq_title.txt");
		contentTemplate = FileUtils
				.readContent("/data/mywork/marketing/template/test/content.html");
		qqcontentTemplate = FileUtils
				.readContent("/data/mywork/marketing/template/test/qq_content.html");
	}

	@Override
	public String getTilte(String email, String channel, String ditch) {
		if (email.indexOf("qq") != -1) {
			return qqtitleTemplate;
		}
		return qqtitleTemplate;
	}

	@Override
	public String getContent(String email, String channel, String ditch) {
		if (email.indexOf("qq") != -1) {
			return qqcontentTemplate.replaceAll("#email#", email)
					.replace("#channel#", channel).replace("#ditch#", ditch);
		}
		return qqcontentTemplate.replaceAll("#email#", email)
				.replace("#channel#", channel).replace("#ditch#", ditch);
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
		System.out.println(template.getContent(email, channel, ditch));
		System.out.println("----");
	}
}
