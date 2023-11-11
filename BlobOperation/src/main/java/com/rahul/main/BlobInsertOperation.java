package com.rahul.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class BlobInsertOperation {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;

		String name = null;
		String image = null;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("insert into persons(name, image) values (?,?)");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				if (sc != null) {
					System.out.println("Enter the name :: ");
					name = sc.next();

					System.out.println("Enter the location of the image to add :: ");
					image = sc.next();
				}

				pstmt.setString(1, name);
				pstmt.setBinaryStream(2, new FileInputStream(new File(image)));

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
