<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<jsp:include page="/Header.jsp"></jsp:include>
	
		<h1>Login</h1>
		<hr>
		<form action="login.do" method="post">
			이메일 : <input type="text" name="email"><br>
			비밀번호 : <input type="password" name="pwd"><br>
			<input type="submit" value="로그인"><br>
			<input type="reset" value="취소"><br>	
		</form>
	<jsp:include page="/Tail.jsp"></jsp:include>

</body>
</html>