package com.yedam.baek;

import java.util.ArrayList;

class Solution {
	public static int solution(int[] ingredient) {
		int answer = 0;
		ArrayList<Integer> sl = new ArrayList<>();
		for (int value : ingredient) {
			sl.add(value);
		}
		for (int i = 0; i < sl.size(); i++) {
			if (i >= 0 && i + 3 < sl.size() && sl.get(i) == 1 && sl.get(i + 1) == 2 && sl.get(i + 2) == 3
					&& sl.get(i + 3) == 1) {
				answer++;
				sl.subList(i, i + 4).clear();
				i = Math.max(i - 4, -1);
			}
		}
		return answer;
	}
}
