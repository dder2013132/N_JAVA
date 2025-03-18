package com.yedam.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yedam.bookApp.Book;

public class ClassExe {
	public static void main(String[] args) {
		
		Book book = new Book();
		book.getAuthor();
		try {
			Class cls = Class.forName("com.yedam.bookApp.Book");
			Method[] methods = cls.getDeclaredMethods();
			for(int i=0;i<methods.length;i++) {
				System.out.println(methods[i].getName());
			}
			
			Field[] fary = cls.getDeclaredFields();
			for(int i=0;i<fary.length;i++) {
				System.out.println(fary[i].getName());
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ClassNotFoundException.
		
	}
}
