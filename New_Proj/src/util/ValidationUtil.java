package util;

import java.util.regex.Pattern;

/**
 * 입력값 검증을 위한 유틸리티 클래스
 */
public class ValidationUtil {
    // 이메일 형식 검증을 위한 정규식
    private static final String EMAIL_REGEX = 
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    
    // 전화번호 형식 검증을 위한 정규식
    private static final String PHONE_REGEX = 
            "^\\d{2,3}-\\d{3,4}-\\d{4}$";
    
    /**
     * 문자열이 비어있는지 확인하는 메소드
     * @param value 검사할 문자열
     * @return 비어있으면 true, 아니면 false
     */
    public static boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
    
    /**
     * 이메일 형식이 유효한지 확인하는 메소드
     * @param email 검사할 이메일
     * @return 유효하면 true, 아니면 false
     */
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) {
            return false;
        }
        return Pattern.matches(EMAIL_REGEX, email);
    }
    
    /**
     * 전화번호 형식이 유효한지 확인하는 메소드
     * @param phone 검사할 전화번호
     * @return 유효하면 true, 아니면 false
     */
    public static boolean isValidPhone(String phone) {
        if (isEmpty(phone)) {
            return false;
        }
        return Pattern.matches(PHONE_REGEX, phone);
    }
    
    /**
     * ID 형식이 유효한지 확인하는 메소드
     * @param id 검사할 ID
     * @return 유효하면 true, 아니면 false
     */
    public static boolean isValidId(String id) {
        if (isEmpty(id)) {
            return false;
        }
        // 영문자와 숫자만 포함, 길이 4-20자
        return id.matches("^[a-zA-Z0-9]{4,20}$");
    }
    
    /**
     * 비밀번호 형식이 유효한지 확인하는 메소드
     * @param password 검사할 비밀번호
     * @return 유효하면 true, 아니면 false
     */
    public static boolean isValidPassword(String password) {
        if (isEmpty(password)) {
            return false;
        }
        // 최소 4자 이상
        return password.length() >= 4;
    }
    
    /**
     * 가격이 유효한지 확인하는 메소드
     * @param price 검사할 가격
     * @return 유효하면 true, 아니면 false
     */
    public static boolean isValidPrice(int price) {
        return price > 0;
    }
    
    /**
     * 수량이 유효한지 확인하는 메소드
     * @param quantity 검사할 수량
     * @return 유효하면 true, 아니면 false
     */
    public static boolean isValidQuantity(int quantity) {
        return quantity > 0;
    }
}