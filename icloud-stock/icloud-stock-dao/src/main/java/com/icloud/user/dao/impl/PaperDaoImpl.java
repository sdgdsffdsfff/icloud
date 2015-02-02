package com.icloud.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.icloud.dao.impl.StockBaseDaoImpl;
import com.icloud.stock.model.Paper;
import com.icloud.user.dao.IPaperDao;

@Repository("paperDao")
public class PaperDaoImpl extends StockBaseDaoImpl<Paper> implements IPaperDao {

	@Override
	public List<Paper> findMetaList(int start, int limit) {
		// String hql =
		// "select model.id, model.title, model.originTitle, model.originUrl, originWebsite, channel, count, createTime, updateTime from "
		// + domainClass.getName() + " as model";

		String hql = "select new Paper(model.id, model.title, model.originTitle, model.originUrl, model.originWebsite, model.channel, model.count, model.createTime, model.updateTime) from "
				+ domainClass.getName() + " as model";

		return this.findByProperty(hql, start, limit);
	}

}
