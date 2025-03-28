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
            ConsoleUtil.clearScreen();
            ConsoleUtil.printHeader("게임 판매 & 리뷰 시스템");
            ConsoleUtil.printMessage(ConsoleUtil.GREEN + loginMember.getMemberId() + 
                                  ConsoleUtil.RESET + " 님, 환영합니다! 마지막 로그인 날짜 : " + ConsoleUtil.CYAN + loginMember.getLastLoginDate() + ConsoleUtil.RESET);
            ConsoleUtil.printDivider();
            ConsoleUtil.printMessage("1. " + ConsoleUtil.YELLOW + "◈ 게시판" + ConsoleUtil.RESET + "       - 게시글 보기 및 작성");
            ConsoleUtil.printMessage("2. " + ConsoleUtil.BLUE + "◈ 게임 구매" + ConsoleUtil.RESET + "     - 게임 상품 둘러보기 및 구매");
            ConsoleUtil.printMessage("3. " + ConsoleUtil.PURPLE + "◈ 게임 판매" + ConsoleUtil.RESET + "     - 내 상품 관리 및 등록");
            ConsoleUtil.printMessage("4. " + ConsoleUtil.CYAN + "◈ 배송 상태 확인" + ConsoleUtil.RESET + " - 주문 상태 확인 및 관리");
            ConsoleUtil.printMessage("5. " + ConsoleUtil.RED + "◈ 로그아웃" + ConsoleUtil.RESET + "       - 시스템에서 로그아웃");
            ConsoleUtil.printDivider();
            
            int choice = ConsoleUtil.readInt("선택 " + ConsoleUtil.CYAN + "▶ " + ConsoleUtil.RESET);
            
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
                    ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "로그아웃 되었습니다. 안녕히 가세요!" + ConsoleUtil.RESET);
                    ConsoleUtil.pressEnterToContinue();
                    return true; // 로그아웃
                default:
                    ConsoleUtil.printError("잘못된 선택입니다. 다시 선택해주세요.");
                    ConsoleUtil.pressEnterToContinue();
            }
        }
    }
}