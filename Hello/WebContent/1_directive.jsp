<%@page import="java.util.Date,java.util.Calendar "%>
<!-- import를 나열할때는 ,로 하고 나열한다. -->

<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" %>


<%
	Date d = new Date();
	Calendar c = Calendar.getInstance();
%>
<%= c.get(Calendar.DAY_OF_MONTH) %>월
<%= c.get(Calendar.MONTH)+1 %>일
<%= c.get(Calendar.HOUR_OF_DAY) %>시
<%= c.get(Calendar.MINUTE) %>분
<%= c.get(Calendar.SECOND) %>초
<!-- 클라이언트의 시간이 아닌 서버의 시간을 알려준다. -->