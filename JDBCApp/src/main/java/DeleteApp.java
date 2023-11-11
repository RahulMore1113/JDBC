import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteApp 
{

	public static void main(String[] args) throws SQLException 
	{
		
		String url = "jdbc:mysql:///ineuron";
		String userName = "root";
		String passWord = "Rahul@1113";
		Connection connection = DriverManager.getConnection(url,userName,passWord);			
		
		Statement statement = connection.createStatement();
		
		int rowAffected = statement.executeUpdate("delete from student where sid = 3");
		System.out.println("No of rows deleted is :: " + rowAffected);
		
		connection.close();
		statement.close();
		System.out.println("Closing the Resources...");

	}

}
