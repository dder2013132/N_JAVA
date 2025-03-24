package com.yedam.classes;

import java.io.Serializable;

/*
 * 상품코드 (중복X)
 * 상품명
 * 가격
 */
public class Product implements Serializable {
    private String productCode;
    private String productName;
    private int price;
    
    public Product() { }
    public Product(String pc, String pn, int price) {
        productCode = pc;
        productName = pn;
        this.price = price;
    }
    
    public String showList() {
        return productCode + " " + productName + " " + price;
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
}
