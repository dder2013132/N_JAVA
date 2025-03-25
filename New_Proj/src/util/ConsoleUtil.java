package util;

import java.util.Scanner;

/**
 * 콘솔 입출력을 관리하는 유틸리티 클래스
 */
public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);
    
    /**
     * 문자열 입력을 받는 메소드
     * @param prompt 사용자에게 보여줄 메시지
     * @return 입력받은 문자열
     */
    public static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    /**
     * 정수 입력을 받는 메소드
     * @param prompt 사용자에게 보여줄 메시지
     * @return 입력받은 정수
     */
    public static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("숫자를 입력해주세요.");
            }
        }
    }
    
    /**
     * 메뉴 출력 및 선택을 받는 메소드
     * @param menu 메뉴 문자열
     * @return 선택한 메뉴 번호
     */
    public static int showMenuAndGetChoice(String menu) {
        System.out.println(menu);
        System.out.println("=====================================================");
        return readInt("선택 >> ");
    }
    
    /**
     * 헤더 출력 메소드
     * @param header 헤더 문자열
     */
    public static void printHeader(String header) {
        System.out.println("\n\t\t  " + header);
        System.out.println("=====================================================");
    }
    
    /**
     * 구분선 출력 메소드
     */
    public static void printDivider() {
        System.out.println("=====================================================");
    }
    
    /**
     * 메시지 출력 메소드
     * @param message 출력할 메시지
     */
    public static void printMessage(String message) {
        System.out.println(message);
    }
    
    /**
     * 에러 메시지 출력 메소드
     * @param message 출력할 에러 메시지
     */
    public static void printError(String message) {
        System.out.println("오류: " + message);
    }
    
    /**
     * 성공 메시지 출력 메소드
     * @param message 출력할 성공 메시지
     */
    public static void printSuccess(String message) {
        System.out.println("성공: " + message);
    }
    
    /**
     * 확인 메시지를 출력하고 응답을 받는 메소드
     * @param message 확인 메시지
     * @return 확인 여부
     */
    public static boolean confirm(String message) {
        System.out.print(message + " (y/n) >> ");
        String input = scanner.nextLine().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
    
    /**
     * 계속하기 위해 엔터를 입력받는 메소드
     */
    public static void pressEnterToContinue() {
        System.out.print("계속하려면 엔터를 누르세요...");
        scanner.nextLine();
    }
    
    /**
     * 스캐너를 닫는 메소드
     */
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}