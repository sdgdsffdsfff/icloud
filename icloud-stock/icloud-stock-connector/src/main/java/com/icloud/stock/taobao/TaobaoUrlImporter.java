package com.icloud.stock.taobao;

import com.icloud.front.juhuasuan.bussiness.JuhuasuanStatBusiness;
import com.icloud.stock.ctx.BeansUtil;

public class TaobaoUrlImporter {
	JuhuasuanStatBusiness JuhuasuanStatBusiness = BeansUtil
			.getJuhuasuanStatBusiness();

	public void update() {
		JuhuasuanStatBusiness.updateUserUrlAccessCountDaily();
	}
}
