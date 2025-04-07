package com.yedam.test;

import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class AppMain {
	public static void main(String[] args) {
		MemberService svc = new MemberServiceImpl();
		MemberVO member = svc.login("user01", "1111");

		System.out.println(member.toString());
	}
}
