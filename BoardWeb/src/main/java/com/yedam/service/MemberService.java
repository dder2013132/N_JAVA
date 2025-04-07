package com.yedam.service;

import com.yedam.vo.MemberVO;

// 업무프로세스(service)
public interface MemberService {
	// 로그인.
	MemberVO login(String id, String pw);
	boolean addMember(MemberVO member); //회원등록.
	MemberVO getMember(String id); //회원아이디로 조회.
	boolean changePasswd(MemberVO member); //비번변경.
}
