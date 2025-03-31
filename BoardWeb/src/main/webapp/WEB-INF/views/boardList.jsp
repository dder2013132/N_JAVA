<%@page import="com.yedam.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
	<%
		List<BoardVO> list = (List<BoardVO>) request.getAttribute("blist");
	%>
	<h3>게시글 목록</h3>
	<table class="table">
	  <thead>
	  	<tr><th>글번호</th><th>제 목</th><th>작성자</th><th>작성일시</th></tr>
	  </thead>
	  <tbody>
	  	<%for (BoardVO board : list) {%>
	  		<tr>
	  			<td><%=board.getBoardNo() %></td>
	  			<td><a href='board.do?bno=<%=board.getBoardNo() %>'><%=board.getTitle() %></a></td>
	  			<td><%=board.getWriter() %></td>
	  			<td><%=board.getWriteDate() %></td>
	  		</tr>
	  		
	  	<%} %>
<jsp:include page="includes/footer.jsp"></jsp:include>