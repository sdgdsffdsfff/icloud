package com.cninfo.media.basedao.impl;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import com.cninfo.media.basedao.ICninfoMediaBaseDao;
import com.github.jmkgreen.morphia.Datastore;
import com.icloud.mongo.Constants;
import com.icloud.mongo.dao.impl.ImageBasicDaoImpl;
import com.mongodb.gridfs.GridFS;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午1:58:31
 */
public class CninfoMediaBaseDaoImpl extends ImageBasicDaoImpl implements
		ICninfoMediaBaseDao {
	@Resource(name = "shtbDatastore")
	private Datastore shtbDatastore;

	@PostConstruct
	public void replaceDatastore() {
		super.setDatastore(shtbDatastore);
		grfs = new GridFS(datastore.getDB(), Constants.GRIDFS_PICTURE_BUCKET);
	}

}
