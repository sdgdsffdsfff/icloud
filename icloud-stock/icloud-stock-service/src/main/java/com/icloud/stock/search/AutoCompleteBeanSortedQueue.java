package com.icloud.stock.search;

import com.icloud.search.autocomplete.search.BeanSortedQueue;

public class AutoCompleteBeanSortedQueue extends BeanSortedQueue<AutoCompleteBean> {

	public AutoCompleteBeanSortedQueue(int size) {
		super(size);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean lessThan(AutoCompleteBean a, AutoCompleteBean b) {
		if (a.getBean() != null && b.getBean() != null)
			return a.getBean().getStatWeight() < b.getBean().getStatWeight();
		return false;
	}

}
