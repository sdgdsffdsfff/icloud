package com.icloud.stock.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.dao.ITaobaoConstantDao;
import com.icloud.stock.model.TaobaoConstant;

@Repository("taobaoConstantDao")
public class TaobaoConstantDaoImpl extends StockBaseDaoImpl<TaobaoConstant>
		implements ITaobaoConstantDao {

}
