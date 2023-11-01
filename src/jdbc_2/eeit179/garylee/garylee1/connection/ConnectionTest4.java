package jdbc_2.eeit179.garylee.garylee1.connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionTest4 {

	public static void main(String[] args)  {
		Connection conn=null;
		try{
			Properties properties = new Properties();
			FileInputStream fis = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fis);
			String user =properties.getProperty("user");
			String password =properties.getProperty("password");
			String url =properties.getProperty("url");
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
