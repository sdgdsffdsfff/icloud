package com.cninfo.shtb.rule;

public class QueryMember {
	private int age;
	private int money;
	private String guanxi;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public String getGuanxi() {
		return guanxi;
	}

	public void setGuanxi(String guanxi) {
		this.guanxi = guanxi;
	}

	public static QueryMember getMem(int old, int money) {
		QueryMember member = new QueryMember();
		member.setAge(old);
		member.setMoney(money);
		return member;
	}

}
