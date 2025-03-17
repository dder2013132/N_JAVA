package com.yedam.nested;

public class MainExe {
	public static void main(String[] args) {
		Outer outer = new Outer();
		outer.method1();

		// Inner 클래스의 타입.
		Outer.Inner inner = outer.new Inner();
		inner.innerMethod();
	}
}
