package com.cninfo.shtb.rule.index;

import java.util.HashMap;
import java.util.Map;

import com.cninfo.shtb.rule.model.Money;

public class Cache {
	public Map<String, Money> map = new HashMap<String, Money>(100);

	public synchronized void put(String key, Money value) {
		this.map.put(key, value);
	}

	public Money getMoney(String key) {
		return this.map.get(key);
	}

	public void clear() {
		this.map.clear();
	}
}
