package com.icloud.stock.marketing.pojo;

public class FromEmail {
	String email;
	String password;
	String host;
	String usename;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUsename() {
		return usename;
	}

	public void setUsename(String usename) {
		this.usename = usename;
	}

	public static FromEmail generateEmail(String email) {
		FromEmail mail = new FromEmail();
		mail.setEmail(email);
		mail.setPassword("Abc12345");
		mail.setHost("mail.tmalllo.me");
		// mail.setUsename("tmalllo特供");
		mail.setUsename(email);
		// fromEmail.setEmail("qq003@tmalllo.me");
		// fromEmail.setHost("mail.tmalllo.me");
		// // fromEmail.setPassword("@123Gabc12345");
		// fromEmail.setPassword("Abc12345");
		// fromEmail.setUsename("qq003@tmalllo.me");
		return mail;
	}

	public static FromEmail generateEmail(int i) {
		return generateEmail("qq" + i + "@tmalllo.me");
	}

}
