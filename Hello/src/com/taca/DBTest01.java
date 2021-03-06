package com.taca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet(urlPatterns = { "/DBTest01" })
public class DBTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection conn;
   
    public DBTest01() {
        super();
      
    }

	
	public void init(ServletConfig config) throws ServletException {
		String url = "jdbc:mysql://jsptest.cpkkponxntb7.ap-northeast-2.rds.amazonaws.com:3306/Tacademy?user=test&password=test1234";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url);
			System.out.println("db연결 성공");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("문제있음01");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("문제있음02");
		}
	}

	
	public void destroy() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String sql = "select count(memIdx) as cnt from tbl_User where uid= ? and upw= ? ;";
		int count = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("uid"));
			pstmt.setString(2, request.getParameter("upw"));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				System.out.println("일치하는 회원수 : " + rs.getInt("cnt"));
				count = rs.getInt("cnt");
			}
			if(rs != null){
				rs.close();
			}
			if(pstmt != null){
				pstmt.close();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 응답
		if(count > 0){
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		}else{
			response.getWriter().append("<script>alert('login fail');history.back();</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
