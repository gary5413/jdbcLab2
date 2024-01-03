package jdbc_2.eeit179.garylee.lab.lab10connectionpool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

public class DemoConnetionPool {
	
	
	private Connection conn;
//	HikariCP hikariCP = new HikariCP() ;
//	DBCP dbcp= new DBCP();
	C3P0 c3p0=new C3P0();
	public void createConnetion() throws Exception {
//		HikariDataSource ds = hikariCP.openDataSource();
//		BasicDataSource ds = dbcp.openDataSource();
		ComboPooledDataSource ds = c3p0.openDataSource();
		conn= ds.getConnection();
		boolean status = !conn.isClosed();
		if(status) {
			System.out.println("Open Connection");
		}
	}
	
	public void closeConnection() throws SQLException {
		if(conn !=null) {
			conn.close();
			System.out.println("Close Connetiion");
//			hikariCP.closeDataSource();
//			dbcp.closeDataSource();
			c3p0.closeDataSource();
			System.out.println("Close datasource");
		}
	}
	
	
	public void queryCustomers() throws SQLException {
		String sql="SELECT * FROM user";
		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
//		boolean result = resultSet.next();
//		System.out.println("result:"+result);
		while(resultSet.next()) {
			System.out.println(resultSet.getInt(1)+","+resultSet.getString(2)+","+resultSet.getString(3));
		}
		resultSet.close();
		statement.close();
	}

	
	public static void main(String[] args) {
		DemoConnetionPool demoConnetionPool = new DemoConnetionPool();
		try {
			demoConnetionPool.createConnetion();
			demoConnetionPool.queryCustomers();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demoConnetionPool.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
