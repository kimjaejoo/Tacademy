package com.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
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
    	try {
			Context initCtx = new InitialContext();
			Context envCtx  = (Context)initCtx.lookup("java:comp/env");
			DataSource ds 	= (DataSource)envCtx.lookup("jdbc/java");
			return ds.getConnection();
		} catch (Exception e) {
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

