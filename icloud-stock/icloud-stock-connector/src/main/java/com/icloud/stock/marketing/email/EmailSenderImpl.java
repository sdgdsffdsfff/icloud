package com.icloud.stock.marketing.email;

import com.icloud.stock.marketing.resouce.EmailSendUtil;
import com.icloud.stock.marketing.resouce.EmailSender;

public class EmailSenderImpl implements EmailSender {

	@Override
	public boolean send(String email) {
		return EmailSendUtil.sendMailUtil(email, "title", "html");
	}

}
