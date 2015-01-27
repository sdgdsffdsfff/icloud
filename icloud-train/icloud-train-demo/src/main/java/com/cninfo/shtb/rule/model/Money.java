package com.cninfo.shtb.rule.model;

public class Money {
	private int startMoney;
	private int endMoney;
	private boolean startBoolean;
	private boolean endBoolean;

	public int getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(int startMoney) {
		this.startMoney = startMoney;
	}

	public int getEndMoney() {
		return endMoney;
	}

	public void setEndMoney(int endMoney) {
		this.endMoney = endMoney;
	}

	public boolean isStartBoolean() {
		return startBoolean;
	}

	public void setStartBoolean(boolean startBoolean) {
		this.startBoolean = startBoolean;
	}

	public boolean isEndBoolean() {
		return endBoolean;
	}

	public void setEndBoolean(boolean endBoolean) {
		this.endBoolean = endBoolean;
	}

	public static Money getMoney(int start, int end, boolean startBoolean,
			boolean endBoolean) {
		Money money = new Money();
		money.setEndMoney(end);
		money.setStartMoney(start);
		money.setEndBoolean(endBoolean);
		money.setStartBoolean(startBoolean);
		return money;
	}
}
