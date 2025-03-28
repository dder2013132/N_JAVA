package util;

/**
 * UI 관련 유틸리티 클래스
 */
public class UIManager {
    
    // UI 너비 상수
    public static final int UI_WIDTH = 80;
    
    /**
     * 로딩 화면을 표시하는 메소드
     */
    public static void showLoadingScreen() {
        ConsoleUtil.clearScreen();
        System.out.println(ConsoleUtil.CYAN + "╔" + "═".repeat(UI_WIDTH) + "╗" + ConsoleUtil.RESET);
        System.out.println(ConsoleUtil.CYAN + "║" + " ".repeat(UI_WIDTH) + "║" + ConsoleUtil.RESET);
        System.out.println(ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET + ConsoleUtil.PURPLE + ConsoleUtil.centerText("Game Shop & Review", UI_WIDTH) + ConsoleUtil.RESET + ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET);
        System.out.println(ConsoleUtil.CYAN + "║" + " ".repeat(UI_WIDTH) + "║" + ConsoleUtil.RESET);
        System.out.println(ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET + ConsoleUtil.GREEN + ConsoleUtil.centerText("Version 1.0.0", UI_WIDTH)  + ConsoleUtil.RESET + ConsoleUtil.CYAN + "║" + ConsoleUtil.RESET);
        System.out.println(ConsoleUtil.CYAN + "║" + " ".repeat(UI_WIDTH) + "║" + ConsoleUtil.RESET);
        System.out.println(ConsoleUtil.CYAN + "╚" + "═".repeat(UI_WIDTH) + "╝" + ConsoleUtil.RESET);
        
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
}