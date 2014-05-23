package com.icloud.front.stock.entity;

import java.util.Date;

import com.icloud.front.stock.entity.StockUpdateOperation.StockDataHistoryTableStatus;

public class StockDataHistoryUpdateCriteria {
	private Date startDate;
	private Date endDate;
	private StockDataHistoryTableStatus status;

	public StockDataHistoryUpdateCriteria(Date startDate, Date endDate,
			StockDataHistoryTableStatus status) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public StockDataHistoryTableStatus getStatus() {
		return status;
	}

	public void setStatus(StockDataHistoryTableStatus status) {
		this.status = status;
	}

}
