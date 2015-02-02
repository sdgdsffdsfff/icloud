package com.icloud.stock.dao.test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icloud.stock.model.Paper;
import com.icloud.user.dao.IPaperDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/icloud-stock-dao-ctx-min.xml" })
public class PaperDaoTest {
	@Resource(name = "paperDao")
	private IPaperDao paperDao;

	@Test
	public void testFindMetaList() {
		List<Paper> list = paperDao.findMetaList(0, 1);
		for (Paper paper : list) {
			System.out.println(paper.toString());
		}
	}

}
