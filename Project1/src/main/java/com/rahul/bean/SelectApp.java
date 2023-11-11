package com.rahul.bean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class SelectApp 
{

	public static void Select() throws SQLException, IOException
	{
		System.out.println("Welcome to Select Operation Application");
		
		Connection con = JdbcUtil.getJdbcConnection();
		
		PreparedStatement pstmt = con.prepareStatement("select sid, sname, sage, saddress from student where sid = ?");
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter the id of the student :: ");
		int sid = sc.nextInt();
		
		pstmt.setInt(1, sid);
		
		ResultSet resultSet = pstmt.executeQuery();
		
		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
		while(resultSet.next())
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3)+"\t"+resultSet.getString(4));
		
		JdbcUtil.cleanUp(con, pstmt, resultSet);
	}
	
}
