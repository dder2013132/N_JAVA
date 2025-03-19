package com.yedam.inheritance;

/*
 * 부모클래스(Animal)가 가진 추상메소드(sound)를 반드시 구현.
 */
public class Bird extends Animal {
	void sound() {
		System.out.println("짹짹");
	}
	
}
