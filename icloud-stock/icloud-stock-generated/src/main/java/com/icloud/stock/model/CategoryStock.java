package com.icloud.stock.model;

// Generated Jan 29, 2015 9:21:11 PM by Hibernate Tools 3.4.0.CR1

/**
 * CategoryStock generated by hbm2java
 */
public class CategoryStock implements java.io.Serializable {

	private Integer id;
	private Stock stock;
	private Category category;

	public CategoryStock() {
	}

	public CategoryStock(Stock stock, Category category) {
		this.stock = stock;
		this.category = category;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Stock getStock() {
		return this.stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
