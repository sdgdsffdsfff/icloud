package com.icloud.insurance.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.icloud.framework.dao.hibernate.IHibernateBaseDao;
import com.icloud.framework.enums.FileType;
import com.icloud.framework.logger.ri.RequestIdentityLogger;
import com.icloud.framework.picture.TZPhotoUtil;
import com.icloud.framework.security.MD5;
import com.icloud.framework.service.impl.SqlBaseService;
import com.icloud.framework.util.FileUtils;
import com.icloud.framework.util.ICloudUtils;
import com.icloud.insurance.dao.MediaFileDao;
import com.icloud.insurance.model.MediaFile;
import com.icloud.insurance.model.constant.MediaFileConstant;

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

	public MediaFile save(File file) {
		if (ICloudUtils.isNotNull(file)) {
			byte[] bytes = null;
			try {
				bytes = TZPhotoUtil.getBytesFromFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ICloudUtils.isNotNull(bytes)) {
				String hashId = MD5.byteToMD5Encode(bytes);
				if (exits(hashId)) {
					MediaFile mediaFile = new MediaFile();
					mediaFile.setCreateTime(new Date());
					mediaFile.setFileBytes(bytes);
					mediaFile.setFileName(file.getName());
					mediaFile.setFileSize(bytes.length);
					mediaFile.setFileType(FileType.getFileType(
							FileUtils.getFileSuffix(file.getName()))
							.getStreamType());
					mediaFile.setLastUpdateTime(mediaFile.getCreateTime());
					mediaFile.setFileHashId(hashId);
					this.save(mediaFile);
					return mediaFile;
				}
			}
		}
		return null;
	}

	public MediaFile getByHashId(String id) {
		if (ICloudUtils.isNotNull(id)) {
			return ICloudUtils.getFirstElement(this.findByProperies(
					MediaFileConstant.FILEHASHID, id));
		}
		return null;
	}

	public boolean exits(String id) {
		return !ICloudUtils.isNotNull(this.getByHashId(id));
	}

	public MediaFile getByFile(File imgFile) {
		byte[] bytes = null;
		try {
			bytes = TZPhotoUtil.getBytesFromFile(imgFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (ICloudUtils.isNotNull(bytes)) {
			String hashId = MD5.byteToMD5Encode(bytes);
			return getByHashId(hashId);
		}
		return null;
	}
}
