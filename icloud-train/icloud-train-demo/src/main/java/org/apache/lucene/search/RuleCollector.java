package org.apache.lucene.search;

import com.cninfo.shtb.rule.QueryMemberModel;
import com.cninfo.shtb.rule.index.Cache;
import com.cninfo.shtb.rule.model.Money;

public class RuleCollector extends ExtendTopScoreDocCollector {
	private Cache cache = null;
	private QueryMemberModel model = null;

	public RuleCollector(int numHits, Cache cache, QueryMemberModel model) {
		this(numHits);
		this.cache = cache;
		this.model = model;
	}

	public RuleCollector(int numHits) {
		super(numHits);
	}

	@Override
	protected boolean isContains(int docId) {
		Money beibaoModel = cache.getMoney("beibao" + "_" + docId);
		Money toubaoModel = cache.getMoney("toubao" + "_" + docId);
		int beibaoMoney = model.getBeibao().getMoney();
		int toubaoMoney = model.getToubao().getMoney();
		if (isInMoneyModel(beibaoModel, beibaoMoney)
				&& isInMoneyModel(toubaoModel, toubaoMoney)) {
			return true;
		}
		return false;
	}

	public boolean isInMoneyModel(Money model, int money) {
		if (model.getStartMoney() < money && model.getEndMoney() > money) {
			return true;
		}
		return false;
	}

	@Override
	protected float extendScore(int docid, float score) {
		return score;
	}

}
