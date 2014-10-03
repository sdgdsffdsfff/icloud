package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.IJuhuasuanSessionDao;
import com.icloud.stock.model.JuhuasuanSession;

@Repository("juhuasuanSessionDao")
public class JuhuasuanSessionDaoImpl extends StockBaseDaoImpl<JuhuasuanSession>
		implements IJuhuasuanSessionDao {

}
