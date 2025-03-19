package com.yedam.api;

import java.util.ArrayList;
import java.util.List;

public class SystemExe {
	public static void main(String[] args) {
		long st = System.currentTimeMillis();
		List<String> str = new ArrayList<>();
		for (long i = 1; i <= 1000000L; i++) {
//			System.out.println(i);
			str.add(0, "" + i);
		}
		long et = System.currentTimeMillis();
		System.out.println(et - st);

//		for (int i = 1; i <= 10; i++) {
//			if (i == 5) {
////				break;
//				System.out.println("종료합니다.");
//				System.exit(0);
//			}
//		}
		System.out.println("end of prog.");
	}
}
