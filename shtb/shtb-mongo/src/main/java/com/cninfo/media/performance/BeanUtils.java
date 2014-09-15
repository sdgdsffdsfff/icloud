package com.cninfo.media.performance;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cninfo.media.dao.IMediaDao;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月15日 上午9:22:06
 */
public class BeanUtils {
	static ClassPathXmlApplicationContext svContext = new ClassPathXmlApplicationContext(
			"classpath*:spring/shtb-mongo-ctx-min.xml");
	public static IMediaDao mediaDao = (IMediaDao) svContext
			.getBean("mediaDao");

}
