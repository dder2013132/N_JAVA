//package com.yedam.baek;
//
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class bfs_dfs {
//	public static void main(String[] args) {
//		boolean check[] = new boolean[10001];
//		Arrays.fill(check, false);
//		int[][] arr = new int[1001][1001];
//		Scanner scn = new Scanner(System.in);
//		int N = scn.nextInt();
//		int M = scn.nextInt();
//		int V = scn.nextInt();
//		for(int i=0;i<M;i++) {
//			int tmp1 = scn.nextInt();
//			int tmp2 = scn.nextInt();
//			arr[tmp1][tmp2]= 1;
//			arr[tmp2][tmp1]= 1;
//		}			
//		dfs(check,arr,V,N);
//		check = new boolean[10000];
//		System.out.println();
//		bfs(check,arr,V,N);
//	}
//	public static void dfs(boolean check[], int arr[][], int i, int p) {
//		check[i] = true;
//		System.out.print(i + " ");
//		for(int j=1;j<=p;j++) {
//			if(arr[i][j] == 1 && check[j] == false) {
//				dfs(check,arr,j,p);
//			}
//		}
//	}
//	
//	public static void bfs(boolean check[], int arr[][], int i, int p) {
//		Queue<Integer> q = new LinkedList<>();
//		q.offer(i);
//		check[i] = true;
//		
//		while(!q.isEmpty()) {
//			int tmp = q.poll();
//			System.out.print(tmp + " ");
//			for(int j=1;j<=p;j++) {
//				if(arr[tmp][j] == 1 && check[j] == false) {
//					q.offer(j);
//					check[j] = true;
//				}
//			}
//		}
//	}
//}
