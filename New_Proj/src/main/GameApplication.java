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
    
    public static void main(String[] args) {
        // 시퀀스 초기화 (프로그램 처음 시작할 때 한 번만 실행)
        initializeSequences();
        
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
                        ConsoleUtil.printMessage("로그아웃 되었습니다.");
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
     * 시퀀스 초기화 메소드
     */
    private static void initializeSequences() {
        try {
            // 게시판 시퀀스 생성
            BoardDAO boardDAO = new BoardDAO();
            boardDAO.createBoardSequence();
            
            // 댓글 시퀀스 생성
            CommentDAO commentDAO = new CommentDAO();
            commentDAO.createCommentSequence();
            
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