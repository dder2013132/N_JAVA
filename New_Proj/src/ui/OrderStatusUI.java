package ui;

import dao.OrderDAO;
import model.Member;
import model.Order;
import model.OrderDetail;
import util.ConsoleUtil;
import java.util.List;

/**
 * 배송 상태 확인 UI 클래스
 */
public class OrderStatusUI {
    private Member loginMember;
    private OrderDAO orderDAO;
    
    public OrderStatusUI(Member loginMember) {
        this.loginMember = loginMember;
        this.orderDAO = new OrderDAO();
    }
    
    /**
     * 배송 상태 목록을 표시하는 메소드
     */
    public void showOrderStatusList() {
        ConsoleUtil.clearScreen();
        List<Order> orderList = orderDAO.getOrdersByMemberId(loginMember.getMemberId());
        
        ConsoleUtil.printHeader("내 주문 목록");
        
        if (orderList.isEmpty()) {
            ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "주문 내역이 없습니다." + ConsoleUtil.RESET);
        } else {
            ConsoleUtil.printMessage(ConsoleUtil.CYAN + "주문번호  |  상품명                  |  주문일자     |  수량  |  가격          |  상태" + ConsoleUtil.RESET);
            ConsoleUtil.printDivider();
            
            for (Order order : orderList) {
                List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(order.getOrderId());
                
                for (OrderDetail detail : orderDetails) {
                    String productName = limitString(detail.getProductName(), 22);
                    String dateStr = formatDate(order.getOrderDate());
                    String statusStr = getColoredStatus(order.getDeliveryStatus());
                    
                    ConsoleUtil.printMessage(String.format("%07d  |  %-20s  |  %s  |  %3d  |  %,10d원  |  %s", 
                        order.getOrderId(), productName, dateStr, detail.getQuantity(), 
                        detail.getPrice() * detail.getQuantity(), statusStr));
                }
            }
        }
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("주문 번호 입력: 해당 주문 관리");
        ConsoleUtil.printMessage(ConsoleUtil.RED + "q" + ConsoleUtil.RESET + ": 돌아가기");
        ConsoleUtil.printDivider();
        
        String input = ConsoleUtil.readString("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
        
        if (input.equalsIgnoreCase("q")) {
            return; // 메인 메뉴로 돌아가기
        } else {
            try {
                int orderId = Integer.parseInt(input);
                cancelOrder(orderId);
                showOrderStatusList(); // 취소 후 목록 새로고침
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("올바른 입력이 아닙니다. 다시 선택해주세요.");
                ConsoleUtil.pressEnterToContinue();
            }
        }
    }
    
    private String getColoredStatus(String status) {
        switch (status) {
            case "PENDING":
                return ConsoleUtil.YELLOW + "보류" + ConsoleUtil.RESET;
            case "SHIPPING":
                return ConsoleUtil.BLUE + "배송중" + ConsoleUtil.RESET;
            case "COMPLETED":
                return ConsoleUtil.GREEN + "완료" + ConsoleUtil.RESET;
            default:
                return status;
        }
    }
    
    /**
     * 문자열을 지정된 길이로 제한하는 메소드
     * @param str 제한할 문자열
     * @param maxLength 최대 길이
     * @return 제한된 문자열
     */
    private String limitString(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) {
            return str;
        }
        return str.substring(0, maxLength - 3) + "...";
    }

    /**
     * 날짜를 포맷팅하는 메소드
     * @param date 포맷팅할 날짜
     * @return 포맷팅된 날짜 문자열
     */
    private String formatDate(java.sql.Date date) {
        if (date == null) return "";
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    
    /**
     * 주문 취소 메소드
     * @param orderId 취소할 주문 ID
     */
    private void cancelOrder(int orderId) {
        ConsoleUtil.clearScreen();
        // 해당 주문이 존재하는지, 본인의 주문인지 확인
        List<Order> orderList = orderDAO.getOrdersByMemberId(loginMember.getMemberId());
        boolean isMyOrder = false;
        Order targetOrder = null;
        
        for (Order order : orderList) {
            if (order.getOrderId() == orderId) {
                isMyOrder = true;
                targetOrder = order;
                break;
            }
        }
        
        if (!isMyOrder || targetOrder == null) {
            ConsoleUtil.printError("해당 주문을 찾을 수 없거나 본인의 주문이 아닙니다.");
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        ConsoleUtil.printHeader("주문 관리");
        
        // 주문 상세 정보
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "주문번호: " + ConsoleUtil.RESET + targetOrder.getOrderId());
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "주문일자: " + ConsoleUtil.RESET + formatDate(targetOrder.getOrderDate()));
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "총 금액: " + ConsoleUtil.RESET + String.format("%,d원", targetOrder.getTotalAmount()));
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "결제방법: " + ConsoleUtil.RESET + targetOrder.getPaymentMethod());
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "배송상태: " + ConsoleUtil.RESET + getColoredStatus(targetOrder.getDeliveryStatus()));
        
        List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(targetOrder.getOrderId());
        
        if (!orderDetails.isEmpty()) {
            ConsoleUtil.printDivider();
            ConsoleUtil.printMessage(ConsoleUtil.PURPLE + "▶ 주문 상품 목록" + ConsoleUtil.RESET);
            
            for (OrderDetail detail : orderDetails) {
                ConsoleUtil.printMessage(detail.getProductName() + " - " + 
                                      detail.getQuantity() + "개 x " + 
                                      String.format("%,d원", detail.getPrice()) + " = " + 
                                      String.format("%,d원", detail.getQuantity() * detail.getPrice()));
            }
        }
        
        ConsoleUtil.printDivider();
        
        // 보류 상태인 경우만 취소 가능
        if ("PENDING".equals(targetOrder.getDeliveryStatus())) {
            ConsoleUtil.printMessage("1. " + ConsoleUtil.RED + "주문 취소" + ConsoleUtil.RESET + "   " + 
                                  "2. " + ConsoleUtil.YELLOW + "돌아가기" + ConsoleUtil.RESET);
            
            int choice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
            
            switch (choice) {
                case 1:
                    // 취소 확인
                    boolean confirm = ConsoleUtil.confirm("정말 주문을 취소하시겠습니까?");
                    
                    if (!confirm) {
                        ConsoleUtil.printMessage("주문 취소가 취소되었습니다.");
                        ConsoleUtil.pressEnterToContinue();
                        return;
                    }
                    
                    // 주문 취소 처리
                    boolean success = orderDAO.cancelOrder(orderId);
                    
                    if (success) {
                        ConsoleUtil.printSuccess("주문이 취소되었습니다.");
                    } else {
                        ConsoleUtil.printError("주문 취소에 실패했습니다.");
                    }
                    ConsoleUtil.pressEnterToContinue();
                    break;
                case 2:
                    return; // 목록으로 돌아가기
                default:
                    ConsoleUtil.printError("잘못된 선택입니다.");
                    ConsoleUtil.pressEnterToContinue();
            }
        } else {
            ConsoleUtil.printMessage(ConsoleUtil.RED + "이미 배송 중이거나 완료된 주문은 취소할 수 없습니다." + ConsoleUtil.RESET);
            ConsoleUtil.printMessage("1. " + ConsoleUtil.YELLOW + "돌아가기" + ConsoleUtil.RESET);
            
            int choice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
            if (choice != 1) {
                ConsoleUtil.printError("잘못된 선택입니다.");
            }
            ConsoleUtil.pressEnterToContinue();
        }
    }
}