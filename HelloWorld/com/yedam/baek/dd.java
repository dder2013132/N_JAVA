//package com.yedam.baek;
//
//public class dd {
//	public static void main(String[] args) {
//		int[] a = new int[2];
//		a = solution(2,5);
//		System.out.println(a[0] + " " + a[1]);
//		
//	}
//	public static int[] solution(int n, int m) {
//		int a = gcd(n,m);
//		int b = n*m/a;
//        int[] answer = {a,b};
//        return answer;
//    }
//	public static int gcd(int n, int m) {
//		int tmp = 0;
//		tmp=m%n;
//		m=n;
//		if(tmp==0) {
//			return n;
//		}
//		return gcd(tmp,n);
//	}
//	
//	
//}
