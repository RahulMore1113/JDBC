package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.rahul.util.JdbcUtil;

public class UpdateApp2 {

	public static void main(String[] args) throws SQLException, IOException {

		Connection con = JdbcUtil.getJdbcConnection();

		PreparedStatement pstmt = con.prepareStatement("update student set sname = ? where sid = ?");

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the ID of student :: ");
		int sid = sc.nextInt();

		System.out.println("Enter the name of the student :: ");
		String sname = sc.next();

		pstmt.setString(1, sname);
		pstmt.setInt(2, sid);

		int rowsAffected = pstmt.executeUpdate();
		System.out.println("No of rows updated are :: " + rowsAffected);

		JdbcUtil.cleanUp(con, pstmt, null);

	}

}
