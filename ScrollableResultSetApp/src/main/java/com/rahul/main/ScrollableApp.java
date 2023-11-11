package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.rahul.util.JdbcUtil;

public class ScrollableApp {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

			if (stmt != null)
				rs = stmt.executeQuery("select id,name,age, address from employees");

			if (rs != null) {
				System.out.println("Moving in forward direction...");
				System.out.println("ID\tNAME\tAGE\tADDRESS");
				while (rs.next())
					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));
			}

			System.out.println();

			System.out.println("Moving in backward direction...");
			System.out.println("ID\tNAME\tAGE\tADDRESS");
			while (rs.previous())
				System.out
						.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.first();
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.last();
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.first();
			rs.absolute(3);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.relative(2);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.first();
			rs.absolute(2);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.relative(-1);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.absolute(5);
			rs.relative(-2);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

//			rs.relative(5);
//			System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getInt(3)+"\t"+rs.getString(4));
//			
			System.out.println();

			rs.absolute(-5);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.last();
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

			System.out.println();

			rs.relative(-1);
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(con, stmt, rs);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

}
