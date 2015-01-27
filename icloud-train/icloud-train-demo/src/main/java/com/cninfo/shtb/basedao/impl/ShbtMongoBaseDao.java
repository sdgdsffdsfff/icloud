/**
 *
 */
package com.cninfo.shtb.basedao.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cninfo.shtb.basedao.IShbtMongoBaseDao;
import com.github.jmkgreen.morphia.Datastore;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.mongo.dao.impl.MorphiaBasicDao;
import com.icloud.mongo.entity.MorphiaEntity;

public class ShbtMongoBaseDao<E extends MorphiaEntity<ObjectId>> extends
		MorphiaBasicDao<E, ObjectId> implements IShbtMongoBaseDao<E> {
	Logger logger = LoggerFactory.getLogger(ShbtMongoBaseDao.class);

	@Resource(name = "shtbDatastore")
	private Datastore shtbDatastore;

	@PostConstruct
	public void replaceDatastore() {
		super.setDatastore(shtbDatastore);
	}

	@Override
	public List<E> getByIdStrs(List<String> ids) {
		if (ICloudUtils.isEmpty(ids)) {
			return null;
		}
		List<ObjectId> objIds = new LinkedList<ObjectId>();
		for (String id : ids) {
			objIds.add(buildObjectId(id));
		}
		return getByIds(objIds);
	}

}
