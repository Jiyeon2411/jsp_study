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
		int num = Integer.parseInt(request.getParameter("num"));
		for(int j=1; j<=9; j++) {
			out.println(num + " * " + j + " = " + 5*j + "<br>");
		}
	%>
</body>
</html>