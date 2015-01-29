package com.icloud.user.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.model.Paper;
import com.icloud.user.dao.IPaperDao;
import com.icloud.user.service.IPaperService;

@Service("paperService")
public class PaperServiceImpl extends SqlBaseService<Paper> implements
		IPaperService {
	@Resource(name = "paperDao")
	private IPaperDao paperDao;

	@Override
	protected IHibernateBaseDao<Paper> getDao() {
		// TODO Auto-generated method stub
		return paperDao;
	}

}
