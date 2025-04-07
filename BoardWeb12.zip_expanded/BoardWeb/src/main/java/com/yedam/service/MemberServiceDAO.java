package com.yedam.service;

import com.yedam.vo.MemberVO;

// MemberService(인터페이스) - 규칙.
// MemberServiceDAO  - jdbc 기능구현.
// MemberServiceImpl - mybatis 기능구현.

public class MemberServiceDAO implements MemberService {

	@Override
	public MemberVO login(String id, String pw) {
		return null;
	}

	@Override
	public boolean addMember(MemberVO member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public MemberVO getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changePasswd(MemberVO member) {
		// TODO Auto-generated method stub
		return false;
	}

}
