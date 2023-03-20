<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>회원가입 : Step2</title>
	
	<style>
		label {
			display: inline-block;
			width : 120px;
		}
	</style>
</head>
<body>

	<h1>회원가입</h1>
	<hr />
	
	<h3>회원 정보 입력</h3>
	
	<form action="step3" method="post">
		<fieldset>
			<legend>회원 정보</legend>
			
			<label for="email">이메일 : </label>
			<input type="email" name="email" id="email" /><br />
			
			<label for="name">이름 : </label>
			<input type="text" name="name" id="name" /><br />
			
			<label for="password">비밀번호 : </label>
			<input type="password" name="password" id="password" /><br />
			
			<label for="repasswd">비밀번호 확인 : </label>
			<input type="password" name="repasswd" id="repasswd" /><br />
			
			<input type="submit" value="회원가입" />
		</fieldset>
	</form>
</body>
</html>