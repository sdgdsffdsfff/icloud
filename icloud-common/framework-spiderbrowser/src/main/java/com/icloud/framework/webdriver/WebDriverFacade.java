package com.icloud.framework.webdriver;

import java.lang.reflect.InvocationTargetException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class WebDriverFacade {

	private static class InstanceHolder {
		private static WebDriverFacade INSTANCE = new WebDriverFacade();
		static {
			INSTANCE.init();
		}
	}

	public static WebDriverFacade getInstance() {
		return InstanceHolder.INSTANCE;
	}

	private WebDriver browser;

	public void initHtmlUnitDriver() {
		browser = new HtmlUnitDriver();
	}

	public void init() {
		initHtmlUnitDriver();
	}

	public WebDriver getWebDriver() throws InvocationTargetException,
			IllegalAccessException, InstantiationException {
		return browser;
	}

	public void closeBrowser() throws IllegalAccessException,
			InvocationTargetException, InstantiationException {
		if (browser != null) {
			browser.close();
			System.out.println(browser.toString());
			if (browser.toString().contains("Chrome")) {
				browser.quit();
			}
		}
	}

	public static void main(String[] args) throws InvocationTargetException,
			IllegalAccessException, InstantiationException {
		// WebDriver webDriver = WebDriverFacade.getWebDriver();
		WebDriverFacade facade = WebDriverFacade.getInstance();
		facade.get("http://localhost:8080/icloud-train-demo/member/getAccountForUserId?userId=1");
		System.out.println(facade.getPageSource());
	}

	public void get(String url) {
		this.browser.get(url);
	}

	public String getPageSource() {
		String pageSource = browser.getPageSource();
		return pageSource;
	}
}