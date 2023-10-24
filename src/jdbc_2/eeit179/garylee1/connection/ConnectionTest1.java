package jdbc_2.eeit179.garylee1.connection;

import static org.hamcrest.CoreMatchers.nullValue;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.junit.Test;

import com.mysql.cj.jdbc.Driver;

public class ConnectionTest1 {
	
	public static void main(String[] args) {
		Connection conn=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //不建議省略
//			Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
//			Driver driver = (Driver) clazz.newInstance();
//			DriverManager.registerDriver(driver);
			String url="jdbc:mysql://localhost:3306/mytestdb";
			String user="root";
			String password="5413gary";
			
			conn = DriverManager.getConnection(url, user, password);
			
			System.out.println(conn);
			
//			有連線物件情況下 會回傳false
			boolean status = !conn.isClosed();
			System.out.println("status:"+status);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				boolean status = !conn.isClosed();
				System.out.println("status:"+status);
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
		
	}
	
	

}
