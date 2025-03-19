package com.yedam.classes;

import java.util.List;
import java.util.Scanner;

public class MethodMain {

	public static void main(String[] args) {

		officeApp();
		System.out.println("end of prog.");

	} // end of main.

	static void officeApp() {
		Scanner scn = new Scanner(System.in);
		// 목록,추가,수정,삭제......
		MethodExe2 m2 = new MethodExe2(); // 기능정의.

		// 사용자입력 받아서 1.목록,2.추가,3.수정,4.삭제 9.종료 구현.
		// 입력메세지 정의 구현.
		boolean run = true;
		while (run) {

			System.out.println("1.목록 2.추가 3.수정 4.삭제 9.종료");
			System.out.print("선택>> ");
			int menu = Integer.parseInt(scn.nextLine());

			switch (menu) {
			case 1:
				System.out.print("상품명입력>> ");
				String name = scn.nextLine();
				if (name.isBlank()) {
					name = "ALL";
				}
				System.out.print("상품가격입력>> ");
				String price = scn.nextLine();
				if (price.isBlank()) {
					price = "0";
				}

				Product product = new Product();
				product.setProductName(name);
				product.setPrice(Integer.parseInt(price));

				List<Product> list = m2.productList(product);
				for (int i = 0; i < list.size(); i++) {
					System.out.println(list.get(i).showList());
				}
				break;

			case 2:
				System.out.print("상품코드입력>> ");
				String code = scn.nextLine();
				System.out.print("상품명입력>> ");
				name = scn.nextLine();
				System.out.print("상품가격입력>> ");
				price = scn.nextLine();

				product = new Product(code, name, Integer.parseInt(price));
				if (m2.add(product)) {
					System.out.println("등록성공.");
				}
				break;

			case 3:
				System.out.print("상품코드입력>> ");
				code = scn.nextLine();
				System.out.print("상품명입력>> ");
				name = scn.nextLine();
				System.out.print("상품가격입력>> ");
				price = scn.nextLine();

				product = new Product(code, name, Integer.parseInt(price));
				if (m2.modify(product)) {
					System.out.println("수정성공.");
				}
				break;

			case 4:
				System.out.print("상품코드입력>> ");
				code = scn.nextLine();
				if (m2.remove(code)) {
					System.out.println("삭제성공.");
				}
				break;

			case 9:
				System.out.println("프로그램을 종료합니다.");
				run = false;
			default:
				System.out.println("메뉴를 선택하세요.");
			}
		}

	}

	void method4() {
//		MethodExe4 m4 = new MethodExe4();
		MethodExe4.main();
	}

	void method3() {
		System.out.println(MethodExe3.gugudan(3, 5));
		MethodExe3.printStar(7, "◎");
		MethodExe3.printCard();
	}

	void method2() {
		// 상품코드: M001, 상품명: 만년필, 가격: 10000
		MethodExe2 m2 = new MethodExe2();
		// 상품코드, 상품명, 가격 modify(Product prod)
//				Product prod = new Product("A001", "지우개1000", 0);
//				if (m2.modify(prod)) {
//					System.out.println("수정성공");
//				}

		Product search = new Product();
		search.setProductName("지우개");
		search.setPrice(700);

		List<Product> list = m2.productList(search);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).showList());
		}
	}

	void method1() {
		MethodExe1 m1 = new MethodExe1();
//		System.out.println(m1);
		// 메소드 호출.
		int n = 5;
		m1.printString(n, "★");

		double result = m1.sum(n, 10.5);
		System.out.println(result);

		int sum = m1.sum(new int[] { 10, 20, 30 });
		result = m1.sum(new double[] { 10.5, 22.1, 34.5 });
		System.out.println(sum);
	}
}
