<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	String n1 = request.getParameter("n1");
	String n2 = request.getParameter("n2");
	String result = "로그인 실패";
	if(n1.equals("person") && n2.equals("1234")) {
		result = n1 + "님 반갑습니다";
	} 
	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 결과</h2>
	<hr>
	<%=result %>
</body>
</html>