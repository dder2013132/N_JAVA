package main.java.com.proj.view;

import java.util.Scanner;

import main.java.com.proj.dao.MemberDAO;

public class LoginView {
	MemberDAO memberDAO;
	Scanner scn = new Scanner(System.in);
	public LoginView() {
		LgView();
	}
	
	public void LgView() {
		System.out.println("-----------------------------");
		System.out.println("---------로그인   회원가입-------");
		System.out.println("-----------------------------");
		System.out.println("-------1.로그인  2.회원가입------");
		String slc = scn.nextLine();
		boolean run = true;
		while(run) {
			switch(slc) {
			case "1": loginUI(); run = false; 
			case "2": registerUI(); break;
			}
		}
	}
	
	public void loginUI() {
		System.out.println(" 아이디 : ");
		String user = scn.nextLine();
		System.out.println("비밀번호 : ");
		String pwd = scn.nextLine();
		memberDAO.login(user, pwd);
	}
	
	public void registerUI() {
		System.out.println(" 아이디 : ");
		String user = scn.nextLine();
		System.out.println("비밀번호 : ");
		String pwd = scn.nextLine();
		System.out.println("   이름 : ");
		String name = scn.nextLine();
		System.out.println(" 이메일 : ");
		String email = scn.nextLine();
		System.out.println("전화번호 : ");
		String phone = scn.nextLine();
		System.out.println("   주소 : ");
		String addr = scn.nextLine();
		memberDAO.register(user, pwd, name, email, phone, addr);
	}
}
