package com.icloud.insurance.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.insurance.dao.MediaFileDao;
import com.icloud.insurance.model.MediaFile;

@Service("mediaFileService")
public class MediaFileService extends SqlBaseService<MediaFile> {
	protected static final Logger logger = RequestIdentityLogger
			.getLogger(MediaFileService.class);

	@Resource(name = "mediaFileDao")
	private MediaFileDao mediaFileDao;

	@Override
	protected IHibernateBaseDao<MediaFile> getDao() {
		return mediaFileDao;
	}

}
