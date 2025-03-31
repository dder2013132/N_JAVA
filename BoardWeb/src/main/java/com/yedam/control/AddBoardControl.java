package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.common.Control;

public class AddBoardControl implements Control {
	
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if(req.getMethod().equals("GET")) {
			// url 직접 입력, 링크
			req.getRequestDispatcher("/WEB-INF/views/addForm.jsp").forward(req, resp);
			
		} else if (req.getMethod().equals("POST")) {
			resp.sendRedirect("boardList.do");
		}
	}
}
