package com.icloud.stock.model;

// Generated Oct 7, 2014 5:54:48 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * UserStockFocus generated by hbm2java
 */
public class UserStockFocus implements java.io.Serializable {

	private Integer id;
	private Integer userId;
	private String userName;
	private Integer stockId;
	private String stockName;
	private Date createTime;
	private String stockCode;

	public UserStockFocus() {
	}

	public UserStockFocus(Integer userId, String userName, Integer stockId,
			String stockName, Date createTime, String stockCode) {
		this.userId = userId;
		this.userName = userName;
		this.stockId = stockId;
		this.stockName = stockName;
		this.createTime = createTime;
		this.stockCode = stockCode;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getStockId() {
		return this.stockId;
	}

	public void setStockId(Integer stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return this.stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getStockCode() {
		return this.stockCode;
	}

	public void setStockCode(String stockCode) {
		this.stockCode = stockCode;
	}

}
