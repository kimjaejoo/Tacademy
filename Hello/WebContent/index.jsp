<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%

response.sendRedirect("/Hello/searchForm.jsp");

%>


	<h3>GET/POST test</h3>
	<fieldset>
		<form action="/Hello/DBTest01" method="post">
			<input type="text" name="uid" placeholder = "아이디" required/> <br />
			 <!-- required 를 넣어주면 빈칸일 경우 넘어가지 않는다. -->
			<input type="password" name="upw" id="" placeholder = "비밀번호" required/> <br />
			<input type="submit" value="전송" />
		</form>
	</fieldset>
	
	<!-- get 방식을 사용하면 주소창에 값이 보이지만, post를 사용하면 보이지 않는다. 두개의 방식이 다르며 속도또한 다르다. -->
</body>
</html>