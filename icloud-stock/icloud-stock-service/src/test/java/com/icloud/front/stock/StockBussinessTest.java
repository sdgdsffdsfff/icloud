package com.icloud.front.stock;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.front.juhuasuan.bussiness.JuhuasuanBussiness;
import com.icloud.front.stock.bussiness.StockCommonBussiness;
import com.icloud.front.stock.bussiness.StockListBussiness;
import com.icloud.stock.service.impl.UserUrlAccessCountServiceImpl;

@ContextConfiguration(locations = { "classpath*:spring/icloud-stock-service-ctx-min.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class StockBussinessTest {
	protected final Logger logger = RequestIdentityLogger.getLogger(this
			.getClass());
	@Resource(name = "stockCommonBussiness")
	protected StockCommonBussiness stockCommonBussiness;
	@Resource(name = "stockListBussiness")
	protected StockListBussiness stockListBussiness;

	@Resource(name = "juhuasuanBussiness")
	protected JuhuasuanBussiness juhuasuanBussiness;
	
}
