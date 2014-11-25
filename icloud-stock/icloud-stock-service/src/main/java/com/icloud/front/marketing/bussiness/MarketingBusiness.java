package com.icloud.front.marketing.bussiness;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.model.MarketingEmail;
import com.icloud.stock.service.IMarketingChannelService;
import com.icloud.stock.service.IMarketingEmailService;

@Service("marketingBusiness")
public class MarketingBusiness {

	@Resource(name = "marketingChannelService")
	protected IMarketingChannelService marketingChannelService;

	@Resource(name = "marketingEmailService")
	protected IMarketingEmailService marketingEmailService;

	public MarketingEmail generate(String email, String ditch) {
		if (ICloudUtils.isNotNull(email) && ICloudUtils.isNotNull(ditch)) {
			MarketingEmail marketingEmail = new MarketingEmail();
			marketingEmail.setAcccessDetail("");
			marketingEmail.setAccessCount(0);
			marketingEmail.setActive(0);
			marketingEmail.setDitch("chinanet");
			marketingEmail.setEmail(email);
			marketingEmail.setLastSendTime(null);
			marketingEmail.setLastUpdateTime(null);
			return marketingEmail;
		}

		return null;
	}

	public List<MarketingEmail> generate(List<String> emails, String ditch) {
		if (ICloudUtils.isNotNull(emails) && ICloudUtils.isNotNull(ditch)) {
			List<MarketingEmail> list = new ArrayList<MarketingEmail>();
			for (String email : emails) {
				MarketingEmail marketingEmail = generate(email, ditch);
				if (ICloudUtils.isNotNull(marketingEmail)) {
					list.add(marketingEmail);
				}
			}
			return list;
		}
		return null;
	}

	public void saveMarketingEmail(String email, String ditch) {
		MarketingEmail marketingEmail = generate(email, ditch);
		if (ICloudUtils.isNotNull(marketingEmail))
			this.marketingEmailService.save(marketingEmail);
	}

	public void saveMarketingEmail(List<String> emails, String ditch) {
		List<MarketingEmail> list = generate(emails, ditch);
		if (!ICloudUtils.isEmpty(list)) {
			this.marketingEmailService.save(list);
		}
	}
}
