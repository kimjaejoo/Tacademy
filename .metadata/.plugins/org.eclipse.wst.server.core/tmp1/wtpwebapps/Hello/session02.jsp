<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>session01의 입력값 : <%=session.getAttribute("uid") %></h3>
	<a href="#" onclick="history.back();">이전</a>
	<a href="session03.jsp?upw=1234">다음</a>
	
</body>
</html>