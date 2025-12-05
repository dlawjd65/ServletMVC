<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 상세 정보</title>
</head>
<body>
	<jsp:include page="/Header.jsp"></jsp:include>
	<h1>회원 상세 정보</h1>
	<hr>
	<form action="update.do" method="post">
		번호 : <input type="text" name="mno" value="${member.mno}" readonly><br>
		이름 : <input type="text" name="mname" value="${member.mname}"><br>
		이메일: <input type="text" name="email" value="${member.email}"><br>
		가입날짜: ${member.cre_date} <br>
		수정날짜: ${member.mod_date} <br>
		<input type="submit" value="수정">
		<input type="reset" value="취소">
		<a href="delete.do?mno=${member.mno}">삭제</a>
	</form>
	<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>