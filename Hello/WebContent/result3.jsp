<%@page import="com.model.CStore"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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