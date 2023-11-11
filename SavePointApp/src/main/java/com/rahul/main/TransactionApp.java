package com.rahul.main;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import com.rahul.util.JdbcUtil;

public class TransactionApp {

	public static void main(String[] args) {

		Connection con = null;
		Statement stmt = null;

		try {
			con = JdbcUtil.getJdbcConnection();

			if (con != null)
				stmt = con.createStatement();

			System.out.println("Transaction started...");

			con.setAutoCommit(false);

			stmt.executeUpdate("insert into politicians (name,party) values ('MODI','BJP')");
			stmt.executeUpdate("insert into politicians (name,party) values ('KCR','TRS')");

			Savepoint sp = con.setSavepoint();

			stmt.executeUpdate("insert into politicians (name,party) values ('SIDDU','BJP')");

			System.out.println("Oop's something went wrong during insertion...");

			con.rollback(sp);

			System.out.println("Operations are back to the savepoint...");

			con.commit();

			System.out.println("Transaction complete...");

		} catch (SQLException | IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				JdbcUtil.cleanUp(con, stmt, null);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
