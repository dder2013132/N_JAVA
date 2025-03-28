package util;

import dao.DBConnection;
import model.Member;
import ui.LoginUI;
import ui.MainMenuUI;

/**
 * 애플리케이션 시작 및 실행을 담당하는 클래스
 */
public class ApplicationStarter {
    
    /**
     * 애플리케이션 시작 메소드
     */
    public static void start() {
        ConsoleUtil.clearScreen();
        
        try {
            // 시퀀스 초기화
            SystemInitializer.initializeSequences();
            
            // 로딩 화면 표시
            UIManager.showLoadingScreen();
            
            // 메인 루프 실행
            runMainLoop();
            
        } catch (Exception e) {
            System.err.println("애플리케이션 실행 중 오류 발생: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 프로그램 종료 시 리소스 정리
            cleanup();
        }
    }
    
    /**
     * 메인 루프 실행 메소드
     */
    private static void runMainLoop() {
        LoginUI loginUI = new LoginUI();
        
        while (true) {
            // 로그인 처리
            Member loginMember = loginUI.showLoginUI();
            
            if (loginMember != null) {
                // 메인 메뉴 UI 생성 및 표시
                MainMenuUI mainMenuUI = new MainMenuUI(loginMember);
                mainMenuUI.showMainMenu();
            }
        }
    }
    
    /**
     * 애플리케이션 종료 시 리소스 정리 메소드
     */
    private static void cleanup() {
        ConsoleUtil.closeScanner();
        DBConnection.closeConnection();
    }
}