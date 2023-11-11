package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class DateRetreivalApp {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;
		int id = 0;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("select id, name, dob, dom from users where id = ?");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				if (sc != null) {
					System.out.println("Enter the if od the user to see the record :: ");
					id = sc.nextInt();

					pstmt.setInt(1, id);
				}

				rs = pstmt.executeQuery();
			}

			if (rs != null) {
				if (rs.next()) {
					System.out.println("ID\tNAME\tDOB\t\tDOM");

					Date sqlDob = rs.getDate(3);
					Date sqlDom = rs.getDate(4);

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + sdf.format(sqlDob) + "\t"
							+ sdf.format(sqlDom));

					System.out.println();

					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getDate(3) + "\t" + rs.getDate(4));
				} else
					System.out.println("Record is not available for id :: " + id);
			}

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(con, pstmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
