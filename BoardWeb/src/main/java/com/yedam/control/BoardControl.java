package com.yedam.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;

import com.yedam.common.Control;
import com.yedam.common.DataSource;
import com.yedam.mapper.BoardMapper;
import com.yedam.vo.BoardVO;

public class BoardControl implements Control{
	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSession sqlSession = DataSource.getInstance().openSession();
		BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
		int bno = Integer.parseInt(req.getParameter("bno"));
		BoardVO board = mapper.selectOne(bno);
		req.setAttribute("board", board);
		//req.getRequestDispatcher("/WEB-INF/views/boardOne.jsp").forward(req, resp);
		req.getRequestDispatcher("/WEB-INF/views/board.jsp").forward(req, resp);
		System.out.println("BoardControl 실행.");
	}
	
}
