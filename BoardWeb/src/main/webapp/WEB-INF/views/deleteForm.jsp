<%@ page import="com.yedam.vo.BoardVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<%
BoardVO board = (BoardVO) request.getAttribute("board");
%>
<h3>게시글 삭제</h3>
<div class="alert alert-danger">
    다음 게시글을 삭제하시겠습니까?
</div>
<table class="table">
    <tr>
        <th>글번호</th><td><%=board.getBoardNo() %></td>
        <th>작성자</th><td><%=board.getWriter() %></td>
    </tr>
    <tr>
        <th>제목</th><td colspan="3"><%=board.getTitle() %></td>
    </tr>
    <tr>
        <th>작성일시</th><td colspan="3"><%=board.getWriteDate() %></td>
    </tr>
    <tr>
        <td colspan="4" align="center">
            <a href="deleteBoard.do?bno=<%=board.getBoardNo() %>" class="btn btn-danger">삭제하기</a>
            <a href="boardList.do" class="btn btn-secondary">취소</a>
        </td>
    </tr>
</table>
<jsp:include page="includes/footer.jsp"></jsp:include>