package com.cninfo.media.mongo.entity;

public enum MediaType {

	IMAGE("image"), DOWNLOAD("download"), VIDEO("video");

	private final String value;

	MediaType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
