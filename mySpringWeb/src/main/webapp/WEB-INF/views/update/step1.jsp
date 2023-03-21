<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 변경</title>
</head>
 </style>
   <script>
   function submitHandler() {
      var password = document.querySelector('#password');
      var confirmPassword = document.querySelector('#confirmPassword');
      // 뒤에 value를 붙여주면 사용자가 입력한 값이 된다.
      
      if(confirmPassword.value.length >= 4 && password.value.length >= 4) {
         alert("비밀번호는 4자리 이상 입력해주세요");
      }
      else if(password.value == confirmPassword.value){
         alert("비밀번호가 일치하지 않습니다.");
         
         password.value = '';
         confirmPassword.value = '';
         
         return false;
         // false처리하면 나중에 동작하는 default 이벤트 처리가 동작하지 않는다.
      }
   }
   
   function init() {
      var registerForm = document.querySelector('#updateForm');
      registerForm.onsubmit = submitHandler;
   }
   
   window.onload = init;
   </script>
<body>
	<hl>비밀번호 변경</hl>
	<hr />
	
	<form method="POST" id="updateForm">
   <fieldset>
      <legend>변경 정보</legend>
      <label for="email">이메일:</label>
      <input type="email" name="email" id="email" required /><br />

      <label for="password">비밀번호:</label>
      <input type="password" name="password" id="password" required /><br />

      <label for="confirmPassword">비밀번호 확인:</label>
      <input type="password" name="confirmPassword" id="confirmPassword" required /><br />
      <input type="submit" value="변경" />
   </fieldset>
   </form>
   
   <p><font color="red">${msg}</font></p>
   
</body>
</html>