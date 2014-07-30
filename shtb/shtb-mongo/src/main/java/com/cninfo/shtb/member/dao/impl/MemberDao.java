/**
 *
 */
package com.cninfo.shtb.member.dao.impl;

import org.springframework.stereotype.Repository;

import com.cninfo.shtb.basedao.impl.ShbtMongoBaseDao;
import com.cninfo.shtb.member.dao.IMemberDao;
import com.cninfo.shtb.mongo.entity.Member;

@Repository("shtb_MemberDao")
public class MemberDao extends ShbtMongoBaseDao<Member> implements IMemberDao {

}
