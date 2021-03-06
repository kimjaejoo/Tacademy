package com.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

// singleton
public class U 
{
	private static U self;
	private U(){}
	public static U getInstance()
	{
		if( self == null ){
			self = new U();
		}
		return self;
	}
	// 커넥션 객체 획득
	public Connection getConnection()
	{
			Context initCtx;
			try {
				initCtx = new InitialContext();
				Context envCtx  = (Context)initCtx.lookup("java:comp/env");
				DataSource ds 	= (DataSource)envCtx.lookup("jdbc/java");
				System.out.println("연결성공");
				return ds.getConnection();
			} catch (NamingException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
				System.out.println("문제있음 01");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("문제있음 02");
				System.out.println(e.getMessage());
			}
		
    	return null;
	}
	// 커넥션 객체 반납
	public void returnConnection(Connection conn)
	{
		if( conn==null ) return;
		try {
			conn.close();	// 반납
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

