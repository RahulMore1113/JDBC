package com.rahul.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SQLInjectionApp 
{

	public static void main(String[] args) 
	{
		
		String url = "jdbc:mysql://localhost:3306/ineuron";
		String username = "root";
		String password = "Rahul@1113";
		
		try {
			Connection con = DriverManager.getConnection(url,username,password);
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Enter the username");
			String name = sc.next();
			
			System.out.println("Enter the passoword");
			String pwd = sc.next();
			
			PreparedStatement pstmt = con.prepareStatement("select count(*) from info where username = ? and password = ?");
			
			pstmt.setString(1, name);
			pstmt.setString(2, pwd);
			
			ResultSet rs = pstmt.executeQuery();
			int row =0;
			if(rs.next())
			{
				row = rs.getInt(1);
			}
			if(row == 1)
			{
				System.out.println("Valid Credential");
			}
			else
			{
				System.out.println("Invalid Credentials");
			}
			sc.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
