package com.yedam.collections;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yedam.bookApp.Book;

public class MapExe {
	public static void main(String[] args) {

		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(100, "홍길동");
		map.put(101, "김민수");
		map.put(102, "김혁수");
		map.put(102, "김혁식");
		// 삭제.
		map.remove(101);

		Set<Entry<Integer, String>> eset = map.entrySet();
		for (Entry<Integer, String> entry : eset) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
		}

		boolean run = true;
		if (run)
			return;

		Map<String, Book> books = new HashMap<String, Book>();
		books.put("B001", new Book("이것이자바다", "신용권", "한빛출", 20000));
		books.put("B002", new Book("자바스크립트", "김자바", "한빛출", 25000));
		books.put("B003", new Book("HTML,CSS", "김자바", "한빛출", 28000));

		// 검색.
		Book result = books.get("B001");

		// 키 set 반환.
		Set<String> keyset = books.keySet();
		for (String key : keyset) {
			System.out.println("key: " + key //
					+ ",val: " + books.get(key).showList());
		}

		// 키:값 (Entry) 반환.
		Set<Entry<String, Book>> entryset = books.entrySet();
		for (Entry<String, Book> entry : entryset) {
			System.out.println("key: " + entry.getKey() //
					+ ",val: " + entry.getValue().showList());
		}

		// {키: 값}
		Gson gson = new GsonBuilder().setPrettyPrinting().create(); // 자바객체->문자열.
		String json = gson.toJson(books);
		System.out.println(json); // 객체 출력.

	}
}
