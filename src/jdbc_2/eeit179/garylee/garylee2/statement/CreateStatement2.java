package jdbc_2.eeit179.garylee.garylee2.statement;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import jdbc_2.eeit179.garylee.garylee1.util.JDBCutils;

public class CreateStatement2 {
	
	
	
	public Connection createConnection() throws Exception {
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(new File("src/jdbc.properties"));
		properties.load(fis);
		String user =properties.getProperty("user");
		String password =properties.getProperty("password");
		String url =properties.getProperty("url");
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(url, user, password);
		boolean status = !conn.isClosed();
		if(status) {
			System.out.println("開啟連線");
		}
		return conn;
	}
	
	public void closeConnection(Connection conn) throws Exception {
		if( conn !=null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}
	
	public void closeConnection(Connection conn,Statement st) throws Exception {
		if( st !=null) {
			st.close();
		}
		if( conn !=null) {
			conn.close();
			System.out.println("關閉連線");
		}
	}
	
	
	public static void main(String[] args) {
		/*
		 * 跟同學介紹實體化
		 * 實體化以前檔案在資料夾某個位置
		 * 實體化後就會把檔案放在Java JVM 可執行程式
		 */
		
		Statement st=null;
		Connection conn=null;
		CreateStatement2 createStatement = new CreateStatement2();
		String sql="INSERT INTO customers (name,email) VALUES ('s2','s2@mail.com')";
		try {
//			開連線
			conn = createStatement.createConnection();
//			產生statement
			st = conn.createStatement();
//			執行sql語句
			st.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
//				關閉連線 關閉st
				createStatement.closeConnection(conn,st);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
