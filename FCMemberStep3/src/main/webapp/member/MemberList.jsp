<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL을 사용한 회원 목록</title>
</head>
<body>
	<jsp:include page="/Header.jsp"></jsp:include>
	<h1>회원목록 : JSTL</h1>
	<hr>
	<a href="add.do">신규등록</a>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>이메일</th>
			<th>등록일자</th>
		</tr>
		<c:forEach var="m" items="${members}">
			<tr>
				<td>${m.mno}</td>
				<td><a href="update.do?mno=${m.mno}">${m.mname}</a></td>
				<td>${m.email}</td>
				<td>${m.cre_date}</td>
			</tr>
		</c:forEach>
	</table>
	<jsp:include page="/Tail.jsp"></jsp:include>
</body>
</html>