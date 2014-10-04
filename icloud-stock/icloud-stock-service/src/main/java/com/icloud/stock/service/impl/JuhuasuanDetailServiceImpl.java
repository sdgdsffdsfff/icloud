package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IJuhuasuanDetailDao;
import com.icloud.stock.model.JuhuasuanDetail;
import com.icloud.stock.service.IJuhuasuanDetailService;

@Service("juhuasuanDetailService")
public class JuhuasuanDetailServiceImpl extends SqlBaseService<JuhuasuanDetail>
		implements IJuhuasuanDetailService {

	@Resource(name = "juhuasuanDetailDao")
	private IJuhuasuanDetailDao juhuasuanDetailDao;

	@Override
	protected IHibernateBaseDao<JuhuasuanDetail> getDao() {
		return this.juhuasuanDetailDao;
	}

}
