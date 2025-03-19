package com.yedam.classes;

public class MethodExe1 {

	// 리턴타입 메소드이름 (매개변수...) {

	// }
	void printString(int times, String str) {
		for (int i = 1; i <= times; i++) {
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

	// 배열의 합
	int sum(int[] ary) {
		int result = 0;
		for (int i = 0; i < ary.length; i++) {
			result += ary[i];
		}
		return result;
	}

	// 배열의 합(double[]을 매개값, 반환:double)
	double sum(double[] ary) {
		double result = 0;
		for (int i = 0; i < ary.length; i++) {
			result += ary[i];
		}
		return result;
	}
}
