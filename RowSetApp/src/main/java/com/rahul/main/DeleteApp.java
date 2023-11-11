package com.rahul.main;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class DeleteApp 
{
	public static void main(String[] args) throws SQLException 
	{
		RowSetFactory rsf = RowSetProvider.newFactory();
		JdbcRowSet jrs = rsf.createJdbcRowSet();
		
		jrs.setUrl("jdbc:mysql://localhost:3306/ineuron");
		jrs.setUsername("root");
		jrs.setPassword("Rahul@1113");
		
		jrs.setCommand("select sid,sname,sage,saddress from student");
		jrs.execute();
		
		while(jrs.next())
		{
			int age = jrs.getInt(3);
			if(age < 25)
			{
				jrs.deleteRow();
			}
		}
		System.out.println("Records deleted successfully");	
		jrs.close();
	}
}
