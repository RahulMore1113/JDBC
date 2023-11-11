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

public class DateInsertionApp {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;

		java.sql.Date sqlDob = null;
		java.sql.Date sqlDom = null;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("insert into users (name, dob, dom) values (?,?,?)");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				if (sc != null) {
					System.out.println("Enter the name :: ");
					String name = sc.next();

					System.out.println("Enter the DOB (dd-MM-yyyy) :: ");
					String dob = sc.next();

					System.out.println("Enter the DOM (yyyy-MM-dd) :: ");
					String dom = sc.next();

					if (dob != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						java.util.Date uDate = sdf.parse(dob);

						long value = uDate.getTime();
						sqlDob = new Date(value);
					}

					if (dom != null)
						sqlDom = Date.valueOf(dom);

					pstmt.setString(1, name);
					pstmt.setDate(2, sqlDob);
					pstmt.setDate(3, sqlDom);

					int affectedRow = pstmt.executeUpdate();
					System.out.println("No of rows inserted are :: " + affectedRow);
				}
			}

			pstmt.close();
			System.out.println();

			System.out.println("After inserted new Record");

			if (con != null)
				pstmt = con.prepareStatement("select id, name, dob, dom from users");

			if (pstmt != null)
				rs = pstmt.executeQuery();

			if (rs != null)
				while (rs.next())
					System.out.println("ID\tNAME\tDOB\t\tDOM\n" + rs.getInt(1) + "\t" + rs.getString(2) + "\t"
							+ rs.getDate(3) + "\t" + rs.getDate(4));

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
