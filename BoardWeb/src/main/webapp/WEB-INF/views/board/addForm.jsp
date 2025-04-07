<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form action="addBoard.do" method="post">
  <table class="table">
    <tr>
      <th>글제목</th>
      <td><input class="form-control" type="text" name="title" required></td>
    </tr>
    <tr>
      <th>작성자</th>
      <td>${logId }
        <input class="form-control" type="hidden" name="writer" value="${logId }" required>
      </td>
    </tr>
    <tr>
      <th>본문</th>
      <td><textarea class="form-control" name="content" rows="3" cols="40" required></textarea></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <input type="submit" class="btn btn-primary">
        <input type="reset" class="btn btn-secondary">
      </td>
    </tr>
  </table>
</form>
