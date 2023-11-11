package com.rahul.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class UpdateApp 
{
	
	public static void Update() throws SQLException, IOException
	{
		System.out.println("Welcome to Update Operation Application");
		
		Connection con = JdbcUtil.getJdbcConnection();
		
		PreparedStatement pstmt = con.prepareStatement("update student set sname = ? , sage = ? , saddress = ? where sid = ?");
		
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the id of the student :: ");
		int sid = sc.nextInt();
		
		System.out.println("Enter the name of the student :: ");
		String sname = sc.next();
		
		System.out.println("Enter the age of the student :: ");
		int sage = sc.nextInt();
		
		System.out.println("Enter the address of the student :: ");
		String saddress = sc.next();
		
		pstmt.setString(1, sname);
		pstmt.setInt(2, sage);
		pstmt.setString(3, saddress);
		pstmt.setInt(4, sid);
		
		int rowAffected = pstmt.executeUpdate();
		System.out.println("No of rows updated are :: "+rowAffected);
	}
	
}
