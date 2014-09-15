/**
 *
 */
package com.cninfo.shtb.member.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cninfo.shtb.basedao.impl.ShbtMongoBaseDao;
import com.cninfo.shtb.member.dao.IMemberDao;
import com.cninfo.shtb.mongo.entity.Member;
import com.github.jmkgreen.morphia.mapping.Mapper;
import com.github.jmkgreen.morphia.query.Query;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

@Repository("shtb_MemberDao")
public class MemberDao extends ShbtMongoBaseDao<Member> implements IMemberDao {

	@Override
	public List<Member> query() {
		Query<Member> query = createQuery();
		/**
		 * 交集
		 */
		// query.field("userName").contains("g2");
		// query.field("chineseName").contains("宁2");
		// query.or(criteria);
		// query.or(criteria);
		query.or(query.criteria("userName").contains("g2"),
				query.criteria("chineseName").contains("宁1"),
				query.criteria("passWord").contains("s3"));
		return query.asList();
	}

	@Override
	public List<Member> queryDistinctName() {
		List list = this.getDatastore().getCollection(getEntityClass())
				.distinct("chineseName");
		
		for (Object object : list) {
			System.out.println(object);
		}
		return null;
	}

}
