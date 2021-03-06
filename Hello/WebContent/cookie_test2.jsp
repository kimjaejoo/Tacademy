
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// 실습 => 2개의 입력창의 쿠키값을 읽어서 셋팅하기
	// 단 , 쿠키값을 뽑는 멤버 메소드를 구성하여 데이터를 획득하고 expression으로 값세팅
	// 이미 넣어져 있는 값을 이용하여
%>
<%!// 쿠키값을 뽑는메서드를 사용한다.
	public String getCookie(String key, Cookie[] cookies) {
		if (cookies != null)
			for (Cookie c : cookies) {
				if (c.getName().equals(key)) // 동일한 키가 존재할시 
				{
					return c.getValue(); // 값획득
				}
			}
		return "";
	}
%>
<%
	// 쿠키 읽기
	Cookie[] cookies = request.getCookies();
	if (cookies != null)
		for (Cookie c : cookies) {
			System.out.println(c.getName() + " / " + c.getValue());
		}
	// 실습 => 2개의 입력창에 각각 쿠키값을 읽어서 세팅하시오.
	//        단, 쿠키값을 뽑는 맴버 메소드를 구성하여 데이터를 획득하고 expression으로 값세팅
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<!-- jquery 추가하기 -->
<script type="text/javascript">
	
	// interpreter 방식으로 위에서 한줄씩 읽어서 실행한다.
	// html 문서가 모두 메모리에 올라왔다 !! 로드됨!!
	$(document).ready(function() {
		//alert("hi");
		// selector : 대상을 찾는다 + 이벤트, 기타 처리 
		// css 제공 / $("*") , $("body"), $("fieldset input")
		// $("fieldset>input"), $(filedset, input), $(".style"),
		//$("#change").onclick(function(){});
		console.log($("#change"));
		$("#change").on("click", function() {
			// ajax 처리 form이 없어서 사용한다.
			// ajax를 만들어 사용할때 만드는 기초적인 스타일
			$.ajax({
				// key and value를 넣는다.
				url : "/Hello/cookieProc", // ajax를 넘길 페이지
				type : "post", // servlet file에서 처리하는 방식
				data : $("form").serialize(), // 아래의 html 코드에서 작성되어지거나 특정 name값을 넣을수있다.
				dataType : "text", // ajax로 값을 넘길때 사용되는 data의 type 지금은 String = text를 사용한다.
				success : function(data) {
					// 실습성격상 응답데이트는 현재 의미없다.
					console.log("성공", data);
				},
				error : function(err) {
					// 실습성격상 응답데이트는 현재 의미없다.
					console.log("실패", err);
				}
			});
		});
		$("#delete").on("click", function() {
			$.ajax({
				// key and value를 넣는다.
				url : "/Hello/cookieProc",
				type : "delete",
				data : "delkey=age", // 
				dataType : "text", // 밑의 데이터가 json타입으로 들어온다.
				success : function(data) {
					// 실습성격상 응답데이트는 현재 의미없다.
					console.log("성공", data);
				},
				error : function(err) {
					// 실습성격상 응답데이트는 현재 의미없다.
					console.log("실패", err);
				}
			});
		});
	});
</script>
</head>
<body>

	<%=getCookie("age", cookies)%>

	<fieldset>
		<form>
			<input type="text" name="uid" value="<%=getCookie("uid", cookies)%>" /><br />
			<input type="text" name="upw" value="<%=getCookie("upw", cookies)%>" />
			<button id="change">변경</button>
			<button id="delete">삭제</button>
			<input type="button" value="버튼" id="btn" />
		</form>
	</fieldset>
</body>
</html>
<%
	
%>
