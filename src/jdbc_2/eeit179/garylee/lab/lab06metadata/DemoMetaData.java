package jdbc_2.eeit179.garylee.lab.lab06metadata;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Properties;



import jdbc_2.eeit179.garylee.lab.util.JDBCutils;

public class DemoMetaData {

	private Connection conn;

	public void CreateConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Properties properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc2.properties"));
		properties.load(fileInputStream);
		String url=properties.getProperty("url");
		String user=properties.getProperty("user");
		String password=properties.getProperty("password");
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
		DatabaseMetaData metaData= conn.getMetaData();
		System.out.println("DatabaseProductName:"+metaData.getDatabaseProductName());
		System.out.println("DatabaseProductName:"+metaData.getDatabaseProductVersion());
		System.out.println("DriverName:"+metaData.getDriverName());
		System.out.println("DriverVersion:"+metaData.getDriverVersion());
		System.out.println("UserName"+metaData.getUserName());
	}
	
	public void testResultMetaData() throws SQLException {
		String sql="SELECT * FROM user";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		ResultSet rs = prepareStatement.executeQuery();
		ResultSetMetaData metaData= rs.getMetaData();
//		回傳結果集的欄位數量
		System.out.println("ColumnCount:"+metaData.getColumnCount());
//		回傳欄位名稱/別名
		System.out.println("ColumnLabel(1):"+metaData.getColumnLabel(1));
		System.out.println("ColumnLabel(2):"+metaData.getColumnLabel(2));
//		回傳SQL資料型別
		System.out.println("ColumnTypeName(1):"+metaData.getColumnTypeName(1));
		System.out.println("ColumnTypeName(2):"+metaData.getColumnTypeName(2));
//		回傳欄位最大儲存尺寸
		System.out.println("ColumnDisplaySize(1):"+metaData.getColumnDisplaySize(1));
		System.out.println("ColumnDisplaySize(2):"+metaData.getColumnDisplaySize(2));
/*
 * 		傳回值: 
		0 代表不允許NULL 
		1 代表允許NULL 
		2 代表無法判斷的情形 
 */
		System.out.println("isNullable(1):"+metaData.isNullable(1));
		System.out.println("isNullable(5):"+metaData.isNullable(5));
		System.out.println("isNullable(7):"+metaData.isNullable(7));
		rs.close();
		prepareStatement.close();
	}
	
	
	public static void main(String[] args) {
		DemoMetaData demoMetaData = new DemoMetaData();
		try {
			demoMetaData.CreateConnection();
//			demoMetaData.testDatabaseMetaData();
			demoMetaData.testResultMetaData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
