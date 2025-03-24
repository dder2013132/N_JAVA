//package com.yedam.baek;
//
//import java.util.Scanner;
//
//public class kbs {
//	public static void main(String[] args) {
//		int N = 0;
//		String k = null;
//		int tmp1 = 0;
//		int tmp2 = 0;
//		String arr[] = new String[100];
//		Scanner scn = new Scanner(System.in);
//		N = scn.nextInt();
//		scn.nextLine();
//		for(int i=0;i<N;i++) {
//			k = scn.nextLine();
//			arr[i]=k;
//			if(k.equals("KBS1")) {
//				tmp1 = i;
//			}
//			if(k.equals("KBS2")) {
//				tmp2 = i;
//			}
//		}
//		int p = 0;
//		for(int i=0;i<tmp1;i++) {
//			System.out.print("1");
//		}
//		for(int i=0;i<tmp1;i++) {
//			System.out.print("4");
//		}
//		for(int i=0;i<tmp2;i++) {
//			System.out.print("1");
//			if(i==(tmp2-1)&&tmp2<tmp1) {
//				System.out.print("1");
//			}
//		}
//		if(tmp1<tmp2) {
//			for(int i=0;i<tmp2-1;i++) {
//				System.out.print("4");
//			}
//		}
//		else if(tmp1>tmp2) {
//			for(int i=0;i<tmp2;i++) {
//				System.out.print("4");
//			}
//		}
//	}
//}