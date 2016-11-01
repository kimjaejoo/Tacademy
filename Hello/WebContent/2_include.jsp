<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP include</title>
<!-- css internal 방식 삽입 -->
<style type="text/css">
	div{
		margin: 0; padding: 0;
	}
	#wrap{
		width: 320px; height: 320px;
	}
	#header{
		width: 100%; height: 50px; background: lightgreen; text-align: center;
	}
	#contents{
		width: 100%; height: 220px; background: lightgrey;
	}
	#footer{
		width: 100%; height: 50px; background: pink;
	}
</style>
</head>
<body>

<!-- 태그에 직접 스타일을 주면 inline 이라고 한다. -->
<div id="wrap">
	<div id="header"><%@ include file="header.jsp" %></div> <!-- 페이지 지시자 -->
	<div id="contents">
	
	<%
		// scriptlet 사용하기 = 아래서 사용한것은 모두 내장객체임
		out.flush(); // 여기까지 만들어진 내용을 강제로 출력한다. -> 가급적으로 사용하지 않는다 성능저하에 원인이 된다.
		RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp"); // 내장객체로서 import하지 않아도 사용가능하다.
		dispatcher.include(request, response);
	%>
	
	</div>
	<div id="footer"></div>
</div>

</body>
</html>