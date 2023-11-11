package com.rahul.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertApp 
{

	public static void main(String[] args) throws SQLException 
	{
		
		String url = "jdbc:mysql:///ineuron";
		String username = "root";
		String password = "Rahul@1113";
		
		Connection con = DriverManager.getConnection(url,username,password);
		
		Scanner sc = new Scanner(System.in);
		
		PreparedStatement pstmt = con.prepareStatement("insert into info(username, password) values (?,?)");
		
		System.out.println("Enter the username :: ");
		String name = sc.next();
		
		System.out.println("Enter the password :: ");
		String pwd = sc.next();
		
		pstmt.setString(1, name);
		pstmt.setString(2, pwd);
		
		int affectedRow = pstmt.executeUpdate();
		System.out.println("No of rows inserted are :: "+affectedRow);
//		
		PreparedStatement pstmt1 = con.prepareStatement("select id,username,password from info");
		
		ResultSet rs = pstmt1.executeQuery();
		
		System.out.println("ID\tUSERNAME\tPASSWORD");
		if(rs.next())
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		
		while(rs.next())
			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3));
		
		sc.close();
	}

}
