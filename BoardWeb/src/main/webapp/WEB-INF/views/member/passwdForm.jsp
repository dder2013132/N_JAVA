<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form name="passwdForm" action="authentificate.do" method="post">
  <table class="table">
    <tr>
      <th>현재비밀번호</th>
      <td><input type="password" name="current_pw" required></td>
    </tr>
    <tr>
      <th>새로운 비밀번호</th>
      <td><input type="password" name="new_pw" required></td>
    </tr>
    <tr>
      <th>비밀번호 확인</th>
      <td><input type="password" name="confirm_pw" required></td>
    </tr>
    <tr>
      <td colspan="2" align="center">
        <button type="submit">변경</button>
      </td>
    </tr>
  </table>
</form>

<script>
  document.forms.passwdForm.addEventListener('submit', function (e) {
    let cpw = document.querySelector('input[name="new_pw"]').value;
    let rpw = document.querySelector('input[name="confirm_pw"]').value;
    if (cpw != rpw) {
      alert('비밀번호가 동일하지 않습니다.')
      return;
    }
    this.submit();
  })
</script>