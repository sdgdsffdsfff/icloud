package com.icloud.stock.marketing.resouce;

import java.util.Iterator;
import java.util.List;

public class EmailResource {
	public Iterator<String> iterator;

	public EmailResource(List<String> list) {
		iterator = list.iterator();
	}

	// public synchronized boolean hasNext() {
	// return iterator.hasNext();
	// }

	public synchronized String next() {
		if (iterator.hasNext())
			return iterator.next();
		return null;
	}

}
