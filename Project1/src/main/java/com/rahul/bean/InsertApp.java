package com.rahul.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class InsertApp
{
	
	public static void Insert() throws SQLException, IOException 
	{
		System.out.println("Welcome to Insert Operation Application");
		
		Connection con = JdbcUtil.getJdbcConnection();
		
		PreparedStatement pstmt = con.prepareStatement("insert into student (sname,sage,saddress) values (?,?,?)");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the name of the student :: ");
		String sname = sc.next();
		
		System.out.println("Enter the age of the student :: ");
		int sage = sc.nextInt();
		
		System.out.println("Enter the address of the student :: ");
		String saddress = sc.next();
		
		pstmt.setString(1, sname);
		pstmt.setInt(2, sage);
		pstmt.setString(3, saddress);
		
		int rowCount = pstmt.executeUpdate();
		
		System.out.println("No of rows inserted are :: "+rowCount);
		
		System.out.println(pstmt);
		
		JdbcUtil.cleanUp(con, pstmt, null);
	}
	
}
