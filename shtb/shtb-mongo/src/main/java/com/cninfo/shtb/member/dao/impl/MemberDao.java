/**
 *
 */
package com.cninfo.shtb.member.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cninfo.shtb.basedao.impl.ShbtMongoBaseDao;
import com.cninfo.shtb.member.dao.IMemberDao;
import com.cninfo.shtb.mongo.entity.Member;
import com.github.jmkgreen.morphia.query.Query;

@Repository("shtb_MemberDao")
public class MemberDao extends ShbtMongoBaseDao<Member> implements IMemberDao {

	@Override
	public List<Member> query() {
		Query<Member> query = datastore.createQuery(getEntityClass());
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

}
