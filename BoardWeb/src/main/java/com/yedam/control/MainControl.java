package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yedam.common.Control;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		String logId = (String) session.getAttribute("logId");
		// null
//		if (logId == null) {
//			resp.sendRedirect("loginForm.do");
//		} else {
			req.getRequestDispatcher("etc/main.tiles").forward(req, resp);
//		}
	}

}
