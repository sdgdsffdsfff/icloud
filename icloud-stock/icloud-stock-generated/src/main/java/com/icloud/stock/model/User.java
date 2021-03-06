package com.icloud.stock.model;

// Generated Jan 29, 2015 9:21:11 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	private Integer fatherId;
	private Integer level;
	private Integer promotion;
	private Integer open;
	private String redirectUrl;
	private String fatherName;
	private Set userAccesses = new HashSet(0);

	public User() {
	}

	public User(String userName, String userPassword, String userTel,
			String userEmail, String userComing, String userSex,
			Date createTime, Date lastUpdateTime, String qq, String chinaName,
			Integer fatherId, Integer level, Integer promotion, Integer open,
			String redirectUrl, String fatherName, Set userAccesses) {
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
		this.fatherId = fatherId;
		this.level = level;
		this.promotion = promotion;
		this.open = open;
		this.redirectUrl = redirectUrl;
		this.fatherName = fatherName;
		this.userAccesses = userAccesses;
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

	public Integer getFatherId() {
		return this.fatherId;
	}

	public void setFatherId(Integer fatherId) {
		this.fatherId = fatherId;
	}

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public Integer getPromotion() {
		return this.promotion;
	}

	public void setPromotion(Integer promotion) {
		this.promotion = promotion;
	}

	public Integer getOpen() {
		return this.open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public String getRedirectUrl() {
		return this.redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getFatherName() {
		return this.fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Set getUserAccesses() {
		return this.userAccesses;
	}

	public void setUserAccesses(Set userAccesses) {
		this.userAccesses = userAccesses;
	}

}
