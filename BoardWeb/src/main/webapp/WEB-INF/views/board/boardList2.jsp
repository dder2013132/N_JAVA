<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="https://cdn.datatables.net/2.2.2/js/dataTables.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/2.2.2/css/dataTables.dataTables.css">

<!-- webapp/WEB-INF/views/boardList.jsp -->
<!-- blist, paging, searchCondition, keyword -->
<h3>게시글 목록(boardList.jsp)</h3>
<!-- 검색조건. -->
<form action="boardList.do">
  <div class="row">
    <div class="col-sm-4">
      <select name="searchCondition" class="form-control">
        <option>선택하세요</option>
        <option value="T" ${searchCondition =='T' ? 'selected' : '' }>제목</option>
        <option value="W" ${searchCondition =='W' ? 'selected' : '' }>작성자</option>
        <option value="TW" ${searchCondition =='TW' ? 'selected' : '' }>제목&작성자</option>
      </select>
    </div>
    <div class="col-sm-4">
      <input type="text" name="keyword" class="form-control" value="${keyword }">
    </div>
    <div class="col-sm-2">
      <button type="submit" class="btn btn-info">검색</button>
    </div>
  </div>
</form>

<table class="display" id="example" style="width:100%">
  <thead>
    <tr>
      <th>글번호</th>
      <th>제 목</th>
      <th>작성자</th>
      <th>작성일시</th>
    </tr>
  </thead>
  <tbody>
    <c:forEach var="board" items="${blist }">
      <tr>
        <td><c:out value="${board.boardNo }" /></td>
        <td><a href='board.do?page=${paging.currentPage }&bno=${board.boardNo}'><c:out value="${board.title }" /></a></td>
        <td><c:out value="${board.writer }" /></td>
        <td><fmt:formatDate value="${board.writeDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
      </tr>
    </c:forEach>
  </tbody>
</table>

<!-- paging 처리. -->
<script>
   new DataTable('#example');
</script>
