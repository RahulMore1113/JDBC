package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class BatchUpdateUsingPreparedStatement {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("insert into employees (name, age, address) values (?,?,?)");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				while (true) {
					System.out.println("Enter name of the employee :: ");
					String name = sc.next();

					System.out.println("Enter age of the employee :: ");
					int age = sc.nextInt();

					System.out.println("Enter address of the employee :: ");
					String address = sc.next();

					pstmt.setString(1, name);
					pstmt.setInt(2, age);
					pstmt.setString(3, address);

					pstmt.addBatch();

					System.out.println("Do you want to insert one or more records [YES/NO] :: ");
					String option = sc.next();

					if (option.equalsIgnoreCase("no"))
						break;
				}

				int[] affectedRow = pstmt.executeBatch();

				System.out.println("Records inserted successfully.... " + affectedRow);
			}

			pstmt = con.prepareStatement("select id, name, age, address from employees");

			if (pstmt != null)
				rs = pstmt.executeQuery();

			if (rs != null) {
				System.out.println("ID\tNAME\tAGE\tADDRESS");

				while (rs.next())
					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

}
