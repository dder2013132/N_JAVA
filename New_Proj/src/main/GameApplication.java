package main;


import dao.DBConnection;
import dao.BoardDAO;
import dao.CommentDAO;
import dao.ProductDAO;
import dao.OrderDAO;
import model.Member;
import ui.LoginUI;
import ui.MainMenuUI;
import util.ConsoleUtil;

/**
 * 게임 판매 및 리뷰 애플리케이션 메인 클래스
 */
public class GameApplication {
	static int uiwidth = 80;
    
	public static void main(String[] args) {
	    ConsoleUtil.clearScreen();
	    
	    // 시퀀스 초기화 (프로그램 처음 시작할 때 한 번만 실행)
	    initializeSequences();
	    
	    // 로딩 화면 표시
	    showLoadingScreen();
	    
	    // 로그인 UI 생성
	    LoginUI loginUI = new LoginUI();
	    
	    try {
	        while (true) {
	            // 로그인 처리
	            Member loginMember = loginUI.showLoginUI();
	            
	            if (loginMember != null) {
	                // 메인 메뉴 UI 생성
	                MainMenuUI mainMenuUI = new MainMenuUI(loginMember);
	                
	                // 메인 메뉴 표시
	                boolean logout = mainMenuUI.showMainMenu();
	                
	                if (logout) {
	                    ConsoleUtil.printMessage(ConsoleUtil.YELLOW + "로그아웃 되었습니다." + ConsoleUtil.RESET);
	                    ConsoleUtil.pressEnterToContinue();
	                }
	            }
	        }
	    } finally {
	        // 프로그램 종료 시 리소스 정리
	        ConsoleUtil.closeScanner();
	        DBConnection.closeConnection();
	    }
	}

	/**
	 * 로딩 화면을 표시하는 메소드
	 */
	private static void showLoadingScreen() {
	    ConsoleUtil.clearScreen();
	    System.out.println(ConsoleUtil.CYAN + "╔" + "═".repeat(uiwidth) + "╗" + ConsoleUtil.RESET);
	    System.out.println(ConsoleUtil.CYAN + "║" + " ".repeat(uiwidth) + "║" + ConsoleUtil.RESET);
	    System.out.println(ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET + ConsoleUtil.PURPLE + ConsoleUtil.centerText("Game Shop & Review", uiwidth) + ConsoleUtil.RESET + ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET);
	    System.out.println(ConsoleUtil.CYAN + "║" + " ".repeat(uiwidth) + "║" + ConsoleUtil.RESET);
	    System.out.println(ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET + ConsoleUtil.GREEN + ConsoleUtil.centerText("Version 1.0.0", uiwidth)  + ConsoleUtil.RESET + ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET);
	    System.out.println(ConsoleUtil.CYAN + "║" + " ".repeat(uiwidth) + "║" + ConsoleUtil.RESET);
	    System.out.println(ConsoleUtil.CYAN + "╚" + "═".repeat(uiwidth) + "╝" + ConsoleUtil.RESET);
	    
	    System.out.println("\n시스템을 초기화하는 중입니다. 잠시만 기다려주세요...\n");
	    
	    char[] spinner = {'|', '/', '-', '\\'};
	    for (int i = 0; i < 20; i++) {
	        System.out.print("\r" + ConsoleUtil.CYAN + "로딩 중... " + 
	                      spinner[i % spinner.length] + " " + 
	                      (i * 5) + "%" + ConsoleUtil.RESET);
	        try {
	            Thread.sleep(100);
	        } catch (InterruptedException e) {
	            // 무시
	        }
	    }
	    
	    System.out.println("\r" + ConsoleUtil.GREEN + "로딩 완료! (100%)" + ConsoleUtil.RESET + "                    ");
	    
	    try {
	        Thread.sleep(1000);
	    } catch (InterruptedException e) {
	        // 무시
	    }
	    
	    ConsoleUtil.clearScreen();
	}
    
    /**
     * 시퀀스 초기화 메소드
     */
    private static void initializeSequences() {
        try {
            // 게시판 시퀀스 생성
            BoardDAO boardDAO = new BoardDAO();
            boardDAO.createBoardSequence();
            
            // 상품 시퀀스 생성
            ProductDAO productDAO = new ProductDAO();
            productDAO.createProductSequence();
            
            // 주문 시퀀스 생성
            OrderDAO orderDAO = new OrderDAO();
            orderDAO.createOrderSequences();
            
        } catch (Exception e) {
            System.out.println("시퀀스 초기화 오류: " + e.getMessage());
        }
    }
}