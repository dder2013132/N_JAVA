package com.yedam.classes;

/*
 * 상품코드, 상품명, 가격
 */
public class Product {

	private String productCode;
	private String productName;
	private int price;
	
	Product() {}
	Product(String pc, String pn, int price) {
		productCode = pc;
		productName = pn;
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	// 목록.
	public String showList() {
		return productCode + " " + productName + " " + price;
	}
}
