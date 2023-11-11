package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class UpdateApp {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Scanner sc = null;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("update student set sname = ? , sage = ? where sid = ?");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				if (sc != null) {

					System.out.println("Enter the id of the student :: ");
					int id = sc.nextInt();

					System.out.println("Enter the name of the student :: ");
					String name = sc.next();

					System.out.println("Enter the age of the Student :: ");
					int age = sc.nextInt();

					pstmt.setString(1, name);
					pstmt.setInt(2, age);
					pstmt.setInt(3, id);
				}

				int affectedRow = pstmt.executeUpdate();
				System.out.println("No of rows updated are :: " + affectedRow);
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(con, pstmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
