package com.icloud.stock.search;

public class PinyinBean {

	public int docId; // required
	public String iataCode; // required
	public String pinyin; // required
	public String indexName; // required
	public boolean isMatch; // required
	public double statWeight; // required
	public int metaType; // required

	public int getDocId() {
		return docId;
	}

	public void setDocId(int docId) {
		this.docId = docId;
	}

	public String getIataCode() {
		return iataCode;
	}

	public void setIataCode(String iataCode) {
		this.iataCode = iataCode;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}

	public boolean isMatch() {
		return isMatch;
	}

	public void setMatch(boolean isMatch) {
		this.isMatch = isMatch;
	}

	public double getStatWeight() {
		return statWeight;
	}

	public void setStatWeight(double statWeight) {
		this.statWeight = statWeight;
	}

	public int getMetaType() {
		return metaType;
	}

	public void setMetaType(int metaType) {
		this.metaType = metaType;
	}

}
