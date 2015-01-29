package com.icloud.user.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.Paper;

public interface IPaperDao extends StockBaseDao<Paper> {
	public static final String TITLE = "title";
	public static final String CONTENT = "content";
	public static final String ORIGINTITLE = "originTitle";
	public static final String ORIGINURL = "originUrl";
	public static final String ORIGINWEBSITE = "originWebsite";

	public static final String CHANNEL = "channel";
	public static final String COUNT = "count";

	public static final String CREATETIME = "createTime";
	public static final String UPDATETIME = "updateTime";

}
