package com.rahul.main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.bean.*;

public class JDBCProject1 
{

	public static void main(String[] args) throws SQLException, IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to JDBC Console based application");
		System.out.println("Please choose any one to perform the action");
		System.out.println("==============================================");
		System.out.println("1. Insert Operation");
		System.out.println("2. Select Operation");
		System.out.println("3. Update Operation");
		System.out.println("4. Delete Operation");
		System.out.println("5. Exit");
		System.out.println("==============================================");
		
		System.out.println();
		
		System.out.println("Enter you choice to perform the Operation :: ");
		int ch = sc.nextInt();
		
		switch (ch) 
		{
			case 1 -> InsertApp.Insert();
			case 2 -> SelectApp.Select();
			case 3 -> UpdateApp.Update();
			case 4 -> System.out.println("Welcome to Delete Operation Application");
			case 5 -> System.exit(0);
			default -> System.out.println("You have put wrong choice, please enter correct choice.");
		}

	}

}
