import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertApp {

	public static void main(String[] args) throws SQLException {

		String url = "jdbc:mysql:///ineuron";
		String userName = "root";
		String passWord = "Rahul@1113";
		Connection connection = DriverManager.getConnection(url, userName, passWord);

		Statement statement = connection.createStatement();

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the name of the student :: ");
		String name = sc.next();

		System.out.println("Enter the age of the student :: ");
		int age = sc.nextInt();

		System.out.println("Enter the address of the student :: ");
		String address = sc.next();

//		String sqlInsertQuery = "insert into student (sname, sage, saddress)values('"+name+"',"+age+",'"+address+"')";      //Old approach
		String sqlInsertQuery = String.format("insert into student (sname,sage,saddress)values('%s','%d','%s')", name,
				age, address);// New approach
		System.out.println(sqlInsertQuery);
		int rowAffected = statement.executeUpdate(sqlInsertQuery);
		System.out.println("No of rows inserted is :: " + rowAffected);

		ResultSet resultSet = statement.executeQuery("select sid,sname,sage,saddress from student");
		System.out.println("ResultSet Object created...");

		System.out.println("SID\tSNAME\tSAGE\tSADDRESS");
		while (resultSet.next()) {
			int sid = resultSet.getInt("sid");
			String sname = resultSet.getString("sname");
			int sage = resultSet.getInt("sage");
			String saddress = resultSet.getString("saddress");
			System.out.println(sid + "\t" + sname + "\t" + sage + "\t" + saddress);
		}

		connection.close();
		statement.close();
		resultSet.close();
		System.out.println("Closing the Resources...");

	}

}
