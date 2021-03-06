package com.taca;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.model.CStore;
import com.util.U;


@WebServlet(name = "search", description = "검색", urlPatterns = { "/search" })
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Search() {
        super();
        
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//response.setContentType("text/html; charset=utf-8");
		
		// singleton으로 연결하기
		Connection conn = U.getInstance().getConnection();
		
		String key = request.getParameter("key");
		String keyword = request.getParameter("keyword");
		
		//response.getWriter().println(key + " / " + keyword);
		// 편의점의 이름을 넣어서 검색하는 방법
		String sql = "select idx, name, addr from tbl_cStore where name like '%" + keyword + "%'";
		
		ArrayList<CStore> cStores = new ArrayList<CStore>();
		// 커넥션객체 => statement 획득
		
		
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				response.getWriter().println(
						rs.getString("name") + "<br>");
				cStores.add(
						new CStore(
								rs.getInt("idx"), 
								rs.getString("name"), 
								rs.getString("addr")
									));
			}
			if (rs != null)
				rs.close();
			if (ps != null)
				ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		U.getInstance().returnConnection(conn);
		
		// 응답 
		request.setAttribute("result", cStores);
		request.getRequestDispatcher("result3.jsp").forward(request, response);;
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = U.getInstance().getConnection();
		
		U.getInstance().returnConnection(conn);
	}

}
