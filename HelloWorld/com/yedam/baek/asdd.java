//package com.yedam.baek;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.Scanner;
//import java.util.StringTokenizer;
//
//public class asdd {
//	public static void main(String[] args) throws IOException {
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//		Scanner scn = new Scanner(System.in);
//		int M = Integer.parseInt(br.readLine());
//		int x = 0;
//		int[] intArr = new int[20];
//		
//		for(int i=0;i<M;i++) {
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int tmp = st.countTokens();
//			String a = st.nextToken();
//			if(tmp != 1) x = Integer.parseInt(st.nextToken());
//			switch(a){
//				case "add": add(intArr, x); break;
//				case "remove": remove(intArr, x); break;
//				case "check": bw.write(String.valueOf(check(intArr, x))); 
//							  bw.newLine(); break;
//				case "toggle": toggle(intArr, x); break;
//				case "all": all(intArr); break;
//				case "empty": empty(intArr); break;
//			}
//		}
//		bw.flush();
//		bw.close();
//		scn.close();
//	}
//	public static void add(int[] intArr, int x) {
//		boolean chk = false;
//		for(int i=0;i<20;i++) {
//			if(intArr[i]==x) {
//				chk = true;
//				break;
//			}
//		}
//		if(chk == false) {
//			for(int i=0;i<20;i++) {
//				if(intArr[i]==0) {
//					intArr[i]=x;
//					break;
//				}
//			}
//		}
//	}
//	
//	public static void remove(int[] intArr, int x){
//		for(int i=0;i<20;i++) {
//			if(intArr[i]==x) {
//				intArr[i]=0;
//				break;
//			}
//		}
//	}
//	
//	public static int check(int[] intArr, int x){
//		for(int i=0;i<20;i++) {
//			if(intArr[i]==x) {
//				return 1;
//			}
//			else if(i==19){
//				return 0;
//			}
//		}
//		return 0;
//	}
//	
//	public static void toggle(int[] intArr, int x) {
//		for(int i=0;i<20;i++) {
//			if(intArr[i]==x) {
//				remove(intArr, x);
//				break;
//			}
//			else if(i==19) {
//				add(intArr, x);
//				break;
//			}
//		}
//	}
//	
//	public static void all(int[] intArr) {
//		int j=1;
//		for(int i=0;i<20;i++) {
//			intArr[i]=j++;
//		}
//	}
//	
//	public static void empty(int[] intArr) {
//		for(int i=0;i<20;i++) {
//			intArr[i]=0;
//		}
//	}
//}
