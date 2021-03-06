<%@page import="com.model.ResVersion"%>
<%@page import="com.model.VersionVO"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.google.gson.Gson"%>
<%@page import="com.model.ResError"%>
<%@ page language="java" contentType="text/plain; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	// 0. 파라미터 체크
	String os = request.getParameter("os");
	if( os == null && os.trim().length()==0 ){
		// 에러 ResError :	{ "code":-1, "msg":"매개변수가 누락되었습니다." }
		ResError err = new ResError(-1, "매개변수가 누락되었습니다.");
		// 객채 -> json으로 
		Gson gson 	= new Gson();
		String json = gson.toJson(err);
		out.print(json);
		return;
	}
	try{
		// 1. 드라이버 로드
		Class.forName("com.mysql.jdbc.Driver");
		// 2. 디비 커넥션
		Connection conn 			= DriverManager.getConnection("jdbc:mysql://jsptest.cpkkponxntb7.ap-northeast-2.rds.amazonaws.com:3306/Tacademy?" +
                "user=test&password=test1234");
		// 3. 쿼리
		String sql 						= "select * from tbl_version where os=? limit 1;";
		PreparedStatement ps 	= conn.prepareStatement(sql);
		ps.setString(1, os);
		ResultSet rs 					= ps.executeQuery();
		VersionVO version 		= null;
		while( rs.next() ){
			System.out.print( rs.toString() );
			version = new VersionVO(	rs.getString("os"),
																rs.getString("version"),
																rs.getString("url")       );
		}
		// 4. 디비 클로즈
		if( rs != null )
			rs.close();
		if( conn != null )
			conn.close();
		// 5. 응답
		ResVersion res = new ResVersion();
		res.setCode(1);
		res.setMsg(version);
		Gson gson 	= new Gson();
		String json = gson.toJson(res);
		out.print(json);
		// { "code":1, "msg":{"os":"a", "version":"1.0.0", "url":"http://m.daum.net"} }
	}catch(Exception e){
		
	}
%>



