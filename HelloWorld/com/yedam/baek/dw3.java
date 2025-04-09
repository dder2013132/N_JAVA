package com.yedam.baek;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class dw3 {
	public static void main(String[] args) {
		String par = "10012371444444283";
		String com = "203045124124123";
		System.out.println(solution(par, com));
	}

	public static String solution(String X, String Y) {
		String answer = "";
		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
		for (int i = 0; i < X.length(); i++) {
			hm.put(X.charAt(i), hm.getOrDefault(X.charAt(i), 0) + 1);
		}
		System.out.println(hm.toString());
		for (int i = 0; i < Y.length(); i++) {
			if(hm.get(Y.charAt(i))!=null&&hm.containsKey(Y.charAt(i))==true){
				int tmp=hm.getOrDefault(Y.charAt(i), 0);
				hm.put(Y.charAt(i), hm.getOrDefault(Y.charAt(i), 0)-1);
			} else if(hm.get(Y.charAt(i))==null) {
				hm.put(Y.charAt(i), 1);
			}
		}
		System.out.println(hm.toString());
		List<Character> lt = new ArrayList<>();

		for (Character ele : hm.keySet()) {
			Integer value = hm.get(ele);
			if (value >= 2) {
				if (value % 2 != 0) {
					for (int i = 0; i < (value - 1) / 2; i++)
						lt.add(ele);
				} else {
					for (int i = 0; i < value / 2; i++)
						lt.add(ele);
				}
			}
		}
		Collections.reverse(lt);
		answer = String.join("", lt.toString());
		answer = answer.replaceAll(", ", "");
		answer = answer.replaceAll("[\\[\\]]", "");
		if (lt.size() == 1) {
			answer = "-1";
		}
		if (lt.size() != 1) {
			boolean chk = true;
			for (char pp : lt) {
				if (pp != '0') {
					chk = false;
					break;
				}
			}
			if (chk) {
				answer="0";
			}
		}
		return answer;
	}
}
