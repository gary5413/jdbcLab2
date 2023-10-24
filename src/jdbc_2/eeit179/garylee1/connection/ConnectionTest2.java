package jdbc_2.eeit179.garylee1.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class ConnectionTest2 {
	public static void main(String[] args) {
		try {
			String url = "jdbc:mysql://localhost:3306/mytestdb";
			String user = "root";
			String password = "5413gary";
			Driver driver = new Driver();
			DriverManager.registerDriver(driver);

			Connection conn = DriverManager.getConnection(url, user, password);

			System.out.println(conn);
			boolean status = !conn.isClosed();
			System.out.println("status:" + status);
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
