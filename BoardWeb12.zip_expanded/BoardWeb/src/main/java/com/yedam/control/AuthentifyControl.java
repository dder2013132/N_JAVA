package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;
import com.yedam.service.MemberService;
import com.yedam.service.MemberServiceImpl;
import com.yedam.vo.MemberVO;

public class AuthentifyControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (req.getMethod().equals("GET")) {
			req.getRequestDispatcher("member/passwdForm.tiles").forward(req, resp);

		} else if (req.getMethod().equals("POST")) {
			HttpSession session = req.getSession();
			String logId = (String) session.getAttribute("logId");

			MemberService svc = new MemberServiceImpl();
			MemberVO mvo = svc.getMember(logId);

			String cpw = req.getParameter("current_pw");

			if (mvo.getMemberPw().equals(cpw)) {// 비밀번호 다름.
				req.setAttribute("msg", "회원비밀번호가 다릅니다.");
				req.getRequestDispatcher("member/passwdForm.tiles").forward(req, resp);
				return;
			}

			String npw = req.getParameter("new_pw");
//			String rpw = req.getParameter("confirm_pw");
			mvo.setMemberPw(npw);

			svc.changePasswd(mvo);
			resp.sendRedirect("loginForm.do");
		}

	}

}
