<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 변수 -->
<c:set var="num" value="100"/>
<c:set var="num1" value="100" scope="request"/>
<h3>&ltc:set 변수설정 (scope)에 따라 유효성 범위가 적용됨;</h3>
${num}
<br />
<jsp:forward page="custom1.jsp"></jsp:forward>