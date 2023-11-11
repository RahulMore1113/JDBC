package com.rahul.main;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

public class TestApp {

	public static void main(String[] args) throws SQLException {

		MysqlConnectionPoolDataSource dataSource = new MysqlConnectionPoolDataSource();
		dataSource.setURL("jdbc:mysql://localhost:3306/jdbc");
		dataSource.setUser("root");
		dataSource.setPassword("Rahul@1113");

		Connection con = dataSource.getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select id, name, age, address from employees");
		System.out.println("ID\tNAME\tAGE\tADDRESS");
		while (rs.next())
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getInt(3) + "\t" + rs.getString(4));

		rs.close();
		stmt.close();
		con.close();

	}

}
