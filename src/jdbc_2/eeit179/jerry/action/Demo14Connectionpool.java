package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariDataSource;

import jdbc_2.eeit179.jerry.util.HikariUtil;

public class Demo14Connectionpool {
	
	private Connection conn;
	HikariUtil hikariUtil = new HikariUtil();
	
	public void createConnection() throws Exception{
		HikariDataSource ds = hikariUtil.openDataSource();
		conn = ds.getConnection();
		boolean status = !conn.isClosed();
		if(status) {
			System.out.println("開啟連線");
		}
	}
	
	public void closeConnection() throws SQLException {
		if(conn !=null) {
			conn.close();
			System.out.println("關閉連線");
			hikariUtil.closeDataSource();
			System.out.println("關閉datasource");
		}
	}
	
	
	public static void main(String[] args) {
		
	}

}
