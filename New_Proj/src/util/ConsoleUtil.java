package util;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 콘솔 입출력을 관리하는 유틸리티 클래스
 */
public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);
    static int uiwidth = 80;
    
    // ANSI 색상 코드
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    // 테두리 스타일
    private static final String TOP_BORDER    = "╔" + "═".repeat(uiwidth - 2) + "╗";
    private static final String BOTTOM_BORDER = "╚" + "═".repeat(uiwidth - 2) + "╝";
    private static final String DIVIDER       = "╠" + "═".repeat(uiwidth - 2) + "╣";
    private static final String SIDE_BORDER   = "║";
    
    /**
     * 문자열 입력을 받는 메소드
     * @param prompt 사용자에게 보여줄 메시지
     * @return 입력받은 문자열
     */
    public static String readString(String prompt) {
        System.out.print(BLUE + prompt + RESET);
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
                System.out.print(BLUE + prompt + RESET);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                printError("숫자를 입력해주세요.");
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
        printDivider();
        return readInt("선택 " + CYAN + "▶ " + RESET);
    }
    
    /**
     * 헤더 출력 메소드
     * @param header 헤더 문자열
     */
    public static void printHeader(String header) {
        System.out.println(CYAN + TOP_BORDER + RESET);
        System.out.println(CYAN + SIDE_BORDER + RESET +  
                          PURPLE + centerText(header, uiwidth - 2) + RESET);
        System.out.println(CYAN + DIVIDER + RESET);
    }
    
    /**
     * 구분선 출력 메소드
     */
    public static void printDivider() {
        System.out.println(CYAN + DIVIDER + RESET);
    }
    
    /**
     * 메시지 출력 메소드
     * @param message 출력할 메시지
     */
    public static void printMessage(String message) {
        System.out.println(CYAN + SIDE_BORDER + RESET + message);
    }
    
    /**
     * 에러 메시지 출력 메소드
     * @param message 출력할 에러 메시지
     */
    public static void printError(String message) {
        System.out.println(CYAN + SIDE_BORDER + RESET + " " + RED + "✘ 오류: " + message + RESET);
    }
    
    /**
     * 성공 메시지 출력 메소드
     * @param message 출력할 성공 메시지
     */
    public static void printSuccess(String message) {
        System.out.println(CYAN + SIDE_BORDER + RESET + " " + GREEN + "✓ 성공: " + message + RESET);
    }
    
    /**
     * 경고 메시지 출력 메소드
     * @param message 출력할 경고 메시지
     */
    public static void printWarning(String message) {
        System.out.println(CYAN + SIDE_BORDER + RESET + " " + YELLOW + "⚠ 경고: " + message + RESET);
    }
    
    /**
     * 정보 메시지 출력 메소드
     * @param message 출력할 정보 메시지
     */
    public static void printInfo(String message) {
        System.out.println(CYAN + SIDE_BORDER + RESET + " " + BLUE + "ℹ 정보: " + message + RESET);
    }
    
    /**
     * 확인 메시지를 출력하고 응답을 받는 메소드
     * @param message 확인 메시지
     * @return 확인 여부
     */
    public static boolean confirm(String message) {
        System.out.print(CYAN + SIDE_BORDER + RESET + " " + YELLOW + "? " + message + " (y/n) " + BLUE + "▶ " + RESET);
        String input = scanner.nextLine().toLowerCase();
        return input.equals("y") || input.equals("yes");
    }
    
    /**
     * 계속하기 위해 엔터를 입력받는 메소드
     */
    public static void pressEnterToContinue() {
        System.out.print(CYAN + SIDE_BORDER + RESET + " " + YELLOW + "계속하려면 엔터를 누르세요..." + RESET);
        scanner.nextLine();
    }
    
    /**
     * 콘솔 화면을 클리어하는 메소드
     */
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    /**
     * 푸터 출력 메소드
     */
    public static void printFooter() {
        System.out.println(CYAN + BOTTOM_BORDER + RESET);
    }
    
    /**
     * 텍스트를 중앙 정렬하는 메소드
     * @param text 정렬할 텍스트
     * @param width 전체 너비
     * @return 중앙 정렬된 텍스트
     */
//    private static String centerText(String text, int width) {
//        if (text.length() >= width) {
//            return text;
//        }
//        
//        int padding = (width - text.length()) / 2;
//        StringBuilder sb = new StringBuilder();
//        
//        for (int i = 0; i < padding; i++) {
//            sb.append(" ");
//        }
//        
//        sb.append(text);
//        
//        while (sb.length() < width) {
//            sb.append(" ");
//        }
//        
//        return sb.toString();
//    }
    
    /**
     * 스캐너를 닫는 메소드
     */
    public static void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
    
    /**
     * 텍스트를 왼쪽에 정렬할 수 있도록 앞에 공백을 채운 문자열을 반환
     * 
     * @param text
     * 오른쪽 정렬할 텍스트(String)
     * 
     * @param width
     * 한줄에 차지할 글자 수(int)
     * 
     * @return
     * 텍스트의 앞에 공백이 채워진 문자열
     */
    public static String leftText(String text, int width) {
        int textLength = getTextWidth(text);
        int padding = width - textLength;
        return text + " ".repeat(padding);
    }
    
    /**
     * 텍스트를 오른쪽에 정렬할 수 있도록 앞에 공백을 채운 문자열을 반환
     * 
     * @param text
     * 오른쪽 정렬할 텍스트(String)
     * 
     * @param width
     * 한줄에 차지할 글자 수(int)
     * 
     * @return
     * 텍스트의 앞에 공백이 채워진 문자열
     */
    public static String rightText(String text, int width) {
        int textLength = getTextWidth(text);
        int padding = width - textLength;
        return " ".repeat(padding) + text;
    }
    
    /**
     * 텍스트를 가운데 정렬할 수 있도록 앞뒤 공백을 채운 문자열을 반환
     * 
     * @param text
     * 가운데 정렬할 텍스트(String)
     * 
     * @param width
     * 한줄에 차지할 글자 수(int)
     * 
     * @return
     * 텍스트의 앞 뒤에 공백이 채워진 문자열
     */
    public static String centerText(String text, int width) {
        int textLength = getTextWidth(text);
        int padding = (width - textLength) / 2;
        return " ".repeat(padding) + text + " ".repeat(width - textLength - padding);
    }

    /**
     * 텍스트가 실제로 차지하는 길이 반환
     * 한글은 2칸으로, 알파벳/숫자/특수문자는 1칸으로 계산됨
     * 
     * @param text
     * 길이를 구하고 싶은 텍스트(String)
     * 
     * @return
     * 텍스트의 실제 길이
     */
    public static int getTextWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            if (c >= '가' && c <= '힣' || c >= 'ㄱ' && c <= 'ㅎ' || c >= 'ㅏ' && c <= 'ㅣ') { // 한글일 경우에는 글자크기 2로 계산
                width += 2;
            } else { // 영어, 숫자, 특수문자는 1로 계산
                width += 1;
            }
        }
        
        return width;
    }
    
    /**
     * String 배열에서 가장 긴 문자열의 길이 반환
     * 단, 한글은 2칸으로 계산
     * 
     * @param stringArr
     * 가장 긴 문자열의 길이를 알고 싶은 String 배열
     * 
     * @return
     * 가장 긴 문자열의 길이
     * stringArr이 null이거나 크기가 0이면 0 반환
     */
    public static int getMaxLength(String[] stringArr) {
        int length = 0;
        
        if (stringArr == null || stringArr.length == 0)
            return 0;
        
        for (String str : stringArr) {
            int tmpLen = getTextWidth(str);
            length = (length < tmpLen) ? tmpLen : length;
        }
        
        return length;
    }
}