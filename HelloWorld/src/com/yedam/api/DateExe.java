package com.yedam.api;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateExe {
	public static void main(String[] args) {
		Date today = new Date();
		System.out.println(today.toString());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String formatStr = sdf.format(today);
		System.out.println(formatStr);
		
		try {
			Date tday = sdf.parse("2000-01-01 10:00:00");
			System.out.println(tday);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
