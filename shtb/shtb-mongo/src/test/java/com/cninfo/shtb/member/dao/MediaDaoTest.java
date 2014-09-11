package com.cninfo.shtb.member.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.cninfo.media.dao.IMediaDao;
import com.cninfo.media.mongo.entity.Media;
import com.cninfo.media.mongo.entity.MediaType;
import com.cninfo.shtb.base.dao.BaseDaoTest;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:23:46
 */
public class MediaDaoTest extends BaseDaoTest {
	@Resource(name = "mediaDao")
	private IMediaDao mediaDao;

	@Test
	public void testSave() throws FileNotFoundException {
		Media media = new Media();
		InputStream inputStream = new FileInputStream(new File(
				"D:\\test\\media\\test1.txt"));
		media.setInputStream(inputStream);
		media.setFilename("nihao");
		media.setMediaId("dsafas");
		media.setType(MediaType.IMAGE.name());
		media.setCreateTime(new Date());
		media.setLength(5555);
		mediaDao.addMedia(media);
	}

}
