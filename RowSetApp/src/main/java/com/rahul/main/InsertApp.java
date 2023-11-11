package com.rahul.main;

import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class InsertApp 
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
		
		Scanner sc = new Scanner(System.in);
		jrs.moveToInsertRow();
		while(true)
		{
			System.out.println("Enter the student name :: ");
			String name = sc.next();
			
			System.out.println("Enter the student age :: ");
			int age = sc.nextInt();
			
			System.out.println("Enter the student address :: ");
			String address = sc.next();
			
			jrs.updateString(2, name);
			jrs.updateInt(3, age);
			jrs.updateString(4, address);
			
			jrs.insertRow();
			
			System.out.println("Record inserted successfully");
			System.out.println("Do you want to insert one more record [Yes/No]");
			String option = sc.next();
			
			if (option.equalsIgnoreCase("no"))
				break;
		}
		
		sc.close();
		jrs.close();
	}
}
