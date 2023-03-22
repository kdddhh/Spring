<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>전체 회원 검색</title>
	<style>
		table, th, td {
			border: 2px solid gray;
			border-collapse: collapse;
		}
		
		th, td {
			padding: 10px;
		}
	</style>
	
</head>

<body>
	<h2>전체 회원</h2>
	<hr />
	
	<table>
		<tr>
			<th>이메일</th>
			<th>이름</th>
			<th>등록일자</th>
		</tr>
			${table}
	</table>
	
</body>
</html>