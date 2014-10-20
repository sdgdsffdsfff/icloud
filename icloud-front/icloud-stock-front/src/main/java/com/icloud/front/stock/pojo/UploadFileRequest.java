package com.icloud.front.stock.pojo;

import org.springframework.web.multipart.MultipartFile;

public class UploadFileRequest {
	private String name; // 文件名
	private MultipartFile filedata; // 导入文件

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MultipartFile getFiledata() {
		return filedata;
	}

	public void setFiledata(MultipartFile filedata) {
		this.filedata = filedata;
	}



}
