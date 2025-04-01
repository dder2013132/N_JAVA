<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"/>
  <!-- webapp/WEB-INF/views/board.jsp -->
  <%
    BoardVO board = (BoardVO) request.getAttribute("board");
  	String paging = (String) request.getAttribute("page");
  %>
  <h3>상세페이지(board.jsp)</h3>
  <form action="modifyForm.do">
  <input type="hidden" name="bno" value="<%=board.getBoardNo() %>">
  <input type="hidden" name="page" value="<%=paging %>">
  <table class="table">
    <tr>
      <th>글번호</th><td><%=board.getBoardNo() %></td>
      <th>작성자</th><td><%=board.getWriter() %></td>
    </tr>
    <tr>
      <th>제목</th>
      <td colspan="3"><%=board.getTitle() %></td>
    </tr>
    <tr>
      <th>내용</th>
      <td colspan="3">
        <textarea class="form-control" cols="30" rows="3" readonly><%=board.getContent() %></textarea>
      </td>
    </tr>
    <tr>
      <th>작성일시</th>
      <td colspan="3"><%=board.getWriteDate() %></td>
    </tr>
    <tr>
      <td colspan="3" align="center">
        <input type="submit" value="수정" class="btn btn-warning">
        <button type="button" class="btn btn-danger">삭제</button>
      </td>
    </tr>
  </table>
  </form>
  <p><a href='boardList.do'>목록이동</a></p>
  <script>
    // 삭제버튼에 이벤트등록.
    document.querySelector('button.btn.btn-danger')
        .addEventListener('click', deleteFnc);
    // 삭제함수.
    function deleteFnc() {
    	location.href = 'deleteForm.do?bno=<%=board.getBoardNo() %>'; // 삭제화면 -> 삭제처리.
    }
  </script>
<jsp:include page="includes/footer.jsp"/>
