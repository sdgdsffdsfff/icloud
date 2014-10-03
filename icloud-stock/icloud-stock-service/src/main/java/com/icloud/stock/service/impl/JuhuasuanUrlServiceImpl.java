package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IJuhuasuanUrlDao;
import com.icloud.stock.model.JuhuasuanUrl;
import com.icloud.stock.service.IJuhuasuanUrlService;

@Service("juhuasuanUrlService")
public class JuhuasuanUrlServiceImpl extends SqlBaseService<JuhuasuanUrl>
		implements IJuhuasuanUrlService {

	@Resource(name = "juhuasuanUrlDao")
	private IJuhuasuanUrlDao juhuasuanUrlDao;

	@Override
	protected IHibernateBaseDao<JuhuasuanUrl> getDao() {
		return this.juhuasuanUrlDao;
	}

}
