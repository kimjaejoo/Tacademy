<%@page import="com.mybean.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String uid = request.getParameter("uid");
		String upw = request.getParameter("upw");
	%>
	<%=uid%>
	/
	<%=upw%>
	<!-- EL -->
	${param.uid } / ${param.upw }
	
	<!-- 표준액션 -->
	
	<!-- useBean 의 id는 객체의 역할 -->
	<jsp:useBean id="login" class="com.mybean.LoginBean"></jsp:useBean>
	
	<!-- 모든 파라미터를 한번에 세팅 -->
	<jsp:setProperty property="*" name="login" />
	
	<!-- parameter를 받는다 -->
	<jsp:getProperty property="uid" name="login"/>
	<jsp:getProperty property="upw" name="login"/>
	
</body>
</html>