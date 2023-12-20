package jdbc_2.eeit179.garylee.lab.lab01connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoConnection1 {
//	快捷main
	public static void main(String[] args) {
		
		try {
//			加載Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			/*
			 * jdbc:mysql://localhost:3306/db
			 * jdbc:mysql協議
			 * localhost:ip位置
			 * 3306:mysql默認
			 * db:DB name
			 */
			String url="jdbc:mysql://localhost:3306/mytestdb";
			String user="root";
			String password="5413gary";
//			取得連線Connection
			Connection conn = DriverManager.getConnection(url,user,password);
			boolean status=!conn.isClosed();
			System.out.println("連線狀態："+status);
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
