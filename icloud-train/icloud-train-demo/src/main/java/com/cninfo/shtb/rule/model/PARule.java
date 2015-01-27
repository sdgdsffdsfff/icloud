package com.cninfo.shtb.rule.model;

public class PARule {
	private String name;
	private MemberRule toubao;
	private MemberRule beibao;

	public MemberRule getToubao() {
		return toubao;
	}

	public void setToubao(MemberRule toubao) {
		this.toubao = toubao;
	}

	public MemberRule getBeibao() {
		return beibao;
	}

	public void setBeibao(MemberRule beibao) {
		this.beibao = beibao;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static PARule getPARule1() {
		PARule paRule = new PARule();
		paRule.setName("pafaName1");

		MemberRule toubao = new MemberRule();
		Age age = Age.getAge(1, 3, true, false);
		toubao.setAge(age);
		toubao.setMoney(Money.getMoney(3, 8, true, false));
		paRule.setToubao(toubao);

		MemberRule beibao = new MemberRule();
		age = Age.getAge(2, 4, true, false);
		beibao.setAge(age);
		beibao.setMoney(Money.getMoney(100, 120, true, false));
		paRule.setBeibao(beibao);
		return paRule;
	}

	public static PARule getPARule2() {
		PARule paRule = new PARule();
		paRule.setName("pafaName2");
		MemberRule toubao = new MemberRule();
		Age age = Age.getAge(10, 13, true, false);
		toubao.setAge(age);
		toubao.setMoney(Money.getMoney(10, 15, true, false));
		paRule.setToubao(toubao);

		MemberRule beibao = new MemberRule();
		age = Age.getAge(22, 24, true, false);
		beibao.setAge(age);
		beibao.setMoney(Money.getMoney(5, 7, true, false));
		paRule.setBeibao(beibao);
		return paRule;
	}
}
