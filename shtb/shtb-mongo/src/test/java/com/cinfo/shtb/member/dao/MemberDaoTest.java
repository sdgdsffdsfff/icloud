package com.cinfo.shtb.member.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.cinfo.shtb.base.dao.BaseDaoTest;
import com.cninfo.shtb.member.dao.IMemberDao;
import com.cninfo.shtb.mongo.entity.Member;

public class MemberDaoTest extends BaseDaoTest {
	@Resource(name = "shtb_MemberDao")
	private IMemberDao memberDao;

	@Test
	public void saveMember() {
		Member member = new Member();
		member.setPassWord("1234");
		member.setUserName("cuijiangning");
		this.memberDao.saveEntity(member);
	}

	@Test
	public void findMember() {
		List<Member> list = this.memberDao.findAll();
		for (Member member : list) {
			System.out.println(member.getUserName() + " " + member.getKey()
					+ " " + member.getPassWord());
		}
	}

}
