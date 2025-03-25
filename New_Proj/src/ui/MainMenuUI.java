package ui;

import model.Member;
import util.ConsoleUtil;

/**
 * 메인 메뉴 UI 클래스
 */
public class MainMenuUI {
    private Member loginMember;
    private BoardUI boardUI;
    private ProductPurchaseUI purchaseUI;
    private ProductSellUI sellUI;
    private OrderStatusUI orderStatusUI;
    
    public MainMenuUI(Member loginMember) {
        this.loginMember = loginMember;
        this.boardUI = new BoardUI(loginMember);
        this.purchaseUI = new ProductPurchaseUI(loginMember);
        this.sellUI = new ProductSellUI(loginMember);
        this.orderStatusUI = new OrderStatusUI(loginMember);
    }
    
    /**
     * 메인 메뉴를 표시하고 처리하는 메소드
     * @return 로그아웃 여부
     */
    public boolean showMainMenu() {
        while (true) {
            ConsoleUtil.printHeader("게임 판매&리뷰");
            System.out.println("1. 게시판  2. 게임 구매 3. 게임 판매 4. 배송 상태 확인 5. 로그아웃");
            ConsoleUtil.printDivider();
            
            int choice = ConsoleUtil.readInt("선택 >> ");
            
            switch (choice) {
                case 1:
                    boardUI.showBoardList();
                    break;
                case 2:
                    purchaseUI.showProductList();
                    break;
                case 3:
                    sellUI.showSellingProducts();
                    break;
                case 4:
                    orderStatusUI.showOrderStatusList();
                    break;
                case 5:
                    return true; // 로그아웃
                default:
                    ConsoleUtil.printError("잘못된 선택입니다. 다시 선택해주세요.");
            }
        }
    }
}