package com.rahul.main;

import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class SelectApp 
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
		
		System.out.println("Retreiving he records in the forward direction");
		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
		while(jrs.next())
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
		
		System.out.println();
		
		System.out.println("Retreiving he records in the backward direction");
		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
		while(jrs.previous())
			System.out.println(jrs.getInt(1)+"\t"+jrs.getString(2)+"\t"+jrs.getInt(3)+"\t"+jrs.getString(4));
	}
}
