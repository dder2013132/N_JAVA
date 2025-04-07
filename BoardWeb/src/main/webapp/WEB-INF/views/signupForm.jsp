<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="includes/header.jsp"></jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<h2>회원가입~~</h2>
<form action="signup.do" method="post" enctype="multipart/form-data">
    <table class="table">
        <tr>
            <th>아이디</th><td><input class="form-control" type="text" name="userId" placeholder="아이디를 입력해주세요."></td>
        </tr>
        <tr>
            <th>비밀번호</th><td><input class="form-control" type="password" name="userPw" placeholder="비밀번호를 입력해주세요."></td>
        </tr>
        <tr>
            <th>비밀번호 확인</th><td><input class="form-control" type="password" name="userPwc" placeholder="비밀번호를 다시 입력해주세요."></td>
        </tr>
        <tr>
            <th>이름</th><td><input class="form-control" type="text" name="userName" placeholder="이름을 입력해주세요."></td>
        </tr>
        <tr>
            <th>이미지</th><td><input class="form-control" type="file" name="userImg"></td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <button type="submit" class="btn btn-primary">가입하기</button>
                <button type="reset" class="btn btn-secondary">초기화</button>
            </td>
        </tr>
    </table>
</form>
<jsp:include page="includes/footer.jsp"></jsp:include>