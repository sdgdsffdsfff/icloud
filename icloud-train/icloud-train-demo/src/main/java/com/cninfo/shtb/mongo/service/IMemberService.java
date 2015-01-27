package com.cninfo.shtb.mongo.service;

import org.bson.types.ObjectId;

import com.cninfo.shtb.mongo.entity.Member;
import com.icloud.framework.mongo.service.IMongoBaseService;

public interface IMemberService extends IMongoBaseService<Member, ObjectId> {

}
