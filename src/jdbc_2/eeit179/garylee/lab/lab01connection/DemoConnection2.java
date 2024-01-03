package jdbc_2.eeit179.garylee.lab.lab01connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DemoConnection2 {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/mytestdb";
		String user = "root";
		String password = "5413gary";
		try {
			/*
			 * 此方法不推薦 
			 */
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);
			Connection conn = DriverManager.getConnection(url,user,password);
			boolean status =!conn.isClosed();
			System.out.println("status:"+status);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
