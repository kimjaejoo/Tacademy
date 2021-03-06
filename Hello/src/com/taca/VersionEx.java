package com.taca;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
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

import com.google.gson.Gson;
import com.model.ResError;
import com.model.ResVersion;
import com.model.VersionVO;

@WebServlet(name = "version", description = "버전체크 모델2 방식", urlPatterns = { "/versionEx" })
public class VersionEx extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;

	public VersionEx() {
		super();
	}

	// 드라이버에 연결한다.
	public void init(ServletConfig config) throws ServletException {
		System.out.println("jspInit() call ");
		try {
			// 1. 드라이버 로드
			Class.forName("com.mysql.jdbc.Driver");
			// 2. 디비 커넥션
			conn = DriverManager
					.getConnection("jdbc:mysql://jsptest.cpkkponxntb7.ap-northeast-2.rds.amazonaws.com:3306/Tacademy?"
							+ "user=test&password=test1234");
		} catch (Exception e) {

		}
	}

	// 연결된 드라이버에서 자원을 모두 사용하고 연결을 종료한다. Connection은 전역변수로 사용하여 모든 method가 공유하게 한다.
	public void destroy() {
		System.out.println("jspDestory() call ");
		// 4. 디비 클로즈
		try {
			if (conn != null)
				conn.close();
		} catch (Exception e) {

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// req, resp의 한글설정
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		
		
		// 0. 파라미터 체크
		String os = request.getParameter("os");
		if (os == null || os.trim().length() == 0) {
			// 에러 ResError : { "code":-1, "msg":"매개변수가 누락되었습니다." }
			ResError err = new ResError(-1, "매개변수가 누락되었습니다.");
			// 객채 -> json으로
			Gson gson = new Gson();
			String json = gson.toJson(err);
			out.println(json);
			return;
		}
		try {
			// 3. 쿼리
			String sql = "SELECT * FROM tbl_version WHERE os=? LIMIT 1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, os);
			ResultSet rs = ps.executeQuery();
			VersionVO version = null;
			while (rs.next()) {
				System.out.println(rs.toString());
				//String os = rs.getString("os"); 지금까지 이렇게 해서 넣었지만 쓸데없는 변수를 생성하는 것보다 속도가 빠를 수있다.
				version = new VersionVO(rs.getString("os"), rs.getString("version"), rs.getString("url"));
			}
			if (rs != null)
				rs.close();
			// 5. 응답
			ResVersion res = new ResVersion();
			res.setCode(1);
			res.setMsg(version);
			Gson gson = new Gson();
			String json = gson.toJson(res);
			response.getWriter().append(json);
			// { "code":1, "msg":{"os":"a", "version":"1.0.0",
			// "url":"http://m.daum.net"} }
		} catch (Exception e) {

		}

		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 0. 파라미터 체크
		String os = request.getParameter("os");
		if (os == null || os.trim().length() == 0) {
			// 에러 ResError : { "code":-1, "msg":"매개변수가 누락되었습니다." }
			ResError err = new ResError(-1, "매개변수가 누락되었습니다.");
			// 객채 -> json으로
			Gson gson = new Gson();
			String json = gson.toJson(err);
			out.println(json);
			return;
		}
		try {
			// 3. 쿼리
			String sql = "select * from tbl_version where os=? limit 1;";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, os);
			ResultSet rs = ps.executeQuery();
			VersionVO version = null;
			while (rs.next()) {
				System.out.println(rs.toString());
				version = new VersionVO(rs.getString("os"), rs.getString("version"), rs.getString("url"));
			}
			if (rs != null)
				rs.close();
			// 5. 응답
			ResVersion res = new ResVersion();
			res.setCode(1);
			res.setMsg(version);

			Gson gson = new Gson();
			String json = gson.toJson(res);
			// response.getWriter().append(json);

			// JSP 포워딩
			request.setAttribute("jsonStr", json);
			RequestDispatcher dispatcher = request.getRequestDispatcher("version3.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {

		}
	}

}
