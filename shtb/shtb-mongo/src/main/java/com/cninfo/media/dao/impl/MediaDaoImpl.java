package com.cninfo.media.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.cninfo.media.basedao.impl.CninfoMediaBaseDaoImpl;
import com.cninfo.media.dao.IMediaDao;
import com.cninfo.media.mongo.entity.Media;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;

/**
 * @comment
 * @author 崔江宁
 * @email cuijiangning@cninfo.com.cn 2014年9月11日 下午2:14:06
 */
@Repository("mediaDao")
public class MediaDaoImpl extends CninfoMediaBaseDaoImpl implements IMediaDao {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(MediaDaoImpl.class);

	@Override
	public Object addMedia(Media media) {
		GridFSFile mediafile = this.grfs.createFile(media.getInputStream());
		mediafile.put("mediaId", media.getMediaId());
		mediafile.put("filename", media.getFilename());
		mediafile.put("contentType", media.getType());
		mediafile.save();

		media.setCreateTime(mediafile.getUploadDate());

		return media.getMediaId();
	}

	@Override
	public Media getMedia(Object mediaId) {
		DBObject obj = new BasicDBObject();
		obj.put("mediaId", mediaId);
		GridFSDBFile file = this.grfs.findOne(obj);

		if (file == null) {
			return null;
		}

		Media media = new Media();
		media.setCreateTime(file.getUploadDate());
		media.setInputStream(file.getInputStream());
		media.setFilename(file.getFilename());
		media.setMediaId((String) mediaId);
		media.setType(file.getContentType());
		media.setLength(file.getLength());
		return media;
	}
}
