package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class DeleteApp {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		Scanner sc = null;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("delete from student where sid = ?");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				System.out.println("Enter the id of the student :: ");
				int id = sc.nextInt();

				pstmt.setInt(1, id);

				int affectedRow = pstmt.executeUpdate();
				System.out.println("No of rows deleted are  :: " + affectedRow);
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
