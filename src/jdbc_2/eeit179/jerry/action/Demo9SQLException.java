package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo9SQLException {
	private Connection conn;
	public void createConnection() throws SQLException {
//		可以省略 
//		Class.forName("com.mysql.cj.jdbc.Driver");
//		String url="jdbc:mysql://localhost:3306/mytestdb";
//		故意改錯的資料庫
		String url="jdbc:mysql://localhost:3306/mytestdb1";
		String user="root";
		String password="5413gary";
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
	public static void main(String[] args) {
		Demo9SQLException demo9 = new Demo9SQLException();
		try {
			demo9.createConnection();
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}finally {
			try {
				demo9.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
