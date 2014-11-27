package com.icloud.stock.marketing.content;

public interface EmailContentTemplate {
	public String getTilte(String email, String channel, String ditch);

	public String getContent(String email, String channle, String ditch);
}
