package jdbc_2.eeit179.garylee.lab.lab09.product.dao;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class EtfDao {
private Connection conn;
	
	public void createConnection() throws Exception {
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
	
	public void insertEtfData(String stockId,String stockName,Integer stockQuantity) throws SQLException {
		String sql="insert into ETF_V(stock_id,stock_name,month_quantity) values(?,?,?)";
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		prepareStatement.setString(1, stockId);
		prepareStatement.setString(2, stockName);
		prepareStatement.setInt(3, stockQuantity);
		prepareStatement.execute();
		prepareStatement.close();
		System.out.println("Insert ETF data");
	}
	
	
}
