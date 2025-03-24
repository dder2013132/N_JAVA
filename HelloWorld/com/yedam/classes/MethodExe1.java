package com.yedam.classes;

public class MethodExe1 {
    // 메소드 형식
    // returnType methodName (parameters, ...) {
    //     ...
    // }
    
    void printString(int times, String str) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
        
    }
    
    // 두 정수의 합
    int sum(int n1, int n2) {
        int result = 0;
        result = n1 + n2;
        return result;
    }
    
    // 두 실수의 합
    double sum(double n1, double n2) {
        return (n1 + n2);
    }
    
    // 정수 배열의 합
    int sum(int[] arr) {
        int result = 0;
        
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        
        return result;
    }
    
    // 실수 배열의 합
    double sum(double[] arr) {
        double result = 0.0;
        
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        
        return result;
    }
}
