package com.cninfo.shtb.member.dao;

import java.util.List;

import com.cninfo.shtb.basedao.IShbtMongoBaseDao;
import com.cninfo.shtb.mongo.entity.Member;

public interface IMemberDao extends IShbtMongoBaseDao<Member> {

	/**
	 * @return List<Member>
	 * @throws
	 */
	List<Member> query();

	List<Member> queryDistinctName();

	/** 
	 * 
	 * void
	 * @throws 
	 */
	void mapreduce();
}
