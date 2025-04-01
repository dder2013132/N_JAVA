<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- modifyBoard.jsp -->
<jsp:include page="includes/header.jsp"/>
<h3>수정화면(modifyForm.jsp)</h3>
<%
  BoardVO board = (BoardVO) request.getAttribute("board");
  String paging = (String) request.getAttribute("page");
%>
<form action="modifyBoard.do">
  <input type="hidden" name="bno" value="<%=board.getBoardNo() %>">
  <input type="hidden" name="page" value="<%=paging %>">
  <table class="table">
    <tr>
      <th>글번호</th><td><%=board.getBoardNo() %></td>
      <th>작성자</th><td><%=board.getWriter() %></td>
    </tr>
    <tr>
      <th>제목</th>
      <td colspan="3"><input type="text" name="title" class="form-control" value="<%=board.getTitle() %>"></td>
    </tr>
    <tr>
      <th>내용</th>
      <td colspan="3">
        <textarea class="form-control" name="content" cols="30" rows="3"><%=board.getContent() %></textarea>
      </td>
    </tr>
    <tr>
      <th>작성일시</th>
      <td colspan="3"><%=board.getWriteDate() %></td>
    </tr>
    <tr>
      <td colspan="3" align="center">
        <input type="submit" value="수정" class="btn btn-warning">
      </td>
    </tr>
  </table>
</form>
<jsp:include page="includes/footer.jsp"/>