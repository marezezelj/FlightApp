package com.dolphweb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl="jdbc:mysql://localhost:3306/hb_flight_tracker?useSSL=false";
		String username="hbstudent";
		String password="hbstudent";
		
		try {
			
			System.out.println("Connecting...");
			
			Connection conn=DriverManager.getConnection(jdbcUrl, username, password);
			
			System.out.println("Connected.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
