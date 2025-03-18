package com.yedam.api;

public class StringExe {
	public static void main(String[] args) {
		String str = "Hello, World";
		str = new String("Hello, World");
		byte[] bAry = str.getBytes();
		for(int i=0;i<bAry.length;i++) {
			System.out.println(bAry[i]);
		}
		
		byte[] bstr = {72,101,108,108,111,44,32,87,111,114,108,100};
		String msg = new String(bstr, 7, 5);
		System.out.println(msg);
		char result = msg.charAt(0);
		System.out.println(result);
		
		// String : "", 
		if(result == 'W') {
			//비교구문
		}
		int idx = msg.indexOf("O");
		if(idx != -1) {
			// 처리
		}
				
		String[] names = {"강철중", "송구호", "박살호"};
	    int cnt =0;
	    for(int i=0;i<names.length;i++) {
	    	if(names[i].indexOf("홍") ==0) {
	    		}
	    	}
	    	
		
	}
}
