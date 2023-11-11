package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class SelectApp {

	public static void main(String[] args) {

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		Scanner sc = null;
		int sid = 0;

		try {
			connection = JdbcUtil.getJdbcConnection();
			System.out.println("Connection Established...");

			String sqlSelectQuery = "select sid,sname,sage,saddress from student where sid = ?";
			if (connection != null)
				pstmt = connection.prepareStatement(sqlSelectQuery);

			if (pstmt != null) {
				sc = new Scanner(System.in);

				System.out.println("Enter the ID of the student");
				sid = sc.nextInt();

				pstmt.setInt(1, sid);

				resultSet = pstmt.executeQuery();
			}

			if (resultSet != null) {
				if (resultSet.next()) {
					System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
					System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + resultSet.getInt(3)
							+ "\t" + resultSet.getString(4));
				} else {
					System.out.println("Record not avalable for the given id :: " + sid);
				}
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(connection, pstmt, resultSet);
				System.out.println("Connection ended...");
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

	}

}
