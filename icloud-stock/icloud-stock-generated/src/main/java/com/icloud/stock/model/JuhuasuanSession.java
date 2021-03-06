package com.icloud.stock.model;

// Generated Jan 29, 2015 9:21:11 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * JuhuasuanSession generated by hbm2java
 */
public class JuhuasuanSession implements java.io.Serializable {

	private Integer id;
	private int juhuasuanId;
	private int userId;
	private Date createTime;
	private Date lastupdateTime;
	private String sessionId;
	private Integer count;
	private String lastreadIp;

	public JuhuasuanSession() {
	}

	public JuhuasuanSession(int juhuasuanId, int userId) {
		this.juhuasuanId = juhuasuanId;
		this.userId = userId;
	}

	public JuhuasuanSession(int juhuasuanId, int userId, Date createTime,
			Date lastupdateTime, String sessionId, Integer count,
			String lastreadIp) {
		this.juhuasuanId = juhuasuanId;
		this.userId = userId;
		this.createTime = createTime;
		this.lastupdateTime = lastupdateTime;
		this.sessionId = sessionId;
		this.count = count;
		this.lastreadIp = lastreadIp;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getJuhuasuanId() {
		return this.juhuasuanId;
	}

	public void setJuhuasuanId(int juhuasuanId) {
		this.juhuasuanId = juhuasuanId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastupdateTime() {
		return this.lastupdateTime;
	}

	public void setLastupdateTime(Date lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}

	public String getSessionId() {
		return this.sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getLastreadIp() {
		return this.lastreadIp;
	}

	public void setLastreadIp(String lastreadIp) {
		this.lastreadIp = lastreadIp;
	}

}
