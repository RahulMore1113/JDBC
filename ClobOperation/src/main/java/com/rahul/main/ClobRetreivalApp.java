package com.rahul.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.rahul.util.JdbcUtil;

public class ClobRetreivalApp {

	public static void main(String[] args) {

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = null;
		int id = 0;

		try {

			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				pstmt = con.prepareStatement("select id,name,file from cities where id = ?");

			if (pstmt != null) {
				sc = new Scanner(System.in);

				if (sc != null) {
					System.out.println("Enter the id of the person :: ");
					id = sc.nextInt();
				}

				pstmt.setInt(1, id);

				rs = pstmt.executeQuery();
			}
			if (rs != null) {
				if (rs.next()) {
					System.out.println("ID\tNAME\tFILE");
					Reader reader = rs.getCharacterStream("file");

					File file = new File("file.txt");
					FileWriter writer = new FileWriter(file);

					IOUtils.copy(reader, writer);

					reader.close();
					writer.close();

					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + file.getAbsolutePath());
				} else
					System.out.println("Record not available for id :: " + id);
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
