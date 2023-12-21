package jdbc_2.eeit179.garylee.lab.lab02statement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatement1 {
	
/*
 * 先示範這個類別
 */
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"));
			properties.load(fileInputStream);
			String url=properties.getProperty("url");
			String user=properties.getProperty("user");
			String password=properties.getProperty("password");
			conn = DriverManager.getConnection(url,user,password);
			boolean status = !conn.isClosed();
			if(status) {
				System.out.println("開啟連線");
			}
			String sql="INSERT INTO customers(name,email) VALUES('test','test@mail.com')";
			Statement statement = conn.createStatement();
			statement.execute(sql);
			System.out.println("執行excute");
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				System.out.println("關閉連線");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
	}


}
