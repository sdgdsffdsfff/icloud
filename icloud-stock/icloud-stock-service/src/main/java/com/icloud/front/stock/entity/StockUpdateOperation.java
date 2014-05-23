package com.icloud.front.stock.entity;

public class StockUpdateOperation {

	public static enum StockDataHistoryTableStatus {
		NO("no data"), UPDATE("update"), OK("ok");
		private String status;

		StockDataHistoryTableStatus(String s) {
			this.status = s;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

	}
}
