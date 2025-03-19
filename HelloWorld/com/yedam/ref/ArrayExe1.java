package com.yedam.ref;

public class ArrayExe1 {
	public static void main(String[] args) {
		int[] intAry = { 10, 20, 30 };
		for (int i = 0; i < 3; i++) {
//			System.out.println(intAry[i]);
		}

		intAry = new int[] { 40, 50, 60 };

		//
		intAry = new int[10]; // {0, 0, ...., 0}
		intAry[3] = 30;
		intAry[9] = 90;

		intAry[1] = 100; // 예외발생.
		intAry[0] = (int) (Math.random() * 100) + 1;
		for (int i = 0; i < intAry.length; i++) {
			if (intAry[i] == 0) {
				intAry[i] = (int) (Math.random() * 100) + 1;
			}
		}
		// 홀수값의 합 구하기.
		// "홀수값의 합은 234입니다."
		for (int i = 0; i < intAry.length; i++) {
			System.out.println(i + "번째 값은 " + intAry[i]);
		}

	}
}
