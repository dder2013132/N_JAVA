package main.java.com.proj.service;

import java.util.Date;

import main.java.com.proj.dao.MemberDAO;
import main.java.com.proj.model.Member;

public class MemberService {
	private MemberDAO memberDAO;
	
	public MemberService() {
		memberDAO = new MemberDAO();
	}
	
	public boolean register(Member member) {
		return memberDAO.register(member.getMemberId(), member.getpassword(), member.getUsername(), member.getEmail(), member.getPhone(), member.getAdress());
	}
	
	public boolean login(String userId, String pwd) {
		return memberDAO.login(userId, pwd);
	}
	
	
}