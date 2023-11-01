package jdbc_2.eeit179.garylee.garylee1.util;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class JDBCutils {
	/**
	 * 獲取連線物件
	 * 
	 * @author garylee
	 * @return Connection
	 * @throws Exception
	 * 
	 */
	public static Connection getConnection() throws Exception {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(new File("src/jdbc.properties"));
		properties.load(fis);
		String user =properties.getProperty("user");
		String password =properties.getProperty("password");
		String url =properties.getProperty("url");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println("開啟連線");
		return conn;
	}
//	
	public static void close(Connection conn) {
		if(conn !=null) {
			try {
				conn.close();
				System.out.println("關閉連線");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
//	
	public static void close(Connection conn,Statement st) {
		try {
			if(st !=null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn !=null)
				conn.close();
				System.out.println("關閉連線");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
