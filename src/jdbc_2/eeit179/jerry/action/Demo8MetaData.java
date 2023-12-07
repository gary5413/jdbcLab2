package jdbc_2.eeit179.jerry.action;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Demo8MetaData {
	private Connection conn;
	public void createConnection() throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/mytestdb";
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
	
	public void testDatabaseMetaData() throws SQLException {
		DatabaseMetaData metaData = conn.getMetaData();
		System.out.println("DatabaseProductName:"+metaData.getDatabaseProductName());
		System.out.println("DatabaseProductVersion:"+metaData.getDatabaseProductVersion());
		System.out.println("DriverName:"+metaData.getDriverName());
		System.out.println("DriverVersion:"+metaData.getDriverVersion());
		System.out.println("UserName"+metaData.getUserName());
	}
	
	public void testResultMetaData() throws SQLException {
		String sql="SELECT * FROM customers";
		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();
		ResultSetMetaData metaData = rs.getMetaData();
		System.out.println("ColumnCount:"+metaData.getColumnCount());
		System.out.println("ColumnCount(1):"+metaData.getColumnLabel(1));
		System.out.println("ColumnCount(2):"+metaData.getColumnLabel(2));
		System.out.println("ColumnTypeName(1):"+metaData.getColumnTypeName(1));
		System.out.println("ColumnTypeName(2):"+metaData.getColumnTypeName(2));
		System.out.println("ColumnDisplaySize(1):"+metaData.getColumnDisplaySize(1));
		System.out.println("ColumnDisplaySize(2):"+metaData.getColumnDisplaySize(2));
		rs.close();
		preState.close();
	}
	
	public static void main(String[] args) {
		Demo8MetaData demo8 = new Demo8MetaData();
		try {
			demo8.createConnection();
//			demo8.testDatabaseMetaData();
			demo8.testResultMetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				demo8.closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
