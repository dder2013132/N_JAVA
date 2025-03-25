package ui;

import dao.ProductDAO;
import model.Member;
import model.Product;
import util.ConsoleUtil;
import util.ValidationUtil;
import java.util.List;

/**
 * 상품 판매 UI 클래스
 */
public class ProductSellUI {
    private Member loginMember;
    private ProductDAO productDAO;
    
    public ProductSellUI(Member loginMember) {
        this.loginMember = loginMember;
        this.productDAO = new ProductDAO();
    }
    
    /**
     * 판매 중인 상품 목록을 표시하는 메소드
     */
    public void showSellingProducts() {
        List<Product> sellingList = productDAO.getSellingProductList(loginMember.getMemberId());
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("현재 판매중 목록");
        ConsoleUtil.printMessage("-----------------------------------------------------------------------------------");
        
        if (sellingList.isEmpty()) {
            ConsoleUtil.printMessage("판매 중인 상품이 없습니다.");
        } else {
            for (Product product : sellingList) {
                ConsoleUtil.printMessage(product.getDisplayInfo());
            }
        }
        
        ConsoleUtil.printDivider();
        ConsoleUtil.printMessage("번호 입력시 해당 상품 수정 화면 q.등록 w.돌아가기");
        
        String input = ConsoleUtil.readString("선택 >> ");
        
        if (input.equalsIgnoreCase("q")) {
            registerProduct();
            showSellingProducts(); // 등록 후 목록 새로고침
        } else if (input.equalsIgnoreCase("w")) {
            return; // 메인 메뉴로 돌아가기
        } else {
            try {
                int productId = Integer.parseInt(input);
                updateProduct(productId);
                showSellingProducts(); // 수정 후 목록 새로고침
            } catch (NumberFormatException e) {
                ConsoleUtil.printError("올바른 입력이 아닙니다. 다시 선택해주세요.");
            }
        }
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
}