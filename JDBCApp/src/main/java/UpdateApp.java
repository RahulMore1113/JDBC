import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateApp {

	public static void main(String[] args) throws SQLException {
		
		String url = "jdbc:mysql:///ineuron";
		String userName = "root";
		String passWord = "Rahul@1113";
		Connection connection = DriverManager.getConnection(url,userName,passWord);			
		
		Statement statement = connection.createStatement();
		
		int rowAffected = statement.executeUpdate("update student set sname = 'Rahul' where sid = 2");
		System.out.println("No of rows updated is :: " + rowAffected);
		
		connection.close();
		statement.close();
		System.out.println("Closing the Resources...");

	}

}
