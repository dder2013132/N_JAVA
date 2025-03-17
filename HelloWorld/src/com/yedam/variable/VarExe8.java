package com.yedam.variable;

public class VarExe8 {
	public static void main(String[] args) {
		int n1 = 10;
		byte b1 = 120;
		// -128 ~ 127 형변환(casting)
		byte result = (byte) (b1 + 130);
		System.out.println("더한 결과: " + result);
		// byte -> int (자동형변환: promotion)
		n1 = b1;
		System.out.println(n1);
		n1 = 200;
		b1 = (byte) n1;
		System.out.println(b1);

		for (int i = 1; i < 15; i++) {
//			System.out.println(b1++);
		}
	}
}
