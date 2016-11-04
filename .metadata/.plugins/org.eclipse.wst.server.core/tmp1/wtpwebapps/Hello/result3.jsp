<%@page import="com.model.CStore"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
// 하이브리드 앱에서 ajax 통신시 cross domain을 허용
response.setHeader("Access-Control-Allow-Origin", "*");
response.setHeader("Access-Control-Allow-Origin", "origin, x-requested-with, content-type, accept");
%>

{"data" : [
<c:forEach var="cs" items="${result }" varStatus="status">
		{
			"idx" : ${cs.idx},
			"name" : ${cs.name},
			"addr" : ${cs.addr}
		}
		<c:if test="${status.last == false}">
			,
		</c:if>
</c:forEach>
	]
}
