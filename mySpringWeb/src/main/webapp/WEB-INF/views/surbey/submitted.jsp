<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>설문 응답 내용</title>
</head>

<body>
	<h2>설문 응답 결과</h2>
	<hr />
	
	<h5>### 응답 내용 ###</h5>
	<c:forEach var = "result" items = "${ansData.responses}" varStatus = "status">
		<ul>
			<li>${status.count}번 문항: ${result}</li>
		</ul>
	</c:forEach>
	
	<h5>### 응답자 정보 ###</h5>
		<h5>응답자 위치: ${ansData.res.location}</h5>
		<h5>응답자 나이: ${ansData.res.age}</h5>
	
	
</body>
</html>