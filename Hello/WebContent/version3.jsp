<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Object jsonStr = request.getAttribute("jsonStr");
	out.print(jsonStr);

%>

${jsonStr }
