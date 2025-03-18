package com.yedam.api;

import java.util.Calendar;

public class CalendarExe {
	public static void main(String[] args) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DATE, 10);
		cal.set(2023, 2, 5);
		System.out.println(cal.get(Calendar.YEAR));
		System.out.println(cal.get(Calendar.MONTH)+1);
		System.out.println(cal.get(Calendar.DATE));
		System.out.println(cal.get(Calendar.DAY_OF_WEEK)); //요일
		System.out.println(cal.getActualMaximum(Calendar.DATE)); //마지막날
		
		//년, 월 입력 => 1일의 요일 반환. 말일을 반환.
		 // 7월달의 1일의 요일
		 // 7월달의 말일
		System.out.println(getDay(2025, 7));
		System.out.println(getLastDate(2025, 7));
	}
	
	static String getDay(int year, int month) {
		int mnt = month - 1;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, mnt);
		cal.set(Calendar.DATE, 1);
		int date = cal.get(Calendar.DAY_OF_WEEK);
		switch(date) {
		case 1: return "일요일";
		case 2: return "월요일";
		case 3: return "화요일";
		case 4: return "수요일";
		case 5: return "목요일";
		case 6: return "금요일";
		case 7: return "토요일";
		default: return "오류";
		}
	}
	
	static int getLastDate(int year, int month) {
		int mnt = month - 1;
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, mnt);
		return cal.getActualMaximum(Calendar.DATE);
	}
}