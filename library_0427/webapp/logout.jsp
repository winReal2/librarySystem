<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	// 세션무효화 처리 
	session.invalidate();

	// 세션아이디 새로 발급하는 역할
	request.getSession(true);
	
	//메인페이지로 이동
	response.sendRedirect("login.jsp");
%>
</body>
</html>