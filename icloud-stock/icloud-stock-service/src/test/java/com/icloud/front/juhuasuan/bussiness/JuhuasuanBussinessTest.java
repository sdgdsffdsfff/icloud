package com.icloud.front.juhuasuan.bussiness;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.icloud.framework.picture.TZPhotoUtil;
import com.icloud.framework.util.ExcelIEUtil;
import com.icloud.framework.vo.KeyValue;
import com.icloud.front.stock.StockBussinessTest;
import com.icloud.stock.model.JuhuasuanUrl;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年10月10日 下午2:28:18
 */
public class JuhuasuanBussinessTest extends StockBussinessTest {
	@Test
	public void getXls() {
		JuhuasuanUrl urlBean = new JuhuasuanUrl();
		urlBean.setUserId(1);
		List<JuhuasuanUrl> urls = this.juhuasuanBussiness
				.searchAllJuhuasuanUrl(urlBean);
		logger.info("----------start----------");
		for (JuhuasuanUrl url : urls) {
			logger.info("urlname:{},taobaoUrl:{}", url.getName(),
					url.getTaobaoUrl());
		}
		logger.info("count={}", urls.size());
		logger.info("----------end----------");
		logger.info("----------start to 生成xls----------");
		List<KeyValue<String, String>> list = new ArrayList<KeyValue<String, String>>();
		// = new HashMap<String, String>();
		list.add(new KeyValue<String, String>("id", "Id"));
		list.add(new KeyValue<String, String>("userId", "用户ID"));
		list.add(new KeyValue<String, String>("icloudUrl", "本地代码"));

		list.add(new KeyValue<String, String>("name", "链接名"));
		list.add(new KeyValue<String, String>("taobaoUrl", "淘宝url"));
		list.add(new KeyValue<String, String>("desText", "描述"));
		list.add(new KeyValue<String, String>("status", "状态"));
		list.add(new KeyValue<String, String>("type", "类型"));
		list.add(new KeyValue<String, String>("solidify", "加固方式"));
		list.add(new KeyValue<String, String>("originUrl", "原始链接"));
		
		byte[] bytes = ExcelIEUtil.exportBytes(list,
				urls);
		TZPhotoUtil.storeToFile("d:/test/icloud/t.xls", bytes);
		logger.info("----------end to 生成xls----------");
	}
}
