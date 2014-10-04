package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.IJuhuasuanSessionDao;
import com.icloud.stock.model.JuhuasuanSession;
import com.icloud.stock.service.IJuhuasuanSessionService;

@Service("juhuasuanSessionService")
public class JuhuasuanSessionServiceImpl extends SqlBaseService<JuhuasuanSession>
		implements IJuhuasuanSessionService {

	@Resource(name = "juhuasuanSessionDao")
	private IJuhuasuanSessionDao juhuasuanSessionDao;

	@Override
	protected IHibernateBaseDao<JuhuasuanSession> getDao() {
		return this.juhuasuanSessionDao;
	}

}
