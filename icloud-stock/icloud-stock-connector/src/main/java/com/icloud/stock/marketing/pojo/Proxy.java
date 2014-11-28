package com.icloud.stock.marketing.pojo;

import com.icloud.framework.util.ICloudUtils;

public class Proxy {
	private String ip;
	private int port;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static Proxy generate(String proxyStr) {
		Proxy proxy = new Proxy();
		String[] split = proxyStr.split("\t");
		proxy.setIp(split[0]);
		proxy.setPort(ICloudUtils.parseInt(split[1]));
		return proxy;
	}
}
