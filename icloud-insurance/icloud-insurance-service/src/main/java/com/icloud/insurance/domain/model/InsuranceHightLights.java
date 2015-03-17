package com.icloud.insurance.domain.model;

import java.util.List;

public class InsuranceHightLights {
	private List<String> highlights;

	public List<String> getHighlights() {
		return highlights;
	}

	public void setHighlights(List<String> highlights) {
		this.highlights = highlights;
	}

	@Override
	public String toString() {
		return "InsuranceHightLights [highlights=" + highlights + "]";
	}

}
