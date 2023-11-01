package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo2TryWithResource {

	
	public static void main(String[] args) {
		String url="jdbc:mysql://localhost:3306/mytestdb";
		String user="root";
		String password="5413gary";
		try (Connection conn = DriverManager.getConnection(url,user,password);) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			boolean status = !conn.isClosed();
			System.out.println("status:"+status);
//			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
