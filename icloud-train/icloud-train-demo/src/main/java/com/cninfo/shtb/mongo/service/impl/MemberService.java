package com.cninfo.shtb.mongo.service.impl;

import javax.annotation.Resource;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.cninfo.shtb.member.dao.impl.MemberDao;
import com.cninfo.shtb.mongo.entity.Member;
import com.cninfo.shtb.mongo.service.IMemberService;
import com.icloud.framework.mongo.service.impl.MongoBaseService;
import com.icloud.mongo.dao.IBasicDao;

@Service("shtb_MemberService")
public class MemberService extends MongoBaseService<Member, ObjectId> implements
		IMemberService {
	@Resource(name = "shtb_MemberDao")
	private MemberDao memberDao;

	@Override
	protected IBasicDao<Member, ObjectId> getDao() {
		// TODO Auto-generated method stub
		return this.memberDao;
	}

}
