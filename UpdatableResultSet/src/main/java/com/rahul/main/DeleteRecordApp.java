package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rahul.util.JdbcUtil;

public class DeleteRecordApp 
{
	public static void main(String[] args) 
	{
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			con = JdbcUtil.getJdbcConnection();
			
			if(con != null)
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			
			if(stmt != null)
				rs = stmt.executeQuery("select id,name,age,address from employees");
			
			if(rs != null)
			{
				System.out.println("Record before deletion...");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while(rs.next())
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
			}
			
			System.out.println();
			
			rs.last();
			
			rs.deleteRow();
			
			rs.beforeFirst();
			System.out.println("Records after deletion...");
			System.out.println("ID\tNAME\tAGE\tADDRESS");
			while(rs.next())
				System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
			
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(con, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
