package com.icloud.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.model.Paper;
import com.icloud.user.dao.IPaperDao;

@Repository("paperDao")
public class PaperDaoImpl extends StockBaseDaoImpl<Paper> implements IPaperDao {

}
