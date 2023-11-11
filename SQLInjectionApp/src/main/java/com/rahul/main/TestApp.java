package com.rahul.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestApp 
{

	public static void main(String[] args) throws SQLException 
	{
		
		String url = "jdbc:mysql:///ineuron";
		String username = "root";
		String password = "Rahul@1113";
		
		Connection con = DriverManager.getConnection(url,username,password);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the username :: ");
		String uname = sc.next();
		
		System.out.println("Enter the password ::");
		String pwd = sc.next();
		
		PreparedStatement pstmt = con.prepareStatement("select count(*) from info where username ='"+uname+"' and password = '"+pwd+"'");
		
		ResultSet rs = pstmt.executeQuery();
		
		int row = 0;
		if(rs.next())
		{
			row = rs.getInt(1);
		}
		
		if(row == 1)
			System.out.println("Valid credentials");
		else
			System.out.println("Invalid credentials");
		
		sc.close();
	}

}
