package com.cninfo.shtb.rule;

public class QueryMemberModel {
	private QueryMember beibao;
	private QueryMember toubao;

	public QueryMember getBeibao() {
		return beibao;
	}

	public void setBeibao(QueryMember beibao) {
		this.beibao = beibao;
	}

	public QueryMember getToubao() {
		return toubao;
	}

	public void setToubao(QueryMember toubao) {
		this.toubao = toubao;
	}

	public static QueryMemberModel getQueryModel(int toubaoOld, int beiBaoOld,
			int toubaoMoney, int beibaoMoney) {
		QueryMemberModel model = new QueryMemberModel();
		QueryMember beibao = QueryMember.getMem(beiBaoOld, beibaoMoney);

		model.setBeibao(beibao);

		QueryMember mem = QueryMember.getMem(toubaoOld, toubaoMoney);
		model.setToubao(mem);
		return model;
	}
}
