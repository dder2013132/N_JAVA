package ui;

import dao.ProductDAO;
import dao.OrderDAO;
import model.Member;
import model.Product;
import model.Order;
import model.OrderDetail;
import util.ConsoleUtil;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 상품 구매 UI 클래스
 */
public class ProductPurchaseUI {
    private Member loginMember;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    
    public ProductPurchaseUI(Member loginMember) {
        this.loginMember = loginMember;
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
    }
    
    /**
     * 상품 목록을 표시하는 메소드
     */
    public void showProductList() {
        List<Product> productList = productDAO.getProductList();
        
        ConsoleUtil.printDivider();
        
        if (productList.isEmpty()) {
            ConsoleUtil.printMessage("판매 중인 상품이 없습니다.");
        } else {
            for (Product product : productList) {
                ConsoleUtil.printMessage(product.getDisplayInfo());
            }
        }
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("번호입력시 게임판매 게시글로 이동 q. 돌아가기");
        
        String input = ConsoleUtil.readString("선택 >> ");
        
        if (input.equalsIgnoreCase("q")) {
            return; // 메인 메뉴로 돌아가기
        } else {
            try {
                int productId = Integer.parseInt(input);
                showProductDetail(productId);
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("올바른 입력이 아닙니다. 다시 선택해주세요.");
            }
        }
    }
    
    /**
     * 상품 상세 정보를 표시하는 메소드
     * @param productId 상품 ID
     */
    private void showProductDetail(int productId) {
        Product product = productDAO.getProductById(productId);
        
        if (product == null) {
            ConsoleUtil.printError("존재하지 않는 상품입니다.");
            return;
        }
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage(product.getDetailInfo());
        ConsoleUtil.printDivider();
        
        ConsoleUtil.printMessage("1. 구매 2. 돌아가기");
        
        int choice = ConsoleUtil.readInt("선택 >> ");
        
        switch (choice) {
            case 1:
                purchaseProduct(product);
                break;
            case 2:
                // 상품 목록으로 돌아가기
                break;
            default:
                ConsoleUtil.printError("잘못된 선택입니다.");
        }
    }
    
    /**
     * 상품 구매 프로세스를 처리하는 메소드
     * @param product 구매할 상품
     */
    private void purchaseProduct(Product product) {
        ConsoleUtil.printDivider();
        
        // 현재 날짜 표시
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(new Date());
        
        ConsoleUtil.printMessage(String.format("%s|%-70s %s",
                product.getCategory(), product.getProductName(), today));
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        ConsoleUtil.printMessage(product.getSellerName());
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        
        // 수량 입력
        int quantity;
        while (true) {
            quantity = ConsoleUtil.readInt("수량 >> ");
            
            if (quantity <= 0) {
                ConsoleUtil.printError("수량은 1개 이상이어야 합니다.");
                continue;
            }
            
            if (quantity > product.getStockQuantity()) {
                ConsoleUtil.printError("재고가 부족합니다. 현재 재고: " + product.getStockQuantity());
                continue;
            }
            
            break;
        }
        
        int totalPrice = product.getPrice() * quantity;
        
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        ConsoleUtil.printMessage("총 가격: " + totalPrice + "원");
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        
        // 구매 방법 입력
        ConsoleUtil.printMessage("구매 방법: 1. 신용카드 2. 계좌이체 3. 휴대폰결제");
        int paymentChoice = ConsoleUtil.readInt("선택 >> ");
        
        String paymentMethod;
        switch (paymentChoice) {
            case 1:
                paymentMethod = "신용카드";
                break;
            case 2:
                paymentMethod = "계좌이체";
                break;
            case 3:
                paymentMethod = "휴대폰결제";
                break;
            default:
                paymentMethod = "신용카드"; // 기본값
        }
        
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        ConsoleUtil.printMessage("본인 주소: " + loginMember.getAddress());
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        
        // 배송지 수정 여부
        boolean updateAddress = ConsoleUtil.confirm("배송지를 수정하시겠습니까?");
        
        String deliveryAddress = loginMember.getAddress();
        if (updateAddress) {
            deliveryAddress = ConsoleUtil.readString("새로운 배송지 >> ");
            if (deliveryAddress.trim().isEmpty()) {
                deliveryAddress = loginMember.getAddress();
            }
        }
        
        // 주문 취소 여부 확인
        String confirmation = ConsoleUtil.readString("주문을 진행하려면 아무 키나 누르세요. 취소하려면 'cancel'을 입력하세요. >> ");
        if (confirmation.equalsIgnoreCase("cancel")) {
            ConsoleUtil.printMessage("주문이 취소되었습니다.");
            return;
        }
        
        // 주문 처리
        Order order = new Order(loginMember.getMemberId(), totalPrice, paymentMethod);
        int orderId = orderDAO.insertOrder(order);
        
        if (orderId != -1) {
            OrderDetail orderDetail = new OrderDetail(orderId, product.getProductId(), quantity, product.getPrice());
            boolean detailSuccess = orderDAO.insertOrderDetail(orderDetail);
            
            if (detailSuccess) {
                // 재고 감소
                boolean stockUpdated = productDAO.decreaseStock(product.getProductId(), quantity);
                
                if (stockUpdated) {
                    ConsoleUtil.printSuccess("주문이 완료되었습니다. 주문번호: " + orderId);
                } else {
                    ConsoleUtil.printError("재고 업데이트에 실패했습니다.");
                    // 롤백 처리가 필요할 수 있음
                }
            } else {
                ConsoleUtil.printError("주문 상세 정보 등록에 실패했습니다.");
            }
        } else {
            ConsoleUtil.printError("주문 등록에 실패했습니다.");
        }
    }
}