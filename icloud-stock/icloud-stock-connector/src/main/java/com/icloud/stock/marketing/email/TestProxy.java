package com.icloud.stock.marketing.email;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

import com.icloud.framework.file.TextFile;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.marketing.pojo.FromEmail;

public class TestProxy {
	public static void createIPAddress(String ip, int port) {
		URL url = null;
		try {
			url = new URL("http://www.baidu.com");
		} catch (MalformedURLException e) {
			System.out.println("url invalidate");
		}
		InetSocketAddress addr = null;
		addr = new InetSocketAddress(ip, port);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http proxy
		InputStream in = null;
		try {
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(1000);
			in = conn.getInputStream();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("ip " + ip + " is not aviable");
		}
		String s = convertStreamToString(in);
		// System.out.println(s);
		if (s.indexOf("baidu") > 0) {
			System.out.println(ip + "\t" + port);
		}
	}

	public static String convertStreamToString(InputStream is) {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

	public static void main(String[] args) {
		// createIPAddress("59.46.72.245", 8080);
		TextFile emailFile = new TextFile(
				"/data/mywork/task/emailtask1/qq/proxy.txt");
		for (String email : emailFile) {
			// // System.out.println(email);
			String[] split = email.split("\t");
			// System.out.println(split[1] + "  " + split[2]);
			createIPAddress(split[1], ICloudUtils.parseInt(split[2]));
		}
	}
}
