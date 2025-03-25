package model;

/**
 * 주문 상세 정보를 담는 모델 클래스
 */
public class OrderDetail {
    private int orderDetailId;
    private int orderId;
    private int productId;
    private int quantity;
    private int price;
    
    // 추가 정보 (조인에 사용)
    private String productName;
    private String category;
    
    // 기본 생성자
    public OrderDetail() {}
    
    // 모든 필드를 포함한 생성자
    public OrderDetail(int orderDetailId, int orderId, int productId, int quantity, int price) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    
    // 주문 상세 등록을 위한 생성자
    public OrderDetail(int orderId, int productId, int quantity, int price) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }
    
    // Getter와 Setter
    public int getOrderDetailId() {
        return orderDetailId;
    }
    
    public void setOrderDetailId(int orderDetailId) {
        this.orderDetailId = orderDetailId;
    }
    
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getProductId() {
        return productId;
    }
    
    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    public int getPrice() {
        return price;
    }
    
    public void setPrice(int price) {
        this.price = price;
    }
    
    public String getProductName() {
        return productName;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    // 배송 상태 목록에 사용할 메소드
    public String getDisplayInfo(Order order) {
        return String.format("%03d | %-10s | %-20s | %03d | %-12s | %d | %,d원 | %s",
                orderId, category, productName, productId, order.getOrderDate(), 
                quantity, price * quantity, order.getDeliveryStatusString());
    }
    
    @Override
    public String toString() {
        return "상품명: " + productName + ", 수량: " + quantity + ", 가격: " + price + "원";
    }
}