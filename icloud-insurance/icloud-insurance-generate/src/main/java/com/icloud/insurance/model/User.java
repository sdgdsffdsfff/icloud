package com.icloud.insurance.model;

// Generated 2015-2-16 16:20:21 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer id;
	private String userName;
	private String userPassword;
	private String userTel;
	private String userEmail;
	private String userComing;
	private String userSex;
	private Date createTime;
	private Date lastUpdateTime;
	private String qq;
	private String chinaName;
	private Integer level;
	private Integer open;

	public User() {
	}

	public User(String userName, String userPassword, String userTel,
			String userEmail, String userComing, String userSex,
			Date createTime, Date lastUpdateTime, String qq, String chinaName,
			Integer level, Integer open) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.userTel = userTel;
		this.userEmail = userEmail;
		this.userComing = userComing;
		this.userSex = userSex;
		this.createTime = createTime;
		this.lastUpdateTime = lastUpdateTime;
		this.qq = qq;
		this.chinaName = chinaName;
		this.level = level;
		this.open = open;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserTel() {
		return this.userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getUserEmail() {
		return this.userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserComing() {
		return this.userComing;
	}

	public void setUserComing(String userComing) {
		this.userComing = userComing;
	}

	public String getUserSex() {
		return this.userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getQq() {
		return this.qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getChinaName() {
		return this.chinaName;
	}

	public void setChinaName(String chinaName) {
		this.chinaName = chinaName;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getOpen() {
		return this.open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

}
