<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- modifyBoard.jsp -->
<h3>수정화면(modifyBoard.jsp)</h3>
<!-- board, page -->
<form action="modifyBoard.do">
  <input type="hidden" name="bno" value="${board.boardNo }">
  <input type="hidden" name="page" value="${page }">
  <table class="table">
    <tr>
      <th>글번호</th><td><c:out value="${board.boardNo }"/></td>
      <th>작성자</th><td><c:out value="${board.writer }"/></td>
    </tr>
    <tr>
      <th>제목</th>
      <td colspan="3"><input type="text" name="title" class="form-control" value="${board.title }"></td>
    </tr>
    <tr>
      <th>내용</th>
      <td colspan="3">
        <textarea class="form-control" name="content" cols="30" rows="3"><c:out value="${board.content }"/></textarea>
      </td>
    </tr>
    <tr>
      <th>작성일시</th>
      <td colspan="3"><fmt:formatDate value="${board.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
    </tr>
    <tr>
      <td colspan="3" align="center">
        <input type="submit" value="수정" class="btn btn-warning">
      </td>
    </tr>
  </table>
</form>
