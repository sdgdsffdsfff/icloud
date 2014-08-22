package com.cinfo.shtb.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;

import com.cinfo.shtb.base.dao.BaseDaoTest;
import com.cninfo.shtb.member.dao.IMemberDao;
import com.cninfo.shtb.mongo.entity.Member;

public class MemberDaoTest extends BaseDaoTest {
	@Resource(name = "shtb_MemberDao")
	private IMemberDao memberDao;

//	@Test
//	public void saveMember() {
//		for (int i = 0; i < 10; i++) {
//			Member member = new Member();
//			member.setPassWord("sssss" + i);
//			member.setUserName("cuijiangning" + i);
//			member.setChineseName("崔江宁" + i);
//			this.memberDao.saveEntity(member);
//		}
//	}

	@Test
	public void findMember() {
		List<Member> list = this.memberDao.findAll();
		print(list);
	}

	void print(List<Member> list) {
		System.out.println("---------------------------");
		for (Member member : list) {
			System.out.println(member.getUserName() + "  "
					+ member.getPassWord() + " " + member.getKey() + " "
					+ member.getChineseName());
		}
		System.out.println("----------end------------");
	}

	@Test
	public void findWithProperties() {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("userName", "cuijiangning1");
		List<Member> list = this.memberDao.findByProperty(properties);
		print(list);
	}

	@Test
	public void findWithProperties2() {
		List<Member> list = memberDao.query();
		print(list);
	}
}
