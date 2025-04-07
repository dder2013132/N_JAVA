<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>webapp/jstl.jsp</title>
</head>
<body>
  <%
    request.setAttribute("name", "Hong");
    List<String> list = new ArrayList<String>();
    list.add("Hello");
    list.add("Nice");
    list.add("Good");
    request.setAttribute("list", list);
    request.setAttribute("today", new Date());
  %>
  <!-- jstl 변수초기화. Expression Language (EL)-->
  <%String msg = "Hello"; %>
  <c:set var="msg" value="Hello"/>
  <p><c:out value="${msg }"></c:out></p>
  <p>request객체의 name어트리뷰트의 값은 ${name }</p>
  <p>session객체의 logId어트리뷰의 값은 ${logId }</p>
  
  <!-- 조건문 -->
  <c:if test="${msg == 'Hello' }">
    <c:out value="msg의 값이 Hello입니다."></c:out>
  </c:if>
  
  <c:set var="age" value="19"></c:set>
  <c:choose>
    <c:when test="${age >= 20 }">
      <p>성년입니다</p>
    </c:when>
    <c:otherwise>
      <p>미성년입니다</p>
    </c:otherwise>
  </c:choose>
  
  <!-- 반복문. -->
  <% for(int i=1;i<=10;i++) {} %>
  <c:forEach var="i" begin="1" end="10" step="2">
    <p>2 * ${i } = ${2 * i }</p>
  </c:forEach>
  
  <ul>
  <c:forEach var="str" items="${list }">
    <li><c:out value="${str }"></c:out></li>
  </c:forEach>
  </ul>
  
  <!-- fmt -->
  <fmt:formatDate value="${today }" pattern="yyyy-MM-dd HH:mm:ss" />
  <c:set var="cnt" value="100000"></c:set>
  <p><fmt:formatNumber value="${cnt }" pattern="##,###"/></p>

  <script>
    let msg = 'Hello';
    console.log(`${msg}`);
  </script>
</body>
</html>