package jdbc_2.eeit179.garylee.lab.lab01connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DemoConnection3 {
	public static void main(String[] args) {
		/*
		 * 將資料庫連線資料 放在文件中 通過讀取文件來獲取
		 * 耦合度降低 此方式更改資料庫資訊較為容易維護
		 */
		try {
//			加載Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
//			讀取jdbc.properties
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"));
			properties.load(fileInputStream);
			String url=properties.getProperty("url");
			String user=properties.getProperty("user");
			String password=properties.getProperty("password");
//			取得連線Connection
			Connection conn = DriverManager.getConnection(url,user,password);
			boolean status=!conn.isClosed();
			System.out.println("連線狀態："+status);
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
