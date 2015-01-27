package com.cninfo.shtb.rule.model;

public class Age {
	private int startAge;
	private int endAge;
	private boolean startBoolean;
	private boolean endBoolean;

	public int getStartAge() {
		return startAge;
	}

	public void setStartAge(int startAge) {
		this.startAge = startAge;
	}

	public int getEndAge() {
		return endAge;
	}

	public void setEndAge(int endAge) {
		this.endAge = endAge;
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

	public static Age getAge(int start, int end, boolean startBoolean,
			boolean endBoolean) {
		Age age = new Age();
		age.setEndAge(end);
		age.setStartAge(start);
		age.setEndBoolean(endBoolean);
		age.setStartBoolean(startBoolean);
		return age;
	}
}
