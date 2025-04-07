<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="includes/header.jsp" />
<table class="table">
  <tr>
    <td>안녕하세요 ${!empty logId ? userName : 'guest'}!!</td>
    <td>권한은 ${!empty logId ? (not empty responsibility and fn:toLowerCase(fn:trim(responsibility)) == 'user' ? '일반사용자' : '관리자') : '손님입니다'} </td>
  </tr>
</table>
    <p>권한 ${responsibility }</p>
<jsp:include page="includes/footer.jsp" />