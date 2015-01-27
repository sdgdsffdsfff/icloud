/**
 *
 */
package com.cninfo.shtb.member.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cninfo.shtb.basedao.impl.ShbtMongoBaseDao;
import com.cninfo.shtb.member.dao.IMemberDao;
import com.cninfo.shtb.mongo.entity.Member;
import com.github.jmkgreen.morphia.mapping.Mapper;
import com.github.jmkgreen.morphia.query.Query;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MapReduceOutput;

@Repository("shtb_MemberDao")
public class MemberDao extends ShbtMongoBaseDao<Member> implements IMemberDao {

	@Override
	public List<Member> query() {
		Query<Member> query = createQuery();
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

	@Override
	public void mapreduce() {
		String map = "function () { emit(this.chineseName, 1)}";
		String reduce = "function (key, values) "
				+ "{ var count = 0; "
				+ "for (var i = 0; i < values.length; i++) { count += values[i]; }"
				+ " return count;  }";
		MapReduceOutput out = this.getDatastore()
				.getCollection(getEntityClass())
				.mapReduce(map, reduce, null, OutputType.INLINE, null);

		Map<String, Integer> result = new HashMap<String, Integer>();
		BasicDBList list = (BasicDBList) out.getCommandResult().get("results");
		Iterator<Object> it = list.iterator();
		while (it.hasNext()) {
			DBObject dbObj = (DBObject) it.next();
			result.put(dbObj.get(Mapper.ID_KEY).toString(),
					Double.valueOf(dbObj.get("value").toString()).intValue());
		}
	}

}
