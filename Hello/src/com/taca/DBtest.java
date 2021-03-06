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

@WebServlet(description = "디비 연동 테스트", urlPatterns = { "/DBtest" })
public class DBtest extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn;

	public DBtest() {
		super();
		System.out.println("생성자");
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
			System.out.println("여기가 뜨면 안됨");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("뭔가 잘못됨");
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

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("service()");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet() -> 쿼리 수행");

		int memberCnt = 0;
		String sql = "select count(memIdx) as cnt from tbl_User where uid= ? and upw= ? ;";
		// 커넥션객체 => statement 획득
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, request.getParameter("uid"));
			ps.setString(2, request.getParameter("upw"));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				System.out.println("일치하는 회원수 : " + rs.getInt("cnt"));
				memberCnt = rs.getInt("cnt");
			}
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// 응답
		if (memberCnt > 0) {
			// 회원 -> main.jsp 이동
			RequestDispatcher dispatcher = request.getRequestDispatcher("main.jsp");
			dispatcher.forward(request, response);
		} else {
			// 비회원
			response.getWriter().append("<script>alert('login fail');history.back();</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost()");
		doGet(request, response);
	}
}
