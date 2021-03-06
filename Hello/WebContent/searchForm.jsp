<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<title>편의점 검색</title>
<style type="text/css">

	.container{
		margin-top: 100px;
	}
	#searchBtn{
		font-weight: bold;
	}

</style>
<script type="text/javascript">
	$(function(){
		$("#searchBtn").on("click", function(){
			console.log("지금 버튼을 누르고 있지롱");
			//alert($("input[name=keyword])").val();
			$.ajax({
				url : "/Hello/search", // 전송방식을 생략하면 기본값으로 get방식이 들어간다.
				data : "keyword =" + $("#txt").val(), // 아이디가 txt인 곳의 value값을 주소창에 넘긴다.
				console.log($("#txt").val());
				dataType : "json", // 전송하는 파일의 형태가 json파일이다.
				success : function(json){
					//alert(json.data[0].name);
					$("tbody").empty(); // 이전에 검색한 내용을 비운다.
					$.each(json.data, function(idx, item){
					var html = "<tr>"+
							   "<td>"+item.idx+"</td><td>"+item.name+"</td><td>"+item.addr+"</td>"+
					 		   "</tr>";
					$("tbody").append(html); // html 태그안에 내용을 넣는다.
					$("tbody>tr:last").on("click", function(){
							alert(item.name);		
						});
					});
					//$("tbody>tr:odd").css();
					//$("tbody>tr:even").css();
				},
				error : function(err){
					alert("err : " + err);
				}
			})
		})
	})
</script>
</head>
<body>
	<div class="container">
		
			<div>
				<div class="form-group">
					<input class="form-control" id="txt" type="text" name="keyword" placeholder="검색어를 입력하세요" />
				</div>
					<button id="searchBtn" class="btn btn-default btn-lg">검색</button>
				</div>
		
		<table id="result" class="table table-striped">
		<thead>
			<tr>
				<td>고유번호</td>
				<td>편의점 이름</td>
				<td>주소</td>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	</div>
	<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</body>
</html>