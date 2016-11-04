<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" errorPage="index.jsp"%>
<!--errorPage="index.jsp" 페이지에 에러가 발생하면 index.jsp 로 자동으로 이동한다.  -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
${num }, ${num1 }
<c:if test="${num1 == 100 }">
	<h3>if문 수행</h3>
</c:if>
	<c:choose>
		<c:when test="${num1 > 100 }">
			100보다 크다
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${num1 < 100 }">
			100보다 작다
		</c:when>
	<c:otherwise>
			100이다
	</c:otherwise>
	</c:choose><br />
<c:forEach var="i" begin="95" end="105">
	${i }    <br />
</c:forEach>

<c:catch var="e">
	<%
		int a = 10/0;
	%>
</c:catch>
${e.message }

