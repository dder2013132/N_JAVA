//package baek;
//
//import java.util.Scanner;
//
//public class plus {
//	public static void main(String[] args) {
//		Scanner scn = new Scanner(System.in);
//		int m = scn.nextInt();
//		for(int i=0;i<m;i++) {
//			System.out.println(num(scn.nextInt()));
//		}
//	}
//	static int num(int x) {
//		if(x==1) return 1;
//		if(x==2) return 2;
//		if(x==3) return 3;
//		int result = (int) (num(x-3)+Math.floor(x/2)+1);
//		return result;
//	}
//}
