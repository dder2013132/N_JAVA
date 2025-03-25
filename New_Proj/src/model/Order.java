package model;

import java.sql.Date;

/**
 * 주문 정보를 담는 모델 클래스
 */
public class Order {
    private int orderId;
    private String memberId;
    private Date orderDate;
    private int totalAmount;
    private String paymentMethod;
    private String deliveryStatus; // PENDING(보류), SHIPPING(배송중), COMPLETED(완료)
    private String userName; // 조인 시 사용할 필드
    
    // 기본 생성자
    public Order() {}
    
    // 모든 필드를 포함한 생성자
    public Order(int orderId, String memberId, Date orderDate, int totalAmount, 
                String paymentMethod, String deliveryStatus) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.deliveryStatus = deliveryStatus;
    }
    
    // 주문 생성을 위한 생성자
    public Order(String memberId, int totalAmount, String paymentMethod) {
        this.memberId = memberId;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.deliveryStatus = "PENDING"; // 기본값
    }
    
    // Getter와 Setter
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getMemberId() {
        return memberId;
    }
    
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
    
    public Date getOrderDate() {
        return orderDate;
    }
    
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
    
    public int getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }
    
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    public String getDeliveryStatus() {
        return deliveryStatus;
    }
    
    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
    
    public String getUserName() {
        return userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    // 배송 상태 문자열로 변환
    public String getDeliveryStatusString() {
        switch (deliveryStatus) {
            case "PENDING":
                return "보류";
            case "SHIPPING":
                return "배송중";
            case "COMPLETED":
                return "완료";
            default:
                return "알 수 없음";
        }
    }
    
    @Override
    public String toString() {
        return "주문번호: " + orderId + 
               ", 주문자: " + (userName != null ? userName : memberId) + 
               ", 주문일자: " + orderDate + 
               ", 총액: " + totalAmount + 
               "원, 배송상태: " + getDeliveryStatusString();
    }
}