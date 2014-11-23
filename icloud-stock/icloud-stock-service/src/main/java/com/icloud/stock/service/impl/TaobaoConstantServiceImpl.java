package com.icloud.stock.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.stock.dao.ITaobaoConstantDao;
import com.icloud.stock.model.TaobaoConstant;
import com.icloud.stock.service.ITaobaoConstantService;

@Service("taobaoConstantService")
public class TaobaoConstantServiceImpl extends SqlBaseService<TaobaoConstant>
		implements ITaobaoConstantService {

	@Resource(name = "taobaoConstantDao")
	private ITaobaoConstantDao taobaoConstantDao;

	@Override
	protected IHibernateBaseDao<TaobaoConstant> getDao() {
		// TODO Auto-generated method stub
		return taobaoConstantDao;
	}

}
