<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	// 쿠키 설정하기 : local에 저정하는 것
	response.addCookie(new Cookie("uid","kkk")); // 응답을 할때 쿠키를 저장한다. 따라서 resp를 사용
	response.addCookie(new Cookie("upw","10"));


%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쿠키</title>
</head>
<body>

</body>
</html>