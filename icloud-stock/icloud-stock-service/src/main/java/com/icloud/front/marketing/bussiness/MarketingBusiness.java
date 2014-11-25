package com.icloud.front.marketing.bussiness;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.util.ICloudUtils;
import com.icloud.stock.dao.IMarketingEmailDao;
import com.icloud.stock.model.MarketingEmail;
import com.icloud.stock.service.IMarketingChannelService;
import com.icloud.stock.service.IMarketingEmailService;

@Service("marketingBusiness")
public class MarketingBusiness {
	public final static String SEQ = "###";
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
			marketingEmail.setDitch(ditch);
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

	public MarketingEmail saveMarketingEmail(String email, String ditch) {
		MarketingEmail marketingEmail = generate(email, ditch);
		if (ICloudUtils.isNotNull(marketingEmail))
			this.marketingEmailService.save(marketingEmail);
		return marketingEmail;
	}

	public void saveMarketingEmail(List<String> emails, String ditch) {
		List<MarketingEmail> list = generate(emails, ditch);
		if (!ICloudUtils.isEmpty(list)) {
			this.marketingEmailService.save(list);
		}
	}

	public void update(String email, String channel, String ditch) {
		/**
		 * 检查有没有ditch
		 */
		String[] params = { IMarketingEmailDao.EMAIL, IMarketingEmailDao.DITCH };
		Object[] values = { email, ditch };
		MarketingEmail marketingEmail = ICloudUtils
				.getFirstElement(this.marketingEmailService.findByProperty(
						params, values, null, false, 0, 2));
		if (!ICloudUtils.isNotNull(marketingEmail)) {
			marketingEmail = saveMarketingEmail(email, ditch);
		}
		if (ICloudUtils.isNotNull(marketingEmail)) {
			marketingEmail.setActive(marketingEmail.getActive() + 1);
			marketingEmail.setAccessCount(marketingEmail.getAccessCount() + 1);
			marketingEmail.setLastUpdateTime(new Date());
			String processAccesssDetail = processAccesssDetail(
					marketingEmail.getAcccessDetail(), channel);
			marketingEmail.setAcccessDetail(processAccesssDetail);
			this.marketingEmailService.update(marketingEmail);
		}

	}

	public String processAccesssDetail(String detail, String channel) {

		if (ICloudUtils.isNotNull(detail)) {
			String[] params = detail.split(SEQ);
			if (ICloudUtils.isNotNull(params)) {
				Map<String, Integer> map = new TreeMap<String, Integer>();
				for (String param : params) {
					String[] ps = param.split(",");
					if (ICloudUtils.isNotNull(ps) && ps.length == 2) {
						map.put(ps[0], ICloudUtils.parseInt(ps[1], 1));
					}
				}
				Integer in = map.get(channel);
				if (in == 0)
					in = 1;
				else
					in = in + 1;
				map.put(channel, in);

				// 拼凑数据
				StringBuffer sb = new StringBuffer();
				for (Entry<String, Integer> entry : map.entrySet()) {
					sb.append(entry.getKey() + "," + entry.getValue() + SEQ);
				}
				return sb.toString().substring(0,
						sb.toString().length() - SEQ.length());
			}
		}
		return channel + ",1";

	}

}
