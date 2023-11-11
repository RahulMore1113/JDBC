package com.rahul.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {

	private JdbcUtil() {

	}

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getJdbcConnection() throws IOException, SQLException {
		
		FileInputStream fis = new FileInputStream("src/main/java/com/rahul/properties/application.properties");
		Properties properties = new Properties();
		properties.load(fis);

		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("user"), properties.getProperty("password"));

		return connection;
		
	}

	public static void cleanUp(Connection con, Statement stmt, ResultSet rs) throws SQLException {
		if (con != null)
			con.close();

		if (stmt != null)
			stmt.close();

		if (rs != null)
			rs.close();
	}

}
