<%@page import="com.yedam.common.PageDTO"%>
<%@page import="com.yedam.common.SearchDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
	<%
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("blist");
	PageDTO pageDTO = (PageDTO) request.getAttribute("pageDTO");
	int paging = (int) request.getAttribute("page");
	String sc = (String) request.getAttribute("searchCondition");
	String kw = (String) request.getAttribute("keyword");
	
	%>
    <p><%=pageDTO %></p>
    <h3>게시글 목록</h3>	
    <!--  검색 조건 -->
    <form action="boardList.do" method="get">
    <div class="row">
    	<div class="col-sm-4">
    		<select name="searchCondition" class="form-control">
    			<option>선택하세요</option>
    			<option value="T" ${searchCondition == 'T' ? 'selected="selected"' : ''}>제목</option>
    			<option value="W" ${searchCondition == 'W' ? 'selected="selected"' : ''}>작성자</option>
    			<option value="TW" ${searchCondition == 'TW' ? 'selected="selected"' : ''}>제목&작성자</option>
    		</select>
    	</div>
		<div class="col-sm-4">
			<input type="text" name="keyword" class="form-control" value="<%=kw %>">
		</div>
		<div class="col-sm-2">
			<button type="submit" class="btn btn-info">검색</button>
		</div>
    </div>
    </form>
    <table class="table">
        <thead>
            <tr>
                <th>글번호</th><th>글제목</th><th>글쓴이</th><th>작성일</th>
            </tr>
        </thead>
        <tbody>
            <%
		    for (BoardVO board : list) {
		    %>
		    <tr>
                <td><%= board.getBoardNo() %></td>
		        <td><a href='board.do?page=<%=pageDTO.getCurrentPage() %>&bno=<%=board.getBoardNo() %>'><%= board.getTitle() %></a></td>
		        <td><%= board.getContent() %></td>
		        <td><%= board.getWriter() %></td>
		        <td><%= board.getWriteDate() %></td>
		    </tr>
		    <%} %>
        </tbody>
    </table>
    <!-- 페이징 -->
    <nav aria-label="...">
	  <ul class="pagination">
	    <%if (pageDTO.isPrev()) {%>
	    <li class="page-item">
	      <a class="page-link" href="boardList.do?page=<%=pageDTO.getStartPage() - 1%>&searchCondition=<%=sc%>&keyword=<%=kw%>">Previous</a>
	    <%} else {%>
	    <li class="page-item disabled">
	      <span class="page-link">Previous</span>
	    <%} %>
	    </li>
	    <%
	    for (int p = pageDTO.getStartPage(); p <= pageDTO.getEndPage(); p++) {
	        if (pageDTO.getCurrentPage() == p) {
	    %>
	    <li class="page-item active" aria-current="page">
	      <span class="page-link"><%=p %></span>
	    </li>
	    <%  } else {%>
        <li class="page-item"><a class="page-link" href="boardList.do?page=<%=p %>&searchCondition=<%=sc%>&keyword=<%=kw%>"><%=p %></a></li>
	    <%
	        }
	    }
	    %>
	    <%if (pageDTO.isNext()) {%>
	    <li class="page-item">
	      <a class="page-link" href="boardList.do?page=<%=pageDTO.getEndPage() + 1%>&searchCondition=<%=sc%>&keyword=<%=kw%>">Next</a>
	    <%} else {%>
	    <li class="page-item disabled">
	
      <span class="page-link">Next</span>
	    <%} %>
	    </li>
	  </ul>
	</nav>
<jsp:include page="includes/footer.jsp"></jsp:include>