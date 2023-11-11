import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertApp {

	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:mysql:///ineuron";
		String userName = "root";
		String passWord = "Rahul@1113";
		Connection connection = DriverManager.getConnection(url,userName,passWord);			
		
		Statement statement = connection.createStatement();
		
		int rowAffected = statement.executeUpdate("insert into student (sname, sage,saddress)values ('More', 24, 'Mumbai')");
		System.out.println("No of rows inserted is :: " + rowAffected);
		
		connection.close();
		statement.close();
		System.out.println("Closing the Resources...");

	}

}
