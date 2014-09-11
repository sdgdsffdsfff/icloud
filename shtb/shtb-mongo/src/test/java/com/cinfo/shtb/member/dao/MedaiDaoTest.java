package com.cinfo.shtb.member.dao;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.cinfo.shtb.base.dao.BaseDaoTest;
import com.cninfo.media.dao.IMediaDao;
import com.cninfo.media.mongo.entity.Media;
import com.cninfo.media.mongo.entity.MediaType;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:23:46
 */
public class MedaiDaoTest extends BaseDaoTest {
	@Resource(name = "shtb_MemberDao")
	private IMediaDao mediaDao;

	@Test
	public void testSave() {
		Media media = new Media();

		media.setFilename("nihao");
		media.setMediaId("dsafas");
		media.setType(MediaType.IMAGE.name());
		media.setCreateTime(new Date());
		media.setLength(5555);
		mediaDao.addMedia(media);
	}
}
