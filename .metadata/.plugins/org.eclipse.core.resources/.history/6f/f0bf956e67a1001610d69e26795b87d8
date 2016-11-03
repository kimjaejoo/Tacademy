package com.taca;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

@WebServlet(name = "/regist", description = "회원가입", urlPatterns = { "/regist" })
public class Regist extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
    public Regist() {
        super();
    }
    public void init(ServletConfig config) throws ServletException {
		System.out.println("init() -> DB 연결");
		
		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 커넥션
			conn = DriverManager.getConnection("jdbc:mysql://jsptest.cnsk7fdb6ed8.ap-northeast-2.rds.amazonaws.com:3306/taca?" +
				                               "user=test&password=12341234");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	doPost(request, response);
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		System.out.println("====== 회원가입 처리 ======");
		// JSON 객체로 데이터가 넘어올 경우 처리
		StringBuffer sb 	= new StringBuffer();
		BufferedReader br 	= request.getReader();
		String tmp = null;
		while( (tmp = br.readLine()) != null)
		{
			sb.append(tmp);
		}
		// 요청 -------------------------------------------------------------
		String json 		= sb.toString();
		Gson gson 			= new Gson();
		ReqRegistDto reqD 	= gson.fromJson(json, ReqRegistDto.class);
		// 비즈니스 로직 -----------------------------------------------------
		String sql = "INSERT "+
					 "INTO "+ 
					 "tbl_User "+
					 "(`uid`,`name`,`upw`,`regDate`,`uuid`,`token`,`phone`,`model`,`os`) "+ 
					 "VALUES  "+
					 "(?,?,?,now(),?,?,?,?,?);";
		int nRtn = 0;
		try{
			PreparedStatement ps = conn.prepareStatement(sql);
			// 파라미터 세팅
			ps.setString(1, reqD.getBody().getUid());
			ps.setString(2, reqD.getBody().getName());
			ps.setString(3, reqD.getBody().getUpw());
			ps.setString(4, reqD.getBody().getUuid());
			ps.setString(5, reqD.getBody().getToken());
			ps.setString(6, reqD.getBody().getPhone());
			ps.setString(7, reqD.getBody().getModel());
			ps.setString(8, reqD.getBody().getOs());
			// 쿼리수행
			nRtn = ps.executeUpdate();
			System.out.println(nRtn);
		}catch(Exception e){
			System.out.println("SQL오류 : "+e.getMessage());
		}
		// 응답 -------------------------------------------------------------
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		
		ResRegistDto rrd = new ResRegistDto();
		if( nRtn > 0){
			rrd.setCode(1);
			rrd.setMsg("회원가입이 되었습니다.");
		}else{
			rrd.setCode(-1);
			rrd.setMsg("회원가입 실패");
		}
		UserDao user = new UserDao();
		user.setUid(	reqD.getBody().getUid());
		user.setName(	reqD.getBody().getName());
		user.setUpw(	reqD.getBody().getUpw());
		rrd.setData(user);
		response.getWriter().println(gson.toJson(rrd));
	}
}
