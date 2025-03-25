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
        List<Order> orderList = orderDAO.getOrdersByMemberId(loginMember.getMemberId());
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("현재 배송중 목록");
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        
        if (orderList.isEmpty()) {
            ConsoleUtil.printMessage("주문 내역이 없습니다.");
        } else {
            for (Order order : orderList) {
                List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(order.getOrderId());
                
                for (OrderDetail detail : orderDetails) {
                    ConsoleUtil.printMessage(detail.getDisplayInfo(order));
                }
            }
        }
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("번호 입력시 해당 배송중인 상품이 '보류'상태 인 경우 취소 가능 q.돌아가기");
        
        String input = ConsoleUtil.readString("선택 >> ");
        
        if (input.equalsIgnoreCase("q")) {
            return; // 메인 메뉴로 돌아가기
        } else {
            try {
                int orderId = Integer.parseInt(input);
                cancelOrder(orderId);
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("올바른 입력이 아닙니다. 다시 선택해주세요.");
            }
        }
    }
    
    /**
     * 주문 취소 메소드
     * @param orderId 취소할 주문 ID
     */
    private void cancelOrder(int orderId) {
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
            return;
        }
        
        // 보류 상태인지 확인
        if (!"PENDING".equals(targetOrder.getDeliveryStatus())) {
            ConsoleUtil.printError("이미 배송 중이거나 완료된 주문은 취소할 수 없습니다.");
            return;
        }
        
        // 취소 확인
        boolean confirm = ConsoleUtil.confirm("정말 주문을 취소하시겠습니까?");
        
        if (!confirm) {
            ConsoleUtil.printMessage("주문 취소가 취소되었습니다.");
            return;
        }
        
        // 주문 취소 처리
        boolean success = orderDAO.cancelOrder(orderId);
        
        if (success) {
            ConsoleUtil.printSuccess("주문이 취소되었습니다.");
        } else {
            ConsoleUtil.printError("주문 취소에 실패했습니다.");
        }
    }
}