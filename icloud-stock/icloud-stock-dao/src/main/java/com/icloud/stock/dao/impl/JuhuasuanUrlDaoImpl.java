package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IJuhuasuanUrlDao;
import com.icloud.stock.model.JuhuasuanUrl;

@Repository("juhuasuanUrlDao")
public class JuhuasuanUrlDaoImpl extends StockBaseDaoImpl<JuhuasuanUrl>
		implements IJuhuasuanUrlDao {
	
}
