package com.yedam.classes;

public class MethodExe5 {

    public static void main(String[] args) {
        // final 키워드
        int num1 = 10;
        num1 = 20;
        
        final int num2 = 20;
        // num2 = 30; // final 키워드가 붙으면 값을 지정한 후에는 변경 불가
        
        final Product p1 = new Product();
        p1.setProductCode("A001"); // O
        p1.setProductCode("B001"); // O
        // p1 = new Product();     // X
        
    }

}
