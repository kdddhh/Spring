<%@ page contentType="text/html; charset=UTF-8" %>

<%--
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>


<!-- 
      1. 사용자가 반드시 모든 입력 양식에 올바른 값을 입력한 후 submit 버튼을 클릭하여야 한다.
      
      [조건]
      1. 모든 입력 양식에는 반드시 값이 존재해야 한다.
      2. 이메일은 이메일 양식에 맞는 값이어야 한다.
      3. 이름은 2자 이상이어야 한다.
      4. 비밀번호와 비밀번호 확인은 4자 이상이어야 하고 서로 같은 값이어야 한다.
      
      [구현방법]
      1. 서버에서 구현
      2. 자바스크립트를 이용하여 브라우저에서 구현
      ======================================================================================================
      2. 회원 가입 양식에서 중복되는 이메일을 입력한 경우 다시 회원 가입 양식으로 돌아온다.
         1) 이전 사용자 입력(이메일, 이름) 값을 다시 보여줄 수 있어야 한다.
         
         [구현방법]
         이메일, 이름 입력 양식에서 EL Expression을 사용하여 그 값을 출력한다.
         -------------------------------------------------------
         2) 사용자에게 이메일이 중복되었다 라는 메시지를 보여줄 수 있어야 한다.
         
         [구현방법]
         1. JSTL을 이용하는 방법
         2. Model 객체를 사용하는 방법
 -->

<!DOCTYPE html>

<html>
<head>
   <meta charset="UTF-8">
   <title>회원가입: Step2</title>
   <style>
   label {
      display: inline-block;
      width: 120px;
   }
   </style>
   <script>
   function submitHandler() {
      var userName = document.querySelector('#name').value;
      var password = document.querySelector('#password');
      var confirmPassword = document.querySelector('#confirmPassword');
      // 뒤에 value를 붙여주면 사용자가 입력한 값이 된다.
      
      if(userName.length >= 2 && password.value.length >= 4 && password.value == confirmPassword.value) {
         alert("회원가입 처리를 수행합니다.");
      }
      else {
         alert("올바른 회원 정보를 입력하세요.");
         
         password.value = '';
         confirmPassword.value = '';
         
         return false;
         // false처리하면 나중에 동작하는 default 이벤트 처리가 동작하지 않는다.
      }
   }
   
   function init() {
      var registerForm = document.querySelector('#registerForm');
      registerForm.onsubmit = submitHandler;
   }
   
   window.onload = init;
   </script>
</head>

<body>

   <h1>회원가입</h1>
   <hr />
   <h3>회원 정보 입력</h3>
   
   <form:form action="step3" modelAttribute="formData" id="registerForm">
   <fieldset>
      <legend>회원 정보</legend>
      <label for="email">이메일:</label>
      <form:input type="email" path="email" required="required" /><br />

      <label for="name">이름:</label>
      <form:input path="name" required="required" /><br />

      <label for="password">비밀번호:</label>
      <form:password path="password" required="required" /><br />

      <label for="confirmPassword">비밀번호 확인:</label>
      <form:password path="confirmPassword" required="required" /><br />
      <input type="submit" value="회원가입" />
   </fieldset>
   </form:form>
   
   <!-- 
   Spring form태그를 이용하지 않은 HTML form태그
   <form action="step3" method="POST" id="registerForm">
   <fieldset>
      <legend>회원 정보</legend>
      <label for="email">이메일:</label>
      <input type="email" name="email" id="email" value="${formData.email}" required /><br />

      <label for="name">이름:</label>
      <input type="text" name="name" id="name" value="${formData.name}" required /><br />

      <label for="password">비밀번호:</label>
      <input type="password" name="password" id="password" required /><br />

      <label for="confirmPassword">비밀번호 확인:</label>
      <input type="password" name="confirmPassword" id="confirmPassword" required /><br />
      <input type="submit" value="회원가입" />
   </fieldset>
   </form>
   -->
   
   <%--
   JSTL을 사용하는 방법
   <c:if test="${registerRequest.email != null}" var="result">
      <c:set var="msg" value="중복된 이메일 주소가 발견되었습니다." />
   </c:if>
   
   <p><font color="red">${msg}</font></p>
    --%>
    
   <p><font color="red">${msg}</font></p>
   

</body>
</html>