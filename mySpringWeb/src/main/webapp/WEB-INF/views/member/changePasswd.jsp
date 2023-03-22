<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
<head>
   <meta charset="UTF-8">
   <title>회원 비밀번호 변경</title>
   <style>
   label {
      display: inline-block;
      width: 150px;
   }
   </style>
   <script src = "/mySpringWeb/res/script/changePasswd.js" charset = "UTF-8">
   	
   </script>
</head>

<body>

   <h1>회원 비밀번호 변경</h1>
   <hr />
   <h3>비밀번호 변경</h3>
   
   <form action="changePasswdProcess" method="POST" id = "changePasswd">
   <fieldset>
      <label for="email">이메일:</label>
      <input type = "email" value = "${auth.email}" disabled /><br />

      <label for="password">현재 비밀번호:</label>
      <input type="password" name="password" id="password" required /><br />

      <label for="newPassword">변경 비밀번호:</label>
      <input type="password" name="newPassword" id="newPassword" required /><br />
      
      <label for="confirmPassword">변경 비밀번호 확인:</label>
      <input type="password" id="confirmPassword" required /><br />
      <!-- 검증을 위한 목적이기 때문에 서버에 넘길 필요가 없다 따라서 name 속성이 없음 -->
      
      <input type = "hidden" name = "email" value = "${auth.email}" />
      <input type="submit" value="비밀번호 변경" />
   </fieldset>
   </form>
   
   <p><font color="red">${changePasswdMsg}</font></p>
   

</body>
</html>