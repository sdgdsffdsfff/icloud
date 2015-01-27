package com.cninfo.shtb.basedao;

import java.util.List;

import org.bson.types.ObjectId;

import com.icloud.mongo.dao.IBasicDao;

public interface IShbtMongoBaseDao<E> extends IBasicDao<E, ObjectId> {
	List<E> getByIdStrs(List<String> ids);

}
