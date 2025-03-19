package com.yedam.variable;

import java.util.Scanner;

// 예금,출금,잔고 확인기능.

public class VarExe5 {
	public static void main(String[] args) {
		boolean run = true;
		int balance = 0; // 예금액을 저장하는 변수.
		// 10만원 최대, 잔액이 > 0 조건.
		int amt = 0; // 최대금액과 최소금액을 넘어서지 않도록.
		Scanner scn = new Scanner(System.in);
		while (run) {
			System.out.println("1.예금 2.출금 3.잔고 4.종료");
			int menu = scn.nextInt();
			switch (menu) {
			case 1:
				System.out.print("예금액을 입력>>");
				amt = scn.nextInt();
				// 최대잔고 10만원.
				if (balance + amt > 100000) {
					System.out.println("10만원을 초과합니다.");
				} else {
					balance = balance + amt;
				}
				break; // case 1 종료.
			case 2:
				System.out.print("출금액을 입력>>");
				// -잔고가 안되도록.
				amt = scn.nextInt();
				if (balance < amt) {
					System.out.println("잔액초과 출금할 수 없습니다.");
				} else {
					balance = balance - amt;
				}
				break; // case 2 종료.
			case 3:
				System.out.println("잔액은 " + balance + " 입니다.");
				break; // case 3 종료.
			case 4:
				run = false;
			}
		}
		System.out.println("end of prog.");
	} // end of main().
}
