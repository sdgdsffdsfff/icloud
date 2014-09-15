package com.cninfo.media.dao;

import java.util.List;

import com.cninfo.media.basedao.ICninfoMediaBaseDao;
import com.cninfo.media.mongo.entity.Media;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:13:13
 */
public interface IMediaDao extends ICninfoMediaBaseDao {
	public Object addMedia(Media media);

	public Media getMedia(Object mediaId);

	public List<String> getMediaIds(int start, int limit);

	public Long count();
}
