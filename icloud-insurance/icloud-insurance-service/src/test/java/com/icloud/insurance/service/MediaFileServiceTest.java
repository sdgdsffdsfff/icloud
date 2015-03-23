package com.icloud.insurance.service;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;

import com.icloud.insurance.model.MediaFile;

public class MediaFileServiceTest extends BaseTest {


	@Test
	public void findTest() {
		// List<MediaFile> list = this.mediaFileService.findAll();
		// System.out.println(list);
		String path = "/data/test/insurance/image/1.jpg";
		File file = new File(path);
		mediaFileService.save(file);
		// byte[] bytes = null;
		// try {
		// bytes = TZPhotoUtil.getBytesFromFile(file);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(MD5.byteToMD5Encode(bytes));
	}

	@Test
	public void fileTest() {
		String id = "916dfa45610e606ec79a2cb1a14a4387";
		MediaFile file = mediaFileService.getByHashId(id);
		System.out.println(file.getFileName());
	}
}
