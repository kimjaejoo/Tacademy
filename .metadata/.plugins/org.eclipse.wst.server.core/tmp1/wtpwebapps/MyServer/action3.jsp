<%@page import="com.mybean.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String ip = request.getRemoteAddr();
	String host = request.getRemoteHost();
	String user = request.getRemoteUser();
	
	request.setAttribute("ip", ip);
	request.setAttribute("host", host);
	request.setAttribute("user", user);
%>
<jsp:forward page="action4.jsp"></jsp:forward>
<jsp:useBean id="loginBean" class="com.mybean.LoginBean" scope="request"></jsp:useBean>

	<!-- 모든 파라미터를 한번에 세팅 -->
	<jsp:setProperty property="*" name="login" />
	
	<!-- parameter를 받는다 -->
	<jsp:getProperty property="uid" name="login"/>
	<jsp:getProperty property="upw" name="login"/>