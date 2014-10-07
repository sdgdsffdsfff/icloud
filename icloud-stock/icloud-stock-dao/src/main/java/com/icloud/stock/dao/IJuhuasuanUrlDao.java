package com.icloud.stock.dao;

import com.icloud.dao.StockBaseDao;
import com.icloud.stock.model.JuhuasuanUrl;

public interface IJuhuasuanUrlDao extends StockBaseDao<JuhuasuanUrl> {
	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String TAOBAOURL = "taobaoUrl";
	public static final String ICLOUDURL = "icloudUrl";
	public static final String USERID = "userId";
	public static final String CREATETIME = "createTime";
	public static final String UPDATETIME = "updateTime";
	public static final String DESTEXT = "desText";
	public static final String STATUS = "status";
	public static final String TYPE = "type";
	public static final String SOLIDIFY = "solidify";
	public static final String ORIGINURL = "orginUrl";

}
