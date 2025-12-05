<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="/Header.jsp"></jsp:include>
	<h1>회원 목록(View 분리)</h1>
	<hr>
	<form action="add.do" method="post">
		이름 : <input type="text" name="mname"><br>
		이메일: <input type="text" name="email"><br>
		비밀번호 : <input type="password" name="pwd"><br>
		<input type="submit" value="등록">
		<input type="reset" value="취소">
	</form>
	<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>