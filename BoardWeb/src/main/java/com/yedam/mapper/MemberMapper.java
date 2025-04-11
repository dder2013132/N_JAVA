package com.yedam.mapper;

import org.apache.ibatis.annotations.Param;

import com.yedam.vo.MemberVO;

public interface MemberMapper {
	//아이디&비밀번호 단건조회.
	MemberVO selectMember(@Param("id") String id, @Param("pw") String pw);
	int insertMember(MemberVO member); // 입력.
	MemberVO selectMemberId(String id);
	int updatePasswd(MemberVO member);
}
