package com.yedam.service;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.DataSource;
import com.yedam.mapper.MemberMapper;
import com.yedam.vo.MemberVO;

// 업무(service 구현객체)
public class MemberServiceImpl implements MemberService {

	SqlSession sqlSession = DataSource.getInstance().openSession(true);
	MemberMapper mapper = sqlSession.getMapper(MemberMapper.class);

	@Override
	public MemberVO login(String id, String pw) {
		return mapper.selectMember(id, pw);
	}

	@Override
	public boolean addMember(MemberVO member) {
		return mapper.insertMember(member) == 1;
	}

	@Override
	public MemberVO getMember(String id) {
		return mapper.selectMemberId(id);
	}

	@Override
	public boolean changePasswd(MemberVO member) {
		return false;
	}

}
