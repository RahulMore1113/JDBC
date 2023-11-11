package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class TransactionApp {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		ResultSet rs1 = null;
		Scanner sc = null;

		try {
			con = JdbcUtil.getJdbcConnection();

			System.out.println("Data before transaction...");

			if (con != null)
				stmt = con.createStatement();

			if (stmt != null)
				rs = stmt.executeQuery("select id,name,balance from accounts");

			if (rs != null) {
				System.out.println("ID\tNAME\tBALANCE");
				while (rs.next())
					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3));
			}

			System.out.println("Transaction begin...");
			con.setAutoCommit(false);

			stmt.executeUpdate("update accounts set balance = balance-2000 where name = 'Sachin'");
			stmt.executeUpdate("update accounts set balance = balance+2000 where name = 'Dhoni'");

			System.out.println("Can you please confirm transaction of 2000INR... (YES/NO)");
			sc = new Scanner(System.in);
			String option = sc.next();

			if (option.equalsIgnoreCase("yes"))
				con.commit();
			else
				con.rollback();

			System.out.println("Data after transaction...");

			rs1 = stmt.executeQuery("select id,name,balance from accounts");

			if (rs1 != null) {
				System.out.println("ID\tNAME\tBALANCE");
				while (rs1.next())
					System.out.println(rs1.getInt(1) + "\t" + rs1.getString(2) + "\t" + rs1.getInt(3));
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(con, stmt, rs1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
