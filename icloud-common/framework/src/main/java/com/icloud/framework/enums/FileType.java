package com.icloud.framework.enums;

import com.icloud.framework.util.ICloudUtils;

public enum FileType {
	JPG("jpg", "image/jpeg"), OTHER("*", "application/octet-stream"), AWF(
			"awf", "application/vnd.adobe.workflow"), BMP("*",
			"application/x-bmp"), HTM("htm", "text/html"), HTML("html",
			"text/html"), IMG("img", "application/x-img"), JPEG("jpeg",
			"image/jpeg"), PDF("pdf", "application/pdf"), PNG("png",
			"image/png"), PPT("ppt", "application/vnd.ms-powerpoint"), TXT(
			"txt", "text/plain"), XLS("xls", "application/x-xls");
	private String type;
	private String streamType;

	private FileType(String type, String streamType) {
		this.type = type;
		this.streamType = streamType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStreamType() {
		return streamType;
	}

	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	public static FileType getFileType(String value) {
		if (ICloudUtils.isNotNull(value)) {
			value = value.toLowerCase();
			FileType[] values = FileType.values();
			for (FileType type : values) {
				if (value.equalsIgnoreCase(type.getType())) {
					return type;
				}
			}
		}
		// if (ICloudUtils.isNotNull(value)) {
		// value = value.toLowerCase();
		// FileType fileType = FileType.valueOf(value);
		// if (ICloudUtils.isNotNull(fileType))
		// return fileType;
		// }
		return FileType.OTHER;
	}

	public static void main(String[] args) {
		String value = "pdf";
		FileType fileType = FileType.getFileType(value);
		System.out.println(fileType.getType());
		System.out.println(fileType.getStreamType());
	}
}
