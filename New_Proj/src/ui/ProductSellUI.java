package ui;

import dao.OrderDAO;
import dao.ProductDAO;
import model.Member;
import model.Order;
import model.OrderDetail;
import model.Product;
import util.ConsoleUtil;
import util.ValidationUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 상품 판매 UI 클래스
 */
public class ProductSellUI {
    private Member loginMember;
    private ProductDAO productDAO;
    private OrderDAO orderDAO;
    
    public ProductSellUI(Member loginMember) {
        this.loginMember = loginMember;
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
    }
    
    /**
     * 판매 중인 상품 목록을 표시하는 메소드
     */
    public void showSellingProducts() {
        ConsoleUtil.clearScreen();
        List<Product> sellingList = productDAO.getSellingProductList(loginMember.getMemberId());
        
        // 재고 부족 상품 확인
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : sellingList) {
            if (product.getStockQuantity() <= 0) {
                lowStockProducts.add(product);
            }
        }
        
        ConsoleUtil.printHeader("내 판매 상품 관리");
        
        // 재고 부족 알림 표시
        if (!lowStockProducts.isEmpty()) {
            ConsoleUtil.printMessage(ConsoleUtil.RED + "⚠️ 재고 부족 알림 ⚠️" + ConsoleUtil.RESET);
            for (Product product : lowStockProducts) {
                ConsoleUtil.printMessage(ConsoleUtil.RED + "- " + product.getProductName() + 
                                      ": 현재 재고 " + product.getStockQuantity() + 
                                      "개, 주문 요청 " + product.getRequestCount() + "개" + ConsoleUtil.RESET);
            }
            ConsoleUtil.printDivider();
        }
        
        if (sellingList.isEmpty()) {
            ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "판매 중인 상품이 없습니다." + ConsoleUtil.RESET);
        } else {
            ConsoleUtil.printMessage(ConsoleUtil.CYAN + "번호  |  장르        |  게임명                    |  가격          |  재고  |  주문수" + ConsoleUtil.RESET);
            ConsoleUtil.printDivider();
            
            for (Product product : sellingList) {
                String gameName = limitString(product.getProductName(), 20);
                String category = limitString(product.getCategory(), 10);
                
                // 재고 상태에 따라 색상 적용
                String stockInfo;
                if (product.getStockQuantity() <= 0) {
                    stockInfo = ConsoleUtil.RED + product.getStockQuantity() + ConsoleUtil.RESET;
                } else if (product.getStockQuantity() < 5) {
                    stockInfo = ConsoleUtil.YELLOW + product.getStockQuantity() + ConsoleUtil.RESET;
                } else {
                    stockInfo = ConsoleUtil.GREEN + product.getStockQuantity() + ConsoleUtil.RESET;
                }
                
                ConsoleUtil.printMessage(String.format("%03d   |  %-10s  |  %-20s  |  %,10d원  |  %s  |  %d", 
                    product.getProductId(), category, gameName, product.getPrice(), stockInfo, product.getRequestCount()));
            }
        }
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("상품 번호 입력: 해당 상품 관리");
        ConsoleUtil.printMessage(ConsoleUtil.GREEN + "q" + ConsoleUtil.RESET + ": 상품 등록   " + 
                              ConsoleUtil.RED + "w" + ConsoleUtil.RESET + ": 돌아가기");
        ConsoleUtil.printDivider();
        
        String input = ConsoleUtil.readString("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
        
        if (input.equalsIgnoreCase("q")) {
            registerProduct();
            showSellingProducts(); // 등록 후 목록 새로고침
        } else if (input.equalsIgnoreCase("w")) {
            return; // 메인 메뉴로 돌아가기
        } else {
            try {
                int productId = Integer.parseInt(input);
                manageProduct(productId);
                showSellingProducts(); // 수정/삭제 후 목록 새로고침
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("올바른 입력이 아닙니다. 다시 선택해주세요.");
                ConsoleUtil.pressEnterToContinue();
            }
        }
    }

    /**
     * 상품 관리 메뉴를 표시하는 메소드
     * @param productId 관리할 상품 ID
     */
    private void manageProduct(int productId) {
        ConsoleUtil.clearScreen();
        Product product = productDAO.getProductById(productId);
        
        if (product == null) {
            ConsoleUtil.printError("존재하지 않는 상품입니다.");
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        // 본인 상품인지 확인
        if (!product.getSellerId().equals(loginMember.getMemberId())) {
            ConsoleUtil.printError("자신이 등록한 상품만 관리할 수 있습니다.");
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        ConsoleUtil.printHeader("상품 관리");
        
        // 상품 정보
        ConsoleUtil.printMessage(ConsoleUtil.PURPLE + "▶ " + product.getProductName() + ConsoleUtil.RESET);
        
        // 재고 상태 표시
        if (product.getStockQuantity() <= 0) {
            ConsoleUtil.printMessage(ConsoleUtil.RED + "⚠️ 재고 부족!" + ConsoleUtil.RESET + " 현재 재고: " + 
                                  ConsoleUtil.RED + product.getStockQuantity() + "개" + ConsoleUtil.RESET);
            ConsoleUtil.printMessage("추가 주문 요청: " + ConsoleUtil.YELLOW + 
                                  Math.abs(product.getStockQuantity()) + "개" + ConsoleUtil.RESET);
        } else if (product.getStockQuantity() < 5) {
            ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "⚠️ 재고 소진 임박!" + ConsoleUtil.RESET + " 현재 재고: " + 
                                  ConsoleUtil.YELLOW + product.getStockQuantity() + "개" + ConsoleUtil.RESET);
        } else {
            ConsoleUtil.printMessage("현재 재고: " + ConsoleUtil.GREEN + product.getStockQuantity() + "개" + ConsoleUtil.RESET);
        }
        
        ConsoleUtil.printMessage("판매 가격: " + ConsoleUtil.BLUE + String.format("%,d원", product.getPrice()) + ConsoleUtil.RESET);
        ConsoleUtil.printMessage("총 주문 요청: " + ConsoleUtil.PURPLE + product.getRequestCount() + "개" + ConsoleUtil.RESET);
        
        ConsoleUtil.printDivider();
        
        // 재고 부족 시 추가 메뉴 제공
        if (product.getStockQuantity() <= 0) {
            ConsoleUtil.printMessage("1. " + ConsoleUtil.RED + "삭제" + ConsoleUtil.RESET + "   " + 
                                  "2. " + ConsoleUtil.BLUE + "수정" + ConsoleUtil.RESET + "   " + 
                                  "3. " + ConsoleUtil.PURPLE + "배송 상태 관리" + ConsoleUtil.RESET + "   " + 
                                  "4. " + ConsoleUtil.GREEN + "재고 보충" + ConsoleUtil.RESET + "   " + 
                                  "5. " + ConsoleUtil.YELLOW + "돌아가기" + ConsoleUtil.RESET);
        } else {
            ConsoleUtil.printMessage("1. " + ConsoleUtil.RED + "삭제" + ConsoleUtil.RESET + "   " + 
                                  "2. " + ConsoleUtil.BLUE + "수정" + ConsoleUtil.RESET + "   " + 
                                  "3. " + ConsoleUtil.PURPLE + "배송 상태 관리" + ConsoleUtil.RESET + "   " + 
                                  "4. " + ConsoleUtil.YELLOW + "돌아가기" + ConsoleUtil.RESET);
        }
        
        int choice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
        
        if (product.getStockQuantity() <= 0) {
            // 재고 부족 시 메뉴
            switch (choice) {
                case 1:
                    deleteProduct(product);
                    break;
                case 2:
                    updateProduct(productId);
                    break;
                case 3:
                    manageDeliveryStatus(product);
                    break;
                case 4:
                    replenishStock(product);
                    break;
                case 5:
                    return; // 상품 목록으로 돌아가기
                default:
                    ConsoleUtil.printError("잘못된 선택입니다.");
                    ConsoleUtil.pressEnterToContinue();
            }
        } else {
            // 재고 정상 시 메뉴
            switch (choice) {
                case 1:
                    deleteProduct(product);
                    break;
                case 2:
                    updateProduct(productId);
                    break;
                case 3:
                    manageDeliveryStatus(product);
                    break;
                case 4:
                    return; // 상품 목록으로 돌아가기
                default:
                    ConsoleUtil.printError("잘못된 선택입니다.");
                    ConsoleUtil.pressEnterToContinue();
            }
        }
    }

    /**
     * 재고 보충 메소드
     * @param product 재고를 보충할 상품
     */
    private void replenishStock(Product product) {
        ConsoleUtil.clearScreen();
        ConsoleUtil.printHeader("재고 보충");
        
        ConsoleUtil.printMessage(ConsoleUtil.PURPLE + "▶ " + product.getProductName() + ConsoleUtil.RESET);
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "현재 재고: " + ConsoleUtil.RESET + 
                              (product.getStockQuantity() <= 0 ? 
                               ConsoleUtil.RED + product.getStockQuantity() + "개" + ConsoleUtil.RESET : 
                               product.getStockQuantity() + "개"));
        
        if (product.getStockQuantity() < 0) {
            ConsoleUtil.printMessage(ConsoleUtil.RED + "⚠️ 재고 부족!" + ConsoleUtil.RESET + " 최소 " + 
                                  ConsoleUtil.YELLOW + Math.abs(product.getStockQuantity()) + "개" + 
                                  ConsoleUtil.RESET + " 이상의 재고 보충이 필요합니다.");
        }
        
        ConsoleUtil.printDivider();
        
        int additionalStock;
        while (true) {
            try {
                additionalStock = ConsoleUtil.readInt("추가할 재고 수량 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
                
                if (additionalStock <= 0) {
                    ConsoleUtil.printError("1개 이상의 수량을 입력해주세요.");
                    continue;
                }
                
                break;
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("숫자를 입력해주세요.");
            }
        }
        
        ConsoleUtil.printDivider();
        
        // 재고 업데이트
        int newStock = product.getStockQuantity() + additionalStock;
        
        ConsoleUtil.printMessage("현재 재고: " + 
                              (product.getStockQuantity() <= 0 ? 
                               ConsoleUtil.RED + product.getStockQuantity() + "개" + ConsoleUtil.RESET : 
                               product.getStockQuantity() + "개"));
        ConsoleUtil.printMessage("추가 수량: " + ConsoleUtil.GREEN + "+" + additionalStock + "개" + ConsoleUtil.RESET);
        ConsoleUtil.printMessage("변경 후 재고: " + ConsoleUtil.BLUE + newStock + "개" + ConsoleUtil.RESET);
        
        boolean confirm = ConsoleUtil.confirm("재고를 보충하시겠습니까?");
        
        if (!confirm) {
            ConsoleUtil.printMessage("재고 보충이 취소되었습니다.");
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        product.setStockQuantity(newStock);
        boolean success = productDAO.updateProduct(product);
        
        if (success) {
            ConsoleUtil.printSuccess("재고가 성공적으로 보충되었습니다. 현재 재고: " + newStock + "개");
        } else {
            ConsoleUtil.printError("재고 보충에 실패했습니다.");
        }
        
        ConsoleUtil.pressEnterToContinue();
    }

    /**
     * 상품 삭제 메소드
     * @param product 삭제할 상품
     */
    private void deleteProduct(Product product) {
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("[ " + product.getProductName() + " ] 상품을 삭제합니다.");
        
        boolean confirm = ConsoleUtil.confirm("정말 삭제하시겠습니까? (삭제 후 복구할 수 없습니다)");
        
        if (!confirm) {
            ConsoleUtil.printMessage("상품 삭제가 취소되었습니다.");
            return;
        }
        
        boolean success = productDAO.deleteProduct(product.getProductId());
        
        if (success) {
            ConsoleUtil.printSuccess("상품이 삭제되었습니다.");
        } else {
            ConsoleUtil.printError("상품 삭제에 실패했습니다.");
        }
    }
    
    /**
     * 상품의 배송상태를 관리하는 메소드
     * @param product 관리할 상품
     */
    
    private void manageDeliveryStatus(Product product) {
        ConsoleUtil.clearScreen();
        // 해당 상품에 대한 주문 목록 조회
        List<Order> orderList = orderDAO.getOrdersByProductId(product.getProductId());
        
        if (orderList.isEmpty()) {
            ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "해당 상품에 대한 주문 내역이 없습니다." + ConsoleUtil.RESET);
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        ConsoleUtil.printHeader("배송 상태 관리");
        
        ConsoleUtil.printMessage(ConsoleUtil.PURPLE + "▶ " + product.getProductName() + ConsoleUtil.RESET + " 주문 목록");
        ConsoleUtil.printDivider();
        
        ConsoleUtil.printMessage(ConsoleUtil.CYAN + "번호  |  주문번호   |  주문자     |  주문일자     |  수량  |  상태" + ConsoleUtil.RESET);
        ConsoleUtil.printDivider();
        
        int index = 1;
        for (Order order : orderList) {
            List<OrderDetail> orderDetails = orderDAO.getOrderDetailsByOrderId(order.getOrderId());
            for (OrderDetail detail : orderDetails) {
                if (detail.getProductId() == product.getProductId()) {
                    String statusStr = getColoredStatus(order.getDeliveryStatus());
                    String dateStr = formatDate(order.getOrderDate());
                    
                    ConsoleUtil.printMessage(String.format("%2d    |  %09d  |  %-8s  |  %s  |  %3d  |  %s", 
                        index++, order.getOrderId(), order.getUserName(), dateStr, 
                        detail.getQuantity(), statusStr));
                }
            }
        }
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("번호 입력: 해당 주문 상태 변경");
        ConsoleUtil.printMessage(ConsoleUtil.RED + "0" + ConsoleUtil.RESET + ": 돌아가기");
        ConsoleUtil.printDivider();
        
        int orderChoice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
        
        if (orderChoice == 0) {
            return;
        }
        
        if (orderChoice < 1 || orderChoice > orderList.size()) {
            ConsoleUtil.printError("잘못된 선택입니다.");
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        Order selectedOrder = orderList.get(orderChoice - 1);
        
        ConsoleUtil.clearScreen();
        ConsoleUtil.printHeader("배송 상태 변경");
        
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "주문번호: " + ConsoleUtil.RESET + selectedOrder.getOrderId());
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "주문자: " + ConsoleUtil.RESET + selectedOrder.getUserName());
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "주문일자: " + ConsoleUtil.RESET + formatDate(selectedOrder.getOrderDate()));
        ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "현재 상태: " + ConsoleUtil.RESET + getColoredStatus(selectedOrder.getDeliveryStatus()));
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage(ConsoleUtil.PURPLE + "▶ 변경할 상태 선택" + ConsoleUtil.RESET);
        ConsoleUtil.printMessage("1. " + ConsoleUtil.YELLOW + "보류" + ConsoleUtil.RESET + " - 결제 완료 및 배송 대기 중");
        ConsoleUtil.printMessage("2. " + ConsoleUtil.BLUE + "배송중" + ConsoleUtil.RESET + " - 상품 발송 완료 및 배송 진행 중");
        ConsoleUtil.printMessage("3. " + ConsoleUtil.GREEN + "완료" + ConsoleUtil.RESET + " - 배송 완료 및 구매 확정");
        ConsoleUtil.printDivider();
        
        int statusChoice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
        
        String newStatus;
        switch (statusChoice) {
            case 1:
                newStatus = "PENDING";
                break;
            case 2:
                newStatus = "SHIPPING";
                break;
            case 3:
                newStatus = "COMPLETED";
                break;
            default:
                ConsoleUtil.printError("잘못된 선택입니다.");
                ConsoleUtil.pressEnterToContinue();
                return;
        }
        
        boolean confirm = ConsoleUtil.confirm("배송 상태를 '" + getColoredStatus(newStatus) + "'(으)로 변경하시겠습니까?");
        
        if (!confirm) {
            ConsoleUtil.printMessage("배송 상태 변경이 취소되었습니다.");
            ConsoleUtil.pressEnterToContinue();
            return;
        }
        
        boolean success = orderDAO.updateDeliveryStatus(selectedOrder.getOrderId(), newStatus);
        
        if (success) {
            ConsoleUtil.printSuccess("배송 상태가 변경되었습니다.");
        } else {
            ConsoleUtil.printError("배송 상태 변경에 실패했습니다.");
        }
        
        ConsoleUtil.pressEnterToContinue();
    }
    
    
    /**
     * 상품 등록 메소드
     */
    private void registerProduct() {
        ConsoleUtil.printDivider();
        
        // 장르(카테고리) 입력
        String category = ConsoleUtil.readString("장르>> ");
        if (category.trim().isEmpty()) {
            ConsoleUtil.printError("장르를 입력해주세요.");
            return;
        }
        
        // 게임명(제목) 입력
        String productName = ConsoleUtil.readString("제목>> ");
        if (productName.trim().isEmpty()) {
            ConsoleUtil.printError("제목을 입력해주세요.");
            return;
        }
        
        // 설명 입력
        String description = ConsoleUtil.readString("설명>> ");
        if (description.trim().isEmpty()) {
            ConsoleUtil.printError("설명을 입력해주세요.");
            return;
        }
        
        // 가격 입력
        int price;
        while (true) {
            try {
                price = ConsoleUtil.readInt("가격>> ");
                
                if (!ValidationUtil.isValidPrice(price)) {
                    ConsoleUtil.printError("가격은 0보다 커야 합니다.");
                    continue;
                }
                
                break;
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("숫자를 입력해주세요.");
            }
        }
        
        // 재고 입력
        int stockQuantity;
        while (true) {
            try {
                stockQuantity = ConsoleUtil.readInt("재고 수량>> ");
                
                if (!ValidationUtil.isValidQuantity(stockQuantity)) {
                    ConsoleUtil.printError("재고는 0보다 커야 합니다.");
                    continue;
                }
                
                break;
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("숫자를 입력해주세요.");
            }
        }
        
        ConsoleUtil.printDivider();
        
        // 취소 여부 확인
        boolean confirm = ConsoleUtil.confirm("상품을 등록하시겠습니까?");
        
        if (!confirm) {
            ConsoleUtil.printMessage("상품 등록이 취소되었습니다.");
            return;
        }
        
        // Product 객체 생성 및 등록
        Product product = new Product(productName, price, stockQuantity, description, category, loginMember.getMemberId(), loginMember.getMemberId());
        boolean success = productDAO.insertProduct(product);
        
        if (success) {
            ConsoleUtil.printSuccess("상품이 등록되었습니다.");
        } else {
            ConsoleUtil.printError("상품 등록에 실패했습니다.");
        }
    }
    
    /**
     * 상품 수정 메소드
     * @param productId 수정할 상품 ID
     */
    private void updateProduct(int productId) {
        Product product = productDAO.getProductById(productId);
        
        if (product == null) {
            ConsoleUtil.printError("존재하지 않는 상품입니다.");
            return;
        }
        
        // 본인 상품인지 확인
        if (!product.getSellerId().equals(loginMember.getMemberId())) {
            ConsoleUtil.printError("자신이 등록한 상품만 수정할 수 있습니다.");
            return;
        }
        
        ConsoleUtil.printDivider();
        
        // 장르(카테고리) 수정
        String category = ConsoleUtil.readString("장르 (" + product.getCategory() + ") >> ");
        if (!category.trim().isEmpty()) {
            product.setCategory(category);
        }
        
        // 게임명(제목) 수정
        String productName = ConsoleUtil.readString("제목 (" + product.getProductName() + ") >> ");
        if (!productName.trim().isEmpty()) {
            product.setProductName(productName);
        }
        
        // 설명 수정
        String description = ConsoleUtil.readString("설명 >> ");
        if (!description.trim().isEmpty()) {
            product.setDescription(description);
        }
        
        // 가격 수정
        String priceStr = ConsoleUtil.readString("가격 (" + product.getPrice() + ") >> ");
        if (!priceStr.trim().isEmpty()) {
            try {
                int price = Integer.parseInt(priceStr);
                if (ValidationUtil.isValidPrice(price)) {
                    product.setPrice(price);
                } else {
                    ConsoleUtil.printError("가격은 0보다 커야 합니다. 기존 가격을 유지합니다.");
                }
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("유효한 숫자가 아닙니다. 기존 가격을 유지합니다.");
            }
        }
        
        // 재고 수정
        String stockStr = ConsoleUtil.readString("재고 수량 (" + product.getStockQuantity() + ") >> ");
        if (!stockStr.trim().isEmpty()) {
            try {
                int stockQuantity = Integer.parseInt(stockStr);
                if (ValidationUtil.isValidQuantity(stockQuantity)) {
                    product.setStockQuantity(stockQuantity);
                } else {
                    ConsoleUtil.printError("재고는 0보다 커야 합니다. 기존 재고를 유지합니다.");
                }
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("유효한 숫자가 아닙니다. 기존 재고를 유지합니다.");
            }
        }
        
        ConsoleUtil.printDivider();
        
        // 취소 여부 확인
        boolean confirm = ConsoleUtil.confirm("상품을 수정하시겠습니까?");
        
        if (!confirm) {
            ConsoleUtil.printMessage("상품 수정이 취소되었습니다.");
            return;
        }
        
        boolean success = productDAO.updateProduct(product);
        
        if (success) {
            ConsoleUtil.printSuccess("상품이 수정되었습니다.");
        } else {
            ConsoleUtil.printError("상품 수정에 실패했습니다.");
        }
    }
    
    /**
     * 배송 상태 코드를 색상이 적용된 문자열로 변환
     * @param status 배송 상태 코드
     * @return 색상이 적용된 배송 상태
     */
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
}