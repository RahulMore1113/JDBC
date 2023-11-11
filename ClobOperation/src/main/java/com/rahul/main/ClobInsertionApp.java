package com.rahul.main;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class ClobInsertionApp {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;

		String name = null;
		String file = null;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("insert into cities (name, file) values (?,?)");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				if (sc != null) {
					System.out.println("Enter the name :: ");
					name = sc.next();

					System.out.println("Enter the location of the file to add :: ");
					file = sc.next();
				}

				pstmt.setString(1, name);
				pstmt.setCharacterStream(2, new FileReader(new File(file)));

				int affectedRow = pstmt.executeUpdate();
				System.out.println("No of rows inserted are :: " + affectedRow);
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
