package com.taca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// servlet을 사용하기위함
// urlPatterns을 주소창에 입력하면 이동한다.
@WebServlet(name = "Version", description = "버전 정보 획득", urlPatterns = { "/Version" })
public class Version extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Version() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 사용자가 post방식으로 값을 넘기면 get방식으로 받아서 post방식으로 포장한 후에 data를 전송한다.
		System.out.println("UID : " + request.getParameter("uid"));
		System.out.println("UPW : " + request.getParameter("upw"));
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}

}
