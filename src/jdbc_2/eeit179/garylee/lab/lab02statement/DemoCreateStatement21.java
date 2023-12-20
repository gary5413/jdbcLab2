package jdbc_2.eeit179.garylee.lab.lab02statement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatement21 {
	
	private Connection conn;

	public void CreateConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"));
		properties.load(fileInputStream);
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
//		this關鍵字
		/*
		 * 區分成員變數和區域變數： 如果方法的參數名稱或方法內部的區域變數與類的成員變數同名，
		 * 使用 this 關鍵字可以將引用限定為類的成員變數。這樣可以區分出哪個是成員變數，哪個是區域變數。
		 * 在你的程式碼中，this.conn 用來指向類的成員變數 conn，而不是方法內部的 conn。
		 */
		this.conn = DriverManager.getConnection(url,user,password);
		boolean status = !conn.isClosed();
		if(status) {
			System.out.println("開啟連線");
		}
	}
	
	public void closeConnection() throws SQLException {
		if(conn !=null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}
	
	public void insert() throws SQLException {
		String sql="INSERT INTO customers(name,email,birth) VALUES('insert22','test@mail.com','2023-12-20')";
		Statement statement = conn.createStatement();
		statement.execute(sql);	
		System.out.println("執行excute");
		statement.close();
	}
	
	
	public static void main(String[] args) {
//		介紹實體化 什麼是new
		DemoCreateStatement21 demo02 = new DemoCreateStatement21();
			try {
				demo02.CreateConnection();
				demo02.insert();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					demo02.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}				
			}
		
	}


}
