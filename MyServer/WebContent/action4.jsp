<%@page import="com.mybean.LoginBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	<jsp:getProperty property="uid" name="loginBean"/>
${param.uid}<br />
${ip }
${host}
${user}