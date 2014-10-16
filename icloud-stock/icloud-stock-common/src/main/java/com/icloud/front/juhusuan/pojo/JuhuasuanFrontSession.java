package com.icloud.front.juhusuan.pojo;

public class JuhuasuanFrontSession {
	private Integer id;
	private String icloudUrl;
	private int userId;
	private String sessionId;
	private Long count;
	private String lastreadIp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIcloudUrl() {
		return icloudUrl;
	}

	public void setIcloudUrl(String icloudUrl) {
		this.icloudUrl = icloudUrl;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getLastreadIp() {
		return lastreadIp;
	}

	public void setLastreadIp(String lastreadIp) {
		this.lastreadIp = lastreadIp;
	}

	@Override
	public String toString() {
		return "JuhuasuanFrontSession [id=" + id + ", icloudUrl=" + icloudUrl
				+ ", userId=" + userId + ", sessionId=" + sessionId
				+ ", count=" + count + ", lastreadIp=" + lastreadIp + "]";
	}

}
