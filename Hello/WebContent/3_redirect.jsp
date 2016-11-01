<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 페이지 redirect -->
<%
	// 조건처리후 화면 전환
	// dispacher.forword() => 동일도메인에서만 가능
	response.sendRedirect("http://www.naver.com"); // 다른도메인도 가능, 데이터의 전달은 get방식을 사용한다.
	
%>