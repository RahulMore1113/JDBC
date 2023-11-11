package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.rahul.util.JdbcUtil;

public class InsertApp {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("insert into student (sname,sage,saddress) values (?,?,?)");

			if (pstmt != null) {
				pstmt.setString(1, "Rahul");
				pstmt.setInt(2, 38);
				pstmt.setString(3, "Mumbai");

				long affectedRow = pstmt.executeLargeUpdate();
				System.out.println("No of rows inserted id :: " + affectedRow);
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
