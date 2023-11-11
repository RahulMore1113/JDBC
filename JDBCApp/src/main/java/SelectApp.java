import java.sql.*;

public class SelectApp 
{

	public static void main(String[] args) throws ClassNotFoundException, SQLException, Exception
	{
//			//Step1. Load and register the Driver
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			System.out.println("Driver loaded successfully...");
//			
			//Step2. Established the connection
			String url = "jdbc:mysql://localhost:3306/ineuron";
			String userName = "root";
			String passWord = "Rahul@1113";
			Connection connection = DriverManager.getConnection(url,userName,passWord);
			
			System.out.println("Conection Object created...");
			
			//Step3. Create statement Object and send the query
			Statement statement = connection.createStatement();
			System.out.println("Statement Object created...");
			
			//Step4.Execute the statement and process the ResultSet
//			String sqlSelectQuery = "select sid,sname,sage,saddress from student";
//			ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
			ResultSet resultSet = statement.executeQuery("select sid,sname,sage,saddress from student");
			System.out.println("ResultSet Object created...");
			
			//Step5. Retrive details
			System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
			while(resultSet.next())
			{
				int sid = resultSet.getInt("sid");
				String sname= resultSet.getString("sname");
				int sage = resultSet.getInt("sage");
				String saddress = resultSet.getString("saddress");
				System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddress);
			}
			
			//Step6. Close the Resources
			connection.close();
			statement.close();
			resultSet.close();
			System.out.println("Closing the Resources...");
	}

}
