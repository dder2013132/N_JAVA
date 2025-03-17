package com.yedam.api;

public class SystemExe {
	public static void main(String[] args) {
		for(int i=1;i<=10;i++) {
			if(i==5) {
				//break;
				System.exit(0);
			}
		}
		System.out.println("end of prog");
	}
}
