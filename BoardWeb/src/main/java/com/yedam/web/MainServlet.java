package com.yedam.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.yedam.common.DataSource;
import com.yedam.dao.BoardDAO;
import com.yedam.vo.BoardVO;

/*
 * tomact(WAS) 의 규칙에 따라 작성.
 * 1. url 패턴 (ex:http://localhost/BoardWeb/welcom)
 * 2. 서블릿클래스 (init() -> service() -> destroy())
 */
public class MainServlet extends HttpServlet{
	//생성자
	public MainServlet() {
		System.out.println("MainServlet() 호출 성공");
	}
	//서블릿의 생명주기(init)
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("최초호출 한번만 실행 init() 메소드 입니다");
	}	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=utf-8");
		System.out.println("호출될때마다 실행되는 service() 메소드 입니다");
		//BoardDAO dao = new BoardDAO();
		//List<BoardVO> list = dao.boardList();
		SqlSessionFactory sqlSessionFactory = DataSource.getInstance();
		try (SqlSession session = sqlSessionFactory.openSession()) {
			List<BoardVO> list = session.selectList("com.yedam.mapper.BoardMapper.selectBoard");
			PrintWriter out = resp.getWriter();
			String html = "<h3>게시글목록</h3>";
			html += "<table border='2'";
			html += "<thead><tr><th>글번호</th><th>제목</th><th>글내용</th><th>작성자</th><th>작성일시</th></tr></thead>";
			html += "<tbody>";
			for(BoardVO board : list) {
				html += "<tr>";
				html += "<td>" + board.getBoardNo() + "</td>";
				html += "<td>" + board.getTitle() + "</td>";
				html += "<td>" + board.getContent() + "</td>";
				html += "<td>" + board.getWriter() + "</td>";
				html += "<td>" + board.getWriteDate() + "</td>";
				html += "</tr>";
			}
			
			html += "</tbody></table>";
			out.print(html);
		}
	}
	@Override
	public void destroy() {
		System.out.println("서버가 종료될때 호출되는 destroy() 메소드 입니다");
	}
}
