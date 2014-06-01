package com.icloud.stock.search;

import org.apache.lucene.document.Document;

public class AutoCompleteBean {
	PinyinBean bean;
	Document doc;

	public AutoCompleteBean(PinyinBean bean, Document doc) {
		super();
		this.bean = bean;
		this.doc = doc;
	}

	public PinyinBean getBean() {
		return bean;
	}

	public void setBean(PinyinBean bean) {
		this.bean = bean;
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

}
