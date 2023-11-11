import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectApp2 
{

	public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception
	{
			
			String url = "jdbc:mysql:///ineuron";
			String userName = "root";
			String passWord = "Rahul@1113";
			Connection connection = DriverManager.getConnection(url,userName,passWord);			
			
			Statement statement = connection.createStatement();
			
			ResultSet resultSet = statement.executeQuery("select sid,sname,sage,saddress from student");
			
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			while(resultSet.next())
			{
				int sid = resultSet.getInt("sid");
				String sname= resultSet.getString("sname");
				int sage = resultSet.getInt("sage");
				String saddress = resultSet.getString("saddress");
				System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddress);
			}
			
			connection.close();
			statement.close();
			resultSet.close();
			System.out.println("Closing the Resources...");
	}

}
