package model;

import java.sql.Date;

/**
 * 상품 정보를 담는 모델 클래스
 */
public class Product {
    private int productId;
    private String productName;
    private int price;
    private int stockQuantity;
    private String description;
    private String category; // 장르
    private Date regDate;
    private String sellerId; // DB 테이블에는 없지만 판매자 ID 저장을 위해 추가
    private String sellerName; // 조인 시 사용할 필드
    private int requestCount; // 판매 요청 수를 위한 필드
    
    // 기본 생성자
    public Product() {}
    
    // 모든 필드를 포함한 생성자
    public Product(int productId, String productName, int price, int stockQuantity, 
                  String description, String category, Date regDate, String sellerId, String sellerName) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.category = category;
        this.regDate = regDate;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
    }
    
    // 상품 등록을 위한 생성자
    public Product(String productName, int price, int stockQuantity, 
                  String description, String category, String sellerId, String sellerName) {
        this.productName = productName;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.category = category;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
    }
    
    // Getter와 Setter
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
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
    
    public int getStockQuantity() {
        return stockQuantity;
    }
    
    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Date getRegDate() {
        return regDate;
    }
    
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }
    
    public String getSellerId() {
        return sellerId;
    }
    
    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
    
    public String getSellerName() {
        return sellerName;
    }
    
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
    
    public int getRequestCount() {
        return requestCount;
    }
    
    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }
    
    // 상품 목록에 사용할 메소드
    public String getDisplayInfo() {
        // 번호, 장르, 게임명, 유저명, 날짜, 가격, 판매요청 수 형식
        return String.format("%03d | %-10s | %-20s | %-10s | %-12s | %,d원 | %d개 | %d회", 
                productId, category, productName, sellerName, regDate, price, stockQuantity ,requestCount);
    }
    
    // 상품 상세 정보에 사용할 메소드
    public String getDetailInfo() {
        return String.format("%s|%-70s\n-----------------------------------------------------------------------------------\n%s\n-----------------------------------------------------------------------------------\n%s\n-----------------------------------------------------------------------------------\n가격: %,d원",
                category, productName, sellerName, description, price);
    }
    
    @Override
    public String toString() {
        return "상품명: " + productName + ", 가격: " + price + "원, 카테고리: " + category;
    }
}