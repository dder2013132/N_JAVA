package com.yedam.classes;

import java.util.Scanner;

public class MethodExe3 {

	// 구구단출력.
	static String gugudan(int num, int toNum) {
		// 2 * 1 = 2
		// 2 * 2 = 4\n
		// ...
		// 2 * 9 = 18
		String str = "";
		for (int n = num; n <= toNum; n++) {
			int dan = n;
			for (int i = 1; i <= 9; i++) {
//				System.out.println(dan + " * " + i + " = " + (dan * i));
				str += dan + " * " + i + " = " + (dan * i) + "\n";
			}
		}
		return str;
	} // end of gugudan.

	// 별출력.
	static void printStar(int cnt, String str) {
		for (int j = 1; j <= cnt; j++) {
			for (int i = 1; i <= cnt; i++) {
				if (i <= j) {
					System.out.print(str); // 기호.
				}
			}
			System.out.println(); // 줄바꿈.
		}
	}

	// 카드번호보여주기. 중복을 제거.
	static void printCard() {
		// 배열선언.
		int[] intAry = new int[16];
		// 1 ~ 16까지의 임의수 할당.
		for (int i = 0; i < intAry.length;) {
			int temp = (int) (Math.random() * 16) + 1;
			// 이전에 생성된 값인지 체크.
			boolean dup = false;
			for (int j = 0; j < i; j++) {
				if (intAry[j] == temp) {
					dup = true;
				}
			}
			// 이전에 생성된 값이 있다면..
			if (dup) {
				continue;
			}
			intAry[i] = temp;
			i++;
		}
		// 출력.
		Scanner scn = new Scanner(System.in);
		int[] numAry = new int[16];
		while (true) {
			for (int i = 0; i < intAry.length; i++) {
				// 비교.
				if (numAry[i] == 0) {
					System.out.printf("%4s", "(" + (i + 1) + ")");
				} else {
					System.out.printf("%3d ", intAry[i]);
				}
				if (i % 4 == 3) {
					System.out.println();
				}
			}
			System.out.print("번호를 입력>> ");
			int choice = scn.nextInt();
			if (choice < 0) { // 종료조건.
				break;
			}
			// 선택된 위치의 인덱스에 0이 아닌 값으로 변경.
			numAry[choice - 1] = 1;
		} // end of while.

		System.out.println("end of prog.");
		scn.close();
	} // end of printCard().

}
