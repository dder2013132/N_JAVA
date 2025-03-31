<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%BoardVO board = (BoardVO) request.getAttribute("brd"); %>
    <ul>
	    <li>글번호: <%= board.getBoardNo() %></li>
	    <li>글제목: <%= board.getTitle() %></li>
	    <li>글내용: <%= board.getContent() %></li>
	    <li>글쓴이: <%= board.getWriter() %></li>
	    <li>글쓴날: <%= board.getWriteDate() %></li>
    </ul>
</body>
</html>