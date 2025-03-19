package com.yedam.api;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassExe {
	public static void main(String[] args) {

		try {
			Class cls = Class.forName("com.yedam.bookApp.Book");// ClassNotFoundException.

			Method[] methods = cls.getDeclaredMethods();
			// 클래스의 메소드 반환.
			for (int i = 0; i < methods.length; i++) {
//				System.out.println(methods[i].getName());
			}

			Field[] fary = cls.getDeclaredFields();
			// 클래스의 필드 반환.
			for (int i = 0; i < fary.length; i++) {
				System.out.println(fary[i].getName());
			}

//			Constructor[] fcon = cls.getDeclaredConstructors();
//			// 클래스의 생성자 반환.
//			for (int i = 0; i < fcon.length; i++) {
//				System.out.println(fcon[i].getName());
//			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
}
