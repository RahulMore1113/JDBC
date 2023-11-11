package com.rahul.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestApp {

	public static void main(String[] args) throws SQLException {

		String data = "src/main/java/com/rahul/properties/application.properties";
		HikariConfig config = new HikariConfig(data);

		try (HikariDataSource dataSource = new HikariDataSource(config)) {
			Connection con = dataSource.getConnection();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select sid, sname, sage, saddress from student");
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			while (rs.next())
				System.out
						.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));
		}

	}

}
