package com.taca;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.model.ReqRegistDto;
import com.model.ResRegistDto;
import com.model.UserDao;

@WebServlet(name = "login", description = "로그인처리", urlPatterns = { "/login" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection conn;
   
    public Login() {
        super();
        
    }

	
    public void init(ServletConfig config) throws ServletException {
		System.out.println("init() -> DB 연결");
		
		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 커넥션
			conn = DriverManager.getConnection("jdbc:mysql://jsptest.cpkkponxntb7.ap-northeast-2.rds.amazonaws.com:3306/Tacademy?" +
				                               "user=test&password=test1234");
			System.out.println("DB 연결 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
	public void destroy() {
		System.out.println("destroy() -> DB 해제");
		try {
			conn.close();
			System.out.println("DB 연결 종료");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("====== 로그인 처리 ======");
		// JSON 객체로 데이터가 넘어올 경우 처리
		StringBuffer sb 	= new StringBuffer();
		BufferedReader br 	= request.getReader();
		String tmp 			= null;
		while( (tmp = br.readLine()) != null)
		{
			sb.append(tmp);
		}
		// 요청 -------------------------------------------------------------
		String json 		= sb.toString();
		Gson gson 			= new Gson();
		ReqRegistDto reqD 	= gson.fromJson(json, ReqRegistDto.class);
		// 비즈니스 로직 -----------------------------------------------------
		String sql 			= "select * from tbl_User where uid=? and upw=? limit 1;";
		ResultSet rs		= null;
		UserDao user 		= new UserDao();
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			// 파라미터 세팅
			ps.setString(1, reqD.getBody().getUid());		
			ps.setString(2, reqD.getBody().getUpw());
			// 쿼리수행
			rs 				= ps.executeQuery();
			if( rs.next() ){
				// 데이터가 존재한다.
				user.setUid( 	rs.getString("uid") );				
				user.setPoint(  rs.getInt("point"));
				user.setLevel(  rs.getInt("level"));
			}
			if( rs != null )
				rs.close();
		}catch(Exception e){
			System.out.println("SQL오류 : "+e.getMessage());
		}
		// 응답 -------------------------------------------------------------
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		ResRegistDto rrd = new ResRegistDto();
		if( user.getUid() != null && user.getUid().length()>0 ){
			rrd.setCode(1);
			rrd.setMsg("로그인 성공");
		}else{
			rrd.setCode(-1);
			rrd.setMsg("로그인 실패");
		}		
		rrd.setData(user);
		response.getWriter().println(gson.toJson(rrd));		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}