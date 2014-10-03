package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IJuhuasuanDetailDao;
import com.icloud.stock.model.JuhuasuanDetail;

@Repository("juhuasuanDetailDao")
public class JuhuasuanDetailDaoImpl extends StockBaseDaoImpl<JuhuasuanDetail>
		implements IJuhuasuanDetailDao {
	
}
