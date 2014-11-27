package com.icloud.stock.marketing.resouce;

import com.icloud.stock.marketing.content.EmailContentTemplate;

public class EmailSenderImpl implements EmailSender {
	private EmailContentTemplate template;
	private String channel;
	private String ditch;

	public EmailSenderImpl(EmailContentTemplate template, String channel,
			String ditch) {
		this.template = template;
		this.channel = channel;
		this.ditch = ditch;
	}

	@Override
	public boolean send(String email) {
		/**
		 * 模板
		 */
		return EmailSendUtil.sendMailUtil(email,
				template.getTilte(email, channel, ditch),
				template.getContent(email, channel, ditch));
	}

}
