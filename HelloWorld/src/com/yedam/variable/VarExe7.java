package com.yedam.variable;

import java.util.Scanner;

// 추가, 수정, 삭제, 목록 출력.

public class VarExe7 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		boolean run = true;
		// Member 값을 저장.
		Member[] storage = new Member[100]; // {null, null, ... null}
		storage[0] = new Member("홍길동", 83);
		storage[1] = new Member("김민혁", 86);
		storage[2] = new Member("한수아", 90);

		while (run) {
			System.out.println("1.등록 2.수정 3.삭제 4.출력 5.평균 6.종료");
			System.out.print("선택>> ");
			int menu = Integer.parseInt(scn.nextLine()); // 1 엔터.

			switch (menu) {
			case 1: // 등록.
				System.out.print("이름입력>> ");
				String name = scn.nextLine();
				System.out.print("점수입력>> ");
				int score = Integer.parseInt(scn.nextLine());
				Member member = new Member(); // 인스턴스 생성.
				// member.name = name;
				// member.score = score;
				member.setMember(name, score);

				// 빈공간에 값을 할당.
				for (int i = 0; i < storage.length; i++) {
					if (storage[i] == null) {
						storage[i] = member;
						break; // for반복문 종료.
					}
				}
				break; // case 1의 종료.
			case 2: // 수정
				System.out.print("변경할 이름 입력>> ");
				name = scn.nextLine();
				System.out.print("변경할 점수 입력>> ");
				score = Integer.parseInt(scn.nextLine());

				boolean isExist = false; // 존재여부를 체크.
				for (int i = 0; i < storage.length; i++) {
					if (storage[i] != null //
							&& storage[i].getName().equals(name)) {
						storage[i].setScore(score); // 수정.
						System.out.println("수정 되었습니다.");
						isExist = true; // 체크확인.
						break;
					}
				}
				if (!isExist) {
					System.out.println("찾는 이름이 없습니다.");
				}
				break;
			case 3: // 삭제. 이름입력 조회 후 => null 대입.
				System.out.print("삭제할 이름 입력>> ");
				name = scn.nextLine();

				isExist = false;
				for (int i = 0; i < storage.length; i++) {
					if (storage[i] != null //
							&& storage[i].getName().equals(name)) {
						storage[i] = null; // 삭제.
						System.out.println("삭제되었습니다.");
						isExist = true; // 체크확인.
						break;
					}
				}
				if (!isExist) {
					System.out.println("찾는 이름이 없습니다.");
				}
				break;
			case 4: // 목록 출력.
				System.out.println("이름  점수");
				System.out.println("============");
				for (int i = 0; i < storage.length; i++) {
					if (storage[i] != null) {
						System.out.println(storage[i].getName() + " " + storage[i].getScore());
					}
				}
				break;
			case 5: // 평균.
				// 학생들의 점수 합을 구하고 학생수만큼 나누면
				// 평균입니다.
				int sum = 0, count = 0;
				for (int i = 0; i < storage.length; i++) {
					if (storage[i] != null) {
						sum += storage[i].getScore();
						count++;
					}
				}
				double avg = sum * 1.0 / count;
				System.out.println("평균은 " + avg + "입니다.");
				break;
			case 6: // 종료.
				run = false;
			}
		}
		System.out.println("end of prog.");
	} // end of main().
}
