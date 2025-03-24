//package com.yedam.baek;
//
//import java.util.Scanner;
//
//public class ddong {
//	public static void main(String[] args) {
//		boolean chk = true;
//		int i = 0,j=0,k=0;
//		Scanner scn = new Scanner(System.in);
//		while(chk) {
//			i = scn.nextInt();
//			j = scn.nextInt();
//			k = scn.nextInt();
//			if(i==0&&j==0&&k==0) {
//				break;
//			}
//			int tmp = i;
//			if(tmp < j) {
//				tmp = j;
//			}
//			if(tmp < k) {
//				tmp = k;
//			}
//			int sum = i+j+k-tmp;
//			if(tmp>=sum) {
//				System.out.println("Invalid");
//			}
//			else if(i==j&&j==k) {
//				System.out.println("Equilateral");
//			}
//			else if((i==j&&j!=k)||(i!=j&&j==k)||(i==k&&j!=k)) {
//				System.out.println("Isosceles");
//			}
//			else if(i!=j&&j!=k&&i!=k) {
//				System.out.println("Scalene");
//			}
//		}
//	}
//}
