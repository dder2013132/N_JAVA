package com.yedam.api;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ObjectExe2 {
	public static void main(String[] args) {
		Object obj1 = new Object();
		System.out.println(obj1.toString());
		
		Date obj2 = new Date();
		System.out.println(obj2.toString());
		
		// 출력 2025년 03월 17일 00시 00분 00초
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초");
		System.out.println(sdf.format(obj2));
	}
}
