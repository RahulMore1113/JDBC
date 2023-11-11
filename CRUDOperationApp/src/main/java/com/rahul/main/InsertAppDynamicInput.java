package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class InsertAppDynamicInput {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getJdbcConnection();
			System.out.println("Connection Established...");

			String sqlInsertQuery = "insert into student (sname,sage,saddress) values (?,?,?)";
			if (con != null)
				pstmt = con.prepareStatement(sqlInsertQuery);

			if (pstmt != null) {
				Scanner sc = new Scanner(System.in);

				System.out.println("Enter the name of the student :: ");
				String name = sc.next();

				System.out.println("Enter the age of the student :: ");
				int age = sc.nextInt();

				System.out.println("Enter the address of the student :: ");
				String address = sc.next();

				pstmt.setString(1, name);
				pstmt.setInt(2, age);
				pstmt.setString(3, address);

				System.out.println(sqlInsertQuery);

				int rowCount = pstmt.executeUpdate();
				System.out.println("No of rows updated is :: " + rowCount);
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(con, pstmt, null);
				System.out.println("Connection ended...");
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

	}

}
