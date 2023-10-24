package jdbc_2.eeit179.garylee1.connection;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class ConnectionTest3 {
	
	public static void main(String[] args) throws Exception {
//		String url="jdbc:mysql://localhost:3306/mytestdb";
//		String user="root";
//		String password="5413gary";
		Properties properties = new Properties();
		FileInputStream fis = new FileInputStream(new File("src/jdbc.properties"));
		properties.load(fis);
		String user =properties.getProperty("user");
		String password =properties.getProperty("password");
		String url =properties.getProperty("url");
		try (Connection conn = DriverManager.getConnection(url, user, password)) {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(conn);
//			有連線物件情況下 會回傳false 
			boolean status = !conn.isClosed();
			System.out.println("status:"+status);
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	

}
